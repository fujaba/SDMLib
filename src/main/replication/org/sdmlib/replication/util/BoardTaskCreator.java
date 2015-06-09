/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.replication.util;

import java.util.HashMap;
import java.util.Map;

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.Task;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.event.ObjectMapEntry;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.replication.SeppelSpaceProxy;
import java.beans.PropertyChangeEvent;

public class BoardTaskCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      BoardTask.PROPERTY_NAME,
      BoardTask.PROPERTY_STATUS,
      Task.PROPERTY_LOGENTRIES,
      BoardTask.PROPERTY_NEXT,
      BoardTask.PROPERTY_PREV,
      BoardTask.PROPERTY_TASKOBJECTS,
      BoardTask.PROPERTY_LANE,
      BoardTask.PROPERTY_PROXY,
      BoardTask.PROPERTY_MANUALEXECUTION,
      BoardTask.PROPERTY_STASHEDPROPERTYCHANGEEVENT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new BoardTask();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (BoardTask.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getName();
      }

      if (BoardTask.PROPERTY_STATUS.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getStatus();
      }

      if (BoardTask.PROPERTY_LOGENTRIES.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getLogEntries();
      }

      if (BoardTask.PROPERTY_LANE.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getLane();
      }

      if (BoardTask.PROPERTY_NEXT.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getNext();
      }

      if (BoardTask.PROPERTY_PREV.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getPrev();
      }

      if (BoardTask.PROPERTY_TASKOBJECTS.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getTaskObjects();
      }

      if (BoardTask.PROPERTY_PROXY.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getProxy();
      }

      if (BoardTask.PROPERTY_MANUALEXECUTION.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).isManualExecution();
      }

      if (BoardTask.PROPERTY_STASHEDPROPERTYCHANGEEVENT.equalsIgnoreCase(attribute))
      {
         return ((BoardTask) target).getStashedPropertyChangeEvent();
      }
      
      return null;
   }
   
   @SuppressWarnings("unchecked")
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (BoardTask.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withName((String) value);
         return true;
      }

      if (BoardTask.PROPERTY_STATUS.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withStatus((String) value);
         return true;
      }

      if (BoardTask.PROPERTY_LOGENTRIES.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withLogEntries((LogEntry) value);
         return true;
      }
      
      if ((BoardTask.PROPERTY_LOGENTRIES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withoutLogEntries((LogEntry) value);
         return true;
      }

      if (BoardTask.PROPERTY_LANE.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).setLane((Lane) value);
         return true;
      }

      if (BoardTask.PROPERTY_NEXT.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withNext((BoardTask) value);
         return true;
      }
      
      if ((BoardTask.PROPERTY_NEXT + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withoutNext((BoardTask) value);
         return true;
      }

      if (BoardTask.PROPERTY_PREV.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withPrev((BoardTask) value);
         return true;
      }

      if ((BoardTask.PROPERTY_PREV + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withoutPrev((BoardTask) value);
         return true;
      }
      
      if (BoardTask.PROPERTY_TASKOBJECTS.equalsIgnoreCase(attrName))
      {
         if(value instanceof ObjectMapEntry) {
            ObjectMapEntry item = (ObjectMapEntry) value;
            ((BoardTask) target).getTaskObjects().put(""+item.getKey(), item.getValue());
            return true;
         }
         if(value instanceof Map<?,?>) {
            ((BoardTask) target).withTaskObjects((HashMap<String, Object>) value);
            return true;
         }  
      }

      if (BoardTask.PROPERTY_PROXY.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).setProxy((SeppelSpaceProxy) value);
         return true;
      }

      if (BoardTask.PROPERTY_MANUALEXECUTION.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withManualExecution((Boolean) value);
         return true;
      }

      if (BoardTask.PROPERTY_STASHEDPROPERTYCHANGEEVENT.equalsIgnoreCase(attrName))
      {
         ((BoardTask) target).withStashedPropertyChangeEvent((PropertyChangeEvent) value);
         return true;
      }

      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((BoardTask) entity).removeYou();
   }
}
