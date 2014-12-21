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

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.replication.SharedSpaceProxy;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.longList;
import org.sdmlib.models.modelsets.ObjectSet;
import java.util.Collections;
import org.sdmlib.replication.util.SharedSpaceProxySet;
import org.sdmlib.replication.util.ReplicationChannelSet;
import org.sdmlib.replication.ReplicationChannel;

public class SharedSpaceProxySet extends SDMSet<SharedSpaceProxy>
{


   public SharedSpaceProxyPO hasSharedSpaceProxyPO()
   {
      return new SharedSpaceProxyPO(this.toArray(new SharedSpaceProxy[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.SharedSpaceProxy";
   }


   @SuppressWarnings("unchecked")
   public SharedSpaceProxySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SharedSpaceProxy>)value);
      }
      else if (value != null)
      {
         this.add((SharedSpaceProxy) value);
      }
      
      return this;
   }
   
   public SharedSpaceProxySet without(SharedSpaceProxy value)
   {
      this.remove(value);
      return this;
   }

   public StringList getSpaceId()
   {
      StringList result = new StringList();
      
      for (SharedSpaceProxy obj : this)
      {
         result.add(obj.getSpaceId());
      }
      
      return result;
   }

   public SharedSpaceProxySet hasSpaceId(String value)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (value.equals(obj.getSpaceId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet hasSpaceId(String lower, String upper)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getSpaceId()) <= 0 && obj.getSpaceId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet withSpaceId(String value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.setSpaceId(value);
      }
      
      return this;
   }

   public StringList getPassword()
   {
      StringList result = new StringList();
      
      for (SharedSpaceProxy obj : this)
      {
         result.add(obj.getPassword());
      }
      
      return result;
   }

   public SharedSpaceProxySet hasPassword(String value)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (value.equals(obj.getPassword()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet hasPassword(String lower, String upper)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getPassword()) <= 0 && obj.getPassword().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet withPassword(String value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.setPassword(value);
      }
      
      return this;
   }

   public booleanList getAcceptsConnectionRequests()
   {
      booleanList result = new booleanList();
      
      for (SharedSpaceProxy obj : this)
      {
         result.add(obj.isAcceptsConnectionRequests());
      }
      
      return result;
   }

   public SharedSpaceProxySet hasAcceptsConnectionRequests(boolean value)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (value == obj.isAcceptsConnectionRequests())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet withAcceptsConnectionRequests(boolean value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.setAcceptsConnectionRequests(value);
      }
      
      return this;
   }

   public StringList getHostName()
   {
      StringList result = new StringList();
      
      for (SharedSpaceProxy obj : this)
      {
         result.add(obj.getHostName());
      }
      
      return result;
   }

   public SharedSpaceProxySet hasHostName(String value)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet hasHostName(String lower, String upper)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet withHostName(String value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.setHostName(value);
      }
      
      return this;
   }

   public longList getPortNo()
   {
      longList result = new longList();
      
      for (SharedSpaceProxy obj : this)
      {
         result.add(obj.getPortNo());
      }
      
      return result;
   }

   public SharedSpaceProxySet hasPortNo(long value)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (value == obj.getPortNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet hasPortNo(long lower, long upper)
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (lower <= obj.getPortNo() && obj.getPortNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet withPortNo(long value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.setPortNo(value);
      }
      
      return this;
   }

   public SharedSpaceProxySet getPartners()
   {
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         result.addAll(obj.getPartners());
      }
      
      return result;
   }

   public SharedSpaceProxySet hasPartners(Object value)
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
      
      SharedSpaceProxySet answer = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPartners()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public SharedSpaceProxySet getPartnersTransitive()
   {
      SharedSpaceProxySet todo = new SharedSpaceProxySet().with(this);
      
      SharedSpaceProxySet result = new SharedSpaceProxySet();
      
      while ( ! todo.isEmpty())
      {
         SharedSpaceProxy current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getPartners().minus(result));
         }
      }
      
      return result;
   }

   public SharedSpaceProxySet withPartners(SharedSpaceProxy value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.withPartners(value);
      }
      
      return this;
   }

   public SharedSpaceProxySet withoutPartners(SharedSpaceProxy value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.withoutPartners(value);
      }
      
      return this;
   }

   public ReplicationChannelSet getChannel()
   {
      ReplicationChannelSet result = new ReplicationChannelSet();
      
      for (SharedSpaceProxy obj : this)
      {
         result.add(obj.getChannel());
      }
      
      return result;
   }

   public SharedSpaceProxySet hasChannel(Object value)
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
      
      SharedSpaceProxySet answer = new SharedSpaceProxySet();
      
      for (SharedSpaceProxy obj : this)
      {
         if (neighbors.contains(obj.getChannel()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SharedSpaceProxySet withChannel(ReplicationChannel value)
   {
      for (SharedSpaceProxy obj : this)
      {
         obj.withChannel(value);
      }
      
      return this;
   }

}
