package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.modelsets.intList;
import java.util.List;

public class TaskFlowSet extends LinkedHashSet<TaskFlow>
{
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
         obj.withTaskNo(value);
      }
      
      return this;
   }

}



