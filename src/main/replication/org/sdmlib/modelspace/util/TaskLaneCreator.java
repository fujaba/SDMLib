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
   
package org.sdmlib.modelspace.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.IdMap;
import org.sdmlib.modelspace.TaskLane;
import org.sdmlib.modelspace.TaskBoard;
import org.sdmlib.modelspace.Task;

public class TaskLaneCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      TaskLane.PROPERTY_HOSTNAME,
      TaskLane.PROPERTY_PORTNO,
      TaskLane.PROPERTY_BOARD,
      TaskLane.PROPERTY_TASKS,
      TaskLane.PROPERTY_MYREQUESTS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new TaskLane();
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

      if (TaskLane.PROPERTY_HOSTNAME.equalsIgnoreCase(attribute))
      {
         return ((TaskLane) target).getHostName();
      }

      if (TaskLane.PROPERTY_PORTNO.equalsIgnoreCase(attribute))
      {
         return ((TaskLane) target).getPortNo();
      }

      if (TaskLane.PROPERTY_BOARD.equalsIgnoreCase(attribute))
      {
         return ((TaskLane) target).getBoard();
      }

      if (TaskLane.PROPERTY_TASKS.equalsIgnoreCase(attribute))
      {
         return ((TaskLane) target).getTasks();
      }

      if (TaskLane.PROPERTY_MYREQUESTS.equalsIgnoreCase(attribute))
      {
         return ((TaskLane) target).getMyRequests();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (TaskLane.PROPERTY_HOSTNAME.equalsIgnoreCase(attrName))
      {
         ((TaskLane) target).withHostName((String) value);
         return true;
      }

      if (TaskLane.PROPERTY_PORTNO.equalsIgnoreCase(attrName))
      {
         ((TaskLane) target).withPortNo(Long.parseLong(value.toString()));
         return true;
      }

      if (TaskLane.PROPERTY_BOARD.equalsIgnoreCase(attrName))
      {
         ((TaskLane) target).setBoard((TaskBoard) value);
         return true;
      }

      if (TaskLane.PROPERTY_TASKS.equalsIgnoreCase(attrName))
      {
         ((TaskLane) target).withTasks((Task) value);
         return true;
      }
      
      if ((TaskLane.PROPERTY_TASKS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((TaskLane) target).withoutTasks((Task) value);
         return true;
      }

      if (TaskLane.PROPERTY_MYREQUESTS.equalsIgnoreCase(attrName))
      {
         ((TaskLane) target).withMyRequests((Task) value);
         return true;
      }
      
      if ((TaskLane.PROPERTY_MYREQUESTS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((TaskLane) target).withoutMyRequests((Task) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((TaskLane) entity).removeYou();
   }
}
