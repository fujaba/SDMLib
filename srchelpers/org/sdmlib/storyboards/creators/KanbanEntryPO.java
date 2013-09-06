package org.sdmlib.storyboards.creators;

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
   
}


