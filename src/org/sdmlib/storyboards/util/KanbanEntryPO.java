package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.storyboards.util.LogEntryStoryBoardPO;
import org.sdmlib.storyboards.LogEntryStoryBoard;
import org.sdmlib.storyboards.util.KanbanEntryPO;

public class KanbanEntryPO extends PatternObject<KanbanEntryPO, KanbanEntry>
{
   public KanbanEntryPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public KanbanEntryPO(KanbanEntry... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
  }
   public LogEntryStoryBoardPO hasLogEntries()
   {
      LogEntryStoryBoardPO result = new LogEntryStoryBoardPO(new LogEntryStoryBoard[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(KanbanEntry.PROPERTY_LOGENTRIES, result);
      
      return result;
   }
   
   public KanbanEntryPO hasLogEntries(LogEntryStoryBoardPO tgt)
   {
      return hasLinkConstraint(tgt, KanbanEntry.PROPERTY_LOGENTRIES);
   }
   
   public KanbanEntryPO withLogEntries(LogEntryStoryBoardPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((KanbanEntry) this.getCurrentMatch()).withLogEntries((LogEntryStoryBoard) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public KanbanEntryPO withoutLogEntries(LogEntryStoryBoardPO tgtPO)
   {
      if (this.getPattern().getHasMatch())
      {
         ((KanbanEntry) this.getCurrentMatch()).withoutLogEntries((LogEntryStoryBoard) tgtPO.getCurrentMatch());
      }
      return this;
   }
   
   public LogEntryStoryboardSet getLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((KanbanEntry) this.getCurrentMatch()).getLogEntries();
      }
      return null;
   }
   
   public KanbanEntryPO hasOldNoOfLogEntries(int value)
   {
      new AttributeConstraint()
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
   public KanbanEntryPO hasOldNoOfLogEntries(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(KanbanEntry.PROPERTY_OLDNOOFLOGENTRIES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public KanbanEntryPO hasPhases(String value)
   {
      new AttributeConstraint()
      .withAttrName(KanbanEntry.PROPERTY_PHASES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public KanbanEntryPO hasPhases(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(KanbanEntry.PROPERTY_PHASES)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getPhases()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((KanbanEntry) getCurrentMatch()).getPhases();
      }
      return null;
   }
   
   public KanbanEntryPO withPhases(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((KanbanEntry) getCurrentMatch()).setPhases(value);
      }
      return this;
   }
   
   public KanbanEntryPO createOldNoOfLogEntries(int value)
   {
      this.startCreate().hasOldNoOfLogEntries(value).endCreate();
      return this;
   }
   
   public KanbanEntryPO createPhases(String value)
   {
      this.startCreate().hasPhases(value).endCreate();
      return this;
   }
   
   public LogEntryStoryBoardPO createLogEntries()
   {
      return this.startCreate().hasLogEntries().endCreate();
   }

   public KanbanEntryPO createLogEntries(LogEntryStoryBoardPO tgt)
   {
      return this.startCreate().hasLogEntries(tgt).endCreate();
   }

}







