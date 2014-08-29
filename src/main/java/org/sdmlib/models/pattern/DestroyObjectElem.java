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

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;

public class DestroyObjectElem extends PatternElement implements PropertyChangeInterface
{
   
   
   //==========================================================================
   
   @Override
   public boolean findNextMatch()
   {
      if (this.getHasMatch())
      {
         // backtrack
         setHasMatch(false);
         
         return false;
      }
      else 
      {
         // forward
         if (this.getPattern().getHasMatch())
         {
            setHasMatch(true);
            
            Object currentMatch = this.getPatternObject().getCurrentMatch();
            
            EntityFactory creatorClass = (EntityFactory) this.getPattern().getJsonIdMap().getCreatorClass(currentMatch);
            
            creatorClass.removeObject(currentMatch);
            
            if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
            {
               getTopPattern().addLogMsg(getPatternObject().getPatternObjectName() + ".removeYou(); // kill: " + dumpHostGraphObject(currentMatch));
            }
            
            return true;
         }
         else 
         {
            return false;
         }
      }
      
   }


   @Override
   public void resetSearch()
   {
      setHasMatch(false);
   }
   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      setPatternObject(null);
      setPattern(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   /********************************************************************
    * <pre>
    *              one                       one
    * DestroyObjectElem ----------------------------------- PatternObject
    *              destroyElem                   patternObject
    * </pre>
    */
   
   public static final String PROPERTY_PATTERNOBJECT = "patternObject";
   
   private PatternObject patternObject = null;
   
   public PatternObject getPatternObject()
   {
      return this.patternObject;
   }
   
   public boolean setPatternObject(PatternObject value)
   {
      boolean changed = false;
      
      if (this.patternObject != value)
      {
         PatternObject oldValue = this.patternObject;
         
         if (this.patternObject != null)
         {
            this.patternObject = null;
            oldValue.setDestroyElem(null);
         }
         
         this.patternObject = value;
         
         if (value != null)
         {
            value.withDestroyElem(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PATTERNOBJECT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public DestroyObjectElem withPatternObject(PatternObject value)
   {
      setPatternObject(value);
      return this;
   } 
   
   public PatternObject createPatternObject()
   {
      PatternObject value = new PatternObject();
      withPatternObject(value);
      return value;
   } 

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      return s.substring(1);
   }
   
}

