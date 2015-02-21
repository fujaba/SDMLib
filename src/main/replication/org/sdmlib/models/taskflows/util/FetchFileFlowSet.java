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
   
package org.sdmlib.models.taskflows.util;

import java.util.Collection;

import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.models.taskflows.FetchFileFlow;
import org.sdmlib.models.taskflows.PeerProxy;
import org.sdmlib.models.taskflows.TaskFlow;
import org.sdmlib.serialization.SDMLibJsonIdMap;

public class FetchFileFlowSet extends SDMSet<FetchFileFlow>
{


   public FetchFileFlowPO hasFetchFileFlowPO()
   {
      return new FetchFileFlowPO(this.toArray(new FetchFileFlow[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.models.taskflows.FetchFileFlow";
   }


   @SuppressWarnings("unchecked")
   public FetchFileFlowSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<FetchFileFlow>)value);
      }
      else if (value != null)
      {
         this.add((FetchFileFlow) value);
      }
      
      return this;
   }
   
   public FetchFileFlowSet without(FetchFileFlow value)
   {
      this.remove(value);
      return this;
   }

   
   //==========================================================================
   
   public FetchFileFlowSet run()
   {
      for (FetchFileFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public PeerProxySet getFileServer()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (FetchFileFlow obj : this)
      {
         result.add(obj.getFileServer());
      }
      
      return result;
   }

   public FetchFileFlowSet hasFileServer(org.sdmlib.models.taskflows.PeerProxy value)
   {
      FetchFileFlowSet result = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (value == obj.getFileServer())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FetchFileFlowSet withFileServer(PeerProxy value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.setFileServer(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (FetchFileFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public FetchFileFlowSet hasIdMap(org.sdmlib.serialization.SDMLibJsonIdMap value)
   {
      FetchFileFlowSet result = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (value == obj.getIdMap())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FetchFileFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

   public StringList getFileName()
   {
      StringList result = new StringList();
      
      for (FetchFileFlow obj : this)
      {
         result.add(obj.getFileName());
      }
      
      return result;
   }

   public FetchFileFlowSet hasFileName(String value)
   {
      FetchFileFlowSet result = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (value.equals(obj.getFileName()))
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FetchFileFlowSet hasFileName(String lower, String upper)
   {
      FetchFileFlowSet result = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (lower.compareTo(obj.getFileName()) <= 0 && obj.getFileName().compareTo(upper) <= 0)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FetchFileFlowSet withFileName(String value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.setFileName(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (FetchFileFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public FetchFileFlowSet hasTaskNo(int value)
   {
      FetchFileFlowSet result = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FetchFileFlowSet hasTaskNo(int lower, int upper)
   {
      FetchFileFlowSet result = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (lower <= obj.getTaskNo() && obj.getTaskNo() <= upper)
         {
            result.add(obj);
         }
      }
      
      return result;
   }

   public FetchFileFlowSet withTaskNo(int value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.setTaskNo(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public FetchFileFlowSet hasSubFlow(Object value)
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
      
      FetchFileFlowSet answer = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (neighbors.contains(obj.getSubFlow()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public TaskFlowSet getSubFlowTransitive()
   {
      TaskFlowSet todo = new TaskFlowSet().with(this);
      
      TaskFlowSet result = new TaskFlowSet();
      
      while ( ! todo.isEmpty())
      {
         TaskFlow current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getSubFlow()))
            {
               todo.with(current.getSubFlow());
            }
         }
      }
      
      return result;
   }

   public FetchFileFlowSet withSubFlow(TaskFlow value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public FetchFileFlowSet hasParent(Object value)
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
      
      FetchFileFlowSet answer = new FetchFileFlowSet();
      
      for (FetchFileFlow obj : this)
      {
         if (neighbors.contains(obj.getParent()))
         {
            answer.add(obj);
         }
      }
      
      return answer;
   }


   public TaskFlowSet getParentTransitive()
   {
      TaskFlowSet todo = new TaskFlowSet().with(this);
      
      TaskFlowSet result = new TaskFlowSet();
      
      while ( ! todo.isEmpty())
      {
         TaskFlow current = todo.first();
         
         todo.remove(current);
         
         if ( ! result.contains(current))
         {
            result.add(current);
            
            if ( ! result.contains(current.getParent()))
            {
               todo.with(current.getParent());
            }
         }
      }
      
      return result;
   }

   public FetchFileFlowSet withParent(TaskFlow value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }


   public static final FetchFileFlowSet EMPTY_SET = new FetchFileFlowSet().withReadonly(true);
}
