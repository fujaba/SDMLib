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
   
package org.sdmlib.models.taskflows.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.taskflows.LogEntry;
import org.sdmlib.models.taskflows.Logger;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.list.StringList;
import org.sdmlib.models.taskflows.util.LoggerSet;

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

   public StringList getNodeName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getNodeName());
      }
      
      return result;
   }

   public LogEntrySet hasNodeName(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getNodeName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet hasNodeName(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getNodeName()) <= 0 && obj.getNodeName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet withNodeName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setNodeName(value);
      }
      
      return this;
   }

   public StringList getTaskName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getTaskName());
      }
      
      return result;
   }

   public LogEntrySet hasTaskName(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getTaskName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet hasTaskName(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getTaskName()) <= 0 && obj.getTaskName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LogEntrySet withTaskName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setTaskName(value);
      }
      
      return this;
   }

   public LoggerSet getLogger()
   {
      LoggerSet result = new LoggerSet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getLogger());
      }
      
      return result;
   }

   public LogEntrySet hasLogger(Object value)
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
         if (neighbors.contains(obj.getLogger()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LogEntrySet withLogger(Logger value)
   {
      for (LogEntry obj : this)
      {
         obj.withLogger(value);
      }
      
      return this;
   }

   public LogEntrySet getChildren()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         result.addAll(obj.getChildren());
      }
      
      return result;
   }

   public LogEntrySet hasChildren(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getChildren()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public LogEntrySet getChildrenTransitive()
   {
      LogEntrySet todo = new LogEntrySet().with(this);
      
      LogEntrySet result = new LogEntrySet();
      
      while ( ! todo.isEmpty())
      {
         LogEntry current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getChildren().minus(result));
         }
      }
      
      return result;
   }

   public LogEntrySet withChildren(LogEntry value)
   {
      for (LogEntry obj : this)
      {
         obj.withChildren(value);
      }
      
      return this;
   }

   public LogEntrySet withoutChildren(LogEntry value)
   {
      for (LogEntry obj : this)
      {
         obj.withoutChildren(value);
      }
      
      return this;
   }

   public LogEntrySet getParent()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public LogEntrySet hasParent(Object value)
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
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public LogEntrySet getParentTransitive()
   {
      LogEntrySet todo = new LogEntrySet().with(this);
      
      LogEntrySet result = new LogEntrySet();
      
      while ( ! todo.isEmpty())
      {
         LogEntry current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getParent()))
            {
               todo.with(current.getParent());
            }
         }
      }
      
      return result;
   }

   public LogEntrySet withParent(LogEntry value)
   {
      for (LogEntry obj : this)
      {
         obj.withParent(value);
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
      return "org.sdmlib.models.taskflows.LogEntry";
   }

   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the nodeName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterNodeName(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getNodeName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the nodeName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterNodeName(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getNodeName()) <= 0 && obj.getNodeName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the taskName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterTaskName(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getTaskName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the taskName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet filterTaskName(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getTaskName()) <= 0 && obj.getTaskName().compareTo(upper) <= 0)
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
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the nodeName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createNodeNameCondition(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getNodeName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the nodeName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createNodeNameCondition(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getNodeName()) <= 0 && obj.getNodeName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the taskName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createTaskNameCondition(String value)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (value.equals(obj.getTaskName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of LogEntry objects and collect those LogEntry objects where the taskName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of LogEntry objects that match the parameter
    */
   public LogEntrySet createTaskNameCondition(String lower, String upper)
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         if (lower.compareTo(obj.getTaskName()) <= 0 && obj.getTaskName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
