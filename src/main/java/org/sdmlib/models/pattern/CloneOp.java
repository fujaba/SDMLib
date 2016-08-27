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

import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.Pattern;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
*/
   public class CloneOp extends PatternElement implements PropertyChangeInterface
{
   private IdMap origMap;

   private IdMap cloneMap;

   private PatternObject firstPO;
   
   private Object origGraph;

   private Object cloneGraph;

   //==========================================================================
   @Override
   public boolean findNextMatch()
   {
      if (this.getPattern().getHasMatch())
      {
         if (!getHasMatch())
         {
            this.setHasMatch(true);
            
            origMap = this.getPattern().getIdMap();
            origMap = (IdMap) new SDMLibIdMap("om").with(origMap);
            cloneMap = (IdMap) new SDMLibIdMap("cm").with(origMap);
            
            firstPO = (PatternObject) this.getPattern().getElements().first();
            
            origGraph = firstPO.getCurrentMatch();
            
            JsonArray jsonArray = origMap.toJsonArray(origGraph);
            
            cloneGraph = cloneMap.decode(jsonArray);
            
            // change matches to point to the new nodes
            for (PatternElement pe : this.getPattern().getElements())
            {
               if (pe instanceof PatternObject)
               {
                  PatternObject po = (PatternObject) pe;
                  if (po.getCurrentMatch() != null)
                  {
                     String id = origMap.getId(po.getCurrentMatch());
                     Object cloneObj = cloneMap.getObject(id);
                     po.setCurrentMatch(cloneObj);
                  }
               }
               else if (pe instanceof UnifyGraphsOp)
               {
                  break;
               }
            }
            
            // go on
            return true;
         }
         else
         {
            this.setHasMatch(false);

            // backtracking, restore previous matches of all patternobjects
            for (PatternElement pe : this.getPattern().getElements())
            {
               if (pe instanceof PatternObject)
               {
                  PatternObject po = (PatternObject) pe;
                  if (po.getCurrentMatch() != null)
                  {
                     String id = cloneMap.getId(po.getCurrentMatch());
                     Object cloneObj = origMap.getObject(id);
                     po.setCurrentMatch(cloneObj);
                  }
               }
               else if (pe instanceof UnifyGraphsOp)
               {
                  break;
               }
            }
            
            return false;
         }
      }
      else
      {
         return false;
      }
   }

   @Override
   public void resetSearch()
   {
      this.setHasMatch(false);
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
      
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      return s.substring(1);
   }

}

