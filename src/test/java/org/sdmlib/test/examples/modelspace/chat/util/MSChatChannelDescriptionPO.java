package org.sdmlib.test.examples.modelspace.chat.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelspace.chat.MSChatChannelDescription;
import org.sdmlib.test.examples.modelspace.chat.MSChatGroup;
import org.sdmlib.test.examples.modelspace.chat.MSChatMember;

public class MSChatChannelDescriptionPO extends PatternObject<MSChatChannelDescriptionPO, MSChatChannelDescription>
{

    public MSChatChannelDescriptionSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MSChatChannelDescriptionSet matches = new MSChatChannelDescriptionSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((MSChatChannelDescription) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MSChatChannelDescriptionPO(){
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MSChatChannelDescriptionPO(MSChatChannelDescription... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelspace.chat.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MSChatChannelDescriptionPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatChannelDescription) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public MSChatChannelDescriptionPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatChannelDescription) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public MSChatChannelDescriptionPO hasLocation(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_LOCATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO hasLocation(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_LOCATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO createLocation(String value)
   {
      this.startCreate().hasLocation(value).endCreate();
      return this;
   }
   
   public String getLocation()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatChannelDescription) getCurrentMatch()).getLocation();
      }
      return null;
   }
   
   public MSChatChannelDescriptionPO withLocation(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((MSChatChannelDescription) getCurrentMatch()).setLocation(value);
      }
      return this;
   }
   
   public MSChatGroupPO hasGroup()
   {
      MSChatGroupPO result = new MSChatGroupPO(new MSChatGroup[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannelDescription.PROPERTY_GROUP, result);
      
      return result;
   }

   public MSChatGroupPO createGroup()
   {
      return this.startCreate().hasGroup().endCreate();
   }

   public MSChatChannelDescriptionPO hasGroup(MSChatGroupPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannelDescription.PROPERTY_GROUP);
   }

   public MSChatChannelDescriptionPO createGroup(MSChatGroupPO tgt)
   {
      return this.startCreate().hasGroup(tgt).endCreate();
   }

   public MSChatGroup getGroup()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatChannelDescription) this.getCurrentMatch()).getGroup();
      }
      return null;
   }

   public MSChatMemberPO hasMembers()
   {
      MSChatMemberPO result = new MSChatMemberPO(new MSChatMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannelDescription.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public MSChatMemberPO createMembers()
   {
      return this.startCreate().hasMembers().endCreate();
   }

   public MSChatChannelDescriptionPO hasMembers(MSChatMemberPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannelDescription.PROPERTY_MEMBERS);
   }

   public MSChatChannelDescriptionPO createMembers(MSChatMemberPO tgt)
   {
      return this.startCreate().hasMembers(tgt).endCreate();
   }

   public MSChatMemberSet getMembers()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((MSChatChannelDescription) this.getCurrentMatch()).getMembers();
      }
      return null;
   }

   public MSChatChannelDescriptionPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO filterLocation(String value)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_LOCATION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatChannelDescriptionPO filterLocation(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(MSChatChannelDescription.PROPERTY_LOCATION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MSChatGroupPO filterGroup()
   {
      MSChatGroupPO result = new MSChatGroupPO(new MSChatGroup[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannelDescription.PROPERTY_GROUP, result);
      
      return result;
   }

   public MSChatChannelDescriptionPO filterGroup(MSChatGroupPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannelDescription.PROPERTY_GROUP);
   }

   public MSChatMemberPO filterMembers()
   {
      MSChatMemberPO result = new MSChatMemberPO(new MSChatMember[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(MSChatChannelDescription.PROPERTY_MEMBERS, result);
      
      return result;
   }

   public MSChatChannelDescriptionPO filterMembers(MSChatMemberPO tgt)
   {
      return hasLinkConstraint(tgt, MSChatChannelDescription.PROPERTY_MEMBERS);
   }

}
