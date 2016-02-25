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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.couchspace.tasks.Task;
import java.util.Collection;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskFlowSet;
import org.sdmlib.test.examples.couchspace.tasks.TaskFlow;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskSet;
import java.util.Collections;
import org.sdmlib.test.examples.couchspace.tasks.util.UserGroupSet;
import org.sdmlib.test.examples.couchspace.tasks.UserGroup;
import org.sdmlib.test.examples.couchspace.tasks.util.UserSet;
import org.sdmlib.test.examples.couchspace.tasks.User;

public class TaskSet extends SDMSet<Task>
{

   public static final TaskSet EMPTY_SET = new TaskSet().withFlag(TaskSet.READONLY);


   public TaskPO filterTaskPO()
   {
      return new TaskPO(this.toArray(new Task[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.test.examples.couchspace.tasks.Task";
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
    * Loop through the current set of Task objects and collect a list of the title attribute values. 
    * 
    * @return List of String objects reachable via title attribute
    */
   public StringList getTitle()
   {
      StringList result = new StringList();
      
      for (Task obj : this)
      {
         result.add(obj.getTitle());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the title attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterTitle(String value)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (value.equals(obj.getTitle()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the title attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterTitle(String lower, String upper)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (lower.compareTo(obj.getTitle()) <= 0 && obj.getTitle().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and assign value to the title attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Task objects now with new attribute values.
    */
   public TaskSet withTitle(String value)
   {
      for (Task obj : this)
      {
         obj.setTitle(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Task objects and collect a list of the transitionCondition attribute values. 
    * 
    * @return List of String objects reachable via transitionCondition attribute
    */
   public StringList getTransitionCondition()
   {
      StringList result = new StringList();
      
      for (Task obj : this)
      {
         result.add(obj.getTransitionCondition());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the transitionCondition attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterTransitionCondition(String value)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (value.equals(obj.getTransitionCondition()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the transitionCondition attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterTransitionCondition(String lower, String upper)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (lower.compareTo(obj.getTransitionCondition()) <= 0 && obj.getTransitionCondition().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and assign value to the transitionCondition attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Task objects now with new attribute values.
    */
   public TaskSet withTransitionCondition(String value)
   {
      for (Task obj : this)
      {
         obj.setTransitionCondition(value);
      }
      
      return this;
   }


   /**
    * Loop through the current set of Task objects and collect a list of the copySdmLibId attribute values. 
    * 
    * @return List of String objects reachable via copySdmLibId attribute
    */
   public StringList getCopySdmLibId()
   {
      StringList result = new StringList();
      
      for (Task obj : this)
      {
         result.add(obj.getCopySdmLibId());
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the copySdmLibId attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterCopySdmLibId(String value)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (value.equals(obj.getCopySdmLibId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and collect those Task objects where the copySdmLibId attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Task objects that match the parameter
    */
   public TaskSet filterCopySdmLibId(String lower, String upper)
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         if (lower.compareTo(obj.getCopySdmLibId()) <= 0 && obj.getCopySdmLibId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Task objects and assign value to the copySdmLibId attribute of each of it. 
    * 
    * @param value New attribute value
    * 
    * @return Current set of Task objects now with new attribute values.
    */
   public TaskSet withCopySdmLibId(String value)
   {
      for (Task obj : this)
      {
         obj.setCopySdmLibId(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the TaskFlow objects reached via taskFlow. 
    * 
    * @return Set of TaskFlow objects reachable via taskFlow
    */
   public TaskFlowSet getTaskFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (Task obj : this)
      {
         result.with(obj.getTaskFlow());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference taskFlow pointing to the object passed as parameter. 
    * 
    * @param value The object required as taskFlow neighbor of the collected results. 
    * 
    * @return Set of TaskFlow objects referring to value via taskFlow
    */
   public TaskSet filterTaskFlow(Object value)
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
         if (neighbors.contains(obj.getTaskFlow()) || (neighbors.isEmpty() && obj.getTaskFlow() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the TaskFlow attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their TaskFlow attributes.
    */
   public TaskSet withTaskFlow(TaskFlow value)
   {
      for (Task obj : this)
      {
         obj.withTaskFlow(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the TaskFlow objects reached via taskFlowFirst. 
    * 
    * @return Set of TaskFlow objects reachable via taskFlowFirst
    */
   public TaskFlowSet getTaskFlowFirst()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (Task obj : this)
      {
         result.with(obj.getTaskFlowFirst());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference taskFlowFirst pointing to the object passed as parameter. 
    * 
    * @param value The object required as taskFlowFirst neighbor of the collected results. 
    * 
    * @return Set of TaskFlow objects referring to value via taskFlowFirst
    */
   public TaskSet filterTaskFlowFirst(Object value)
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
         if (neighbors.contains(obj.getTaskFlowFirst()) || (neighbors.isEmpty() && obj.getTaskFlowFirst() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the TaskFlowFirst attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their TaskFlowFirst attributes.
    */
   public TaskSet withTaskFlowFirst(TaskFlow value)
   {
      for (Task obj : this)
      {
         obj.withTaskFlowFirst(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the Task objects reached via transitionSource. 
    * 
    * @return Set of Task objects reachable via transitionSource
    */
   public TaskSet getTransitionSource()
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         result.with(obj.getTransitionSource());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference transitionSource pointing to the object passed as parameter. 
    * 
    * @param value The object required as transitionSource neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via transitionSource
    */
   public TaskSet filterTransitionSource(Object value)
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
         if (neighbors.contains(obj.getTransitionSource()) || (neighbors.isEmpty() && obj.getTransitionSource() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow transitionSource reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Task objects reachable via transitionSource transitively (including the start set)
    */
   public TaskSet getTransitionSourceTransitive()
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
            
            if ( ! result.contains(current.getTransitionSource()))
            {
               todo.with(current.getTransitionSource());
            }
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the TransitionSource attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their TransitionSource attributes.
    */
   public TaskSet withTransitionSource(Task value)
   {
      for (Task obj : this)
      {
         obj.withTransitionSource(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the UserGroup objects reached via responsibles. 
    * 
    * @return Set of UserGroup objects reachable via responsibles
    */
   public UserGroupSet getResponsibles()
   {
      UserGroupSet result = new UserGroupSet();
      
      for (Task obj : this)
      {
         result.with(obj.getResponsibles());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference responsibles pointing to the object passed as parameter. 
    * 
    * @param value The object required as responsibles neighbor of the collected results. 
    * 
    * @return Set of UserGroup objects referring to value via responsibles
    */
   public TaskSet filterResponsibles(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getResponsibles()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the Responsibles attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their Responsibles attributes.
    */
   public TaskSet withResponsibles(UserGroup value)
   {
      for (Task obj : this)
      {
         obj.withResponsibles(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Task object passed as parameter from the Responsibles attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskSet withoutResponsibles(UserGroup value)
   {
      for (Task obj : this)
      {
         obj.withoutResponsibles(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the User objects reached via handledBy. 
    * 
    * @return Set of User objects reachable via handledBy
    */
   public UserSet getHandledBy()
   {
      UserSet result = new UserSet();
      
      for (Task obj : this)
      {
         result.with(obj.getHandledBy());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference handledBy pointing to the object passed as parameter. 
    * 
    * @param value The object required as handledBy neighbor of the collected results. 
    * 
    * @return Set of User objects referring to value via handledBy
    */
   public TaskSet filterHandledBy(Object value)
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
         if (neighbors.contains(obj.getHandledBy()) || (neighbors.isEmpty() && obj.getHandledBy() == null))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the HandledBy attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their HandledBy attributes.
    */
   public TaskSet withHandledBy(User value)
   {
      for (Task obj : this)
      {
         obj.withHandledBy(value);
      }
      
      return this;
   }

   /**
    * Loop through the current set of Task objects and collect a set of the Task objects reached via transitionTargets. 
    * 
    * @return Set of Task objects reachable via transitionTargets
    */
   public TaskSet getTransitionTargets()
   {
      TaskSet result = new TaskSet();
      
      for (Task obj : this)
      {
         result.with(obj.getTransitionTargets());
      }
      
      return result;
   }

   /**
    * Loop through the current set of Task objects and collect all contained objects with reference transitionTargets pointing to the object passed as parameter. 
    * 
    * @param value The object required as transitionTargets neighbor of the collected results. 
    * 
    * @return Set of Task objects referring to value via transitionTargets
    */
   public TaskSet filterTransitionTargets(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getTransitionTargets()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   /**
    * Follow transitionTargets reference zero or more times and collect all reachable objects. Detect cycles and deal with them. 
    * 
    * @return Set of Task objects reachable via transitionTargets transitively (including the start set)
    */
   public TaskSet getTransitionTargetsTransitive()
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
            
            todo.with(current.getTransitionTargets()).minus(result);
         }
      }
      
      return result;
   }

   /**
    * Loop through current set of ModelType objects and attach the Task object passed as parameter to the TransitionTargets attribute of each of it. 
    * 
    * @return The original set of ModelType objects now with the new neighbor attached to their TransitionTargets attributes.
    */
   public TaskSet withTransitionTargets(Task value)
   {
      for (Task obj : this)
      {
         obj.withTransitionTargets(value);
      }
      
      return this;
   }

   /**
    * Loop through current set of ModelType objects and remove the Task object passed as parameter from the TransitionTargets attribute of each of it. 
    * 
    * @return The original set of ModelType objects now without the old neighbor.
    */
   public TaskSet withoutTransitionTargets(Task value)
   {
      for (Task obj : this)
      {
         obj.withoutTransitionTargets(value);
      }
      
      return this;
   }

}
