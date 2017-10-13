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
import java.util.List;

import org.sdmlib.StrUtil;
import org.sdmlib.modelspace.util.ModelCloudProxySet;
import org.sdmlib.replication.ChangeEvent;
import org.sdmlib.replication.ChangeEventList;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.json.JsonObject;
import org.sdmlib.modelspace.ModelCloud;
import org.sdmlib.modelspace.ModelCloudProxy;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/modelspace/ModelSpaceModel.java'>ModelSpaceModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/modelspace/ModelSpaceModel.java'>ModelSpaceModel.java</a>
 */
   public  class ModelSpaceProxy implements PropertyChangeInterface, SendableEntity
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
   
      setCloud(null);
      withoutProvidingClouds(this.getProvidingClouds().toArray(new ModelCloudProxy[this.getProvidingClouds().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   
   //==========================================================================
   
   public static final String PROPERTY_LOCATION = "location";
   
   private String location;

   public String getLocation()
   {
      return this.location;
   }
   
   public void setLocation(String value)
   {
      if ( ! StrUtil.stringEquals(this.location, value)) {
      
         String oldValue = this.location;
         this.location = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LOCATION, oldValue, value);
      }
   }
   
   public ModelSpaceProxy withLocation(String value)
   {
      setLocation(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getLocation());
      return result.substring(1);
   }


   
   /********************************************************************
    * <pre>
    *              many                       one
    * ModelSpaceProxy ----------------------------------- ModelCloud
    *              modelSpaces                   cloud
    * </pre>
    */
   
   public static final String PROPERTY_CLOUD = "cloud";

   private ModelCloud cloud = null;

   public ModelCloud getCloud()
   {
      return this.cloud;
   }

   public boolean setCloud(ModelCloud value)
   {
      boolean changed = false;
      
      if (this.cloud != value)
      {
         ModelCloud oldValue = this.cloud;
         
         if (this.cloud != null)
         {
            this.cloud = null;
            oldValue.withoutModelSpaces(this);
         }
         
         this.cloud = value;
         
         if (value != null)
         {
            value.withModelSpaces(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CLOUD, oldValue, value);
         changed = true;
      }
      
      return changed;
   }

   public ModelSpaceProxy withCloud(ModelCloud value)
   {
      setCloud(value);
      return this;
   } 

   public ModelCloud createCloud()
   {
      ModelCloud value = new ModelCloud();
      withCloud(value);
      return value;
   }

   
   private ModelDirListener modelDirListener = null;
   
   public ModelDirListener getModelDirListener()
   {
      return modelDirListener;
   }
   
   public void setModelDirListener(ModelDirListener modelDirListener)
   {
      if (this.modelDirListener != modelDirListener)
      {
         
         this.modelDirListener = modelDirListener;
         
         if (modelDirListener != null)
         {
            // subscribe at providing cloud servers
            cloud.subscribeForModelSpace(location);
         }
      }
   } 

   
   /********************************************************************
    * <pre>
    *              many                       many
    * ModelSpaceProxy ----------------------------------- ModelCloudProxy
    *              providedSpaces                   providingClouds
    * </pre>
    */
   
   public static final String PROPERTY_PROVIDINGCLOUDS = "providingClouds";

   private ModelCloudProxySet providingClouds = null;
   
   public ModelCloudProxySet getProvidingClouds()
   {
      if (this.providingClouds == null)
      {
         return ModelCloudProxySet.EMPTY_SET;
      }
   
      return this.providingClouds;
   }

   public ModelSpaceProxy withProvidingClouds(ModelCloudProxy... value)
   {
      if(value==null){
         return this;
      }
      for (ModelCloudProxy item : value)
      {
         if (item != null)
         {
            if (this.providingClouds == null)
            {
               this.providingClouds = new ModelCloudProxySet();
            }
            
            boolean changed = this.providingClouds.add (item);

            if (changed)
            {
               item.withProvidedSpaces(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PROVIDINGCLOUDS, null, item);
            }
         }
      }
      return this;
   } 

   
   public ModelSpaceProxy withoutProvidingClouds(ModelCloudProxy... value)
   {
      for (ModelCloudProxy item : value)
      {
         if ((this.providingClouds != null) && (item != null))
         {
            if (this.providingClouds.remove(item))
            {
               item.withoutProvidedSpaces(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_PROVIDINGCLOUDS, item, null);
            }
         }
      }
      return this;
   }

   
   public ModelCloudProxy createProvidingClouds()
   {
      ModelCloudProxy value = new ModelCloudProxy();
      withProvidingClouds(value);
      return value;
   }

   
   public void sendAllDataTo(ModelCloudProxy cloudProxy)
   {
      if (modelDirListener != null)
      {
         ChangeEventList history = modelDirListener.getHistory();
         
         List<ChangeEvent> changes = history.getChanges();
         
         for (ChangeEvent change : changes)
         {
            JsonObject jsonObject = change.toJson();
            jsonObject.withKeyValue("modelSpaceName", location);
            
            String msg = jsonObject.toString();
            
            cloudProxy.getChannel().send(msg);
         }
      }
      
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
