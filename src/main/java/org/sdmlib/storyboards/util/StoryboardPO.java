package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardStep;
import org.sdmlib.storyboards.StoryboardWall;
import org.sdmlib.storyboards.util.StoryboardWallPO;
import org.sdmlib.storyboards.util.StoryboardPO;
import org.sdmlib.storyboards.util.StoryboardStepPO;

public class StoryboardPO extends PatternObject<StoryboardPO, Storyboard>
{
   public StoryboardPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StoryboardPO(Storyboard... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
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
      StoryboardStepPO result = new StoryboardStepPO(new StoryboardStep[]{});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Storyboard.PROPERTY_STORYBOARDSTEPS, result);

      return result;
   }

   public StoryboardPO hasStoryboardSteps(StoryboardStepPO tgt)
   {
      return hasLinkConstraint(tgt, Storyboard.PROPERTY_STORYBOARDSTEPS);
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
      StoryboardWallPO result = new StoryboardWallPO(new StoryboardWall[]{});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Storyboard.PROPERTY_WALL, result);

      return result;
   }

   public StoryboardPO hasWall(StoryboardWallPO tgt)
   {
      return hasLinkConstraint(tgt, Storyboard.PROPERTY_WALL);
   }

//   public StoryboardWall getWall()
//   {
//      if (this.getPattern().getHasMatch())
//      {
//         return ((Storyboard) this.getCurrentMatch()).getWall();
//      }
//      return null;
//   }

   public StoryboardPO hasRootDir(String value)
   {
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
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
      new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPDONECOUNTER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public StoryboardPO createRootDir(String value)
   {
      this.startCreate().hasRootDir(value).endCreate();
      return this;
   }

   public StoryboardPO createStepCounter(int value)
   {
      this.startCreate().hasStepCounter(value).endCreate();
      return this;
   }

   public StoryboardPO createStepDoneCounter(int value)
   {
      this.startCreate().hasStepDoneCounter(value).endCreate();
      return this;
   }

   public StoryboardWallPO createWall()
   {
      return this.startCreate().hasWall().endCreate();
   }

   public StoryboardPO createWall(StoryboardWallPO tgt)
   {
      return this.startCreate().hasWall(tgt).endCreate();
   }

   public StoryboardStepPO createStoryboardSteps()
   {
      return this.startCreate().hasStoryboardSteps().endCreate();
   }

   public StoryboardPO createStoryboardSteps(StoryboardStepPO tgt)
   {
      return this.startCreate().hasStoryboardSteps(tgt).endCreate();
   }

   public StoryboardWall getWall()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Storyboard) this.getCurrentMatch()).getWall();
      }
      return null;
   }

   public StoryboardPO filterRootDir(String value)
   {
      new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_ROOTDIR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO filterRootDir(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_ROOTDIR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO filterStepCounter(int value)
   {
      new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPCOUNTER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO filterStepCounter(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPCOUNTER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO filterStepDoneCounter(int value)
   {
      new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPDONECOUNTER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO filterStepDoneCounter(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Storyboard.PROPERTY_STEPDONECOUNTER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardWallPO filterWall()
   {
      StoryboardWallPO result = new StoryboardWallPO(new StoryboardWall[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Storyboard.PROPERTY_WALL, result);
      
      return result;
   }

   public StoryboardPO filterWall(StoryboardWallPO tgt)
   {
      return hasLinkConstraint(tgt, Storyboard.PROPERTY_WALL);
   }

   public StoryboardStepPO filterStoryboardSteps()
   {
      StoryboardStepPO result = new StoryboardStepPO(new StoryboardStep[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Storyboard.PROPERTY_STORYBOARDSTEPS, result);
      
      return result;
   }

   public StoryboardPO filterStoryboardSteps(StoryboardStepPO tgt)
   {
      return hasLinkConstraint(tgt, Storyboard.PROPERTY_STORYBOARDSTEPS);
   }

}







