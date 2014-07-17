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

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.replication.ReplicationChannel;
import org.sdmlib.replication.SharedSpace;
import org.sdmlib.replication.util.SharedSpaceSet;

public class ReplicationChannelSet extends SDMSet<ReplicationChannel>
{


   public ReplicationChannelPO hasReplicationChannelPO()
   {
      return new ReplicationChannelPO(this.toArray(new ReplicationChannel[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.ReplicationChannel";
   }


   @SuppressWarnings("unchecked")
   public ReplicationChannelSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReplicationChannel>)value);
      }
      else if (value != null)
      {
         this.add((ReplicationChannel) value);
      }
      
      return this;
   }
   
   public ReplicationChannelSet without(ReplicationChannel value)
   {
      this.remove(value);
      return this;
   }

   public SharedSpaceSet getSharedSpace()
   {
      SharedSpaceSet result = new SharedSpaceSet();
      
      for (ReplicationChannel obj : this)
      {
         result.add(obj.getSharedSpace());
      }
      
      return result;
   }

   public ReplicationChannelSet hasSharedSpace(Object value)
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
      
      ReplicationChannelSet answer = new ReplicationChannelSet();
      
      for (ReplicationChannel obj : this)
      {
         if (neighbors.contains(obj.getSharedSpace()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public ReplicationChannelSet withSharedSpace(SharedSpace value)
   {
      for (ReplicationChannel obj : this)
      {
         obj.withSharedSpace(value);
      }
      
      return this;
   }

   public SocketSet getSocket()
   {
      SocketSet result = new SocketSet();
      
      for (ReplicationChannel obj : this)
      {
         result.add(obj.getSocket());
      }
      
      return result;
   }

   public ReplicationChannelSet hasSocket(java.net.Socket value)
   {
      ReplicationChannelSet result = new ReplicationChannelSet();
      
      for (ReplicationChannel obj : this)
      {
         if (value == obj.getSocket())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChannelSet withSocket(Socket value)
   {
      for (ReplicationChannel obj : this)
      {
         obj.setSocket(value);
      }
      
      return this;
   }

   public StringList getTargetNodeId()
   {
      StringList result = new StringList();
      
      for (ReplicationChannel obj : this)
      {
         result.add(obj.getTargetNodeId());
      }
      
      return result;
   }

   public ReplicationChannelSet hasTargetNodeId(String value)
   {
      ReplicationChannelSet result = new ReplicationChannelSet();
      
      for (ReplicationChannel obj : this)
      {
         if (value.equals(obj.getTargetNodeId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChannelSet hasTargetNodeId(String lower, String upper)
   {
      ReplicationChannelSet result = new ReplicationChannelSet();
      
      for (ReplicationChannel obj : this)
      {
         if (lower.compareTo(obj.getTargetNodeId()) <= 0 && obj.getTargetNodeId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public ReplicationChannelSet withTargetNodeId(String value)
   {
      for (ReplicationChannel obj : this)
      {
         obj.setTargetNodeId(value);
      }
      
      return this;
   }

}
