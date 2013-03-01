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

import org.sdmlib.examples.chats.P2PNetworkLoginFlow;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.model.taskflows.creators.TaskFlowSet;
import org.sdmlib.models.modelsets.ModelSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.modelsets.intList;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.json.creators.SDMLibJsonIdMapSet;

public class P2PNetworkLoginFlowSet extends LinkedHashSet<P2PNetworkLoginFlow> implements ModelSet
{


   public String toString()
   {
      StringList stringList = new StringList();
      
      for (P2PNetworkLoginFlow elem : this)
      {
         stringList.add(elem.toString());
      }
      
      return "(" + stringList.concat(", ") + ")";
   }


   public String getEntryType()
   {
      return "org.sdmlib.examples.chats.P2PNetworkLoginFlow";
   }


   public P2PNetworkLoginFlowSet with(P2PNetworkLoginFlow value)
   {
      this.add(value);
      return this;
   }
   
   public P2PNetworkLoginFlowSet without(P2PNetworkLoginFlow value)
   {
      this.remove(value);
      return this;
   }
   
   //==========================================================================
   
   public P2PNetworkLoginFlowSet run()
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.run();
      }
      return this;
   }

   
   //==========================================================================
   
   public LinkedHashSet<Object> getTaskNames()
   {
      LinkedHashSet<Object> result = new LinkedHashSet<Object>();
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getTaskNames());
      }
      return result;
   }

   public PeerProxySet getFirstPeer()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getFirstPeer());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withFirstPeer(PeerProxy value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withFirstPeer(value);
      }
      
      return this;
   }

   public StringList getClientName()
   {
      StringList result = new StringList();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getClientName());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withClientName(String value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withClientName(value);
      }
      
      return this;
   }

   public PeerProxySet getPeerList()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.addAll(obj.getPeerList());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withPeerList(PeerProxySet value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withPeerList(value);
      }
      
      return this;
   }

   public intList getTaskNo()
   {
      intList result = new intList();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getTaskNo());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withTaskNo(int value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withTaskNo(value);
      }
      
      return this;
   }

   public SDMLibJsonIdMapSet getIdMap()
   {
      SDMLibJsonIdMapSet result = new SDMLibJsonIdMapSet();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getIdMap());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withIdMap(SDMLibJsonIdMap value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withIdMap(value);
      }
      
      return this;
   }

   public PeerProxySet getClientPeer()
   {
      PeerProxySet result = new PeerProxySet();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getClientPeer());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withClientPeer(PeerProxy value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withClientPeer(value);
      }
      
      return this;
   }

   public StringList getAllMessages()
   {
      StringList result = new StringList();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getAllMessages());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withAllMessages(String value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withAllMessages(value);
      }
      
      return this;
   }

   public TaskFlowSet getSubFlow()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getSubFlow());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withSubFlow(TaskFlow value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withSubFlow(value);
      }
      
      return this;
   }

   public TaskFlowSet getParent()
   {
      TaskFlowSet result = new TaskFlowSet();
      
      for (P2PNetworkLoginFlow obj : this)
      {
         result.add(obj.getParent());
      }
      
      return result;
   }

   public P2PNetworkLoginFlowSet withParent(TaskFlow value)
   {
      for (P2PNetworkLoginFlow obj : this)
      {
         obj.withParent(value);
      }
      
      return this;
   }

}



