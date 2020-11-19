package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.ChoiceTemplate;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

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

   public TemplatePO()
   {
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TemplatePO(Template... hostGraphObject)
   {
      if (hostGraphObject == null || hostGraphObject.length < 1)
      {
         return;
      }
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }

   public TemplatePO hasTemplateText(String value)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_TEMPLATETEXT)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO hasTemplateText(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_TEMPLATETEXT)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createTemplateText(String value)
   {
      this.startCreate().hasTemplateText(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO hasExpandedText(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createExpandedText(String value)
   {
      this.startCreate().hasExpandedText(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_MODELOBJECT)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createModelObject(Object value)
   {
      this.startCreate().hasModelObject(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_MODELCLASSNAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO hasModelClassName(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_MODELCLASSNAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createModelClassName(String value)
   {
      this.startCreate().hasModelClassName(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTSTART)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO hasListStart(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTSTART)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createListStart(String value)
   {
      this.startCreate().hasListStart(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTSEPARATOR)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO hasListSeparator(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTSEPARATOR)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createListSeparator(String value)
   {
      this.startCreate().hasListSeparator(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTEND)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO hasListEnd(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_LISTEND)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createListEnd(String value)
   {
      this.startCreate().hasListEnd(value).endCreate();
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

   public TemplatePO hasReferenceLookup(boolean value)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_REFERENCELOOKUP)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createReferenceLookup(boolean value)
   {
      this.startCreate().hasReferenceLookup(value).endCreate();
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
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_NAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO hasName(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Template.PROPERTY_NAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }

   public TemplatePO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
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

   public TemplatePO hasPlaceholders(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PLACEHOLDERS);
   }

   public TemplatePO createPlaceholders(PlaceHolderDescriptionPO tgt)
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

   public TemplatePO hasChooser(ChoiceTemplatePO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_CHOOSER);
   }

   public TemplatePO createChooser(ChoiceTemplatePO tgt)
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

   public TemplatePO hasMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_MATCHES);
   }

   public TemplatePO createMatches(MatchPO tgt)
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

   public TemplatePO hasParents(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PARENTS);
   }

   public TemplatePO createParents(PlaceHolderDescriptionPO tgt)
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

   public TemplatePO filterTemplateText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_TEMPLATETEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterTemplateText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_TEMPLATETEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterExpandedText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterExpandedText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterModelObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterModelClassName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterModelClassName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELCLASSNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterListStart(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSTART)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterListStart(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSTART)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterListSeparator(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterListSeparator(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSEPARATOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterListEnd(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTEND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterListEnd(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTEND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterReferenceLookup(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_REFERENCELOOKUP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterName(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_NAME)
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

   public TemplatePO filterPlaceholders(PlaceHolderDescriptionPO tgt)
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

   public TemplatePO filterChooser(ChoiceTemplatePO tgt)
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

   public TemplatePO filterMatches(MatchPO tgt)
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

   public TemplatePO filterParents(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PARENTS);
   }


   public TemplatePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TemplatePO createExpandedTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createExpandedTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createExpandedTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_EXPANDEDTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListEndCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTEND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListEndCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTEND)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListEndAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTEND)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListSeparatorCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListSeparatorCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSEPARATOR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListSeparatorAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSEPARATOR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListStartCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSTART)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListStartCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSTART)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createListStartAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_LISTSTART)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createModelClassNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createModelClassNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELCLASSNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createModelClassNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELCLASSNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createModelObjectCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createModelObjectAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createReferenceLookupCondition(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_REFERENCELOOKUP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createReferenceLookupAssignment(boolean value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_REFERENCELOOKUP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createTemplateTextCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_TEMPLATETEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createTemplateTextCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_TEMPLATETEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO createTemplateTextAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Template.PROPERTY_TEMPLATETEXT)
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
      super.hasLink(Template.PROPERTY_CHOOSER, result);
      
      return result;
   }

   public ChoiceTemplatePO createChooserPO(String modifier)
   {
      ChoiceTemplatePO result = new ChoiceTemplatePO(new ChoiceTemplate[]{});
      
      result.setModifier(modifier);
      super.hasLink(Template.PROPERTY_CHOOSER, result);
      
      return result;
   }

   public TemplatePO createChooserLink(ChoiceTemplatePO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_CHOOSER);
   }

   public TemplatePO createChooserLink(ChoiceTemplatePO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_CHOOSER, modifier);
   }

   public PlaceHolderDescriptionPO createPlaceholdersPO()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_PLACEHOLDERS, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createPlaceholdersPO(String modifier)
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(modifier);
      super.hasLink(Template.PROPERTY_PLACEHOLDERS, result);
      
      return result;
   }

   public TemplatePO createPlaceholdersLink(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PLACEHOLDERS);
   }

   public TemplatePO createPlaceholdersLink(PlaceHolderDescriptionPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PLACEHOLDERS, modifier);
   }

   public PlaceHolderDescriptionPO createParentsPO()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_PARENTS, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createParentsPO(String modifier)
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(modifier);
      super.hasLink(Template.PROPERTY_PARENTS, result);
      
      return result;
   }

   public TemplatePO createParentsLink(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PARENTS);
   }

   public TemplatePO createParentsLink(PlaceHolderDescriptionPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_PARENTS, modifier);
   }

   public MatchPO createMatchesPO()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Template.PROPERTY_MATCHES, result);
      
      return result;
   }

   public MatchPO createMatchesPO(String modifier)
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(modifier);
      super.hasLink(Template.PROPERTY_MATCHES, result);
      
      return result;
   }

   public TemplatePO createMatchesLink(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_MATCHES);
   }

   public TemplatePO createMatchesLink(MatchPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Template.PROPERTY_MATCHES, modifier);
   }

}
