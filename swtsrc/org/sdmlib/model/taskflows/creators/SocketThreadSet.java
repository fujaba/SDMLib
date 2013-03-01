package org.sdmlib.model.taskflows.creators;

import java.util.LinkedHashSet;

import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.JsonIdMapSet;

public class SocketThreadSet extends LinkedHashSet<SocketThread>
{
   public StringList getIp()
   {
      StringList result = new StringList();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getIp());
      }
      
      return result;
   }

   public SocketThreadSet withIp(String value)
   {
      for (SocketThread obj : this)
      {
         obj.withIp(value);
      }
      
      return this;
   }

   public intList getPort()
   {
      intList result = new intList();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getPort());
      }
      
      return result;
   }

   public SocketThreadSet withPort(int value)
   {
      for (SocketThread obj : this)
      {
         obj.withPort(value);
      }
      
      return this;
   }

   public SocketThreadSet withIdMap(JsonIdMap value)
   {
      for (SocketThread obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public JsonIdMapSet getIdMap()
   {
      JsonIdMapSet result = new JsonIdMapSet();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public ObjectSet getDefaultTargetThread()
   {
      ObjectSet result = new ObjectSet();
      
      for (SocketThread obj : this)
      {
         result.add(obj.getDefaultTargetThread());
      }
      
      return result;
   }

   public SocketThreadSet withDefaultTargetThread(Object value)
   {
      for (SocketThread obj : this)
      {
         obj.withDefaultTargetThread(value);
      }
      
      return this;
   }

   public SocketThreadSet withIdMap(SDMLibJsonIdMap value)
   {
      for (SocketThread obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }



   public String toString()
   {
      StringList stringList = new StringList();
      
      for (SocketThread elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.model.taskflows.SocketThread";
   }


   public SocketThreadSet with(SocketThread value)
   {
      this.add(value);
      return this;
   }
   
   public SocketThreadSet without(SocketThread value)
   {
      this.remove(value);
      return this;
   }
}





