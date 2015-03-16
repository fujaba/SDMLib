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
import org.sdmlib.examples.replication.chat.ChatRoot;
import org.sdmlib.examples.replication.chat.ChatUser;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class ChatUserCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChatUser.PROPERTY_USERNAME,
      ChatUser.PROPERTY_CHATROOT,
      ChatUser.PROPERTY_CHANNELS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ChatUser();
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

      if (ChatUser.PROPERTY_CHATROOT.equalsIgnoreCase(attribute))
      {
         return ((ChatUser) target).getChatRoot();
      }

      if (ChatUser.PROPERTY_CHANNELS.equalsIgnoreCase(attribute))
      {
         return ((ChatUser) target).getChannels();
      }

      if (ChatUser.PROPERTY_USERNAME.equalsIgnoreCase(attribute))
      {
         return ((ChatUser) target).getUserName();
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

      if (ChatUser.PROPERTY_CHATROOT.equalsIgnoreCase(attrName))
      {
         ((ChatUser) target).setChatRoot((ChatRoot) value);
         return true;
      }

      if (ChatUser.PROPERTY_CHANNELS.equalsIgnoreCase(attrName))
      {
         ((ChatUser) target).withChannels((ChatChannel) value);
         return true;
      }
      
      if ((ChatUser.PROPERTY_CHANNELS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ChatUser) target).withoutChannels((ChatChannel) value);
         return true;
      }

      if (ChatUser.PROPERTY_USERNAME.equalsIgnoreCase(attrName))
      {
         ((ChatUser) target).withUserName((String) value);
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
      ((ChatUser) entity).removeYou();
   }
}
