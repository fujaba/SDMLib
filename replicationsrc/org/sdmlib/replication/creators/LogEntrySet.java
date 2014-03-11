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
import org.sdmlib.replication.LogEntry;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.replication.creators.TaskSet;
import org.sdmlib.replication.Task;
import java.util.Collection;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class LogEntrySet extends LinkedHashSet<LogEntry> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (LogEntry elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.LogEntry";
   }


   public StringList getStepName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getStepName());
      }
      
      return result;
   }

   public LogEntrySet withStepName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setStepName(value);
      }
      
      return this;
   }

   public StringList getExecutedBy()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getExecutedBy());
      }
      
      return result;
   }

   public LogEntrySet withExecutedBy(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setExecutedBy(value);
      }
      
      return this;
   }

   public longList getTimeStamp()
   {
      longList result = new longList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getTimeStamp());
      }
      
      return result;
   }

   public LogEntrySet withTimeStamp(long value)
   {
      for (LogEntry obj : this)
      {
         obj.setTimeStamp(value);
      }
      
      return this;
   }

   public TaskSet getTask()
   {
      TaskSet result = new TaskSet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getTask());
      }
      
      return result;
   }

   public LogEntrySet withTask(Task value)
   {
      for (LogEntry obj : this)
      {
         obj.withTask(value);
      }
      
      return this;
   }



   public LogEntryPO hasLogEntryPO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();
      
      LogEntryPO patternObject = pattern.hasElementLogEntryPO();
      
      patternObject.withCandidates(this.clone());
      
      pattern.setHasMatch(true);
      pattern.findMatch();
      
      return patternObject;
   }


   public LogEntrySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<LogEntry>)value);
      }
      else if (value != null)
      {
         this.add((LogEntry) value);
      }
      
      return this;
   }
   
   public LogEntrySet without(LogEntry value)
   {
      this.remove(value);
      return this;
   }

}





