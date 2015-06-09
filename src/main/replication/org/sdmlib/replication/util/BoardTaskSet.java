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

import java.beans.PropertyChangeEvent;
import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.SeppelSpaceProxy;

public class BoardTaskSet extends SDMSet<BoardTask>
{


   public BoardTaskPO hasBoardTaskPO()
   {
      return new BoardTaskPO(this.toArray(new BoardTask[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.BoardTask";
   }


   @SuppressWarnings("unchecked")
   public BoardTaskSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<BoardTask>)value);
      }
      else if (value != null)
      {
         this.add((BoardTask) value);
      }
      
      return this;
   }
   
   public BoardTaskSet without(BoardTask value)
   {
      this.remove(value);
      return this;
   }

   public StringList getName()
   {
      StringList result = new StringList();
      
      for (BoardTask obj : this)
      {
         result.add(obj.getName());
      }
      
      return result;
   }

   public BoardTaskSet hasName(String value)
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (value.equals(obj.getName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BoardTaskSet hasName(String lower, String upper)
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (lower.compareTo(obj.getName()) <= 0 && obj.getName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BoardTaskSet withName(String value)
   {
      for (BoardTask obj : this)
      {
         obj.setName(value);
      }
      
      return this;
   }

   public StringList getStatus()
   {
      StringList result = new StringList();
      
      for (BoardTask obj : this)
      {
         result.add(obj.getStatus());
      }
      
      return result;
   }

   public BoardTaskSet hasStatus(String value)
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (value.equals(obj.getStatus()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BoardTaskSet hasStatus(String lower, String upper)
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (lower.compareTo(obj.getStatus()) <= 0 && obj.getStatus().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BoardTaskSet withStatus(String value)
   {
      for (BoardTask obj : this)
      {
         obj.setStatus(value);
      }
      
      return this;
   }

   public LogEntrySet getLogEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (BoardTask obj : this)
      {
         result.addAll(obj.getLogEntries());
      }
      
      return result;
   }

   public BoardTaskSet hasLogEntries(Object value)
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
      
      BoardTaskSet answer = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLogEntries()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BoardTaskSet withLogEntries(LogEntry value)
   {
      for (BoardTask obj : this)
      {
         obj.withLogEntries(value);
      }
      
      return this;
   }

   public BoardTaskSet withoutLogEntries(LogEntry value)
   {
      for (BoardTask obj : this)
      {
         obj.withoutLogEntries(value);
      }
      
      return this;
   }

   public LaneSet getLane()
   {
      LaneSet result = new LaneSet();
      
      for (BoardTask obj : this)
      {
         result.add(obj.getLane());
      }
      
      return result;
   }

   public BoardTaskSet hasLane(Object value)
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
      
      BoardTaskSet answer = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (neighbors.contains(obj.getLane()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BoardTaskSet withLane(Lane value)
   {
      for (BoardTask obj : this)
      {
         obj.withLane(value);
      }
      
      return this;
   }

   public BoardTaskSet getNext()
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         result.addAll(obj.getNext());
      }
      
      return result;
   }

   public BoardTaskSet hasNext(Object value)
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
      
      BoardTaskSet answer = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getNext()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public BoardTaskSet getNextTransitive()
   {
      BoardTaskSet todo = new BoardTaskSet().with(this);
      
      BoardTaskSet result = new BoardTaskSet();
      
      while ( ! todo.isEmpty())
      {
         BoardTask current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getNext().minus(result));
         }
      }
      
      return result;
   }

   public BoardTaskSet withNext(BoardTask value)
   {
      for (BoardTask obj : this)
      {
         obj.withNext(value);
      }
      
      return this;
   }

   public BoardTaskSet withoutNext(BoardTask value)
   {
      for (BoardTask obj : this)
      {
         obj.withoutNext(value);
      }
      
      return this;
   }

   public BoardTaskSet getPrev()
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         result.addAll(obj.getPrev());
      }
      
      return result;
   }

   public BoardTaskSet hasPrev(Object value)
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
      
      BoardTaskSet answer = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPrev()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public BoardTaskSet getPrevTransitive()
   {
      BoardTaskSet todo = new BoardTaskSet().with(this);
      
      BoardTaskSet result = new BoardTaskSet();
      
      while ( ! todo.isEmpty())
      {
         BoardTask current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getPrev().minus(result));
         }
      }
      
      return result;
   }

   public BoardTaskSet withPrev(BoardTask value)
   {
      for (BoardTask obj : this)
      {
         obj.withPrev(value);
      }
      
      return this;
   }

   public BoardTaskSet withoutPrev(BoardTask value)
   {
      for (BoardTask obj : this)
      {
         obj.withoutPrev(value);
      }
      
      return this;
   }


   public static final BoardTaskSet EMPTY_SET = new BoardTaskSet().withReadOnly(true);
   public SeppelSpaceProxySet getProxy()
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (BoardTask obj : this)
      {
         result.add(obj.getProxy());
      }
      
      return result;
   }

   public BoardTaskSet hasProxy(Object value)
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
      
      BoardTaskSet answer = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (neighbors.contains(obj.getProxy()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public BoardTaskSet withProxy(SeppelSpaceProxy value)
   {
      for (BoardTask obj : this)
      {
         obj.withProxy(value);
      }
      
      return this;
   }

   public booleanList getManualExecution()
   {
      booleanList result = new booleanList();
      
      for (BoardTask obj : this)
      {
         result.add(obj.isManualExecution());
      }
      
      return result;
   }

   public BoardTaskSet hasManualExecution(boolean value)
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (value == obj.isManualExecution())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BoardTaskSet withManualExecution(boolean value)
   {
      for (BoardTask obj : this)
      {
         obj.setManualExecution(value);
      }
      
      return this;
   }

   
   //==========================================================================
   
   public BoardTaskSet execute()
   {
      for (BoardTask obj : this)
      {
         obj.execute();
      }
      return this;
   }

   public BoardTaskSet hasStashedPropertyChangeEvent(PropertyChangeEvent value)
   {
      BoardTaskSet result = new BoardTaskSet();
      
      for (BoardTask obj : this)
      {
         if (value == obj.getStashedPropertyChangeEvent())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public BoardTaskSet withStashedPropertyChangeEvent(PropertyChangeEvent value)
   {
      for (BoardTask obj : this)
      {
         obj.setStashedPropertyChangeEvent(value);
      }
      
      return this;
   }

}
