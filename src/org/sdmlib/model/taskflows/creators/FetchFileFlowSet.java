package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;

import org.sdmlib.model.taskflows.FetchFileFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;

public class FetchFileFlowSet extends LinkedHashSet<FetchFileFlow>
{
   
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

   public FetchFileFlowSet withFileServer(PeerProxy value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.withFileServer(value);
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

   public FetchFileFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.withIdMap(value);
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

   public FetchFileFlowSet withFileName(String value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.withFileName(value);
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

   public FetchFileFlowSet withTaskNo(int value)
   {
      for (FetchFileFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (FetchFileFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.FetchFileFlow";
   }


   public FetchFileFlowSet with(FetchFileFlow value)
   {
      this.add(value);
      return this;
   }
   
   public FetchFileFlowSet without(FetchFileFlow value)
   {
      this.remove(value);
      return this;
   }
}



