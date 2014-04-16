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
   
package org.sdmlib.examples.adamandeve;

import java.beans.PropertyChangeSupport;
import java.util.Timer;

import org.sdmlib.examples.adamandeve.creators.CreatorCreator;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.serialization.util.PropertyChangeInterface;

public class Eve extends Timer implements PropertyChangeInterface
{
   public static void main(String[] args)
   {
      Eve eve = new Eve();
      
      SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) CreatorCreator.createIdMap("eve");
      
      idMap.put("json.idmap", idMap); // oh oh
      
      new SocketThread().withIdMap(idMap)
      .withPort(8484)
      .start();
      
      new UpdateAdamFlow().withIdMap(idMap)
      .run();
   }
   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_PEERS.equalsIgnoreCase(attribute))
      {
         return getPeers();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_PEERS.equalsIgnoreCase(attrName))
      {
         addToPeers((PeerProxy) value);
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.JsonIdMap) value);
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
   }

   
   //==========================================================================
   
   public static final String PROPERTY_PEERS = "peers";
   
   private org.sdmlib.model.taskflows.creators.PeerProxySet peers;

   public org.sdmlib.model.taskflows.creators.PeerProxySet getPeers()
   {
      return this.peers;
   }
   
   public void addToPeers(PeerProxy value)
   {
      if (this.peers == null)
      {
         this.peers = new PeerProxySet();
      }
      
      boolean done = this.peers.add(value);
      
      if (done)
      {
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PEERS, null, value);
      }
   }
   
   public void removeFromPeers(PeerProxy value)
   {
      if (this.peers == null)
      {
         return;
      }
      
      boolean done = this.peers.remove(value);
      
      if (done)
      {
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PEERS, value, null);
      }
   }
   
   public Eve withPeers(PeerProxy value)
   {
      addToPeers(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_IDMAP = "idMap";
   
   private org.sdmlib.serialization.json.JsonIdMap idMap;

   public org.sdmlib.serialization.json.JsonIdMap getIdMap()
   {
      return this.idMap;
   }
   
   public void setIdMap(org.sdmlib.serialization.json.JsonIdMap value)
   {
      if (this.idMap != value)
      {
         org.sdmlib.serialization.json.JsonIdMap oldValue = this.idMap;
         this.idMap = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IDMAP, oldValue, value);
      }
   }
   
   public Eve withIdMap(org.sdmlib.serialization.json.JsonIdMap value)
   {
      setIdMap(value);
      return this;
   } 
}

