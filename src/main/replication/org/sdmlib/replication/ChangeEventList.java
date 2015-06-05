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

import java.util.List;

import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleList;
import de.uniks.networkparser.list.SortedList;
import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class ChangeEventList implements PropertyChangeInterface
{
   private ObjectChangeTable objectTable = new ObjectChangeTable();
   
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
            eventList.add(change);
            return 0;
         }
      }
      else
      {
         // there is one or more old event
         if (ChangeEvent.PLAIN.equals(propertyKind) || ChangeEvent.TO_ONE.equals(propertyKind))
         {
            ChangeEvent oldEvent = (ChangeEvent) attrChange;
            if (oldEvent.compareTo(change) <= 0)
            {
               // new change is newer
               attrTable.put(property, change);
               return 0;
            }
            else
            {
               // new change is older
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
               eventList.add(change);
               
               return eventList.getPositionKey(change);
            }
            else
            {
               // yes this target object has been here before
               if (oldChange.compareTo(change) <= 0)
               {
                  // new change is newer
                  eventList.remove(oldChange);
                  
                  eventList.add(change);
                  
                  return eventList.getPositionKey(change);
               }
               else
               {
                  // oldChange is newer
                  return -1;
               }
            }
         }
      }      
   }
   
   
   public static class ObjectChangeTable extends SimpleKeyValueList<String, AttributeChangeTable>
   {
      
   }
   
   public static class AttributeChangeTable extends SimpleKeyValueList<String, Object>
   {
      
   }
   
   public static class TimeSortedChangeList extends SortedList<ChangeEvent>
   {
      public TimeSortedChangeList()
      {
         withComparator(ChangeEvent.timeComparator);
      }

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
   }
  
   public List<ChangeEvent> getChanges()
   {
      SimpleList<ChangeEvent> result = new SimpleList<ChangeEvent>();
      
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