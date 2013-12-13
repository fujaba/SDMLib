package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.transformations.creators.TemplateSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import java.lang.Object;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.TemplatePO;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionSet;
import org.sdmlib.models.transformations.creators.ChoiceTemplatePO;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.creators.MatchPO;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.creators.MatchSet;

public class TemplatePO extends PatternObject<TemplatePO, Template>
{
   public TemplateSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TemplateSet matches = new TemplateSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Template) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public TemplatePO hasTemplateText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Template.PROPERTY_TEMPLATETEXT)
      .withTgtValue(value)
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
         return ((Template) getCurrentMatch()).getTemplateText();
      }
      return null;
   }
   
   public TemplatePO withTemplateText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setTemplateText(value);
      }
      return this;
   }
   
   public TemplatePO hasExpandedText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
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
         return ((Template) getCurrentMatch()).getExpandedText();
      }
      return null;
   }
   
   public TemplatePO withExpandedText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setExpandedText(value);
      }
      return this;
   }
   
   public TemplatePO hasModelObject(Object value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
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
         return ((Template) getCurrentMatch()).getModelObject();
      }
      return null;
   }
   
   public TemplatePO withModelObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setModelObject(value);
      }
      return this;
   }
   
   public TemplatePO hasModelClassName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
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
         return ((Template) getCurrentMatch()).getModelClassName();
      }
      return null;
   }
   
   public TemplatePO withModelClassName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setModelClassName(value);
      }
      return this;
   }
   
   public TemplatePO hasListStart(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSTART)
      .withTgtValue(value)
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
         return ((Template) getCurrentMatch()).getListStart();
      }
      return null;
   }
   
   public TemplatePO withListStart(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setListStart(value);
      }
      return this;
   }
   
   public TemplatePO hasListSeparator(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
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
         return ((Template) getCurrentMatch()).getListSeparator();
      }
      return null;
   }
   
   public TemplatePO withListSeparator(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setListSeparator(value);
      }
      return this;
   }
   
   public TemplatePO hasListEnd(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTEND)
      .withTgtValue(value)
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
         return ((Template) getCurrentMatch()).getListEnd();
      }
      return null;
   }
   
   public TemplatePO withListEnd(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setListEnd(value);
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

   public TemplatePO hasPlaceholders(PlaceHolderDescriptionPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Template.PROPERTY_PLACEHOLDERS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PlaceHolderDescriptionSet getPlaceholders()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getPlaceholders();
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

   public TemplatePO hasChooser(ChoiceTemplatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Template.PROPERTY_CHOOSER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public ChoiceTemplate getChooser()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getChooser();
      }
      return null;
   }

   public PlaceHolderDescriptionPO hasParent()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Template.PROPERTY_PARENT, result);
      
      return result;
   }

   public TemplatePO hasParent(PlaceHolderDescriptionPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Template.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public PlaceHolderDescription getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getParent();
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

   public TemplatePO hasMatches(MatchPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Template.PROPERTY_MATCHES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public MatchSet getMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getMatches();
      }
      return null;
   }

}


