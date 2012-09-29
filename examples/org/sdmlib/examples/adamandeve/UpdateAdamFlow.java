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
   
package org.sdmlib.examples.adamandeve;

import org.sdmlib.utils.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.io.File;

import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;

public class UpdateAdamFlow extends TaskFlow implements PropertyChangeInterface
{
   enum TaskNames
   {
      StartAtEve, 
      AdamCheckJar,
      EveSendNewJar,
   }
   

   //==========================================================================
   
   @Override            
   public void run()
   {
      switch (TaskNames.values()[taskNo])
      {
      case StartAtEve:
         // prepare PeerProxy for Adam
         withAdam(new PeerProxy("localhost", 4242, getIdMap()));
         withEve(new PeerProxy("localhost", 8484, getIdMap()));
         
         // what is the modification data of Adam.jar?
         File adamJarFile = new File("Adam.jar");
         
         if ( ! adamJarFile.exists())
         {
            System.out.println("Error: did not find file Adam.jar");
            return;
         }
         
         adamJarAtEveSiteLastModified = adamJarFile.lastModified();
         switchTo(adam);
         break;
         
      case AdamCheckJar:
         // what is the modification data of Adam.jar?
         adamJarFile = new File("Adam.jar");
         
         if ( ! adamJarFile.exists())
         {
            System.out.println("Error: did not find file Adam.jar");
            return;
         }
         
         long adamJarAtAdamSiteLastModified = adamJarFile.lastModified();
         
         if (adamJarAtAdamSiteLastModified < adamJarAtEveSiteLastModified)
         {
            // need to update Adam.jar at Adam site
            // new FetchFileFlow();
         }
         break;
         
      default:
         // nothing
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

      if (PROPERTY_IDMAP.equalsIgnoreCase(attribute))
      {
         return getIdMap();
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attribute))
      {
         return getTaskNo();
      }

      if (PROPERTY_ADAMJARATEVESITELASTMODIFIED.equalsIgnoreCase(attribute))
      {
         return getAdamJarAtEveSiteLastModified();
      }

      if (PROPERTY_ADAM.equalsIgnoreCase(attribute))
      {
         return getAdam();
      }

      if (PROPERTY_EVE.equalsIgnoreCase(attribute))
      {
         return getEve();
      }
      
      return null;
   }

   
   //==========================================================================
   
   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_IDMAP.equalsIgnoreCase(attrName))
      {
         setIdMap((org.sdmlib.serialization.json.JsonIdMap) value);
         return true;
      }

      if (PROPERTY_TASKNO.equalsIgnoreCase(attrName))
      {
         setTaskNo(Integer.parseInt(value.toString()));
         return true;
      }

      if (PROPERTY_ADAMJARATEVESITELASTMODIFIED.equalsIgnoreCase(attrName))
      {
         setAdamJarAtEveSiteLastModified(Long.parseLong(value.toString()));
         return true;
      }

      if (PROPERTY_ADAM.equalsIgnoreCase(attrName))
      {
         setAdam((org.sdmlib.model.taskflows.PeerProxy) value);
         return true;
      }

      if (PROPERTY_EVE.equalsIgnoreCase(attrName))
      {
         setEve((org.sdmlib.model.taskflows.PeerProxy) value);
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
   
   public UpdateAdamFlow withIdMap(org.sdmlib.serialization.json.JsonIdMap value)
   {
      setIdMap(value);
      return this;
   } 

   

   
   //==========================================================================
   
   public static final String PROPERTY_ADAMJARATEVESITELASTMODIFIED = "adamJarAtEveSiteLastModified";
   
   private long adamJarAtEveSiteLastModified;

   public long getAdamJarAtEveSiteLastModified()
   {
      return this.adamJarAtEveSiteLastModified;
   }
   
   public void setAdamJarAtEveSiteLastModified(long value)
   {
      if (this.adamJarAtEveSiteLastModified != value)
      {
         long oldValue = this.adamJarAtEveSiteLastModified;
         this.adamJarAtEveSiteLastModified = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ADAMJARATEVESITELASTMODIFIED, oldValue, value);
      }
   }
   
   public UpdateAdamFlow withAdamJarAtEveSiteLastModified(long value)
   {
      setAdamJarAtEveSiteLastModified(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_ADAM = "adam";
   
   private org.sdmlib.model.taskflows.PeerProxy adam;

   public org.sdmlib.model.taskflows.PeerProxy getAdam()
   {
      return this.adam;
   }
   
   public void setAdam(org.sdmlib.model.taskflows.PeerProxy value)
   {
      if (this.adam != value)
      {
         org.sdmlib.model.taskflows.PeerProxy oldValue = this.adam;
         this.adam = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ADAM, oldValue, value);
      }
   }
   
   public UpdateAdamFlow withAdam(org.sdmlib.model.taskflows.PeerProxy value)
   {
      setAdam(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_EVE = "eve";
   
   private org.sdmlib.model.taskflows.PeerProxy eve;

   public org.sdmlib.model.taskflows.PeerProxy getEve()
   {
      return this.eve;
   }
   
   public void setEve(org.sdmlib.model.taskflows.PeerProxy value)
   {
      if (this.eve != value)
      {
         org.sdmlib.model.taskflows.PeerProxy oldValue = this.eve;
         this.eve = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EVE, oldValue, value);
      }
   }
   
   public UpdateAdamFlow withEve(org.sdmlib.model.taskflows.PeerProxy value)
   {
      setEve(value);
      return this;
   } 
}

