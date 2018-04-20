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

import java.util.LinkedHashSet;

import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.models.pattern.Pattern;


public class MatchIsomorphicConstraint extends PatternElement implements PropertyChangeInterface
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
         LinkedHashSet<Object> usedObjects = new LinkedHashSet<Object>();

         for (PatternElement patElem : this.getPattern().getElements())
         {
            if (patElem instanceof PatternObject)
            {
               Object currentMatch = ((PatternObject) patElem).getCurrentMatch();
               if (currentMatch == null)
                  continue;
               
               if (usedObjects.contains(currentMatch))
               {
                  if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
                  {
                     getTopPattern().addLogMsg("// match is NOT isomorphic. "
                        + dumpHostGraphObject(currentMatch) + " has been matched twice (or more). Backtrack! ");
                  }
                  
                  return false;
               }
               else
               {
                  usedObjects.add(currentMatch);
               }
            }
         }
         
         // no double match, match is isomorphic
         this.setHasMatch(true);
         
         if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
         {
            getTopPattern().addLogMsg("// match is isomorphic");
         }
         
         return true;
      }
   }
   
   

   
   //==========================================================================
   
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

      setPattern(null);
      firePropertyChange("REMOVE_YOU", this, null);
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

