package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.creators.TaskSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.LogEntryPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.TaskPO;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.creators.LogEntrySet;

public class TaskPO extends PatternObject<TaskPO, Task>
{
   public TaskSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskSet matches = new TaskSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Task) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public LogEntryPO hasLogEntries()
   {
      LogEntryPO result = new LogEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Task.PROPERTY_LOGENTRIES, result);
      
      return result;
   }

   public TaskPO hasLogEntries(LogEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Task.PROPERTY_LOGENTRIES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public LogEntrySet getLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getLogEntries();
      }
      return null;
   }

}

