/*
   Copyright (c) 2013 zuendorf 
   
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
import java.util.LinkedHashSet;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.models.pattern.util.ReachabilityGraphSet;
import org.sdmlib.models.pattern.util.RuleApplicationSet;
import org.sdmlib.models.modelsets.doubleList;

public class ReachableStateSet extends LinkedHashSet<ReachableState> implements org.sdmlib.models.modelsets.ModelSet
{
   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ReachableState elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.ReachableState";
   }


   public ReachabilityGraphSet getParent()
   {
      ReachabilityGraphSet result = new ReachabilityGraphSet();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public ReachableStateSet withParent(ReachabilityGraph value)
   {
      for (ReachableState obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public ReachableState first()
   {
      for (ReachableState s : this)
      {
         return s;
      }
      
      return null;
   }

   public ObjectSet getGraphRoot()
   {
      ObjectSet result = new ObjectSet();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getGraphRoot());
      }
      
      return result;
   }

   public ReachableStateSet withGraphRoot(Object value)
   {
      for (ReachableState obj : this)
      {
         obj.setGraphRoot(value);
      }
      
      return this;
   }

   public longList getNumber()
   {
      longList result = new longList();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getNumber());
      }
      
      return result;
   }

   public ReachableStateSet withNumber(int value)
   {
      for (ReachableState obj : this)
      {
         obj.setNumber(value);
      }
      
      return this;
   }
   
   public ReachableStateSet filterNumber(int lower, int upper)
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

   public RuleApplicationSet getRuleapplications()
   {
      RuleApplicationSet result = new RuleApplicationSet();
      
      for (ReachableState obj : this)
      {
         result.addAll(obj.getRuleapplications());
      }
      
      return result;
   }

   public ReachableStateSet withRuleapplications(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withRuleapplications(value);
      }
      
      return this;
   }

   public ReachableStateSet withoutRuleapplications(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withoutRuleapplications(value);
      }
      
      return this;
   }

   public RuleApplicationSet getResultOf()
   {
      RuleApplicationSet result = new RuleApplicationSet();
      
      for (ReachableState obj : this)
      {
         result.addAll(obj.getResultOf());
      }
      
      return result;
   }

   public ReachableStateSet withResultOf(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withResultOf(value);
      }
      
      return this;
   }

   public ReachableStateSet withoutResultOf(RuleApplication value)
   {
      for (ReachableState obj : this)
      {
         obj.withoutResultOf(value);
      }
      
      return this;
   }

   public ReachableStateSet withNumber(long value)
   {
      for (ReachableState obj : this)
      {
         obj.setNumber(value);
      }
      
      return this;
   }



   public ReachableStatePO startModelPattern()
   {
      return new ReachableStatePO(this.toArray(new ReachableState[this.size()]));
   }


   public ReachableStateSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<? extends ReachableState>)value);
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



   public ReachableStatePO hasReachableStatePO()
   {
      return new ReachableStatePO(this.toArray(new ReachableState[this.size()]));
   }

   public static final ReachableStateSet EMPTY_SET = new ReachableStateSet(); // .withFlag(ReachableStateSet.READONLY);
   public ReachableStateSet hasNumber(long value)
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

   public ReachableStateSet hasNumber(long lower, long upper)
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

   public ReachableStateSet hasGraphRoot(Object value)
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



   public ReachableStatePO filterReachableStatePO()
   {
      return new ReachableStatePO(this.toArray(new ReachableState[this.size()]));
   }

   /**
    * Loop through the current set of ReachableState objects and collect those ReachableState objects where the number attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ReachableState objects that match the parameter
    */
   public ReachableStateSet filterNumber(long value)
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
   public ReachableStateSet filterNumber(long lower, long upper)
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
    * Loop through the current set of ReachableState objects and collect those ReachableState objects where the graphRoot attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of ReachableState objects that match the parameter
    */
   public ReachableStateSet filterGraphRoot(Object value)
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
    * Loop through the current set of ReachableState objects and collect a list of the metricValue attribute values. 
    * 
    * @return List of double objects reachable via metricValue attribute
    */
   public doubleList getMetricValue()
   {
      doubleList result = new doubleList();
      
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
   public ReachableStateSet filterMetricValue(double value)
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
   public ReachableStateSet filterMetricValue(double lower, double upper)
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

}
