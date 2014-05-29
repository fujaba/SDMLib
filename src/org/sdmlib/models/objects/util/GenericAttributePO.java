package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

public class GenericAttributePO extends PatternObject
{
   public GenericAttributePO startNAC()
   {
      return (GenericAttributePO) super.startNAC();
   }
   
   public GenericAttributePO endNAC()
   {
      return (GenericAttributePO) super.endNAC();
   }
   
   public GenericAttributePO startCreate()
   {
      this.getPattern().startCreate();
      
      return this;
   }
   
   public GenericAttribute getCurrentMatch()
   {
      return (GenericAttribute) super.getCurrentMatch();
   }
   
   public GenericAttributeSet allMatches()
   {
      GenericAttributeSet result = new GenericAttributeSet();
      
      while (this.getPattern().getHasMatch())
      {
         result.add(this.getCurrentMatch());
         
         this.getPattern().findNextMatch();
      }
      
      return result;
   }
   
   public GenericAttributePO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(GenericAttribute.PROPERTY_NAME)
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
         return ((GenericAttribute) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public GenericAttributePO hasValue(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(GenericAttribute.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericAttribute) getCurrentMatch()).getValue();
      }
      return null;
   }
   
   public GenericObjectPO hasOwner()
   {
      GenericObjectPO result = new GenericObjectPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(GenericAttribute.PROPERTY_OWNER, result);
      
      return result;   }
   
   public GenericAttributePO hasOwner(GenericObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(GenericAttribute.PROPERTY_OWNER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericObject getOwner()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((GenericAttribute) this.getCurrentMatch()).getOwner();
      }
      return null;
   }
   
   public GenericAttributePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericAttribute.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericAttributePO hasValue(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(GenericAttribute.PROPERTY_VALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public GenericAttributePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public GenericAttributePO createValue(String value)
   {
      this.startCreate().hasValue(value).endCreate();
      return this;
   }
   
   public GenericObjectPO createOwner()
   {
      return this.startCreate().hasOwner().endCreate();
   }

   public GenericAttributePO createOwner(GenericObjectPO tgt)
   {
      return (GenericAttributePO) this.startCreate().hasOwner(tgt).endCreate();
   }
}
