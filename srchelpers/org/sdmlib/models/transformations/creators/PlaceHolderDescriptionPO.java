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
import org.sdmlib.models.transformations.creators.TemplateSet;
import org.sdmlib.models.transformations.creators.MatchPO;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.creators.MatchSet;

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
   
   public PlaceHolderDescriptionPO hasTextFragment(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
   
   public PlaceHolderDescriptionPO hasValue(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
   
   public PlaceHolderDescriptionPO hasAttrName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
   
   public PlaceHolderDescriptionPO hasIsKeyAttribute(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getIsKeyAttribute()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) getCurrentMatch()).getIsKeyAttribute();
      }
      return false;
   }
   
   public PlaceHolderDescriptionPO withIsKeyAttribute(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PlaceHolderDescription) getCurrentMatch()).setIsKeyAttribute(value);
      }
      return this;
   }
   
   public TemplatePO hasOwners()
   {
      TemplatePO result = new TemplatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PlaceHolderDescription.PROPERTY_OWNERS, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO hasOwners(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_OWNERS);
   }

   public TemplateSet getOwners()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) this.getCurrentMatch()).getOwners();
      }
      return null;
   }

   public MatchPO hasMatches()
   {
      MatchPO result = new MatchPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(PlaceHolderDescription.PROPERTY_MATCHES, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO hasMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_MATCHES);
   }

   public MatchSet getMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) this.getCurrentMatch()).getMatches();
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
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_SUBTEMPLATE);
   }

   public Template getSubTemplate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) this.getCurrentMatch()).getSubTemplate();
      }
      return null;
   }

   public PlaceHolderDescriptionPO createTextFragment(String value)
   {
      this.startCreate().hasTextFragment(value).endCreate();
      return this;
   }
   
   public PlaceHolderDescriptionPO createValue(String value)
   {
      this.startCreate().hasValue(value).endCreate();
      return this;
   }
   
   public PlaceHolderDescriptionPO createAttrName(String value)
   {
      this.startCreate().hasAttrName(value).endCreate();
      return this;
   }
   
   public PlaceHolderDescriptionPO createIsKeyAttribute(boolean value)
   {
      this.startCreate().hasIsKeyAttribute(value).endCreate();
      return this;
   }
   
   public TemplatePO createOwners()
   {
      return this.startCreate().hasOwners().endCreate();
   }

   public PlaceHolderDescriptionPO createOwners(TemplatePO tgt)
   {
      return this.startCreate().hasOwners(tgt).endCreate();
   }

   public MatchPO createMatches()
   {
      return this.startCreate().hasMatches().endCreate();
   }

   public PlaceHolderDescriptionPO createMatches(MatchPO tgt)
   {
      return this.startCreate().hasMatches(tgt).endCreate();
   }

   public TemplatePO createSubTemplate()
   {
      return this.startCreate().hasSubTemplate().endCreate();
   }

   public PlaceHolderDescriptionPO createSubTemplate(TemplatePO tgt)
   {
      return this.startCreate().hasSubTemplate(tgt).endCreate();
   }

   public PlaceHolderDescriptionPO hasPrefix(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO hasPrefix(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createPrefix(String value)
   {
      this.startCreate().hasPrefix(value).endCreate();
      return this;
   }
   
   public String getPrefix()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) getCurrentMatch()).getPrefix();
      }
      return null;
   }
   
   public PlaceHolderDescriptionPO withPrefix(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((PlaceHolderDescription) getCurrentMatch()).setPrefix(value);
      }
      return this;
   }
   
}



