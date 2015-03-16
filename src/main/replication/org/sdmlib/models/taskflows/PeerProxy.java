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
   
package org.sdmlib.models.taskflows;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.sdmlib.StrUtil;
import org.sdmlib.serialization.PropertyChangeInterface;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

public class PeerProxy implements PropertyChangeInterface, Comparable<PeerProxy>
{
   public PeerProxy ()
   {
      // blank
   }
   
   public PeerProxy(String ip, int port, SDMLibJsonIdMap map)
   {
      this.withIp(ip).withPort(port).withIdMap(map);
      map.put(ip + "." + port, this);
   }

   public void transferTaskFlow(TaskFlow taskFlow)
   {
      try
      {
         if (socket == null || ! socket.isConnected())
         {
            socket = new Socket(ip, port);
            
            out = new OutputStreamWriter(socket.getOutputStream());
         }
         
         JsonArray jsonArray = idMap.toJsonArray(taskFlow); 
                  
         out.write(jsonArray.toString()+"\n");
         out.flush();
         
         
      }
      catch (UnknownHostException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
   
   private Socket socket = null;
   
   public Socket getSocket()
   {
      return socket;
   }
   
   private OutputStreamWriter out = null;
   
   //==========================================================================
   
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
         setIdMap((org.sdmlib.serialization.SDMLibJsonIdMap) value);
         return true;
      }

      return false;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
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
   
   public PeerProxy withIp(String value)
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
   
   public PeerProxy withPort(int value)
   {
      setPort(value);
      return this;
   } 


   
   //==========================================================================
   
   public static final String PROPERTY_IDMAP = "idMap";
   
   private SDMLibJsonIdMap idMap;

   public SDMLibJsonIdMap getIdMap()
   {
      return this.idMap;
   }
   
   public void setIdMap(SDMLibJsonIdMap value)
   {
      if (this.idMap != value)
      {
         JsonIdMap oldValue = this.idMap;
         this.idMap = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IDMAP, oldValue, value);
      }
   }
   
   public PeerProxy withIdMap(SDMLibJsonIdMap value)
   {
      setIdMap(value);
      return this;
   } 
   
   @Override
   public String toString()
   {
      StringBuilder s = new StringBuilder();
      
      s.append(" ").append(this.getIp());
      s.append(" ").append(this.getPort());
      return "" + ip + ":" + port;
   }

   @Override
   public int compareTo(PeerProxy o)
   {
      int result = this.getIp().compareTo(o.getIp());
      if (result == 0)
      {
         if (this.getPort() > o.getPort())
         {
            return 1;
         }
         
         if (this.getPort() < o.getPort())
         {
            return -1;
         }
         
         return 0;
      }
      return result;
   }

}

