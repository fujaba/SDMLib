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
import org.sdmlib.utils.StrUtil;

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

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attribute))
      {
         return getModifier();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attribute))
      {
         return getHasMatch();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return getName();
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

      if (PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         setModifier((String) value);
         return true;
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         setHasMatch((Boolean) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
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
      return false;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_MODIFIER = "modifier";
   
   private String modifier;

   public String getModifier()
   {
      return this.modifier;
   }
   
   public void setModifier(String value)
   {
      if ( ! StrUtil.stringEquals(this.modifier, value))
      {
         String oldValue = this.modifier;
         this.modifier = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODIFIER, oldValue, value);
      }
   }
   
   public PatternElement withModifier(String value)
   {
      setModifier(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_HASMATCH = "hasMatch";
   
   private boolean hasMatch = false;

   public boolean getHasMatch()
   {
      return this.hasMatch;
   }
   
   public void setHasMatch(boolean value)
   {
      if (this.hasMatch != value)
      {
         boolean oldValue = this.hasMatch;
         this.hasMatch = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HASMATCH, oldValue, value);
      }
   }
   
   public PatternElement withHasMatch(boolean value)
   {
      setHasMatch(value);
      return this;
   }


   public void resetSearch()
   {
      // please override
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
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public PatternElement withName(String value)
   {
      setName(value);
      return this;
   } 
}

