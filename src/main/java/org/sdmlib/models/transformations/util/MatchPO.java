package org.sdmlib.models.transformations.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.models.transformations.util.TemplatePO;
import org.sdmlib.models.transformations.util.MatchPO;
import org.sdmlib.models.transformations.util.PlaceHolderDescriptionPO;

public class MatchPO extends PatternObject<MatchPO, Match>
{

    public MatchSet allMatches()
   {
      this.setDoAllMatches(true);
      
      MatchSet matches = new MatchSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Match) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public MatchPO(){
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public MatchPO(Match... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.models.transformations.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public MatchPO hasStartPos(int value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_STARTPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO hasStartPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_STARTPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO createStartPos(int value)
   {
      this.startCreate().hasStartPos(value).endCreate();
      return this;
   }
   
   public int getStartPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) getCurrentMatch()).getStartPos();
      }
      return 0;
   }
   
   public MatchPO withStartPos(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Match) getCurrentMatch()).setStartPos(value);
      }
      return this;
   }
   
   public MatchPO hasEndPos(int value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_ENDPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO hasEndPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_ENDPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO createEndPos(int value)
   {
      this.startCreate().hasEndPos(value).endCreate();
      return this;
   }
   
   public int getEndPos()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) getCurrentMatch()).getEndPos();
      }
      return 0;
   }
   
   public MatchPO withEndPos(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Match) getCurrentMatch()).setEndPos(value);
      }
      return this;
   }
   
   public MatchPO hasFullText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_FULLTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO hasFullText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_FULLTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO createFullText(String value)
   {
      this.startCreate().hasFullText(value).endCreate();
      return this;
   }
   
   public String getFullText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) getCurrentMatch()).getFullText();
      }
      return null;
   }
   
   public MatchPO withFullText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Match) getCurrentMatch()).setFullText(value);
      }
      return this;
   }
   
   public MatchPO hasMatchText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MATCHTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO hasMatchText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MATCHTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO createMatchText(String value)
   {
      this.startCreate().hasMatchText(value).endCreate();
      return this;
   }
   
   public String getMatchText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) getCurrentMatch()).getMatchText();
      }
      return null;
   }
   
   public MatchPO withMatchText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Match) getCurrentMatch()).setMatchText(value);
      }
      return this;
   }
   
   public MatchPO hasModelObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO createModelObject(Object value)
   {
      this.startCreate().hasModelObject(value).endCreate();
      return this;
   }
   
   public Object getModelObject()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) getCurrentMatch()).getModelObject();
      }
      return null;
   }
   
   public MatchPO withModelObject(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Match) getCurrentMatch()).setModelObject(value);
      }
      return this;
   }
   
   public TemplatePO hasTemplate()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Match.PROPERTY_TEMPLATE, result);
      
      return result;
   }

   public TemplatePO createTemplate()
   {
      return this.startCreate().hasTemplate().endCreate();
   }

   public MatchPO hasTemplate(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, Match.PROPERTY_TEMPLATE);
   }

   public MatchPO createTemplate(TemplatePO tgt)
   {
      return this.startCreate().hasTemplate(tgt).endCreate();
   }

   public Template getTemplate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) this.getCurrentMatch()).getTemplate();
      }
      return null;
   }

   public PlaceHolderDescriptionPO hasPlaceholder()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Match.PROPERTY_PLACEHOLDER, result);
      
      return result;
   }

   public PlaceHolderDescriptionPO createPlaceholder()
   {
      return this.startCreate().hasPlaceholder().endCreate();
   }

   public MatchPO hasPlaceholder(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Match.PROPERTY_PLACEHOLDER);
   }

   public MatchPO createPlaceholder(PlaceHolderDescriptionPO tgt)
   {
      return this.startCreate().hasPlaceholder(tgt).endCreate();
   }

   public PlaceHolderDescription getPlaceholder()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) this.getCurrentMatch()).getPlaceholder();
      }
      return null;
   }

   public MatchPO hasSubMatches()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Match.PROPERTY_SUBMATCHES, result);
      
      return result;
   }

   public MatchPO createSubMatches()
   {
      return this.startCreate().hasSubMatches().endCreate();
   }

   public MatchPO hasSubMatches(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Match.PROPERTY_SUBMATCHES);
   }

   public MatchPO createSubMatches(MatchPO tgt)
   {
      return this.startCreate().hasSubMatches(tgt).endCreate();
   }

   public MatchSet getSubMatches()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) this.getCurrentMatch()).getSubMatches();
      }
      return null;
   }

   public MatchPO hasParentMatch()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Match.PROPERTY_PARENTMATCH, result);
      
      return result;
   }

   public MatchPO createParentMatch()
   {
      return this.startCreate().hasParentMatch().endCreate();
   }

   public MatchPO hasParentMatch(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Match.PROPERTY_PARENTMATCH);
   }

   public MatchPO createParentMatch(MatchPO tgt)
   {
      return this.startCreate().hasParentMatch(tgt).endCreate();
   }

   public Match getParentMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) this.getCurrentMatch()).getParentMatch();
      }
      return null;
   }

   public MatchPO filterStartPos(int value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_STARTPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterStartPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_STARTPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterEndPos(int value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_ENDPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterEndPos(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_ENDPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterFullText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_FULLTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterFullText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_FULLTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterMatchText(String value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MATCHTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterMatchText(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MATCHTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public MatchPO filterModelObject(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MODELOBJECT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TemplatePO filterTemplate()
   {
      TemplatePO result = new TemplatePO(new Template[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Match.PROPERTY_TEMPLATE, result);
      
      return result;
   }

   public MatchPO filterTemplate(TemplatePO tgt)
   {
      return hasLinkConstraint(tgt, Match.PROPERTY_TEMPLATE);
   }

   public PlaceHolderDescriptionPO filterPlaceholder()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO(new PlaceHolderDescription[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Match.PROPERTY_PLACEHOLDER, result);
      
      return result;
   }

   public MatchPO filterPlaceholder(PlaceHolderDescriptionPO tgt)
   {
      return hasLinkConstraint(tgt, Match.PROPERTY_PLACEHOLDER);
   }

   public MatchPO filterParentMatch()
   {
      MatchPO result = new MatchPO(new Match[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Match.PROPERTY_PARENTMATCH, result);
      
      return result;
   }

   public MatchPO filterParentMatch(MatchPO tgt)
   {
      return hasLinkConstraint(tgt, Match.PROPERTY_PARENTMATCH);
   }

}
