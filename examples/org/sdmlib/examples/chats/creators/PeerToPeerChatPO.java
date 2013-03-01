package org.sdmlib.examples.chats.creators;

import org.sdmlib.examples.chats.PeerToPeerChat;
import org.sdmlib.models.pattern.PatternObject;

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

