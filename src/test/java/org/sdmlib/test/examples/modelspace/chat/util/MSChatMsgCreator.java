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
import org.sdmlib.test.examples.modelspace.chat.MSChatMsg;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannel;

public class MSChatMsgCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MSChatMsg.PROPERTY_TEXT,
      MSChatMsg.PROPERTY_TIME,
      MSChatMsg.PROPERTY_SENDER,
      MSChatMsg.PROPERTY_CHANNEL,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MSChatMsg();
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

      if (MSChatMsg.PROPERTY_TEXT.equalsIgnoreCase(attribute))
      {
         return ((MSChatMsg) target).getText();
      }

      if (MSChatMsg.PROPERTY_TIME.equalsIgnoreCase(attribute))
      {
         return ((MSChatMsg) target).getTime();
      }

      if (MSChatMsg.PROPERTY_SENDER.equalsIgnoreCase(attribute))
      {
         return ((MSChatMsg) target).getSender();
      }

      if (MSChatMsg.PROPERTY_CHANNEL.equalsIgnoreCase(attribute))
      {
         return ((MSChatMsg) target).getChannel();
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

      if (MSChatMsg.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((MSChatMsg) target).withText((String) value);
         return true;
      }

      if (MSChatMsg.PROPERTY_TIME.equalsIgnoreCase(attrName))
      {
         ((MSChatMsg) target).withTime(Long.parseLong(value.toString()));
         return true;
      }

      if (MSChatMsg.PROPERTY_SENDER.equalsIgnoreCase(attrName))
      {
         ((MSChatMsg) target).withSender((String) value);
         return true;
      }

      if (MSChatMsg.PROPERTY_CHANNEL.equalsIgnoreCase(attrName))
      {
         ((MSChatMsg) target).setChannel((MSChatChannel) value);
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
      ((MSChatMsg) entity).removeYou();
   }
}
