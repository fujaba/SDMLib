package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatGroupPO;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatMemberPO;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelDescriptionPO;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.util.MSChatChannelDescriptionSet;

public class MSChatMemberPO extends PatternObject<MSChatMemberPO, MSChatMember>
{

    public MSChatMemberSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MSChatMemberSet matches = new MSChatMemberSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MSChatMember) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MSChatMemberPO(){
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MSChatMemberPO(MSChatMember... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MSChatMemberPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMember.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public MSChatMemberPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatMember.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.hasAttr();
      
      return this;
   }
   
   public MSChatMemberPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatMember) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MSChatMemberPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatMember) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public MSChatGroupPO hasGroup()
   {
      MSChatGroupPO result = new MSChatGroupPO(new MSChatGroup[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatMember.PROPERTY_GROUP, result);
      
      return result;
   }

   public MSChatGroupPO createGroup()
   {
      return this.startCreate().hasGroup().endCreate();
   }

   public MSChatMemberPO hasGroup(MSChatGroupPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatMember.PROPERTY_GROUP);
   }

   public MSChatMemberPO createGroup(MSChatGroupPO tgt)
   {
      return this.startCreate().hasGroup(tgt).endCreate();
   }

   public MSChatGroup getGroup()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatMember) this.getCurrentMatch()).getGroup();
      }
      return null;
   }

   public MSChatChannelDescriptionPO hasChannels()
   {
      MSChatChannelDescriptionPO result = new MSChatChannelDescriptionPO(new MSChatChannelDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatMember.PROPERTY_CHANNELS, result);
      
      return result;
   }

   public MSChatChannelDescriptionPO createChannels()
   {
      return this.startCreate().hasChannels().endCreate();
   }

   public MSChatMemberPO hasChannels(MSChatChannelDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatMember.PROPERTY_CHANNELS);
   }

   public MSChatMemberPO createChannels(MSChatChannelDescriptionPO tgt)
   {
      return this.startCreate().hasChannels(tgt).endCreate();
   }

   public MSChatChannelDescriptionSet getChannels()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatMember) this.getCurrentMatch()).getChannels();
      }
      return null;
   }

}
