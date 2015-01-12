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
import org.sdmlib.replication.SeppelUser;
import java.util.Collection;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.replication.util.SeppelSpaceProxySet;
import org.sdmlib.replication.SeppelSpaceProxy;
import java.util.Collections;
import org.sdmlib.replication.util.SeppelScopeSet;
import org.sdmlib.replication.SeppelScope;

public class SeppelUserSet extends SDMSet<SeppelUser>
{

   public static final SeppelUserSet EMPTY_SET = new SeppelUserSet().withReadonly(true);


   public SeppelUserPO hasSeppelUserPO()
   {
      return new SeppelUserPO(this.toArray(new SeppelUser[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.replication.SeppelUser";
   }


   @SuppressWarnings("unchecked")
   public SeppelUserSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SeppelUser>)value);
      }
      else if (value != null)
      {
         this.add((SeppelUser) value);
      }
      
      return this;
   }
   
   public SeppelUserSet without(SeppelUser value)
   {
      this.remove(value);
      return this;
   }

   public StringList getLoginName()
   {
      StringList result = new StringList();
      
      for (SeppelUser obj : this)
      {
         result.add(obj.getLoginName());
      }
      
      return result;
   }

   public SeppelUserSet hasLoginName(String value)
   {
      SeppelUserSet result = new SeppelUserSet();
      
      for (SeppelUser obj : this)
      {
         if (value.equals(obj.getLoginName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelUserSet hasLoginName(String lower, String upper)
   {
      SeppelUserSet result = new SeppelUserSet();
      
      for (SeppelUser obj : this)
      {
         if (lower.compareTo(obj.getLoginName()) <= 0 && obj.getLoginName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelUserSet withLoginName(String value)
   {
      for (SeppelUser obj : this)
      {
         obj.setLoginName(value);
      }
      
      return this;
   }

   public StringList getPassword()
   {
      StringList result = new StringList();
      
      for (SeppelUser obj : this)
      {
         result.add(obj.getPassword());
      }
      
      return result;
   }

   public SeppelUserSet hasPassword(String value)
   {
      SeppelUserSet result = new SeppelUserSet();
      
      for (SeppelUser obj : this)
      {
         if (value.equals(obj.getPassword()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelUserSet hasPassword(String lower, String upper)
   {
      SeppelUserSet result = new SeppelUserSet();
      
      for (SeppelUser obj : this)
      {
         if (lower.compareTo(obj.getPassword()) <= 0 && obj.getPassword().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelUserSet withPassword(String value)
   {
      for (SeppelUser obj : this)
      {
         obj.setPassword(value);
      }
      
      return this;
   }

   public SeppelSpaceProxySet getMasterSpace()
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelUser obj : this)
      {
         result.add(obj.getMasterSpace());
      }
      
      return result;
   }

   public SeppelUserSet hasMasterSpace(Object value)
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
      
      SeppelUserSet answer = new SeppelUserSet();
      
      for (SeppelUser obj : this)
      {
         if (neighbors.contains(obj.getMasterSpace()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelUserSet withMasterSpace(SeppelSpaceProxy value)
   {
      for (SeppelUser obj : this)
      {
         obj.withMasterSpace(value);
      }
      
      return this;
   }

   public SeppelSpaceProxySet getSpaces()
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelUser obj : this)
      {
         result.addAll(obj.getSpaces());
      }
      
      return result;
   }

   public SeppelUserSet hasSpaces(Object value)
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
      
      SeppelUserSet answer = new SeppelUserSet();
      
      for (SeppelUser obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSpaces()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelUserSet withSpaces(SeppelSpaceProxy value)
   {
      for (SeppelUser obj : this)
      {
         obj.withSpaces(value);
      }
      
      return this;
   }

   public SeppelUserSet withoutSpaces(SeppelSpaceProxy value)
   {
      for (SeppelUser obj : this)
      {
         obj.withoutSpaces(value);
      }
      
      return this;
   }

   public SeppelScopeSet getScopes()
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelUser obj : this)
      {
         result.addAll(obj.getScopes());
      }
      
      return result;
   }

   public SeppelUserSet hasScopes(Object value)
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
      
      SeppelUserSet answer = new SeppelUserSet();
      
      for (SeppelUser obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getScopes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelUserSet withScopes(SeppelScope value)
   {
      for (SeppelUser obj : this)
      {
         obj.withScopes(value);
      }
      
      return this;
   }

   public SeppelUserSet withoutScopes(SeppelScope value)
   {
      for (SeppelUser obj : this)
      {
         obj.withoutScopes(value);
      }
      
      return this;
   }

}
