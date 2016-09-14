package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.StoryboardImpl;
import org.sdmlib.storyboards.StoryboardStep;

public class StoryboardPO extends PatternObject<StoryboardPO, StoryboardImpl>
{
   public StoryboardPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public StoryboardPO(StoryboardImpl... hostGraphObject) {
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
         matches.add((StoryboardImpl) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }

   public StoryboardStepPO hasStoryboardSteps()
   {
      StoryboardStepPO result = new StoryboardStepPO(new StoryboardStep[]{});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(StoryboardImpl.PROPERTY_STORYBOARDSTEPS, result);

      return result;
   }

   public StoryboardPO hasStoryboardSteps(StoryboardStepPO tgt)
   {
      return hasLinkConstraint(tgt, StoryboardImpl.PROPERTY_STORYBOARDSTEPS);
   }

   public StoryboardStepSet getStoryboardSteps()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((StoryboardImpl) this.getCurrentMatch()).getStoryboardSteps();
      }
      return null;
   }

   public StoryboardPO hasRootDir(String value)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_ROOTDIR)
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
         return ((StoryboardImpl) getCurrentMatch()).getRootDir();
      }
      return null;
   }

   public StoryboardPO withRootDir(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((StoryboardImpl) getCurrentMatch()).setRootDir(value);
      }
      return this;
   }

   public StoryboardPO hasStepCounter(int value)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_STEPCOUNTER)
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
         return ((StoryboardImpl) getCurrentMatch()).getStepCounter();
      }
      return 0;
   }

   public StoryboardPO withStepCounter(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((StoryboardImpl) getCurrentMatch()).setStepCounter(value);
      }
      return this;
   }

   public StoryboardPO hasStepDoneCounter(int value)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_STEPDONECOUNTER)
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
         return ((StoryboardImpl) getCurrentMatch()).getStepDoneCounter();
      }
      return 0;
   }

   public StoryboardPO withStepDoneCounter(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((StoryboardImpl) getCurrentMatch()).setStepDoneCounter(value);
      }
      return this;
   }

   public StoryboardPO hasRootDir(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_ROOTDIR)
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
      .withAttrName(StoryboardImpl.PROPERTY_STEPCOUNTER)
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
      .withAttrName(StoryboardImpl.PROPERTY_STEPDONECOUNTER)
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

   public StoryboardPO withRootDirConstraint(String value)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_ROOTDIR)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO withRootDirConstraint(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_ROOTDIR)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO withStepCounterConstraint(int value)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_STEPCOUNTER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO withStepCounterConstraint(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_STEPCOUNTER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO withStepDoneCounterConstraint(int value)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_STEPDONECOUNTER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardPO withStepDoneCounterConstraint(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(StoryboardImpl.PROPERTY_STEPDONECOUNTER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public StoryboardStepPO createStoryboardStepsPO()
   {
      StoryboardStepPO result = new StoryboardStepPO(new StoryboardStep[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(StoryboardImpl.PROPERTY_STORYBOARDSTEPS, result);
      
      return result;
   }

   public StoryboardPO withStoryboardStepsLink(StoryboardStepPO tgt)
   {
      return hasLinkConstraint(tgt, StoryboardImpl.PROPERTY_STORYBOARDSTEPS);
   }

}







