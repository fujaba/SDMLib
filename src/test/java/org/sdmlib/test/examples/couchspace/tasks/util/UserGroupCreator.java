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
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.User;

public class UserGroupCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      UserGroup.PROPERTY_NAME,
      UserGroup.PROPERTY_RESPONSIBLE,
      UserGroup.PROPERTY_MEMBERS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new UserGroup();
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

      if (UserGroup.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((UserGroup) target).getName();
      }

      if (UserGroup.PROPERTY_RESPONSIBLE.equalsIgnoreCase(attribute))
      {
         return ((UserGroup) target).getResponsible();
      }

      if (UserGroup.PROPERTY_MEMBERS.equalsIgnoreCase(attribute))
      {
         return ((UserGroup) target).getMembers();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (UserGroup.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((UserGroup) target).withName((String) value);
         return true;
      }

      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (UserGroup.PROPERTY_RESPONSIBLE.equalsIgnoreCase(attrName))
      {
         ((UserGroup) target).withResponsible((Task) value);
         return true;
      }
      
      if ((UserGroup.PROPERTY_RESPONSIBLE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((UserGroup) target).withoutResponsible((Task) value);
         return true;
      }

      if (UserGroup.PROPERTY_MEMBERS.equalsIgnoreCase(attrName))
      {
         ((UserGroup) target).withMembers((User) value);
         return true;
      }
      
      if ((UserGroup.PROPERTY_MEMBERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((UserGroup) target).withoutMembers((User) value);
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
      ((UserGroup) entity).removeYou();
   }
}
