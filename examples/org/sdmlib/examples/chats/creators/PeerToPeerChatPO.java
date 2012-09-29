package org.sdmlib.examples.chats.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.examples.chats.creators.PeerToPeerChatSet;

public class PeerToPeerChatPO extends PatternObject<PeerToPeerChatPO, PeerToPeerChat>
{
   public PeerToPeerChatSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PeerToPeerChatSet matches = new PeerToPeerChatSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PeerToPeerChat) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

