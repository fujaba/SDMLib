package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.creators.PatternObjectPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.creators.AttributeConstraintPO;

public class AttributeConstraintPO extends PatternObject
{
   public AttributeConstraintPO hasAttrName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_ATTRNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeConstraintPO withAttrName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).withAttrName(value);
      }
      return this;
   }
   
   public AttributeConstraintPO hasTgtValue(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_TGTVALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeConstraintPO withTgtValue(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).withTgtValue(value);
      }
      return this;
   }
   
   public AttributeConstraintPO hasHostGraphSrcObject(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeConstraintPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).withHostGraphSrcObject(value);
      }
      return this;
   }
   
   public AttributeConstraintPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public AttributeConstraintPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(AttributeConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public PatternObjectPO hasSrc()
   {
      PatternObjectPO result = new PatternObjectPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(AttributeConstraint.PROPERTY_SRC)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public AttributeConstraintPO hasSrc(PatternObjectPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(AttributeConstraint.PROPERTY_SRC)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public AttributeConstraintPO withSrc(PatternObjectPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((AttributeConstraint) this.getCurrentMatch()).withSrc((PatternObject) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
}

