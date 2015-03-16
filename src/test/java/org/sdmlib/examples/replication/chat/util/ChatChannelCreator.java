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
   
package org.sdmlib.examples.replication.chat.util;

import org.sdmlib.examples.replication.chat.ChatChannel;
import org.sdmlib.examples.replication.chat.ChatMsg;
import org.sdmlib.examples.replication.chat.ChatUser;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class ChatChannelCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChatChannel.PROPERTY_USERS,
      ChatChannel.PROPERTY_MSGS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ChatChannel();
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

      if (ChatChannel.PROPERTY_USERS.equalsIgnoreCase(attribute))
      {
         return ((ChatChannel) target).getUsers();
      }

      if (ChatChannel.PROPERTY_MSGS.equalsIgnoreCase(attribute))
      {
         return ((ChatChannel) target).getMsgs();
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

      if (ChatChannel.PROPERTY_USERS.equalsIgnoreCase(attrName))
      {
         ((ChatChannel) target).withUsers((ChatUser) value);
         return true;
      }
      
      if ((ChatChannel.PROPERTY_USERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChatChannel) target).withoutUsers((ChatUser) value);
         return true;
      }

      if (ChatChannel.PROPERTY_MSGS.equalsIgnoreCase(attrName))
      {
         ((ChatChannel) target).withMsgs((ChatMsg) value);
         return true;
      }
      
      if ((ChatChannel.PROPERTY_MSGS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChatChannel) target).withoutMsgs((ChatMsg) value);
         return true;
      }
      
      return false;
   }
   public static JsonIdMap createIdMap(String sessionID)
   {
      return org.sdmlib.examples.replication.chat.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ChatChannel) entity).removeYou();
   }
}
