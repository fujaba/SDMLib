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

package org.sdmlib.models.pattern;

import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.pattern.GenericConstraint.Condition;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.utils.PropertyChangeInterface;

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashSet;
import org.sdmlib.utils.StrUtil;

public class GenericConstraint extends PatternElement implements
      PropertyChangeInterface
{
   @Override
   public boolean findNextMatch()
   {
      if (!this.getPattern().getHasMatch())
      {
         return false;
      }

      if (this.getHasMatch())
      {
         // backtracking
         this.setHasMatch(false);
         return false;
      }
      else
      {
         // forward
         boolean ok = condition.check();

         this.setHasMatch(ok);

         if (ok && getTopPattern().getDebugMode() >= R.DEBUG_ON)
         {
            getTopPattern().addLogMsg("// match is isomorphic");
         }

         return ok;
      }
   }

   Condition condition;

   public static abstract class Condition
   {
      public abstract boolean check();
   }

   // ==========================================================================

   @Override
   public void resetSearch()
   {
      setHasMatch(false);
   }

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_MODIFIER.equalsIgnoreCase(attrName))
      {
         return getModifier();
      }

      if (PROPERTY_HASMATCH.equalsIgnoreCase(attrName))
      {
         return getHasMatch();
      }

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         return getPatternObjectName();
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         return getDoAllMatches();
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         return getPattern();
      }

      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return getText();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
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

      if (PROPERTY_PATTERNOBJECTNAME.equalsIgnoreCase(attrName))
      {
         setPatternObjectName((String) value);
         return true;
      }

      if (PROPERTY_DOALLMATCHES.equalsIgnoreCase(attrName))
      {
         setDoAllMatches((Boolean) value);
         return true;
      }

      if (PROPERTY_PATTERN.equalsIgnoreCase(attrName))
      {
         setPattern((Pattern) value);
         return true;
      }

      if (PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         setText((String) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }

   // ==========================================================================

   public void removeYou()
   {
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      _.append(" ").append(this.getText());
      return _.substring(1);
   }

   public GenericConstraint withCondition(Condition newCondition)
   {
      this.condition = newCondition;
      return this;
   }


   
   //==========================================================================
   
   public static final String PROPERTY_TEXT = "text";
   
   private String text;

   public String getText()
   {
      return this.text;
   }
   
   public void setText(String value)
   {
      if ( ! StrUtil.stringEquals(this.text, value))
      {
         String oldValue = this.text;
         this.text = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEXT, oldValue, value);
      }
   }
   
   public GenericConstraint withText(String value)
   {
      setText(value);
      return this;
   } 
}
