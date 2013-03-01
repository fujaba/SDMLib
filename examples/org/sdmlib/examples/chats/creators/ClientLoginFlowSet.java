/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.examples.chats.creators;

import java.util.LinkedHashSet;

import org.sdmlib.examples.chats.ClientLoginFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;

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



   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.ClientLoginFlow";
   }
   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public ClientLoginFlowSet withSubFlow(TaskFlow value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (ClientLoginFlow obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public ClientLoginFlowSet withParent(TaskFlow value)
   {
      for (ClientLoginFlow obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}





