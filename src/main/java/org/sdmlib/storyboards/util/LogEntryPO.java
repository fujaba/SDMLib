package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.LogEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.storyboards.util.GoalPO;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.util.LogEntryPO;
import org.sdmlib.storyboards.util.MikadoLogPO;
import org.sdmlib.storyboards.MikadoLog;

public class LogEntryPO extends PatternObject<LogEntryPO, LogEntry>
{

    public LogEntrySet allMatches()
   {
      this.setDoAllMatches(true);
      
      LogEntrySet matches = new LogEntrySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LogEntry) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LogEntryPO(){
      newInstance(null);
   }

   public LogEntryPO(LogEntry... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public LogEntryPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LogEntryPO createDateCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_DATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createDateCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_DATE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createDateAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_DATE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getDate()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) getCurrentMatch()).getDate();
      }
      return null;
   }
   
   public LogEntryPO withDate(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setDate(value);
      }
      return this;
   }
   
   public LogEntryPO createHoursDoneCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_HOURSDONE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createHoursDoneCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_HOURSDONE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createHoursDoneAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_HOURSDONE)
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
         return ((LogEntry) getCurrentMatch()).getHoursDone();
      }
      return 0;
   }
   
   public LogEntryPO withHoursDone(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setHoursDone(value);
      }
      return this;
   }
   
   public LogEntryPO createHoursRemainingCondition(double value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_HOURSREMAINING)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createHoursRemainingCondition(double lower, double upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_HOURSREMAINING)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createHoursRemainingAssignment(double value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_HOURSREMAINING)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public double getHoursRemaining()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) getCurrentMatch()).getHoursRemaining();
      }
      return 0;
   }
   
   public LogEntryPO withHoursRemaining(double value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setHoursRemaining(value);
      }
      return this;
   }
   
   public GoalPO createGoalPO()
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_GOAL, result);
      
      return result;
   }

   public GoalPO createGoalPO(String modifier)
   {
      GoalPO result = new GoalPO(new Goal[]{});
      
      result.setModifier(modifier);
      super.hasLink(LogEntry.PROPERTY_GOAL, result);
      
      return result;
   }

   public LogEntryPO createGoalLink(GoalPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_GOAL);
   }

   public LogEntryPO createGoalLink(GoalPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_GOAL, modifier);
   }

   public Goal getGoal()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getGoal();
      }
      return null;
   }

   public MikadoLogPO createParentPO()
   {
      MikadoLogPO result = new MikadoLogPO(new MikadoLog[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_PARENT, result);
      
      return result;
   }

   public MikadoLogPO createParentPO(String modifier)
   {
      MikadoLogPO result = new MikadoLogPO(new MikadoLog[]{});
      
      result.setModifier(modifier);
      super.hasLink(LogEntry.PROPERTY_PARENT, result);
      
      return result;
   }

   public LogEntryPO createParentLink(MikadoLogPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_PARENT);
   }

   public LogEntryPO createParentLink(MikadoLogPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_PARENT, modifier);
   }

   public MikadoLog getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getParent();
      }
      return null;
   }

}
