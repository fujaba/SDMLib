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

import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.taskflows.LogEntry;
import org.sdmlib.models.taskflows.Logger;
import org.sdmlib.models.taskflows.PeerProxy;
import org.sdmlib.models.taskflows.TaskFlow;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.list.NumberList;
import org.sdmlib.models.taskflows.util.LogEntrySet;

public class LoggerSet extends SimpleSet<Logger>
{


   public LoggerPO hasLoggerPO()
   {
      return new LoggerPO(this.toArray(new Logger[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public LoggerSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Logger>)value);
      }
      else if (value != null)
      {
         this.add((Logger) value);
      }
      
      return this;
   }
   
   public LoggerSet without(Logger value)
   {
      this.remove(value);
      return this;
   }

   public PeerProxySet getStartPeer()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (Logger obj : this)
      {
         result.add(obj.getStartPeer());
      }
      
      return result;
   }

   public LoggerSet hasStartPeer(PeerProxy value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getStartPeer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LoggerSet withStartPeer(PeerProxy value)
   {
      for (Logger obj : this)
      {
         obj.setStartPeer(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (Logger obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public LoggerSet hasTaskNo(int value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LoggerSet hasTaskNo(int lower, int upper)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (lower <= obj.getTaskNo() && obj.getTaskNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LoggerSet withTaskNo(int value)
   {
      for (Logger obj : this)
      {
         obj.setTaskNo(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (Logger obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public LoggerSet hasIdMap(SDMLibJsonIdMap value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public LoggerSet withIdMap(SDMLibJsonIdMap value)
   {
      for (Logger obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (Logger obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public LoggerSet hasSubFlow(Object value)
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
      
      LoggerSet answer = new LoggerSet();
      
      for (Logger obj : this)
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

   public LoggerSet withSubFlow(TaskFlow value)
   {
      for (Logger obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (Logger obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public LoggerSet hasParent(Object value)
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
      
      LoggerSet answer = new LoggerSet();
      
      for (Logger obj : this)
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

   public LoggerSet withParent(TaskFlow value)
   {
      for (Logger obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public LogEntrySet getEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (Logger obj : this)
      {
         result.addAll(obj.getEntries());
      }
      
      return result;
   }

   public LoggerSet hasEntries(Object value)
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
      
      LoggerSet answer = new LoggerSet();
      
      for (Logger obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getEntries()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LoggerSet withEntries(LogEntry value)
   {
      for (Logger obj : this)
      {
         obj.withEntries(value);
      }
      
      return this;
   }

   public LoggerSet withoutEntries(LogEntry value)
   {
      for (Logger obj : this)
      {
         obj.withoutEntries(value);
      }
      
      return this;
   }


   public static final LoggerSet EMPTY_SET = new LoggerSet().withFlag(LoggerSet.READONLY);


   public LoggerPO filterLoggerPO()
   {
      return new LoggerPO(this.toArray(new Logger[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.taskflows.Logger";
   }

   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the startPeer attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet filterStartPeer(PeerProxy value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getStartPeer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the taskNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet filterTaskNo(int value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the taskNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet filterTaskNo(int lower, int upper)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (lower <= obj.getTaskNo() && obj.getTaskNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the idMap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet filterIdMap(SDMLibJsonIdMap value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public LoggerSet()
   {
      // empty
   }

   public LoggerSet(Logger... objects)
   {
      for (Logger obj : objects)
      {
         this.add(obj);
      }
   }

   public LoggerSet(Collection<Logger> objects)
   {
      this.addAll(objects);
   }


   public LoggerPO createLoggerPO()
   {
      return new LoggerPO(this.toArray(new Logger[this.size()]));
   }


   @Override
   public LoggerSet getNewList(boolean keyValue)
   {
      return new LoggerSet();
   }


   public LoggerSet filter(Condition<Logger> condition) {
      LoggerSet filterList = new LoggerSet();
      filterItems(filterList, condition);
      return filterList;
   }
   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the startPeer attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet createStartPeerCondition(PeerProxy value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getStartPeer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the idMap attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet createIdMapCondition(SDMLibJsonIdMap value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the taskNo attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet createTaskNoCondition(int value)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of Logger objects and collect those Logger objects where the taskNo attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of Logger objects that match the parameter
    */
   public LoggerSet createTaskNoCondition(int lower, int upper)
   {
      LoggerSet result = new LoggerSet();
      
      for (Logger obj : this)
      {
         if (lower <= obj.getTaskNo() && obj.getTaskNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
