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
import org.sdmlib.models.pattern.AttributeConstraint;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.BooleanList;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.PatternObjectSet;
import org.sdmlib.models.pattern.PatternObject;

public class AttributeConstraintSet extends SimpleSet<AttributeConstraint>
{
	protected Class<?> getTypClass() {
		return AttributeConstraint.class;
	}

   public AttributeConstraintSet()
   {
      // empty
   }

   public AttributeConstraintSet(AttributeConstraint... objects)
   {
      for (AttributeConstraint obj : objects)
      {
         this.add(obj);
      }
   }

   public AttributeConstraintSet(Collection<AttributeConstraint> objects)
   {
      this.addAll(objects);
   }

   public static final AttributeConstraintSet EMPTY_SET = new AttributeConstraintSet().withFlag(AttributeConstraintSet.READONLY);


   public AttributeConstraintPO createAttributeConstraintPO()
   {
      return new AttributeConstraintPO(this.toArray(new AttributeConstraint[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.AttributeConstraint";
   }


   @SuppressWarnings("unchecked")
   public AttributeConstraintSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<AttributeConstraint>)value);
      }
      else if (value != null)
      {
         this.add((AttributeConstraint) value);
      }
      
      return this;
   }
   
   public AttributeConstraintSet without(AttributeConstraint value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the attrName attribute values. 
    * 
    * @return List of String objects reachable via attrName attribute
    */
   public ObjectSet getAttrName()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getAttrName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the attrName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createAttrNameCondition(String value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value.equals(obj.getAttrName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the attrName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createAttrNameCondition(String lower, String upper)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (lower.compareTo(obj.getAttrName()) <= 0 && obj.getAttrName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the attrName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withAttrName(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setAttrName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the tgtValue attribute values. 
    * 
    * @return List of Object objects reachable via tgtValue attribute
    */
   public ObjectSet getTgtValue()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getTgtValue());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the tgtValue attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createTgtValueCondition(Object value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value == obj.getTgtValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the tgtValue attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withTgtValue(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setTgtValue(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the upperTgtValue attribute values. 
    * 
    * @return List of Object objects reachable via upperTgtValue attribute
    */
   public ObjectSet getUpperTgtValue()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getUpperTgtValue());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the upperTgtValue attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createUpperTgtValueCondition(Object value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value == obj.getUpperTgtValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the upperTgtValue attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withUpperTgtValue(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setUpperTgtValue(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the cmpOp attribute values. 
    * 
    * @return List of String objects reachable via cmpOp attribute
    */
   public ObjectSet getCmpOp()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getCmpOp());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the cmpOp attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createCmpOpCondition(String value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value.equals(obj.getCmpOp()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the cmpOp attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createCmpOpCondition(String lower, String upper)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (lower.compareTo(obj.getCmpOp()) <= 0 && obj.getCmpOp().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the cmpOp attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withCmpOp(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setCmpOp(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the hostGraphSrcObject attribute values. 
    * 
    * @return List of Object objects reachable via hostGraphSrcObject attribute
    */
   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the hostGraphSrcObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createHostGraphSrcObjectCondition(Object value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the hostGraphSrcObject attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withHostGraphSrcObject(Object value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setHostGraphSrcObject(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createModifierCondition(String value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createModifierCondition(String lower, String upper)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withModifier(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createHasMatchCondition(boolean value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withHasMatch(boolean value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createPatternObjectNameCondition(String value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createPatternObjectNameCondition(String lower, String upper)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withPatternObjectName(String value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (AttributeConstraint obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and collect those AttributeConstraint objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of AttributeConstraint objects that match the parameter
    */
   public AttributeConstraintSet createDoAllMatchesCondition(boolean value)
   {
      AttributeConstraintSet result = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of AttributeConstraint objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of AttributeConstraint objects now with new attribute values.
    */
   public AttributeConstraintSet withDoAllMatches(boolean value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of AttributeConstraint objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (AttributeConstraint obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of AttributeConstraint objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public AttributeConstraintSet filterPattern(Object value)
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
      
      AttributeConstraintSet answer = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the AttributeConstraint object passed as parameter to the Pattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public AttributeConstraintSet withPattern(Pattern value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of AttributeConstraint objects and collect a set of the PatternObject objects reached via src. 
    * 
    * @return Set of PatternObject objects reachable via src
    */
   public PatternObjectSet getSrc()
   {
      PatternObjectSet result = new PatternObjectSet();
      
      for (AttributeConstraint obj : this)
      {
         result.with(obj.getSrc());
      }
      
      return result;
   }

   /**
    * Loop through the current set of AttributeConstraint objects and collect all contained objects with reference src pointing to the object passed as parameter. 
    * 
    * @param value The object required as src neighbor of the collected results. 
    * 
    * @return Set of PatternObject objects referring to value via src
    */
   public AttributeConstraintSet filterSrc(Object value)
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
      
      AttributeConstraintSet answer = new AttributeConstraintSet();
      
      for (AttributeConstraint obj : this)
      {
         if (neighbors.contains(obj.getSrc()) || (neighbors.isEmpty() && obj.getSrc() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the AttributeConstraint object passed as parameter to the Src attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Src attributes.
    */
   public AttributeConstraintSet withSrc(PatternObject value)
   {
      for (AttributeConstraint obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

}
