package org.sdmlib.examples.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.examples.replication.ChatMsg;
import org.sdmlib.examples.replication.creators.ChatMsgSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.examples.replication.creators.ChatRootPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.examples.replication.creators.ChatMsgPO;
import org.sdmlib.examples.replication.ChatRoot;

public class ChatMsgPO extends PatternObject<ChatMsgPO, ChatMsg>
{
   public ChatMsgSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChatMsgSet matches = new ChatMsgSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChatMsg) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ChatMsgPO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMsg) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public ChatMsgPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChatMsg) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public ChatMsgPO hasTime(long value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public long getTime()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMsg) getCurrentMatch()).getTime();
      }
      return 0;
   }
   
   public ChatMsgPO withTime(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChatMsg) getCurrentMatch()).setTime(value);
      }
      return this;
   }
   
   public ChatMsgPO hasSender(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getSender()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMsg) getCurrentMatch()).getSender();
      }
      return null;
   }
   
   public ChatMsgPO withSender(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChatMsg) getCurrentMatch()).setSender(value);
      }
      return this;
   }
   
   public ChatRootPO hasRoot()
   {
      ChatRootPO result = new ChatRootPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ChatMsg.PROPERTY_ROOT, result);
      
      return result;
   }

   public ChatMsgPO hasRoot(ChatRootPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ChatMsg.PROPERTY_ROOT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ChatRoot getRoot()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMsg) this.getCurrentMatch()).getRoot();
      }
      return null;
   }

}

