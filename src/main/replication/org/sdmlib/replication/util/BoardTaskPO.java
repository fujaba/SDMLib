package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.Task;
import org.sdmlib.replication.util.SeppelSpaceProxyPO;
import org.sdmlib.replication.SeppelSpaceProxy;
import org.sdmlib.replication.util.BoardTaskPO;

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


   public BoardTaskPO(){
      newInstance(CreatorCreator.createIdMap("PatternObjectType"));
   }

   public BoardTaskPO(BoardTask... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public BoardTaskPO hasName(String value)
   {
      new AttributeConstraint()
      .withAttrName(BoardTask.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public BoardTaskPO hasName(String lower, String upper)
   {
      new AttributeConstraint()
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
   
   public BoardTaskPO hasStatus(String value)
   {
      new AttributeConstraint()
      .withAttrName(BoardTask.PROPERTY_STATUS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return this;
   }
   
   public BoardTaskPO hasStatus(String lower, String upper)
   {
      new AttributeConstraint()
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
   
   public LogEntryPO hasLogEntries()
   {
      LogEntryPO result = new LogEntryPO(new LogEntry[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_LOGENTRIES, result);
      
      return result;
   }

   public LogEntryPO createLogEntries()
   {
      return this.startCreate().hasLogEntries().endCreate();
   }

   public BoardTaskPO hasLogEntries(LogEntryPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_LOGENTRIES);
   }

   public BoardTaskPO createLogEntries(LogEntryPO tgt)
   {
      return this.startCreate().hasLogEntries(tgt).endCreate();
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
      LanePO result = new LanePO(new Lane[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BoardTask.PROPERTY_LANE, result);
      
      return result;
   }

   public LanePO createLane()
   {
      return this.startCreate().hasLane().endCreate();
   }

   public BoardTaskPO hasLane(LanePO tgt)
   {
      return hasLinkConstraint(tgt, BoardTask.PROPERTY_LANE);
   }

   public BoardTaskPO createLane(LanePO tgt)
   {
      return this.startCreate().hasLane(tgt).endCreate();
   }

   public Lane getLane()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) this.getCurrentMatch()).getLane();
      }
      return null;
   }

   public BoardTaskPO hasNext()
   {
      BoardTaskPO result = new BoardTaskPO(new BoardTask[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BoardTask.PROPERTY_NEXT, result);
      
      return result;
   }

   public BoardTaskPO createNext()
   {
      return this.startCreate().hasNext().endCreate();
   }

   public BoardTaskPO hasNext(BoardTaskPO tgt)
   {
      return hasLinkConstraint(tgt, BoardTask.PROPERTY_NEXT);
   }

   public BoardTaskPO createNext(BoardTaskPO tgt)
   {
      return this.startCreate().hasNext(tgt).endCreate();
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
      BoardTaskPO result = new BoardTaskPO(new BoardTask[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BoardTask.PROPERTY_PREV, result);
      
      return result;
   }

   public BoardTaskPO createPrev()
   {
      return this.startCreate().hasPrev().endCreate();
   }

   public BoardTaskPO hasPrev(BoardTaskPO tgt)
   {
      return hasLinkConstraint(tgt, BoardTask.PROPERTY_PREV);
   }

   public BoardTaskPO createPrev(BoardTaskPO tgt)
   {
      return this.startCreate().hasPrev(tgt).endCreate();
   }

   public BoardTaskSet getPrev()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) this.getCurrentMatch()).getPrev();
      }
      return null;
   }

   public SeppelSpaceProxyPO hasProxy()
   {
      SeppelSpaceProxyPO result = new SeppelSpaceProxyPO(new SeppelSpaceProxy[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(BoardTask.PROPERTY_PROXY, result);
      
      return result;
   }

   public SeppelSpaceProxyPO createProxy()
   {
      return this.startCreate().hasProxy().endCreate();
   }

   public BoardTaskPO hasProxy(SeppelSpaceProxyPO tgt)
   {
      return hasLinkConstraint(tgt, BoardTask.PROPERTY_PROXY);
   }

   public BoardTaskPO createProxy(SeppelSpaceProxyPO tgt)
   {
      return this.startCreate().hasProxy(tgt).endCreate();
   }

   public SeppelSpaceProxy getProxy()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((BoardTask) this.getCurrentMatch()).getProxy();
      }
      return null;
   }

}
