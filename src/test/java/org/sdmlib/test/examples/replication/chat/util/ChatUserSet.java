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

import org.sdmlib.test.examples.replication.chat.ChatChannel;
import org.sdmlib.test.examples.replication.chat.ChatRoot;
import org.sdmlib.test.examples.replication.chat.ChatUser;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class ChatUserSet extends SimpleSet<ChatUser>
{

   public static final ChatUserSet EMPTY_SET = new ChatUserSet().withFlag(ChatUserSet.READONLY);


   public ChatUserPO hasChatUserPO()
   {
      return new ChatUserPO(this.toArray(new ChatUser[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public ChatUserSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChatUser>)value);
      }
      else if (value != null)
      {
         this.add((ChatUser) value);
      }
      
      return this;
   }
   
   public ChatUserSet without(ChatUser value)
   {
      this.remove(value);
      return this;
   }

   public ChatRootSet getChatRoot()
   {
      ChatRootSet result = new ChatRootSet();
      
      for (ChatUser obj : this)
      {
         result.add(obj.getChatRoot());
      }
      
      return result;
   }

   public ChatUserSet hasChatRoot(Object value)
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
      
      ChatUserSet answer = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if (neighbors.contains(obj.getChatRoot()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ChatUserSet withChatRoot(ChatRoot value)
   {
      for (ChatUser obj : this)
      {
         obj.withChatRoot(value);
      }
      
      return this;
   }

   public ChatChannelSet getChannels()
   {
      ChatChannelSet result = new ChatChannelSet();
      
      for (ChatUser obj : this)
      {
         result.addAll(obj.getChannels());
      }
      
      return result;
   }

   public ChatUserSet hasChannels(Object value)
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
      
      ChatUserSet answer = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getChannels()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ChatUserSet withChannels(ChatChannel value)
   {
      for (ChatUser obj : this)
      {
         obj.withChannels(value);
      }
      
      return this;
   }

   public ChatUserSet withoutChannels(ChatChannel value)
   {
      for (ChatUser obj : this)
      {
         obj.withoutChannels(value);
      }
      
      return this;
   }

   public StringList getUserName()
   {
      StringList result = new StringList();
      
      for (ChatUser obj : this)
      {
         result.add(obj.getUserName());
      }
      
      return result;
   }

   public ChatUserSet hasUserName(String value)
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if (value.equals(obj.getUserName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatUserSet hasUserName(String lower, String upper)
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if (lower.compareTo(obj.getUserName()) <= 0 && obj.getUserName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ChatUserSet withUserName(String value)
   {
      for (ChatUser obj : this)
      {
         obj.setUserName(value);
      }
      
      return this;
   }



   public ChatUserPO filterChatUserPO()
   {
      return new ChatUserPO(this.toArray(new ChatUser[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.replication.chat.ChatUser";
   }

   /**
    * Loop through the current set of ChatUser objects and collect those ChatUser objects where the userName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChatUser objects that match the parameter
    */
   public ChatUserSet filterUserName(String value)
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if (value.equals(obj.getUserName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChatUser objects and collect those ChatUser objects where the userName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChatUser objects that match the parameter
    */
   public ChatUserSet filterUserName(String lower, String upper)
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if (lower.compareTo(obj.getUserName()) <= 0 && obj.getUserName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public ChatUserSet()
   {
      // empty
   }

   public ChatUserSet(ChatUser... objects)
   {
      for (ChatUser obj : objects)
      {
         this.add(obj);
      }
   }

   public ChatUserSet(Collection<ChatUser> objects)
   {
      this.addAll(objects);
   }


   public ChatUserPO createChatUserPO()
   {
      return new ChatUserPO(this.toArray(new ChatUser[this.size()]));
   }


   @Override
   public ChatUserSet getNewList(boolean keyValue)
   {
      return new ChatUserSet();
   }


   /**
    * Loop through the current set of ChatUser objects and collect those ChatUser objects where the userName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ChatUser objects that match the parameter
    */
   public ChatUserSet createUserNameCondition(String value)
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if (value.equals(obj.getUserName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ChatUser objects and collect those ChatUser objects where the userName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ChatUser objects that match the parameter
    */
   public ChatUserSet createUserNameCondition(String lower, String upper)
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatUser obj : this)
      {
         if (lower.compareTo(obj.getUserName()) <= 0 && obj.getUserName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
