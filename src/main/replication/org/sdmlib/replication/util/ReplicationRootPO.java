package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationRoot;

public class ReplicationRootPO extends PatternObject<ReplicationRootPO, ReplicationRoot>
{

    public ReplicationRootSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ReplicationRootSet matches = new ReplicationRootSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ReplicationRoot) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ReplicationRootPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ReplicationRootPO(ReplicationRoot... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public ReplicationRootPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationRootPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationRootPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationRoot) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ReplicationRootPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationRoot) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public ReplicationRootPO hasApplicationObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_APPLICATIONOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ReplicationRootPO createApplicationObject(Object value)
   {
      this.startCreate().hasApplicationObject(value).endCreate();
      return this;
   }
   
   public Object getApplicationObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationRoot) getCurrentMatch()).getApplicationObject();
      }
      return null;
   }
   
   public ReplicationRootPO withApplicationObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ReplicationRoot) getCurrentMatch()).setApplicationObject(value);
      }
      return this;
   }
   
   public ReplicationRootPO hasKids()
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationRoot.PROPERTY_KIDS, result);
      
      return result;
   }

   public ReplicationRootPO createKids()
   {
      return this.startCreate().hasKids().endCreate();
   }

   public ReplicationRootPO hasKids(ReplicationRootPO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_KIDS);
   }

   public ReplicationRootPO createKids(ReplicationRootPO tgt)
   {
      return this.startCreate().hasKids(tgt).endCreate();
   }

   public ReplicationRootSet getKids()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationRoot) this.getCurrentMatch()).getKids();
      }
      return null;
   }

   public ReplicationRootPO hasParent()
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationRoot.PROPERTY_PARENT, result);
      
      return result;
   }

   public ReplicationRootPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public ReplicationRootPO hasParent(ReplicationRootPO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_PARENT);
   }

   public ReplicationRootPO createParent(ReplicationRootPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

   public ReplicationRoot getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ReplicationRoot) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public ReplicationRootPO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO filterApplicationObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_APPLICATIONOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO filterParent()
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationRoot.PROPERTY_PARENT, result);
      
      return result;
   }

   public ReplicationRootPO filterParent(ReplicationRootPO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_PARENT);
   }

   public ReplicationRootPO filterKids()
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationRoot.PROPERTY_KIDS, result);
      
      return result;
   }

   public ReplicationRootPO filterKids(ReplicationRootPO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_KIDS);
   }


   public ReplicationRootPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ReplicationRootPO createApplicationObjectCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_APPLICATIONOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO createApplicationObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_APPLICATIONOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ReplicationRoot.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ReplicationRootPO createParentPO()
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationRoot.PROPERTY_PARENT, result);
      
      return result;
   }

   public ReplicationRootPO createParentPO(String modifier)
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(modifier);
      super.hasLink(ReplicationRoot.PROPERTY_PARENT, result);
      
      return result;
   }

   public ReplicationRootPO createParentLink(ReplicationRootPO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_PARENT);
   }

   public ReplicationRootPO createParentLink(ReplicationRootPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_PARENT, modifier);
   }

   public ReplicationRootPO createKidsPO()
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ReplicationRoot.PROPERTY_KIDS, result);
      
      return result;
   }

   public ReplicationRootPO createKidsPO(String modifier)
   {
      ReplicationRootPO result = new ReplicationRootPO(new ReplicationRoot[]{});
      
      result.setModifier(modifier);
      super.hasLink(ReplicationRoot.PROPERTY_KIDS, result);
      
      return result;
   }

   public ReplicationRootPO createKidsLink(ReplicationRootPO tgt)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_KIDS);
   }

   public ReplicationRootPO createKidsLink(ReplicationRootPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ReplicationRoot.PROPERTY_KIDS, modifier);
   }

}
