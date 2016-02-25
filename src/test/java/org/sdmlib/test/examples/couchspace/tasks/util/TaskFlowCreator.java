/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.test.examples.couchspace.tasks.util;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;
import org.sdmlib.test.examples.couchspace.tasks.Task;

public class TaskFlowCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      TaskFlow.PROPERTY_TITLE,
      TaskFlow.PROPERTY_TASKS,
      TaskFlow.PROPERTY_FIRSTTASKS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new TaskFlow();
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

      if (TaskFlow.PROPERTY_TITLE.equalsIgnoreCase(attribute))
      {
         return ((TaskFlow) target).getTitle();
      }

      if (TaskFlow.PROPERTY_TASKS.equalsIgnoreCase(attribute))
      {
         return ((TaskFlow) target).getTasks();
      }

      if (TaskFlow.PROPERTY_FIRSTTASKS.equalsIgnoreCase(attribute))
      {
         return ((TaskFlow) target).getFirstTasks();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (TaskFlow.PROPERTY_TITLE.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).withTitle((String) value);
         return true;
      }

      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (TaskFlow.PROPERTY_TASKS.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).withTasks((Task) value);
         return true;
      }
      
      if ((TaskFlow.PROPERTY_TASKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).withoutTasks((Task) value);
         return true;
      }

      if (TaskFlow.PROPERTY_FIRSTTASKS.equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).withFirstTasks((Task) value);
         return true;
      }
      
      if ((TaskFlow.PROPERTY_FIRSTTASKS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((TaskFlow) target).withoutFirstTasks((Task) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.couchspace.tasks.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((TaskFlow) entity).removeYou();
   }
}
