/*
   Copyright (c) 2018 zuendorf
   
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
   
package org.sdmlib.storyboards.util;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.storyboards.Goal;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.NumberList;
import java.util.Collections;
import org.sdmlib.storyboards.util.GoalSet;

public class GoalSet extends SimpleSet<Goal>
{
	public Class<?> getTypClass() {
		return Goal.class;
	}

   public GoalSet()
   {
      // empty
   }

   public GoalSet(Goal... objects)
   {
      for (Goal obj : objects)
      {
         this.add(obj);
      }
   }

   public GoalSet(Collection<Goal> objects)
   {
      this.addAll(objects);
   }

   public static final GoalSet EMPTY_SET = new GoalSet().withFlag(GoalSet.READONLY);


   public GoalPO createGoalPO()
   {
      return new GoalPO(this.toArray(new Goal[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.storyboards.Goal";
   }


   @Override
   public GoalSet getNewList(boolean keyValue)
   {
      return new GoalSet();
   }


   @SuppressWarnings("unchecked")
   public GoalSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Goal>)value);
      }
      else if (value != null)
      {
         this.add((Goal) value);
      }
      
      return this;
   }
   
   public GoalSet without(Goal value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of Goal objects and collect a list of the description attribute values. 
    * 
    * @return List of String objects reachable via description attribute
    */
   public ObjectSet getDescription()
   {
      ObjectSet result = new ObjectSet();
      
      for (Goal obj : this)
      {
         result.add(obj.getDescription());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and collect those Goal objects where the description attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Goal objects that match the parameter
    */
   public GoalSet createDescriptionCondition(String value)
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         if (value.equals(obj.getDescription()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and collect those Goal objects where the description attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Goal objects that match the parameter
    */
   public GoalSet createDescriptionCondition(String lower, String upper)
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         if (lower.compareTo(obj.getDescription()) <= 0 && obj.getDescription().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and assign value to the description attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Goal objects now with new attribute values.
    */
   public GoalSet withDescription(String value)
   {
      for (Goal obj : this)
      {
         obj.setDescription(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Goal objects and collect a list of the hoursDone attribute values. 
    * 
    * @return List of double objects reachable via hoursDone attribute
    */
   public NumberList getHoursDone()
   {
      NumberList result = new NumberList();
      
      for (Goal obj : this)
      {
         result.add(obj.getHoursDone());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and collect those Goal objects where the hoursDone attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Goal objects that match the parameter
    */
   public GoalSet createHoursDoneCondition(double value)
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         if (value == obj.getHoursDone())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and collect those Goal objects where the hoursDone attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Goal objects that match the parameter
    */
   public GoalSet createHoursDoneCondition(double lower, double upper)
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         if (lower <= obj.getHoursDone() && obj.getHoursDone() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and assign value to the hoursDone attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Goal objects now with new attribute values.
    */
   public GoalSet withHoursDone(double value)
   {
      for (Goal obj : this)
      {
         obj.setHoursDone(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Goal objects and collect a list of the hoursTodo attribute values. 
    * 
    * @return List of double objects reachable via hoursTodo attribute
    */
   public NumberList getHoursTodo()
   {
      NumberList result = new NumberList();
      
      for (Goal obj : this)
      {
         result.add(obj.getHoursTodo());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and collect those Goal objects where the hoursTodo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Goal objects that match the parameter
    */
   public GoalSet createHoursTodoCondition(double value)
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         if (value == obj.getHoursTodo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and collect those Goal objects where the hoursTodo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Goal objects that match the parameter
    */
   public GoalSet createHoursTodoCondition(double lower, double upper)
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         if (lower <= obj.getHoursTodo() && obj.getHoursTodo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Goal objects and assign value to the hoursTodo attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Goal objects now with new attribute values.
    */
   public GoalSet withHoursTodo(double value)
   {
      for (Goal obj : this)
      {
         obj.setHoursTodo(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Goal objects and collect a set of the Goal objects reached via parents. 
    * 
    * @return Set of Goal objects reachable via parents
    */
   public GoalSet getParents()
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         result.with(obj.getParents());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Goal objects and collect all contained objects with reference parents pointing to the object passed as parameter. 
    * 
    * @param value The object required as parents neighbor of the collected results. 
    * 
    * @return Set of Goal objects referring to value via parents
    */
   public GoalSet filterParents(Object value)
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
      
      GoalSet answer = new GoalSet();
      
      for (Goal obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getParents()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow parents reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Goal objects reachable via parents transitively (including the start set)
    */
   public GoalSet getParentsTransitive()
   {
      GoalSet todo = new GoalSet().with(this);
      
      GoalSet result = new GoalSet();
      
      while ( ! todo.isEmpty())
      {
         Goal current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getParents()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Goal object passed as parameter to the Parents attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Parents attributes.
    */
   public GoalSet withParents(Goal value)
   {
      for (Goal obj : this)
      {
         obj.withParents(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Goal object passed as parameter from the Parents attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GoalSet withoutParents(Goal value)
   {
      for (Goal obj : this)
      {
         obj.withoutParents(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Goal objects and collect a set of the Goal objects reached via preGoals. 
    * 
    * @return Set of Goal objects reachable via preGoals
    */
   public GoalSet getPreGoals()
   {
      GoalSet result = new GoalSet();
      
      for (Goal obj : this)
      {
         result.with(obj.getPreGoals());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Goal objects and collect all contained objects with reference preGoals pointing to the object passed as parameter. 
    * 
    * @param value The object required as preGoals neighbor of the collected results. 
    * 
    * @return Set of Goal objects referring to value via preGoals
    */
   public GoalSet filterPreGoals(Object value)
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
      
      GoalSet answer = new GoalSet();
      
      for (Goal obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPreGoals()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow preGoals reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Goal objects reachable via preGoals transitively (including the start set)
    */
   public GoalSet getPreGoalsTransitive()
   {
      GoalSet todo = new GoalSet().with(this);
      
      GoalSet result = new GoalSet();
      
      while ( ! todo.isEmpty())
      {
         Goal current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getPreGoals()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Goal object passed as parameter to the PreGoals attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their PreGoals attributes.
    */
   public GoalSet withPreGoals(Goal value)
   {
      for (Goal obj : this)
      {
         obj.withPreGoals(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Goal object passed as parameter from the PreGoals attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public GoalSet withoutPreGoals(Goal value)
   {
      for (Goal obj : this)
      {
         obj.withoutPreGoals(value);
      }
      
      return this;
   }

}
