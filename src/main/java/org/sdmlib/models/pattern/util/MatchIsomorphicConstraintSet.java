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
   
package org.sdmlib.models.pattern.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.MatchIsomorphicConstraint;
import org.sdmlib.models.pattern.Pattern;

import de.uniks.networkparser.list.SimpleSet;

public class MatchIsomorphicConstraintSet extends SimpleSet<MatchIsomorphicConstraint>
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
         obj.setModifier(value);
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
         obj.setHasMatch(value);
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
         obj.setDoAllMatches(value);
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
         obj.setPatternObjectName(value);
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

   public MatchIsomorphicConstraintSet withPattern(Pattern<?> value)
   {
      for (MatchIsomorphicConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   public MatchIsomorphicConstraintPO startModelPattern()
   {
      return new MatchIsomorphicConstraintPO(this.toArray(new MatchIsomorphicConstraint[this.size()]));
   }


   public MatchIsomorphicConstraintSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
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
      return new MatchIsomorphicConstraintPO(this.toArray(new MatchIsomorphicConstraint[this.size()]));
   }

   public static final MatchIsomorphicConstraintSet EMPTY_SET = new MatchIsomorphicConstraintSet().withFlag(MatchIsomorphicConstraintSet.READONLY);
   public MatchIsomorphicConstraintSet hasModifier(String value)
   {
      MatchIsomorphicConstraintSet result = new MatchIsomorphicConstraintSet();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet hasModifier(String lower, String upper)
   {
      MatchIsomorphicConstraintSet result = new MatchIsomorphicConstraintSet();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet hasHasMatch(boolean value)
   {
      MatchIsomorphicConstraintSet result = new MatchIsomorphicConstraintSet();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet hasPatternObjectName(String value)
   {
      MatchIsomorphicConstraintSet result = new MatchIsomorphicConstraintSet();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet hasPatternObjectName(String lower, String upper)
   {
      MatchIsomorphicConstraintSet result = new MatchIsomorphicConstraintSet();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public MatchIsomorphicConstraintSet hasDoAllMatches(boolean value)
   {
      MatchIsomorphicConstraintSet result = new MatchIsomorphicConstraintSet();
      
      for (MatchIsomorphicConstraint obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
