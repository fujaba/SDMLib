/*
   Copyright (c) 2013 zuendorf 
   
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
   
package org.sdmlib.replication.creators;

import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.replication.BoardTask;
import org.sdmlib.replication.Lane;
import org.sdmlib.replication.LogEntry;
import java.util.Collection;

public class BoardTaskSet extends LinkedHashSet<BoardTask> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (BoardTask elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.BoardTask";
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

   public BoardTaskSet withName(String value)
   {
      for (BoardTask obj : this)
      {
         obj.setName(value);
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

   public BoardTaskSet withLane(Lane value)
   {
      for (BoardTask obj : this)
      {
         obj.withLane(value);
      }
      
      return this;
   }


   public BoardTask getFirst()
   {
      for (BoardTask t : this)
      {
         return t;
      }
      
      return null;
   }


   public void removeYou()
   {
      for (BoardTask t : this)
      {
         t.removeYou();
      }
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

   public BoardTaskSet withStatus(String value)
   {
      for (BoardTask obj : this)
      {
         obj.setStatus(value);
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



   public BoardTaskPO hasBoardTaskPO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();
      
      BoardTaskPO patternObject = pattern.hasElementBoardTaskPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


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

}



