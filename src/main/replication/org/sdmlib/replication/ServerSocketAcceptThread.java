/*
   Copyright (c) 2013 zuendorf 
   
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
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
   public class ServerSocketAcceptThread extends Thread implements
      PropertyChangeInterface, SendableEntity
{
   // ==========================================================================

   public int port;
   public ReplicationNode replicationNode;
   private SharedSpace sharedSpace;

     /**
    * Constructor
    * @param replicationNode The ReplicationNode
    * @param replicationServerPort The PortNumber
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
 */
   public ServerSocketAcceptThread(ReplicationNode replicationNode,
         int replicationServerPort)
   {
      this.replicationNode = replicationNode;
      this.port = replicationServerPort;
   }

     /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
 */
   public ServerSocketAcceptThread()
   {
      // blank
   }

     /**
    * @param sharedSpace The SharedSpace
    * @param port The Portnumber 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java'>ReplicationObjectScenarioForCoverage.java</a>
 */
   public ServerSocketAcceptThread(SharedSpace sharedSpace, int port)
   {
      this.sharedSpace = sharedSpace;
      this.port = port;
   }

   @Override
   public void run()
   {
      this.setName("SocketAcceptThread");
      // open server socket
      ServerSocket serverSocket = null;
      try
      {
         serverSocket = new ServerSocket(port);

         int i = 1;

         while (true)
         {
            Socket connection = serverSocket.accept();

            ReplicationChannel channel = new ReplicationChannel(
                  replicationNode, connection);
            channel.setName("ReplicationChannel" + i++);
            channel.setSharedSpace(sharedSpace);
            channel.start();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      } finally {
         if(serverSocket != null) {
            try
            {
               serverSocket.close();
            }
            catch (IOException e)
            {
               e.printStackTrace();
            }
         }
      }

   }

   // ==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_PORT.equalsIgnoreCase(attrName))
      {
         return getPort();
      }

      if (PROPERTY_REPLICATIONNODE.equalsIgnoreCase(attrName))
      {
         return getReplicationNode();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_PORT.equalsIgnoreCase(attrName))
      {
         setPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_REPLICATIONNODE.equalsIgnoreCase(attrName))
      {
         setReplicationNode((ReplicationNode) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

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
   // ==========================================================================

   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
      // super.removeYou();
   }

   // ==========================================================================

   public static final String PROPERTY_PORT = "port";

   public int getPort()
   {
      return this.port;
   }

   public void setPort(int value)
   {
      if (this.port != value)
      {
         int oldValue = this.port;
         this.port = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PORT, oldValue,
            value);
      }
   }

   public ServerSocketAcceptThread withPort(int value)
   {
      setPort(value);
      return this;
   }

   public String toString()
   {
      StringBuilder s = new StringBuilder();

      s.append(" ").append(this.getPort());
      return s.substring(1);
   }

   // ==========================================================================

   public static final String PROPERTY_REPLICATIONNODE = "replicationNode";

   public ReplicationNode getReplicationNode()
   {
      return this.replicationNode;
   }

   public void setReplicationNode(ReplicationNode value)
   {
      if (this.replicationNode != value)
      {
         ReplicationNode oldValue = this.replicationNode;
         this.replicationNode = value;
         getPropertyChangeSupport().firePropertyChange(
            PROPERTY_REPLICATIONNODE, oldValue, value);
      }
   }

   public ServerSocketAcceptThread withReplicationNode(ReplicationNode value)
   {
      setReplicationNode(value);
      return this;
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
