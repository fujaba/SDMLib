package org.sdmlib.storyboards.creators;

import java.util.Vector;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.creators.StoryboardSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.storyboards.creators.StoryboardStepPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.storyboards.creators.StoryboardPO;
import org.sdmlib.storyboards.StoryboardStep;
import org.sdmlib.storyboards.creators.StoryboardStepSet;
import org.sdmlib.storyboards.creators.StoryboardWallPO;
import org.sdmlib.storyboards.StoryboardWall;

public class StoryboardPO extends PatternObject<StoryboardPO, Storyboard>
{
   public StoryboardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StoryboardSet matches = new StoryboardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Storyboard) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StoryboardStepPO hasStoryboardSteps()
   {
      StoryboardStepPO result = new StoryboardStepPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Storyboard.PROPERTY_STORYBOARDSTEPS, result);
      
      return result;
   }

   public StoryboardPO hasStoryboardSteps(StoryboardStepPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Storyboard.PROPERTY_STORYBOARDSTEPS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public StoryboardStepSet getStoryboardSteps()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Storyboard) this.getCurrentMatch()).getStoryboardSteps();
      }
      return null;
   }

   public StoryboardWallPO hasWall()
   {
      StoryboardWallPO result = new StoryboardWallPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Storyboard.PROPERTY_WALL, result);
      
      return result;
   }

   public StoryboardPO hasWall(StoryboardWallPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Storyboard.PROPERTY_WALL)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public StoryboardWall getWall()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Storyboard) this.getCurrentMatch()).getWall();
      }
      return null;
   }

   public StoryboardPO hasRootDir(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_ROOTDIR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getRootDir()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Storyboard) getCurrentMatch()).getRootDir();
      }
      return null;
   }
   
   public StoryboardPO withRootDir(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Storyboard) getCurrentMatch()).setRootDir(value);
      }
      return this;
   }
   
   public StoryboardPO hasStepCounter(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPCOUNTER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getStepCounter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Storyboard) getCurrentMatch()).getStepCounter();
      }
      return 0;
   }
   
   public StoryboardPO withStepCounter(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Storyboard) getCurrentMatch()).setStepCounter(value);
      }
      return this;
   }
   
   public StoryboardPO hasStepDoneCounter(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPDONECOUNTER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getStepDoneCounter()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Storyboard) getCurrentMatch()).getStepDoneCounter();
      }
      return 0;
   }
   
   public StoryboardPO withStepDoneCounter(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Storyboard) getCurrentMatch()).setStepDoneCounter(value);
      }
      return this;
   }
   
   public StoryboardPO hasRootDir(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_ROOTDIR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StoryboardPO hasStepCounter(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPCOUNTER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public StoryboardPO hasStepDoneCounter(int lower, int upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPDONECOUNTER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
}






