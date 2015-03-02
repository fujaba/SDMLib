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

import java.util.Collection;

import org.sdmlib.examples.replication.chat.ChatChannel;
import org.sdmlib.examples.replication.chat.ChatMsg;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.longList;

public class ChatMsgSet extends SDMSet<ChatMsg>
{

   public static final ChatMsgSet EMPTY_SET = new ChatMsgSet().withReadOnly(true);


   public ChatMsgPO hasChatMsgPO()
   {
      return new ChatMsgPO(this.toArray(new ChatMsg[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.replication.chat.ChatMsg";
   }


   @SuppressWarnings("unchecked")
   public ChatMsgSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChatMsg>)value);
      }
      else if (value != null)
      {
         this.add((ChatMsg) value);
      }
      
      return this;
   }
   
   public ChatMsgSet without(ChatMsg value)
   {
      this.remove(value);
      return this;
   }

   public StringList getText()
   {
      StringList result = new StringList();
      
      for (ChatMsg obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public ChatMsgSet hasText(String value)
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatMsg obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatMsgSet hasText(String lower, String upper)
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatMsg obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatMsgSet withText(String value)
   {
      for (ChatMsg obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }

   public longList getTime()
   {
      longList result = new longList();
      
      for (ChatMsg obj : this)
      {
         result.add(obj.getTime());
      }
      
      return result;
   }

   public ChatMsgSet hasTime(long value)
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatMsg obj : this)
      {
         if (value == obj.getTime())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatMsgSet hasTime(long lower, long upper)
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatMsg obj : this)
      {
         if (lower <= obj.getTime() && obj.getTime() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatMsgSet withTime(long value)
   {
      for (ChatMsg obj : this)
      {
         obj.setTime(value);
      }
      
      return this;
   }

   public StringList getSender()
   {
      StringList result = new StringList();
      
      for (ChatMsg obj : this)
      {
         result.add(obj.getSender());
      }
      
      return result;
   }

   public ChatMsgSet hasSender(String value)
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatMsg obj : this)
      {
         if (value.equals(obj.getSender()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatMsgSet hasSender(String lower, String upper)
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatMsg obj : this)
      {
         if (lower.compareTo(obj.getSender()) <= 0 && obj.getSender().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatMsgSet withSender(String value)
   {
      for (ChatMsg obj : this)
      {
         obj.setSender(value);
      }
      
      return this;
   }

   public ChatChannelSet getChannel()
   {
      ChatChannelSet result = new ChatChannelSet();
      
      for (ChatMsg obj : this)
      {
         result.add(obj.getChannel());
      }
      
      return result;
   }

   public ChatMsgSet hasChannel(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      ChatMsgSet answer = new ChatMsgSet();
      
      for (ChatMsg obj : this)
      {
         if (neighbors.contains(obj.getChannel()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ChatMsgSet withChannel(ChatChannel value)
   {
      for (ChatMsg obj : this)
      {
         obj.withChannel(value);
      }
      
      return this;
   }

}
