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
import java.util.Collections;

import org.sdmlib.examples.replication.chat.ChatRoot;
import org.sdmlib.examples.replication.chat.ChatUser;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.examples.replication.chat.util.ChatUserSet;

public class ChatRootSet extends SDMSet<ChatRoot>
{

   public static final ChatRootSet EMPTY_SET = new ChatRootSet().withReadOnly(true);


   public ChatRootPO hasChatRootPO()
   {
      return new ChatRootPO(this.toArray(new ChatRoot[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.examples.replication.chat.ChatRoot";
   }


   @SuppressWarnings("unchecked")
   public ChatRootSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChatRoot>)value);
      }
      else if (value != null)
      {
         this.add((ChatRoot) value);
      }
      
      return this;
   }
   
   public ChatRootSet without(ChatRoot value)
   {
      this.remove(value);
      return this;
   }

   public ChatUserSet getUsers()
   {
      ChatUserSet result = new ChatUserSet();
      
      for (ChatRoot obj : this)
      {
         result.addAll(obj.getUsers());
      }
      
      return result;
   }

   public ChatRootSet hasUsers(Object value)
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
      
      ChatRootSet answer = new ChatRootSet();
      
      for (ChatRoot obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getUsers()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ChatRootSet withUsers(ChatUser value)
   {
      for (ChatRoot obj : this)
      {
         obj.withUsers(value);
      }
      
      return this;
   }

   public ChatRootSet withoutUsers(ChatUser value)
   {
      for (ChatRoot obj : this)
      {
         obj.withoutUsers(value);
      }
      
      return this;
   }

}
