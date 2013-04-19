package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.Step;
import org.sdmlib.replication.creators.StepSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.TaskFlowBoardPO;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.StepPO;
import org.sdmlib.replication.TaskFlowBoard;
import org.sdmlib.replication.creators.ExecutorPO;
import org.sdmlib.replication.Executor;
import org.sdmlib.replication.creators.BoardTaskPO;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.creators.BoardTaskSet;

public class StepPO extends PatternObject<StepPO, Step>
{
   public StepSet allMatches()
   {
      this.setDoAllMatches(true);
      
      StepSet matches = new StepSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Step) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public StepPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(Step.PROPERTY_NAME)
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
         return ((Step) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public StepPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Step) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TaskFlowBoardPO hasBoard()
   {
      TaskFlowBoardPO result = new TaskFlowBoardPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Step.PROPERTY_BOARD, result);
      
      return result;
   }

   public StepPO hasBoard(TaskFlowBoardPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Step.PROPERTY_BOARD)
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
         return ((Step) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

   public ExecutorPO hasExecutor()
   {
      ExecutorPO result = new ExecutorPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Step.PROPERTY_EXECUTOR, result);
      
      return result;
   }

   public StepPO hasExecutor(ExecutorPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Step.PROPERTY_EXECUTOR)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Executor getExecutor()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Step) this.getCurrentMatch()).getExecutor();
      }
      return null;
   }

   public BoardTaskPO hasTasks()
   {
      BoardTaskPO result = new BoardTaskPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Step.PROPERTY_TASKS, result);
      
      return result;
   }

   public StepPO hasTasks(BoardTaskPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Step.PROPERTY_TASKS)
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
         return ((Step) this.getCurrentMatch()).getTasks();
      }
      return null;
   }

}

