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

import org.sdmlib.serialization.json.JsonArray;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.sdmlib.utils.StrUtil;

public class PeerProxy implements PropertyChangeInterface
{

   public void transferTaskFlow(TaskFlow taskFlow)
   {
      try
      {
         Socket socket = new Socket(ip, port);
         OutputStreamWriter out = new OutputStreamWriter(socket.getOutputStream());
         
         JsonArray jsonArray = idMap.toJsonArray(taskFlow); 
         
         JsonArray smallArray = new JsonArray();
         smallArray.add(jsonArray.get(0));
         
         out.write(smallArray.toString()+"\n");
         out.flush();
         out.close();
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
   
   public PeerProxy withIdMap(org.sdmlib.serialization.json.JsonIdMap value)
   {
      setIdMap(value);
      return this;
   } 
}

