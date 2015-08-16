/*
   Copyright (c) 2015 zuendorf
   
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
   
package org.sdmlib.modelspace;

import org.sdmlib.serialization.PropertyChangeInterface;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import org.sdmlib.StrUtil;

public  class ModelCloudProxy implements PropertyChangeInterface
{

   
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
   
      setRoot(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_HOSTNAME = "hostName";
   
   private String hostName;

   public String getHostName()
   {
      return this.hostName;
   }
   
   public void setHostName(String value)
   {
      if ( ! StrUtil.stringEquals(this.hostName, value)) {
      
         String oldValue = this.hostName;
         this.hostName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HOSTNAME, oldValue, value);
      }
   }
   
   public ModelCloudProxy withHostName(String value)
   {
      setHostName(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getHostName());
      result.append(" ").append(this.getPortNo());
      return result.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_PORTNO = "portNo";
   
   private int portNo;

   public int getPortNo()
   {
      return this.portNo;
   }
   
   public void setPortNo(int value)
   {
      if (this.portNo != value) {
      
         int oldValue = this.portNo;
         this.portNo = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_PORTNO, oldValue, value);
      }
   }
   
   public ModelCloudProxy withPortNo(int value)
   {
      setPortNo(value);
      return this;
   } 

   
   /********************************************************************
    * <pre>
    *              many                       one
    * ModelCloudProxy ----------------------------------- ModelCloud
    *              servers                   root
    * </pre>
    */
   
   public static final String PROPERTY_ROOT = "root";

   private ModelCloud root = null;

   public ModelCloud getRoot()
   {
      return this.root;
   }

   public boolean setRoot(ModelCloud value)
   {
      boolean changed = false;
      
      if (this.root != value)
      {
         ModelCloud oldValue = this.root;
         
         if (this.root != null)
         {
            this.root = null;
            oldValue.withoutServers(this);
         }
         
         this.root = value;
         
         if (value != null)
         {
            value.withServers(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ROOT, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public ModelCloudProxy withRoot(ModelCloud value)
   {
      setRoot(value);
      return this;
   } 

   public ModelCloud createRoot()
   {
      ModelCloud value = new ModelCloud();
      withRoot(value);
      return value;
   } 
}
