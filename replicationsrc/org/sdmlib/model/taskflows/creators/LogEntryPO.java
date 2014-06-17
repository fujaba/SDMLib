package org.sdmlib.model.taskflows.creators;

import org.sdmlib.logger.LogEntry;
import org.sdmlib.logger.Logger;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;

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
         .withAttrName(LogEntry.PROPERTY_NODENAME).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
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

   public LogEntryPO hasTaskName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
         .withAttrName(LogEntry.PROPERTY_TASKNAME).withTgtValue(value)
         .withSrc(this).withModifier(this.getPattern().getModifier())
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
         .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_LOGGER).withSrc(this)
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

   public LogEntryPO hasLogger(TaskFlowPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
         .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_LOGGER).withSrc(this)
         .withModifier(this.getPattern().getModifier());

      this.getPattern().addToElements(patternLink);

      this.getPattern().findMatch();

      return this;
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
         .withSrc(this).withModifier(this.getPattern().getModifier());

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
         .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_PARENT).withSrc(this)
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

}
