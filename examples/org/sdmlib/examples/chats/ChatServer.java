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

import java.beans.PropertyChangeSupport;

import org.sdmlib.examples.chats.creators.CreatorCreator;
import org.sdmlib.model.taskflows.SDMThread;
import org.sdmlib.model.taskflows.SocketThread;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class ChatServer implements PropertyChangeInterface
{
   public static final String CHAT_SERVER = "chat.server";

   public static final int CHAT_SERVER_PORT = 11112;

   public static void main(String[] args)
   {
      JsonIdMap idMap = CreatorCreator.createIdMap("chatServer");
      idMap.put(PeerToPeerChat.ID_MAP, idMap);
      
      ChatServer chatServer = new ChatServer();
      
      idMap.put(CHAT_SERVER, chatServer);
      
      SDMThread sdmThread = new SDMThread("ModelThread");
      sdmThread.start();
      
      new SocketThread()
      .withPort(CHAT_SERVER_PORT)
      .withIdMap(idMap)
      .withDefaultTargetThread(sdmThread)
      .start();
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

      if (PROPERTY_ALLMESSAGES.equalsIgnoreCase(attribute))
      {
         return getAllMessages();
      }

      if (PROPERTY_ALLPEERS.equalsIgnoreCase(attribute))
      {
         return getAllPeers();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_ALLMESSAGES.equalsIgnoreCase(attrName))
      {
         setAllMessages((String) value);
         return true;
      }

      if (PROPERTY_ALLPEERS.equalsIgnoreCase(attrName))
      {
         setAllPeers((org.sdmlib.model.taskflows.creators.PeerProxySet) value);
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
   
   public static final String PROPERTY_ALLMESSAGES = "allMessages";
   
   private StringBuilder allMessages = new StringBuilder("");

   public String getAllMessages()
   {
      return this.allMessages.toString();
   }
   
   public void setAllMessages(String value)
   {
      if ( ! StrUtil.stringEquals(this.allMessages.toString(), value))
      {
         this.allMessages.append(value);
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ALLMESSAGES, null, value);
      }
   }
   
   public ChatServer withAllMessages(String value)
   {
      setAllMessages(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ALLPEERS = "allPeers";
   
   private org.sdmlib.model.taskflows.creators.PeerProxySet allPeers = new PeerProxySet();

   public org.sdmlib.model.taskflows.creators.PeerProxySet getAllPeers()
   {
      return this.allPeers;
   }
   
   public void setAllPeers(org.sdmlib.model.taskflows.creators.PeerProxySet value)
   {
      if (this.allPeers != value)
      {
         org.sdmlib.model.taskflows.creators.PeerProxySet oldValue = this.allPeers;
         this.allPeers = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ALLPEERS, oldValue, value);
      }
   }
   
   public ChatServer withAllPeers(org.sdmlib.model.taskflows.creators.PeerProxySet value)
   {
      setAllPeers(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getAllMessages());
      return _.substring(1);
   }

}

