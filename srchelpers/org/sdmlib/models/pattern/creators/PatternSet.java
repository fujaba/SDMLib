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
   
package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.booleanSet;
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.pattern.creators.PatternSet;
import org.sdmlib.models.pattern.creators.ReachabilityGraphSet;
import org.sdmlib.models.pattern.ReachabilityGraph;
import java.util.List;

public class PatternSet extends LinkedHashSet<Pattern>
{
   public PatternElementSet getElements()
   {
      PatternElementSet result = new PatternElementSet();
      
      for (Pattern obj : this)
      {
         result.addAll(obj.getElements());
      }
      
      return result;
   }
   public booleanSet getHasMatch()
   {
      booleanSet result = new booleanSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public PatternSet withHasMatch(boolean value)
   {
      for (Pattern obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public PatternSet withCurrentNAC(NegativeApplicationCondition value)
   {
      for (Pattern obj : this)
      {
         obj.withCurrentSubPattern(value);
      }
      
      return this;
   }

   public PatternSet withElements(PatternElement value)
   {
      for (Pattern obj : this)
      {
         obj.withElements(value);
      }
      
      return this;
   }

   public PatternSet withoutElements(PatternElement value)
   {
      for (Pattern obj : this)
      {
         obj.withoutElements(value);
      }
      
      return this;
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public PatternSet withModifier(String value)
   {
      for (Pattern obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public PatternSet withDoAllMatches(boolean value)
   {
      for (Pattern obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public PatternSet withPatternObjectName(String value)
   {
      for (Pattern obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }

   public PatternSet getCurrentSubPattern()
   {
      PatternSet result = new PatternSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getCurrentSubPattern());
      }
      
      return result;
   }

   public PatternSet withCurrentSubPattern(Pattern value)
   {
      for (Pattern obj : this)
      {
         obj.withCurrentSubPattern(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (Pattern elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.Pattern";
   }


   public PatternSet with(Pattern value)
   {
      this.add(value);
      return this;
   }
   
   public PatternSet without(Pattern value)
   {
      this.remove(value);
      return this;
   }
   public intList getDebugMode()
   {
      intList result = new intList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getDebugMode());
      }
      
      return result;
   }

   public PatternSet withDebugMode(int value)
   {
      for (Pattern obj : this)
      {
         obj.setDebugMode(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public PatternSet withPattern(Pattern value)
   {
      for (Pattern obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public StringBuilderSet getTrace()
   {
      StringBuilderSet result = new StringBuilderSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getTrace());
      }
      
      return result;
   }

   public PatternSet withTrace(StringBuilder value)
   {
      for (Pattern obj : this)
      {
         obj.setTrace(value);
      }
      
      return this;
   }

   public ReachabilityGraphSet getRgraph()
   {
      ReachabilityGraphSet result = new ReachabilityGraphSet();
      
      for (Pattern obj : this)
      {
         result.add(obj.getRgraph());
      }
      
      return result;
   }

   public PatternSet withRgraph(ReachabilityGraph value)
   {
      for (Pattern obj : this)
      {
         obj.withRgraph(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Pattern obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public PatternSet withName(String value)
   {
      for (Pattern obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }



   public PatternPO startModelPattern()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      PatternPO patternObject = pattern.hasElementPatternPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}














