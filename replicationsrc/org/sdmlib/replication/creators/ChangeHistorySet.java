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
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.replication.creators.ReplicationChangeSet;
import org.sdmlib.replication.ReplicationChange;
import java.util.Collection;
import java.util.Collections;
import org.sdmlib.models.modelsets.ObjectSet;

public class ChangeHistorySet extends LinkedHashSet<ChangeHistory> implements
      org.sdmlib.models.modelsets.ModelSet
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (ChangeHistory elem : this)
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.replication.ChangeHistory";
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

   public ChangeHistoryPO hasChangeHistoryPO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();

      ChangeHistoryPO patternObject = pattern.hasElementChangeHistoryPO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public ChangeHistorySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ChangeHistory>) value);
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

}





