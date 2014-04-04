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
import org.sdmlib.replication.Task;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.replication.creators.LogEntrySet;
import org.sdmlib.replication.LogEntry;
import java.util.Collection;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class TaskSet extends LinkedHashSet<Task> implements
      org.sdmlib.models.modelsets.ModelSet
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (Task elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.replication.Task";
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

   public TaskPO hasTaskPO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();

      TaskPO patternObject = pattern.hasElementTaskPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public TaskSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<Task>) value);
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

}
