package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.modelsets.StringList;
import java.util.List;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.creators.JsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class PeerProxySet extends LinkedHashSet<PeerProxy>
{

   public PeerProxySet with(PeerProxy value)
   {
      this.add(value);
      return this;
   }
   
   public PeerProxySet without(PeerProxy value)
   {
      this.remove(value);
      return this;
   }
   
   public StringList getIp()
   {
      StringList result = new StringList();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getIp());
      }
      
      return result;
   }

   public PeerProxySet withIp(String value)
   {
      for (PeerProxy obj : this)
      {
         obj.withIp(value);
      }
      
      return this;
   }

   public intList getPort()
   {
      intList result = new intList();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getPort());
      }
      
      return result;
   }

   public PeerProxySet withPort(int value)
   {
      for (PeerProxy obj : this)
      {
         obj.withPort(value);
      }
      
      return this;
   }


   public PeerProxySet withIdMap(JsonIdMap value)
   {
      for (PeerProxy obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public JsonIdMapSet getIdMap()
   {
      JsonIdMapSet result = new JsonIdMapSet();
      
      for (PeerProxy obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public PeerProxySet withIdMap(SDMLibJsonIdMap value)
   {
      for (PeerProxy obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }
   
   public String toString()
   {
      StringList stringList = new StringList();
      
      for (PeerProxy peerProxy : this)
      {
         stringList.add(peerProxy.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }



   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.PeerProxy";
   }
}




