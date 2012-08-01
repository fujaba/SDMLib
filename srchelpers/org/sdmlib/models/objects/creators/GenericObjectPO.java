package org.sdmlib.models.objects.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.objects.creators.GenericAttributePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.objects.creators.GenericObjectPO;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.creators.GenericAttributeSet;
import org.sdmlib.models.objects.creators.GenericLinkPO;
import org.sdmlib.models.objects.GenericLink;
import org.sdmlib.models.objects.creators.GenericLinkSet;

public class GenericObjectPO extends PatternObject
{
   public GenericObjectPO startNAC()
   {
      return (GenericObjectPO) super.startNAC();
   }
   
   public GenericObjectPO endNAC()
   {
      return (GenericObjectPO) super.endNAC();
   }
   
   public GenericObjectPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public GenericObjectPO hasType(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GenericObject.PROPERTY_TYPE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getType()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) getCurrentMatch()).getType();
      }
      return null;
   }
   
   public GenericAttributePO hasAttrs()
   {
      GenericAttributePO result = new GenericAttributePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_ATTRS, result);
      
      return result;   }
   
   public GenericObjectPO hasAttrs(GenericAttributePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_ATTRS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericAttributeSet getAttrs()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) this.getCurrentMatch()).getAttrs();
      }
      return null;
   }
   
   public GenericLinkPO hasOutgoingLinks()
   {
      GenericLinkPO result = new GenericLinkPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_OUTGOINGLINKS, result);
      
      return result;   }
   
   public GenericObjectPO hasOutgoingLinks(GenericLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_OUTGOINGLINKS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkSet getOutgoingLinks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) this.getCurrentMatch()).getOutgoingLinks();
      }
      return null;
   }
   
   public GenericLinkPO hasIncommingLinks()
   {
      GenericLinkPO result = new GenericLinkPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericObject.PROPERTY_INCOMMINGLINKS, result);
      
      return result;   }
   
   public GenericObjectPO hasIncommingLinks(GenericLinkPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericObject.PROPERTY_INCOMMINGLINKS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericLinkSet getIncommingLinks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericObject) this.getCurrentMatch()).getIncommingLinks();
      }
      return null;
   }
   
}

