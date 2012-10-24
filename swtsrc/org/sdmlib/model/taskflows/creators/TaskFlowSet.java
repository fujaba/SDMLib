package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.LogEntry;

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
         obj.withIdMap(value);
      }
      
      return this;
   }

   public LoggerSet getLogger()
   {
      LoggerSet result = new LoggerSet();
      
      for (TaskFlow obj : this)
      {
         result.add(obj.getLogger());
      }
      
      return result;
   }
   public TaskFlowSet withLogger(Logger value)
   {
      for (TaskFlow obj : this)
      {
         obj.withLogger(value);
      }
      
      return this;
   }

}





