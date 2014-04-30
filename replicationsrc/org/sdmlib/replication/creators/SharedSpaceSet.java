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

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.models.modelsets.StringList;

import java.util.List;

import org.sdmlib.replication.creators.ReplicationNodeSet;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.creators.ReplicationChannelSet;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.models.modelsets.longList;

import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;

public class SharedSpaceSet extends LinkedHashMap<String, SharedSpace>
      implements org.sdmlib.models.modelsets.ModelSet
{

   public String toString()
   {
      StringList stringList = new StringList();

      for (SharedSpace elem : this.values())
      {
         stringList.add(elem.toString());
      }

      return "(" + stringList.concat(", ") + ")";
   }

   public String getEntryType()
   {
      return "org.sdmlib.replication.SharedSpace";
   }

   public StringList getSpaceId()
   {
      StringList result = new StringList();

      for (SharedSpace obj : this.values())
      {
         result.add(obj.getSpaceId());
      }

      return result;
   }

   public SharedSpaceSet withSpaceId(String value)
   {
      throw new UnsupportedOperationException();
   }

   public ReplicationNodeSet getNode()
   {
      ReplicationNodeSet result = new ReplicationNodeSet();

      for (SharedSpace obj : this.values())
      {
         result.add(obj.getNode());
      }

      return result;
   }

   public SharedSpaceSet withNode(ReplicationNode value)
   {
      for (SharedSpace obj : this.values())
      {
         obj.withNode(value);
      }

      return this;
   }

   public void add(SharedSpace value)
   {
      this.put("" + value.getSpaceId(), value);
   }

   public ReplicationChannelSet getChannels()
   {
      ReplicationChannelSet result = new ReplicationChannelSet();

      for (SharedSpace obj : this.values())
      {
         result.addAll(obj.getChannels());
      }

      return result;
   }

   public SharedSpaceSet withChannels(ReplicationChannel value)
   {
      for (SharedSpace obj : this.values())
      {
         obj.withChannels(value);
      }

      return this;
   }

   public SharedSpaceSet withoutChannels(ReplicationChannel value)
   {
      for (SharedSpace obj : this.values())
      {
         obj.withoutChannels(value);
      }

      return this;
   }

   public ChangeHistorySet getHistory()
   {
      ChangeHistorySet result = new ChangeHistorySet();

      for (SharedSpace obj : this.values())
      {
         result.add(obj.getHistory());
      }

      return result;
   }

   public SharedSpaceSet withHistory(ChangeHistory value)
   {
      for (SharedSpace obj : this.values())
      {
         obj.setHistory(value);
      }

      return this;
   }

   public longList getLastChangeId()
   {
      longList result = new longList();

      for (SharedSpace obj : this.values())
      {
         result.add(obj.getLastChangeId());
      }

      return result;
   }

   public SharedSpaceSet withLastChangeId(long value)
   {
      for (SharedSpace obj : this.values())
      {
         obj.setLastChangeId(value);
      }

      return this;
   }

   public StringList getNodeId()
   {
      StringList result = new StringList();

      for (SharedSpace obj : this.values())
      {
         result.add(obj.getNodeId());
      }

      return result;
   }

   public SharedSpaceSet withNodeId(String value)
   {
      for (SharedSpace obj : this.values())
      {
         obj.setNodeId(value);
      }

      return this;
   }

   public SharedSpacePO hasSharedSpacePO()
   {
      org.sdmlib.replication.creators.ModelPattern pattern = new org.sdmlib.replication.creators.ModelPattern();

      SharedSpacePO patternObject = pattern.hasElementSharedSpacePO();

      patternObject.withCandidates(this.clone());

      pattern.setHasMatch(true);
      pattern.findMatch();

      return patternObject;
   }

   public SharedSpaceSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.putAll((Map<String, SharedSpace>) value);
      }
      else if (value != null)
      {
         this.add((SharedSpace) value);
      }

      return this;
   }

   public SharedSpaceSet without(SharedSpace value)
   {
      this.remove(value);
      return this;
   }

}




