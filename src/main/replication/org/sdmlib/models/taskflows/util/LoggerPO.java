package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.taskflows.LogEntry;
import org.sdmlib.models.taskflows.Logger;
import org.sdmlib.models.taskflows.PeerProxy;
import org.sdmlib.models.taskflows.TaskFlow;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.taskflows.util.LogEntryPO;
import org.sdmlib.models.taskflows.util.LoggerPO;
import org.sdmlib.models.taskflows.util.TaskFlowPO;

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

   public LoggerPO filterStartPeer(PeerProxy value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_STARTPEER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO filterTaskNo(int value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO filterTaskNo(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO filterIdMap(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO filterParent()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_PARENT, result);
      
      return result;
   }

   public LoggerPO filterParent(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_PARENT);
   }

   public TaskFlowPO filterSubFlow()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public LoggerPO filterSubFlow(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_SUBFLOW);
   }

   public LogEntryPO filterEntries()
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Logger.PROPERTY_ENTRIES, result);
      
      return result;
   }

   public LoggerPO filterEntries(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_ENTRIES);
   }


   public LoggerPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public LoggerPO createStartPeerCondition(PeerProxy value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_STARTPEER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO createStartPeerAssignment(PeerProxy value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_STARTPEER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO createIdMapCondition(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO createIdMapAssignment(SDMLibJsonIdMap value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO createTaskNoCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO createTaskNoCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LoggerPO createTaskNoAssignment(int value)
   {
      new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public LogEntryPO createEntriesPO()
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Logger.PROPERTY_ENTRIES, result);
      
      return result;
   }

   public LogEntryPO createEntriesPO(String modifier)
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(modifier);
      super.hasLink(Logger.PROPERTY_ENTRIES, result);
      
      return result;
   }

   public LoggerPO createEntriesLink(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_ENTRIES);
   }

   public LoggerPO createEntriesLink(LogEntryPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_ENTRIES, modifier);
   }

   public TaskFlowPO createParentPO()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Logger.PROPERTY_PARENT, result);
      
      return result;
   }

   public TaskFlowPO createParentPO(String modifier)
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(modifier);
      super.hasLink(Logger.PROPERTY_PARENT, result);
      
      return result;
   }

   public LoggerPO createParentLink(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_PARENT);
   }

   public LoggerPO createParentLink(TaskFlowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_PARENT, modifier);
   }

   public TaskFlowPO createSubFlowPO()
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Logger.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public TaskFlowPO createSubFlowPO(String modifier)
   {
      TaskFlowPO result = new TaskFlowPO(new TaskFlow[]{});
      
      result.setModifier(modifier);
      super.hasLink(Logger.PROPERTY_SUBFLOW, result);
      
      return result;
   }

   public LoggerPO createSubFlowLink(TaskFlowPO tgt)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_SUBFLOW);
   }

   public LoggerPO createSubFlowLink(TaskFlowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Logger.PROPERTY_SUBFLOW, modifier);
   }

}
