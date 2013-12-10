package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.creators.ChoiceTemplateSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import java.lang.Object;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionPO;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.transformations.creators.ChoiceTemplatePO;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionSet;
import org.sdmlib.models.transformations.creators.TemplatePO;
import org.sdmlib.models.transformations.creators.TemplateSet;

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

   public TemplatePO hasChoices()
   {
      TemplatePO result = new TemplatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(ChoiceTemplate.PROPERTY_CHOICES, result);
      
      return result;
   }

   public ChoiceTemplatePO hasChoices(TemplatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(ChoiceTemplate.PROPERTY_CHOICES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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

   public ChoiceTemplatePO hasParent(PlaceHolderDescriptionPO tgt)
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

}

