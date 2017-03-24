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
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;

import de.uniks.networkparser.IdMap;

public class MSChatGroupCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      MSChatGroup.PROPERTY_MEMBERS,
      MSChatGroup.PROPERTY_CHANNELS,
      MSChatGroup.PROPERTY_TASK,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new MSChatGroup();
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

      if (MSChatGroup.PROPERTY_MEMBERS.equalsIgnoreCase(attribute))
      {
         return ((MSChatGroup) target).getMembers();
      }

      if (MSChatGroup.PROPERTY_CHANNELS.equalsIgnoreCase(attribute))
      {
         return ((MSChatGroup) target).getChannels();
      }

      if (MSChatGroup.PROPERTY_TASK.equalsIgnoreCase(attribute))
      {
         return ((MSChatGroup) target).getTask();
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

      if (MSChatGroup.PROPERTY_MEMBERS.equalsIgnoreCase(attrName))
      {
         ((MSChatGroup) target).withMembers((MSChatMember) value);
         return true;
      }
      
      if ((MSChatGroup.PROPERTY_MEMBERS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((MSChatGroup) target).withoutMembers((MSChatMember) value);
         return true;
      }

      if (MSChatGroup.PROPERTY_CHANNELS.equalsIgnoreCase(attrName))
      {
         ((MSChatGroup) target).withChannels((MSChatChannelDescription) value);
         return true;
      }
      
      if ((MSChatGroup.PROPERTY_CHANNELS + REMOVE).equalsIgnoreCase(attrName))
      {
         ((MSChatGroup) target).withoutChannels((MSChatChannelDescription) value);
         return true;
      }

      if (MSChatGroup.PROPERTY_TASK.equalsIgnoreCase(attrName))
      {
         ((MSChatGroup) target).withTask((String) value);
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
      ((MSChatGroup) entity).removeYou();
   }
}
