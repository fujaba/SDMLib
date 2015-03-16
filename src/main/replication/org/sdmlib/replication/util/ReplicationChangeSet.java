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
import java.util.TreeSet;

import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.LogEntry;
import org.sdmlib.replication.ReplicationChange;

public class ReplicationChangeSet extends TreeSet<ReplicationChange> implements ModelSet
{


   public ReplicationChangePO hasReplicationChangePO()
   {
      return new ReplicationChangePO(this.toArray(new ReplicationChange[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.ReplicationChange";
   }


   @SuppressWarnings("unchecked")
   public ReplicationChangeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReplicationChange>)value);
      }
      else if (value != null)
      {
         this.add((ReplicationChange) value);
      }
      
      return this;
   }
   
   public ReplicationChangeSet without(ReplicationChange value)
   {
      this.remove(value);
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

   public ReplicationChangeSet hasHistoryIdPrefix(String value)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (value.equals(obj.getHistoryIdPrefix()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChangeSet hasHistoryIdPrefix(String lower, String upper)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (lower.compareTo(obj.getHistoryIdPrefix()) <= 0 && obj.getHistoryIdPrefix().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public ReplicationChangeSet hasHistoryIdNumber(long value)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (value == obj.getHistoryIdNumber())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChangeSet hasHistoryIdNumber(long lower, long upper)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (lower <= obj.getHistoryIdNumber() && obj.getHistoryIdNumber() <= upper)
         {
            result.add(obj);
         }
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

   public StringList getTargetObjectId()
   {
      StringList result = new StringList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getTargetObjectId());
      }
      
      return result;
   }

   public ReplicationChangeSet hasTargetObjectId(String value)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (value.equals(obj.getTargetObjectId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChangeSet hasTargetObjectId(String lower, String upper)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (lower.compareTo(obj.getTargetObjectId()) <= 0 && obj.getTargetObjectId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public ReplicationChangeSet hasTargetProperty(String value)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (value.equals(obj.getTargetProperty()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChangeSet hasTargetProperty(String lower, String upper)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (lower.compareTo(obj.getTargetProperty()) <= 0 && obj.getTargetProperty().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public booleanList getIsToManyProperty()
   {
      booleanList result = new booleanList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getIsToManyProperty());
      }
      
      return result;
   }

   public ReplicationChangeSet hasIsToManyProperty(boolean value)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (value == obj.getIsToManyProperty())
         {
            result.add(obj);
         }
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

   public StringList getChangeMsg()
   {
      StringList result = new StringList();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getChangeMsg());
      }
      
      return result;
   }

   public ReplicationChangeSet hasChangeMsg(String value)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (value.equals(obj.getChangeMsg()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChangeSet hasChangeMsg(String lower, String upper)
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (lower.compareTo(obj.getChangeMsg()) <= 0 && obj.getChangeMsg().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
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

   public LogEntrySet getLogEntries()
   {
      LogEntrySet result = new LogEntrySet();
      
      for (ReplicationChange obj : this)
      {
         result.addAll(obj.getLogEntries());
      }
      
      return result;
   }

   public ReplicationChangeSet hasLogEntries(Object value)
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
      
      ReplicationChangeSet answer = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getLogEntries()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
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

   public ChangeHistorySet getHistory()
   {
      ChangeHistorySet result = new ChangeHistorySet();
      
      for (ReplicationChange obj : this)
      {
         result.add(obj.getHistory());
      }
      
      return result;
   }

   public ReplicationChangeSet hasHistory(Object value)
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
      
      ReplicationChangeSet answer = new ReplicationChangeSet();
      
      for (ReplicationChange obj : this)
      {
         if (neighbors.contains(obj.getHistory()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ReplicationChangeSet withHistory(ChangeHistory value)
   {
      for (ReplicationChange obj : this)
      {
         obj.withHistory(value);
      }
      
      return this;
   }


   public static final ReplicationChangeSet EMPTY_SET = new ReplicationChangeSet();
}
