package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.Task;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.replication.util.TaskPO;
import org.sdmlib.replication.util.LogEntryPO;

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
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LogEntryPO(LogEntry... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LogEntryPO hasStepName(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_STEPNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO hasStepName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_STEPNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
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
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_EXECUTEDBY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO hasExecutedBy(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_EXECUTEDBY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
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
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TIMESTAMP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO hasTimeStamp(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TIMESTAMP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
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
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_TASK, result);
      
      return result;
   }

   public TaskPO createTask()
   {
      return this.startCreate().hasTask().endCreate();
   }

   public LogEntryPO hasTask(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_TASK);
   }

   public LogEntryPO createTask(TaskPO tgt)
   {
      return this.startCreate().hasTask(tgt).endCreate();
   }

   public Task getTask()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getTask();
      }
      return null;
   }

   public LogEntryPO filterStepName(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_STEPNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO filterStepName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_STEPNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO filterExecutedBy(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_EXECUTEDBY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO filterExecutedBy(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_EXECUTEDBY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO filterTimeStamp(long value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TIMESTAMP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO filterTimeStamp(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TIMESTAMP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO filterTask()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_TASK, result);
      
      return result;
   }

   public LogEntryPO filterTask(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_TASK);
   }


   public LogEntryPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LogEntryPO createExecutedByCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_EXECUTEDBY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createExecutedByCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_EXECUTEDBY)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createExecutedByAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_EXECUTEDBY)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createStepNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_STEPNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createStepNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_STEPNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createStepNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_STEPNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createTimeStampCondition(long value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TIMESTAMP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createTimeStampCondition(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TIMESTAMP)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createTimeStampAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TIMESTAMP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createTaskPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_TASK, result);
      
      return result;
   }

   public TaskPO createTaskPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(LogEntry.PROPERTY_TASK, result);
      
      return result;
   }

   public LogEntryPO createTaskLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_TASK);
   }

   public LogEntryPO createTaskLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_TASK, modifier);
   }

}
