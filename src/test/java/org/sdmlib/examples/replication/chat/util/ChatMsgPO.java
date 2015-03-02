package org.sdmlib.examples.replication.chat.util;

import org.sdmlib.examples.replication.chat.ChatChannel;
import org.sdmlib.examples.replication.chat.ChatMsg;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;

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
      newInstance(org.sdmlib.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChatMsgPO(ChatMsg... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.examples.replication.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
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

}
