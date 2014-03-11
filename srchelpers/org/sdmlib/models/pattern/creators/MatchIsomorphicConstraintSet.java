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
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.creators.PatternSet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class MatchIsomorphicConstraintSet extends LinkedHashSet<MatchIsomorphicConstraint>
{
   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withModifier(String value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withHasMatch(boolean value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withHasMatch(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withDoAllMatches(boolean value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withDoAllMatches(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withPatternObjectName(String value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withPatternObjectName(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (MatchIsomorphicConstraint elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.MatchIsomorphicConstraint";
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet withPattern(Pattern value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   public MatchIsomorphicConstraintPO startModelPattern()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      MatchIsomorphicConstraintPO patternObject = pattern.hasElementMatchIsomorphicConstraintPO();
      
      patternObject.withCandidates(this);
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public MatchIsomorphicConstraintSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MatchIsomorphicConstraint>)value);
      }
      else if (value != null)
      {
         this.add((MatchIsomorphicConstraint) value);
      }
      
      return this;
   }
   
   public MatchIsomorphicConstraintSet without(MatchIsomorphicConstraint value)
   {
      this.remove(value);
      return this;
   }



   public MatchIsomorphicConstraintPO hasMatchIsomorphicConstraintPO()
   {
      org.sdmlib.models.pattern.creators.ModelPattern pattern = new org.sdmlib.models.pattern.creators.ModelPattern();
      
      MatchIsomorphicConstraintPO patternObject = pattern.hasElementMatchIsomorphicConstraintPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}








