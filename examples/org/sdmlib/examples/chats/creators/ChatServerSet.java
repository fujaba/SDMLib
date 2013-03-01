/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.chats.ChatServer;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.models.modelsets.StringList;

public class ChatServerSet extends LinkedHashSet<ChatServer>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ChatServer elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public ChatServerSet with(ChatServer value)
   {
      this.add(value);
      return this;
   }
   
   public ChatServerSet without(ChatServer value)
   {
      this.remove(value);
      return this;
   }
   public StringList getAllMessages()
   {
      StringList result = new StringList();
      
      for (ChatServer obj : this)
      {
         result.add(obj.getAllMessages());
      }
      
      return result;
   }

   public ChatServerSet withAllMessages(String value)
   {
      for (ChatServer obj : this)
      {
         obj.withAllMessages(value);
      }
      
      return this;
   }


   public ChatServerSet withAllPeers(PeerProxySet value)
   {
      for (ChatServer obj : this)
      {
         obj.withAllPeers(value);
      }
      
      return this;
   }

   public PeerProxySet getAllPeers()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (ChatServer obj : this)
      {
         result.addAll(obj.getAllPeers());
      }
      
      return result;
   }



   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.ChatServer";
   }
}



