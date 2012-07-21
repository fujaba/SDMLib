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
   
package org.sdmlib.models.pattern;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.models.pattern.creators.PatternElementSet;

public class PatternElement implements PropertyChangeInterface
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

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return getPattern();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         setPattern((Pattern) value);
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
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   public static final PatternElementSet EMPTY_SET = new PatternElementSet();

   
   /********************************************************************
    * <pre>
    *              many                       one
    * PatternElement ----------------------------------- Pattern
    *              elements                   pattern
    * </pre>
    */
   
   public static final String PROPERTY_PATTERN = "pattern";
   
   private Pattern pattern = null;
   
   public Pattern getPattern()
   {
      return this.pattern;
   }
   
   public boolean setPattern(Pattern value)
   {
      boolean changed = false;
      
      if (this.pattern != value)
      {
         Pattern oldValue = this.pattern;
         
         if (this.pattern != null)
         {
            this.pattern = null;
            oldValue.withoutElements(this);
         }
         
         this.pattern = value;
         
         if (value != null)
         {
            value.withElements(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PATTERN, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public PatternElement withPattern(Pattern value)
   {
      setPattern(value);
      return this;
   }


   public boolean findNextMatch()
   {
      // TODO Auto-generated method stub
      return false;
   } 
}

