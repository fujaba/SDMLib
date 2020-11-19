package org.sdmlib.test.examples.couchspace.tasks.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;

public class TaskFlowPO extends PatternObject<TaskFlowPO, TaskFlow>
{

    public TaskFlowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskFlowSet matches = new TaskFlowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((TaskFlow) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TaskFlowPO(){
      newInstance(null);
   }

   public TaskFlowPO(TaskFlow... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public TaskFlowPO filterTitle(String value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO filterTitle(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TITLE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createTitle(String value)
   {
      this.startCreate().filterTitle(value).endCreate();
      return this;
   }
   
   public String getTitle()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) getCurrentMatch()).getTitle();
      }
      return null;
   }
   
   public TaskFlowPO withTitle(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((TaskFlow) getCurrentMatch()).setTitle(value);
      }
      return this;
   }
   
   public TaskPO filterTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskPO createTasks()
   {
      return this.startCreate().filterTasks().endCreate();
   }

   public TaskFlowPO filterTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_TASKS);
   }

   public TaskFlowPO createTasks(TaskPO tgt)
   {
      return this.startCreate().filterTasks(tgt).endCreate();
   }

   public TaskSet getTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getTasks();
      }
      return null;
   }

   public TaskPO filterFirstTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_FIRSTTASKS, result);
      
      return result;
   }

   public TaskPO createFirstTasks()
   {
      return this.startCreate().filterFirstTasks().endCreate();
   }

   public TaskFlowPO filterFirstTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_FIRSTTASKS);
   }

   public TaskFlowPO createFirstTasks(TaskPO tgt)
   {
      return this.startCreate().filterFirstTasks(tgt).endCreate();
   }

   public TaskSet getFirstTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((TaskFlow) this.getCurrentMatch()).getFirstTasks();
      }
      return null;
   }


   public TaskFlowPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TaskFlowPO createTitleCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createTitleCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TITLE)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskFlowPO createTitleAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(TaskFlow.PROPERTY_TITLE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createFirstTasksPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_FIRSTTASKS, result);
      
      return result;
   }

   public TaskPO createFirstTasksPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(TaskFlow.PROPERTY_FIRSTTASKS, result);
      
      return result;
   }

   public TaskFlowPO createFirstTasksLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_FIRSTTASKS);
   }

   public TaskFlowPO createFirstTasksLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_FIRSTTASKS, modifier);
   }

   public TaskPO createTasksPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(TaskFlow.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskPO createTasksPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(TaskFlow.PROPERTY_TASKS, result);
      
      return result;
   }

   public TaskFlowPO createTasksLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_TASKS);
   }

   public TaskFlowPO createTasksLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, TaskFlow.PROPERTY_TASKS, modifier);
   }

}
