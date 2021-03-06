/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.replication.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.longList;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.Task;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;

public class LogEntrySet extends SimpleSet<LogEntry>
{


   public LogEntryPO hasLogEntryPO()
   {
      return new LogEntryPO(this.toArray(new LogEntry[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public LogEntrySet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public StringList getStepName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getStepName());
      }
      
      return result;
   }

   public LogEntrySet hasStepName(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getStepName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet hasStepName(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getStepName()) <= 0 && obj.getStepName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet withStepName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setStepName(value);
      }
      
      return this;
   }

   public StringList getExecutedBy()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getExecutedBy());
      }
      
      return result;
   }

   public LogEntrySet hasExecutedBy(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getExecutedBy()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet hasExecutedBy(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getExecutedBy()) <= 0 && obj.getExecutedBy().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet withExecutedBy(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setExecutedBy(value);
      }
      
      return this;
   }

   public longList getTimeStamp()
   {
      longList result = new longList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getTimeStamp());
      }
      
      return result;
   }

   public LogEntrySet hasTimeStamp(long value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value == obj.getTimeStamp())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet hasTimeStamp(long lower, long upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower <= obj.getTimeStamp() && obj.getTimeStamp() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet withTimeStamp(long value)
   {
      for (LogEntry obj : this)
      {
         obj.setTimeStamp(value);
      }
      
      return this;
   }

   public TaskSet getTask()
   {
      TaskSet result = new TaskSet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getTask());
      }
      
      return result;
   }

   public LogEntrySet hasTask(Object value)
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
         if (neighbors.contains(obj.getTask()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LogEntrySet withTask(Task value)
   {
      for (LogEntry obj : this)
      {
         obj.withTask(value);
      }
      
      return this;
   }


   public static final LogEntrySet EMPTY_SET = new LogEntrySet().withFlag(LogEntrySet.READONLY);


   public LogEntryPO filterLogEntryPO()
   {
      return new LogEntryPO(this.toArray(new LogEntry[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.LogEntry";
   }

   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the stepName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterStepName(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getStepName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the stepName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterStepName(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getStepName()) <= 0 && obj.getStepName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the executedBy attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterExecutedBy(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getExecutedBy()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the executedBy attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterExecutedBy(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getExecutedBy()) <= 0 && obj.getExecutedBy().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the timeStamp attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterTimeStamp(long value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value == obj.getTimeStamp())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the timeStamp attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterTimeStamp(long lower, long upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower <= obj.getTimeStamp() && obj.getTimeStamp() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
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


   public LogEntryPO createLogEntryPO()
   {
      return new LogEntryPO(this.toArray(new LogEntry[this.size()]));
   }


   @Override
   public LogEntrySet getNewList(boolean keyValue)
   {
      return new LogEntrySet();
   }

   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the executedBy attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createExecutedByCondition(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getExecutedBy()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the executedBy attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createExecutedByCondition(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getExecutedBy()) <= 0 && obj.getExecutedBy().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the stepName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createStepNameCondition(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getStepName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the stepName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createStepNameCondition(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getStepName()) <= 0 && obj.getStepName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the timeStamp attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createTimeStampCondition(long value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value == obj.getTimeStamp())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the timeStamp attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createTimeStampCondition(long lower, long upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower <= obj.getTimeStamp() && obj.getTimeStamp() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
