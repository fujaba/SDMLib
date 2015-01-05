package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.RemoteTask;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.replication.util.LogEntryPO;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.util.RemoteTaskPO;
import org.sdmlib.replication.util.LogEntrySet;

public class RemoteTaskPO extends PatternObject<RemoteTaskPO, RemoteTask>
{

    public RemoteTaskSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RemoteTaskSet matches = new RemoteTaskSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((RemoteTask) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RemoteTaskPO(){
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public RemoteTaskPO(RemoteTask... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.replication.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public RemoteTaskPO hasBoardTask(BoardTask value)
   {
      new AttributeConstraint()
      .withAttrName(RemoteTask.PROPERTY_BOARDTASK)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public RemoteTaskPO createBoardTask(BoardTask value)
   {
      this.startCreate().hasBoardTask(value).endCreate();
      return this;
   }
   
   public BoardTask getBoardTask()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((RemoteTask) getCurrentMatch()).getBoardTask();
      }
      return null;
   }
   
   public RemoteTaskPO withBoardTask(BoardTask value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((RemoteTask) getCurrentMatch()).setBoardTask(value);
      }
      return this;
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

   public RemoteTaskPO hasLogEntries(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_LOGENTRIES);
   }

   public RemoteTaskPO createLogEntries(LogEntryPO tgt)
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

}
