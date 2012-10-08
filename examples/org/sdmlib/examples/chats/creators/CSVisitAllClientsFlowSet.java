package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.CSVisitAllClientsFlow;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.modelsets.intList;
import java.util.List;
import org.sdmlib.examples.chats.CSClientTask;

public class CSVisitAllClientsFlowSet extends LinkedHashSet<CSVisitAllClientsFlow>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (CSVisitAllClientsFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public CSVisitAllClientsFlowSet with(CSVisitAllClientsFlow value)
   {
      this.add(value);
      return this;
   }
   
   public CSVisitAllClientsFlowSet without(CSVisitAllClientsFlow value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public CSVisitAllClientsFlowSet run()
   {
      for (CSVisitAllClientsFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (CSVisitAllClientsFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public CSVisitAllClientsFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (CSVisitAllClientsFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (CSVisitAllClientsFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public CSVisitAllClientsFlowSet withTaskNo(int value)
   {
      for (CSVisitAllClientsFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public CSClientTaskSet getTgtTask()
   {
      CSClientTaskSet result = new CSClientTaskSet();
      
      for (CSVisitAllClientsFlow obj : this)
      {
         result.add(obj.getTgtTask());
      }
      
      return result;
   }
   public CSVisitAllClientsFlowSet withTgtTask(CSClientTask value)
   {
      for (CSVisitAllClientsFlow obj : this)
      {
         obj.withTgtTask(value);
      }
      
      return this;
   }

}

