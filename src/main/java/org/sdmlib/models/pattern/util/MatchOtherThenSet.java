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
import org.sdmlib.models.pattern.MatchOtherThen;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.BooleanList;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternObjectSet;
import org.sdmlib.models.pattern.PatternObject;

public class MatchOtherThenSet extends SimpleSet<MatchOtherThen>
{
	protected Class<?> getTypClass() {
		return MatchOtherThen.class;
	}

   public MatchOtherThenSet()
   {
      // empty
   }

   public MatchOtherThenSet(MatchOtherThen... objects)
   {
      for (MatchOtherThen obj : objects)
      {
         this.add(obj);
      }
   }

   public MatchOtherThenSet(Collection<MatchOtherThen> objects)
   {
      this.addAll(objects);
   }

   public static final MatchOtherThenSet EMPTY_SET = new MatchOtherThenSet().withFlag(MatchOtherThenSet.READONLY);


   public MatchOtherThenPO createMatchOtherThenPO()
   {
      return new MatchOtherThenPO(this.toArray(new MatchOtherThen[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.MatchOtherThen";
   }


   @SuppressWarnings("unchecked")
   public MatchOtherThenSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<MatchOtherThen>)value);
      }
      else if (value != null)
      {
         this.add((MatchOtherThen) value);
      }
      
      return this;
   }
   
   public MatchOtherThenSet without(MatchOtherThen value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect a list of the hostGraphSrcObject attribute values. 
    * 
    * @return List of Object objects reachable via hostGraphSrcObject attribute
    */
   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the hostGraphSrcObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet createHostGraphSrcObjectCondition(Object value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and assign value to the hostGraphSrcObject attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of MatchOtherThen objects now with new attribute values.
    */
   public MatchOtherThenSet withHostGraphSrcObject(Object value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setHostGraphSrcObject(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet createModifierCondition(String value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet createModifierCondition(String lower, String upper)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of MatchOtherThen objects now with new attribute values.
    */
   public MatchOtherThenSet withModifier(String value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet createHasMatchCondition(boolean value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of MatchOtherThen objects now with new attribute values.
    */
   public MatchOtherThenSet withHasMatch(boolean value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet createPatternObjectNameCondition(String value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet createPatternObjectNameCondition(String lower, String upper)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of MatchOtherThen objects now with new attribute values.
    */
   public MatchOtherThenSet withPatternObjectName(String value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (MatchOtherThen obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and collect those MatchOtherThen objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of MatchOtherThen objects that match the parameter
    */
   public MatchOtherThenSet createDoAllMatchesCondition(boolean value)
   {
      MatchOtherThenSet result = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of MatchOtherThen objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of MatchOtherThen objects now with new attribute values.
    */
   public MatchOtherThenSet withDoAllMatches(boolean value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MatchOtherThen objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (MatchOtherThen obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MatchOtherThen objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public MatchOtherThenSet filterPattern(Object value)
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
      
      MatchOtherThenSet answer = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MatchOtherThen object passed as parameter to the Pattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public MatchOtherThenSet withPattern(Pattern value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MatchOtherThen objects and collect a set of the PatternObject objects reached via src. 
    * 
    * @return Set of PatternObject objects reachable via src
    */
   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.with(obj.getSrc());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MatchOtherThen objects and collect all contained objects with reference src pointing to the object passed as parameter. 
    * 
    * @param value The object required as src neighbor of the collected results. 
    * 
    * @return Set of PatternObject objects referring to value via src
    */
   public MatchOtherThenSet filterSrc(Object value)
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
      
      MatchOtherThenSet answer = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (neighbors.contains(obj.getSrc()) || (neighbors.isEmpty() && obj.getSrc() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MatchOtherThen object passed as parameter to the Src attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Src attributes.
    */
   public MatchOtherThenSet withSrc(PatternObject value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of MatchOtherThen objects and collect a set of the PatternObject objects reached via forbidden. 
    * 
    * @return Set of PatternObject objects reachable via forbidden
    */
   public PatternObjectSet getForbidden()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (MatchOtherThen obj : this)
      {
         result.with(obj.getForbidden());
      }
      
      return result;
   }

   /**
    * Loop through the current set of MatchOtherThen objects and collect all contained objects with reference forbidden pointing to the object passed as parameter. 
    * 
    * @param value The object required as forbidden neighbor of the collected results. 
    * 
    * @return Set of PatternObject objects referring to value via forbidden
    */
   public MatchOtherThenSet filterForbidden(Object value)
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
      
      MatchOtherThenSet answer = new MatchOtherThenSet();
      
      for (MatchOtherThen obj : this)
      {
         if (neighbors.contains(obj.getForbidden()) || (neighbors.isEmpty() && obj.getForbidden() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the MatchOtherThen object passed as parameter to the Forbidden attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Forbidden attributes.
    */
   public MatchOtherThenSet withForbidden(PatternObject value)
   {
      for (MatchOtherThen obj : this)
      {
         obj.withForbidden(value);
      }
      
      return this;
   }

}
