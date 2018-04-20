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
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.ReachabilityGraph;

/**
 * 
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 * @see org.sdmlib.test.examples.SDMLib.PatternModelCodeGen#testPatternModelCodegen
 */
public class NegativeApplicationCondition extends Pattern implements PropertyChangeInterface
{
   public NegativeApplicationCondition()
   {
      super();
      setHasMatch(true);
   }


   // ==========================================================================

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

         return !nacHasMatch;
      }
      else
      {
         // backtracking, the NAC has no alternative choice
         this.setHasMatch(true);
         return false;
      }
   }


   // ==========================================================================

   @Override
   public void removeYou()
   {
      super.removeYou();

      removeAllFromElements();
      setPattern(null);
      withoutElements(this.getElements().toArray(new PatternElement[this.getElements().size()]));
      setCurrentSubPattern(null);
      setRgraph(null);
      firePropertyChange("REMOVE_YOU", this, null);
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

}
