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

import java.util.Collection;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternElement;

import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class PatternElementSet extends SimpleSet<PatternElement>
{
	protected Class<?> getTypClass() {
		return PatternElement.class;
	}

   public PatternElementSet()
   {
      // empty
   }

   public PatternElementSet(PatternElement... objects)
   {
      for (PatternElement obj : objects)
      {
         this.add(obj);
      }
   }

   public PatternElementSet(Collection<PatternElement> objects)
   {
      this.addAll(objects);
   }

   public static final PatternElementSet EMPTY_SET = new PatternElementSet().withFlag(PatternElementSet.READONLY);


   public PatternElementPO createPatternElementPO()
   {
      return new PatternElementPO(this.toArray(new PatternElement[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.PatternElement";
   }


   @SuppressWarnings("unchecked")
   public PatternElementSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PatternElement>)value);
      }
      else if (value != null)
      {
         this.add((PatternElement) value);
      }
      
      return this;
   }
   
   public PatternElementSet without(PatternElement value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of PatternElement objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet createModifierCondition(String value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet createModifierCondition(String lower, String upper)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternElement objects now with new attribute values.
    */
   public PatternElementSet withModifier(String value)
   {
      for (PatternElement obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternElement objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet createHasMatchCondition(boolean value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternElement objects now with new attribute values.
    */
   public PatternElementSet withHasMatch(boolean value)
   {
      for (PatternElement obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternElement objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternElement obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet createPatternObjectNameCondition(String value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet createPatternObjectNameCondition(String lower, String upper)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternElement objects now with new attribute values.
    */
   public PatternElementSet withPatternObjectName(String value)
   {
      for (PatternElement obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternElement objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (PatternElement obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and collect those PatternElement objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternElement objects that match the parameter
    */
   public PatternElementSet createDoAllMatchesCondition(boolean value)
   {
      PatternElementSet result = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternElement objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternElement objects now with new attribute values.
    */
   public PatternElementSet withDoAllMatches(boolean value)
   {
      for (PatternElement obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PatternElement objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternElement obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PatternElement objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public PatternElementSet filterPattern(Object value)
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
      
      PatternElementSet answer = new PatternElementSet();
      
      for (PatternElement obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PatternElement object passed as parameter to the Pattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public PatternElementSet withPattern(Pattern value)
   {
      for (PatternElement obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

}
