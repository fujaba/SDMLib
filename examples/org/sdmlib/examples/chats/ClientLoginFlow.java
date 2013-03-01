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

import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class ClientLoginFlow extends TaskFlow implements PropertyChangeInterface
{
	enum TaskNames 
	{
	   Start, ServerLogin, ClientHandleAllMessages
	}
   
	public Object[] getTaskNames()
	{
		return TaskNames.values();
	}
   
   //==========================================================================
   
   public void run(  )
   {
      switch (TaskNames.values()[taskNo])
      {
      case Start:
         switchTo(server);
         break;

      case ServerLogin:
         // add client to peer list
         PeerProxy clientProxy = new PeerProxy().withIp(clientIP).withPort(clientPort).withIdMap(getIdMap());
         
         ChatServer chatServer = (ChatServer) getIdMap().getObject(ChatServer.CHAT_SERVER);
         
         chatServer.getAllPeers().add(clientProxy);
         
         // send client all current messages, 
         allMessagesText = chatServer.getAllMessages();
         switchTo(clientProxy);
         break;
      
      case ClientHandleAllMessages:
         PeerToPeerChat gui = (PeerToPeerChat) getIdMap().getObject(PeerToPeerChat.MY_GUI);
         gui.getAllMessages().setText(allMessagesText);
         
         System.out.println("Login successfull:");
         
         break;
         
      default:
         break;
      }
      
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

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_SERVER.equalsIgnoreCase(attribute))
      {
         return getServer();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_CLIENTIP.equalsIgnoreCase(attribute))
      {
         return getClientIP();
      }

      if (PROPERTY_CLIENTPORT.equalsIgnoreCase(attribute))
      {
         return getClientPort();
      }

      if (PROPERTY_ALLMESSAGESTEXT.equalsIgnoreCase(attribute))
      {
         return getAllMessagesText();
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         return getSubFlow();
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         return getParent();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_SERVER.equalsIgnoreCase(attrName))
      {
         setServer((org.sdmlib.model.taskflows.PeerProxy) value);
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_CLIENTIP.equalsIgnoreCase(attrName))
      {
         setClientIP((String) value);
         return true;
      }

      if (PROPERTY_CLIENTPORT.equalsIgnoreCase(attrName))
      {
         setClientPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_ALLMESSAGESTEXT.equalsIgnoreCase(attrName))
      {
         setAllMessagesText((String) value);
         return true;
      }

      if (PROPERTY_SUBFLOW.equalsIgnoreCase(attrName))
      {
         setSubFlow((TaskFlow) value);
         return true;
      }

      if (PROPERTY_PARENT.equalsIgnoreCase(attrName))
      {
         setParent((TaskFlow) value);
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
      setSubFlow(null);
      setParent(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      super.removeYou();
   }

  
   //==========================================================================
   
   public static final String PROPERTY_SERVER = "server";
   
   private org.sdmlib.model.taskflows.PeerProxy server;

   public org.sdmlib.model.taskflows.PeerProxy getServer()
   {
      return this.server;
   }
   
   public void setServer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      if (this.server != value)
      {
         org.sdmlib.model.taskflows.PeerProxy oldValue = this.server;
         this.server = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVER, oldValue, value);
      }
   }
   
   public ClientLoginFlow withServer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      setServer(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CLIENTIP = "clientIP";
   
   private String clientIP;

   public String getClientIP()
   {
      return this.clientIP;
   }
   
   public void setClientIP(String value)
   {
      if ( ! StrUtil.stringEquals(this.clientIP, value))
      {
         String oldValue = this.clientIP;
         this.clientIP = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLIENTIP, oldValue, value);
      }
   }
   
   public ClientLoginFlow withClientIP(String value)
   {
      setClientIP(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_CLIENTPORT = "clientPort";
   
   private int clientPort;

   public int getClientPort()
   {
      return this.clientPort;
   }
   
   public void setClientPort(int value)
   {
      if (this.clientPort != value)
      {
         int oldValue = this.clientPort;
         this.clientPort = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLIENTPORT, oldValue, value);
      }
   }
   
   public ClientLoginFlow withClientPort(int value)
   {
      setClientPort(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ALLMESSAGESTEXT = "allMessagesText";
   
   private String allMessagesText;

   public String getAllMessagesText()
   {
      return this.allMessagesText;
   }
   
   public void setAllMessagesText(String value)
   {
      if ( ! StrUtil.stringEquals(this.allMessagesText, value))
      {
         String oldValue = this.allMessagesText;
         this.allMessagesText = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ALLMESSAGESTEXT, oldValue, value);
      }
   }
   
   public ClientLoginFlow withAllMessagesText(String value)
   {
      setAllMessagesText(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getClientIP());
      _.append(" ").append(this.getClientPort());
      _.append(" ").append(this.getAllMessagesText());
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }

}

