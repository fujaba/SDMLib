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
import java.util.Collections;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.MatchOtherThen;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.models.pattern.util.AttributeConstraintSet;
import org.sdmlib.models.pattern.util.DestroyObjectElemSet;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.util.MatchOtherThenSet;
import org.sdmlib.models.pattern.util.CardinalityConstraintSet;

public class PatternObjectSet extends SimpleSet<PatternObject>
{
   public Class<?> getTypClass() {
		return PatternObject.class;
	}

   public PatternObjectSet()
   {
      // empty
   }

   public PatternObjectSet(PatternObject... objects)
   {
      for (PatternObject obj : objects)
      {
         this.add(obj);
      }
   }

   public PatternObjectSet(Collection<PatternObject> objects)
   {
      this.addAll(objects);
   }

   public static final PatternObjectSet EMPTY_SET = new PatternObjectSet().withFlag(PatternObjectSet.READONLY);


   public PatternObjectPO createPatternObjectPO()
   {
      return new PatternObjectPO(this.toArray(new PatternObject[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.PatternObject";
   }


   @SuppressWarnings("unchecked")
   public PatternObjectSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<PatternObject>)value);
      }
      else if (value != null)
      {
         this.add((PatternObject) value);
      }
      
      return this;
   }
   
   public PatternObjectSet without(PatternObject value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of PatternObject objects and collect a list of the currentMatch attribute values. 
    * 
    * @return List of Object objects reachable via currentMatch attribute
    */
   public ObjectSet getCurrentMatch()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getCurrentMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the currentMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createCurrentMatchCondition(Object value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.getCurrentMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and assign value to the currentMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternObject objects now with new attribute values.
    */
   public PatternObjectSet withCurrentMatch(Object value)
   {
      for (PatternObject obj : this)
      {
         obj.setCurrentMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternObject objects and collect a list of the candidates attribute values. 
    * 
    * @return List of Object objects reachable via candidates attribute
    */
   public ObjectSet getCandidates()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getCandidates());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the candidates attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createCandidatesCondition(Object value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.getCandidates())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and assign value to the candidates attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternObject objects now with new attribute values.
    */
   public PatternObjectSet withCandidates(Object value)
   {
      for (PatternObject obj : this)
      {
         obj.setCandidates(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternObject objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createModifierCondition(String value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createModifierCondition(String lower, String upper)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternObject objects now with new attribute values.
    */
   public PatternObjectSet withModifier(String value)
   {
      for (PatternObject obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternObject objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createHasMatchCondition(boolean value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternObject objects now with new attribute values.
    */
   public PatternObjectSet withHasMatch(boolean value)
   {
      for (PatternObject obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternObject objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (PatternObject obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createPatternObjectNameCondition(String value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createPatternObjectNameCondition(String lower, String upper)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternObject objects now with new attribute values.
    */
   public PatternObjectSet withPatternObjectName(String value)
   {
      for (PatternObject obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of PatternObject objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (PatternObject obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and collect those PatternObject objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of PatternObject objects that match the parameter
    */
   public PatternObjectSet createDoAllMatchesCondition(boolean value)
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of PatternObject objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of PatternObject objects now with new attribute values.
    */
   public PatternObjectSet withDoAllMatches(boolean value)
   {
      for (PatternObject obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PatternObject objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (PatternObject obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PatternObject objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public PatternObjectSet filterPattern(Object value)
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
      
      PatternObjectSet answer = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PatternObject object passed as parameter to the Pattern attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public PatternObjectSet withPattern(Pattern value)
   {
      for (PatternObject obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PatternObject objects and collect a set of the AttributeConstraint objects reached via attrConstraints. 
    * 
    * @return Set of AttributeConstraint objects reachable via attrConstraints
    */
   public AttributeConstraintSet getAttrConstraints()
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (PatternObject obj : this)
      {
         result.with(obj.getAttrConstraints());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PatternObject objects and collect all contained objects with reference attrConstraints pointing to the object passed as parameter. 
    * 
    * @param value The object required as attrConstraints neighbor of the collected results. 
    * 
    * @return Set of AttributeConstraint objects referring to value via attrConstraints
    */
   public PatternObjectSet filterAttrConstraints(Object value)
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
      
      PatternObjectSet answer = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getAttrConstraints()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PatternObject object passed as parameter to the AttrConstraints attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their AttrConstraints attributes.
    */
   public PatternObjectSet withAttrConstraints(AttributeConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withAttrConstraints(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the PatternObject object passed as parameter from the AttrConstraints attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PatternObjectSet withoutAttrConstraints(AttributeConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutAttrConstraints(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PatternObject objects and collect a set of the DestroyObjectElem objects reached via destroyElem. 
    * 
    * @return Set of DestroyObjectElem objects reachable via destroyElem
    */
   public DestroyObjectElemSet getDestroyElem()
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (PatternObject obj : this)
      {
         result.with(obj.getDestroyElem());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PatternObject objects and collect all contained objects with reference destroyElem pointing to the object passed as parameter. 
    * 
    * @param value The object required as destroyElem neighbor of the collected results. 
    * 
    * @return Set of DestroyObjectElem objects referring to value via destroyElem
    */
   public PatternObjectSet filterDestroyElem(Object value)
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
      
      PatternObjectSet answer = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if (neighbors.contains(obj.getDestroyElem()) || (neighbors.isEmpty() && obj.getDestroyElem() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PatternObject object passed as parameter to the DestroyElem attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their DestroyElem attributes.
    */
   public PatternObjectSet withDestroyElem(DestroyObjectElem value)
   {
      for (PatternObject obj : this)
      {
         obj.withDestroyElem(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PatternObject objects and collect a set of the CardinalityConstraint objects reached via cardConstraints. 
    * 
    * @return Set of CardinalityConstraint objects reachable via cardConstraints
    */
   public CardinalityConstraintSet getCardConstraints()
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (PatternObject obj : this)
      {
         result.with(obj.getCardConstraints());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PatternObject objects and collect all contained objects with reference cardConstraints pointing to the object passed as parameter. 
    * 
    * @param value The object required as cardConstraints neighbor of the collected results. 
    * 
    * @return Set of CardinalityConstraint objects referring to value via cardConstraints
    */
   public PatternObjectSet filterCardConstraints(Object value)
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
      
      PatternObjectSet answer = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getCardConstraints()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PatternObject object passed as parameter to the CardConstraints attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their CardConstraints attributes.
    */
   public PatternObjectSet withCardConstraints(CardinalityConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withCardConstraints(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the PatternObject object passed as parameter from the CardConstraints attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PatternObjectSet withoutCardConstraints(CardinalityConstraint value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutCardConstraints(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PatternObject objects and collect a set of the MatchOtherThen objects reached via matchOtherThen. 
    * 
    * @return Set of MatchOtherThen objects reachable via matchOtherThen
    */
   public MatchOtherThenSet getMatchOtherThen()
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (PatternObject obj : this)
      {
         result.with(obj.getMatchOtherThen());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PatternObject objects and collect all contained objects with reference matchOtherThen pointing to the object passed as parameter. 
    * 
    * @param value The object required as matchOtherThen neighbor of the collected results. 
    * 
    * @return Set of MatchOtherThen objects referring to value via matchOtherThen
    */
   public PatternObjectSet filterMatchOtherThen(Object value)
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
      
      PatternObjectSet answer = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMatchOtherThen()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PatternObject object passed as parameter to the MatchOtherThen attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their MatchOtherThen attributes.
    */
   public PatternObjectSet withMatchOtherThen(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withMatchOtherThen(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the PatternObject object passed as parameter from the MatchOtherThen attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PatternObjectSet withoutMatchOtherThen(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutMatchOtherThen(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of PatternObject objects and collect a set of the MatchOtherThen objects reached via excluders. 
    * 
    * @return Set of MatchOtherThen objects reachable via excluders
    */
   public MatchOtherThenSet getExcluders()
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (PatternObject obj : this)
      {
         result.with(obj.getExcluders());
      }
      
      return result;
   }

   /**
    * Loop through the current set of PatternObject objects and collect all contained objects with reference excluders pointing to the object passed as parameter. 
    * 
    * @param value The object required as excluders neighbor of the collected results. 
    * 
    * @return Set of MatchOtherThen objects referring to value via excluders
    */
   public PatternObjectSet filterExcluders(Object value)
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
      
      PatternObjectSet answer = new PatternObjectSet();
      
      for (PatternObject obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getExcluders()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the PatternObject object passed as parameter to the Excluders attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their Excluders attributes.
    */
   public PatternObjectSet withExcluders(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withExcluders(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the PatternObject object passed as parameter from the Excluders attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public PatternObjectSet withoutExcluders(MatchOtherThen value)
   {
      for (PatternObject obj : this)
      {
         obj.withoutExcluders(value);
      }
      
      return this;
   }



   @Override
   public PatternObjectSet getNewList(boolean keyValue)
   {
      return new PatternObjectSet();
   }
}
