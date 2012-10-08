package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.PeerToPeerChatArgs;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;

public class PeerToPeerChatArgsSet extends LinkedHashSet<PeerToPeerChatArgs>
{
   public StringList getUserName()
   {
      StringList result = new StringList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getUserName());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withUserName(String value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withUserName(value);
      }
      
      return this;
   }

   public intList getLocalPort()
   {
      intList result = new intList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getLocalPort());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withLocalPort(int value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withLocalPort(value);
      }
      
      return this;
   }

   public StringList getPeerIp()
   {
      StringList result = new StringList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getPeerIp());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withPeerIp(String value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withPeerIp(value);
      }
      
      return this;
   }

   public intList getPeerPort()
   {
      intList result = new intList();
      
      for (PeerToPeerChatArgs obj : this)
      {
         result.add(obj.getPeerPort());
      }
      
      return result;
   }

   public PeerToPeerChatArgsSet withPeerPort(int value)
   {
      for (PeerToPeerChatArgs obj : this)
      {
         obj.withPeerPort(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PeerToPeerChatArgs elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public PeerToPeerChatArgsSet with(PeerToPeerChatArgs value)
   {
      this.add(value);
      return this;
   }
   
   public PeerToPeerChatArgsSet without(PeerToPeerChatArgs value)
   {
      this.remove(value);
      return this;
   }
}



