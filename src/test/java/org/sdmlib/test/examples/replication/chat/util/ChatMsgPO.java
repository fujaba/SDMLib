package org.sdmlib.test.examples.replication.chat.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.replication.chat.ChatChannel;
import org.sdmlib.test.examples.replication.chat.ChatMsg;

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


   public ChatMsgPO(){
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChatMsgPO(ChatMsg... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ChatMsgPO hasText(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatMsgPO hasText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatMsgPO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatMsgPO hasTime(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatMsgPO createTime(long value)
   {
      this.startCreate().hasTime(value).endCreate();
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
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatMsgPO hasSender(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChatMsgPO createSender(String value)
   {
      this.startCreate().hasSender(value).endCreate();
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
   
   public ChatChannelPO hasChannel()
   {
      ChatChannelPO result = new ChatChannelPO(new ChatChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatMsg.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public ChatChannelPO createChannel()
   {
      return this.startCreate().hasChannel().endCreate();
   }

   public ChatMsgPO hasChannel(ChatChannelPO tgt)
   {
      return hasLinkConstraint(tgt, ChatMsg.PROPERTY_CHANNEL);
   }

   public ChatMsgPO createChannel(ChatChannelPO tgt)
   {
      return this.startCreate().hasChannel(tgt).endCreate();
   }

   public ChatChannel getChannel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChatMsg) this.getCurrentMatch()).getChannel();
      }
      return null;
   }

   public ChatMsgPO filterText(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO filterText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO filterTime(long value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO filterTime(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO filterSender(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO filterSender(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatChannelPO filterChannel()
   {
      ChatChannelPO result = new ChatChannelPO(new ChatChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatMsg.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public ChatMsgPO filterChannel(ChatChannelPO tgt)
   {
      return hasLinkConstraint(tgt, ChatMsg.PROPERTY_CHANNEL);
   }


   public ChatMsgPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ChatMsgPO createSenderCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createSenderCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createSenderAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_SENDER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createTimeCondition(long value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createTimeCondition(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatMsgPO createTimeAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(ChatMsg.PROPERTY_TIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChatChannelPO createChannelPO()
   {
      ChatChannelPO result = new ChatChannelPO(new ChatChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChatMsg.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public ChatChannelPO createChannelPO(String modifier)
   {
      ChatChannelPO result = new ChatChannelPO(new ChatChannel[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChatMsg.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public ChatMsgPO createChannelLink(ChatChannelPO tgt)
   {
      return hasLinkConstraint(tgt, ChatMsg.PROPERTY_CHANNEL);
   }

   public ChatMsgPO createChannelLink(ChatChannelPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChatMsg.PROPERTY_CHANNEL, modifier);
   }

}
