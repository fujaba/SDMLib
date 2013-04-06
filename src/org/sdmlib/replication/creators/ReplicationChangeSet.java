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
import java.util.TreeSet;

import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.replication.creators.ChangeHistorySet;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.replication.creators.LogEntrySet;
import org.sdmlib.replication.LogEntry;

public class ReplicationChangeSet extends TreeSet<ReplicationChange> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ReplicationChange elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.ReplicationChange";
   }


   public ReplicationChangeSet with(ReplicationChange value)
   {
      this.add(value);
      return this;
   }
   
   public ReplicationChangeSet without(ReplicationChange value)
   {
      this.remove(value);
      return this;
   }

   public StringList getTargetObjectId()
   {
      StringList result = new StringList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getTargetObjectId());
      }
      
      return result;
   }

   public ReplicationChangeSet withTargetObjectId(String value)
   {
      for (ReplicationChange obj : this)
      {
         obj.setTargetObjectId(value);
      }
      
      return this;
   }

   public StringList getTargetProperty()
   {
      StringList result = new StringList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getTargetProperty());
      }
      
      return result;
   }

   public ReplicationChangeSet withTargetProperty(String value)
   {
      for (ReplicationChange obj : this)
      {
         obj.setTargetProperty(value);
      }
      
      return this;
   }

   public StringList getChangeMsg()
   {
      StringList result = new StringList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getChangeMsg());
      }
      
      return result;
   }

   public ReplicationChangeSet withChangeMsg(String value)
   {
      for (ReplicationChange obj : this)
      {
         obj.setChangeMsg(value);
      }
      
      return this;
   }

   public ChangeHistorySet getHistory()
   {
      ChangeHistorySet result = new ChangeHistorySet();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getHistory());
      }
      
      return result;
   }

   public ReplicationChangeSet withHistory(ChangeHistory value)
   {
      for (ReplicationChange obj : this)
      {
         obj.withHistory(value);
      }
      
      return this;
   }

   public booleanList getIsToManyProperty()
   {
      booleanList result = new booleanList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getIsToManyProperty());
      }
      
      return result;
   }

   public ReplicationChangeSet withIsToManyProperty(boolean value)
   {
      for (ReplicationChange obj : this)
      {
         obj.setIsToManyProperty(value);
      }
      
      return this;
   }

   public StringList getHistoryIdPrefix()
   {
      StringList result = new StringList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getHistoryIdPrefix());
      }
      
      return result;
   }

   public ReplicationChangeSet withHistoryIdPrefix(String value)
   {
      for (ReplicationChange obj : this)
      {
         obj.setHistoryIdPrefix(value);
      }
      
      return this;
   }

   public longList getHistoryIdNumber()
   {
      longList result = new longList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getHistoryIdNumber());
      }
      
      return result;
   }

   public ReplicationChangeSet withHistoryIdNumber(long value)
   {
      for (ReplicationChange obj : this)
      {
         obj.setHistoryIdNumber(value);
      }
      
      return this;
   }

   public LogEntrySet getLogEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (ReplicationChange obj : this)
      {
         result.addAll(obj.getLogEntries());
      }
      
      return result;
   }

   public ReplicationChangeSet withLogEntries(LogEntry value)
   {
      for (ReplicationChange obj : this)
      {
         obj.withLogEntries(value);
      }
      
      return this;
   }

   public ReplicationChangeSet withoutLogEntries(LogEntry value)
   {
      for (ReplicationChange obj : this)
      {
         obj.withoutLogEntries(value);
      }
      
      return this;
   }

}




