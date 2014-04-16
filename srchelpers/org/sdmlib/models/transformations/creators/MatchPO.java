package org.sdmlib.models.transformations.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.transformations.Match;
import org.sdmlib.models.transformations.PlaceHolderDescription;
import org.sdmlib.models.transformations.Template;

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
   
   public MatchPO hasStartPos(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_STARTPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_ENDPOS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_FULLTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MATCHTEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MODELOBJECT)
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
      TemplatePO result = new TemplatePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Match.PROPERTY_TEMPLATE, result);
      
      return result;
   }

   public MatchPO hasTemplate(TemplatePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Match.PROPERTY_TEMPLATE)
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
         return ((Match) this.getCurrentMatch()).getTemplate();
      }
      return null;
   }

   public PlaceHolderDescriptionPO hasPlaceholder()
   {
      PlaceHolderDescriptionPO result = new PlaceHolderDescriptionPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Match.PROPERTY_PLACEHOLDER, result);
      
      return result;
   }

   public MatchPO hasPlaceholder(PlaceHolderDescriptionPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Match.PROPERTY_PLACEHOLDER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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
      MatchPO result = new MatchPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Match.PROPERTY_SUBMATCHES, result);
      
      return result;
   }

   public MatchPO hasSubMatches(MatchPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Match.PROPERTY_SUBMATCHES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
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
      MatchPO result = new MatchPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Match.PROPERTY_PARENTMATCH, result);
      
      return result;
   }

   public MatchPO hasParentMatch(MatchPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Match.PROPERTY_PARENTMATCH)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Match getParentMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Match) this.getCurrentMatch()).getParentMatch();
      }
      return null;
   }

   public MatchPO hasStartPos(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_STARTPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchPO hasEndPos(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_ENDPOS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchPO hasFullText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_FULLTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchPO hasMatchText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MATCHTEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchPO hasModelObject(Object lower, Object upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Match.PROPERTY_MODELOBJECT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public MatchPO createStartPos(int value)
   {
      this.startCreate().hasStartPos(value).endCreate();
      return this;
   }
   
   public MatchPO createEndPos(int value)
   {
      this.startCreate().hasEndPos(value).endCreate();
      return this;
   }
   
   public MatchPO createFullText(String value)
   {
      this.startCreate().hasFullText(value).endCreate();
      return this;
   }
   
   public MatchPO createMatchText(String value)
   {
      this.startCreate().hasMatchText(value).endCreate();
      return this;
   }
   
   public MatchPO createModelObject(Object value)
   {
      this.startCreate().hasModelObject(value).endCreate();
      return this;
   }
   
   public TemplatePO createTemplate()
   {
      return this.startCreate().hasTemplate().endCreate();
   }

   public MatchPO createTemplate(TemplatePO tgt)
   {
      return this.startCreate().hasTemplate(tgt).endCreate();
   }

   public PlaceHolderDescriptionPO createPlaceholder()
   {
      return this.startCreate().hasPlaceholder().endCreate();
   }

   public MatchPO createPlaceholder(PlaceHolderDescriptionPO tgt)
   {
      return this.startCreate().hasPlaceholder(tgt).endCreate();
   }

   public MatchPO createSubMatches()
   {
      return this.startCreate().hasSubMatches().endCreate();
   }

   public MatchPO createSubMatches(MatchPO tgt)
   {
      return this.startCreate().hasSubMatches(tgt).endCreate();
   }

   public MatchPO createParentMatch()
   {
      return this.startCreate().hasParentMatch().endCreate();
   }

   public MatchPO createParentMatch(MatchPO tgt)
   {
      return this.startCreate().hasParentMatch(tgt).endCreate();
   }

}




