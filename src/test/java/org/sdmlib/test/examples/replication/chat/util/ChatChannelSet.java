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

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.replication.chat.ChatChannel;
import org.sdmlib.test.examples.replication.chat.ChatMsg;
import org.sdmlib.test.examples.replication.chat.ChatUser;

import de.uniks.networkparser.list.ObjectSet;

public class ChatChannelSet extends SDMSet<ChatChannel>
{

   public static final ChatChannelSet EMPTY_SET = new ChatChannelSet().withFlag(ChatChannelSet.READONLY);


   public ChatChannelPO hasChatChannelPO()
   {
      return new ChatChannelPO(this.toArray(new ChatChannel[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ChatChannelSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChatChannel>)value);
      }
      else if (value != null)
      {
         this.add((ChatChannel) value);
      }
      
      return this;
   }
   
   public ChatChannelSet without(ChatChannel value)
   {
      this.remove(value);
      return this;
   }

   public ChatUserSet getUsers()
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatChannel obj : this)
      {
         result.addAll(obj.getUsers());
      }
      
      return result;
   }

   public ChatChannelSet hasUsers(Object value)
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
      
      ChatChannelSet answer = new ChatChannelSet();
      
      for (ChatChannel obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getUsers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ChatChannelSet withUsers(ChatUser value)
   {
      for (ChatChannel obj : this)
      {
         obj.withUsers(value);
      }
      
      return this;
   }

   public ChatChannelSet withoutUsers(ChatUser value)
   {
      for (ChatChannel obj : this)
      {
         obj.withoutUsers(value);
      }
      
      return this;
   }

   public ChatMsgSet getMsgs()
   {
      ChatMsgSet result = new ChatMsgSet();
      
      for (ChatChannel obj : this)
      {
         result.addAll(obj.getMsgs());
      }
      
      return result;
   }

   public ChatChannelSet hasMsgs(Object value)
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
      
      ChatChannelSet answer = new ChatChannelSet();
      
      for (ChatChannel obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMsgs()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ChatChannelSet withMsgs(ChatMsg value)
   {
      for (ChatChannel obj : this)
      {
         obj.withMsgs(value);
      }
      
      return this;
   }

   public ChatChannelSet withoutMsgs(ChatMsg value)
   {
      for (ChatChannel obj : this)
      {
         obj.withoutMsgs(value);
      }
      
      return this;
   }



   public ChatChannelPO filterChatChannelPO()
   {
      return new ChatChannelPO(this.toArray(new ChatChannel[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.replication.chat.ChatChannel";
   }

   public ChatChannelSet()
   {
      // empty
   }

   public ChatChannelSet(ChatChannel... objects)
   {
      for (ChatChannel obj : objects)
      {
         this.add(obj);
      }
   }

   public ChatChannelSet(Collection<ChatChannel> objects)
   {
      this.addAll(objects);
   }


   public ChatChannelPO createChatChannelPO()
   {
      return new ChatChannelPO(this.toArray(new ChatChannel[this.size()]));
   }


   @Override
   public ChatChannelSet getNewList(boolean keyValue)
   {
      return new ChatChannelSet();
   }
}
