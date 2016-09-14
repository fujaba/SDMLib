/*
   Copyright (c) 2016 deeptought
   
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
   
package org.sdmlib.test.examples.modelcouch.util;

import java.util.Collection;
import java.util.Collections;

import de.uniks.networkparser.list.ObjectSet;
import org.sdmlib.test.examples.modelcouch.DocumentData;
import org.sdmlib.test.examples.modelcouch.Person;
import org.sdmlib.test.examples.modelcouch.Task;

import de.uniks.networkparser.list.SimpleSet;

public class TaskSet extends SimpleSet<Task>
{

   public static final TaskSet EMPTY_SET = new TaskSet().withFlag(TaskSet.READONLY);


   public TaskPO hasTaskPO()
   {
      return new TaskPO(this.toArray(new Task[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.modelcouch.Task";
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
         result.addAll(obj.getSubTasks());
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
   public TaskSet hasSubTasks(Object value)
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
            
            todo.with(current.getSubTasks().minus(result));
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
         result.addAll(obj.getParentTasks());
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
   public TaskSet hasParentTasks(Object value)
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
            
            todo.with(current.getParentTasks().minus(result));
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
    * Loop through the current set of Task objects and collect a set of the Person objects reached via persons. 
    * 
    * @return Set of Person objects reachable via persons
    */
   public PersonSet getPersons()
   {
      PersonSet result = new PersonSet();
      
      for (Task obj : this)
      {
         result.addAll(obj.getPersons());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference persons pointing to the object passed as parameter. 
    * 
    * @param value The object required as persons neighbor of the collected results. 
    * 
    * @return Set of Person objects referring to value via persons
    */
   public TaskSet hasPersons(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getPersons()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Persons attributes.
    */
   public TaskSet withPersons(Person value)
   {
      for (Task obj : this)
      {
         obj.withPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Task object passed as parameter from the Persons attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskSet withoutPersons(Person value)
   {
      for (Task obj : this)
      {
         obj.withoutPersons(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the DocumentData objects reached via taskData. 
    * 
    * @return Set of DocumentData objects reachable via taskData
    */
   public DocumentDataSet getTaskData()
   {
      DocumentDataSet result = new DocumentDataSet();
      
      for (Task obj : this)
      {
         result.addAll(obj.getTaskData());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference taskData pointing to the object passed as parameter. 
    * 
    * @param value The object required as taskData neighbor of the collected results. 
    * 
    * @return Set of DocumentData objects referring to value via taskData
    */
   public TaskSet hasTaskData(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getTaskData()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the TaskData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their TaskData attributes.
    */
   public TaskSet withTaskData(DocumentData value)
   {
      for (Task obj : this)
      {
         obj.withTaskData(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Task object passed as parameter from the TaskData attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskSet withoutTaskData(DocumentData value)
   {
      for (Task obj : this)
      {
         obj.withoutTaskData(value);
      }
      
      return this;
   }

}
