package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.util.LogEntryPO;
import org.sdmlib.replication.util.TaskPO;

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


   public TaskPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TaskPO(Task... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LogEntryPO hasLogEntries()
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_LOGENTRIES, result);
      
      return result;
   }

   public LogEntryPO createLogEntries()
   {
      return this.startCreate().hasLogEntries().endCreate();
   }

   public TaskPO hasLogEntries(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_LOGENTRIES);
   }

   public TaskPO createLogEntries(LogEntryPO tgt)
   {
      return this.startCreate().hasLogEntries(tgt).endCreate();
   }

   public LogEntrySet getLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getLogEntries();
      }
      return null;
   }

   public LogEntryPO filterLogEntries()
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_LOGENTRIES, result);
      
      return result;
   }

   public TaskPO filterLogEntries(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_LOGENTRIES);
   }

}
