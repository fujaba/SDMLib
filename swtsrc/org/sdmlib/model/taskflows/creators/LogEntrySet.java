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
   
package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.taskflows.LogEntry;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.model.taskflows.creators.LoggerSet;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.creators.LogEntrySet;

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
      return "org.sdmlib.model.taskflows.LogEntry";
   }


   public LogEntrySet with(LogEntry value)
   {
      this.add(value);
      return this;
   }
   
   public LogEntrySet without(LogEntry value)
   {
      this.remove(value);
      return this;
   }
   public StringList getNodeName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getNodeName());
      }
      
      return result;
   }

   public LogEntrySet withNodeName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setNodeName(value);
      }
      
      return this;
   }

   public StringList getTaskName()
   {
      StringList result = new StringList();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getTaskName());
      }
      
      return result;
   }

   public LogEntrySet withTaskName(String value)
   {
      for (LogEntry obj : this)
      {
         obj.setTaskName(value);
      }
      
      return this;
   }

   public LoggerSet getLogger()
   {
      LoggerSet result = new LoggerSet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getLogger());
      }
      
      return result;
   }

   public LogEntrySet withLogger(Logger value)
   {
      for (LogEntry obj : this)
      {
         obj.withLogger(value);
      }
      
      return this;
   }

   public LogEntrySet getChildren()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         result.addAll(obj.getChildren());
      }
      
      return result;
   }

   public LogEntrySet withChildren(LogEntry value)
   {
      for (LogEntry obj : this)
      {
         obj.withChildren(value);
      }
      
      return this;
   }

   public LogEntrySet withoutChildren(LogEntry value)
   {
      for (LogEntry obj : this)
      {
         obj.withoutChildren(value);
      }
      
      return this;
   }

   public LogEntrySet getParent()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (LogEntry obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public LogEntrySet withParent(LogEntry value)
   {
      for (LogEntry obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }



   public LogEntryPO startModelPattern()
   {
      org.sdmlib.model.taskflows.creators.ModelPattern pattern = new org.sdmlib.model.taskflows.creators.ModelPattern();
      
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


