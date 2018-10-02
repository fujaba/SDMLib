package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.modelspace.Task;
import org.sdmlib.modelspace.TaskBoard;
import org.sdmlib.modelspace.TaskLane;

public class TaskLanePO extends PatternObject<TaskLanePO, TaskLane>
{

    public TaskLaneSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskLaneSet matches = new TaskLaneSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TaskLane) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TaskLanePO(){
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TaskLanePO(TaskLane... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.modelspace.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public TaskLanePO hasHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO hasHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO createHostName(String value)
   {
      this.startCreate().hasHostName(value).endCreate();
      return this;
   }
   
   public String getHostName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskLane) getCurrentMatch()).getHostName();
      }
      return null;
   }
   
   public TaskLanePO withHostName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TaskLane) getCurrentMatch()).setHostName(value);
      }
      return this;
   }
   
   public TaskLanePO hasPortNo(long value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO hasPortNo(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO createPortNo(long value)
   {
      this.startCreate().hasPortNo(value).endCreate();
      return this;
   }
   
   public long getPortNo()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskLane) getCurrentMatch()).getPortNo();
      }
      return 0;
   }
   
   public TaskLanePO withPortNo(long value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TaskLane) getCurrentMatch()).setPortNo(value);
      }
      return this;
   }
   
   public TaskBoardPO hasBoard()
   {
      TaskBoardPO result = new TaskBoardPO(new TaskBoard[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_BOARD, result);
      
      return result;
   }

   public TaskBoardPO createBoard()
   {
      return this.startCreate().hasBoard().endCreate();
   }

   public TaskLanePO hasBoard(TaskBoardPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_BOARD);
   }

   public TaskLanePO createBoard(TaskBoardPO tgt)
   {
      return this.startCreate().hasBoard(tgt).endCreate();
   }

   public TaskBoard getBoard()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskLane) this.getCurrentMatch()).getBoard();
      }
      return null;
   }

   public TaskPO hasTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskPO createTasks()
   {
      return this.startCreate().hasTasks().endCreate();
   }

   public TaskLanePO hasTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_TASKS);
   }

   public TaskLanePO createTasks(TaskPO tgt)
   {
      return this.startCreate().hasTasks(tgt).endCreate();
   }

   public TaskSet getTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskLane) this.getCurrentMatch()).getTasks();
      }
      return null;
   }

   public TaskPO hasMyRequests()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_MYREQUESTS, result);
      
      return result;
   }

   public TaskPO createMyRequests()
   {
      return this.startCreate().hasMyRequests().endCreate();
   }

   public TaskLanePO hasMyRequests(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_MYREQUESTS);
   }

   public TaskLanePO createMyRequests(TaskPO tgt)
   {
      return this.startCreate().hasMyRequests(tgt).endCreate();
   }

   public TaskSet getMyRequests()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskLane) this.getCurrentMatch()).getMyRequests();
      }
      return null;
   }

   public TaskLanePO filterHostName(String value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO filterHostName(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO filterPortNo(long value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO filterPortNo(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskBoardPO filterBoard()
   {
      TaskBoardPO result = new TaskBoardPO(new TaskBoard[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_BOARD, result);
      
      return result;
   }

   public TaskLanePO filterBoard(TaskBoardPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_BOARD);
   }

   public TaskPO filterTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskLanePO filterTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_TASKS);
   }

   public TaskPO filterMyRequests()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_MYREQUESTS, result);
      
      return result;
   }

   public TaskLanePO filterMyRequests(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_MYREQUESTS);
   }


   public TaskLanePO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TaskLanePO createHostNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO createHostNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_HOSTNAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO createHostNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_HOSTNAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO createPortNoCondition(long value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO createPortNoCondition(long lower, long upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_PORTNO)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskLanePO createPortNoAssignment(long value)
   {
      new AttributeConstraint()
      .withAttrName(TaskLane.PROPERTY_PORTNO)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createMyRequestsPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_MYREQUESTS, result);
      
      return result;
   }

   public TaskPO createMyRequestsPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(TaskLane.PROPERTY_MYREQUESTS, result);
      
      return result;
   }

   public TaskLanePO createMyRequestsLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_MYREQUESTS);
   }

   public TaskLanePO createMyRequestsLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_MYREQUESTS, modifier);
   }

   public TaskBoardPO createBoardPO()
   {
      TaskBoardPO result = new TaskBoardPO(new TaskBoard[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_BOARD, result);
      
      return result;
   }

   public TaskBoardPO createBoardPO(String modifier)
   {
      TaskBoardPO result = new TaskBoardPO(new TaskBoard[]{});
      
      result.setModifier(modifier);
      super.hasLink(TaskLane.PROPERTY_BOARD, result);
      
      return result;
   }

   public TaskLanePO createBoardLink(TaskBoardPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_BOARD);
   }

   public TaskLanePO createBoardLink(TaskBoardPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_BOARD, modifier);
   }

   public TaskPO createTasksPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskLane.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskPO createTasksPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(TaskLane.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskLanePO createTasksLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_TASKS);
   }

   public TaskLanePO createTasksLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TaskLane.PROPERTY_TASKS, modifier);
   }

}
