package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannel;
import org.sdmlib.test.examples.modelspace.chat.MSChatMsg;

public class MSChatChannelPO extends PatternObject<MSChatChannelPO, MSChatChannel>
{

    public MSChatChannelSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MSChatChannelSet matches = new MSChatChannelSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MSChatChannel) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MSChatChannelPO(){
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MSChatChannelPO(MSChatChannel... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MSChatMsgPO hasMsgs()
   {
      MSChatMsgPO result = new MSChatMsgPO(new MSChatMsg[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public MSChatMsgPO createMsgs()
   {
      return this.startCreate().hasMsgs().endCreate();
   }

   public MSChatChannelPO hasMsgs(MSChatMsgPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannel.PROPERTY_MSGS);
   }

   public MSChatChannelPO createMsgs(MSChatMsgPO tgt)
   {
      return this.startCreate().hasMsgs(tgt).endCreate();
   }

   public MSChatMsgSet getMsgs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatChannel) this.getCurrentMatch()).getMsgs();
      }
      return null;
   }

   public MSChatChannelPO hasTask(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannel.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelPO hasTask(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannel.PROPERTY_TASK)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelPO createTask(String value)
   {
      this.startCreate().hasTask(value).endCreate();
      return this;
   }
   
   public String getTask()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatChannel) getCurrentMatch()).getTask();
      }
      return null;
   }
   
   public MSChatChannelPO withTask(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatChannel) getCurrentMatch()).setTask(value);
      }
      return this;
   }
   
   public MSChatChannelPO filterTask(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannel.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelPO filterTask(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannel.PROPERTY_TASK)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO filterMsgs()
   {
      MSChatMsgPO result = new MSChatMsgPO(new MSChatMsg[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public MSChatChannelPO filterMsgs(MSChatMsgPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannel.PROPERTY_MSGS);
   }


   public MSChatChannelPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MSChatChannelPO createTaskCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannel.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelPO createTaskCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannel.PROPERTY_TASK)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelPO createTaskAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannel.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMsgPO createMsgsPO()
   {
      MSChatMsgPO result = new MSChatMsgPO(new MSChatMsg[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public MSChatMsgPO createMsgsPO(String modifier)
   {
      MSChatMsgPO result = new MSChatMsgPO(new MSChatMsg[]{});
      
      result.setModifier(modifier);
      super.hasLink(MSChatChannel.PROPERTY_MSGS, result);
      
      return result;
   }

   public MSChatChannelPO createMsgsLink(MSChatMsgPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannel.PROPERTY_MSGS);
   }

   public MSChatChannelPO createMsgsLink(MSChatMsgPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MSChatChannel.PROPERTY_MSGS, modifier);
   }

}
