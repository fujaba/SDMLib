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
   
package org.sdmlib.examples.chats;

import org.sdmlib.examples.chats.TestChatMessageFlow.TaskNames;
import org.sdmlib.model.taskflows.Logger;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;

public class P2PNetworkLoginFlow extends TaskFlow implements PropertyChangeInterface
{   
   enum TaskNames 
   {
      Start, AtFirstPeer, AtOtherPeer
   }

   @Override
   public Object[] getTaskNames()
   {
      return TaskNames.values();
   }

   @Override
   public void run()
   {
      switch (TaskNames.values()[taskNo])
      {
      case Start:
         // go to first peer
         switchTo(firstPeer);
         break;

      case AtFirstPeer:
         // add myself to list of all peers
         PeerToPeerChat gui = (PeerToPeerChat) getIdMap().getObject(PeerToPeerChat.MY_GUI); 
         
         // get list of all peers 
         
         // get current state
         
         // multi hop visit all peers to introduce myself
         
         break;
         
      default:
         break;
      }

   }
   
   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_FIRSTPEER.equalsIgnoreCase(attrName))
      {
         return getFirstPeer();
      }

      if (PROPERTY_CLIENTNAME.equalsIgnoreCase(attrName))
      {
         return getClientName();
      }

      if (PROPERTY_PEERLIST.equalsIgnoreCase(attrName))
      {
         return getPeerList();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         return getTaskNo();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         return getIdMap();
      }

      if (PROPERTY_CLIENTPEER.equalsIgnoreCase(attrName))
      {
         return getClientPeer();
      }

      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_FIRSTPEER.equalsIgnoreCase(attrName))
      {
         setFirstPeer((org.sdmlib.model.taskflows.PeerProxy) value);
         return true;
      }

      if (PROPERTY_CLIENTNAME.equalsIgnoreCase(attrName))
      {
         setClientName((String) value);
         return true;
      }

      if (PROPERTY_PEERLIST.equalsIgnoreCase(attrName))
      {
         setPeerList((org.sdmlib.model.taskflows.creators.PeerProxySet) value);
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_CLIENTPEER.equalsIgnoreCase(attrName))
      {
         setClientPeer((org.sdmlib.model.taskflows.PeerProxy) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   
   //==========================================================================
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

   
   //==========================================================================
   
   public static final String PROPERTY_FIRSTPEER = "firstPeer";
   
   private org.sdmlib.model.taskflows.PeerProxy firstPeer;

   public org.sdmlib.model.taskflows.PeerProxy getFirstPeer()
   {
      return this.firstPeer;
   }
   
   public void setFirstPeer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      if (this.firstPeer != value)
      {
         org.sdmlib.model.taskflows.PeerProxy oldValue = this.firstPeer;
         this.firstPeer = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FIRSTPEER, oldValue, value);
      }
   }
   
   public P2PNetworkLoginFlow withFirstPeer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      setFirstPeer(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CLIENTNAME = "clientName";
   
   private String clientName;

   public String getClientName()
   {
      return this.clientName;
   }
   
   public void setClientName(String value)
   {
      if ( ! StrUtil.stringEquals(this.clientName, value))
      {
         String oldValue = this.clientName;
         this.clientName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLIENTNAME, oldValue, value);
      }
   }
   
   public P2PNetworkLoginFlow withClientName(String value)
   {
      setClientName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PEERLIST = "peerList";
   
   private org.sdmlib.model.taskflows.creators.PeerProxySet peerList;

   public org.sdmlib.model.taskflows.creators.PeerProxySet getPeerList()
   {
      return this.peerList;
   }
   
   public void setPeerList(org.sdmlib.model.taskflows.creators.PeerProxySet value)
   {
      if (this.peerList != value)
      {
         org.sdmlib.model.taskflows.creators.PeerProxySet oldValue = this.peerList;
         this.peerList = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PEERLIST, oldValue, value);
      }
   }
   
   public P2PNetworkLoginFlow withPeerList(org.sdmlib.model.taskflows.creators.PeerProxySet value)
   {
      setPeerList(value);
      return this;
   } 


   
   //==========================================================================
   
   public static final String PROPERTY_CLIENTPEER = "clientPeer";
   
   private org.sdmlib.model.taskflows.PeerProxy clientPeer;

   public org.sdmlib.model.taskflows.PeerProxy getClientPeer()
   {
      return this.clientPeer;
   }
   
   public void setClientPeer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      if (this.clientPeer != value)
      {
         org.sdmlib.model.taskflows.PeerProxy oldValue = this.clientPeer;
         this.clientPeer = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLIENTPEER, oldValue, value);
      }
   }
   
   public P2PNetworkLoginFlow withClientPeer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      setClientPeer(value);
      return this;
   } 
}

