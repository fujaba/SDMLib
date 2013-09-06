package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntry;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public KanbanEntryPO hasElementKanbanEntryPO()
   {
      KanbanEntryPO value = new KanbanEntryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public KanbanEntryPO hasElementKanbanEntryPO(KanbanEntry hostGraphObject)
   {
      KanbanEntryPO value = new KanbanEntryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LogEntryPO hasElementLogEntryPO()
   {
      LogEntryPO value = new LogEntryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LogEntryPO hasElementLogEntryPO(LogEntry hostGraphObject)
   {
      LogEntryPO value = new LogEntryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


