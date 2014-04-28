package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.creators.LogEntrySet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.TaskPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.LogEntryPO;
import org.sdmlib.replication.Task;

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

   public LogEntryPO hasStepName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(LogEntry.PROPERTY_STEPNAME).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getStepName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) getCurrentMatch()).getStepName();
      }
      return null;
   }

   public LogEntryPO withStepName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setStepName(value);
      }
      return this;
   }

   public LogEntryPO hasExecutedBy(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(LogEntry.PROPERTY_EXECUTEDBY).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public String getExecutedBy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) getCurrentMatch()).getExecutedBy();
      }
      return null;
   }

   public LogEntryPO withExecutedBy(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setExecutedBy(value);
      }
      return this;
   }

   public LogEntryPO hasTimeStamp(long value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(LogEntry.PROPERTY_TIMESTAMP).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public long getTimeStamp()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) getCurrentMatch()).getTimeStamp();
      }
      return 0;
   }

   public LogEntryPO withTimeStamp(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setTimeStamp(value);
      }
      return this;
   }

   public TaskPO hasTask()
   {
      TaskPO result = new TaskPO();
      result.setModifier(this.getPattern().getModifier());

      super.hasLink(LogEntry.PROPERTY_TASK, result);

      return result;
   }

   public LogEntryPO hasTask(TaskPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_TASK).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
   }

   public Task getTask()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getTask();
      }
      return null;
   }

   public LogEntryPO hasStepName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(LogEntry.PROPERTY_STEPNAME).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public LogEntryPO createStepName(String value)
   {
      this.startCreate().hasStepName(value).endCreate();
      return this;
   }

   public LogEntryPO hasExecutedBy(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(LogEntry.PROPERTY_EXECUTEDBY).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public LogEntryPO createExecutedBy(String value)
   {
      this.startCreate().hasExecutedBy(value).endCreate();
      return this;
   }

   public LogEntryPO hasTimeStamp(long lower, long upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(LogEntry.PROPERTY_TIMESTAMP).withTgtValue(lower)
         .withUpperTgtValue(upper).withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return this;
   }

   public LogEntryPO createTimeStamp(long value)
   {
      this.startCreate().hasTimeStamp(value).endCreate();
      return this;
   }

   public TaskPO createTask()
   {
      return this.startCreate().hasTask().endCreate();
   }

   public LogEntryPO createTask(TaskPO tgt)
   {
      return this.startCreate().hasTask(tgt).endCreate();
   }

}
