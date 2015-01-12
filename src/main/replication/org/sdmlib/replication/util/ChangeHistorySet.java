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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationChange;
import org.sdmlib.replication.util.ReplicationChangeSet;

public class ChangeHistorySet extends SDMSet<ChangeHistory>
{


   public ChangeHistoryPO hasChangeHistoryPO()
   {
      return new ChangeHistoryPO(this.toArray(new ChangeHistory[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.ChangeHistory";
   }


   @SuppressWarnings("unchecked")
   public ChangeHistorySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChangeHistory>)value);
      }
      else if (value != null)
      {
         this.add((ChangeHistory) value);
      }
      
      return this;
   }
   
   public ChangeHistorySet without(ChangeHistory value)
   {
      this.remove(value);
      return this;
   }

   public ReplicationChangeSet getChanges()
   {
      ReplicationChangeSet result = new ReplicationChangeSet();
      
      for (ChangeHistory obj : this)
      {
         result.addAll(obj.getChanges());
      }
      
      return result;
   }

   public ChangeHistorySet hasChanges(Object value)
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
      
      ChangeHistorySet answer = new ChangeHistorySet();
      
      for (ChangeHistory obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getChanges()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ChangeHistorySet withChanges(ReplicationChange value)
   {
      for (ChangeHistory obj : this)
      {
         obj.withChanges(value);
      }
      
      return this;
   }

   public ChangeHistorySet withoutChanges(ReplicationChange value)
   {
      for (ChangeHistory obj : this)
      {
         obj.withoutChanges(value);
      }
      
      return this;
   }


   public static final ChangeHistorySet EMPTY_SET = new ChangeHistorySet().withReadonly(true);
}
