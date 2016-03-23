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
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.replication.SeppelScope;
import org.sdmlib.replication.SeppelSpaceProxy;

import de.uniks.networkparser.list.SimpleSet;
import org.sdmlib.replication.util.SeppelSpaceProxySet;

public class SeppelScopeSet extends SimpleSet<SeppelScope>
{

   public static final SeppelScopeSet EMPTY_SET = new SeppelScopeSet().withFlag(SeppelScopeSet.READONLY);


   public SeppelScopePO hasSeppelScopePO()
   {
      return new SeppelScopePO(this.toArray(new SeppelScope[this.size()]));
   }

   @SuppressWarnings("unchecked")
   public SeppelScopeSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<SeppelScope>)value);
      }
      else if (value != null)
      {
         this.add((SeppelScope) value);
      }
      
      return this;
   }
   
   public SeppelScopeSet without(SeppelScope value)
   {
      this.remove(value);
      return this;
   }

   public StringList getScopeName()
   {
      StringList result = new StringList();
      
      for (SeppelScope obj : this)
      {
         result.add(obj.getScopeName());
      }
      
      return result;
   }

   public SeppelScopeSet hasScopeName(String value)
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if (value.equals(obj.getScopeName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelScopeSet hasScopeName(String lower, String upper)
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if (lower.compareTo(obj.getScopeName()) <= 0 && obj.getScopeName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public SeppelScopeSet withScopeName(String value)
   {
      for (SeppelScope obj : this)
      {
         obj.setScopeName(value);
      }
      
      return this;
   }

   public SeppelScopeSet getSubScopes()
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         result.addAll(obj.getSubScopes());
      }
      
      return result;
   }

   public SeppelScopeSet hasSubScopes(Object value)
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
      
      SeppelScopeSet answer = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSubScopes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public SeppelScopeSet getSubScopesTransitive()
   {
      SeppelScopeSet todo = new SeppelScopeSet().with(this);
      
      SeppelScopeSet result = new SeppelScopeSet();
      
      while ( ! todo.isEmpty())
      {
         SeppelScope current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getSubScopes().minus(result));
         }
      }
      
      return result;
   }

   public SeppelScopeSet withSubScopes(SeppelScope value)
   {
      for (SeppelScope obj : this)
      {
         obj.withSubScopes(value);
      }
      
      return this;
   }

   public SeppelScopeSet withoutSubScopes(SeppelScope value)
   {
      for (SeppelScope obj : this)
      {
         obj.withoutSubScopes(value);
      }
      
      return this;
   }

   public SeppelScopeSet getSuperScopes()
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         result.addAll(obj.getSuperScopes());
      }
      
      return result;
   }

   public SeppelScopeSet hasSuperScopes(Object value)
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
      
      SeppelScopeSet answer = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSuperScopes()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public SeppelScopeSet getSuperScopesTransitive()
   {
      SeppelScopeSet todo = new SeppelScopeSet().with(this);
      
      SeppelScopeSet result = new SeppelScopeSet();
      
      while ( ! todo.isEmpty())
      {
         SeppelScope current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            todo.with(current.getSuperScopes().minus(result));
         }
      }
      
      return result;
   }

   public SeppelScopeSet withSuperScopes(SeppelScope value)
   {
      for (SeppelScope obj : this)
      {
         obj.withSuperScopes(value);
      }
      
      return this;
   }

   public SeppelScopeSet withoutSuperScopes(SeppelScope value)
   {
      for (SeppelScope obj : this)
      {
         obj.withoutSuperScopes(value);
      }
      
      return this;
   }

   public SeppelSpaceProxySet getSpaces()
   {
      SeppelSpaceProxySet result = new SeppelSpaceProxySet();
      
      for (SeppelScope obj : this)
      {
         result.addAll(obj.getSpaces());
      }
      
      return result;
   }

   public SeppelScopeSet hasSpaces(Object value)
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
      
      SeppelScopeSet answer = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getSpaces()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelScopeSet withSpaces(SeppelSpaceProxy value)
   {
      for (SeppelScope obj : this)
      {
         obj.withSpaces(value);
      }
      
      return this;
   }

   public SeppelScopeSet withoutSpaces(SeppelSpaceProxy value)
   {
      for (SeppelScope obj : this)
      {
         obj.withoutSpaces(value);
      }
      
      return this;
   }

   public ObjectSet getObservedObjects()
   {
      ObjectSet result = new ObjectSet();
      
      for (SeppelScope obj : this)
      {
         result.addAll(obj.getObservedObjects());
      }
      
      return result;
   }

   public SeppelScopeSet hasObservedObjects(Object value)
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
      
      SeppelScopeSet answer = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if ( ! Collections.disjoint(neighbors, obj.getObservedObjects()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }

   public SeppelScopeSet withObservedObjects(Object value)
   {
      for (SeppelScope obj : this)
      {
         obj.withObservedObjects(value);
      }
      
      return this;
   }

   public SeppelScopeSet withoutObservedObjects(Object value)
   {
      for (SeppelScope obj : this)
      {
         obj.withoutObservedObjects(value);
      }
      
      return this;
   }


   public boolean containsObservedObjects(Object targetObject)
   {
      for (SeppelScope scope : this)
      {
         if (scope.getObservedObjects().contains(targetObject))
         {
            return true;
         }
      }
      
      return false;
   }



   public SeppelScopePO filterSeppelScopePO()
   {
      return new SeppelScopePO(this.toArray(new SeppelScope[this.size()]));
   }


   public String getEntryType()
   {
      return "org.sdmlib.replication.SeppelScope";
   }

   /**
    * Loop through the current set of SeppelScope objects and collect those SeppelScope objects where the scopeName attribute matches the parameter value. 
    * 
    * @param value Search value
    * 
    * @return Subset of SeppelScope objects that match the parameter
    */
   public SeppelScopeSet filterScopeName(String value)
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if (value.equals(obj.getScopeName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }


   /**
    * Loop through the current set of SeppelScope objects and collect those SeppelScope objects where the scopeName attribute is between lower and upper. 
    * 
    * @param lower Lower bound 
    * @param upper Upper bound 
    * 
    * @return Subset of SeppelScope objects that match the parameter
    */
   public SeppelScopeSet filterScopeName(String lower, String upper)
   {
      SeppelScopeSet result = new SeppelScopeSet();
      
      for (SeppelScope obj : this)
      {
         if (lower.compareTo(obj.getScopeName()) <= 0 && obj.getScopeName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

}
