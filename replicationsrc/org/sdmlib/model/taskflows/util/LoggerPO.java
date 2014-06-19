package org.sdmlib.model.taskflows.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.util.TaskFlowPO;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.util.LoggerPO;
import org.sdmlib.model.taskflows.util.LogEntryPO;
import org.sdmlib.model.taskflows.LogEntry;
import org.sdmlib.model.taskflows.util.LogEntrySet;

public class LoggerPO extends PatternObject<LoggerPO, Logger>
{

    public LoggerSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LoggerSet matches = new LoggerSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Logger) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LoggerPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LoggerPO(Logger... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public LoggerPO hasStartPeer(PeerProxy value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_STARTPEER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LoggerPO createStartPeer(PeerProxy value)
   {
      this.startCreate().hasStartPeer(value).endCreate();
      return this;
   }
   
   public PeerProxy getStartPeer()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Logger) getCurrentMatch()).getStartPeer();
      }
      return null;
   }
   
   public LoggerPO withStartPeer(PeerProxy value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Logger) getCurrentMatch()).setStartPeer(value);
      }
      return this;
   }
   
   public LoggerPO hasTaskNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LoggerPO hasTaskNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LoggerPO createTaskNo(int value)
   {
      this.startCreate().hasTaskNo(value).endCreate();
      return this;
   }
   
   public int getTaskNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Logger) getCurrentMatch()).getTaskNo();
      }
      return 0;
   }
   
   public LoggerPO withTaskNo(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Logger) getCurrentMatch()).setTaskNo(value);
      }
      return this;
   }
   
   public LoggerPO hasIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LoggerPO createIdMap(SDMLibJsonIdMap value)
   {
      this.startCreate().hasIdMap(value).endCreate();
      return this;
   }
   
   public SDMLibJsonIdMap getIdMap()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Logger) getCurrentMatch()).getIdMap();
      }
      return null;
   }
   
   public LoggerPO withIdMap(SDMLibJsonIdMap value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Logger) getCurrentMatch()).setIdMap(value);
      }
      return this;
   }
   
   public TaskFlowPO hasSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO createSubFlow()
   {
      return this.startCreate().hasSubFlow().endCreate();
   }

   public LoggerPO hasSubFlow(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_SUBFLOW);
   }

   public LoggerPO createSubFlow(TaskFlowPO tgt)
   {
      return this.startCreate().hasSubFlow(tgt).endCreate();
   }

   public TaskFlow getSubFlow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getSubFlow();
      }
      return null;
   }

   public TaskFlowPO hasParent()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public LoggerPO hasParent(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_PARENT);
   }

   public LoggerPO createParent(TaskFlowPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

   public TaskFlow getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public LogEntryPO hasEntries()
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Logger.PROPERTY_ENTRIES, result);
      
      return result;
   }

   public LogEntryPO createEntries()
   {
      return this.startCreate().hasEntries().endCreate();
   }

   public LoggerPO hasEntries(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_ENTRIES);
   }

   public LoggerPO createEntries(LogEntryPO tgt)
   {
      return this.startCreate().hasEntries(tgt).endCreate();
   }

   public LogEntrySet getEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Logger) this.getCurrentMatch()).getEntries();
      }
      return null;
   }

}
