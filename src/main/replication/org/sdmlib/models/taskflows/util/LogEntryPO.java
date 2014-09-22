package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.taskflows.LogEntry;
import org.sdmlib.models.taskflows.Logger;

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
   public LogEntryPO hasNodeName(String value)
   {
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_NODENAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO hasNodeName(String lower, String upper)
   {
      new AttributeConstraint()
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
      new AttributeConstraint()
      .withAttrName(LogEntry.PROPERTY_TASKNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO hasTaskName(String lower, String upper)
   {
      new AttributeConstraint()
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
      LoggerPO result = new LoggerPO(new Logger[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_LOGGER, result);
      
      return result;
   }

   public LoggerPO createLogger()
   {
      return this.startCreate().hasLogger().endCreate();
   }

   public LogEntryPO hasLogger(LoggerPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_LOGGER);
   }

   public LogEntryPO createLogger(LoggerPO tgt)
   {
      return this.startCreate().hasLogger(tgt).endCreate();
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
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_CHILDREN, result);
      
      return result;
   }

   public LogEntryPO createChildren()
   {
      return this.startCreate().hasChildren().endCreate();
   }

   public LogEntryPO hasChildren(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_CHILDREN);
   }

   public LogEntryPO createChildren(LogEntryPO tgt)
   {
      return this.startCreate().hasChildren(tgt).endCreate();
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
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntry.PROPERTY_PARENT, result);
      
      return result;
   }

   public LogEntryPO createParent()
   {
      return this.startCreate().hasParent().endCreate();
   }

   public LogEntryPO hasParent(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntry.PROPERTY_PARENT);
   }

   public LogEntryPO createParent(LogEntryPO tgt)
   {
      return this.startCreate().hasParent(tgt).endCreate();
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
