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
   
package org.sdmlib.model.classes.test;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

public class Uncle implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_BROTHER.equalsIgnoreCase(attrName))
      {
         return getBrother();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_BROTHER.equalsIgnoreCase(attrName))
      {
         setBrother((Parent) value);
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
      setBrother(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * Uncle ----------------------------------- Parent
    *              uncle                   brother
    * </pre>
    */
   
   public static final String PROPERTY_BROTHER = "brother";
   
   private Parent brother = null;
   
   public Parent getBrother()
   {
      return this.brother;
   }
   
   public boolean setBrother(Parent value)
   {
      boolean changed = false;
      
      if (this.brother != value)
      {
         Parent oldValue = this.brother;
         
         if (this.brother != null)
         {
            this.brother = null;
            oldValue.setUncle(null);
         }
         
         this.brother = value;
         
         if (value != null)
         {
            value.withUncle(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_BROTHER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Uncle withBrother(Parent value)
   {
      setBrother(value);
      return this;
   } 
   
   public Parent createBrother()
   {
      Parent value = new Parent();
      withBrother(value);
      return value;
   } 
}

