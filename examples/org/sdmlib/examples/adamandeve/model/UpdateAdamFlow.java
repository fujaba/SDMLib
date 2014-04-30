/*
   Copyright (c) 2014 Stefan 
   
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
   
package org.sdmlib.examples.adamandeve.model;

import org.sdmlib.logger.TaskFlow;
import org.sdmlib.serialization.util.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;

public class UpdateAdamFlow extends TaskFlow implements PropertyChangeInterface
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
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
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public void run(  )
   {
      
   }

   
   //==========================================================================
   
   public Object[] getTaskNames(  )
   {
      return null;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_ADAM = "adam";
   
   private org.sdmlib.logger.PeerProxy adam;

   public org.sdmlib.logger.PeerProxy getAdam()
   {
      return this.adam;
   }
   
   public void setAdam(org.sdmlib.logger.PeerProxy value)
   {
      if (this.adam != value)
      {
         org.sdmlib.logger.PeerProxy oldValue = this.adam;
         this.adam = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ADAM, oldValue, value);
      }
   }
   
   public UpdateAdamFlow withAdam(org.sdmlib.logger.PeerProxy value)
   {
      setAdam(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_EVE = "eve";
   
   private org.sdmlib.logger.PeerProxy eve;

   public org.sdmlib.logger.PeerProxy getEve()
   {
      return this.eve;
   }
   
   public void setEve(org.sdmlib.logger.PeerProxy value)
   {
      if (this.eve != value)
      {
         org.sdmlib.logger.PeerProxy oldValue = this.eve;
         this.eve = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EVE, oldValue, value);
      }
   }
   
   public UpdateAdamFlow withEve(org.sdmlib.logger.PeerProxy value)
   {
      setEve(value);
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
   
   public UpdateAdamFlow withIdMap(org.sdmlib.serialization.json.SDMLibJsonIdMap value)
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


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getTaskNo());
      return _.substring(1);
   }

}

