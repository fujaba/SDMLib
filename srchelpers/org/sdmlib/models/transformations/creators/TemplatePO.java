package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.transformations.creators.TemplateSet;
import org.sdmlib.models.pattern.AttributeConstraint;
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
         .withAttrName(Template.PROPERTY_TEMPLATETEXT).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasTemplateText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_TEMPLATETEXT).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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
         .withAttrName(Template.PROPERTY_EXPANDEDTEXT).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasExpandedText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_EXPANDEDTEXT).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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
         .withAttrName(Template.PROPERTY_MODELOBJECT).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasModelObject(Object lower, Object upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_MODELOBJECT).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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
         .withAttrName(Template.PROPERTY_MODELCLASSNAME).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasModelClassName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_MODELCLASSNAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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
         .withAttrName(Template.PROPERTY_LISTSTART).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasListStart(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTSTART).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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
         .withAttrName(Template.PROPERTY_LISTSEPARATOR).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasListSeparator(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTSEPARATOR).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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
         .withAttrName(Template.PROPERTY_LISTEND).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasListEnd(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTEND).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
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

   public ChoiceTemplatePO hasChooser()
   {
      ChoiceTemplatePO result = new ChoiceTemplatePO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(Template.PROPERTY_CHOOSER, result);

      return result;
   }

   public TemplatePO hasChooser(ChoiceTemplatePO tgt)
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

   public TemplatePO hasMatches(MatchPO tgt)
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

   public TemplatePO hasParents(PlaceHolderDescriptionPO tgt)
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

   public TemplatePO hasReferenceLookup(boolean value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_REFERENCELOOKUP).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public boolean getReferenceLookup()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) getCurrentMatch()).getReferenceLookup();
      }
      return false;
   }

   public TemplatePO withReferenceLookup(boolean value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setReferenceLookup(value);
      }
      return this;
   }

   public TemplatePO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_NAME).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public TemplatePO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(Template.PROPERTY_NAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) getCurrentMatch()).getName();
      }
      return null;
   }

   public TemplatePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Template) getCurrentMatch()).setName(value);
      }
      return this;
   }

   public TemplatePO createTemplateText(String value)
   {
      this.startCreate().hasTemplateText(value).endCreate();
      return this;
   }

   public TemplatePO createExpandedText(String value)
   {
      this.startCreate().hasExpandedText(value).endCreate();
      return this;
   }

   public TemplatePO createModelObject(Object value)
   {
      this.startCreate().hasModelObject(value).endCreate();
      return this;
   }

   public TemplatePO createModelClassName(String value)
   {
      this.startCreate().hasModelClassName(value).endCreate();
      return this;
   }

   public TemplatePO createListStart(String value)
   {
      this.startCreate().hasListStart(value).endCreate();
      return this;
   }

   public TemplatePO createListSeparator(String value)
   {
      this.startCreate().hasListSeparator(value).endCreate();
      return this;
   }

   public TemplatePO createListEnd(String value)
   {
      this.startCreate().hasListEnd(value).endCreate();
      return this;
   }

   public TemplatePO createReferenceLookup(boolean value)
   {
      this.startCreate().hasReferenceLookup(value).endCreate();
      return this;
   }

   public TemplatePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }

   public PlaceHolderDescriptionPO createPlaceholders()
   {
      return this.startCreate().hasPlaceholders().endCreate();
   }

   public TemplatePO createPlaceholders(PlaceHolderDescriptionPO tgt)
   {
      return this.startCreate().hasPlaceholders(tgt).endCreate();
   }

   public ChoiceTemplatePO createChooser()
   {
      return this.startCreate().hasChooser().endCreate();
   }

   public TemplatePO createChooser(ChoiceTemplatePO tgt)
   {
      return this.startCreate().hasChooser(tgt).endCreate();
   }

   public MatchPO createMatches()
   {
      return this.startCreate().hasMatches().endCreate();
   }

   public TemplatePO createMatches(MatchPO tgt)
   {
      return this.startCreate().hasMatches(tgt).endCreate();
   }

   public PlaceHolderDescriptionPO createParents()
   {
      return this.startCreate().hasParents().endCreate();
   }

   public TemplatePO createParents(PlaceHolderDescriptionPO tgt)
   {
      return this.startCreate().hasParents(tgt).endCreate();
   }

}
