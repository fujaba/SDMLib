package org.sdmlib.storyboards.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardStep;
import org.sdmlib.storyboards.StoryboardWall;

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
   
   public LogEntryPO hasElementLogEntryPO(LogEntryStoryBoard hostGraphObject)
   {
      LogEntryPO value = new LogEntryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StoryboardPO hasElementStoryboardPO()
   {
      StoryboardPO value = new StoryboardPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StoryboardPO hasElementStoryboardPO(Storyboard hostGraphObject)
   {
      StoryboardPO value = new StoryboardPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StoryboardStepPO hasElementStoryboardStepPO()
   {
      StoryboardStepPO value = new StoryboardStepPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StoryboardStepPO hasElementStoryboardStepPO(StoryboardStep hostGraphObject)
   {
      StoryboardStepPO value = new StoryboardStepPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StoryboardWallPO hasElementStoryboardWallPO()
   {
      StoryboardWallPO value = new StoryboardWallPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StoryboardWallPO hasElementStoryboardWallPO(StoryboardWall hostGraphObject)
   {
      StoryboardWallPO value = new StoryboardWallPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}






