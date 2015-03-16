/*
   Copyright (c) 2015 alex 
   
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
   
package org.sdmlib.examples.features.model.all;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Window implements PropertyChangeInterface
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
      setHouse(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              many                       one
    * Window ----------------------------------- House
    *              windows                   house
    * </pre>
    */
   
   public static final String PROPERTY_HOUSE = "house";

   private House house = null;

   public House getHouse()
   {
      return this.house;
   }

   public boolean setHouse(House value)
   {
      boolean changed = false;
      
      if (this.house != value)
      {
         House oldValue = this.house;
         
         if (this.house != null)
         {
            this.house = null;
            oldValue.withoutWindows(this);
         }
         
         this.house = value;
         
         if (value != null)
         {
            value.withWindows(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOUSE, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public Window withHouse(House value)
   {
      setHouse(value);
      return this;
   } 

   public House createHouse()
   {
      House value = new House();
      withHouse(value);
      return value;
   } 
}
