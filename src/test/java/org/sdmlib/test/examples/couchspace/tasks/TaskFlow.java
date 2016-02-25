/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.test.examples.couchspace.tasks;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskSet;
import org.sdmlib.test.examples.couchspace.tasks.Task;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelcouch/ModelCouchTasksModel.java'>ModelCouchTasksModel.java</a>
 */
   public  class TaskFlow implements SendableEntity
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
   
      withoutTasks(this.getTasks().toArray(new Task[this.getTasks().size()]));
      withoutFirstTasks(this.getFirstTasks().toArray(new Task[this.getFirstTasks().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TITLE = "title";
   
   private String title;

   public String getTitle()
   {
      return this.title;
   }
   
   public void setTitle(String value)
   {
      if ( ! StrUtil.stringEquals(this.title, value)) {
      
         String oldValue = this.title;
         this.title = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TITLE, oldValue, value);
      }
   }
   
   public TaskFlow withTitle(String value)
   {
      setTitle(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getTitle());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * TaskFlow ----------------------------------- Task
    *              taskFlow                   tasks
    * </pre>
    */
   
   public static final String PROPERTY_TASKS = "tasks";

   private TaskSet tasks = null;
   
   public TaskSet getTasks()
   {
      if (this.tasks == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.tasks;
   }

   public TaskFlow withTasks(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.tasks == null)
            {
               this.tasks = new TaskSet();
            }
            
            boolean changed = this.tasks.add (item);

            if (changed)
            {
               item.withTaskFlow(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKS, null, item);
            }
         }
      }
      return this;
   } 

   public TaskFlow withoutTasks(Task... value)
   {
      for (Task item : value)
      {
         if ((this.tasks != null) && (item != null))
         {
            if (this.tasks.remove(item))
            {
               item.setTaskFlow(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKS, item, null);
            }
         }
      }
      return this;
   }

   public Task createTasks()
   {
      Task value = new Task();
      withTasks(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * TaskFlow ----------------------------------- Task
    *              taskFlowFirst                   firstTasks
    * </pre>
    */
   
   public static final String PROPERTY_FIRSTTASKS = "firstTasks";

   private TaskSet firstTasks = null;
   
   public TaskSet getFirstTasks()
   {
      if (this.firstTasks == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.firstTasks;
   }

   public TaskFlow withFirstTasks(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.firstTasks == null)
            {
               this.firstTasks = new TaskSet();
            }
            
            boolean changed = this.firstTasks.add (item);

            if (changed)
            {
               item.withTaskFlowFirst(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FIRSTTASKS, null, item);
            }
         }
      }
      return this;
   } 

   public TaskFlow withoutFirstTasks(Task... value)
   {
      for (Task item : value)
      {
         if ((this.firstTasks != null) && (item != null))
         {
            if (this.firstTasks.remove(item))
            {
               item.setTaskFlowFirst(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_FIRSTTASKS, item, null);
            }
         }
      }
      return this;
   }

   public Task createFirstTasks()
   {
      Task value = new Task();
      withFirstTasks(value);
      return value;
   } 
}
