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
import java.util.Collections;

import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.Task;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;
import de.uniks.networkparser.interfaces.Condition;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.util.ReplicationChangeSet;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.util.BoardTaskSet;
import org.sdmlib.replication.RemoteTask;
import org.sdmlib.replication.util.RemoteTaskSet;
import org.sdmlib.replication.util.LogEntrySet;

public class TaskSet extends SimpleSet<Task>
{


   public TaskPO hasTaskPO()
   {
      return new TaskPO(this.toArray(new Task[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public TaskSet with(Object value)
   {
      if (value instanceof java.util.Collection)
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

   public LogEntrySet getLogEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (Task obj : this)
      {
         result.addAll(obj.getLogEntries());
      }
      
      return result;
   }

   public TaskSet hasLogEntries(Object value)
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
         if ( ! Collections.disjoint(neighbors, obj.getLogEntries()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public TaskSet withLogEntries(LogEntry value)
   {
      for (Task obj : this)
      {
         obj.withLogEntries(value);
      }
      
      return this;
   }

   public TaskSet withoutLogEntries(LogEntry value)
   {
      for (Task obj : this)
      {
         obj.withoutLogEntries(value);
      }
      
      return this;
   }


   public static final TaskSet EMPTY_SET = new TaskSet().withFlag(TaskSet.READONLY);


   public TaskPO filterTaskPO()
   {
      return new TaskPO(this.toArray(new Task[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.Task";
   }

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


   public TaskPO createTaskPO()
   {
      return new TaskPO(this.toArray(new Task[this.size()]));
   }


   @Override
   public TaskSet getNewList(boolean keyValue)
   {
      return new TaskSet();
   }


   public TaskSet filter(Condition<Task> condition) {
      TaskSet filterList = new TaskSet();
      filterItems(filterList, condition);
      return filterList;
   }

   public ReplicationChangeSet instanceOfReplicationChange()
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for(Object obj : this)
      {
         if (obj instanceof ReplicationChange)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public BoardTaskSet instanceOfBoardTask()
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for(Object obj : this)
      {
         if (obj instanceof BoardTask)
         {
            result.with(obj);
         }
      }
      
      return result;
   }

   public RemoteTaskSet instanceOfRemoteTask()
   {
      RemoteTaskSet result = new RemoteTaskSet();
      
      for(Object obj : this)
      {
         if (obj instanceof RemoteTask)
         {
            result.with(obj);
         }
      }
      
      return result;
   }}
