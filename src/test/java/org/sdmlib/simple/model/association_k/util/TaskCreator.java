/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.association_k.util;

import org.sdmlib.simple.model.association_k.Task;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class TaskCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Task.PROPERTY_NAME,
      Task.PROPERTY_PARENTTASKS,
      Task.PROPERTY_SUBTASKS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Task();
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

      if (Task.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getName();
      }

      if (Task.PROPERTY_PARENTTASKS.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getParentTasks();
      }

      if (Task.PROPERTY_SUBTASKS.equalsIgnoreCase(attribute))
      {
         return ((Task) target).getSubTasks();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Task.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Task) target).setName((String) value);
         return true;
      }

      if (SendableEntityCreator.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Task.PROPERTY_PARENTTASKS.equalsIgnoreCase(attrName))
      {
         ((Task) target).withParentTasks((Task) value);
         return true;
      }
      
      if ((Task.PROPERTY_PARENTTASKS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Task) target).withoutParentTasks((Task) value);
         return true;
      }

      if (Task.PROPERTY_SUBTASKS.equalsIgnoreCase(attrName))
      {
         ((Task) target).withSubTasks((Task) value);
         return true;
      }
      
      if ((Task.PROPERTY_SUBTASKS + SendableEntityCreator.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Task) target).withoutSubTasks((Task) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.simple.model.association_k.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((Task) entity).removeYou();
   }
}
