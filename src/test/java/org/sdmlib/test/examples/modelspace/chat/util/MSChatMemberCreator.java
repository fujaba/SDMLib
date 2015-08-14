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
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;

public class MSChatMemberCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MSChatMember.PROPERTY_NAME,
      MSChatMember.PROPERTY_GROUP,
      MSChatMember.PROPERTY_CHANNELS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MSChatMember();
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

      if (MSChatMember.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((MSChatMember) target).getName();
      }

      if (MSChatMember.PROPERTY_GROUP.equalsIgnoreCase(attribute))
      {
         return ((MSChatMember) target).getGroup();
      }

      if (MSChatMember.PROPERTY_CHANNELS.equalsIgnoreCase(attribute))
      {
         return ((MSChatMember) target).getChannels();
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

      if (MSChatMember.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((MSChatMember) target).withName((String) value);
         return true;
      }

      if (MSChatMember.PROPERTY_GROUP.equalsIgnoreCase(attrName))
      {
         ((MSChatMember) target).setGroup((MSChatGroup) value);
         return true;
      }

      if (MSChatMember.PROPERTY_CHANNELS.equalsIgnoreCase(attrName))
      {
         ((MSChatMember) target).withChannels((MSChatChannelDescription) value);
         return true;
      }
      
      if ((MSChatMember.PROPERTY_CHANNELS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((MSChatMember) target).withoutChannels((MSChatChannelDescription) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((MSChatMember) entity).removeYou();
   }
}
