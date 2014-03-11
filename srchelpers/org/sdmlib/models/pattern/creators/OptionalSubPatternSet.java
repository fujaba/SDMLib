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

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.pattern.OptionalSubPattern;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.creators.PatternElementSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.pattern.creators.PatternSet;
import org.sdmlib.models.pattern.creators.ReachabilityGraphSet;

public class OptionalSubPatternSet extends LinkedHashSet<OptionalSubPattern>
{
   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public OptionalSubPatternSet withModifier(String value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public OptionalSubPatternSet withHasMatch(boolean value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public OptionalSubPatternSet withPatternObjectName(String value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public OptionalSubPatternSet withDoAllMatches(boolean value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public booleanList getMatchForward()
   {
      booleanList result = new booleanList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getMatchForward());
      }
      
      return result;
   }

   public OptionalSubPatternSet withMatchForward(boolean value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withMatchForward(value);
      }
      
      return this;
   }

   public PatternSet getCurrentSubPattern()
   {
      PatternSet result = new PatternSet();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getCurrentSubPattern());
      }
      
      return result;
   }

   public OptionalSubPatternSet withCurrentSubPattern(Pattern value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withCurrentSubPattern(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (OptionalSubPattern elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.OptionalSubPattern";
   }

   public intList getDebugMode()
   {
      intList result = new intList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getDebugMode());
      }
      
      return result;
   }

   public OptionalSubPatternSet withDebugMode(int value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.setDebugMode(value);
      }
      
      return this;
   }

   public PatternElementSet getElements()
   {
      PatternElementSet result = new PatternElementSet();
      
      for (OptionalSubPattern obj : this)
      {
         result.addAll(obj.getElements());
      }
      
      return result;
   }

   public OptionalSubPatternSet withElements(PatternElement value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withElements(value);
      }
      
      return this;
   }

   public OptionalSubPatternSet withoutElements(PatternElement value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withoutElements(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public OptionalSubPatternSet withPattern(Pattern value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   public StringBuilderSet getTrace()
   {
      StringBuilderSet result = new StringBuilderSet();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getTrace());
      }
      
      return result;
   }

   public OptionalSubPatternSet withTrace(StringBuilder value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.setTrace(value);
      }
      
      return this;
   }

   public ReachabilityGraphSet getRgraph()
   {
      ReachabilityGraphSet result = new ReachabilityGraphSet();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getRgraph());
      }
      
      return result;
   }

   public OptionalSubPatternSet withRgraph(ReachabilityGraph value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.withRgraph(value);
      }
      
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (OptionalSubPattern obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public OptionalSubPatternSet withName(String value)
   {
      for (OptionalSubPattern obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }



   public OptionalSubPatternPO startModelPattern()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      OptionalSubPatternPO patternObject = pattern.hasElementOptionalSubPatternPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public OptionalSubPatternSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<OptionalSubPattern>)value);
      }
      else if (value != null)
      {
         this.add((OptionalSubPattern) value);
      }
      
      return this;
   }
   
   public OptionalSubPatternSet without(OptionalSubPattern value)
   {
      this.remove(value);
      return this;
   }



   public OptionalSubPatternPO hasOptionalSubPatternPO()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      OptionalSubPatternPO patternObject = pattern.hasElementOptionalSubPatternPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}













