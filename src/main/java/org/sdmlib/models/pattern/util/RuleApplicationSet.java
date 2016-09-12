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
import org.sdmlib.models.pattern.RuleApplication;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.models.pattern.util.PatternSet;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.util.ReachableStateSet;
import org.sdmlib.models.pattern.ReachableState;

public class RuleApplicationSet extends SimpleSet<RuleApplication>
{
	protected Class<?> getTypClass() {
		return RuleApplication.class;
	}

   public RuleApplicationSet()
   {
      // empty
   }

   public RuleApplicationSet(RuleApplication... objects)
   {
      for (RuleApplication obj : objects)
      {
         this.add(obj);
      }
   }

   public RuleApplicationSet(Collection<RuleApplication> objects)
   {
      this.addAll(objects);
   }

   public static final RuleApplicationSet EMPTY_SET = new RuleApplicationSet().withFlag(RuleApplicationSet.READONLY);


   public RuleApplicationPO createRuleApplicationPO()
   {
      return new RuleApplicationPO(this.toArray(new RuleApplication[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.RuleApplication";
   }


   @SuppressWarnings("unchecked")
   public RuleApplicationSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<RuleApplication>)value);
      }
      else if (value != null)
      {
         this.add((RuleApplication) value);
      }
      
      return this;
   }
   
   public RuleApplicationSet without(RuleApplication value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of RuleApplication objects and collect a list of the description attribute values. 
    * 
    * @return List of String objects reachable via description attribute
    */
   public ObjectSet getDescription()
   {
      ObjectSet result = new ObjectSet();
      
      for (RuleApplication obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
   }


   /**
    * Loop through the current set of RuleApplication objects and collect those RuleApplication objects where the description attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of RuleApplication objects that match the parameter
    */
   public RuleApplicationSet createDescriptionCondition(String value)
   {
      RuleApplicationSet result = new RuleApplicationSet();
      
      for (RuleApplication obj : this)
      {
         if (value.equals(obj.getDescription()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of RuleApplication objects and collect those RuleApplication objects where the description attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of RuleApplication objects that match the parameter
    */
   public RuleApplicationSet createDescriptionCondition(String lower, String upper)
   {
      RuleApplicationSet result = new RuleApplicationSet();
      
      for (RuleApplication obj : this)
      {
         if (lower.compareTo(obj.getDescription()) <= 0 && obj.getDescription().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of RuleApplication objects and assign value to the description attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of RuleApplication objects now with new attribute values.
    */
   public RuleApplicationSet withDescription(String value)
   {
      for (RuleApplication obj : this)
      {
         obj.setDescription(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of RuleApplication objects and collect a set of the Pattern objects reached via rule. 
    * 
    * @return Set of Pattern objects reachable via rule
    */
   public PatternSet getRule()
   {
      PatternSet result = new PatternSet();
      
      for (RuleApplication obj : this)
      {
         result.with(obj.getRule());
      }
      
      return result;
   }

   /**
    * Loop through the current set of RuleApplication objects and collect all contained objects with reference rule pointing to the object passed as parameter. 
    * 
    * @param value The object required as rule neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via rule
    */
   public RuleApplicationSet filterRule(Object value)
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
      
      RuleApplicationSet answer = new RuleApplicationSet();
      
      for (RuleApplication obj : this)
      {
         if (neighbors.contains(obj.getRule()) || (neighbors.isEmpty() && obj.getRule() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the RuleApplication object passed as parameter to the Rule attribute of each of it. 
    * @param value Pattern 
    * @return The original set of ModelType objects now with the new neighbor attached to their Rule attributes.
    */
   public RuleApplicationSet withRule(Pattern value)
   {
      for (RuleApplication obj : this)
      {
         obj.withRule(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of RuleApplication objects and collect a set of the ReachableState objects reached via src. 
    * 
    * @return Set of ReachableState objects reachable via src
    */
   public ReachableStateSet getSrc()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (RuleApplication obj : this)
      {
         result.with(obj.getSrc());
      }
      
      return result;
   }

   /**
    * Loop through the current set of RuleApplication objects and collect all contained objects with reference src pointing to the object passed as parameter. 
    * 
    * @param value The object required as src neighbor of the collected results. 
    * 
    * @return Set of ReachableState objects referring to value via src
    */
   public RuleApplicationSet filterSrc(Object value)
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
      
      RuleApplicationSet answer = new RuleApplicationSet();
      
      for (RuleApplication obj : this)
      {
         if (neighbors.contains(obj.getSrc()) || (neighbors.isEmpty() && obj.getSrc() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the RuleApplication object passed as parameter to the Src attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Src attributes.
    */
   public RuleApplicationSet withSrc(ReachableState value)
   {
      for (RuleApplication obj : this)
      {
         obj.withSrc(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of RuleApplication objects and collect a set of the ReachableState objects reached via tgt. 
    * 
    * @return Set of ReachableState objects reachable via tgt
    */
   public ReachableStateSet getTgt()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (RuleApplication obj : this)
      {
         result.with(obj.getTgt());
      }
      
      return result;
   }

   /**
    * Loop through the current set of RuleApplication objects and collect all contained objects with reference tgt pointing to the object passed as parameter. 
    * 
    * @param value The object required as tgt neighbor of the collected results. 
    * 
    * @return Set of ReachableState objects referring to value via tgt
    */
   public RuleApplicationSet filterTgt(Object value)
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
      
      RuleApplicationSet answer = new RuleApplicationSet();
      
      for (RuleApplication obj : this)
      {
         if (neighbors.contains(obj.getTgt()) || (neighbors.isEmpty() && obj.getTgt() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the RuleApplication object passed as parameter to the Tgt attribute of each of it. 
    * @param value ReachableState
    * @return The original set of ModelType objects now with the new neighbor attached to their Tgt attributes.
    */
   public RuleApplicationSet withTgt(ReachableState value)
   {
      for (RuleApplication obj : this)
      {
         obj.withTgt(value);
      }
      
      return this;
   }

}
