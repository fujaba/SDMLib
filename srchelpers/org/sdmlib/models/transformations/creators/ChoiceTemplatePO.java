package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.creators.ChoiceTemplateSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionPO;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.ChoiceTemplatePO;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionSet;
import org.sdmlib.models.transformations.creators.TemplatePO;
import org.sdmlib.models.transformations.creators.TemplateSet;
import org.sdmlib.models.transformations.creators.MatchPO;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.creators.MatchSet;

public class ChoiceTemplatePO extends PatternObject<ChoiceTemplatePO, ChoiceTemplate>
{
   public ChoiceTemplateSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ChoiceTemplateSet matches = new ChoiceTemplateSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((ChoiceTemplate) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public ChoiceTemplatePO hasTemplateText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasTemplateText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTemplateText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getTemplateText();
      }
      return null;
   }
   
   public ChoiceTemplatePO withTemplateText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setTemplateText(value);
      }
      return this;
   }
   
   public ChoiceTemplatePO hasExpandedText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasExpandedText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getExpandedText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getExpandedText();
      }
      return null;
   }
   
   public ChoiceTemplatePO withExpandedText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setExpandedText(value);
      }
      return this;
   }
   
   public ChoiceTemplatePO hasModelObject(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasModelObject(Object lower, Object upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELOBJECT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public Object getModelObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getModelObject();
      }
      return null;
   }
   
   public ChoiceTemplatePO withModelObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setModelObject(value);
      }
      return this;
   }
   
   public ChoiceTemplatePO hasModelClassName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasModelClassName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getModelClassName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getModelClassName();
      }
      return null;
   }
   
   public ChoiceTemplatePO withModelClassName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setModelClassName(value);
      }
      return this;
   }
   
   public ChoiceTemplatePO hasListStart(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasListStart(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getListStart()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getListStart();
      }
      return null;
   }
   
   public ChoiceTemplatePO withListStart(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setListStart(value);
      }
      return this;
   }
   
   public ChoiceTemplatePO hasListSeparator(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasListSeparator(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getListSeparator()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getListSeparator();
      }
      return null;
   }
   
   public ChoiceTemplatePO withListSeparator(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setListSeparator(value);
      }
      return this;
   }
   
   public ChoiceTemplatePO hasListEnd(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasListEnd(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getListEnd()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getListEnd();
      }
      return null;
   }
   
   public ChoiceTemplatePO withListEnd(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setListEnd(value);
      }
      return this;
   }
   
   public PlaceHolderDescriptionPO hasPlaceholders()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Template.PROPERTY_PLACEHOLDERS, result);
      
      return result;
   }

   public ChoiceTemplatePO hasPlaceholders(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PLACEHOLDERS);
   }

   public PlaceHolderDescriptionSet getPlaceholders()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getPlaceholders();
      }
      return null;
   }

   public TemplatePO hasChoices()
   {
      TemplatePO result = new TemplatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ChoiceTemplate.PROPERTY_CHOICES, result);
      
      return result;
   }

   public ChoiceTemplatePO hasChoices(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_CHOICES);
   }

   public TemplateSet getChoices()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) this.getCurrentMatch()).getChoices();
      }
      return null;
   }

   public ChoiceTemplatePO hasChooser()
   {
      ChoiceTemplatePO result = new ChoiceTemplatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Template.PROPERTY_CHOOSER, result);
      
      return result;
   }

   public ChoiceTemplatePO hasChooser(ChoiceTemplatePO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_CHOOSER);
   }

   public ChoiceTemplate getChooser()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getChooser();
      }
      return null;
   }

   public MatchPO hasMatches()
   {
      MatchPO result = new MatchPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Template.PROPERTY_MATCHES, result);
      
      return result;
   }

   public ChoiceTemplatePO hasMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_MATCHES);
   }

   public MatchSet getMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getMatches();
      }
      return null;
   }

   public PlaceHolderDescriptionPO hasParents()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Template.PROPERTY_PARENTS, result);
      
      return result;
   }

   public ChoiceTemplatePO hasParents(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PARENTS);
   }

   public PlaceHolderDescriptionSet getParents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getParents();
      }
      return null;
   }

   public ChoiceTemplatePO hasReferenceLookup(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_REFERENCELOOKUP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public boolean getReferenceLookup()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((ChoiceTemplate) getCurrentMatch()).getReferenceLookup();
      }
      return false;
   }
   
   public ChoiceTemplatePO withReferenceLookup(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setReferenceLookup(value);
      }
      return this;
   }
   
   public ChoiceTemplatePO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public ChoiceTemplatePO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
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
         return ((ChoiceTemplate) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ChoiceTemplatePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((ChoiceTemplate) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
}



