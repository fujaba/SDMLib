package org.sdmlib.examples.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.replication.ChatRoot;
import org.sdmlib.examples.replication.creators.ChatRootSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.replication.creators.ChatMsgPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.replication.creators.ChatRootPO;
import org.sdmlib.examples.replication.ChatMsg;
import org.sdmlib.examples.replication.creators.ChatMsgSet;

public class ChatRootPO extends PatternObject<ChatRootPO, ChatRoot>
{
   public ChatRootSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChatRootSet matches = new ChatRootSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChatRoot) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ChatMsgPO hasMsgs()
   {
      ChatMsgPO result = new ChatMsgPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ChatRoot.PROPERTY_MSGS, result);
      
      return result;
   }

   public ChatRootPO hasMsgs(ChatMsgPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ChatRoot.PROPERTY_MSGS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ChatMsgSet getMsgs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatRoot) this.getCurrentMatch()).getMsgs();
      }
      return null;
   }

}

