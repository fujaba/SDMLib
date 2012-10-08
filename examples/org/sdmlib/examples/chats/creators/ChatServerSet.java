package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.ChatServer;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.model.taskflows.creators.PeerProxySet;

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

}


