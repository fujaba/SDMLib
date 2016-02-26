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
import org.sdmlib.models.pattern.ReachabilityGraph;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
*/
   public class OptionalSubPattern extends Pattern<OptionalSubPattern> implements PropertyChangeInterface
{
   public OptionalSubPattern()
   {
      this.setHasMatch(true);
   }
   
   @Override
   public boolean findMatch()
   {
      if ( ! this.getPattern().getHasMatch())
      {
         this.setHasMatch(false);
         
         return false;
      }
      
      return super.findMatch();
   }
   
   @Override
   public boolean findNextMatch()
   {
      
      if (matchForward)
      {
         // last time this subpattern has run backward thus we run forward now
         // thus some earlier pattern elements have been rematched.
         // check the subpattern / NAC again
         resetSearch();
         
         if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
         {
            getTopPattern().addLogMsg("// (re)startSubPattern " + getPatternObjectName() + ";");
         }
         
         boolean nacHasMatch = findMatch();
         
         if (getDoAllMatches())
         {
            while (getHasMatch())
            {
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  getTopPattern().addLogMsg("// " + getPatternObjectName() + " allMatches?");
               }
               
               findMatch();
            }
         }
         
         // next time backtrack
         setMatchForward(false);
         
         // optional sub pattern are always successful
         return true;
      }
      else
      {
         // backtracking, 
         setMatchForward(true);
         
         return false;
      }
   }

   @Override
   public boolean addToElements(PatternElement value)
   {
      // sub pattern is extended, thus do not backtrack but match forward
      this.setMatchForward(true);
      return super.addToElements(value);
   }
   
   //==========================================================================
   
   @Override
   public void removeYou()
   {
      super.removeYou();

      removeAllFromElements();
      setPattern(null);
      setRgraph(null);
      withoutElements(this.getElements().toArray(new PatternElement[this.getElements().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_MATCHFORWARD = "matchForward";
   
   private boolean matchForward = true;

   public boolean getMatchForward()
   {
      return this.matchForward;
   }
   
   public void setMatchForward(boolean value)
   {
      if (this.matchForward != value)
      {
         boolean oldValue = this.matchForward;
         this.matchForward = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MATCHFORWARD, oldValue, value);
      }
   }
   
   public OptionalSubPattern withMatchForward(boolean value)
   {
      setMatchForward(value);
      return this;
   } 
   
   @Override
   public void resetSearch()
   {
      setHasMatch(true);
      setMatchForward(true);
      super.resetSearch();
   }

   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getDebugMode());
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      s.append(" ").append(this.getName());
      return s.substring(1);
   }


   
   //==========================================================================
   
   public boolean isMatchForward()
   {
      return this.matchForward;
   }
   }

