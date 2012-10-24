package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.creators.LoggerSet;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.taskflows.creators.LogEntryPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.model.taskflows.creators.LoggerPO;
import org.sdmlib.model.taskflows.LogEntry;
import org.sdmlib.model.taskflows.creators.LogEntrySet;
import org.sdmlib.model.taskflows.creators.TaskFlowPO;

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
   
   public LoggerPO hasTaskNo(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_TASKNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public LoggerPO hasIdMap(SDMLibJsonIdMap value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Logger.PROPERTY_IDMAP)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
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
   
   public LogEntryPO hasEntries()
   {
      LogEntryPO result = new LogEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Logger.PROPERTY_ENTRIES, result);
      
      return result;
   }
   
   public LoggerPO hasEntries(LogEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Logger.PROPERTY_ENTRIES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntrySet getEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Logger) this.getCurrentMatch()).getEntries();
      }
      return null;
   }
   
   public TaskFlowPO hasTargetTaskFlow()
   {
      TaskFlowPO result = new TaskFlowPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Logger.PROPERTY_TARGETTASKFLOW, result);
      
      return result;
   }
   
   public LoggerPO hasTargetTaskFlow(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Logger.PROPERTY_TARGETTASKFLOW)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public TaskFlow getTargetTaskFlow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Logger) this.getCurrentMatch()).getTargetTaskFlow();
      }
      return null;
   }
   
}


