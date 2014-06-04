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

package org.sdmlib.models.classes;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.models.classes.util.ParameterSet;

public class Parameter extends Value implements PropertyChangeInterface
{
   public static final String PROPERTY_METHOD = "method";
   private Method method = null;
   
   public Parameter()
   {
      
   }
   
   public Parameter(DataType type)
   {
      this.type = type;
   }

   public Method getMethod()
   {
      return this.method;
   }

   public boolean setMethod(Method value)
   {
      boolean changed = false;

      if (this.method != value)
      {
         Method oldValue = this.method;

         if (this.method != null)
         {
            this.method = null;
            oldValue.withoutParameter(this);
         }

         this.method = value;

         if (value != null)
         {
            value.withParameter(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_METHOD, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Parameter withClazz(Method value)
   {
      setMethod(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getInitialization());
      _.append(" ").append(this.getName());
      return _.substring(1);
   }
   
   //==========================================================================
   @Override
   public void removeYou()
   {
      super.removeYou();
      setMethod(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }
   
   // OVERRIDE
   @Override
   public Parameter withName(String string)
   {
      setName(string);
      return this;
   }

   
   @Override
   public Parameter withType(DataType value)
   {
      setType(value);
      return this;
   } 
   
   @Override
   public Parameter withInitialization(String value){
      setInitialization(value);
      return this;
   }

   public Parameter withMethod(Method value)
   {
      setMethod(value);
      return this;
   }


   
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

   public Method createMethod()
   {
      Method value = new Method();
      withMethod(value);
      return value;
   } 

   
   public static final ParameterSet EMPTY_SET = new ParameterSet();
}
