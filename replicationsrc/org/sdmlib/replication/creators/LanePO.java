package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.creators.LaneSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.TaskFlowBoardPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.LanePO;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.replication.creators.BoardTaskPO;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.creators.BoardTaskSet;
import java.lang.Object;
import java.beans.PropertyChangeSupport;

public class LanePO extends PatternObject<LanePO, Lane>
{
   public LaneSet allMatches()
   {
      this.setDoAllMatches(true);
      
      LaneSet matches = new LaneSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Lane) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public LanePO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Lane.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lane) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public LanePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Lane) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TaskFlowBoardPO hasBoard()
   {
      TaskFlowBoardPO result = new TaskFlowBoardPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Lane.PROPERTY_BOARD, result);
      
      return result;
   }

   public LanePO hasBoard(TaskFlowBoardPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lane.PROPERTY_BOARD)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public TaskFlowBoard getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lane) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

   public BoardTaskPO hasTasks()
   {
      BoardTaskPO result = new BoardTaskPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Lane.PROPERTY_TASKS, result);
      
      return result;
   }

   public LanePO hasTasks(BoardTaskPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Lane.PROPERTY_TASKS)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public BoardTaskSet getTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Lane) this.getCurrentMatch()).getTasks();
      }
      return null;
   }

}



