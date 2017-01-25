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

import org.sdmlib.models.pattern.LinkConstraint;
import org.sdmlib.models.pattern.Pattern;

import de.uniks.networkparser.list.BooleanList;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.pattern.util.PatternSet;

public class LinkConstraintSet extends SimpleSet<LinkConstraint>
{
	protected Class<?> getTypClass() {
		return LinkConstraint.class;
	}

   public LinkConstraintSet()
   {
      // empty
   }

   public LinkConstraintSet(LinkConstraint... objects)
   {
      for (LinkConstraint obj : objects)
      {
         this.add(obj);
      }
   }

   public LinkConstraintSet(Collection<LinkConstraint> objects)
   {
      this.addAll(objects);
   }

   public static final LinkConstraintSet EMPTY_SET = new LinkConstraintSet().withFlag(LinkConstraintSet.READONLY);


   public LinkConstraintPO createLinkConstraintPO()
   {
      return new LinkConstraintPO(this.toArray(new LinkConstraint[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.LinkConstraint";
   }


   @SuppressWarnings("unchecked")
   public LinkConstraintSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LinkConstraint>)value);
      }
      else if (value != null)
      {
         this.add((LinkConstraint) value);
      }
      
      return this;
   }
   
   public LinkConstraintSet without(LinkConstraint value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect a list of the tgtRoleName attribute values. 
    * 
    * @return List of String objects reachable via tgtRoleName attribute
    */
   public ObjectSet getTgtRoleName()
   {
      ObjectSet result = new ObjectSet();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getTgtRoleName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the tgtRoleName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createTgtRoleNameCondition(String value)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (value.equals(obj.getTgtRoleName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the tgtRoleName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createTgtRoleNameCondition(String lower, String upper)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (lower.compareTo(obj.getTgtRoleName()) <= 0 && obj.getTgtRoleName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and assign value to the tgtRoleName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LinkConstraint objects now with new attribute values.
    */
   public LinkConstraintSet withTgtRoleName(String value)
   {
      for (LinkConstraint obj : this)
      {
         obj.setTgtRoleName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect a list of the hostGraphSrcObject attribute values. 
    * 
    * @return List of Object objects reachable via hostGraphSrcObject attribute
    */
   public ObjectSet getHostGraphSrcObject()
   {
      ObjectSet result = new ObjectSet();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getHostGraphSrcObject());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the hostGraphSrcObject attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createHostGraphSrcObjectCondition(Object value)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (value == obj.getHostGraphSrcObject())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and assign value to the hostGraphSrcObject attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LinkConstraint objects now with new attribute values.
    */
   public LinkConstraintSet withHostGraphSrcObject(Object value)
   {
      for (LinkConstraint obj : this)
      {
         obj.setHostGraphSrcObject(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect a list of the modifier attribute values. 
    * 
    * @return List of String objects reachable via modifier attribute
    */
   public ObjectSet getModifier()
   {
      ObjectSet result = new ObjectSet();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getModifier());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the modifier attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createModifierCondition(String value)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (value.equals(obj.getModifier()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the modifier attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createModifierCondition(String lower, String upper)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (lower.compareTo(obj.getModifier()) <= 0 && obj.getModifier().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and assign value to the modifier attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LinkConstraint objects now with new attribute values.
    */
   public LinkConstraintSet withModifier(String value)
   {
      for (LinkConstraint obj : this)
      {
         obj.setModifier(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect a list of the hasMatch attribute values. 
    * 
    * @return List of boolean objects reachable via hasMatch attribute
    */
   public BooleanList getHasMatch()
   {
      BooleanList result = new BooleanList();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.isHasMatch());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the hasMatch attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createHasMatchCondition(boolean value)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (value == obj.isHasMatch())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and assign value to the hasMatch attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LinkConstraint objects now with new attribute values.
    */
   public LinkConstraintSet withHasMatch(boolean value)
   {
      for (LinkConstraint obj : this)
      {
         obj.setHasMatch(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect a list of the patternObjectName attribute values. 
    * 
    * @return List of String objects reachable via patternObjectName attribute
    */
   public ObjectSet getPatternObjectName()
   {
      ObjectSet result = new ObjectSet();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.getPatternObjectName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the patternObjectName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createPatternObjectNameCondition(String value)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (value.equals(obj.getPatternObjectName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the patternObjectName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createPatternObjectNameCondition(String lower, String upper)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (lower.compareTo(obj.getPatternObjectName()) <= 0 && obj.getPatternObjectName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and assign value to the patternObjectName attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LinkConstraint objects now with new attribute values.
    */
   public LinkConstraintSet withPatternObjectName(String value)
   {
      for (LinkConstraint obj : this)
      {
         obj.setPatternObjectName(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect a list of the doAllMatches attribute values. 
    * 
    * @return List of boolean objects reachable via doAllMatches attribute
    */
   public BooleanList getDoAllMatches()
   {
      BooleanList result = new BooleanList();
      
      for (LinkConstraint obj : this)
      {
         result.add(obj.isDoAllMatches());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and collect those LinkConstraint objects where the doAllMatches attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LinkConstraint objects that match the parameter
    */
   public LinkConstraintSet createDoAllMatchesCondition(boolean value)
   {
      LinkConstraintSet result = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (value == obj.isDoAllMatches())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LinkConstraint objects and assign value to the doAllMatches attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LinkConstraint objects now with new attribute values.
    */
   public LinkConstraintSet withDoAllMatches(boolean value)
   {
      for (LinkConstraint obj : this)
      {
         obj.setDoAllMatches(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LinkConstraint objects and collect a set of the Pattern objects reached via pattern. 
    * 
    * @return Set of Pattern objects reachable via pattern
    */
   public PatternSet getPattern()
   {
      PatternSet result = new PatternSet();
      
      for (LinkConstraint obj : this)
      {
         result.with(obj.getPattern());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LinkConstraint objects and collect all contained objects with reference pattern pointing to the object passed as parameter. 
    * 
    * @param value The object required as pattern neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via pattern
    */
   public LinkConstraintSet filterPattern(Object value)
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
      
      LinkConstraintSet answer = new LinkConstraintSet();
      
      for (LinkConstraint obj : this)
      {
         if (neighbors.contains(obj.getPattern()) || (neighbors.isEmpty() && obj.getPattern() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LinkConstraint object passed as parameter to the Pattern attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Pattern attributes.
    */
   public LinkConstraintSet withPattern(Pattern value)
   {
      for (LinkConstraint obj : this)
      {
         obj.withPattern(value);
      }
      
      return this;
   }



   @Override
   public LinkConstraintSet getNewList(boolean keyValue)
   {
      return new LinkConstraintSet();
   }


   public LinkConstraintSet filter(Condition<LinkConstraint> condition) {
      LinkConstraintSet filterList = new LinkConstraintSet();
      filterItems(filterList, condition);
      return filterList;
   }}
