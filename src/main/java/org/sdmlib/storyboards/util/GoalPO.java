package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.storyboards.util.GoalPO;
import org.sdmlib.storyboards.util.GoalSet;

public class GoalPO extends PatternObject<GoalPO, Goal>
{

    public GoalSet allMatches()
   {
      this.setDoAllMatches(true);
      
      GoalSet matches = new GoalSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Goal) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public GoalPO(){
      newInstance(null);
   }

   public GoalPO(Goal... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public GoalPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public GoalPO createDescriptionCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GoalPO createDescriptionCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_DESCRIPTION)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GoalPO createDescriptionAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_DESCRIPTION)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getDescription()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Goal) getCurrentMatch()).getDescription();
      }
      return null;
   }
   
   public GoalPO withDescription(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Goal) getCurrentMatch()).setDescription(value);
      }
      return this;
   }
   
   public GoalPO createHoursDoneCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_HOURSDONE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GoalPO createHoursDoneCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_HOURSDONE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GoalPO createHoursDoneAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_HOURSDONE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getHoursDone()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Goal) getCurrentMatch()).getHoursDone();
      }
      return 0;
   }
   
   public GoalPO withHoursDone(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Goal) getCurrentMatch()).setHoursDone(value);
      }
      return this;
   }
   
   public GoalPO createHoursTodoCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_HOURSTODO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GoalPO createHoursTodoCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_HOURSTODO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public GoalPO createHoursTodoAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(Goal.PROPERTY_HOURSTODO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getHoursTodo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Goal) getCurrentMatch()).getHoursTodo();
      }
      return 0;
   }
   
   public GoalPO withHoursTodo(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Goal) getCurrentMatch()).setHoursTodo(value);
      }
      return this;
   }
   
   public GoalPO createParentsPO()
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Goal.PROPERTY_PARENTS, result);
      
      return result;
   }

   public GoalPO createParentsPO(String modifier)
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(modifier);
      super.hasLink(Goal.PROPERTY_PARENTS, result);
      
      return result;
   }

   public GoalPO createParentsLink(GoalPO tgt)
   {
      return hasLinkConstraint(tgt, Goal.PROPERTY_PARENTS);
   }

   public GoalPO createParentsLink(GoalPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Goal.PROPERTY_PARENTS, modifier);
   }

   public GoalSet getParents()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Goal) this.getCurrentMatch()).getParents();
      }
      return null;
   }

   public GoalPO createPreGoalsPO()
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Goal.PROPERTY_PREGOALS, result);
      
      return result;
   }

   public GoalPO createPreGoalsPO(String modifier)
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(modifier);
      super.hasLink(Goal.PROPERTY_PREGOALS, result);
      
      return result;
   }

   public GoalPO createPreGoalsLink(GoalPO tgt)
   {
      return hasLinkConstraint(tgt, Goal.PROPERTY_PREGOALS);
   }

   public GoalPO createPreGoalsLink(GoalPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Goal.PROPERTY_PREGOALS, modifier);
   }

   public GoalSet getPreGoals()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Goal) this.getCurrentMatch()).getPreGoals();
      }
      return null;
   }

}
