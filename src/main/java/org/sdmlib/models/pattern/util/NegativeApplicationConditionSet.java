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
import org.sdmlib.models.pattern.NegativeApplicationCondition;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.BooleanList;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.Pattern;
import java.util.Collections;
import org.sdmlib.models.pattern.util.PatternElementSet;
import org.sdmlib.models.pattern.PatternElement;
import org.sdmlib.models.pattern.util.ReachabilityGraphSet;
import org.sdmlib.models.pattern.ReachabilityGraph;

public class NegativeApplicationConditionSet extends SimpleSet<NegativeApplicationCondition>
{
	protected Class<?> getTypClass() {
		return NegativeApplicationCondition.class;
	}

   public NegativeApplicationConditionSet()
   {
      // empty
   }

   public NegativeApplicationConditionSet(NegativeApplicationCondition... objects)
   {
      for (NegativeApplicationCondition obj : objects)
      {
         this.add(obj);
      }
   }

   public NegativeApplicationConditionSet(Collection<NegativeApplicationCondition> objects)
   {
      this.addAll(objects);
   }

   public static final NegativeApplicationConditionSet EMPTY_SET = new NegativeApplicationConditionSet().withFlag(NegativeApplicationConditionSet.READONLY);


   public NegativeApplicationConditionPO createNegativeApplicationConditionPO()
   {
      return new NegativeApplicationConditionPO(this.toArray(new NegativeApplicationCondition[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.NegativeApplicationCondition";
   }


   @SuppressWarnings("unchecked")
   public NegativeApplicationConditionSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<NegativeApplicationCondition>)value);
      }
      else if (value != null)
      {
         this.add((NegativeApplicationCondition) value);
      }
      
      return this;
   }
   
   public NegativeApplicationConditionSet without(NegativeApplicationCondition value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a list of the debugMode attribute values. 
    * 
    * @return List of int objects reachable via debugMode attribute
    */
   public NumberList getDebugMode()
   {
      NumberList result = new NumberList();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getDebugMode());
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the debugMode attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createDebugModeCondition(int value)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (value == obj.getDebugMode())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the debugMode attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createDebugModeCondition(int lower, int upper)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (lower <= obj.getDebugMode() && obj.getDebugMode() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and assign value to the debugMode attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of NegativeApplicationCondition objects now with new attribute values.
    */
   public NegativeApplicationConditionSet withDebugMode(int value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.setDebugMode(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public ObjectSet getName()
   {
      ObjectSet result = new ObjectSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createNameCondition(String value)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createNameCondition(String lower, String upper)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of NegativeApplicationCondition objects now with new attribute values.
    */
   public NegativeApplicationConditionSet withName(String value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createModifierCondition(String value)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createModifierCondition(String lower, String upper)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of NegativeApplicationCondition objects now with new attribute values.
    */
   public NegativeApplicationConditionSet withModifier(String value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createHasMatchCondition(boolean value)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of NegativeApplicationCondition objects now with new attribute values.
    */
   public NegativeApplicationConditionSet withHasMatch(boolean value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createPatternObjectNameCondition(String value)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createPatternObjectNameCondition(String lower, String upper)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of NegativeApplicationCondition objects now with new attribute values.
    */
   public NegativeApplicationConditionSet withPatternObjectName(String value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect those NegativeApplicationCondition objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of NegativeApplicationCondition objects that match the parameter
    */
   public NegativeApplicationConditionSet createDoAllMatchesCondition(boolean value)
   {
      NegativeApplicationConditionSet result = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of NegativeApplicationCondition objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of NegativeApplicationCondition objects now with new attribute values.
    */
   public NegativeApplicationConditionSet withDoAllMatches(boolean value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public NegativeApplicationConditionSet filterPattern(Object value)
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
      
      NegativeApplicationConditionSet answer = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the NegativeApplicationCondition object passed as parameter to the Pattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public NegativeApplicationConditionSet withPattern(Pattern value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a set of the PatternElement objects reached via elements. 
    * 
    * @return Set of PatternElement objects reachable via elements
    */
   public PatternElementSet getElements()
   {
      PatternElementSet result = new PatternElementSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.with(obj.getElements());
      }
      
      return result;
   }

   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect all contained objects with reference elements pointing to the object passed as parameter. 
    * 
    * @param value The object required as elements neighbor of the collected results. 
    * 
    * @return Set of PatternElement objects referring to value via elements
    */
   public NegativeApplicationConditionSet filterElements(Object value)
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
      
      NegativeApplicationConditionSet answer = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getElements()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the NegativeApplicationCondition object passed as parameter to the Elements attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Elements attributes.
    */
   public NegativeApplicationConditionSet withElements(PatternElement value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withElements(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the NegativeApplicationCondition object passed as parameter from the Elements attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public NegativeApplicationConditionSet withoutElements(PatternElement value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withoutElements(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect a set of the Pattern objects reached via currentSubPattern. 
    * 
    * @return Set of Pattern objects reachable via currentSubPattern
    */
   public PatternSet getCurrentSubPattern()
   {
      PatternSet result = new PatternSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         result.with(obj.getCurrentSubPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of NegativeApplicationCondition objects and collect all contained objects with reference currentSubPattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as currentSubPattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via currentSubPattern
    */
   public NegativeApplicationConditionSet filterCurrentSubPattern(Object value)
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
      
      NegativeApplicationConditionSet answer = new NegativeApplicationConditionSet();
      
      for (NegativeApplicationCondition obj : this)
      {
         if (neighbors.contains(obj.getCurrentSubPattern()) || (neighbors.isEmpty() && obj.getCurrentSubPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow currentSubPattern reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Pattern objects reachable via currentSubPattern transitively (including the start set)
    */
   public PatternSet getCurrentSubPatternTransitive()
   {
      PatternSet todo = new PatternSet().with(this);
      
      PatternSet result = new PatternSet();
      
      while ( ! todo.isEmpty())
      {
         Pattern current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getCurrentSubPattern()))
            {
               todo.with(current.getCurrentSubPattern());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the NegativeApplicationCondition object passed as parameter to the CurrentSubPattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their CurrentSubPattern attributes.
    */
   public NegativeApplicationConditionSet withCurrentSubPattern(Pattern value)
   {
      for (NegativeApplicationCondition obj : this)
      {
         obj.withCurrentSubPattern(value);
      }
      
      return this;
   }


}
