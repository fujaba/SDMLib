package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
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

   public PlaceHolderDescriptionPO()
   {
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public PlaceHolderDescriptionPO(PlaceHolderDescription... hostGraphObject)
   {
      if (hostGraphObject == null || hostGraphObject.length < 1)
      {
         return;
      }
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }

   public PlaceHolderDescriptionPO hasTextFragment(String value)
   {
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO hasTextFragment(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO createTextFragment(String value)
   {
      this.startCreate().hasTextFragment(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO hasValue(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO createValue(String value)
   {
      this.startCreate().hasValue(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO hasAttrName(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO createAttrName(String value)
   {
      this.startCreate().hasAttrName(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO createIsKeyAttribute(boolean value)
   {
      this.startCreate().hasIsKeyAttribute(value).endCreate();
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

   public PlaceHolderDescriptionPO hasPrefix(String value)
   {
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public PlaceHolderDescriptionPO hasPrefix(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

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

   public TemplatePO hasOwners()
   {
      TemplatePO result = new TemplatePO(new Template[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_OWNERS, result);

      return result;
   }

   public TemplatePO createOwners()
   {
      return this.startCreate().hasOwners().endCreate();
   }

   public PlaceHolderDescriptionPO hasOwners(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_OWNERS);
   }

   public PlaceHolderDescriptionPO createOwners(TemplatePO tgt)
   {
      return this.startCreate().hasOwners(tgt).endCreate();
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
      MatchPO result = new MatchPO(new Match[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_MATCHES, result);

      return result;
   }

   public MatchPO createMatches()
   {
      return this.startCreate().hasMatches().endCreate();
   }

   public PlaceHolderDescriptionPO hasMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_MATCHES);
   }

   public PlaceHolderDescriptionPO createMatches(MatchPO tgt)
   {
      return this.startCreate().hasMatches(tgt).endCreate();
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
      TemplatePO result = new TemplatePO(new Template[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_SUBTEMPLATE, result);

      return result;
   }

   public TemplatePO createSubTemplate()
   {
      return this.startCreate().hasSubTemplate().endCreate();
   }

   public PlaceHolderDescriptionPO hasSubTemplate(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_SUBTEMPLATE);
   }

   public PlaceHolderDescriptionPO createSubTemplate(TemplatePO tgt)
   {
      return this.startCreate().hasSubTemplate(tgt).endCreate();
   }

   public Template getSubTemplate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((PlaceHolderDescription) this.getCurrentMatch()).getSubTemplate();
      }
      return null;
   }

   public PlaceHolderDescriptionPO filterTextFragment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterTextFragment(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterValue(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterValue(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterAttrName(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterAttrName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterIsKeyAttribute(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterPrefix(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterPrefix(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterOwners()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_OWNERS, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO filterOwners(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_OWNERS);
   }

   public MatchPO filterMatches()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_MATCHES, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO filterMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_MATCHES);
   }

   public TemplatePO filterSubTemplate()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_SUBTEMPLATE, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO filterSubTemplate(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_SUBTEMPLATE);
   }


   public PlaceHolderDescriptionPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public PlaceHolderDescriptionPO createAttrNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createAttrNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createAttrNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ATTRNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createIsKeyAttributeCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createIsKeyAttributeAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_ISKEYATTRIBUTE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createPrefixCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createPrefixCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createPrefixAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_PREFIX)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createTextFragmentCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createTextFragmentCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createTextFragmentAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_TEXTFRAGMENT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createValueCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createValueCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO createValueAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(PlaceHolderDescription.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO createMatchesPO()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_MATCHES, result);
      
      return result;
   }

   public MatchPO createMatchesPO(String modifier)
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(modifier);
      super.hasLink(PlaceHolderDescription.PROPERTY_MATCHES, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createMatchesLink(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_MATCHES);
   }

   public PlaceHolderDescriptionPO createMatchesLink(MatchPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_MATCHES, modifier);
   }

   public TemplatePO createOwnersPO()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_OWNERS, result);
      
      return result;
   }

   public TemplatePO createOwnersPO(String modifier)
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(modifier);
      super.hasLink(PlaceHolderDescription.PROPERTY_OWNERS, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createOwnersLink(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_OWNERS);
   }

   public PlaceHolderDescriptionPO createOwnersLink(TemplatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_OWNERS, modifier);
   }

   public TemplatePO createSubTemplatePO()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(PlaceHolderDescription.PROPERTY_SUBTEMPLATE, result);
      
      return result;
   }

   public TemplatePO createSubTemplatePO(String modifier)
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(modifier);
      super.hasLink(PlaceHolderDescription.PROPERTY_SUBTEMPLATE, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createSubTemplateLink(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_SUBTEMPLATE);
   }

   public PlaceHolderDescriptionPO createSubTemplateLink(TemplatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, PlaceHolderDescription.PROPERTY_SUBTEMPLATE, modifier);
   }

}
