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
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.ReplicationChannelSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.json.JsonObject;
import org.sdmlib.replication.SharedSpace;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
   public class ReplicationChannel extends Thread implements
      PropertyChangeInterface, SendableEntity
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

            if (sharedSpace == null)
            {
               // line should provide spaceId
               JsonObject jsonObject = new JsonObject().withValue(line);
               Object object = jsonObject.get(SharedSpace.PROPERTY_SPACEID);

               if (object != null && object instanceof String)
               {
                  String spaceId = (String) object;

                  SharedSpace matchingSharedSpace = replicationNode
                     .getOrCreateSharedSpace(spaceId);

                  this.setSharedSpace(matchingSharedSpace);

                  sharedSpace.enqueueMsg(this, line);
               }
            }
            else if (line.startsWith("hello from"))
            {
               String senderNodeId = line.split(" ")[2];
               this.setTargetNodeId(senderNodeId);
               // send history
               for (ReplicationChange change : sharedSpace.getHistory().getChanges()) {
            	   this.send(sharedSpace.getChangeMap().toJsonObject(change).toString());
               }
            }
            else if (line.startsWith("mouse"))
            {
               String[] data = line.split(" ");
               sharedSpace.setMousePositionAndWindowIdForUser(data[1], Double.parseDouble(data[2]), Double.parseDouble(data[3]), data[4]);
            }
            else
            {
               sharedSpace.enqueueMsg(this, line);
            }
         }
      }
      catch (Exception e)
      {
         System.out.println("Socket has been closed");
         this.removeYou();
      }
   }

   // ==========================================================================

   public void send(String line)
   {
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

   // ==========================================================================

   private ReplicationNode replicationNode;

   public ReplicationChannel(ReplicationNode replicationNode, Socket connection)
   {
      this.replicationNode = replicationNode;
      this.setSocket(connection);
   }

   public ReplicationChannel()
   {
      // blank
   }

   public Object get(String attrName)
   {
      if (PROPERTY_SHAREDSPACE.equalsIgnoreCase(attrName))
      {
         return getSharedSpace();
      }

      if (PROPERTY_SOCKET.equalsIgnoreCase(attrName))
      {
         return getSocket();
      }

      if (PROPERTY_TARGETNODEID.equalsIgnoreCase(attrName))
      {
         return getTargetNodeId();
      }

      return null;
   }

   // ==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SHAREDSPACE.equalsIgnoreCase(attrName))
      {
         setSharedSpace((SharedSpace) value);
         return true;
      }

      if (PROPERTY_SOCKET.equalsIgnoreCase(attrName))
      {
         setSocket((Socket) value);
         return true;
      }

      if (PROPERTY_TARGETNODEID.equalsIgnoreCase(attrName))
      {
         setTargetNodeId((String) value);
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
      setSharedSpace(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   public static final ReplicationChannelSet EMPTY_SET = new ReplicationChannelSet();

   /********************************************************************
    * <pre>
    *              many                       one
    * ReplicationChannel ----------------------------------- SharedSpace
    *              channels                   sharedSpace
    * </pre>
    */

   public static final String PROPERTY_SHAREDSPACE = "sharedSpace";

   private SharedSpace sharedSpace = null;

   public SharedSpace getSharedSpace()
   {
      return this.sharedSpace;
   }

   public boolean setSharedSpace(SharedSpace value)
   {
      boolean changed = false;

      if (this.sharedSpace != value)
      {
         SharedSpace oldValue = this.sharedSpace;

         if (this.sharedSpace != null)
         {
            this.sharedSpace = null;
            oldValue.withoutChannels(this);
         }

         this.sharedSpace = value;

         if (value != null)
         {
            value.withChannels(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_SHAREDSPACE,
            oldValue, value);
         changed = true;
      }

      return changed;
   }

   public ReplicationChannel withSharedSpace(SharedSpace value)
   {
      setSharedSpace(value);
      return this;
   }

   public SharedSpace createSharedSpace()
   {
      SharedSpace value = new SharedSpace();
      withSharedSpace(value);
      return value;
   }

   // ==========================================================================

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
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SOCKET,
            oldValue, value);
      }
   }

   public ReplicationChannel withSocket(Socket value)
   {
      setSocket(value);
      return this;
   }

   public ReplicationChannel withConnect(String ip, int port)
   {
      if (socket == null || !socket.isConnected())
      {
         try
         {
            socket = new Socket(ip, port);

            out = new OutputStreamWriter(socket.getOutputStream());
         }
         catch (Exception e)
         {
            // did not work, close channel
            removeYou();
         }
      }

      return this;
   }

   public void sendSpaceConnectionRequest(String spaceId)
   {
      JsonObject jsonObject = new JsonObject();
      jsonObject.put(SharedSpace.PROPERTY_SPACEID, spaceId);

      String line = jsonObject.toString() + "\n";
      this.send(line);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_TARGETNODEID = "targetNodeId";
   
   private String targetNodeId;

   public String getTargetNodeId()
   {
      return this.targetNodeId;
   }
   
   public void setTargetNodeId(String value)
   {
      if ( ! StrUtil.stringEquals(this.targetNodeId, value))
      {
         String oldValue = this.targetNodeId;
         this.targetNodeId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETNODEID, oldValue, value);
      }
   }
   
   public ReplicationChannel withTargetNodeId(String value)
   {
      setTargetNodeId(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getTargetNodeId());
      return s.substring(1);
   }

   public void loadHistory()
   {
      JsonObject jsonObject = new JsonObject();
      jsonObject.put(SharedSpace.RESEND_ID_HISTORY_NUMBER, 0);
      jsonObject.put(SharedSpace.RESEND_ID_HISTORY_PREFIX, "");

      String line = jsonObject.toString();
      this.send(line);     
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

