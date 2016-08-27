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
import org.sdmlib.models.pattern.ReachableState;
import java.util.Collection;
import de.uniks.networkparser.list.NumberList;
import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.models.pattern.util.ReachabilityGraphSet;
import org.sdmlib.models.pattern.ReachabilityGraph;
import java.util.Collections;
import org.sdmlib.models.pattern.util.RuleApplicationSet;
import org.sdmlib.models.pattern.RuleApplication;

public class ReachableStateSet extends SimpleSet<ReachableState>
{
	protected Class<?> getTypClass() {
		return ReachableState.class;
	}

   public ReachableStateSet()
   {
      // empty
   }

   public ReachableStateSet(ReachableState... objects)
   {
      for (ReachableState obj : objects)
      {
         this.add(obj);
      }
   }

   public ReachableStateSet(Collection<ReachableState> objects)
   {
      this.addAll(objects);
   }

   public static final ReachableStateSet EMPTY_SET = new ReachableStateSet().withFlag(ReachableStateSet.READONLY);


   public ReachableStatePO createReachableStatePO()
   {
      return new ReachableStatePO(this.toArray(new ReachableState[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.ReachableState";
   }


   @SuppressWarnings("unchecked")
   public ReachableStateSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReachableState>)value);
      }
      else if (value != null)
      {
         this.add((ReachableState) value);
      }
      
      return this;
   }
   
   public ReachableStateSet without(ReachableState value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of ReachableState objects and collect a list of the number attribute values. 
    * 
    * @return List of long objects reachable via number attribute
    */
   public NumberList getNumber()
   {
      NumberList result = new NumberList();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getNumber());
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and collect those ReachableState objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ReachableState objects that match the parameter
    */
   public ReachableStateSet createNumberCondition(long value)
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if (value == obj.getNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and collect those ReachableState objects where the number attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ReachableState objects that match the parameter
    */
   public ReachableStateSet createNumberCondition(long lower, long upper)
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if (lower <= obj.getNumber() && obj.getNumber() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and assign value to the number attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of ReachableState objects now with new attribute values.
    */
   public ReachableStateSet withNumber(long value)
   {
      for (ReachableState obj : this)
      {
         obj.setNumber(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of ReachableState objects and collect a list of the metricValue attribute values. 
    * 
    * @return List of double objects reachable via metricValue attribute
    */
   public NumberList getMetricValue()
   {
      NumberList result = new NumberList();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getMetricValue());
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and collect those ReachableState objects where the metricValue attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ReachableState objects that match the parameter
    */
   public ReachableStateSet createMetricValueCondition(double value)
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if (value == obj.getMetricValue())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and collect those ReachableState objects where the metricValue attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of ReachableState objects that match the parameter
    */
   public ReachableStateSet createMetricValueCondition(double lower, double upper)
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if (lower <= obj.getMetricValue() && obj.getMetricValue() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and assign value to the metricValue attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of ReachableState objects now with new attribute values.
    */
   public ReachableStateSet withMetricValue(double value)
   {
      for (ReachableState obj : this)
      {
         obj.setMetricValue(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of ReachableState objects and collect a list of the graphRoot attribute values. 
    * 
    * @return List of Object objects reachable via graphRoot attribute
    */
   public ObjectSet getGraphRoot()
   {
      ObjectSet result = new ObjectSet();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getGraphRoot());
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and collect those ReachableState objects where the graphRoot attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ReachableState objects that match the parameter
    */
   public ReachableStateSet createGraphRootCondition(Object value)
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if (value == obj.getGraphRoot())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of ReachableState objects and assign value to the graphRoot attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of ReachableState objects now with new attribute values.
    */
   public ReachableStateSet withGraphRoot(Object value)
   {
      for (ReachableState obj : this)
      {
         obj.setGraphRoot(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ReachableState objects and collect a set of the ReachabilityGraph objects reached via parent. 
    * 
    * @return Set of ReachabilityGraph objects reachable via parent
    */
   public ReachabilityGraphSet getParent()
   {
      ReachabilityGraphSet result = new ReachabilityGraphSet();
      
      for (ReachableState obj : this)
      {
         result.with(obj.getParent());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ReachableState objects and collect all contained objects with reference parent pointing to the object passed as parameter. 
    * 
    * @param value The object required as parent neighbor of the collected results. 
    * 
    * @return Set of ReachabilityGraph objects referring to value via parent
    */
   public ReachableStateSet filterParent(Object value)
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
      
      ReachableStateSet answer = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if (neighbors.contains(obj.getParent()) || (neighbors.isEmpty() && obj.getParent() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ReachableState object passed as parameter to the Parent attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Parent attributes.
    */
   public ReachableStateSet withParent(ReachabilityGraph value)
   {
      for (ReachableState obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ReachableState objects and collect a set of the RuleApplication objects reached via ruleapplications. 
    * 
    * @return Set of RuleApplication objects reachable via ruleapplications
    */
   public RuleApplicationSet getRuleapplications()
   {
      RuleApplicationSet result = new RuleApplicationSet();
      
      for (ReachableState obj : this)
      {
         result.with(obj.getRuleapplications());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ReachableState objects and collect all contained objects with reference ruleapplications pointing to the object passed as parameter. 
    * 
    * @param value The object required as ruleapplications neighbor of the collected results. 
    * 
    * @return Set of RuleApplication objects referring to value via ruleapplications
    */
   public ReachableStateSet filterRuleapplications(Object value)
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
      
      ReachableStateSet answer = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRuleapplications()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ReachableState object passed as parameter to the Ruleapplications attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Ruleapplications attributes.
    */
   public ReachableStateSet withRuleapplications(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withRuleapplications(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ReachableState object passed as parameter from the Ruleapplications attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ReachableStateSet withoutRuleapplications(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withoutRuleapplications(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ReachableState objects and collect a set of the RuleApplication objects reached via resultOf. 
    * 
    * @return Set of RuleApplication objects reachable via resultOf
    */
   public RuleApplicationSet getResultOf()
   {
      RuleApplicationSet result = new RuleApplicationSet();
      
      for (ReachableState obj : this)
      {
         result.with(obj.getResultOf());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ReachableState objects and collect all contained objects with reference resultOf pointing to the object passed as parameter. 
    * 
    * @param value The object required as resultOf neighbor of the collected results. 
    * 
    * @return Set of RuleApplication objects referring to value via resultOf
    */
   public ReachableStateSet filterResultOf(Object value)
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
      
      ReachableStateSet answer = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getResultOf()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ReachableState object passed as parameter to the ResultOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their ResultOf attributes.
    */
   public ReachableStateSet withResultOf(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withResultOf(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ReachableState object passed as parameter from the ResultOf attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ReachableStateSet withoutResultOf(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withoutResultOf(value);
      }
      
      return this;
   }

}
