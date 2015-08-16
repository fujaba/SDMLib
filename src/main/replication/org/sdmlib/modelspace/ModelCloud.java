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
import org.sdmlib.modelspace.util.ModelCloudProxySet;

public  class ModelCloud implements PropertyChangeInterface
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
   
      withoutServers(this.getServers().toArray(new ModelCloudProxy[this.getServers().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_ACCEPTPORT = "acceptPort";
   
   private int acceptPort;

   public int getAcceptPort()
   {
      return this.acceptPort;
   }
   
   public void setAcceptPort(int value)
   {
      if (this.acceptPort != value) {
      
         int oldValue = this.acceptPort;
         this.acceptPort = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_ACCEPTPORT, oldValue, value);
      }
   }
   
   public ModelCloud withAcceptPort(int value)
   {
      setAcceptPort(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getAcceptPort());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              one                       many
    * ModelCloud ----------------------------------- ModelCloudProxy
    *              root                   servers
    * </pre>
    */
   
   public static final String PROPERTY_SERVERS = "servers";

   private ModelCloudProxySet servers = null;
   
   public ModelCloudProxySet getServers()
   {
      if (this.servers == null)
      {
         return ModelCloudProxySet.EMPTY_SET;
      }
   
      return this.servers;
   }

   public ModelCloud withServers(ModelCloudProxy... value)
   {
      if(value==null){
         return this;
      }
      for (ModelCloudProxy item : value)
      {
         if (item != null)
         {
            if (this.servers == null)
            {
               this.servers = new ModelCloudProxySet();
            }
            
            boolean changed = this.servers.add (item);

            if (changed)
            {
               item.withRoot(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVERS, null, item);
            }
         }
      }
      return this;
   } 

   public ModelCloud withoutServers(ModelCloudProxy... value)
   {
      for (ModelCloudProxy item : value)
      {
         if ((this.servers != null) && (item != null))
         {
            if (this.servers.remove(item))
            {
               item.setRoot(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_SERVERS, item, null);
            }
         }
      }
      return this;
   }

   public ModelCloudProxy createServers()
   {
      ModelCloudProxy value = new ModelCloudProxy();
      withServers(value);
      return value;
   } 
}
