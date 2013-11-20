package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.TemplatePO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionPO;
import org.sdmlib.models.transformations.Template;

public class PlaceHolderDescriptionPO extends PatternObject<PlaceHolderDescriptionPO, PlaceHolderDescription>
{
   public PlaceHolderDescriptionSet allMatches()
   {
      this.setDoAllMatches(true);
      
      PlaceHolderDescriptionSet matches = new PlaceHolderDescriptionSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PlaceHolderDescription) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public PlaceHolderDescriptionPO hasTextFragment(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTextFragment()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) getCurrentMatch()).getTextFragment();
      }
      return null;
   }
   
   public PlaceHolderDescriptionPO withTextFragment(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PlaceHolderDescription) getCurrentMatch()).setTextFragment(value);
      }
      return this;
   }
   
   public PlaceHolderDescriptionPO hasValue(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
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
         return ((PlaceHolderDescription) getCurrentMatch()).getValue();
      }
      return null;
   }
   
   public PlaceHolderDescriptionPO withValue(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PlaceHolderDescription) getCurrentMatch()).setValue(value);
      }
      return this;
   }
   
   public PlaceHolderDescriptionPO hasAttrName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getAttrName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) getCurrentMatch()).getAttrName();
      }
      return null;
   }
   
   public PlaceHolderDescriptionPO withAttrName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PlaceHolderDescription) getCurrentMatch()).setAttrName(value);
      }
      return this;
   }
   
   public TemplatePO hasTemplate()
   {
      TemplatePO result = new TemplatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PlaceHolderDescription.PROPERTY_TEMPLATE, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO hasTemplate(TemplatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PlaceHolderDescription.PROPERTY_TEMPLATE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Template getTemplate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) this.getCurrentMatch()).getTemplate();
      }
      return null;
   }

   public TemplatePO hasSubTemplate()
   {
      TemplatePO result = new TemplatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PlaceHolderDescription.PROPERTY_SUBTEMPLATE, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO hasSubTemplate(TemplatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(PlaceHolderDescription.PROPERTY_SUBTEMPLATE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Template getSubTemplate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) this.getCurrentMatch()).getSubTemplate();
      }
      return null;
   }

}

