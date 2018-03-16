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
import org.sdmlib.storyboards.LogEntry;
import de.uniks.networkparser.interfaces.Condition;
import java.util.Collection;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.storyboards.util.GoalSet;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.util.MikadoLogSet;
import org.sdmlib.storyboards.MikadoLog;

public class LogEntrySet extends SimpleSet<LogEntry>
{
	public Class<?> getTypClass() {
		return LogEntry.class;
	}

   public LogEntrySet()
   {
      // empty
   }

   public LogEntrySet(LogEntry... objects)
   {
      for (LogEntry obj : objects)
      {
         this.add(obj);
      }
   }

   public LogEntrySet(Collection<LogEntry> objects)
   {
      this.addAll(objects);
   }

   public static final LogEntrySet EMPTY_SET = new LogEntrySet().withFlag(LogEntrySet.READONLY);


   public LogEntryPO createLogEntryPO()
   {
      return new LogEntryPO(this.toArray(new LogEntry[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.storyboards.LogEntry";
   }


   @Override
   public LogEntrySet getNewList(boolean keyValue)
   {
      return new LogEntrySet();
   }


   @SuppressWarnings("unchecked")
   public LogEntrySet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LogEntry>)value);
      }
      else if (value != null)
      {
         this.add((LogEntry) value);
      }
      
      return this;
   }
   
   public LogEntrySet without(LogEntry value)
   {
      this.remove(value);
      return this;
   }


   /**
    * Loop through the current set of LogEntry objects and collect a list of the date attribute values. 
    * 
    * @return List of String objects reachable via date attribute
    */
   public ObjectSet getDate()
   {
      ObjectSet result = new ObjectSet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getDate());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the date attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createDateCondition(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getDate()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the date attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createDateCondition(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getDate()) <= 0 && obj.getDate().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and assign value to the date attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LogEntry objects now with new attribute values.
    */
   public LogEntrySet withDate(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setDate(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LogEntry objects and collect a list of the hoursDone attribute values. 
    * 
    * @return List of double objects reachable via hoursDone attribute
    */
   public NumberList getHoursDone()
   {
      NumberList result = new NumberList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getHoursDone());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the hoursDone attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createHoursDoneCondition(double value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value == obj.getHoursDone())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the hoursDone attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createHoursDoneCondition(double lower, double upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower <= obj.getHoursDone() && obj.getHoursDone() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and assign value to the hoursDone attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LogEntry objects now with new attribute values.
    */
   public LogEntrySet withHoursDone(double value)
   {
      for (LogEntry obj : this)
      {
         obj.setHoursDone(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of LogEntry objects and collect a list of the hoursRemaining attribute values. 
    * 
    * @return List of double objects reachable via hoursRemaining attribute
    */
   public NumberList getHoursRemaining()
   {
      NumberList result = new NumberList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getHoursRemaining());
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the hoursRemaining attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createHoursRemainingCondition(double value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value == obj.getHoursRemaining())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the hoursRemaining attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createHoursRemainingCondition(double lower, double upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower <= obj.getHoursRemaining() && obj.getHoursRemaining() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and assign value to the hoursRemaining attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of LogEntry objects now with new attribute values.
    */
   public LogEntrySet withHoursRemaining(double value)
   {
      for (LogEntry obj : this)
      {
         obj.setHoursRemaining(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LogEntry objects and collect a set of the Goal objects reached via goal. 
    * 
    * @return Set of Goal objects reachable via goal
    */
   public GoalSet getGoal()
   {
      GoalSet result = new GoalSet();
      
      for (LogEntry obj : this)
      {
         result.with(obj.getGoal());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LogEntry objects and collect all contained objects with reference goal pointing to the object passed as parameter. 
    * 
    * @param value The object required as goal neighbor of the collected results. 
    * 
    * @return Set of Goal objects referring to value via goal
    */
   public LogEntrySet filterGoal(Object value)
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
      
      LogEntrySet answer = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (neighbors.contains(obj.getGoal()) || (neighbors.isEmpty() && obj.getGoal() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LogEntry object passed as parameter to the Goal attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their Goal attributes.
    */
   public LogEntrySet withGoal(Goal value)
   {
      for (LogEntry obj : this)
      {
         obj.withGoal(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of LogEntry objects and collect a set of the MikadoLog objects reached via parent. 
    * 
    * @return Set of MikadoLog objects reachable via parent
    */
   public MikadoLogSet getParent()
   {
      MikadoLogSet result = new MikadoLogSet();
      
      for (LogEntry obj : this)
      {
         result.with(obj.getParent());
      }
      
      return result;
   }

   /**
    * Loop through the current set of LogEntry objects and collect all contained objects with reference parent pointing to the object passed as parameter. 
    * 
    * @param value The object required as parent neighbor of the collected results. 
    * 
    * @return Set of MikadoLog objects referring to value via parent
    */
   public LogEntrySet filterParent(Object value)
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
      
      LogEntrySet answer = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (neighbors.contains(obj.getParent()) || (neighbors.isEmpty() && obj.getParent() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the LogEntry object passed as parameter to the Parent attribute of each of it. 
    *
    * @param value value
    * @return The original set of ModelType objects now with the new neighbor attached to their Parent attributes.
    */
   public LogEntrySet withParent(MikadoLog value)
   {
      for (LogEntry obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}
