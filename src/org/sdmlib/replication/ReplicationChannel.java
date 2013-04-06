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

import org.eclipse.swt.widgets.Display;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;

import org.sdmlib.replication.creators.ReplicationChannelSet;
import org.sdmlib.serialization.json.JsonObject;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Thread;

public class ReplicationChannel extends Thread implements PropertyChangeInterface
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
               JsonObject jsonObject = new JsonObject(line);
               Object object = jsonObject.get(SharedSpace.PROPERTY_SPACEID);
               
               if (object != null && object instanceof String)
               {
                  String spaceId = (String) object;
                  
                  SharedSpace matchingSharedSpace = replicationNode.getOrCreateSharedSpace(spaceId);
                  
                  this.setSharedSpace(matchingSharedSpace);
               }
            }
            else
            {
               sharedSpace.enqueueMsg(line);
            }
         }
      }
      catch (IOException e)
      {
         System.out.println("Socket has been closed");
      }
   }
   

   //==========================================================================
   
   public void send(String line)
   {
      if ( ! line.endsWith("\n"))
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
      catch (IOException e)
      {
         e.printStackTrace();
      }
      
   } 

   //==========================================================================
   
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

      return null;
   }

   
   //==========================================================================
   
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
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SHAREDSPACE, oldValue, value);
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
   
   public ReplicationChannel withSocket(Socket value)
   {
      setSocket(value);
      return this;
   }


   public ReplicationChannel withConnect(String ip, int port)
   {
      if (socket == null || ! socket.isConnected())
      {
         try
         {
            socket = new Socket(ip, port);

            out = new OutputStreamWriter(socket.getOutputStream());
         }
         catch (Exception e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      
      return this;
   }
}

