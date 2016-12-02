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

import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.RemoteTask;

import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleSet;

public class RemoteTaskSet extends SimpleSet<RemoteTask>
{
//   public RemoteTaskPO hasRemoteTaskPO()
//   {
//      return new RemoteTaskPO(this.toArray(new RemoteTask[this.size()]));
//   }

   @SuppressWarnings("unchecked")
   public RemoteTaskSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<RemoteTask>)value);
      }
      else if (value != null)
      {
         this.add((RemoteTask) value);
      }
      
      return this;
   }
   
   public RemoteTaskSet without(RemoteTask value)
   {
      this.remove(value);
      return this;
   }

   public BoardTaskSet getBoardTask()
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (RemoteTask obj : this)
      {
         result.add(obj.getBoardTask());
      }
      
      return result;
   }

   public RemoteTaskSet hasBoardTask(BoardTask value)
   {
      RemoteTaskSet result = new RemoteTaskSet();
      
      for (RemoteTask obj : this)
      {
         if (value == obj.getBoardTask())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public RemoteTaskSet withBoardTask(BoardTask value)
   {
      for (RemoteTask obj : this)
      {
         obj.setBoardTask(value);
      }
      
      return this;
   }



   public Object hasRemoteTaskPO()
   {
      return null;
   }
   
   public LogEntrySet getLogEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (RemoteTask obj : this)
      {
         result.addAll(obj.getLogEntries());
      }
      
      return result;
   }

   public RemoteTaskSet hasLogEntries(Object value)
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
      
      RemoteTaskSet answer = new RemoteTaskSet();
      
      for (RemoteTask obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLogEntries()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public RemoteTaskSet withLogEntries(LogEntry value)
   {
      for (RemoteTask obj : this)
      {
         obj.withLogEntries(value);
      }
      
      return this;
   }

   public RemoteTaskSet withoutLogEntries(LogEntry value)
   {
      for (RemoteTask obj : this)
      {
         obj.withoutLogEntries(value);
      }
      
      return this;
   }


   public static final RemoteTaskSet EMPTY_SET = new RemoteTaskSet().withFlag(RemoteTaskSet.READONLY);


   public RemoteTaskPO filterRemoteTaskPO()
   {
      return new RemoteTaskPO(this.toArray(new RemoteTask[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.RemoteTask";
   }

   /**
    * Loop through the current set of RemoteTask objects and collect those RemoteTask objects where the boardTask attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of RemoteTask objects that match the parameter
    */
   public RemoteTaskSet filterBoardTask(BoardTask value)
   {
      RemoteTaskSet result = new RemoteTaskSet();
      
      for (RemoteTask obj : this)
      {
         if (value == obj.getBoardTask())
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   public RemoteTaskSet()
   {
      // empty
   }

   public RemoteTaskSet(RemoteTask... objects)
   {
      for (RemoteTask obj : objects)
      {
         this.add(obj);
      }
   }

   public RemoteTaskSet(Collection<RemoteTask> objects)
   {
      this.addAll(objects);
   }
}
