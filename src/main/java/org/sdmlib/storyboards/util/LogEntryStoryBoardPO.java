package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;

public class LogEntryStoryBoardPO extends PatternObject<LogEntryStoryBoardPO, LogEntryStoryBoard>
{

    public LogEntryStoryBoardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LogEntryStoryBoardSet matches = new LogEntryStoryBoardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((LogEntryStoryBoard) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public LogEntryStoryBoardPO(){
      newInstance(org.sdmlib.storyboards.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public LogEntryStoryBoardPO(LogEntryStoryBoard... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.storyboards.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public KanbanEntryPO hasKanbanEntry()
   {
      KanbanEntryPO result = new KanbanEntryPO(new KanbanEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(LogEntryStoryBoard.PROPERTY_KANBANENTRY, result);
      
      return result;
   }

   public KanbanEntryPO createKanbanEntry()
   {
      return this.startCreate().hasKanbanEntry().endCreate();
   }

   public LogEntryStoryBoardPO hasKanbanEntry(KanbanEntryPO tgt)
   {
      return hasLinkConstraint(tgt, LogEntryStoryBoard.PROPERTY_KANBANENTRY);
   }

   public LogEntryStoryBoardPO createKanbanEntry(KanbanEntryPO tgt)
   {
      return this.startCreate().hasKanbanEntry(tgt).endCreate();
   }

   public KanbanEntry getKanbanEntry()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((LogEntryStoryBoard) this.getCurrentMatch()).getKanbanEntry();
      }
      return null;
   }

}
