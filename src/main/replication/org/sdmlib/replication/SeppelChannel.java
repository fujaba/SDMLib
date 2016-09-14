/*
   Copyright (c) 2014 zuendorf 
   
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
   
package org.sdmlib.replication;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.interfaces.SendableEntity;
import org.sdmlib.replication.SeppelSpaceProxy;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
   public class SeppelChannel extends Thread implements PropertyChangeInterface, SendableEntity
{
   @Override
   public void run()
   {
      try
      {
         InputStream byteIn = socket.getInputStream();

         InputStreamReader readerIn = new InputStreamReader(byteIn);

         BufferedReader in = new BufferedReader(readerIn);

         String line = "";

         while (line != null)
         {
            line = in.readLine();

            if (! isLoginValidated())
            {
               // first login 
               JsonObject jsonObject = new JsonObject().withValue(line);
               String spaceId = jsonObject.getString("spaceId");
               String login = jsonObject.getString("login");
               String pwd = jsonObject.getString("pwd");
               
               this.setName(spaceId + "Channel");
               
               // yes it is a login message enqueue it and wait for its validation
               seppelSpace.enqueueMsg(this, line);
               
               String msg = msgQueue.take();
               
               if (! isLoginValidated())
               {
                  // invalid login, abort
                  this.removeYou();
                  return;
               }
            }
            else
            {
               seppelSpace.enqueueMsg(this, line);
            }
         }
      }
      catch (Exception e)
      {
         // e.printStackTrace();
         System.out.println("Socket has been closed");
         this.removeYou();
      }
   }


   public void send(String line)
   {
      if (! isLoginValidated())
      {
         // do not speak to them until the login is validated
         return;
      }
      
      if (!line.endsWith("\n"))
      {
         line = line + "\n";
      }

      try
      {
         if (out == null)
         {
            out = new OutputStreamWriter(socket.getOutputStream());
         }
         out.write(line);
         out.flush();
      }
      catch (Exception e)
      {
         // this channel is dead, remove it
         this.removeYou();
      }

   }

   public void login()
   {
      try
      {// I trust myself
         this.setLoginValidated(true);

         // connect to server and send my login data
         SeppelSpaceProxy myProxy = this.getSeppelSpaceProxy();
         String hostName = myProxy.getHostName();
         int portNo = (int) myProxy.getPortNo();


         SeppelSpaceProxy selfProxy = myProxy.getPartners().first();
         // SeppelUser selfUser = selfProxy.getKnownUsers().first();

         JsonObject jsonObject = new JsonObject();
         jsonObject.put("spaceId", selfProxy.getSpaceId());
         jsonObject.put("login", selfProxy.getLoginName());
         jsonObject.put("pwd", selfProxy.getPassword());

         this.send(jsonObject.toString());
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   private SeppelSpace seppelSpace;
   
   public void setSeppelSpace(SeppelSpace seppelSpace)
   {
      this.seppelSpace = seppelSpace;
   }

   private SeppelSpaceProxy selfProxy;
   
   public SeppelChannel(Socket connection, SeppelSpace seppelSpace)
   {
      this.socket = connection;
      this.seppelSpace = seppelSpace;
      this.selfProxy = seppelSpace.getSelfProxy();
   }

   public SeppelChannel(String host, int port)
   {
      try
      {
         this.socket = new Socket(host, port);
      }
      catch (Exception e)
      {
         this.socket = null; // just to be sure
         // e.printStackTrace();
      }
   }

   public SeppelChannel()
   {
      // blank
   }

   @Override
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   
   //==========================================================================
   
   
   public void removeYou()
   {
      if (this.socket != null)
      {
         try
         {
            socket.close();
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
      setSeppelSpaceProxy(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   private LinkedBlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();
   
   public LinkedBlockingQueue<String> getMsgQueue()
   {
      return msgQueue;
   }
   
   //==========================================================================
   
   public static final String PROPERTY_SOCKET = "socket";
   
   private Socket socket;
   private OutputStreamWriter out;


   public Socket getSocket()
   {
      return this.socket;
   }
   
   public void setSocket(Socket value)
   {
      if (this.socket != value)
      {
         Socket oldValue = this.socket;
         this.socket = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SOCKET, oldValue, value);
      }
   }
   
   public SeppelChannel withSocket(Socket value)
   {
      setSocket(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_LOGINVALIDATED = "loginValidated";
   
   private boolean loginValidated;

   public boolean isLoginValidated()
   {
      return this.loginValidated;
   }
   
   public void setLoginValidated(boolean value)
   {
      if (this.loginValidated != value)
      {
         boolean oldValue = this.loginValidated;
         this.loginValidated = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOGINVALIDATED, oldValue, value);
      }
   }
   
   public SeppelChannel withLoginValidated(boolean value)
   {
      setLoginValidated(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       one
    * SeppelChannel ----------------------------------- SeppelSpaceProxy
    *              channel                   seppelSpaceProxy
    * </pre>
    */
   
   public static final String PROPERTY_SEPPELSPACEPROXY = "seppelSpaceProxy";

   private SeppelSpaceProxy seppelSpaceProxy = null;

   public SeppelSpaceProxy getSeppelSpaceProxy()
   {
      return this.seppelSpaceProxy;
   }

   public boolean setSeppelSpaceProxy(SeppelSpaceProxy value)
   {
      boolean changed = false;
      
      if (this.seppelSpaceProxy != value)
      {
         SeppelSpaceProxy oldValue = this.seppelSpaceProxy;
         
         if (this.seppelSpaceProxy != null)
         {
            this.seppelSpaceProxy = null;
            oldValue.setChannel(null);
         }
         
         this.seppelSpaceProxy = value;
         
         if (value != null)
         {
            value.withChannel(this);
         }
         
         // no change event, no change log entry
         // getPropertyChangeSupport().firePropertyChange(PROPERTY_SEPPELSPACEPROXY, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public SeppelChannel withSeppelSpaceProxy(SeppelSpaceProxy value)
   {
      setSeppelSpaceProxy(value);
      return this;
   } 

   public SeppelSpaceProxy createSeppelSpaceProxy()
   {
      SeppelSpaceProxy value = new SeppelSpaceProxy();
      withSeppelSpaceProxy(value);
      return value;
   } 
   
   @Override
   public String toString()
   {
      if (this.seppelSpaceProxy != null)
      {
         return "channel to " + this.seppelSpaceProxy.getLoginName();
      }
      return super.toString();
   }

   


   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
