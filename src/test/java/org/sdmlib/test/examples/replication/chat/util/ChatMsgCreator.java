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
   
package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.replication.chat.ChatChannel;
import org.sdmlib.test.examples.replication.chat.ChatMsg;

import de.uniks.networkparser.IdMap;

public class ChatMsgCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ChatMsg.PROPERTY_TEXT,
      ChatMsg.PROPERTY_TIME,
      ChatMsg.PROPERTY_SENDER,
      ChatMsg.PROPERTY_CHANNEL,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ChatMsg();
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

      if (ChatMsg.PROPERTY_TEXT.equalsIgnoreCase(attribute))
      {
         return ((ChatMsg) target).getText();
      }

      if (ChatMsg.PROPERTY_TIME.equalsIgnoreCase(attribute))
      {
         return ((ChatMsg) target).getTime();
      }

      if (ChatMsg.PROPERTY_SENDER.equalsIgnoreCase(attribute))
      {
         return ((ChatMsg) target).getSender();
      }

      if (ChatMsg.PROPERTY_CHANNEL.equalsIgnoreCase(attribute))
      {
         return ((ChatMsg) target).getChannel();
      }
      
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (ChatMsg.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((ChatMsg) target).withText((String) value);
         return true;
      }

      if (ChatMsg.PROPERTY_TIME.equalsIgnoreCase(attrName))
      {
         ((ChatMsg) target).withTime(Long.parseLong(value.toString()));
         return true;
      }

      if (ChatMsg.PROPERTY_SENDER.equalsIgnoreCase(attrName))
      {
         ((ChatMsg) target).withSender((String) value);
         return true;
      }

      if (ChatMsg.PROPERTY_CHANNEL.equalsIgnoreCase(attrName))
      {
         ((ChatMsg) target).setChannel((ChatChannel) value);
         return true;
      }
      
      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ChatMsg) entity).removeYou();
   }
}
