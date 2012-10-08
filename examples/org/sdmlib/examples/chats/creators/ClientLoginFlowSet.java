package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;
import org.sdmlib.examples.chats.ClientLoginFlow;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.models.modelsets.intList;
import java.util.List;

public class ClientLoginFlowSet extends LinkedHashSet<ClientLoginFlow>
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (ClientLoginFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public ClientLoginFlowSet with(ClientLoginFlow value)
   {
      this.add(value);
      return this;
   }
   
   public ClientLoginFlowSet without(ClientLoginFlow value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public ClientLoginFlowSet run()
   {
      for (ClientLoginFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public ClientLoginFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public PeerProxySet getServer()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getServer());
      }
      
      return result;
   }

   public ClientLoginFlowSet withServer(PeerProxy value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withServer(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public ClientLoginFlowSet withTaskNo(int value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public StringList getClientIP()
   {
      StringList result = new StringList();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getClientIP());
      }
      
      return result;
   }

   public ClientLoginFlowSet withClientIP(String value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withClientIP(value);
      }
      
      return this;
   }

   public intList getClientPort()
   {
      intList result = new intList();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getClientPort());
      }
      
      return result;
   }

   public ClientLoginFlowSet withClientPort(int value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withClientPort(value);
      }
      
      return this;
   }

   public StringList getAllMessagesText()
   {
      StringList result = new StringList();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getAllMessagesText());
      }
      
      return result;
   }

   public ClientLoginFlowSet withAllMessagesText(String value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withAllMessagesText(value);
      }
      
      return this;
   }

}



