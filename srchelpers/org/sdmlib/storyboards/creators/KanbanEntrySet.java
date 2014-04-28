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

package org.sdmlib.storyboards.creators;

import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.storyboards.KanbanEntry;
import org.sdmlib.storyboards.LogEntry;
import java.util.List;
import org.sdmlib.storyboards.creators.LogEntrySet;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class KanbanEntrySet extends LinkedHashSet<KanbanEntry> implements
      org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;

   public LogEntrySet getLogEntries()
   {
      LogEntrySet result = new LogEntrySet();

      for (KanbanEntry obj : this)
      {
         result.addAll(obj.getLogEntries());
      }

      return result;
   }

   public KanbanEntrySet withLogEntries(LogEntry value)
   {
      for (KanbanEntry obj : this)
      {
         obj.withLogEntries(value);
      }

      return this;
   }

   public KanbanEntrySet withoutLogEntries(LogEntry value)
   {
      for (KanbanEntry obj : this)
      {
         obj.withoutLogEntries(value);
      }

      return this;
   }

   public String toString()
   {
      StringList stringList = new StringList();

      for (KanbanEntry elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.storyboards.KanbanEntry";
   }

   public intList getOldNoOfLogEntries()
   {
      intList result = new intList();

      for (KanbanEntry obj : this)
      {
         result.add(obj.getOldNoOfLogEntries());
      }

      return result;
   }

   public KanbanEntrySet withOldNoOfLogEntries(int value)
   {
      for (KanbanEntry obj : this)
      {
         obj.setOldNoOfLogEntries(value);
      }

      return this;
   }

   public KanbanEntryPO startModelPattern()
   {
      org.sdmlib.storyboards.creators.ModelPattern pattern = new org.sdmlib.storyboards.creators.ModelPattern();

      KanbanEntryPO patternObject = pattern.hasElementKanbanEntryPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public KanbanEntrySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<KanbanEntry>) value);
      }
      else if (value != null)
      {
         this.add((KanbanEntry) value);
      }

      return this;
   }

   public KanbanEntrySet without(KanbanEntry value)
   {
      this.remove(value);
      return this;
   }

   public StringList getPhases()
   {
      StringList result = new StringList();

      for (KanbanEntry obj : this)
      {
         result.add(obj.getPhases());
      }

      return result;
   }

   public KanbanEntrySet hasPhases(String value)
   {
      KanbanEntrySet result = new KanbanEntrySet();

      for (KanbanEntry obj : this)
      {
         if (value.equals(obj.getPhases()))
         {
            result.add(obj);
         }
      }

      return result;
   }

   public KanbanEntrySet hasPhases(String lower, String upper)
   {
      KanbanEntrySet result = new KanbanEntrySet();

      for (KanbanEntry obj : this)
      {
         if (lower.compareTo(obj.getPhases()) <= 0
            && obj.getPhases().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }

      return result;
   }

   public KanbanEntrySet withPhases(String value)
   {
      for (KanbanEntry obj : this)
      {
         obj.setPhases(value);
      }

      return this;
   }

   public KanbanEntryPO hasKanbanEntryPO()
   {
      org.sdmlib.storyboards.creators.ModelPattern pattern = new org.sdmlib.storyboards.creators.ModelPattern();

      KanbanEntryPO patternObject = pattern.hasElementKanbanEntryPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }
}
