package org.sdmlib.storyboards.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.storyboards.util.LogEntryStoryBoardPO;
import org.sdmlib.storyboards.LogEntryStoryBoard;
import org.sdmlib.storyboards.util.KanbanEntryPO;
import org.sdmlib.storyboards.util.LogEntryStoryBoardSet;

public class KanbanEntryPO extends PatternObject<KanbanEntryPO, KanbanEntry>
{

    public KanbanEntrySet allMatches()
   {
      this.setDoAllMatches(true);
      
      KanbanEntrySet matches = new KanbanEntrySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((KanbanEntry) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public KanbanEntryPO(){
      newInstance(org.sdmlib.storyboards.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public KanbanEntryPO(KanbanEntry... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.storyboards.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public KanbanEntryPO hasOldNoOfLogEntries(int value)
   {
      new AttributeConstraint()
      .withAttrName(KanbanEntry.PROPERTY_OLDNOOFLOGENTRIES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
      
      super.filterAttr();
      
      return this;
   }
   
   public KanbanEntryPO createOldNoOfLogEntries(int value)
   {
      this.startCreate().hasOldNoOfLogEntries(value).endCreate();
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
   
   public KanbanEntryPO hasPhases(String value)
   {
      new AttributeConstraint()
      .withAttrName(KanbanEntry.PROPERTY_PHASES)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
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
      
      super.filterAttr();
      
      return this;
   }
   
   public KanbanEntryPO createPhases(String value)
   {
      this.startCreate().hasPhases(value).endCreate();
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
   
   public LogEntryStoryBoardPO hasLogEntries()
   {
      LogEntryStoryBoardPO result = new LogEntryStoryBoardPO(new LogEntryStoryBoard[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(KanbanEntry.PROPERTY_LOGENTRIES, result);
      
      return result;
   }

   public LogEntryStoryBoardPO createLogEntries()
   {
      return this.startCreate().hasLogEntries().endCreate();
   }

   public KanbanEntryPO hasLogEntries(LogEntryStoryBoardPO tgt)
   {
      return hasLinkConstraint(tgt, KanbanEntry.PROPERTY_LOGENTRIES);
   }

   public KanbanEntryPO createLogEntries(LogEntryStoryBoardPO tgt)
   {
      return this.startCreate().hasLogEntries(tgt).endCreate();
   }

   public LogEntryStoryBoardSet getLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((KanbanEntry) this.getCurrentMatch()).getLogEntries();
      }
      return null;
   }

}
