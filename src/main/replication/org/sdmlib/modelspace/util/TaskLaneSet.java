/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.modelspace.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.modelspace.Task;
import org.sdmlib.modelspace.TaskBoard;
import org.sdmlib.modelspace.TaskLane;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.modelspace.util.TaskBoardSet;
import org.sdmlib.modelspace.util.TaskSet;

public class TaskLaneSet extends SimpleSet<TaskLane>
{

   public static final TaskLaneSet EMPTY_SET = new TaskLaneSet().withFlag(TaskLaneSet.READONLY);


   public TaskLanePO hasTaskLanePO()
   {
      return new TaskLanePO(this.toArray(new TaskLane[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.modelspace.TaskLane";
   }


   @SuppressWarnings("unchecked")
   public TaskLaneSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TaskLane>)value);
      }
      else if (value != null)
      {
         this.add((TaskLane) value);
      }
      
      return this;
   }
   
   public TaskLaneSet without(TaskLane value)
   {
      this.remove(value);
      return this;
   }

   public StringList getHostName()
   {
      StringList result = new StringList();
      
      for (TaskLane obj : this)
      {
         result.add(obj.getHostName());
      }
      
      return result;
   }

   public TaskLaneSet hasHostName(String value)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TaskLaneSet hasHostName(String lower, String upper)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TaskLaneSet withHostName(String value)
   {
      for (TaskLane obj : this)
      {
         obj.setHostName(value);
      }
      
      return this;
   }

   public longList getPortNo()
   {
      longList result = new longList();
      
      for (TaskLane obj : this)
      {
         result.add(obj.getPortNo());
      }
      
      return result;
   }

   public TaskLaneSet hasPortNo(long value)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (value == obj.getPortNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TaskLaneSet hasPortNo(long lower, long upper)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (lower <= obj.getPortNo() && obj.getPortNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TaskLaneSet withPortNo(long value)
   {
      for (TaskLane obj : this)
      {
         obj.setPortNo(value);
      }
      
      return this;
   }

   public TaskBoardSet getBoard()
   {
      TaskBoardSet result = new TaskBoardSet();
      
      for (TaskLane obj : this)
      {
         result.add(obj.getBoard());
      }
      
      return result;
   }

   public TaskLaneSet hasBoard(Object value)
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
      
      TaskLaneSet answer = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (neighbors.contains(obj.getBoard()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TaskLaneSet withBoard(TaskBoard value)
   {
      for (TaskLane obj : this)
      {
         obj.withBoard(value);
      }
      
      return this;
   }

   public TaskSet getTasks()
   {
      TaskSet result = new TaskSet();
      
      for (TaskLane obj : this)
      {
         result.addAll(obj.getTasks());
      }
      
      return result;
   }

   public TaskLaneSet hasTasks(Object value)
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
      
      TaskLaneSet answer = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TaskLaneSet withTasks(Task value)
   {
      for (TaskLane obj : this)
      {
         obj.withTasks(value);
      }
      
      return this;
   }

   public TaskLaneSet withoutTasks(Task value)
   {
      for (TaskLane obj : this)
      {
         obj.withoutTasks(value);
      }
      
      return this;
   }

   public TaskSet getMyRequests()
   {
      TaskSet result = new TaskSet();
      
      for (TaskLane obj : this)
      {
         result.addAll(obj.getMyRequests());
      }
      
      return result;
   }

   public TaskLaneSet hasMyRequests(Object value)
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
      
      TaskLaneSet answer = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getMyRequests()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TaskLaneSet withMyRequests(Task value)
   {
      for (TaskLane obj : this)
      {
         obj.withMyRequests(value);
      }
      
      return this;
   }

   public TaskLaneSet withoutMyRequests(Task value)
   {
      for (TaskLane obj : this)
      {
         obj.withoutMyRequests(value);
      }
      
      return this;
   }



   public TaskLanePO filterTaskLanePO()
   {
      return new TaskLanePO(this.toArray(new TaskLane[this.size()]));
   }

   /**
    * Loop through the current set of TaskLane objects and collect those TaskLane objects where the hostName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskLane objects that match the parameter
    */
   public TaskLaneSet filterHostName(String value)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskLane objects and collect those TaskLane objects where the hostName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TaskLane objects that match the parameter
    */
   public TaskLaneSet filterHostName(String lower, String upper)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskLane objects and collect those TaskLane objects where the portNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskLane objects that match the parameter
    */
   public TaskLaneSet filterPortNo(long value)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (value == obj.getPortNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskLane objects and collect those TaskLane objects where the portNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TaskLane objects that match the parameter
    */
   public TaskLaneSet filterPortNo(long lower, long upper)
   {
      TaskLaneSet result = new TaskLaneSet();
      
      for (TaskLane obj : this)
      {
         if (lower <= obj.getPortNo() && obj.getPortNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
