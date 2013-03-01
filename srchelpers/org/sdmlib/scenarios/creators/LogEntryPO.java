package org.sdmlib.scenarios.creators;

import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.scenarios.KanbanEntry;
import org.sdmlib.scenarios.LogEntry;

public class LogEntryPO extends PatternObject
{
   public KanbanEntryPO hasKanbanEntry()
   {
      KanbanEntryPO result = new KanbanEntryPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(LogEntry.PROPERTY_KANBANENTRY)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LogEntryPO hasKanbanEntry(KanbanEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LogEntry.PROPERTY_KANBANENTRY)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO withKanbanEntry(KanbanEntryPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntry) this.getCurrentMatch()).withKanbanEntry((KanbanEntry) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public KanbanEntry getKanbanEntry()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntry) this.getCurrentMatch()).getKanbanEntry();
      }
      return null;
   }
   
}


