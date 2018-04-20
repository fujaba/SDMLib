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

import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.util.PatternObjectSet;

public class CardinalityConstraintSet extends SimpleSet<CardinalityConstraint>
{
   public Class<?> getTypClass() {
		return CardinalityConstraint.class;
	}

   public CardinalityConstraintSet()
   {
      // empty
   }

   public CardinalityConstraintSet(CardinalityConstraint... objects)
   {
      for (CardinalityConstraint obj : objects)
      {
         this.add(obj);
      }
   }

   public CardinalityConstraintSet(Collection<CardinalityConstraint> objects)
   {
      this.addAll(objects);
   }

   public static final CardinalityConstraintSet EMPTY_SET = new CardinalityConstraintSet().withFlag(CardinalityConstraintSet.READONLY);


   public CardinalityConstraintPO createCardinalityConstraintPO()
   {
      return new CardinalityConstraintPO(this.toArray(new CardinalityConstraint[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.CardinalityConstraint";
   }


   @SuppressWarnings("unchecked")
   public CardinalityConstraintSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<CardinalityConstraint>)value);
      }
      else if (value != null)
      {
         this.add((CardinalityConstraint) value);
      }
      
      return this;
   }
   
   public CardinalityConstraintSet without(CardinalityConstraint value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the tgtRoleName attribute values. 
    * 
    * @return List of String objects reachable via tgtRoleName attribute
    */
   public ObjectSet getTgtRoleName()
   {
      ObjectSet result = new ObjectSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getTgtRoleName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the tgtRoleName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createTgtRoleNameCondition(String value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value.equals(obj.getTgtRoleName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the tgtRoleName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createTgtRoleNameCondition(String lower, String upper)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (lower.compareTo(obj.getTgtRoleName()) <= 0 && obj.getTgtRoleName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the tgtRoleName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withTgtRoleName(String value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setTgtRoleName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the hostGraphSrcObject attribute values. 
    * 
    * @return List of Object objects reachable via hostGraphSrcObject attribute
    */
   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the hostGraphSrcObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createHostGraphSrcObjectCondition(Object value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the hostGraphSrcObject attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withHostGraphSrcObject(Object value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setHostGraphSrcObject(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the minCard attribute values. 
    * 
    * @return List of long objects reachable via minCard attribute
    */
   public NumberList getMinCard()
   {
      NumberList result = new NumberList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getMinCard());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the minCard attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createMinCardCondition(long value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value == obj.getMinCard())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the minCard attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createMinCardCondition(long lower, long upper)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (lower <= obj.getMinCard() && obj.getMinCard() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the minCard attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withMinCard(long value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setMinCard(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the maxCard attribute values. 
    * 
    * @return List of long objects reachable via maxCard attribute
    */
   public NumberList getMaxCard()
   {
      NumberList result = new NumberList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getMaxCard());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the maxCard attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createMaxCardCondition(long value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value == obj.getMaxCard())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the maxCard attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createMaxCardCondition(long lower, long upper)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (lower <= obj.getMaxCard() && obj.getMaxCard() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the maxCard attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withMaxCard(long value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setMaxCard(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createModifierCondition(String value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createModifierCondition(String lower, String upper)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withModifier(String value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createHasMatchCondition(boolean value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withHasMatch(boolean value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createPatternObjectNameCondition(String value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createPatternObjectNameCondition(String lower, String upper)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withPatternObjectName(String value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (CardinalityConstraint obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and collect those CardinalityConstraint objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of CardinalityConstraint objects that match the parameter
    */
   public CardinalityConstraintSet createDoAllMatchesCondition(boolean value)
   {
      CardinalityConstraintSet result = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of CardinalityConstraint objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of CardinalityConstraint objects now with new attribute values.
    */
   public CardinalityConstraintSet withDoAllMatches(boolean value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of CardinalityConstraint objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of CardinalityConstraint objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public CardinalityConstraintSet filterPattern(Object value)
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
      
      CardinalityConstraintSet answer = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the CardinalityConstraint object passed as parameter to the Pattern attribute of each of it. 
    *
    * @param value v
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public CardinalityConstraintSet withPattern(Pattern value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of CardinalityConstraint objects and collect a set of the PatternObject objects reached via src. 
    * 
    * @return Set of PatternObject objects reachable via src
    */
   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (CardinalityConstraint obj : this)
      {
         result.with(obj.getSrc());
      }
      
      return result;
   }

   /**
    * Loop through the current set of CardinalityConstraint objects and collect all contained objects with reference src pointing to the object passed as parameter. 
    * 
    * @param value The object required as src neighbor of the collected results. 
    * @return Set of PatternObject objects referring to value via src
    */
   public CardinalityConstraintSet filterSrc(Object value)
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
      
      CardinalityConstraintSet answer = new CardinalityConstraintSet();
      
      for (CardinalityConstraint obj : this)
      {
         if (neighbors.contains(obj.getSrc()) || (neighbors.isEmpty() && obj.getSrc() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the CardinalityConstraint object passed as parameter to the Src attribute of each of it. 
    *
    * @param value v
    * @return The original set of ModelType objects now with the new neighbor attached to their Src attributes.
    */
   public CardinalityConstraintSet withSrc(PatternObject value)
   {
      for (CardinalityConstraint obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }



   @Override
   public CardinalityConstraintSet getNewList(boolean keyValue)
   {
      return new CardinalityConstraintSet();
   }
}
