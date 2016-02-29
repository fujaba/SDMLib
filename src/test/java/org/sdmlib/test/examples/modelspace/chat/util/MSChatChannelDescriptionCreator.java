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
   
package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;

public class MSChatChannelDescriptionCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MSChatChannelDescription.PROPERTY_NAME,
      MSChatChannelDescription.PROPERTY_LOCATION,
      MSChatChannelDescription.PROPERTY_GROUP,
      MSChatChannelDescription.PROPERTY_MEMBERS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MSChatChannelDescription();
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

      if (MSChatChannelDescription.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((MSChatChannelDescription) target).getName();
      }

      if (MSChatChannelDescription.PROPERTY_LOCATION.equalsIgnoreCase(attribute))
      {
         return ((MSChatChannelDescription) target).getLocation();
      }

      if (MSChatChannelDescription.PROPERTY_GROUP.equalsIgnoreCase(attribute))
      {
         return ((MSChatChannelDescription) target).getGroup();
      }

      if (MSChatChannelDescription.PROPERTY_MEMBERS.equalsIgnoreCase(attribute))
      {
         return ((MSChatChannelDescription) target).getMembers();
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

      if (MSChatChannelDescription.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((MSChatChannelDescription) target).withName((String) value);
         return true;
      }

      if (MSChatChannelDescription.PROPERTY_LOCATION.equalsIgnoreCase(attrName))
      {
         ((MSChatChannelDescription) target).withLocation((String) value);
         return true;
      }

      if (MSChatChannelDescription.PROPERTY_GROUP.equalsIgnoreCase(attrName))
      {
         ((MSChatChannelDescription) target).setGroup((MSChatGroup) value);
         return true;
      }

      if (MSChatChannelDescription.PROPERTY_MEMBERS.equalsIgnoreCase(attrName))
      {
         ((MSChatChannelDescription) target).withMembers((MSChatMember) value);
         return true;
      }
      
      if ((MSChatChannelDescription.PROPERTY_MEMBERS + IdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MSChatChannelDescription) target).withoutMembers((MSChatMember) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MSChatChannelDescription) entity).removeYou();
   }
}
