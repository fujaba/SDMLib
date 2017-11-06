package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelDescriptionPO;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatGroupPO;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMemberPO;

public class MSChatGroupPO extends PatternObject<MSChatGroupPO, MSChatGroup>
{

    public MSChatGroupSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MSChatGroupSet matches = new MSChatGroupSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MSChatGroup) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MSChatGroupPO(){
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MSChatGroupPO(MSChatGroup... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MSChatMemberPO hasMembers()
   {
      MSChatMemberPO result = new MSChatMemberPO(new MSChatMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatGroup.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public MSChatMemberPO createMembers()
   {
      return this.startCreate().hasMembers().endCreate();
   }

   public MSChatGroupPO hasMembers(MSChatMemberPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_MEMBERS);
   }

   public MSChatGroupPO createMembers(MSChatMemberPO tgt)
   {
      return this.startCreate().hasMembers(tgt).endCreate();
   }

   public MSChatMemberSet getMembers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatGroup) this.getCurrentMatch()).getMembers();
      }
      return null;
   }

   public MSChatChannelDescriptionPO hasChannels()
   {
      MSChatChannelDescriptionPO result = new MSChatChannelDescriptionPO(new MSChatChannelDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatGroup.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public MSChatChannelDescriptionPO createChannels()
   {
      return this.startCreate().hasChannels().endCreate();
   }

   public MSChatGroupPO hasChannels(MSChatChannelDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_CHANNELS);
   }

   public MSChatGroupPO createChannels(MSChatChannelDescriptionPO tgt)
   {
      return this.startCreate().hasChannels(tgt).endCreate();
   }

   public MSChatChannelDescriptionSet getChannels()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatGroup) this.getCurrentMatch()).getChannels();
      }
      return null;
   }

   public MSChatGroupPO hasTask(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatGroup.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatGroupPO hasTask(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatGroup.PROPERTY_TASK)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatGroupPO createTask(String value)
   {
      this.startCreate().hasTask(value).endCreate();
      return this;
   }
   
   public String getTask()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatGroup) getCurrentMatch()).getTask();
      }
      return null;
   }
   
   public MSChatGroupPO withTask(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatGroup) getCurrentMatch()).setTask(value);
      }
      return this;
   }
   
   public MSChatGroupPO filterTask(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatGroup.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatGroupPO filterTask(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatGroup.PROPERTY_TASK)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatMemberPO filterMembers()
   {
      MSChatMemberPO result = new MSChatMemberPO(new MSChatMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatGroup.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public MSChatGroupPO filterMembers(MSChatMemberPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_MEMBERS);
   }

   public MSChatChannelDescriptionPO filterChannels()
   {
      MSChatChannelDescriptionPO result = new MSChatChannelDescriptionPO(new MSChatChannelDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatGroup.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public MSChatGroupPO filterChannels(MSChatChannelDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_CHANNELS);
   }


   public MSChatGroupPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public MSChatGroupPO createTaskCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatGroup.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatGroupPO createTaskCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatGroup.PROPERTY_TASK)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatGroupPO createTaskAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatGroup.PROPERTY_TASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO createChannelsPO()
   {
      MSChatChannelDescriptionPO result = new MSChatChannelDescriptionPO(new MSChatChannelDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatGroup.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public MSChatChannelDescriptionPO createChannelsPO(String modifier)
   {
      MSChatChannelDescriptionPO result = new MSChatChannelDescriptionPO(new MSChatChannelDescription[]{});
      
      result.setModifier(modifier);
      super.hasLink(MSChatGroup.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public MSChatGroupPO createChannelsLink(MSChatChannelDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_CHANNELS);
   }

   public MSChatGroupPO createChannelsLink(MSChatChannelDescriptionPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_CHANNELS, modifier);
   }

   public MSChatMemberPO createMembersPO()
   {
      MSChatMemberPO result = new MSChatMemberPO(new MSChatMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatGroup.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public MSChatMemberPO createMembersPO(String modifier)
   {
      MSChatMemberPO result = new MSChatMemberPO(new MSChatMember[]{});
      
      result.setModifier(modifier);
      super.hasLink(MSChatGroup.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public MSChatGroupPO createMembersLink(MSChatMemberPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_MEMBERS);
   }

   public MSChatGroupPO createMembersLink(MSChatMemberPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, MSChatGroup.PROPERTY_MEMBERS, modifier);
   }

}
