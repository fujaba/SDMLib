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

import java.beans.PropertyChangeListener;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;

import de.uniks.networkparser.interfaces.Condition;



public class GenericConstraint extends PatternElement<GenericConstraint>implements PropertyChangeInterface
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
         boolean ok = condition.update(getSrc().getCurrentMatch());

         this.setHasMatch(ok);

         if (ok && getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
         {
            if (this.getText() == null)
            {
               getTopPattern().addLogMsg("// match is isomorphic");
            }
            else
            {
               getTopPattern().addLogMsg("// " + this.getText() + " is OK ");
            }
         }

         return ok;
      }
   }


   // ==========================================================================

   @Override
   public void resetSearch()
   {
      setHasMatch(false);
   }


   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }


   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }


   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }


   // ==========================================================================

   @Override
   public void removeYou()
   {
      super.removeYou();

      setPattern(null);
      firePropertyChange("REMOVE_YOU", this, null);
   }


   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();

      s.append(" ").append(this.getText());
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      return s.substring(1);
   }

   public static final String PROPERTY_CONDITION = "condition";

   private Condition condition;


   public Condition getCondition()
   {
      return this.condition;
   }


   public void setCondition(Condition value)
   {
      if (this.condition != value)
      {

         Condition oldValue = this.condition;
         this.condition = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CONDITION, oldValue, value);
      }
   }


   public GenericConstraint withCondition(Condition value)
   {
      setCondition(value);
      return this;
   }

   /********************************************************************
    * <pre>
    *              many                       one
    * PatternLink ----------------------------------- PatternObject
    *              outgoing                   src
    * </pre>
    */

   public static final String PROPERTY_SRC = "src";

   private PatternObject src = null;


   public PatternObject getSrc()
   {
      return this.src;
   }


   public boolean setSrc(PatternObject value)
   {
      boolean changed = false;

      if (this.src != value)
      {
         PatternObject oldValue = this.src;

         this.src = value;

         getPropertyChangeSupport().firePropertyChange(PROPERTY_SRC, oldValue, value);
         changed = true;
      }

      return changed;
   }


   public GenericConstraint withSrc(PatternObject value)
   {
      setSrc(value);
      return this;
   }

   // ==========================================================================

   public static final String PROPERTY_TEXT = "text";

   private String text;


   public String getText()
   {
      return this.text;
   }


   public void setText(String value)
   {
      if (!StrUtil.stringEquals(this.text, value))
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
