package org.sdmlib.models.pattern.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.creators.LinkConstraintSet;

public class LinkConstraintPO extends PatternObject
{
   public LinkConstraintPO hasTgtRoleName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_TGTROLENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withTgtRoleName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).withTgtRoleName(value);
      }
      return this;
   }
   
   public LinkConstraintPO hasHostGraphSrcObject(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HOSTGRAPHSRCOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withHostGraphSrcObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).withHostGraphSrcObject(value);
      }
      return this;
   }
   
   public LinkConstraintPO hasModifier(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_MODIFIER)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withModifier(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).withModifier(value);
      }
      return this;
   }
   
   public LinkConstraintPO hasHasMatch(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_HASMATCH)
      .withTgtValue(value)
      .withSrc(this)
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LinkConstraintPO withHasMatch(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LinkConstraint) getCurrentMatch()).withHasMatch(value);
      }
      return this;
   }
   
   public String getTgtRoleName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getTgtRoleName();
      }
      return null;
   }
   
   public Object getHostGraphSrcObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getHostGraphSrcObject();
      }
      return null;
   }
   
   public String getModifier()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getModifier();
      }
      return null;
   }
   
   public boolean getHasMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LinkConstraint) getCurrentMatch()).getHasMatch();
      }
      return false;
   }
   
   public LinkConstraintPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LinkConstraint.PROPERTY_NAME)
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
         return ((LinkConstraint) getCurrentMatch()).getName();
      }
      return null;
   }
   
}



