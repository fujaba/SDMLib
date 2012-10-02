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
   
package org.sdmlib.model.taskflows;

import org.eclipse.swt.widgets.Display;
import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.sdmlib.utils.StrUtil;

public class SocketThread extends Thread implements PropertyChangeInterface
{

   
   //==========================================================================
   
   @Override
   public void run()
   {
      this.setName("SocketThread");
      // open server socket
      try
      {
         ServerSocket serverSocket = new ServerSocket(port);
         
         int i = 1;
         
         while (true)
         {
            Socket connection = serverSocket.accept();
            
            SocketReader socketReader = new SocketReader(connection);
            socketReader.setName("SocketReader_" + i++);
            socketReader.start();
         }
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
     
   }
   
   class SocketReader extends Thread
   {
      Socket connection;
      
      public SocketReader (Socket connection)
      {
         this.connection = connection;
      }
      
      @Override
      public void run()
      {
         try
         {
            InputStream byteIn = connection.getInputStream();
            
            InputStreamReader readerIn = new InputStreamReader(
               byteIn);
            BufferedReader in = new BufferedReader(

               readerIn);

            String line = "";

            while (line != null)
            {
               line = in.readLine();

               Object obj = idMap.readJson(new JsonArray(line));

               if (obj instanceof FetchFileFlow)
               {
                  FetchFileFlow fetchFileFlow = (FetchFileFlow) obj;
                  fetchFileFlow.setOut(connection.getOutputStream());
               }
               
               if (obj instanceof TaskFlow)
               {
                  TaskFlow taskFlow = (TaskFlow) obj;
                  
                  if (defaultTargetThread != null && defaultTargetThread instanceof Display)
                  {
                     ((Display) defaultTargetThread).asyncExec(taskFlow);
                  }
                  else if (defaultTargetThread != null && defaultTargetThread instanceof SDMTimer)
                  {
                     ((SDMTimer) defaultTargetThread).schedule(taskFlow);
                  }
                  else
                  {
                     taskFlow.run();
                  }
               }
            }
         }
         catch (IOException e)
         {
            System.out.println("Socket has been closed");
         }
      }
      
   }


   public Object get(String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (PROPERTY_IP.equalsIgnoreCase(attribute))
      {
         return getIp();
      }

      if (PROPERTY_PORT.equalsIgnoreCase(attribute))
      {
         return getPort();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_DEFAULTTARGETTHREAD.equalsIgnoreCase(attribute))
      {
         return getDefaultTargetThread();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_IP.equalsIgnoreCase(attrName))
      {
         setIp((String) value);
         return true;
      }

      if (PROPERTY_PORT.equalsIgnoreCase(attrName))
      {
         setPort(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.JsonIdMap) value);
         return true;
      }

      if (PROPERTY_DEFAULTTARGETTHREAD.equalsIgnoreCase(attrName))
      {
         setDefaultTargetThread((Object) value);
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
   
   public static final String PROPERTY_IP = "ip";
   
   private String ip;

   public String getIp()
   {
      return this.ip;
   }
   
   public void setIp(String value)
   {
      if ( ! StrUtil.stringEquals(this.ip, value))
      {
         String oldValue = this.ip;
         this.ip = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IP, oldValue, value);
      }
   }
   
   public SocketThread withIp(String value)
   {
      setIp(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_PORT = "port";
   
   private int port;

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
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PORT, oldValue, value);
      }
   }
   
   public SocketThread withPort(int value)
   {
      setPort(value);
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
   
   public SocketThread withIdMap(org.sdmlib.serialization.json.JsonIdMap value)
   {
      setIdMap(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_DEFAULTTARGETTHREAD = "defaultTargetThread";
   
   private Object defaultTargetThread;

   public Object getDefaultTargetThread()
   {
      return this.defaultTargetThread;
   }
   
   public void setDefaultTargetThread(Object value)
   {
      if (this.defaultTargetThread != value)
      {
         Object oldValue = this.defaultTargetThread;
         this.defaultTargetThread = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_DEFAULTTARGETTHREAD, oldValue, value);
      }
   }
   
   public SocketThread withDefaultTargetThread(Object value)
   {
      setDefaultTargetThread(value);
      return this;
   } 
}

