/*
   Copyright (c) 2016 deeptought
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.test.examples.modelcouch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.test.examples.modelcouch.util.DocumentDataSet;
import org.sdmlib.test.examples.modelcouch.util.PersonSet;
import org.sdmlib.test.examples.modelcouch.util.TaskSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/test/examples/modelcouch/BasicModel.java'>BasicModel.java</a>
 */
   public  class Task implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
   
      withoutSubTasks(this.getSubTasks().toArray(new Task[this.getSubTasks().size()]));
      withoutParentTasks(this.getParentTasks().toArray(new Task[this.getParentTasks().size()]));
      withoutPersons(this.getPersons().toArray(new Person[this.getPersons().size()]));
      withoutTaskData(this.getTaskData().toArray(new DocumentData[this.getTaskData().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Task ----------------------------------- Task
    *              parentTasks                   subTasks
    * </pre>
    */
   
   public static final String PROPERTY_SUBTASKS = "subTasks";

   private TaskSet subTasks = null;
   
   public TaskSet getSubTasks()
   {
      if (this.subTasks == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.subTasks;
   }
   public TaskSet getSubTasksTransitive()
   {
      TaskSet result = new TaskSet().with(this);
      return result.getSubTasksTransitive();
   }


   public Task withSubTasks(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.subTasks == null)
            {
               this.subTasks = new TaskSet();
            }
            
            boolean changed = this.subTasks.add (item);

            if (changed)
            {
               item.withParentTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBTASKS, null, item);
            }
         }
      }
      return this;
   } 

   public Task withoutSubTasks(Task... value)
   {
      for (Task item : value)
      {
         if ((this.subTasks != null) && (item != null))
         {
            if (this.subTasks.remove(item))
            {
               item.withoutParentTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBTASKS, item, null);
            }
         }
      }
      return this;
   }

   public Task createSubTasks()
   {
      Task value = new Task();
      withSubTasks(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Task ----------------------------------- Task
    *              subTasks                   parentTasks
    * </pre>
    */
   
   public static final String PROPERTY_PARENTTASKS = "parentTasks";

   private TaskSet parentTasks = null;
   
   public TaskSet getParentTasks()
   {
      if (this.parentTasks == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.parentTasks;
   }
   public TaskSet getParentTasksTransitive()
   {
      TaskSet result = new TaskSet().with(this);
      return result.getParentTasksTransitive();
   }


   public Task withParentTasks(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.parentTasks == null)
            {
               this.parentTasks = new TaskSet();
            }
            
            boolean changed = this.parentTasks.add (item);

            if (changed)
            {
               item.withSubTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENTTASKS, null, item);
            }
         }
      }
      return this;
   } 

   public Task withoutParentTasks(Task... value)
   {
      for (Task item : value)
      {
         if ((this.parentTasks != null) && (item != null))
         {
            if (this.parentTasks.remove(item))
            {
               item.withoutSubTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENTTASKS, item, null);
            }
         }
      }
      return this;
   }

   public Task createParentTasks()
   {
      Task value = new Task();
      withParentTasks(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Task ----------------------------------- Person
    *              tasks                   persons
    * </pre>
    */
   
   public static final String PROPERTY_PERSONS = "persons";

   private PersonSet persons = null;
   
   public PersonSet getPersons()
   {
      if (this.persons == null)
      {
         return PersonSet.EMPTY_SET;
      }
   
      return this.persons;
   }

   public Task withPersons(Person... value)
   {
      if(value==null){
         return this;
      }
      for (Person item : value)
      {
         if (item != null)
         {
            if (this.persons == null)
            {
               this.persons = new PersonSet();
            }
            
            boolean changed = this.persons.add (item);

            if (changed)
            {
               item.withTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, null, item);
            }
         }
      }
      return this;
   } 

   public Task withoutPersons(Person... value)
   {
      for (Person item : value)
      {
         if ((this.persons != null) && (item != null))
         {
            if (this.persons.remove(item))
            {
               item.withoutTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PERSONS, item, null);
            }
         }
      }
      return this;
   }

   public Person createPersons()
   {
      Person value = new Person();
      withPersons(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Task ----------------------------------- DocumentData
    *              tasks                   taskData
    * </pre>
    */
   
   public static final String PROPERTY_TASKDATA = "taskData";

   private DocumentDataSet taskData = null;
   
   public DocumentDataSet getTaskData()
   {
      if (this.taskData == null)
      {
         return DocumentDataSet.EMPTY_SET;
      }
   
      return this.taskData;
   }

   public Task withTaskData(DocumentData... value)
   {
      if(value==null){
         return this;
      }
      for (DocumentData item : value)
      {
         if (item != null)
         {
            if (this.taskData == null)
            {
               this.taskData = new DocumentDataSet();
            }
            
            boolean changed = this.taskData.add (item);

            if (changed)
            {
               item.withTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKDATA, null, item);
            }
         }
      }
      return this;
   } 

   public Task withoutTaskData(DocumentData... value)
   {
      for (DocumentData item : value)
      {
         if ((this.taskData != null) && (item != null))
         {
            if (this.taskData.remove(item))
            {
               item.withoutTasks(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKDATA, item, null);
            }
         }
      }
      return this;
   }

   public DocumentData createTaskData()
   {
      DocumentData value = new DocumentData();
      withTaskData(value);
      return value;
   } 
}
