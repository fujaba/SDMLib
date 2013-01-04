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

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import org.sdmlib.utils.StrUtil;

public class PeerToPeerChatArgs implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_USERNAME.equalsIgnoreCase(attribute))
      {
         return getUserName();
      }

      if (PROPERTY_LOCALPORT.equalsIgnoreCase(attribute))
      {
         return getLocalPort();
      }

      if (PROPERTY_PEERIP.equalsIgnoreCase(attribute))
      {
         return getPeerIp();
      }

      if (PROPERTY_PEERPORT.equalsIgnoreCase(attribute))
      {
         return getPeerPort();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_USERNAME.equalsIgnoreCase(attrName))
      {
         setUserName((String) value);
         return true;
      }

      if (PROPERTY_LOCALPORT.equalsIgnoreCase(attrName))
      {
         setLocalPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_PEERIP.equalsIgnoreCase(attrName))
      {
         setPeerIp((String) value);
         return true;
      }

      if (PROPERTY_PEERPORT.equalsIgnoreCase(attrName))
      {
         setPeerPort(Integer.parseInt(value.toString()));
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
   
   public static final String PROPERTY_USERNAME = "userName";
   
   private String userName;

   public String getUserName()
   {
      return this.userName;
   }
   
   public void setUserName(String value)
   {
      if ( ! StrUtil.stringEquals(this.userName, value))
      {
         String oldValue = this.userName;
         this.userName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_USERNAME, oldValue, value);
      }
   }
   
   public PeerToPeerChatArgs withUserName(String value)
   {
      setUserName(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_LOCALPORT = "localPort";
   
   private int localPort;

   public int getLocalPort()
   {
      return this.localPort;
   }
   
   public void setLocalPort(int value)
   {
      if (this.localPort != value)
      {
         int oldValue = this.localPort;
         this.localPort = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOCALPORT, oldValue, value);
      }
   }
   
   public PeerToPeerChatArgs withLocalPort(int value)
   {
      setLocalPort(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PEERIP = "peerIp";
   
   private String peerIp;

   public String getPeerIp()
   {
      return this.peerIp;
   }
   
   public void setPeerIp(String value)
   {
      if ( ! StrUtil.stringEquals(this.peerIp, value))
      {
         String oldValue = this.peerIp;
         this.peerIp = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PEERIP, oldValue, value);
      }
   }
   
   public PeerToPeerChatArgs withPeerIp(String value)
   {
      setPeerIp(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PEERPORT = "peerPort";
   
   private int peerPort;

   public int getPeerPort()
   {
      return this.peerPort;
   }
   
   public void setPeerPort(int value)
   {
      if (this.peerPort != value)
      {
         int oldValue = this.peerPort;
         this.peerPort = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PEERPORT, oldValue, value);
      }
   }
   
   public PeerToPeerChatArgs withPeerPort(int value)
   {
      setPeerPort(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getUserName());
      _.append(" ").append(this.getLocalPort());
      _.append(" ").append(this.getPeerIp());
      _.append(" ").append(this.getPeerPort());
      return _.substring(1);
   }

}

