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

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.models.pattern.util.PatternLinkSet;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.storyboards.Kanban;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import java.lang.Object;

/**
 * 
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 */
public class PatternPath extends PatternLink implements PropertyChangeInterface
{
   private PathExpression pathExpr = null;
   
   public void setPathExpr(PathExpression pathExpr)
   {
      this.pathExpr = pathExpr;
   }
   
   @Override
   public boolean findNextMatch()
   {
      if (this.getHostGraphSrcObject() == null)
      {
         // search forward
         this.setHostGraphSrcObject(this.getSrc().getCurrentMatch());
         
         if (getHostGraphSrcObject() != null)
         {
            Object value = null;
            
            if (getHostGraphSrcObject() != null && getHostGraphSrcObject() instanceof Collection)
            {
               // loop through src elements and collect target elements
               LinkedHashSet<Object> tgtSet = new LinkedHashSet<Object>();
               
               for (Object src : (Collection<?>) getHostGraphSrcObject())
               {
                  Collection<?> tgt = pathExpr.getNeighbors(src);
                  
                  tgtSet.addAll((Collection<?>) tgt); 
               }
               
               value = tgtSet;
            }
            else
            {
               value = pathExpr.getNeighbors(getHostGraphSrcObject());
            }
            
            if (value != null && value instanceof Collection)
            {
               this.getTgt().setCandidates(value);
               
               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  // add set of candidates to trace
                  String setVarName = this.getTgt().getPatternObjectName() + "Candidates";
                  LinkedHashSet<String> variablesAlreadyInTrace = getTopPattern().getVariablesAlreadyInTrace();
                  if ( ! variablesAlreadyInTrace.contains(setVarName))
                  {
                     variablesAlreadyInTrace.add(setVarName);
                     setVarName = value.getClass().getSimpleName() + " " + setVarName;
                  }
                  
                  getTopPattern().addLogMsg(setVarName + " = " + this.getSrc().getPatternObjectName()
                     + ".get path; // " + valueSetString(value));
                  if (((Collection<?>) value).isEmpty())
                  {
                     getTopPattern().addLogMsg("// No candidates, backtrack!");
                  }
               }
            }
            else
            {
               this.getTgt().setCandidates(value);

               if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
               {
                  String setVarName = this.getTgt().getPatternObjectName();
                  LinkedHashSet<String> variablesAlreadyInTrace = getTopPattern().getVariablesAlreadyInTrace();
                  if ( ! variablesAlreadyInTrace.contains(setVarName))
                  {
                     variablesAlreadyInTrace.add(setVarName);
                     setVarName = value.getClass().getSimpleName() + " " + setVarName;
                  }
                  
                  getTopPattern().addLogMsg(setVarName + " = " + this.getSrc().getPatternObjectName()
                     + ".get path ; // " + getTopPattern().getIdMap().getId(value) + " " + value);
               }
               

            }
            
            return true;
         }
         else 
         {
            return false;
         }
      }
      else
      {
         this.setHostGraphSrcObject(null);
      
         return false;
      }
   }
}

