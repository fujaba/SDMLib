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
   
package org.sdmlib.model.taskflows;

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.utils.StrUtil;

public class FetchFileFlow extends TaskFlow implements PropertyChangeInterface
{

   
   //==========================================================================
   
   public Object get(String attrName)
   {
      if (PROPERTY_FILESERVER.equalsIgnoreCase(attrName))
      {
         return getFileServer();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         return getIdMap();
      }

      if (PROPERTY_FILENAME.equalsIgnoreCase(attrName))
      {
         return getFileName();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         return getTaskNo();
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
      if (PROPERTY_FILESERVER.equalsIgnoreCase(attrName))
      {
         setFileServer((org.sdmlib.model.taskflows.PeerProxy) value);
         return true;
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.SDMLibJsonIdMap) value);
         return true;
      }

      if (PROPERTY_FILENAME.equalsIgnoreCase(attrName))
      {
         setFileName((String) value);
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
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
   
   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
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
   
   public void run(  )
   {
      
   }

   
   //==========================================================================
   
   public static final String PROPERTY_FILESERVER = "fileServer";
   
   private org.sdmlib.model.taskflows.PeerProxy fileServer;

   public org.sdmlib.model.taskflows.PeerProxy getFileServer()
   {
      return this.fileServer;
   }
   
   public void setFileServer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      if (this.fileServer != value)
      {
         org.sdmlib.model.taskflows.PeerProxy oldValue = this.fileServer;
         this.fileServer = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FILESERVER, oldValue, value);
      }
   }
   
   public FetchFileFlow withFileServer(org.sdmlib.model.taskflows.PeerProxy value)
   {
      setFileServer(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_IDMAP = "idMap";
   
   private org.sdmlib.serialization.json.SDMLibJsonIdMap idMap;

   public org.sdmlib.serialization.json.SDMLibJsonIdMap getIdMap()
   {
      return this.idMap;
   }
   
   public void setIdMap(org.sdmlib.serialization.json.SDMLibJsonIdMap value)
   {
      if (this.idMap != value)
      {
         org.sdmlib.serialization.json.SDMLibJsonIdMap oldValue = this.idMap;
         this.idMap = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_IDMAP, oldValue, value);
      }
   }
   
   public FetchFileFlow withIdMap(org.sdmlib.serialization.json.SDMLibJsonIdMap value)
   {
      setIdMap(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_FILENAME = "fileName";
   
   private String fileName;

   public String getFileName()
   {
      return this.fileName;
   }
   
   public void setFileName(String value)
   {
      if ( ! StrUtil.stringEquals(this.fileName, value))
      {
         String oldValue = this.fileName;
         this.fileName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_FILENAME, oldValue, value);
      }
   }
   
   public FetchFileFlow withFileName(String value)
   {
      setFileName(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getFileName());
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }

}

