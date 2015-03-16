/*
   Copyright (c) 2015 zuendorf 
   
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
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.booleanList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.replication.SeppelChannel;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpaceProxy;

public class SeppelSpaceProxySet extends SDMSet<SeppelSpaceProxy>
{

   public static final SeppelSpaceProxySet EMPTY_SET = new SeppelSpaceProxySet().withReadOnly(true);


   public SeppelSpaceProxyPO hasSeppelSpaceProxyPO()
   {
      return new SeppelSpaceProxyPO(this.toArray(new SeppelSpaceProxy[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.SeppelSpaceProxy";
   }


   @SuppressWarnings("unchecked")
   public SeppelSpaceProxySet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SeppelSpaceProxy>)value);
      }
      else if (value != null)
      {
         this.add((SeppelSpaceProxy) value);
      }
      
      return this;
   }
   
   public SeppelSpaceProxySet without(SeppelSpaceProxy value)
   {
      this.remove(value);
      return this;
   }

   public StringList getSpaceId()
   {
      StringList result = new StringList();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.add(obj.getSpaceId());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasSpaceId(String value)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (value.equals(obj.getSpaceId()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasSpaceId(String lower, String upper)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getSpaceId()) <= 0 && obj.getSpaceId().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet withSpaceId(String value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.setSpaceId(value);
      }
      
      return this;
   }

   public booleanList getAcceptsConnectionRequests()
   {
      booleanList result = new booleanList();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.add(obj.isAcceptsConnectionRequests());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasAcceptsConnectionRequests(boolean value)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (value == obj.isAcceptsConnectionRequests())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet withAcceptsConnectionRequests(boolean value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.setAcceptsConnectionRequests(value);
      }
      
      return this;
   }

   public StringList getHostName()
   {
      StringList result = new StringList();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.add(obj.getHostName());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasHostName(String value)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (value.equals(obj.getHostName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasHostName(String lower, String upper)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getHostName()) <= 0 && obj.getHostName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet withHostName(String value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.setHostName(value);
      }
      
      return this;
   }

   public intList getPortNo()
   {
      intList result = new intList();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.add(obj.getPortNo());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasPortNo(int value)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (value == obj.getPortNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasPortNo(int lower, int upper)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (lower <= obj.getPortNo() && obj.getPortNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet withPortNo(int value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.setPortNo(value);
      }
      
      return this;
   }

   public StringList getLoginName()
   {
      StringList result = new StringList();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.add(obj.getLoginName());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasLoginName(String value)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (value.equals(obj.getLoginName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasLoginName(String lower, String upper)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getLoginName()) <= 0 && obj.getLoginName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet withLoginName(String value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.setLoginName(value);
      }
      
      return this;
   }

   public StringList getPassword()
   {
      StringList result = new StringList();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.add(obj.getPassword());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasPassword(String value)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (value.equals(obj.getPassword()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasPassword(String lower, String upper)
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (lower.compareTo(obj.getPassword()) <= 0 && obj.getPassword().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet withPassword(String value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.setPassword(value);
      }
      
      return this;
   }

   public SeppelSpaceProxySet getPartners()
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.addAll(obj.getPartners());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasPartners(Object value)
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
      
      SeppelSpaceProxySet answer = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getPartners()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public SeppelSpaceProxySet getPartnersTransitive()
   {
      SeppelSpaceProxySet todo = new SeppelSpaceProxySet().with(this);
      
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      while ( ! todo.isEmpty())
      {
         SeppelSpaceProxy current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getPartners().minus(result));
         }
      }
      
      return result;
   }

   public SeppelSpaceProxySet withPartners(SeppelSpaceProxy value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.withPartners(value);
      }
      
      return this;
   }

   public SeppelSpaceProxySet withoutPartners(SeppelSpaceProxy value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.withoutPartners(value);
      }
      
      return this;
   }

   public SeppelScopeSet getScopes()
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.addAll(obj.getScopes());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasScopes(Object value)
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
      
      SeppelSpaceProxySet answer = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getScopes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelSpaceProxySet withScopes(SeppelScope value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.withScopes(value);
      }
      
      return this;
   }

   public SeppelSpaceProxySet withoutScopes(SeppelScope value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.withoutScopes(value);
      }
      
      return this;
   }

   public SeppelChannelSet getChannel()
   {
      SeppelChannelSet result = new SeppelChannelSet();
      
      for (SeppelSpaceProxy obj : this)
      {
         result.add(obj.getChannel());
      }
      
      return result;
   }

   public SeppelSpaceProxySet hasChannel(Object value)
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
      
      SeppelSpaceProxySet answer = new SeppelSpaceProxySet();
      
      for (SeppelSpaceProxy obj : this)
      {
         if (neighbors.contains(obj.getChannel()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelSpaceProxySet withChannel(SeppelChannel value)
   {
      for (SeppelSpaceProxy obj : this)
      {
         obj.withChannel(value);
      }
      
      return this;
   }

}
