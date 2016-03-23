package org.sdmlib.simple.model.association_k.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.simple.model.association_k.Task;
import org.sdmlib.models.pattern.AttributeConstraint;
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
   public TaskPO filterName(String value)
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
   
   public TaskPO filterName(String lower, String upper)
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
   
   public TaskPO createName(String value)
   {
      this.startCreate().filterName(value).endCreate();
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
   
   public TaskPO filterParentTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_PARENTTASKS, result);
      
      return result;
   }

   public TaskPO createParentTasks()
   {
      return this.startCreate().filterParentTasks().endCreate();
   }

   public TaskPO filterParentTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_PARENTTASKS);
   }

   public TaskPO createParentTasks(TaskPO tgt)
   {
      return this.startCreate().filterParentTasks(tgt).endCreate();
   }

   public TaskSet getParentTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getParentTasks();
      }
      return null;
   }

   public TaskPO filterSubTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_SUBTASKS, result);
      
      return result;
   }

   public TaskPO createSubTasks()
   {
      return this.startCreate().filterSubTasks().endCreate();
   }

   public TaskPO filterSubTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_SUBTASKS);
   }

   public TaskPO createSubTasks(TaskPO tgt)
   {
      return this.startCreate().filterSubTasks(tgt).endCreate();
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
