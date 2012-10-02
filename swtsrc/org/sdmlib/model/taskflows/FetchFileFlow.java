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

import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.sdmlib.utils.StrUtil;

public class FetchFileFlow extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames
   {
      StartAtClient, 
      StartAtFileServer, 
   }
      //==========================================================================
   
   public void run(  )
   {
      byte[] buffer;
      switch (TaskNames.values()[taskNo])
      {
      case StartAtClient:
         // ask the file server to send the file
         switchTo(fileServer);
         
         try
         {
            // receive the file data
            InputStream socketIn = fileServer.getSocket().getInputStream();
            buffer = new byte[4*1024];
            FileOutputStream fileOut = new FileOutputStream(fileName + ".received");
            int socketRead = 0;
            
            while (true)
            {
               socketRead = socketIn.read(buffer);
               
               if (socketRead < 0)
               {
                  break;
               }
               
               fileOut.write(buffer, 0, socketRead);
            }
            
            fileOut.close();
            
            // remove old file
            System.out.println("removing file: " + new File(fileName).getAbsolutePath());
            
            new File(fileName).renameTo(new File(fileName + ".old"));
            
            new File(fileName + ".old").delete();
            
            // rename new file
            new File(fileName + ".received").renameTo(new File(fileName));
            
            
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
         break;
         
      case StartAtFileServer:
         // read file and send it back to caller
         try
         {
            FileInputStream fileIn = new FileInputStream(fileName);
            
            buffer = new byte[4*1024];
            int bytesRead = 0;
            
            while (true)
            {
               bytesRead = fileIn.read(buffer);
               
               if (bytesRead < 0)
               { 
                  break;
               }
               out.write(buffer, 0, bytesRead);
            }
            
            out.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
         
         break;
         
      default:
         break;
      }
   }

   private OutputStream out = null;
   
   public OutputStream getOut()
   {
      return out;
   }
   
   public void setOut(OutputStream out)
   {
      this.out = out;
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

      if (PROPERTY_FILESERVER.equalsIgnoreCase(attribute))
      {
         return getFileServer();
      }

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_FILENAME.equalsIgnoreCase(attribute))
      {
         return getFileName();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
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
      super.removeYou();
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
}

