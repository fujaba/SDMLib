package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntry;

public class KanbanEntryPO extends PatternObject
{
   public LogEntryPO hasLogEntries()
   {
      LogEntryPO result = new LogEntryPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(KanbanEntry.PROPERTY_LOGENTRIES)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public KanbanEntryPO hasLogEntries(LogEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(KanbanEntry.PROPERTY_LOGENTRIES)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public KanbanEntryPO withLogEntries(LogEntryPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((KanbanEntry) this.getCurrentMatch()).withLogEntries((LogEntry) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public KanbanEntryPO withoutLogEntries(LogEntryPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((KanbanEntry) this.getCurrentMatch()).withoutLogEntries((LogEntry) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public LogEntrySet getLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((KanbanEntry) this.getCurrentMatch()).getLogEntries();
      }
      return null;
   }
   
   public KanbanEntryPO hasOldNoOfLogEntries(int value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(KanbanEntry.PROPERTY_OLDNOOFLOGENTRIES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public int getOldNoOfLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((KanbanEntry) getCurrentMatch()).getOldNoOfLogEntries();
      }
      return 0;
   }
   
   public KanbanEntryPO withOldNoOfLogEntries(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((KanbanEntry) getCurrentMatch()).setOldNoOfLogEntries(value);
      }
      return this;
   }
}




