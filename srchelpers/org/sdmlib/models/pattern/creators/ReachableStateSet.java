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
   
package org.sdmlib.models.pattern.creators;

import java.util.LinkedHashSet;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.pattern.creators.ReachabilityGraphSet;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.creators.ReachableStateSet;

public class ReachableStateSet extends LinkedHashSet<ReachableState> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ReachableState elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.models.pattern.ReachableState";
   }


   public ReachableStateSet with(ReachableState value)
   {
      this.add(value);
      return this;
   }
   
   public ReachableStateSet without(ReachableState value)
   {
      this.remove(value);
      return this;
   }
   public ReachabilityGraphSet getParent()
   {
      ReachabilityGraphSet result = new ReachabilityGraphSet();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public ReachableStateSet withParent(ReachabilityGraph value)
   {
      for (ReachableState obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

   public ReachableStateSet getSuccessor()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         result.addAll(obj.getSuccessor());
      }
      
      return result;
   }

   public ReachableStateSet withSuccessor(ReachableState value)
   {
      for (ReachableState obj : this)
      {
         obj.withSuccessor(value);
      }
      
      return this;
   }

   public ReachableStateSet withoutSuccessor(ReachableState value)
   {
      for (ReachableState obj : this)
      {
         obj.withoutSuccessor(value);
      }
      
      return this;
   }

   public ReachableStateSet getPredecessor()
   {
      ReachableStateSet result = new ReachableStateSet();
      
      for (ReachableState obj : this)
      {
         result.addAll(obj.getPredecessor());
      }
      
      return result;
   }

   public ReachableStateSet withPredecessor(ReachableState value)
   {
      for (ReachableState obj : this)
      {
         obj.withPredecessor(value);
      }
      
      return this;
   }

   public ReachableStateSet withoutPredecessor(ReachableState value)
   {
      for (ReachableState obj : this)
      {
         obj.withoutPredecessor(value);
      }
      
      return this;
   }

   public ReachabilityGraphSet getMaster()
   {
      ReachabilityGraphSet result = new ReachabilityGraphSet();
      
      for (ReachableState obj : this)
      {
         result.add(obj.getMaster());
      }
      
      return result;
   }

   public ReachableStateSet withMaster(ReachabilityGraph value)
   {
      for (ReachableState obj : this)
      {
         obj.withMaster(value);
      }
      
      return this;
   }


   public ReachableState first()
   {
      for (ReachableState s : this)
      {
         return s;
      }
      
      return null;
   }

}


