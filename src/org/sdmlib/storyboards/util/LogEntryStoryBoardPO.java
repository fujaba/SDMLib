package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;

public class LogEntryStoryBoardPO extends PatternObject<LogEntryStoryBoardPO, LogEntryStoryBoard>
{
   public LogEntryStoryBoardPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LogEntryStoryBoardPO(LogEntryStoryBoard... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public KanbanEntryPO hasKanbanEntry()
   {
      KanbanEntryPO result = new KanbanEntryPO(new KanbanEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntryStoryBoard.PROPERTY_KANBANENTRY, result);
      
      return result;
   }
   
   public LogEntryStoryBoardPO hasKanbanEntry(KanbanEntryPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntryStoryBoard.PROPERTY_KANBANENTRY);
   }
   
   public LogEntryStoryBoardPO withKanbanEntry(KanbanEntryPO tgtPO)
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

   public LogEntryStoryBoardPO createKanbanEntry(KanbanEntryPO tgt)
   {
      return this.startCreate().hasKanbanEntry(tgt).endCreate();
   }

}



