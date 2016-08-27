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
import org.sdmlib.models.pattern.Pattern;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
*/
   public class GenericConstraint extends PatternElement<GenericConstraint> implements PropertyChangeInterface
{
   @Override
   public boolean findNextMatch()
   {
      if ( ! this.getPattern().getHasMatch())
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
            getTopPattern().addLogMsg("// match is isomorphic");
         }

         return ok;
      }
   }

   Condition<Object> condition;
   
   //==========================================================================

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

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener) {
      getPropertyChangeSupport().removePropertyChangeListener(listener);
      return true;
   }


   //==========================================================================

   @Override
   public void removeYou()
   {      
      super.removeYou();

      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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




   public GenericConstraint withCondition(Condition<Object> newCondition)
   {
      this.condition = newCondition;
      return this;
   }

   private PatternObject src = null;
   
   public PatternObject getSrc()
   {
      return this.src;
   }
    
   public GenericConstraint withSrc(PatternObject value)
   {
	   if(value != this.src) {
		   this.src = value;
	   }
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

