package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

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

   public ChoiceTemplatePO()
   {
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public ChoiceTemplatePO(ChoiceTemplate... hostGraphObject)
   {
      if (hostGraphObject == null || hostGraphObject.length < 1)
      {
         return;
      }
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }

   public ChoiceTemplatePO hasTemplateText(String value)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO hasTemplateText(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createTemplateText(String value)
   {
      this.startCreate().hasTemplateText(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO hasExpandedText(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createExpandedText(String value)
   {
      this.startCreate().hasExpandedText(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_MODELOBJECT)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createModelObject(Object value)
   {
      this.startCreate().hasModelObject(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO hasModelClassName(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createModelClassName(String value)
   {
      this.startCreate().hasModelClassName(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO hasListStart(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createListStart(String value)
   {
      this.startCreate().hasListStart(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO hasListSeparator(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createListSeparator(String value)
   {
      this.startCreate().hasListSeparator(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO hasListEnd(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createListEnd(String value)
   {
      this.startCreate().hasListEnd(value).endCreate();
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

   public ChoiceTemplatePO hasReferenceLookup(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_REFERENCELOOKUP)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createReferenceLookup(boolean value)
   {
      this.startCreate().hasReferenceLookup(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_NAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(ChoiceTemplate.PROPERTY_NAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public ChoiceTemplatePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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

   public PlaceHolderDescriptionPO hasPlaceholders()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_PLACEHOLDERS, result);

      return result;
   }

   public PlaceHolderDescriptionPO createPlaceholders()
   {
      return this.startCreate().hasPlaceholders().endCreate();
   }

   public ChoiceTemplatePO hasPlaceholders(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PLACEHOLDERS);
   }

   public ChoiceTemplatePO createPlaceholders(PlaceHolderDescriptionPO tgt)
   {
      return this.startCreate().hasPlaceholders(tgt).endCreate();
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
      TemplatePO result = new TemplatePO(new Template[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChoiceTemplate.PROPERTY_CHOICES, result);

      return result;
   }

   public TemplatePO createChoices()
   {
      return this.startCreate().hasChoices().endCreate();
   }

   public ChoiceTemplatePO hasChoices(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_CHOICES);
   }

   public ChoiceTemplatePO createChoices(TemplatePO tgt)
   {
      return this.startCreate().hasChoices(tgt).endCreate();
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
      ChoiceTemplatePO result = new ChoiceTemplatePO(new ChoiceTemplate[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_CHOOSER, result);

      return result;
   }

   public ChoiceTemplatePO createChooser()
   {
      return this.startCreate().hasChooser().endCreate();
   }

   public ChoiceTemplatePO hasChooser(ChoiceTemplatePO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_CHOOSER);
   }

   public ChoiceTemplatePO createChooser(ChoiceTemplatePO tgt)
   {
      return this.startCreate().hasChooser(tgt).endCreate();
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
      MatchPO result = new MatchPO(new Match[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_MATCHES, result);

      return result;
   }

   public MatchPO createMatches()
   {
      return this.startCreate().hasMatches().endCreate();
   }

   public ChoiceTemplatePO hasMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_MATCHES);
   }

   public ChoiceTemplatePO createMatches(MatchPO tgt)
   {
      return this.startCreate().hasMatches(tgt).endCreate();
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
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_PARENTS, result);

      return result;
   }

   public PlaceHolderDescriptionPO createParents()
   {
      return this.startCreate().hasParents().endCreate();
   }

   public ChoiceTemplatePO hasParents(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PARENTS);
   }

   public ChoiceTemplatePO createParents(PlaceHolderDescriptionPO tgt)
   {
      return this.startCreate().hasParents(tgt).endCreate();
   }

   public PlaceHolderDescriptionSet getParents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Template) this.getCurrentMatch()).getParents();
      }
      return null;
   }

   public ChoiceTemplatePO filterTemplateText(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterTemplateText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterExpandedText(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterExpandedText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterModelObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterModelClassName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterModelClassName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterListStart(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterListStart(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterListSeparator(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterListSeparator(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterListEnd(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterListEnd(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterReferenceLookup(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_REFERENCELOOKUP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public PlaceHolderDescriptionPO filterPlaceholders()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_PLACEHOLDERS, result);
      
      return result;
   }

   public ChoiceTemplatePO filterPlaceholders(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PLACEHOLDERS);
   }

   public ChoiceTemplatePO filterChooser()
   {
      ChoiceTemplatePO result = new ChoiceTemplatePO(new ChoiceTemplate[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_CHOOSER, result);
      
      return result;
   }

   public ChoiceTemplatePO filterChooser(ChoiceTemplatePO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_CHOOSER);
   }

   public MatchPO filterMatches()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_MATCHES, result);
      
      return result;
   }

   public ChoiceTemplatePO filterMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_MATCHES);
   }

   public PlaceHolderDescriptionPO filterParents()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_PARENTS, result);
      
      return result;
   }

   public ChoiceTemplatePO filterParents(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PARENTS);
   }

   public TemplatePO filterChoices()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChoiceTemplate.PROPERTY_CHOICES, result);
      
      return result;
   }

   public ChoiceTemplatePO filterChoices(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_CHOICES);
   }


   public ChoiceTemplatePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public ChoiceTemplatePO createExpandedTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createExpandedTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createExpandedTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListEndCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListEndCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListEndAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTEND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListSeparatorCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListSeparatorCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListSeparatorAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListStartCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListStartCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createListStartAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_LISTSTART)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createModelClassNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createModelClassNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createModelClassNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createModelObjectCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createModelObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createReferenceLookupCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_REFERENCELOOKUP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createReferenceLookupAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_REFERENCELOOKUP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createTemplateTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createTemplateTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createTemplateTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(ChoiceTemplate.PROPERTY_TEMPLATETEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ChoiceTemplatePO createChooserPO()
   {
      ChoiceTemplatePO result = new ChoiceTemplatePO(new ChoiceTemplate[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChoiceTemplate.PROPERTY_CHOOSER, result);
      
      return result;
   }

   public ChoiceTemplatePO createChooserPO(String modifier)
   {
      ChoiceTemplatePO result = new ChoiceTemplatePO(new ChoiceTemplate[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChoiceTemplate.PROPERTY_CHOOSER, result);
      
      return result;
   }

   public ChoiceTemplatePO createChooserLink(ChoiceTemplatePO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_CHOOSER);
   }

   public ChoiceTemplatePO createChooserLink(ChoiceTemplatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_CHOOSER, modifier);
   }

   public TemplatePO createChoicesPO()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChoiceTemplate.PROPERTY_CHOICES, result);
      
      return result;
   }

   public TemplatePO createChoicesPO(String modifier)
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChoiceTemplate.PROPERTY_CHOICES, result);
      
      return result;
   }

   public ChoiceTemplatePO createChoicesLink(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_CHOICES);
   }

   public ChoiceTemplatePO createChoicesLink(TemplatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_CHOICES, modifier);
   }

   public PlaceHolderDescriptionPO createPlaceholdersPO()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChoiceTemplate.PROPERTY_PLACEHOLDERS, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createPlaceholdersPO(String modifier)
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChoiceTemplate.PROPERTY_PLACEHOLDERS, result);
      
      return result;
   }

   public ChoiceTemplatePO createPlaceholdersLink(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_PLACEHOLDERS);
   }

   public ChoiceTemplatePO createPlaceholdersLink(PlaceHolderDescriptionPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_PLACEHOLDERS, modifier);
   }

   public PlaceHolderDescriptionPO createParentsPO()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChoiceTemplate.PROPERTY_PARENTS, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createParentsPO(String modifier)
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChoiceTemplate.PROPERTY_PARENTS, result);
      
      return result;
   }

   public ChoiceTemplatePO createParentsLink(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_PARENTS);
   }

   public ChoiceTemplatePO createParentsLink(PlaceHolderDescriptionPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_PARENTS, modifier);
   }

   public MatchPO createMatchesPO()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(ChoiceTemplate.PROPERTY_MATCHES, result);
      
      return result;
   }

   public MatchPO createMatchesPO(String modifier)
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(modifier);
      super.hasLink(ChoiceTemplate.PROPERTY_MATCHES, result);
      
      return result;
   }

   public ChoiceTemplatePO createMatchesLink(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_MATCHES);
   }

   public ChoiceTemplatePO createMatchesLink(MatchPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, ChoiceTemplate.PROPERTY_MATCHES, modifier);
   }

}
