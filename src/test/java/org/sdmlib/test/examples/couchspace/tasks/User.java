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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskSet;
import org.sdmlib.test.examples.couchspace.tasks.util.UserGroupSet;

import de.uniks.networkparser.interfaces.SendableEntity;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelcouch/ModelCouchTasksModel.java'>ModelCouchTasksModel.java</a>
 * @see org.sdmlib.test.examples.modelcouch.ModelCouchTasksModel#couchSpaceTasksModel
 */
   public  class User implements SendableEntity
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
   
      withoutHandledTasks(this.getHandledTasks().toArray(new Task[this.getHandledTasks().size()]));
      withoutGroups(this.getGroups().toArray(new UserGroup[this.getGroups().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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
      if ( ! StrUtil.stringEquals(this.name, value)) {
      
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public User withName(String value)
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
    *              one                       many
    * User ----------------------------------- Task
    *              handledBy                   handledTasks
    * </pre>
    */
   
   public static final String PROPERTY_HANDLEDTASKS = "handledTasks";

   private TaskSet handledTasks = null;
   
   public TaskSet getHandledTasks()
   {
      if (this.handledTasks == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.handledTasks;
   }

   public User withHandledTasks(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.handledTasks == null)
            {
               this.handledTasks = new TaskSet();
            }
            
            boolean changed = this.handledTasks.add (item);

            if (changed)
            {
               item.withHandledBy(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_HANDLEDTASKS, null, item);
            }
         }
      }
      return this;
   } 

   public User withoutHandledTasks(Task... value)
   {
      for (Task item : value)
      {
         if ((this.handledTasks != null) && (item != null))
         {
            if (this.handledTasks.remove(item))
            {
               item.setHandledBy(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_HANDLEDTASKS, item, null);
            }
         }
      }
      return this;
   }

   public Task createHandledTasks()
   {
      Task value = new Task();
      withHandledTasks(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * User ----------------------------------- UserGroup
    *              members                   groups
    * </pre>
    */
   
   public static final String PROPERTY_GROUPS = "groups";

   private UserGroupSet groups = null;
   
   public UserGroupSet getGroups()
   {
      if (this.groups == null)
      {
         return UserGroupSet.EMPTY_SET;
      }
   
      return this.groups;
   }

   public User withGroups(UserGroup... value)
   {
      if(value==null){
         return this;
      }
      for (UserGroup item : value)
      {
         if (item != null)
         {
            if (this.groups == null)
            {
               this.groups = new UserGroupSet();
            }
            
            boolean changed = this.groups.add (item);

            if (changed)
            {
               item.withMembers(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_GROUPS, null, item);
            }
         }
      }
      return this;
   } 

   public User withoutGroups(UserGroup... value)
   {
      for (UserGroup item : value)
      {
         if ((this.groups != null) && (item != null))
         {
            if (this.groups.remove(item))
            {
               item.withoutMembers(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_GROUPS, item, null);
            }
         }
      }
      return this;
   }

   public UserGroup createGroups()
   {
      UserGroup value = new UserGroup();
      withGroups(value);
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
