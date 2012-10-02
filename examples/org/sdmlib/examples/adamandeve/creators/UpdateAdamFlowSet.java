package org.sdmlib.examples.adamandeve.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.adamandeve.UpdateAdamFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.models.modelsets.longList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.serialization.json.creators.JsonIdMapSet;

public class UpdateAdamFlowSet extends LinkedHashSet<UpdateAdamFlow>
{
   
   //==========================================================================
   
   public UpdateAdamFlowSet run()
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public UpdateAdamFlowSet withAdam(PeerProxy value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.withAdam(value);
      }
      
      return this;
   }

   public PeerProxySet getEve()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getEve());
      }
      
      return result;
   }

   public UpdateAdamFlowSet withEve(PeerProxy value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.withEve(value);
      }
      
      return this;
   }

   public UpdateAdamFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public longList getAdamJarAtEveSiteLastModified()
   {
      longList result = new longList();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getAdamJarAtEveSiteLastModified());
      }
      
      return result;
   }

   public UpdateAdamFlowSet withAdamJarAtEveSiteLastModified(long value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.withAdamJarAtEveSiteLastModified(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public UpdateAdamFlowSet withTaskNo(int value)
   {
      for (UpdateAdamFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public PeerProxySet getAdam()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getAdam());
      }
      
      return result;
   }

   public JsonIdMapSet getIdMap()
   {
      JsonIdMapSet result = new JsonIdMapSet();
      
      for (UpdateAdamFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

}




