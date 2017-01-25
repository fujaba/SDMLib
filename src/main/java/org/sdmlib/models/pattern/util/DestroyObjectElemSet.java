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

import org.sdmlib.models.pattern.DestroyObjectElem;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;

import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.pattern.util.PatternObjectSet;
import org.sdmlib.models.pattern.util.PatternSet;

public class DestroyObjectElemSet extends SimpleSet<DestroyObjectElem>
{
	protected Class<?> getTypClass() {
		return DestroyObjectElem.class;
	}

   public DestroyObjectElemSet()
   {
      // empty
   }

   public DestroyObjectElemSet(DestroyObjectElem... objects)
   {
      for (DestroyObjectElem obj : objects)
      {
         this.add(obj);
      }
   }

   public DestroyObjectElemSet(Collection<DestroyObjectElem> objects)
   {
      this.addAll(objects);
   }

   public static final DestroyObjectElemSet EMPTY_SET = new DestroyObjectElemSet().withFlag(DestroyObjectElemSet.READONLY);


   public DestroyObjectElemPO createDestroyObjectElemPO()
   {
      return new DestroyObjectElemPO(this.toArray(new DestroyObjectElem[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.DestroyObjectElem";
   }


   @SuppressWarnings("unchecked")
   public DestroyObjectElemSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<DestroyObjectElem>)value);
      }
      else if (value != null)
      {
         this.add((DestroyObjectElem) value);
      }
      
      return this;
   }
   
   public DestroyObjectElemSet without(DestroyObjectElem value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect those DestroyObjectElem objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DestroyObjectElem objects that match the parameter
    */
   public DestroyObjectElemSet createModifierCondition(String value)
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect those DestroyObjectElem objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of DestroyObjectElem objects that match the parameter
    */
   public DestroyObjectElemSet createModifierCondition(String lower, String upper)
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DestroyObjectElem objects now with new attribute values.
    */
   public DestroyObjectElemSet withModifier(String value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect those DestroyObjectElem objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DestroyObjectElem objects that match the parameter
    */
   public DestroyObjectElemSet createHasMatchCondition(boolean value)
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DestroyObjectElem objects now with new attribute values.
    */
   public DestroyObjectElemSet withHasMatch(boolean value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect those DestroyObjectElem objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DestroyObjectElem objects that match the parameter
    */
   public DestroyObjectElemSet createPatternObjectNameCondition(String value)
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect those DestroyObjectElem objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of DestroyObjectElem objects that match the parameter
    */
   public DestroyObjectElemSet createPatternObjectNameCondition(String lower, String upper)
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DestroyObjectElem objects now with new attribute values.
    */
   public DestroyObjectElemSet withPatternObjectName(String value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (DestroyObjectElem obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and collect those DestroyObjectElem objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of DestroyObjectElem objects that match the parameter
    */
   public DestroyObjectElemSet createDoAllMatchesCondition(boolean value)
   {
      DestroyObjectElemSet result = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of DestroyObjectElem objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of DestroyObjectElem objects now with new attribute values.
    */
   public DestroyObjectElemSet withDoAllMatches(boolean value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of DestroyObjectElem objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (DestroyObjectElem obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of DestroyObjectElem objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public DestroyObjectElemSet filterPattern(Object value)
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
      
      DestroyObjectElemSet answer = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the DestroyObjectElem object passed as parameter to the Pattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public DestroyObjectElemSet withPattern(Pattern value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of DestroyObjectElem objects and collect a set of the PatternObject objects reached via patternObject. 
    * 
    * @return Set of PatternObject objects reachable via patternObject
    */
   public PatternObjectSet getPatternObject()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (DestroyObjectElem obj : this)
      {
         result.with(obj.getPatternObject());
      }
      
      return result;
   }

   /**
    * Loop through the current set of DestroyObjectElem objects and collect all contained objects with reference patternObject pointing to the object passed as parameter. 
    * 
    * @param value The object required as patternObject neighbor of the collected results. 
    * 
    * @return Set of PatternObject objects referring to value via patternObject
    */
   public DestroyObjectElemSet filterPatternObject(Object value)
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
      
      DestroyObjectElemSet answer = new DestroyObjectElemSet();
      
      for (DestroyObjectElem obj : this)
      {
         if (neighbors.contains(obj.getPatternObject()) || (neighbors.isEmpty() && obj.getPatternObject() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the DestroyObjectElem object passed as parameter to the PatternObject attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their PatternObject attributes.
    */
   public DestroyObjectElemSet withPatternObject(PatternObject value)
   {
      for (DestroyObjectElem obj : this)
      {
         obj.withPatternObject(value);
      }
      
      return this;
   }



   @Override
   public DestroyObjectElemSet getNewList(boolean keyValue)
   {
      return new DestroyObjectElemSet();
   }


   public DestroyObjectElemSet filter(Condition<DestroyObjectElem> condition) {
      DestroyObjectElemSet filterList = new DestroyObjectElemSet();
      filterItems(filterList, condition);
      return filterList;
   }}
