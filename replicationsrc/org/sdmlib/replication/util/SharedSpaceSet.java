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

import java.net.Socket;
import java.util.Collection;
import java.util.Collections;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.replication.ChangeHistory;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.ReplicationNode;
import org.sdmlib.replication.SharedSpace;

public class SharedSpaceSet extends SDMSet<SharedSpace>
{


   public SharedSpacePO hasSharedSpacePO()
   {
      return new SharedSpacePO(this.toArray(new SharedSpace[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.SharedSpace";
   }


   @SuppressWarnings("unchecked")
   public SharedSpaceSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SharedSpace>)value);
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

   public SocketSet getSocket()
   {
      SocketSet result = new SocketSet();
      
      for (SharedSpace obj : this)
      {
         result.add(obj.getSocket());
      }
      
      return result;
   }

   public SharedSpaceSet hasSocket(java.net.Socket value)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (value == obj.getSocket())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet withSocket(Socket value)
   {
      for (SharedSpace obj : this)
      {
         obj.setSocket(value);
      }
      
      return this;
   }

   public StringList getTargetNodeId()
   {
      StringList result = new StringList();
      
      for (SharedSpace obj : this)
      {
         result.add(obj.getTargetNodeId());
      }
      
      return result;
   }

   public SharedSpaceSet hasTargetNodeId(String value)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (value.equals(obj.getTargetNodeId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet hasTargetNodeId(String lower, String upper)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (lower.compareTo(obj.getTargetNodeId()) <= 0 && obj.getTargetNodeId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet withTargetNodeId(String value)
   {
      for (SharedSpace obj : this)
      {
         obj.setTargetNodeId(value);
      }
      
      return this;
   }

   public ReplicationNodeSet getNode()
   {
      ReplicationNodeSet result = new ReplicationNodeSet();
      
      for (SharedSpace obj : this)
      {
         result.add(obj.getNode());
      }
      
      return result;
   }

   public SharedSpaceSet hasNode(Object value)
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
      
      SharedSpaceSet answer = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (neighbors.contains(obj.getNode()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SharedSpaceSet withNode(ReplicationNode value)
   {
      for (SharedSpace obj : this)
      {
         obj.withNode(value);
      }
      
      return this;
   }

   public ReplicationChannelSet getChannels()
   {
      ReplicationChannelSet result = new ReplicationChannelSet();
      
      for (SharedSpace obj : this)
      {
         result.addAll(obj.getChannels());
      }
      
      return result;
   }

   public SharedSpaceSet hasChannels(Object value)
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
      
      SharedSpaceSet answer = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getChannels()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SharedSpaceSet withChannels(ReplicationChannel value)
   {
      for (SharedSpace obj : this)
      {
         obj.withChannels(value);
      }
      
      return this;
   }

   public SharedSpaceSet withoutChannels(ReplicationChannel value)
   {
      for (SharedSpace obj : this)
      {
         obj.withoutChannels(value);
      }
      
      return this;
   }

   public StringList getSpaceId()
   {
      StringList result = new StringList();
      
      for (SharedSpace obj : this)
      {
         result.add(obj.getSpaceId());
      }
      
      return result;
   }

   public SharedSpaceSet hasSpaceId(String value)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (value.equals(obj.getSpaceId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet hasSpaceId(String lower, String upper)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (lower.compareTo(obj.getSpaceId()) <= 0 && obj.getSpaceId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet withSpaceId(String value)
   {
      for (SharedSpace obj : this)
      {
         obj.setSpaceId(value);
      }
      
      return this;
   }

   public ChangeHistorySet getHistory()
   {
      ChangeHistorySet result = new ChangeHistorySet();
      
      for (SharedSpace obj : this)
      {
         result.add(obj.getHistory());
      }
      
      return result;
   }

   public SharedSpaceSet hasHistory(ChangeHistory value)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (value == obj.getHistory())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet withHistory(ChangeHistory value)
   {
      for (SharedSpace obj : this)
      {
         obj.setHistory(value);
      }
      
      return this;
   }

   public longList getLastChangeId()
   {
      longList result = new longList();
      
      for (SharedSpace obj : this)
      {
         result.add(obj.getLastChangeId());
      }
      
      return result;
   }

   public SharedSpaceSet hasLastChangeId(long value)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (value == obj.getLastChangeId())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet hasLastChangeId(long lower, long upper)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (lower <= obj.getLastChangeId() && obj.getLastChangeId() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet withLastChangeId(long value)
   {
      for (SharedSpace obj : this)
      {
         obj.setLastChangeId(value);
      }
      
      return this;
   }

   public StringList getNodeId()
   {
      StringList result = new StringList();
      
      for (SharedSpace obj : this)
      {
         result.add(obj.getNodeId());
      }
      
      return result;
   }

   public SharedSpaceSet hasNodeId(String value)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (value.equals(obj.getNodeId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet hasNodeId(String lower, String upper)
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (SharedSpace obj : this)
      {
         if (lower.compareTo(obj.getNodeId()) <= 0 && obj.getNodeId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceSet withNodeId(String value)
   {
      for (SharedSpace obj : this)
      {
         obj.setNodeId(value);
      }
      
      return this;
   }
}
