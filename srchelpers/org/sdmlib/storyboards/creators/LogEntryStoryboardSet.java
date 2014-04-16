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
   
package org.sdmlib.storyboards.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntryStoryBoard;

public class LogEntryStoryboardSet extends LinkedHashSet<LogEntryStoryBoard> implements org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;

   public KanbanEntrySet getKanbanEntry()
   {
      KanbanEntrySet result = new KanbanEntrySet();
      
      for (LogEntryStoryBoard obj : this)
      {
         result.add(obj.getKanbanEntry());
      }
      
      return result;
   }
   public LogEntryStoryboardSet withKanbanEntry(KanbanEntry value)
   {
      for (LogEntryStoryBoard obj : this)
      {
         obj.withKanbanEntry(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (LogEntryStoryBoard elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.storyboards.LogEntry";
   }

   public LogEntryPO startModelPattern()
   {
      org.sdmlib.storyboards.creators.ModelPattern pattern = new org.sdmlib.storyboards.creators.ModelPattern();
      
      LogEntryPO patternObject = pattern.hasElementLogEntryPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public LogEntryStoryboardSet with(Object value)
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
   
   public LogEntryStoryboardSet without(LogEntryStoryBoard value)
   {
      this.remove(value);
      return this;
   }



   public LogEntryPO hasLogEntryPO()
   {
      org.sdmlib.storyboards.creators.ModelPattern pattern = new org.sdmlib.storyboards.creators.ModelPattern();
      
      LogEntryPO patternObject = pattern.hasElementLogEntryPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }
}









