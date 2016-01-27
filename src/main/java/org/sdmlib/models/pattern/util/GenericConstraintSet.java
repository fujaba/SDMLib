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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.pattern.GenericConstraint;
import org.sdmlib.models.pattern.Pattern;

import de.uniks.networkparser.list.SimpleSet;

public class GenericConstraintSet extends SimpleSet<GenericConstraint>
{
   public GenericConstraintPO startModelPattern()
   {
      return new GenericConstraintPO(this.toArray(new GenericConstraint[this.size()]));
   }

   public StringList getModifier()
   {
      StringList result = new StringList();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }

   public GenericConstraintSet hasModifier(String value)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericConstraintSet withModifier(String value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }

   public booleanList getHasMatch()
   {
      booleanList result = new booleanList();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getHasMatch());
      }
      
      return result;
   }

   public GenericConstraintSet hasHasMatch(boolean value)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (value == obj.getHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericConstraintSet withHasMatch(boolean value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }

   public StringList getPatternObjectName()
   {
      StringList result = new StringList();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }

   public GenericConstraintSet hasPatternObjectName(String value)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericConstraintSet withPatternObjectName(String value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }

   public booleanList getDoAllMatches()
   {
      booleanList result = new booleanList();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getDoAllMatches());
      }
      
      return result;
   }

   public GenericConstraintSet hasDoAllMatches(boolean value)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (value == obj.getDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericConstraintSet withDoAllMatches(boolean value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getPattern());
      }
      
      return result;
   }

   public GenericConstraintSet hasPattern(Object value)
   {
      ObjectSet neighbors = new ObjectSet();

      if (value instanceof Collection)
      {
         neighbors.addAll((Collection<?>) value);
      }
      else
      {
         neighbors.add(value);
      }
      
      GenericConstraintSet answer = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (neighbors.contains(obj.getPattern()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public GenericConstraintSet withPattern(Pattern value)
   {
      for (GenericConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   public GenericConstraintSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.withList((Collection<?>)value);
      }
      else if (value != null)
      {
         this.add((GenericConstraint) value);
      }
      
      return this;
   }
   
   public GenericConstraintSet without(GenericConstraint value)
   {
      this.remove(value);
      return this;
   }



   public GenericConstraintPO hasGenericConstraintPO()
   {
      return new GenericConstraintPO(this.toArray(new GenericConstraint[this.size()]));
   }
   public StringList getText()
   {
      StringList result = new StringList();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }

   public GenericConstraintSet hasText(String value)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (value.equals(obj.getText()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericConstraintSet hasText(String lower, String upper)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (lower.compareTo(obj.getText()) <= 0 && obj.getText().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericConstraintSet withText(String value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }


   public static final GenericConstraintSet EMPTY_SET = new GenericConstraintSet().withFlag(GenericConstraintSet.READONLY);
   public GenericConstraintSet hasModifier(String lower, String upper)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public GenericConstraintSet hasPatternObjectName(String lower, String upper)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}












