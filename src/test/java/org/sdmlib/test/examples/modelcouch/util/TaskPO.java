package org.sdmlib.test.examples.modelcouch.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.test.examples.modelcouch.DocumentData;
import org.sdmlib.test.examples.modelcouch.Person;
import org.sdmlib.test.examples.modelcouch.Task;

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
      newInstance(org.sdmlib.test.examples.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"));
   }

   public TaskPO(Task... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(org.sdmlib.test.examples.modelcouch.util.CreatorCreator.createIdMap("PatternObjectType"), hostGraphObject);
   }
   public TaskPO hasSubTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_SUBTASKS, result);
      
      return result;
   }

   public TaskPO createSubTasks()
   {
      return this.startCreate().hasSubTasks().endCreate();
   }

   public TaskPO hasSubTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_SUBTASKS);
   }

   public TaskPO createSubTasks(TaskPO tgt)
   {
      return this.startCreate().hasSubTasks(tgt).endCreate();
   }

   public TaskSet getSubTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getSubTasks();
      }
      return null;
   }

   public TaskPO hasParentTasks()
   {
      TaskPO result = new TaskPO(new Task[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_PARENTTASKS, result);
      
      return result;
   }

   public TaskPO createParentTasks()
   {
      return this.startCreate().hasParentTasks().endCreate();
   }

   public TaskPO hasParentTasks(TaskPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_PARENTTASKS);
   }

   public TaskPO createParentTasks(TaskPO tgt)
   {
      return this.startCreate().hasParentTasks(tgt).endCreate();
   }

   public TaskSet getParentTasks()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getParentTasks();
      }
      return null;
   }

   public PersonPO hasPersons()
   {
      PersonPO result = new PersonPO(new Person[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_PERSONS, result);
      
      return result;
   }

   public PersonPO createPersons()
   {
      return this.startCreate().hasPersons().endCreate();
   }

   public TaskPO hasPersons(PersonPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_PERSONS);
   }

   public TaskPO createPersons(PersonPO tgt)
   {
      return this.startCreate().hasPersons(tgt).endCreate();
   }

   public PersonSet getPersons()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getPersons();
      }
      return null;
   }

   public DocumentDataPO hasTaskData()
   {
      DocumentDataPO result = new DocumentDataPO(new DocumentData[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Task.PROPERTY_TASKDATA, result);
      
      return result;
   }

   public DocumentDataPO createTaskData()
   {
      return this.startCreate().hasTaskData().endCreate();
   }

   public TaskPO hasTaskData(DocumentDataPO tgt)
   {
      return hasLinkConstraint(tgt, Task.PROPERTY_TASKDATA);
   }

   public TaskPO createTaskData(DocumentDataPO tgt)
   {
      return this.startCreate().hasTaskData(tgt).endCreate();
   }

   public DocumentDataSet getTaskData()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Task) this.getCurrentMatch()).getTaskData();
      }
      return null;
   }

}
