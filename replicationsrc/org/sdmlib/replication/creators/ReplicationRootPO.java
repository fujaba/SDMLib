package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.ReplicationRoot;
import org.sdmlib.replication.creators.ReplicationRootSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.ReplicationRootPO;
import org.sdmlib.models.pattern.LinkConstraint;

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
   
   public ReplicationRootPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
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
      ReplicationRootPO result = new ReplicationRootPO();
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
      ReplicationRootPO result = new ReplicationRootPO();
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

}

