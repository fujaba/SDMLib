package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.TaskBoard;
import org.sdmlib.modelspace.util.TaskLanePO;
import org.sdmlib.modelspace.TaskLane;
import org.sdmlib.modelspace.util.TaskBoardPO;
import org.sdmlib.modelspace.util.TaskLaneSet;

public class TaskBoardPO extends PatternObject<TaskBoardPO, TaskBoard>
{

    public TaskBoardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskBoardSet matches = new TaskBoardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TaskBoard) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TaskBoardPO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TaskBoardPO(TaskBoard... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public TaskLanePO hasLanes()
   {
      TaskLanePO result = new TaskLanePO(new TaskLane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskBoard.PROPERTY_LANES, result);
      
      return result;
   }

   public TaskLanePO createLanes()
   {
      return this.startCreate().hasLanes().endCreate();
   }

   public TaskBoardPO hasLanes(TaskLanePO tgt)
   {
      return hasLinkConstraint(tgt, TaskBoard.PROPERTY_LANES);
   }

   public TaskBoardPO createLanes(TaskLanePO tgt)
   {
      return this.startCreate().hasLanes(tgt).endCreate();
   }

   public TaskLaneSet getLanes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskBoard) this.getCurrentMatch()).getLanes();
      }
      return null;
   }

   public TaskLanePO filterLanes()
   {
      TaskLanePO result = new TaskLanePO(new TaskLane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskBoard.PROPERTY_LANES, result);
      
      return result;
   }

   public TaskBoardPO filterLanes(TaskLanePO tgt)
   {
      return hasLinkConstraint(tgt, TaskBoard.PROPERTY_LANES);
   }

}
