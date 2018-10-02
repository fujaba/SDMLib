/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.modelspace;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.modelspace.util.TaskSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;


   public  class TaskLane implements PropertyChangeInterface, SendableEntity
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
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   //==========================================================================
   
   
   public void removeYou()
   {
   
      setBoard(null);
      withoutTasks(this.getTasks().toArray(new Task[this.getTasks().size()]));
      withoutMyRequests(this.getMyRequests().toArray(new Task[this.getMyRequests().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_HOSTNAME = "hostName";
   
   private String hostName;

   public String getHostName()
   {
      return this.hostName;
   }
   
   public void setHostName(String value)
   {
      if ( ! StrUtil.stringEquals(this.hostName, value)) {
      
         String oldValue = this.hostName;
         this.hostName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTNAME, oldValue, value);
      }
   }
   
   public TaskLane withHostName(String value)
   {
      setHostName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getHostName());
      result.append(" ").append(this.getPortNo());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_PORTNO = "portNo";
   
   private long portNo;

   public long getPortNo()
   {
      return this.portNo;
   }
   
   public void setPortNo(long value)
   {
      if (this.portNo != value) {
      
         long oldValue = this.portNo;
         this.portNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PORTNO, oldValue, value);
      }
   }
   
   public TaskLane withPortNo(long value)
   {
      setPortNo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * TaskLane ----------------------------------- TaskBoard
    *              lanes                   board
    * </pre>
    */
   
   public static final String PROPERTY_BOARD = "board";

   private TaskBoard board = null;

   public TaskBoard getBoard()
   {
      return this.board;
   }

   public boolean setBoard(TaskBoard value)
   {
      boolean changed = false;
      
      if (this.board != value)
      {
         TaskBoard oldValue = this.board;
         
         if (this.board != null)
         {
            this.board = null;
            oldValue.withoutLanes(this);
         }
         
         this.board = value;
         
         if (value != null)
         {
            value.withLanes(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BOARD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public TaskLane withBoard(TaskBoard value)
   {
      setBoard(value);
      return this;
   } 

   public TaskBoard createBoard()
   {
      TaskBoard value = new TaskBoard();
      withBoard(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * TaskLane ----------------------------------- Task
    *              lane                   tasks
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

   public TaskLane withTasks(Task... value)
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
               item.withLane(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKS, null, item);
            }
         }
      }
      return this;
   } 

   public TaskLane withoutTasks(Task... value)
   {
      for (Task item : value)
      {
         if ((this.tasks != null) && (item != null))
         {
            if (this.tasks.remove(item))
            {
               item.setLane(null);
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
    * TaskLane ----------------------------------- Task
    *              fileTargetCloud                   myRequests
    * </pre>
    */
   
   public static final String PROPERTY_MYREQUESTS = "myRequests";

   private TaskSet myRequests = null;
   
   public TaskSet getMyRequests()
   {
      if (this.myRequests == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.myRequests;
   }

   public TaskLane withMyRequests(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.myRequests == null)
            {
               this.myRequests = new TaskSet();
            }
            
            boolean changed = this.myRequests.add (item);

            if (changed)
            {
               item.withFileTargetCloud(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MYREQUESTS, null, item);
            }
         }
      }
      return this;
   } 

   public TaskLane withoutMyRequests(Task... value)
   {
      for (Task item : value)
      {
         if ((this.myRequests != null) && (item != null))
         {
            if (this.myRequests.remove(item))
            {
               item.setFileTargetCloud(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MYREQUESTS, item, null);
            }
         }
      }
      return this;
   }

   public Task createMyRequests()
   {
      Task value = new Task();
      withMyRequests(value);
      return value;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
