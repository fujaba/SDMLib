package org.sdmlib.simple.model.association_k.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_k.Task;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.simple.model.association_k.util.TaskPO;
import org.sdmlib.simple.model.association_k.util.TaskSet;

public class TaskPO extends PatternObject<TaskPO, Task>
{

    public TaskSet allMatches()
   {
      this.setDoAllMatches(true);
      
      TaskSet matches = new TaskSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Task) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public TaskPO(){
      newInstance(null);
   }

   public TaskPO(Task... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public TaskPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public TaskPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public TaskPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Task.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public TaskPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Task) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TaskPO createParentTasksPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_PARENTTASKS, result);
      
      return result;
   }

   public TaskPO createParentTasksPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_PARENTTASKS, result);
      
      return result;
   }

   public TaskPO createParentTasksLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_PARENTTASKS);
   }

   public TaskPO createParentTasksLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_PARENTTASKS, modifier);
   }

   public TaskSet getParentTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getParentTasks();
      }
      return null;
   }

   public TaskPO createSubTasksPO()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_SUBTASKS, result);
      
      return result;
   }

   public TaskPO createSubTasksPO(String modifier)
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(modifier);
      super.hasLink(Task.PROPERTY_SUBTASKS, result);
      
      return result;
   }

   public TaskPO createSubTasksLink(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_SUBTASKS);
   }

   public TaskPO createSubTasksLink(TaskPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_SUBTASKS, modifier);
   }

   public TaskSet getSubTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getSubTasks();
      }
      return null;
   }

}
