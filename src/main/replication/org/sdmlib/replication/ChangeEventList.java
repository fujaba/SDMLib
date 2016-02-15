/*
   Copyright (c) 2015 zuendorf

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 

   The Software shall be used for Good, not Evil. 

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package org.sdmlib.replication;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.sdmlib.serialization.PropertyChangeInterface;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
*/
   public class ChangeEventList implements PropertyChangeInterface
{
   private ObjectChangeTable objectTable = new ObjectChangeTable();
   private LinkedList<ChangeEvent> overwrittenObjectsList = new LinkedList<>();
   
   private boolean collectOverwrittenChanges = false;

   public int addChange(ChangeEvent change)
   {
      String objectId = change.getObjectId();

      AttributeChangeTable attrTable = (AttributeChangeTable) objectTable.get(objectId);

      if (attrTable == null)
      {
         // first entry for this objectId
         attrTable = new AttributeChangeTable();
         objectTable.put(objectId, attrTable);
      }

      String property = change.getProperty();
      String propertyKind = change.getPropertyKind();

      Object attrChange = attrTable.get(property);

      if (attrChange == null)
      {
         // first entry for this attribute at this objectId
         // to-many?
         if (ChangeEvent.PLAIN.equals(propertyKind) || ChangeEvent.TO_ONE.equals(propertyKind))
         {
            // single entry
            attrTable.put(property, change);
            return 0;
         }
         else
         {
            // to-many
            TimeSortedChangeList eventList = new TimeSortedChangeList();
            attrTable.put(property, eventList);
            eventList.addSorted(change);
            return 0;
         }
      }
      else
      {
         // there is one or more old event
         if (ChangeEvent.PLAIN.equals(propertyKind) || ChangeEvent.TO_ONE.equals(propertyKind))
         {
            ChangeEvent oldEvent = (ChangeEvent) attrChange;
            if (oldEvent.compareTo(change) < 0)
            {
               // new change is newer
               Object overwrittenChange = attrTable.put(property, change);
               if(overwrittenChange != null && collectOverwrittenChanges){
            	   overwrittenObjectsList.add((ChangeEvent) overwrittenChange);
               }
               return 0;
            }
            else
            {
               // new change is older
            	if(collectOverwrittenChanges)
            		overwrittenObjectsList.add(change);
               return -1;
            }
         }
         else
         {
            TimeSortedChangeList eventList = (TimeSortedChangeList) attrChange;

            // if there is an old change on this object and this attribute with the same targetObject, compare time stamps
            ChangeEvent oldChange = eventList.findChange(change);

            if (oldChange == null)
            {
               // first occurences of this target id, insert change
               int pos = eventList.addSorted(change);

               return pos;
            }
            else
            {
               // yes this target object has been here before
               if (oldChange.compareTo(change) < 0)
               {
                  // new change is newer
                  eventList.remove(oldChange);
                  if(collectOverwrittenChanges)
                	  overwrittenObjectsList.add(oldChange);
                  return eventList.addSorted(change);
               }
               else
               {
                  // oldChange is newer
            	  if(collectOverwrittenChanges)
            		   overwrittenObjectsList.add(change);
                  return -1;
               }
            }
         }
      }      
   }


   public static class ObjectChangeTable extends LinkedHashMap<String, AttributeChangeTable>
   {

   }

   public static class AttributeChangeTable extends LinkedHashMap<String, Object>
   {

   }

   public static class TimeSortedChangeList extends ArrayList<ChangeEvent>
   {
      public ChangeEvent findChange(ChangeEvent change)
      {
         String targetId = change.getTargetId();

         for (ChangeEvent event : this)
         {
            if (event.getTargetId().equals(targetId))
            {
               return event;
            }
         }

         return null;
      }

      public int addSorted(ChangeEvent change)
      {
         this.add(change);
         Collections.sort(this);
         
         int pos = 0;
         
         for (ChangeEvent e : this)
         {
            if (e == change)
            {
               return pos;
            }
            
            if (e.getNewValue() != null)
            {
               pos++;
            }
         }
         
         // should not be reached. 
         return this.indexOf(change);
      }
   }

   public List<ChangeEvent> getChanges()
   {
      ArrayList<ChangeEvent> result = new ArrayList<ChangeEvent>();

      for (AttributeChangeTable attrTable : objectTable.values())
      {
         for (Object attrChange : attrTable.values())
         {
            if (attrChange instanceof ChangeEvent)
            {
               result.add((ChangeEvent) attrChange);
            }
            else 
            {
               result.addAll((TimeSortedChangeList)attrChange);
            }
         }
      }

      return result;
   }

   public ArrayList<ChangeEvent> getChanges(String valueObjectId)
   {
      ArrayList<ChangeEvent> result = new ArrayList<ChangeEvent>();

      AttributeChangeTable attrTable = objectTable.get(valueObjectId);

      for (Object attrChange : attrTable.values())
      {
         if (attrChange instanceof ChangeEvent)
         {
            result.add((ChangeEvent) attrChange);
         }
         else 
         {
            result.addAll((TimeSortedChangeList)attrChange);
         }
      }
      
      return result;
   }
   
   public List<ChangeEvent> getOverwrittenChanges()
   {
	  if(this.overwrittenObjectsList == null){
		  this.overwrittenObjectsList = new LinkedList<>();
	  }
      return this.overwrittenObjectsList;
   }
   
   public void clearOverwrittenChanges(){
	   overwrittenObjectsList.clear();
   }
   
   public void setCollectOverwrittenChanges(boolean collectOverwrittenChanges) {
	   this.collectOverwrittenChanges = collectOverwrittenChanges;
   }
   
   public ChangeEventList withCollectOverwrittenChanges(boolean collectOverwrittenChanges){
	   this.setCollectOverwrittenChanges(collectOverwrittenChanges);
	   return this;
   }

   //==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }


   //==========================================================================


   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
}
