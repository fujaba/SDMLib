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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.sdmlib.StrUtil;
import org.sdmlib.modelspace.util.ModelCloudProxySet;
import org.sdmlib.modelspace.util.ModelSpaceProxySet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.json.JsonObject;


   public  class ModelCloud implements PropertyChangeInterface, SendableEntity
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
   
      withoutServers(this.getServers().toArray(new ModelCloudProxy[this.getServers().size()]));
      withoutModelSpaces(this.getModelSpaces().toArray(new ModelSpaceProxy[this.getModelSpaces().size()]));
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
      result.append(" ").append(this.getHostName());
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

   public ModelCloudProxy getProxy(String hostName, long portNo)
   {
      for (ModelCloudProxy proxy : this.getServers())
      {
         if (proxy.getHostName().equals(hostName) && proxy.getPortNo() == portNo)
         {
            return proxy;
         }
      }
      
      return null;
   }
   
   
   public ModelSpaceProxy getFromModelSpaces(String spaceName)
   {
      for (ModelSpaceProxy proxy : this.getModelSpaces())
      {
         if (proxy.getLocation().equals(spaceName))
         {
            return proxy;
         }
      }
      
      return null;
   }

   
   public ModelSpaceProxy getOrCreateModelSpaceProxy(String location, ModelDirListener modelDirListener)
   {
      for (ModelSpaceProxy proxy : this.getModelSpaces())
      {
         if (proxy.getLocation().equals(location))
         {
            if (modelDirListener != null)
            {
               proxy.setModelDirListener(modelDirListener); 
            }
            
            return proxy;
         }
      }
      
      ModelSpaceProxy proxy = new ModelSpaceProxy().withLocation(location).withCloud(this);
      
      if (modelDirListener != null)
      {
         proxy.setModelDirListener(modelDirListener); 
      }
      
      return proxy;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * ModelCloud ----------------------------------- ModelSpaceProxy
    *              cloud                   modelSpaces
    * </pre>
    */
   
   public static final String PROPERTY_MODELSPACES = "modelSpaces";

   private ModelSpaceProxySet modelSpaces = null;
   
   public ModelSpaceProxySet getModelSpaces()
   {
      if (this.modelSpaces == null)
      {
         return ModelSpaceProxySet.EMPTY_SET;
      }
   
      return this.modelSpaces;
   }

   public ModelCloud withModelSpaces(ModelSpaceProxy... value)
   {
      if(value==null){
         return this;
      }
      for (ModelSpaceProxy item : value)
      {
         if (item != null)
         {
            if (this.modelSpaces == null)
            {
               this.modelSpaces = new ModelSpaceProxySet();
            }
            
            boolean changed = this.modelSpaces.add (item);

            if (changed)
            {
               item.withCloud(this);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELSPACES, null, item);
            }
         }
      }
      return this;
   } 

   public ModelCloud withoutModelSpaces(ModelSpaceProxy... value)
   {
      for (ModelSpaceProxy item : value)
      {
         if ((this.modelSpaces != null) && (item != null))
         {
            if (this.modelSpaces.remove(item))
            {
               item.setCloud(null);
               getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELSPACES, item, null);
            }
         }
      }
      return this;
   }

   public ModelSpaceProxy createModelSpaces()
   {
      ModelSpaceProxy value = new ModelSpaceProxy();
      withModelSpaces(value);
      return value;
   }
   

   public void subscribeForModelSpace(String modelSpaceName)
   {
      for (ModelCloudProxy cloudProxy : this.getServers())
      {
         if (cloudProxy.getState().equals("online"))
         {
            ModelCloudChannel channel = cloudProxy.getChannel();

            JsonObject jsonObject = new JsonObject();
            jsonObject.withKeyValue("msgtype", "subscribe")
            .withKeyValue("hostName", this.getHostName())
            .withKeyValue("portNo", this.getAcceptPort())
            .withKeyValue("modelSpaceName", modelSpaceName);
            
            String msg = jsonObject.toString();
            
            channel.send(msg);
         }
         
      }
      
   }

   
   public void subscribeForExistingModelSpaces()
   {
      for (ModelSpaceProxy spaceProxy : this.getModelSpaces())
      {
         if (spaceProxy.getModelDirListener() != null)
         {
            String location = spaceProxy.getLocation();
            
            this.subscribeForModelSpace(location);
         }
      }
   } 

   
   //==========================================================================
   private TaskBoard taskBoard;

   private TaskLane myTaskLane;

   public void setTaskBoard(TaskBoard taskBoard)
   {
      this.taskBoard = taskBoard;
   } 
   
   public void setMyTaskLane(TaskLane myTaskLane)
   {
      this.myTaskLane = myTaskLane;
   }


   private String location;
   
   public void setLocation(String location)
   {
      this.location = location;
   }
   
   public String getLocation()
   {
      return location;
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

   public ModelCloud withHostName(String value)
   {
      setHostName(value);
      return this;
   }

   
   public Task getOrCreateFileTask(String spaceName, String fileName, long lastModified)
   {
      // search through task board
      for (Task task : myTaskLane.getMyRequests())
      {
         if (task.getSpaceName().equals(spaceName)
               && task.getFileName().equals(fileName)
               && task.getLastModified() == lastModified)
         {
            return task;
         }
      }
            
      Task task = new Task()
         .withSpaceName(spaceName)
         .withFileName(fileName)
         .withLastModified(lastModified)
         .withState("init")
         .withFileTargetCloud(myTaskLane)
         .withLane(myTaskLane);
      
      return task;
   }


   public void handleTask(Task task)
   {
      if (task.getState().equals("init"))
      {
         // look for provider and forward task
         ModelSpaceProxy spaceProxy = getFromModelSpaces(task.getSpaceName());
         
         for (ModelCloudProxy cloudProxy : spaceProxy.getProvidingClouds())
         {
            if (cloudProxy.getState().equals("online"))
            {
               // got a provider, find its task lane
               TaskLane providerLane = taskBoard.getOrCreateLane(cloudProxy.getHostName(), cloudProxy.getPortNo());
               task.setLane(null);
               task.setState("sendFileToTarget");
               task.setLane(providerLane);
            }
         }
      }
      else if (task.getState().equals("sendFileToTarget") && task.getLane() == myTaskLane)
      {
         // I shall send the file to my partner
         TaskLane targetCloudLane = task.getFileTargetCloud();
         ModelCloudProxy targetCloudProxy = this.getProxy(targetCloudLane.getHostName(), targetCloudLane.getPortNo());
         if (targetCloudProxy.getState().equals("online"))
         {
            ModelCloudChannel channel = targetCloudProxy.getChannel();
            
            // open file
            Path path = Paths.get(this.location + "/" + task.getSpaceName() + "/" + task.getFileName());
            try
            {
               byte[] allBytes = Files.readAllBytes(path);
               channel.sendFile(task.getSpaceName() + "/" + task.getFileName(), task.getLastModified(), allBytes);
               task.setState("prepareToReceive");
            }
            catch (IOException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
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
