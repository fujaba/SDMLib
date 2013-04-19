package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.creators.BoardTaskSet;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.LogEntryPO;
import org.sdmlib.replication.Task;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.replication.creators.BoardTaskPO;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.creators.LogEntrySet;
import org.sdmlib.replication.creators.StepPO;
import org.sdmlib.replication.Step;
import org.sdmlib.replication.creators.LanePO;
import org.sdmlib.replication.Lane;

public class BoardTaskPO extends PatternObject<BoardTaskPO, BoardTask>
{
   public BoardTaskSet allMatches()
   {
      this.setDoAllMatches(true);
      
      BoardTaskSet matches = new BoardTaskSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((BoardTask) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
   public BoardTaskPO hasName(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(BoardTask.PROPERTY_NAME)
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
         return ((BoardTask) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public BoardTaskPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((BoardTask) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public LogEntryPO hasLogEntries()
   {
      LogEntryPO result = new LogEntryPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(Task.PROPERTY_LOGENTRIES, result);
      
      return result;
   }

   public BoardTaskPO hasLogEntries(LogEntryPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(Task.PROPERTY_LOGENTRIES)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public LogEntrySet getLogEntries()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getLogEntries();
      }
      return null;
   }

   public StepPO hasCurrentStep()
   {
      StepPO result = new StepPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(BoardTask.PROPERTY_CURRENTSTEP, result);
      
      return result;
   }

   public BoardTaskPO hasCurrentStep(StepPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(BoardTask.PROPERTY_CURRENTSTEP)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Step getCurrentStep()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) this.getCurrentMatch()).getCurrentStep();
      }
      return null;
   }

   public LanePO hasLane()
   {
      LanePO result = new LanePO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(BoardTask.PROPERTY_LANE, result);
      
      return result;
   }

   public BoardTaskPO hasLane(LanePO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(BoardTask.PROPERTY_LANE)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public Lane getLane()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) this.getCurrentMatch()).getLane();
      }
      return null;
   }

}

