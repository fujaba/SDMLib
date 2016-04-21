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
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.couchspace.tasks.User;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;

public class UserCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      User.PROPERTY_NAME,
      User.PROPERTY_HANDLEDTASKS,
      User.PROPERTY_GROUPS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new User();
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

      if (User.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((User) target).getName();
      }

      if (User.PROPERTY_HANDLEDTASKS.equalsIgnoreCase(attribute))
      {
         return ((User) target).getHandledTasks();
      }

      if (User.PROPERTY_GROUPS.equalsIgnoreCase(attribute))
      {
         return ((User) target).getGroups();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (User.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((User) target).withName((String) value);
         return true;
      }

      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (User.PROPERTY_HANDLEDTASKS.equalsIgnoreCase(attrName))
      {
         ((User) target).withHandledTasks((Task) value);
         return true;
      }
      
      if ((User.PROPERTY_HANDLEDTASKS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((User) target).withoutHandledTasks((Task) value);
         return true;
      }

      if (User.PROPERTY_GROUPS.equalsIgnoreCase(attrName))
      {
         ((User) target).withGroups((UserGroup) value);
         return true;
      }
      
      if ((User.PROPERTY_GROUPS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((User) target).withoutGroups((UserGroup) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.couchspace.tasks.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
      public void removeObject(Object entity)
   {
      ((User) entity).removeYou();
   }
}