package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;

public class LogEntryPO extends PatternObject<LogEntryPO, LogEntryStoryBoard>
{
   public KanbanEntryPO hasKanbanEntry()
   {
      KanbanEntryPO result = new KanbanEntryPO();
      
      PatternLink patternLink = new PatternLink()
      .withTgt(result).withTgtRoleName(LogEntryStoryBoard.PROPERTY_KANBANENTRY)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().addToElements(result);
      
      this.getPattern().findMatch();
      
      return result;
   }
   
   public LogEntryPO hasKanbanEntry(KanbanEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(LogEntryStoryBoard.PROPERTY_KANBANENTRY)
      .withSrc(this);
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public LogEntryPO withKanbanEntry(KanbanEntryPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((LogEntryStoryBoard) this.getCurrentMatch()).withKanbanEntry((KanbanEntry) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public KanbanEntry getKanbanEntry()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntryStoryBoard) this.getCurrentMatch()).getKanbanEntry();
      }
      return null;
   }
   
   public KanbanEntryPO createKanbanEntry()
   {
      return this.startCreate().hasKanbanEntry().endCreate();
   }

   public LogEntryPO createKanbanEntry(KanbanEntryPO tgt)
   {
      return this.startCreate().hasKanbanEntry(tgt).endCreate();
   }

}



