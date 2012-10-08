package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.models.modelsets.StringList;

public class PeerToPeerChatSet extends LinkedHashSet<PeerToPeerChat>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PeerToPeerChat elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public PeerToPeerChatSet with(PeerToPeerChat value)
   {
      this.add(value);
      return this;
   }
   
   public PeerToPeerChatSet without(PeerToPeerChat value)
   {
      this.remove(value);
      return this;
   }
}



