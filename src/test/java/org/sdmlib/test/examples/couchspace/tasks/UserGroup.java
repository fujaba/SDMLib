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
import org.sdmlib.test.examples.couchspace.tasks.util.UserSet;
import org.sdmlib.test.examples.couchspace.tasks.User;
   /**
    * 
    * @see <a href='../../../../../../../../../src/test/java/org/sdmlib/test/examples/modelcouch/ModelCouchTasksModel.java'>ModelCouchTasksModel.java</a>
 */
   public  class UserGroup implements SendableEntity
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
   
      withoutResponsible(this.getResponsible().toArray(new Task[this.getResponsible().size()]));
      withoutMembers(this.getMembers().toArray(new User[this.getMembers().size()]));
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
   
   public UserGroup withName(String value)
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
    * UserGroup ----------------------------------- Task
    *              responsibles                   responsible
    * </pre>
    */
   
   public static final String PROPERTY_RESPONSIBLE = "responsible";

   private TaskSet responsible = null;
   
   public TaskSet getResponsible()
   {
      if (this.responsible == null)
      {
         return TaskSet.EMPTY_SET;
      }
   
      return this.responsible;
   }

   public UserGroup withResponsible(Task... value)
   {
      if(value==null){
         return this;
      }
      for (Task item : value)
      {
         if (item != null)
         {
            if (this.responsible == null)
            {
               this.responsible = new TaskSet();
            }
            
            boolean changed = this.responsible.add (item);

            if (changed)
            {
               item.withResponsibles(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_RESPONSIBLE, null, item);
            }
         }
      }
      return this;
   } 

   public UserGroup withoutResponsible(Task... value)
   {
      for (Task item : value)
      {
         if ((this.responsible != null) && (item != null))
         {
            if (this.responsible.remove(item))
            {
               item.withoutResponsibles(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_RESPONSIBLE, item, null);
            }
         }
      }
      return this;
   }

   public Task createResponsible()
   {
      Task value = new Task();
      withResponsible(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * UserGroup ----------------------------------- User
    *              groups                   members
    * </pre>
    */
   
   public static final String PROPERTY_MEMBERS = "members";

   private UserSet members = null;
   
   public UserSet getMembers()
   {
      if (this.members == null)
      {
         return UserSet.EMPTY_SET;
      }
   
      return this.members;
   }

   public UserGroup withMembers(User... value)
   {
      if(value==null){
         return this;
      }
      for (User item : value)
      {
         if (item != null)
         {
            if (this.members == null)
            {
               this.members = new UserSet();
            }
            
            boolean changed = this.members.add (item);

            if (changed)
            {
               item.withGroups(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MEMBERS, null, item);
            }
         }
      }
      return this;
   } 

   public UserGroup withoutMembers(User... value)
   {
      for (User item : value)
      {
         if ((this.members != null) && (item != null))
         {
            if (this.members.remove(item))
            {
               item.withoutGroups(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MEMBERS, item, null);
            }
         }
      }
      return this;
   }

   public User createMembers()
   {
      User value = new User();
      withMembers(value);
      return value;
   } 
}
