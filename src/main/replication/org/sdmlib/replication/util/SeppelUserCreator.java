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

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.replication.SeppelUser;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.replication.SeppelScope;

public class SeppelUserCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      SeppelUser.PROPERTY_LOGINNAME,
      SeppelUser.PROPERTY_PASSWORD,
      SeppelUser.PROPERTY_MASTERSPACE,
      SeppelUser.PROPERTY_SPACES,
      SeppelUser.PROPERTY_SCOPES,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new SeppelUser();
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

      if (SeppelUser.PROPERTY_LOGINNAME.equalsIgnoreCase(attribute))
      {
         return ((SeppelUser) target).getLoginName();
      }

      if (SeppelUser.PROPERTY_PASSWORD.equalsIgnoreCase(attribute))
      {
         return ((SeppelUser) target).getPassword();
      }

      if (SeppelUser.PROPERTY_MASTERSPACE.equalsIgnoreCase(attribute))
      {
         return ((SeppelUser) target).getMasterSpace();
      }

      if (SeppelUser.PROPERTY_SPACES.equalsIgnoreCase(attribute))
      {
         return ((SeppelUser) target).getSpaces();
      }

      if (SeppelUser.PROPERTY_SCOPES.equalsIgnoreCase(attribute))
      {
         return ((SeppelUser) target).getScopes();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (SeppelUser.PROPERTY_LOGINNAME.equalsIgnoreCase(attrName))
      {
         ((SeppelUser) target).withLoginName((String) value);
         return true;
      }

      if (SeppelUser.PROPERTY_PASSWORD.equalsIgnoreCase(attrName))
      {
         ((SeppelUser) target).withPassword((String) value);
         return true;
      }

      if (SeppelUser.PROPERTY_MASTERSPACE.equalsIgnoreCase(attrName))
      {
         ((SeppelUser) target).setMasterSpace((SeppelSpaceProxy) value);
         return true;
      }

      if (SeppelUser.PROPERTY_SPACES.equalsIgnoreCase(attrName))
      {
         ((SeppelUser) target).withSpaces((SeppelSpaceProxy) value);
         return true;
      }
      
      if ((SeppelUser.PROPERTY_SPACES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((SeppelUser) target).withoutSpaces((SeppelSpaceProxy) value);
         return true;
      }

      if (SeppelUser.PROPERTY_SCOPES.equalsIgnoreCase(attrName))
      {
         ((SeppelUser) target).withScopes((SeppelScope) value);
         return true;
      }
      
      if ((SeppelUser.PROPERTY_SCOPES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((SeppelUser) target).withoutScopes((SeppelScope) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((SeppelUser) entity).removeYou();
   }
}
