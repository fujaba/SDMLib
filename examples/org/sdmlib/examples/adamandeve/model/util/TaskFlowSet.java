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
   
package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.logger.TaskFlow;
import java.util.Collection;
import org.sdmlib.models.modelsets.intList;

public class TaskFlowSet extends SDMSet<TaskFlow>
{


   public TaskFlowPO hasTaskFlowPO()
   {
      return new TaskFlowPO(this.toArray(new TaskFlow[this.size()]));
   }


   @Override
   public String getEntryType()
   {
      return "org.sdmlib.logger.TaskFlow";
   }


   @SuppressWarnings("unchecked")
   public TaskFlowSet with(Object value)
   {
      if (value instanceof java.util.Collection)
      {
         this.addAll((Collection<TaskFlow>)value);
      }
      else if (value != null)
      {
         this.add((TaskFlow) value);
      }
      
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

   public TaskFlowSet hasTaskNo(int value)
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (TaskFlow obj : this)
      {
         if (value == obj.getTaskNo())
         {
            result.add(obj);
         }
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

}
