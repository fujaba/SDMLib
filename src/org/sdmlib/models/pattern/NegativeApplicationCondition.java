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

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.StrUtil;

public class NegativeApplicationCondition extends Pattern implements PropertyChangeInterface
{
   public NegativeApplicationCondition()
   {
      super();
      setHasMatch(true);
   }
   
   //==========================================================================
   
   @Override
   public boolean findMatch()
   {
      // start matching only if nac is complete 
      if (this.getPattern().getCurrentSubPattern() != null)
      {
         return true;
      }
      else
      {
         return super.findMatch();
      }
   }


   @Override
   public boolean findNextMatch()
   {
      // start matching only if nac is complete 
      if (this.getPattern().getCurrentSubPattern() != null)
      {
         return true;
      }
      else if (getHasMatch())
      {
         // last time this NAC has found a match and thus it was violated and has caused backtracking
         // thus some earlier pattern elements have been rematched.
         // check the NAC again
         resetSearch();
         
         if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
         {
            getTopPattern().addLogMsg("// start NAC " + getPatternObjectName());
         }
         
         boolean nacHasMatch = findMatch();
         
         if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
         {
            if (nacHasMatch)
            {
               getTopPattern().addLogMsg("// NAC " + getPatternObjectName() + " has match, backtrack!");
            }
            else
            {
               getTopPattern().addLogMsg("// NAC " + getPatternObjectName() + " has NO match, that is good");
            }
         }
         
         return ! nacHasMatch;
      }
      else
      {
         // backtracking, the NAC has no alternative choice
         this.setHasMatch(true);
         return false;
      }
   }

   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      removeAllFromElements();
      setPattern(null);
      setRgraph(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getDebugMode());
      _.append(" ").append(this.getModifier());
      _.append(" ").append(this.getPatternObjectName());
      _.append(" ").append(this.getName());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_CURRENTSUBPATTERN = "currentSubPattern";
   
   private Pattern currentSubPattern;

   public Pattern getCurrentSubPattern()
   {
      return this.currentSubPattern;
   }
   
   public void setCurrentSubPattern(Pattern value)
   {
      if (this.currentSubPattern != value)
      {
         Pattern oldValue = this.currentSubPattern;
         this.currentSubPattern = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTSUBPATTERN, oldValue, value);
      }
   }
   
   public Pattern withCurrentSubPattern(Pattern value)
   {
      setCurrentSubPattern(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_DEBUGMODE = "debugMode";
   
   private int debugMode;

   public int getDebugMode()
   {
      return this.debugMode;
   }
   
   public void setDebugMode(int value)
   {
      if (this.debugMode != value)
      {
         int oldValue = this.debugMode;
         this.debugMode = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DEBUGMODE, oldValue, value);
      }
   }
   
   public Pattern withDebugMode(int value)
   {
      setDebugMode(value);
      return this;
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
   
   public Pattern withName(String value)
   {
      setName(value);
      return this;
   } 
 
}

