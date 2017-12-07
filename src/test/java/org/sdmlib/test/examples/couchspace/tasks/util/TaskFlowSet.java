/*
   Copyright (c) 2016 zuendorf
   
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
   
package org.sdmlib.test.examples.couchspace.tasks.util;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.StringList;

public class TaskFlowSet extends SDMSet<TaskFlow>
{

   public static final TaskFlowSet EMPTY_SET = new TaskFlowSet().withFlag(TaskFlowSet.READONLY);


   public TaskFlowPO filterTaskFlowPO()
   {
      return new TaskFlowPO(this.toArray(new TaskFlow[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.couchspace.tasks.TaskFlow";
   }


   @SuppressWarnings("unchecked")
   public TaskFlowSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TaskFlow>)value);
      }
      else if (value != null)
      {
         this.add((TaskFlow) value);
      }
      
      return this;
   }
   
   public TaskFlowSet without(TaskFlow value)
   {
      this.remove(value);
      return this;
   }

   /**
    * Loop through the current set of TaskFlow objects and collect a list of the title attribute values. 
    * 
    * @return List of String objects reachable via title attribute
    */
   public StringList getTitle()
   {
      StringList result = new StringList();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getTitle());
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the title attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet filterTitle(String value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value.equals(obj.getTitle()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the title attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet filterTitle(String lower, String upper)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (lower.compareTo(obj.getTitle()) <= 0 && obj.getTitle().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and assign value to the title attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of TaskFlow objects now with new attribute values.
    */
   public TaskFlowSet withTitle(String value)
   {
      for (TaskFlow obj : this)
      {
         obj.setTitle(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of TaskFlow objects and collect a set of the Task objects reached via tasks. 
    * 
    * @return Set of Task objects reachable via tasks
    */
   public TaskSet getTasks()
   {
      TaskSet result = new TaskSet();
      
      for (TaskFlow obj : this)
      {
         result.with(obj.getTasks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of TaskFlow objects and collect all contained objects with reference tasks pointing to the object passed as parameter. 
    * 
    * @param value The object required as tasks neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via tasks
    */
   public TaskFlowSet filterTasks(Object value)
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
      
      TaskFlowSet answer = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the TaskFlow object passed as parameter to the Tasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Tasks attributes.
    */
   public TaskFlowSet withTasks(Task value)
   {
      for (TaskFlow obj : this)
      {
         obj.withTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the TaskFlow object passed as parameter from the Tasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskFlowSet withoutTasks(Task value)
   {
      for (TaskFlow obj : this)
      {
         obj.withoutTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of TaskFlow objects and collect a set of the Task objects reached via firstTasks. 
    * 
    * @return Set of Task objects reachable via firstTasks
    */
   public TaskSet getFirstTasks()
   {
      TaskSet result = new TaskSet();
      
      for (TaskFlow obj : this)
      {
         result.with(obj.getFirstTasks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of TaskFlow objects and collect all contained objects with reference firstTasks pointing to the object passed as parameter. 
    * 
    * @param value The object required as firstTasks neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via firstTasks
    */
   public TaskFlowSet filterFirstTasks(Object value)
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
      
      TaskFlowSet answer = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getFirstTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the TaskFlow object passed as parameter to the FirstTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their FirstTasks attributes.
    */
   public TaskFlowSet withFirstTasks(Task value)
   {
      for (TaskFlow obj : this)
      {
         obj.withFirstTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the TaskFlow object passed as parameter from the FirstTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskFlowSet withoutFirstTasks(Task value)
   {
      for (TaskFlow obj : this)
      {
         obj.withoutFirstTasks(value);
      }
      
      return this;
   }


   public TaskFlowSet()
   {
      // empty
   }

   public TaskFlowSet(TaskFlow... objects)
   {
      for (TaskFlow obj : objects)
      {
         this.add(obj);
      }
   }

   public TaskFlowSet(Collection<TaskFlow> objects)
   {
      this.addAll(objects);
   }


   public TaskFlowPO createTaskFlowPO()
   {
      return new TaskFlowPO(this.toArray(new TaskFlow[this.size()]));
   }


   @Override
   public TaskFlowSet getNewList(boolean keyValue)
   {
      return new TaskFlowSet();
   }

   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the title attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet createTitleCondition(String value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value.equals(obj.getTitle()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the title attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet createTitleCondition(String lower, String upper)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (lower.compareTo(obj.getTitle()) <= 0 && obj.getTitle().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
