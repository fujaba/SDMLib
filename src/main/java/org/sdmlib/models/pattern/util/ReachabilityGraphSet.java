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
   
package org.sdmlib.models.pattern.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.util.ReachableStateSet;
import org.sdmlib.models.pattern.util.PatternSet;

public class ReachabilityGraphSet extends SDMSet<ReachabilityGraph> implements org.sdmlib.models.modelsets.ModelSet
{
   private static final long serialVersionUID = 1L;



   @Override
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ReachabilityGraph elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.ReachabilityGraph";
   }

   public ReachableStateSet getStates()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachabilityGraph obj : this)
      {
         result.addAll(obj.getStates());
      }
      
      return result;
   }

   public ReachabilityGraphSet withStates(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withStates(value);
      }
      
      return this;
   }

   public ReachabilityGraphSet withoutStates(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withoutStates(value);
      }
      
      return this;
   }

   public ReachableStateSet getTodo()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachabilityGraph obj : this)
      {
         result.addAll(obj.getTodo());
      }
      
      return result;
   }

   public ReachabilityGraphSet withTodo(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withTodo(value);
      }
      
      return this;
   }

   public ReachabilityGraphSet withoutTodo(ReachableState value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withoutTodo(value);
      }
      
      return this;
   }

   public PatternSet getRules()
   {
      PatternSet result = new PatternSet();
      
      for (ReachabilityGraph obj : this)
      {
         result.addAll(obj.getRules());
      }
      
      return result;
   }

   public ReachabilityGraphSet withRules(Pattern value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withRules(value);
      }
      
      return this;
   }

   public ReachabilityGraphSet withoutRules(Pattern value)
   {
      for (ReachabilityGraph obj : this)
      {
         obj.withoutRules(value);
      }
      
      return this;
   }



   public ReachabilityGraphPO startModelPattern()
   {
      return new ReachabilityGraphPO(this.toArray(new ReachabilityGraph[this.size()]));
   }


   public ReachabilityGraphSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<ReachabilityGraph>)value);
      }
      else if (value != null)
      {
         this.add((ReachabilityGraph) value);
      }
      
      return this;
   }
   
   public ReachabilityGraphSet without(ReachabilityGraph value)
   {
      this.remove(value);
      return this;
   }



   public ReachabilityGraphPO hasReachabilityGraphPO()
   {
      return new ReachabilityGraphPO(this.toArray(new ReachabilityGraph[this.size()]));
   }

   public static final ReachabilityGraphSet EMPTY_SET = new ReachabilityGraphSet().withReadOnly(true);
}
