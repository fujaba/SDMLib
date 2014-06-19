/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.model.taskflows;

import org.sdmlib.model.taskflows.util.TaskFlowSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

import org.sdmlib.serialization.SDMLibJsonIdMap;

public class TaskFlow implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   
   public void removeYou()
   {
      setSubFlow(null);
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TASKNO = "taskNo";
   
   protected int taskNo;

   public int getTaskNo()
   {
      return this.taskNo;
   }
   
   public void setTaskNo(int value)
   {
      if (this.taskNo != value)
      {
         int oldValue = this.taskNo;
         this.taskNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TASKNO, oldValue, value);
      }
   }
   
   public TaskFlow withTaskNo(int value)
   {
      setTaskNo(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_IDMAP = "idMap";
   
   private SDMLibJsonIdMap idMap;

   public SDMLibJsonIdMap getIdMap()
   {
      return this.idMap;
   }
   
   public void setIdMap(SDMLibJsonIdMap value)
   {
      if (this.idMap != value)
      {
         SDMLibJsonIdMap oldValue = this.idMap;
         this.idMap = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IDMAP, oldValue, value);
      }
   }
   
   public TaskFlow withIdMap(SDMLibJsonIdMap value)
   {
      setIdMap(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * TaskFlow ----------------------------------- TaskFlow
    *              parent                   subFlow
    * </pre>
    */
   
   public static final String PROPERTY_SUBFLOW = "subFlow";

   private TaskFlow subFlow = null;

   public TaskFlow getSubFlow()
   {
      return this.subFlow;
   }
   public TaskFlowSet getSubFlowTransitive()
   {
      TaskFlowSet result = new TaskFlowSet().with(this);
      return result.getSubFlowTransitive();
   }


   public boolean setSubFlow(TaskFlow value)
   {
      boolean changed = false;
      
      if (this.subFlow != value)
      {
         TaskFlow oldValue = this.subFlow;
         
         if (this.subFlow != null)
         {
            this.subFlow = null;
            oldValue.setParent(null);
         }
         
         this.subFlow = value;
         
         if (value != null)
         {
            value.withParent(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SUBFLOW, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public TaskFlow withSubFlow(TaskFlow value)
   {
      setSubFlow(value);
      return this;
   } 

   public TaskFlow createSubFlow()
   {
      TaskFlow value = new TaskFlow();
      withSubFlow(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * TaskFlow ----------------------------------- TaskFlow
    *              subFlow                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";

   private TaskFlow parent = null;

   public TaskFlow getParent()
   {
      return this.parent;
   }
   public TaskFlowSet getParentTransitive()
   {
      TaskFlowSet result = new TaskFlowSet().with(this);
      return result.getParentTransitive();
   }


   public boolean setParent(TaskFlow value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         TaskFlow oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.setSubFlow(null);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withSubFlow(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public TaskFlow withParent(TaskFlow value)
   {
      setParent(value);
      return this;
   } 

   public TaskFlow createParent()
   {
      TaskFlow value = new TaskFlow();
      withParent(value);
      return value;
   } 
}
