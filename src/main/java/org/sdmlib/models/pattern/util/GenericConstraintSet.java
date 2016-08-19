/*
   Copyright (c) 2016 christoph
   
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

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.models.pattern.GenericConstraint;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.BooleanList;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.Pattern;

public class GenericConstraintSet extends SimpleSet<GenericConstraint>
{
	protected Class<?> getTypClass() {
		return GenericConstraint.class;
	}

   public GenericConstraintSet()
   {
      // empty
   }

   public GenericConstraintSet(GenericConstraint... objects)
   {
      for (GenericConstraint obj : objects)
      {
         this.add(obj);
      }
   }

   public GenericConstraintSet(Collection<GenericConstraint> objects)
   {
      this.addAll(objects);
   }

   public static final GenericConstraintSet EMPTY_SET = new GenericConstraintSet().withFlag(GenericConstraintSet.READONLY);


   public GenericConstraintPO createGenericConstraintPO()
   {
      return new GenericConstraintPO(this.toArray(new GenericConstraint[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.GenericConstraint";
   }


   @SuppressWarnings("unchecked")
   public GenericConstraintSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<GenericConstraint>)value);
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


   /**
    * Loop through the current set of GenericConstraint objects and collect a list of the text attribute values. 
    * 
    * @return List of String objects reachable via text attribute
    */
   public ObjectSet getText()
   {
      ObjectSet result = new ObjectSet();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getText());
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the text attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createTextCondition(String value)
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


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the text attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createTextCondition(String lower, String upper)
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


   /**
    * Loop through the current set of GenericConstraint objects and assign value to the text attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of GenericConstraint objects now with new attribute values.
    */
   public GenericConstraintSet withText(String value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setText(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createModifierCondition(String value)
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


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createModifierCondition(String lower, String upper)
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


   /**
    * Loop through the current set of GenericConstraint objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of GenericConstraint objects now with new attribute values.
    */
   public GenericConstraintSet withModifier(String value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createHasMatchCondition(boolean value)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericConstraint objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of GenericConstraint objects now with new attribute values.
    */
   public GenericConstraintSet withHasMatch(boolean value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createPatternObjectNameCondition(String value)
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


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createPatternObjectNameCondition(String lower, String upper)
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


   /**
    * Loop through the current set of GenericConstraint objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of GenericConstraint objects now with new attribute values.
    */
   public GenericConstraintSet withPatternObjectName(String value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (GenericConstraint obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericConstraint objects and collect those GenericConstraint objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of GenericConstraint objects that match the parameter
    */
   public GenericConstraintSet createDoAllMatchesCondition(boolean value)
   {
      GenericConstraintSet result = new GenericConstraintSet();
      
      for (GenericConstraint obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of GenericConstraint objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of GenericConstraint objects now with new attribute values.
    */
   public GenericConstraintSet withDoAllMatches(boolean value)
   {
      for (GenericConstraint obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of GenericConstraint objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (GenericConstraint obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of GenericConstraint objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public GenericConstraintSet filterPattern(Object value)
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
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the GenericConstraint object passed as parameter to the Pattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public GenericConstraintSet withPattern(Pattern value)
   {
      for (GenericConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

}
