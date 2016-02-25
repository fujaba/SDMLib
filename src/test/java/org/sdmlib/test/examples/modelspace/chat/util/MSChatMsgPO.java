package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelspace.chat.MSChatMsg;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelPO;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannel;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMsgPO;

public class MSChatMsgPO extends PatternObject<MSChatMsgPO, MSChatMsg>
{

    public MSChatMsgSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MSChatMsgSet matches = new MSChatMsgSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MSChatMsg) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MSChatMsgPO(){
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MSChatMsgPO(MSChatMsg... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MSChatMsgPO hasText(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO hasText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO createText(String value)
   {
      this.startCreate().hasText(value).endCreate();
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatMsg) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public MSChatMsgPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatMsg) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public MSChatMsgPO hasTime(long value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO hasTime(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TIME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO createTime(long value)
   {
      this.startCreate().hasTime(value).endCreate();
      return this;
   }
   
   public long getTime()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatMsg) getCurrentMatch()).getTime();
      }
      return 0;
   }
   
   public MSChatMsgPO withTime(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatMsg) getCurrentMatch()).setTime(value);
      }
      return this;
   }
   
   public MSChatMsgPO hasSender(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_SENDER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO hasSender(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_SENDER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO createSender(String value)
   {
      this.startCreate().hasSender(value).endCreate();
      return this;
   }
   
   public String getSender()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatMsg) getCurrentMatch()).getSender();
      }
      return null;
   }
   
   public MSChatMsgPO withSender(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatMsg) getCurrentMatch()).setSender(value);
      }
      return this;
   }
   
   public MSChatChannelPO hasChannel()
   {
      MSChatChannelPO result = new MSChatChannelPO(new MSChatChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatMsg.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public MSChatChannelPO createChannel()
   {
      return this.startCreate().hasChannel().endCreate();
   }

   public MSChatMsgPO hasChannel(MSChatChannelPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatMsg.PROPERTY_CHANNEL);
   }

   public MSChatMsgPO createChannel(MSChatChannelPO tgt)
   {
      return this.startCreate().hasChannel(tgt).endCreate();
   }

   public MSChatChannel getChannel()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatMsg) this.getCurrentMatch()).getChannel();
      }
      return null;
   }

   public MSChatMsgPO filterText(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO filterText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO filterTime(long value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TIME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO filterTime(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_TIME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO filterSender(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_SENDER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO filterSender(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMsg.PROPERTY_SENDER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelPO filterChannel()
   {
      MSChatChannelPO result = new MSChatChannelPO(new MSChatChannel[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatMsg.PROPERTY_CHANNEL, result);
      
      return result;
   }

   public MSChatMsgPO filterChannel(MSChatChannelPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatMsg.PROPERTY_CHANNEL);
   }

}
