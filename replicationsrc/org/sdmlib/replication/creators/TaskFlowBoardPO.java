package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.replication.creators.TaskFlowBoardSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.LanePO;
import org.sdmlib.replication.creators.TaskFlowBoardPO;

public class TaskFlowBoardPO extends PatternObject<TaskFlowBoardPO, TaskFlowBoard>
{
   public TaskFlowBoardSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskFlowBoardSet matches = new TaskFlowBoardSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TaskFlowBoard) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public LanePO hasLanes()
   {
      LanePO result = new LanePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(TaskFlowBoard.PROPERTY_LANES, result);
      
      return result;
   }

   public TaskFlowBoardPO hasLanes(LanePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(TaskFlowBoard.PROPERTY_LANES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public LaneSet getLanes()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlowBoard) this.getCurrentMatch()).getLanes();
      }
      return null;
   }

   public LanePO createLanes()
   {
      return this.startCreate().hasLanes().endCreate();
   }

   public TaskFlowBoardPO createLanes(LanePO tgt)
   {
      return this.startCreate().hasLanes(tgt).endCreate();
   }

}


