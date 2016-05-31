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
   
package org.sdmlib.simple.model.association_k.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.simple.model.association_k.Task;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.simple.model.association_k.util.TaskSet;

public class TaskSet extends SDMSet<Task>
{

   public TaskSet()
   {
      // empty
   }

   public TaskSet(Task... objects)
   {
      for (Task obj : objects)
      {
         this.add(obj);
      }
   }

   public TaskSet(Collection<Task> objects)
   {
      this.addAll(objects);
   }

   public static final TaskSet EMPTY_SET = new TaskSet().withFlag(TaskSet.READONLY);


   public TaskPO filterTaskPO()
   {
      return new TaskPO(this.toArray(new Task[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.simple.model.association_k.Task";
   }


   @SuppressWarnings("unchecked")
   public TaskSet with(Object value)
   {
      if (value == null)
      {
         return this;
      }
      else if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Task>)value);
      }
      else if (value != null)
      {
         this.add((Task) value);
      }
      
      return this;
   }
   
   public TaskSet without(Task value)
   {
      this.remove(value);
      return this;
   }

   @Override
   public TaskSet filter(Condition<Task> newValue) {
      TaskSet filterList = new TaskSet();
      filterItems(filterList, newValue);
      return filterList;
   }

   /**
    * Loop through the current set of Task objects and collect a list of the name attribute values. 
    * 
    * @return List of String objects reachable via name attribute
    */
   public StringList getName()
   {
      StringList result = new StringList();
      
      for (Task obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the name attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterName(String value)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the name attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterName(String lower, String upper)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and assign value to the name attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Task objects now with new attribute values.
    */
   public TaskSet withName(String value)
   {
      for (Task obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the Task objects reached via parentTasks. 
    * 
    * @return Set of Task objects reachable via parentTasks
    */
   public TaskSet getParentTasks()
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         result.with(obj.getParentTasks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference parentTasks pointing to the object passed as parameter. 
    * 
    * @param value The object required as parentTasks neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via parentTasks
    */
   public TaskSet filterParentTasks(Object value)
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
      
      TaskSet answer = new TaskSet();
      
      for (Task obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getParentTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow parentTasks reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Task objects reachable via parentTasks transitively (including the start set)
    */
   public TaskSet getParentTasksTransitive()
   {
      TaskSet todo = new TaskSet().with(this);
      
      TaskSet result = new TaskSet();
      
      while ( ! todo.isEmpty())
      {
         Task current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getParentTasks()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the ParentTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their ParentTasks attributes.
    */
   public TaskSet withParentTasks(Task value)
   {
      for (Task obj : this)
      {
         obj.withParentTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Task object passed as parameter from the ParentTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskSet withoutParentTasks(Task value)
   {
      for (Task obj : this)
      {
         obj.withoutParentTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the Task objects reached via subTasks. 
    * 
    * @return Set of Task objects reachable via subTasks
    */
   public TaskSet getSubTasks()
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         result.with(obj.getSubTasks());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference subTasks pointing to the object passed as parameter. 
    * 
    * @param value The object required as subTasks neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via subTasks
    */
   public TaskSet filterSubTasks(Object value)
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
      
      TaskSet answer = new TaskSet();
      
      for (Task obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSubTasks()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow subTasks reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Task objects reachable via subTasks transitively (including the start set)
    */
   public TaskSet getSubTasksTransitive()
   {
      TaskSet todo = new TaskSet().with(this);
      
      TaskSet result = new TaskSet();
      
      while ( ! todo.isEmpty())
      {
         Task current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getSubTasks()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the SubTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their SubTasks attributes.
    */
   public TaskSet withSubTasks(Task value)
   {
      for (Task obj : this)
      {
         obj.withSubTasks(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Task object passed as parameter from the SubTasks attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskSet withoutSubTasks(Task value)
   {
      for (Task obj : this)
      {
         obj.withoutSubTasks(value);
      }
      
      return this;
   }

}
