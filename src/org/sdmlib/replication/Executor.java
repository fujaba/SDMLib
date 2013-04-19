/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.replication;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class Executor implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_STEP.equalsIgnoreCase(attrName))
      {
         return getStep();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_STEP.equalsIgnoreCase(attrName))
      {
         setStep((Step) value);
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
      setStep(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Executor ----------------------------------- Step
    *              executor                   step
    * </pre>
    */
   
   public static final String PROPERTY_STEP = "step";
   
   private Step step = null;
   
   public Step getStep()
   {
      return this.step;
   }
   
   public boolean setStep(Step value)
   {
      boolean changed = false;
      
      if (this.step != value)
      {
         Step oldValue = this.step;
         
         if (this.step != null)
         {
            this.step = null;
            oldValue.setExecutor(null);
         }
         
         this.step = value;
         
         if (value != null)
         {
            value.withExecutor(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STEP, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Executor withStep(Step value)
   {
      setStep(value);
      return this;
   } 
   
   public Step createStep()
   {
      Step value = new Step();
      withStep(value);
      return value;
   } 
}

