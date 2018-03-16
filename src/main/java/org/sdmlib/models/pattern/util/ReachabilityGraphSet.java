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

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class ReachabilityGraphSet extends SimpleSet<ReachabilityGraph>
{
   public Class<?> getTypClass() {
		return ReachabilityGraph.class;
	}

   public ReachabilityGraphSet()
   {
      // empty
   }

   public ReachabilityGraphSet(ReachabilityGraph... objects)
   {
      for (ReachabilityGraph obj : objects)
      {
         this.add(obj);
      }
   }

   public ReachabilityGraphSet(Collection<ReachabilityGraph> objects)
   {
      this.addAll(objects);
   }

   public static final ReachabilityGraphSet EMPTY_SET = new ReachabilityGraphSet().withFlag(ReachabilityGraphSet.READONLY);


   public ReachabilityGraphPO createReachabilityGraphPO()
   {
      return new ReachabilityGraphPO(this.toArray(new ReachabilityGraph[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.ReachabilityGraph";
   }


   @SuppressWarnings("unchecked")
   public ReachabilityGraphSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReachabilityGraph>)value);
      }
      else if (value != null)
      {
         this.add((ReachabilityGraph) value);
      }
      
      return this;
   }
   
   public ReachabilityGraphSet without(ReachabilityGraph value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect a set of the Pattern objects reached via rules. 
    * 
    * @return Set of Pattern objects reachable via rules
    */
   public PatternSet getRules()
   {
      PatternSet result = new PatternSet();
      
      for (ReachabilityGraph obj : this)
      {
         result.with(obj.getRules());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect all contained objects with reference rules pointing to the object passed as parameter. 
    * 
    * @param value The object required as rules neighbor of the collected results. 
    * 
    * @return Set of Pattern objects referring to value via rules
    */
   public ReachabilityGraphSet filterRules(Object value)
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
      
      ReachabilityGraphSet answer = new ReachabilityGraphSet();
      
      for (ReachabilityGraph obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getRules()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ReachabilityGraph object passed as parameter to the Rules attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their Rules attributes.
    */
   public ReachabilityGraphSet withRules(Pattern value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withRules(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ReachabilityGraph object passed as parameter from the Rules attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ReachabilityGraphSet withoutRules(Pattern value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withoutRules(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect a set of the ReachableState objects reached via finalStates. 
    * 
    * @return Set of ReachableState objects reachable via finalStates
    */
   public ReachableStateSet getFinalStates()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachabilityGraph obj : this)
      {
         result.with(obj.getFinalStates());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect all contained objects with reference finalStates pointing to the object passed as parameter. 
    * 
    * @param value The object required as finalStates neighbor of the collected results. 
    * 
    * @return Set of ReachableState objects referring to value via finalStates
    */
   public ReachabilityGraphSet filterFinalStates(Object value)
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
      
      ReachabilityGraphSet answer = new ReachabilityGraphSet();
      
      for (ReachabilityGraph obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFinalStates()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ReachabilityGraph object passed as parameter to the FinalStates attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their FinalStates attributes.
    */
   public ReachabilityGraphSet withFinalStates(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withFinalStates(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ReachabilityGraph object passed as parameter from the FinalStates attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ReachabilityGraphSet withoutFinalStates(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withoutFinalStates(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect a set of the ReachableState objects reached via states. 
    * 
    * @return Set of ReachableState objects reachable via states
    */
   public ReachableStateSet getStates()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachabilityGraph obj : this)
      {
         result.with(obj.getStates());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect all contained objects with reference states pointing to the object passed as parameter. 
    * 
    * @param value The object required as states neighbor of the collected results. 
    * 
    * @return Set of ReachableState objects referring to value via states
    */
   public ReachabilityGraphSet filterStates(Object value)
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
      
      ReachabilityGraphSet answer = new ReachabilityGraphSet();
      
      for (ReachabilityGraph obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getStates()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ReachabilityGraph object passed as parameter to the States attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their States attributes.
    */
   public ReachabilityGraphSet withStates(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withStates(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ReachabilityGraph object passed as parameter from the States attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ReachabilityGraphSet withoutStates(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withoutStates(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect a set of the ReachableState objects reached via todo. 
    * 
    * @return Set of ReachableState objects reachable via todo
    */
   public ReachableStateSet getTodo()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachabilityGraph obj : this)
      {
         result.with(obj.getTodo());
      }
      
      return result;
   }

   /**
    * Loop through the current set of ReachabilityGraph objects and collect all contained objects with reference todo pointing to the object passed as parameter. 
    * 
    * @param value The object required as todo neighbor of the collected results. 
    * 
    * @return Set of ReachableState objects referring to value via todo
    */
   public ReachabilityGraphSet filterTodo(Object value)
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
      
      ReachabilityGraphSet answer = new ReachabilityGraphSet();
      
      for (ReachabilityGraph obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTodo()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the ReachabilityGraph object passed as parameter to the Todo attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their Todo attributes.
    */
   public ReachabilityGraphSet withTodo(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withTodo(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the ReachabilityGraph object passed as parameter from the Todo attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public ReachabilityGraphSet withoutTodo(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withoutTodo(value);
      }
      
      return this;
   }



   @Override
   public ReachabilityGraphSet getNewList(boolean keyValue)
   {
      return new ReachabilityGraphSet();
   }
}