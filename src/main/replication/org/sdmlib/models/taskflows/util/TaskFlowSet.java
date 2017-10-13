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

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.taskflows.TaskFlow;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.models.taskflows.FetchFileFlow;
import org.sdmlib.models.taskflows.util.FetchFileFlowSet;
import org.sdmlib.models.taskflows.Logger;
import org.sdmlib.models.taskflows.util.LoggerSet;
import de.uniks.networkparser.list.NumberList;

public class TaskFlowSet extends SimpleSet<TaskFlow>
{
   public TaskFlowPO hasTaskFlowPO()
   {
      return new TaskFlowPO(this.toArray(new TaskFlow[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public TaskFlowSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public TaskFlowSet hasTaskNo(int value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TaskFlowSet hasTaskNo(int lower, int upper)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (lower <= obj.getTaskNo() && obj.getTaskNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TaskFlowSet withTaskNo(int value)
   {
      for (TaskFlow obj : this)
      {
         obj.setTaskNo(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public TaskFlowSet hasIdMap(SDMLibJsonIdMap value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public TaskFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (TaskFlow obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public TaskFlowSet hasSubFlow(Object value)
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
         if (neighbors.contains(obj.getSubFlow()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public TaskFlowSet getSubFlowTransitive()
   {
      TaskFlowSet todo = new TaskFlowSet().with(this);
      
      TaskFlowSet result = new TaskFlowSet();
      
      while ( ! todo.isEmpty())
      {
         TaskFlow current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getSubFlow()))
            {
               todo.with(current.getSubFlow());
            }
         }
      }
      
      return result;
   }

   public TaskFlowSet withSubFlow(TaskFlow value)
   {
      for (TaskFlow obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public TaskFlowSet hasParent(Object value)
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
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public TaskFlowSet getParentTransitive()
   {
      TaskFlowSet todo = new TaskFlowSet().with(this);
      
      TaskFlowSet result = new TaskFlowSet();
      
      while ( ! todo.isEmpty())
      {
         TaskFlow current = todo.first();
         
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

   public TaskFlowSet withParent(TaskFlow value)
   {
      for (TaskFlow obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }


   public static final TaskFlowSet EMPTY_SET = new TaskFlowSet().withFlag(TaskFlowSet.READONLY);


   public TaskFlowPO filterTaskFlowPO()
   {
      return new TaskFlowPO(this.toArray(new TaskFlow[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.taskflows.TaskFlow";
   }

   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the taskNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet filterTaskNo(int value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the taskNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet filterTaskNo(int lower, int upper)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (lower <= obj.getTaskNo() && obj.getTaskNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the idMap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet filterIdMap(SDMLibJsonIdMap value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
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


   public TaskFlowSet filter(Condition<TaskFlow> condition) {
      TaskFlowSet filterList = new TaskFlowSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public FetchFileFlowSet instanceOfFetchFileFlow()
   {
      FetchFileFlowSet result = new FetchFileFlowSet();
      
      for(Object obj : this)
      {
         if (obj instanceof FetchFileFlow)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public LoggerSet instanceOfLogger()
   {
      LoggerSet result = new LoggerSet();
      
      for(Object obj : this)
      {
         if (obj instanceof Logger)
         {
            result.with(obj);
         }
      }
      
      return result;
   }
   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the idMap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet createIdMapCondition(SDMLibJsonIdMap value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the taskNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet createTaskNoCondition(int value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of TaskFlow objects and collect those TaskFlow objects where the taskNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of TaskFlow objects that match the parameter
    */
   public TaskFlowSet createTaskNoCondition(int lower, int upper)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (lower <= obj.getTaskNo() && obj.getTaskNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
