package org.sdmlib.replication.creators;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.creators.BoardTaskSet;
import org.sdmlib.models.pattern.PatternLink;
import org.sdmlib.replication.creators.LogEntryPO;
import org.sdmlib.replication.creators.BoardTaskPO;
import org.sdmlib.replication.creators.LanePO;

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

   public BoardTaskPO hasStatus(String value)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(BoardTask.PROPERTY_STATUS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public String getStatus()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) getCurrentMatch()).getStatus();
      }
      return null;
   }
   
   public BoardTaskPO withStatus(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((BoardTask) getCurrentMatch()).setStatus(value);
      }
      return this;
   }
   
   public BoardTaskPO hasNext()
   {
      BoardTaskPO result = new BoardTaskPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(BoardTask.PROPERTY_NEXT, result);
      
      return result;
   }

   public BoardTaskPO hasNext(BoardTaskPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(BoardTask.PROPERTY_NEXT)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public BoardTaskSet getNext()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) this.getCurrentMatch()).getNext();
      }
      return null;
   }

   public BoardTaskPO hasPrev()
   {
      BoardTaskPO result = new BoardTaskPO();
      result.setModifier(this.getPattern().getModifier());
      
      super.hasLink(BoardTask.PROPERTY_PREV, result);
      
      return result;
   }

   public BoardTaskPO hasPrev(BoardTaskPO tgt)
   {
      LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
      .withTgt(tgt).withTgtRoleName(BoardTask.PROPERTY_PREV)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier());
      
      this.getPattern().addToElements(patternLink);
      
      this.getPattern().findMatch();
      
      return this;
   }

   public BoardTaskSet getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public BoardTaskPO hasName(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(BoardTask.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public BoardTaskPO createName(String value)
   {
      this.startCreate().hasName(value).endCreate();
      return this;
   }
   
   public BoardTaskPO hasStatus(String lower, String upper)
   {
      AttributeConstraint constr = (AttributeConstraint) new AttributeConstraint()
      .withAttrName(BoardTask.PROPERTY_STATUS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public BoardTaskPO createStatus(String value)
   {
      this.startCreate().hasStatus(value).endCreate();
      return this;
   }
   
   public LogEntryPO createLogEntries()
   {
      return this.startCreate().hasLogEntries().endCreate();
   }

   public BoardTaskPO createLogEntries(LogEntryPO tgt)
   {
      return this.startCreate().hasLogEntries(tgt).endCreate();
   }

   public LanePO createLane()
   {
      return this.startCreate().hasLane().endCreate();
   }

   public BoardTaskPO createLane(LanePO tgt)
   {
      return this.startCreate().hasLane(tgt).endCreate();
   }

   public BoardTaskPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public BoardTaskPO createNext(BoardTaskPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
   }

   public BoardTaskPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public BoardTaskPO createPrev(BoardTaskPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

}



