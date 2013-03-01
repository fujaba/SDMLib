/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.chats;

import java.beans.PropertyChangeSupport;

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;

public abstract class CSClientTask extends TaskFlow implements PropertyChangeInterface
{
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         return getIdMap();
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         return getSubFlow();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((CSVisitAllClientsFlow) value);
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         setSubFlow((TaskFlow) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setParent(null);
      setSubFlow(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * CSClientTask ----------------------------------- CSVisitAllClientsFlow
    *              tgtTask                   parent
    * </pre>
    */
   
   public static final String PROPERTY_PARENT = "parent";
   
   private CSVisitAllClientsFlow parent = null;
   
   public CSVisitAllClientsFlow getParent()
   {
      return this.parent;
   }
   
   public boolean setParent(CSVisitAllClientsFlow value)
   {
      boolean changed = false;
      
      if (this.parent != value)
      {
         CSVisitAllClientsFlow oldValue = this.parent;
         
         if (this.parent != null)
         {
            this.parent = null;
            oldValue.setTgtTask(null);
         }
         
         this.parent = value;
         
         if (value != null)
         {
            value.withTgtTask(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public CSClientTask withParent(CSVisitAllClientsFlow value)
   {
      setParent(value);
      return this;
   } 
   
   public CSVisitAllClientsFlow createParent()
   {
      CSVisitAllClientsFlow value = new CSVisitAllClientsFlow();
      withParent(value);
      return value;
   }



   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }

}

