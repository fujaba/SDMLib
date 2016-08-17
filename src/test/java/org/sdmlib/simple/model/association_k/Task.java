/*
   Copyright (c) 2016 Stefan
   
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
   
package org.sdmlib.simple.model.association_k;

import de.uniks.networkparser.interfaces.SendableEntity;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import de.uniks.networkparser.EntityUtil;
import org.sdmlib.simple.model.association_k.util.TaskSet;
   /**
    * 
    * @see <a href='../../../../../../../../src/test/java/org/sdmlib/simple/TestAssociation.java'>TestAssociation.java</a>
 */
   public  class Task implements SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = null;
   
   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(listener);
   	return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners = new PropertyChangeSupport(this);
   	}
   	listeners.addPropertyChangeListener(propertyName, listener);
   	return true;
   }
   
   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
   	if (listeners == null) {
   		listeners.removePropertyChangeListener(listener);
   	}
   	listeners.removePropertyChangeListener(listener);
   	return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener) {
   	if (listeners != null) {
   		listeners.removePropertyChangeListener(propertyName, listener);
   	}
   	return true;
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      withoutParentTasks(this.getParentTasks().toArray(new Task[this.getParentTasks().size()]));
      withoutSubTasks(this.getSubTasks().toArray(new Task[this.getSubTasks().size()]));
      firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! EntityUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         this.firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Task withName(String value)
   {
      setName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getName());
      return result.substring(1);
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
               firePropertyChange(PROPERTY_PARENTTASKS, null, item);
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
               firePropertyChange(PROPERTY_PARENTTASKS, item, null);
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
               firePropertyChange(PROPERTY_SUBTASKS, null, item);
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
               firePropertyChange(PROPERTY_SUBTASKS, item, null);
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
}
