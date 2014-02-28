package org.sdmlib.model.taskflows.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.model.taskflows.LogEntry;
import org.sdmlib.model.taskflows.creators.LogEntrySet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.model.taskflows.creators.LoggerPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.model.taskflows.creators.LogEntryPO;
import org.sdmlib.model.taskflows.Logger;

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
   
   public LogEntryPO hasNodeName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_NODENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getNodeName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) getCurrentMatch()).getNodeName();
      }
      return null;
   }
   
   public LogEntryPO withNodeName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setNodeName(value);
      }
      return this;
   }
   
   public LogEntryPO hasTaskName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TASKNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getTaskName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) getCurrentMatch()).getTaskName();
      }
      return null;
   }
   
   public LogEntryPO withTaskName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) getCurrentMatch()).setTaskName(value);
      }
      return this;
   }
   
   public LoggerPO hasLogger()
   {
      LoggerPO result = new LoggerPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(LogEntry.PROPERTY_LOGGER, result);
      
      return result;
   }

   public LogEntryPO hasLogger(LoggerPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_LOGGER)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Logger getLogger()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getLogger();
      }
      return null;
   }

   public LogEntryPO hasChildren()
   {
      LogEntryPO result = new LogEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(LogEntry.PROPERTY_CHILDREN, result);
      
      return result;
   }

   public LogEntryPO hasChildren(LogEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_CHILDREN)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public LogEntrySet getChildren()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getChildren();
      }
      return null;
   }

   public LogEntryPO hasParent()
   {
      LogEntryPO result = new LogEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(LogEntry.PROPERTY_PARENT, result);
      
      return result;
   }

   public LogEntryPO hasParent(LogEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_PARENT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public LogEntry getParent()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getParent();
      }
      return null;
   }

   public LogEntryPO hasNodeName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_NODENAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO createNodeName(String value)
   {
      this.startCreate().hasNodeName(value).endCreate();
      return this;
   }
   
   public LogEntryPO hasTaskName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TASKNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO createTaskName(String value)
   {
      this.startCreate().hasTaskName(value).endCreate();
      return this;
   }
   
   public LoggerPO createLogger()
   {
      return this.startCreate().hasLogger().endCreate();
   }

   public LogEntryPO createLogger(LoggerPO tgt)
   {
      return this.startCreate().hasLogger(tgt).endCreate();
   }

   public LogEntryPO createChildren()
   {
      return this.startCreate().hasChildren().endCreate();
   }

   public LogEntryPO createChildren(LogEntryPO tgt)
   {
      return this.startCreate().hasChildren(tgt).endCreate();
   }

   public LogEntryPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public LogEntryPO createParent(LogEntryPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
   }

}


