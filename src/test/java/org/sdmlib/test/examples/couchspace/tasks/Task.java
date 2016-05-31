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
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskSet;
import org.sdmlib.test.examples.couchspace.tasks.util.UserGroupSet;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;
import org.sdmlib.test.examples.couchspace.tasks.User;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelcouch/ModelCouchTasksModel.java'>ModelCouchTasksModel.java</a>
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
   
      setTaskFlow(null);
      setTaskFlowFirst(null);
      setTransitionSource(null);
      withoutResponsibles(this.getResponsibles().toArray(new UserGroup[this.getResponsibles().size()]));
      setHandledBy(null);
      withoutTransitionTargets(this.getTransitionTargets().toArray(new Task[this.getTransitionTargets().size()]));
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
   
   public Task withTitle(String value)
   {
      setTitle(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getTitle());
      result.append(" ").append(this.getTransitionCondition());
      result.append(" ").append(this.getCopySdmLibId());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_TRANSITIONCONDITION = "transitionCondition";
   
   private String transitionCondition;

   public String getTransitionCondition()
   {
      return this.transitionCondition;
   }
   
   public void setTransitionCondition(String value)
   {
      if ( ! StrUtil.stringEquals(this.transitionCondition, value)) {
      
         String oldValue = this.transitionCondition;
         this.transitionCondition = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TRANSITIONCONDITION, oldValue, value);
      }
   }
   
   public Task withTransitionCondition(String value)
   {
      setTransitionCondition(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_COPYSDMLIBID = "copySdmLibId";
   
   private String copySdmLibId;

   public String getCopySdmLibId()
   {
      return this.copySdmLibId;
   }
   
   public void setCopySdmLibId(String value)
   {
      if ( ! StrUtil.stringEquals(this.copySdmLibId, value)) {
      
         String oldValue = this.copySdmLibId;
         this.copySdmLibId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_COPYSDMLIBID, oldValue, value);
      }
   }
   
   public Task withCopySdmLibId(String value)
   {
      setCopySdmLibId(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Task ----------------------------------- TaskFlow
    *              tasks                   taskFlow
    * </pre>
    */
   
   public static final String PROPERTY_TASKFLOW = "taskFlow";

   private TaskFlow taskFlow = null;

   public TaskFlow getTaskFlow()
   {
      return this.taskFlow;
   }

   public boolean setTaskFlow(TaskFlow value)
   {
      boolean changed = false;
      
      if (this.taskFlow != value)
      {
         TaskFlow oldValue = this.taskFlow;
         
         if (this.taskFlow != null)
         {
            this.taskFlow = null;
            oldValue.withoutTasks(this);
         }
         
         this.taskFlow = value;
         
         if (value != null)
         {
            value.withTasks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKFLOW, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Task withTaskFlow(TaskFlow value)
   {
      setTaskFlow(value);
      return this;
   } 

   public TaskFlow createTaskFlow()
   {
      TaskFlow value = new TaskFlow();
      withTaskFlow(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Task ----------------------------------- TaskFlow
    *              firstTasks                   taskFlowFirst
    * </pre>
    */
   
   public static final String PROPERTY_TASKFLOWFIRST = "taskFlowFirst";

   private TaskFlow taskFlowFirst = null;

   public TaskFlow getTaskFlowFirst()
   {
      return this.taskFlowFirst;
   }

   public boolean setTaskFlowFirst(TaskFlow value)
   {
      boolean changed = false;
      
      if (this.taskFlowFirst != value)
      {
         TaskFlow oldValue = this.taskFlowFirst;
         
         if (this.taskFlowFirst != null)
         {
            this.taskFlowFirst = null;
            oldValue.withoutFirstTasks(this);
         }
         
         this.taskFlowFirst = value;
         
         if (value != null)
         {
            value.withFirstTasks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKFLOWFIRST, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Task withTaskFlowFirst(TaskFlow value)
   {
      setTaskFlowFirst(value);
      return this;
   } 

   public TaskFlow createTaskFlowFirst()
   {
      TaskFlow value = new TaskFlow();
      withTaskFlowFirst(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Task ----------------------------------- UserGroup
    *              responsible                   responsibles
    * </pre>
    */
   
   public static final String PROPERTY_RESPONSIBLES = "responsibles";

   private UserGroupSet responsibles = null;
   
   public UserGroupSet getResponsibles()
   {
      if (this.responsibles == null)
      {
         return UserGroupSet.EMPTY_SET;
      }
   
      return this.responsibles;
   }

   public Task withResponsibles(UserGroup... value)
   {
      if(value==null){
         return this;
      }
      for (UserGroup item : value)
      {
         if (item != null)
         {
            if (this.responsibles == null)
            {
               this.responsibles = new UserGroupSet();
            }
            
            boolean changed = this.responsibles.add (item);

            if (changed)
            {
               item.withResponsible(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_RESPONSIBLES, null, item);
            }
         }
      }
      return this;
   } 

   public Task withoutResponsibles(UserGroup... value)
   {
      for (UserGroup item : value)
      {
         if ((this.responsibles != null) && (item != null))
         {
            if (this.responsibles.remove(item))
            {
               item.withoutResponsible(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_RESPONSIBLES, item, null);
            }
         }
      }
      return this;
   }

   public UserGroup createResponsibles()
   {
      UserGroup value = new UserGroup();
      withResponsibles(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Task ----------------------------------- User
    *              handledTasks                   handledBy
    * </pre>
    */
   
   public static final String PROPERTY_HANDLEDBY = "handledBy";

   private User handledBy = null;

   public User getHandledBy()
   {
      return this.handledBy;
   }

   public boolean setHandledBy(User value)
   {
      boolean changed = false;
      
      if (this.handledBy != value)
      {
         User oldValue = this.handledBy;
         
         if (this.handledBy != null)
         {
            this.handledBy = null;
            oldValue.withoutHandledTasks(this);
         }
         
         this.handledBy = value;
         
         if (value != null)
         {
            value.withHandledTasks(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HANDLEDBY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Task withHandledBy(User value)
   {
      setHandledBy(value);
      return this;
   } 

   public User createHandledBy()
   {
      User value = new User();
      withHandledBy(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Task ----------------------------------- Task
    *              transitionTargets                   transitionSource
    * </pre>
    */
   
   public static final String PROPERTY_TRANSITIONSOURCE = "transitionSource";

   private Task transitionSource = null;

   public Task getTransitionSource()
   {
      return this.transitionSource;
   }
   public TaskSet getTransitionSourceTransitive()
   {
      TaskSet result = new TaskSet().with(this);
      return result.getTransitionSourceTransitive();
   }


   public boolean setTransitionSource(Task value)
   {
      boolean changed = false;
      
      if (this.transitionSource != value)
      {
         Task oldValue = this.transitionSource;
         
         if (this.transitionSource != null)
         {
            this.transitionSource = null;
            oldValue.withoutTransitionTargets(this);
         }
         
         this.transitionSource = value;
         
         if (value != null)
         {
            value.withTransitionTargets(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TRANSITIONSOURCE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Task withTransitionSource(Task value)
   {
      setTransitionSource(value);
      return this;
   } 

   public Task createTransitionSource()
   {
      Task value = new Task();
      withTransitionSource(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Task ----------------------------------- Task
    *              transitionSource                   transitionTargets
    * </pre>
    */
   
   public static final String PROPERTY_TRANSITIONTARGETS = "transitionTargets";

   private TaskSet transitionTargets = null;
   
   public TaskSet getTransitionTargets()
   {
      if (this.transitionTargets == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.transitionTargets;
   }
   public TaskSet getTransitionTargetsTransitive()
   {
      TaskSet result = new TaskSet().with(this);
      return result.getTransitionTargetsTransitive();
   }


   public Task withTransitionTargets(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.transitionTargets == null)
            {
               this.transitionTargets = new TaskSet();
            }
            
            boolean changed = this.transitionTargets.add (item);

            if (changed)
            {
               item.withTransitionSource(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TRANSITIONTARGETS, null, item);
            }
         }
      }
      return this;
   } 

   public Task withoutTransitionTargets(Task... value)
   {
      for (Task item : value)
      {
         if ((this.transitionTargets != null) && (item != null))
         {
            if (this.transitionTargets.remove(item))
            {
               item.setTransitionSource(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_TRANSITIONTARGETS, item, null);
            }
         }
      }
      return this;
   }

   public Task createTransitionTargets()
   {
      Task value = new Task();
      withTransitionTargets(value);
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
