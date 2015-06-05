package org.sdmlib.replication;

import java.util.List;

import de.uniks.networkparser.list.SimpleKeyValueList;
import de.uniks.networkparser.list.SimpleList;
import de.uniks.networkparser.list.SortedList;

public class ChangeEventList
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

}
