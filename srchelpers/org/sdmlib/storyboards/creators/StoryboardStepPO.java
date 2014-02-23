package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.StoryboardStep;
import org.sdmlib.storyboards.creators.StoryboardStepSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.storyboards.creators.StoryboardPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.storyboards.creators.StoryboardStepPO;
import org.sdmlib.storyboards.Storyboard;

public class StoryboardStepPO extends PatternObject<StoryboardStepPO, StoryboardStep>
{
   public StoryboardStepSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoryboardStepSet matches = new StoryboardStepSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((StoryboardStep) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StoryboardStepPO hasText(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(StoryboardStep.PROPERTY_TEXT)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getText()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StoryboardStep) getCurrentMatch()).getText();
      }
      return null;
   }
   
   public StoryboardStepPO withText(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((StoryboardStep) getCurrentMatch()).setText(value);
      }
      return this;
   }
   
   public StoryboardPO hasStoryboard()
   {
      StoryboardPO result = new StoryboardPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(StoryboardStep.PROPERTY_STORYBOARD, result);
      
      return result;
   }

   public StoryboardStepPO hasStoryboard(StoryboardPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(StoryboardStep.PROPERTY_STORYBOARD)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Storyboard getStoryboard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StoryboardStep) this.getCurrentMatch()).getStoryboard();
      }
      return null;
   }

   public StoryboardStepPO hasText(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(StoryboardStep.PROPERTY_TEXT)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}


