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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.sdmlib.StrUtil;
import org.sdmlib.modelspace.util.ModelSpaceProxySet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;


   public  class ModelCloudProxy implements PropertyChangeInterface, SendableEntity
{

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }
   
   public boolean addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
      return true;
   }
   
   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
      getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
      return true;
   }
   
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(listener);
		}
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
   //==========================================================================
   
   
   public void removeYou()
   {
   
      setRoot(null);
      withoutProvidedSpaces(this.getProvidedSpaces().toArray(new ModelSpaceProxy[this.getProvidedSpaces().size()]));
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


   //==========================================================================
   
   public static final String PROPERTY_STATE = "state";
   
   private String state = "offline";

   public String getState()
   {
      return this.state;
   }
   
   public void setState(String value)
   {
      if ( ! StrUtil.stringEquals(this.state, value)) {
      
         String oldValue = this.state;
         this.state = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_STATE, oldValue, value);
      }
   }
   
   public ModelCloudProxy withState(String value)
   {
      setState(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getHostName());
      result.append(" ").append(this.getPortNo());
      result.append(" ").append(this.getState());
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

   private ModelCloudChannel channel;

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

   public void setChannel(ModelCloudChannel modelCloudChannel)
   {
      this.channel = modelCloudChannel;
   } 
   
   public ModelCloudChannel getChannel()
   {
      return channel;
   }

   
   /********************************************************************
    * <pre>
    *              many                       many
    * ModelCloudProxy ----------------------------------- ModelSpaceProxy
    *              providingClouds                   providedSpaces
    * </pre>
    */
   
   public static final String PROPERTY_PROVIDEDSPACES = "providedSpaces";

   private ModelSpaceProxySet providedSpaces = null;
   
   public ModelSpaceProxySet getProvidedSpaces()
   {
      if (this.providedSpaces == null)
      {
         return ModelSpaceProxySet.EMPTY_SET;
      }
   
      return this.providedSpaces;
   }

   public ModelCloudProxy withProvidedSpaces(ModelSpaceProxy... value)
   {
      if(value==null){
         return this;
      }
      for (ModelSpaceProxy item : value)
      {
         if (item != null)
         {
            if (this.providedSpaces == null)
            {
               this.providedSpaces = new ModelSpaceProxySet();
            }
            
            boolean changed = this.providedSpaces.add (item);

            if (changed)
            {
               item.withProvidingClouds(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PROVIDEDSPACES, null, item);
            }
         }
      }
      return this;
   } 

   public ModelCloudProxy withoutProvidedSpaces(ModelSpaceProxy... value)
   {
      for (ModelSpaceProxy item : value)
      {
         if ((this.providedSpaces != null) && (item != null))
         {
            if (this.providedSpaces.remove(item))
            {
               item.withoutProvidingClouds(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PROVIDEDSPACES, item, null);
            }
         }
      }
      return this;
   }

   public ModelSpaceProxy createProvidedSpaces()
   {
      ModelSpaceProxy value = new ModelSpaceProxy();
      withProvidedSpaces(value);
      return value;
   } 

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null) {
   		listeners.firePropertyChange(propertyName, oldValue, newValue);
   		return true;
   	}
   	return false;
   }
   }
