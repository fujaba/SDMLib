/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.storyboards.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;

import de.uniks.networkparser.list.SimpleSet;

public class LogEntryStoryBoardSet extends SimpleSet<LogEntryStoryBoard>
{

   public static final LogEntryStoryBoardSet EMPTY_SET = new LogEntryStoryBoardSet().withFlag(LogEntryStoryBoardSet.READONLY);


   public LogEntryStoryBoardPO hasLogEntryStoryBoardPO()
   {
      return new LogEntryStoryBoardPO(this.toArray(new LogEntryStoryBoard[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.storyboards.LogEntryStoryBoard";
   }


   @SuppressWarnings("unchecked")
   public LogEntryStoryBoardSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LogEntryStoryBoard>)value);
      }
      else if (value != null)
      {
         this.add((LogEntryStoryBoard) value);
      }
      
      return this;
   }
   
   public LogEntryStoryBoardSet without(LogEntryStoryBoard value)
   {
      this.remove(value);
      return this;
   }

   public KanbanEntrySet getKanbanEntry()
   {
      KanbanEntrySet result = new KanbanEntrySet();
      
      for (LogEntryStoryBoard obj : this)
      {
         result.add(obj.getKanbanEntry());
      }
      
      return result;
   }

   public LogEntryStoryBoardSet hasKanbanEntry(Object value)
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
      
      LogEntryStoryBoardSet answer = new LogEntryStoryBoardSet();
      
      for (LogEntryStoryBoard obj : this)
      {
         if (neighbors.contains(obj.getKanbanEntry()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public LogEntryStoryBoardSet withKanbanEntry(KanbanEntry value)
   {
      for (LogEntryStoryBoard obj : this)
      {
         obj.withKanbanEntry(value);
      }
      
      return this;
   }

}
