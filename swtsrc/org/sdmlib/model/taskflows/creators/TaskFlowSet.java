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
   
package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.model.taskflows.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;

public class TaskFlowSet extends LinkedHashSet<TaskFlow> implements org.sdmlib.models.modelsets.ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (TaskFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.TaskFlow";
   }


   public TaskFlowSet with(TaskFlow value)
   {
      this.add(value);
      return this;
   }
   
   public TaskFlowSet without(TaskFlow value)
   {
      this.remove(value);
      return this;
   }
   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public TaskFlowSet withTaskNo(int value)
   {
      for (TaskFlow obj : this)
      {
         obj.setTaskNo(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public TaskFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (TaskFlow obj : this)
      {
         obj.setIdMap(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public TaskFlowSet withSubFlow(TaskFlow value)
   {
      for (TaskFlow obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public TaskFlowSet withParent(TaskFlow value)
   {
      for (TaskFlow obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}

