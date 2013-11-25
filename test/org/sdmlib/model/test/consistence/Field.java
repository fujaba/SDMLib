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
   
package org.sdmlib.model.test.consistence;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class Field implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_BORDER.equalsIgnoreCase(attrName))
      {
         return getBorder();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_BORDER.equalsIgnoreCase(attrName))
      {
         setBorder((Border) value);
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
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      setBorder(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Field ----------------------------------- Border
    *              field                   border
    * </pre>
    */
   
   public static final String PROPERTY_BORDER = "border";
   
   private Border border = null;
   
   public Border getBorder()
   {
      return this.border;
   }
   
   public boolean setBorder(Border value)
   {
      boolean changed = false;
      
      if (this.border != value)
      {
         Border oldValue = this.border;
         
         if (this.border != null)
         {
            this.border = null;
            oldValue.setField(null);
         }
         
         this.border = value;
         
         if (value != null)
         {
            value.withField(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BORDER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Field withBorder(Border value)
   {
      setBorder(value);
      return this;
   } 
   
   public Border createBorder()
   {
      Border value = new Border();
      withBorder(value);
      return value;
   } 
}

