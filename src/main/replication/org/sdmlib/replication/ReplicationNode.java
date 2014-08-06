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

package org.sdmlib.replication;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.ReplicationNodeCreator;
import org.sdmlib.replication.util.SharedSpaceSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;

public class ReplicationNode extends Thread implements PropertyChangeInterface
{
   private Collection<SendableEntityCreator> modelCreators = new LinkedHashSet<SendableEntityCreator>();
   
   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      removeAllFromSharedSpaces();
      withoutSharedSpaces(this.getSharedSpaces().toArray(new SharedSpace[this.getSharedSpaces().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * ReplicationNode ----------------------------------- SharedSpace
    *              node                   sharedSpaces
    * </pre>
    */

   public static final String PROPERTY_SHAREDSPACES = "sharedSpaces";

   private SharedSpaceSet sharedSpaces = null;

   public synchronized SharedSpace getOrCreateSharedSpace(String spaceId)
   {
      SharedSpace sharedSpace = this.getSharedSpaces().hasSpaceId(spaceId).first();

      if (sharedSpace == null)
      {
         sharedSpace = new SharedSpace().withSpaceId(spaceId);
         this.addToSharedSpaces(sharedSpace);
         sharedSpace.setName("SharedSpace" + getSharedSpaces().size());
         
         // add replication root 
         JsonIdMap map = ReplicationNodeCreator.createIdMap("s42");
         
         map.withCreator(modelCreators);
         
         for (SendableEntityCreator sendableEntityCreator : map)
         {
            System.out.println(sendableEntityCreator.toString());
         }
         
         sharedSpace.withMap(map);
         
         ChangeHistory history = new ChangeHistory();
         sharedSpace.setHistory(history);

         ReplicationRoot replicationRoot = new ReplicationRoot();
         sharedSpace.setReplicationRoot(replicationRoot);
         map.put(SharedSpace.REPLICATION_ROOT, replicationRoot);
         
         RemoteTaskBoard remoteTaskBoard = new RemoteTaskBoard();
         sharedSpace.setRemoteTaskBoard(remoteTaskBoard);
         map.put(SharedSpace.REMOTE_TASK_BOARD_ROOT, remoteTaskBoard);

         if (this.remoteTaskListener != null)
         {
            replicationRoot.addPropertyChangeListener(this.remoteTaskListener);
            remoteTaskBoard.getPropertyChangeSupport().addPropertyChangeListener(this.remoteTaskListener);
            
            this.remoteTaskListener.propertyChange(new PropertyChangeEvent(sharedSpace, "new", null, remoteTaskBoard));
         }
         
         sharedSpace.start();
      }

      return sharedSpace;
   }

   public SharedSpaceSet getSharedSpaces()
   {
      if (this.sharedSpaces == null)
      {
         return SharedSpace.EMPTY_SET;
      }

      return this.sharedSpaces;
   }

   public boolean addToSharedSpaces(SharedSpace value)
   {
      SharedSpace oldContent = null;

      if (value != null)
      {
         if (this.sharedSpaces == null)
         {
            this.sharedSpaces = new SharedSpaceSet();
         }

         oldContent = this.getSharedSpaces().hasSpaceId(value.getSpaceId()).first();
               
         this.sharedSpaces.add(value);

         if (oldContent != value)
         {
            value.withNode(this);
            getPropertyChangeSupport().firePropertyChange(
               PROPERTY_SHAREDSPACES, null, value);
         }
      }

      return oldContent != value;
   }

   public boolean removeFromSharedSpaces(SharedSpace value)
   {
      boolean flag = false;
      
      if ((this.sharedSpaces != null) && (value != null))
      {
         flag = this.sharedSpaces.remove(value);

         if (flag)
         {
            value.setNode(null);
            getPropertyChangeSupport().firePropertyChange(
               PROPERTY_SHAREDSPACES, value, null);
         }
      }

      return flag;
   }

   public ReplicationNode withSharedSpaces(SharedSpace value)
   {
      addToSharedSpaces(value);
      return this;
   }

   public ReplicationNode withoutSharedSpaces(SharedSpace value)
   {
      removeFromSharedSpaces(value);
      return this;
   }

   public void removeAllFromSharedSpaces()
   {
      LinkedHashSet<SharedSpace> tmpSet = new LinkedHashSet<SharedSpace>(this
         .getSharedSpaces().values());

      for (SharedSpace value : tmpSet)
      {
         this.removeFromSharedSpaces(value);
      }
   }

   public SharedSpace createSharedSpaces()
   {
      SharedSpace value = new SharedSpace();
      withSharedSpaces(value);
      return value;
   }

   public ReplicationNode withSharedSpaces(SharedSpace... value)
   {
      for (SharedSpace item : value)
      {
         addToSharedSpaces(item);
      }
      return this;
   }

   public ReplicationNode withoutSharedSpaces(SharedSpace... value)
   {
      for (SharedSpace item : value)
      {
         removeFromSharedSpaces(item);
      }
      return this;
   }
   
   private PropertyChangeListener remoteTaskListener; 

   public ReplicationNode withRemoteTaskListener(PropertyChangeListener remoteTaskListener)
   {
      this.remoteTaskListener = remoteTaskListener;
      return this;
   }

   
   //==========================================================================
   
   public static final String PROPERTY_SPACEID = "spaceId";
   
   private String spaceId;

   public String getSpaceId()
   {
      return this.spaceId;
   }
   
   public void setSpaceId(String value)
   {
      if ( ! StrUtil.stringEquals(this.spaceId, value))
      {
         String oldValue = this.spaceId;
         this.spaceId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SPACEID, oldValue, value);
      }
   }
   
   public ReplicationNode withSpaceId(String value)
   {
      setSpaceId(value);
      return this;
   } 


   @Override
   public String toString()
   {
      StringBuilder _ = new StringBuilder();
      
      _.append(" ").append(this.getSpaceId());
      _.append(" ").append(this.getNodeId());
      return _.substring(1);
   }


   
   //==========================================================================
   
   public static final String PROPERTY_HISTORY = "history";
   
   private ChangeHistory history;

   public ChangeHistory getHistory()
   {
      return this.history;
   }
   
   public void setHistory(ChangeHistory value)
   {
      if (this.history != value)
      {
         ChangeHistory oldValue = this.history;
         this.history = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HISTORY, oldValue, value);
      }
   }
   
   public ReplicationNode withHistory(ChangeHistory value)
   {
      setHistory(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_LASTCHANGEID = "lastChangeId";
   
   private long lastChangeId;

   public long getLastChangeId()
   {
      return this.lastChangeId;
   }
   
   public void setLastChangeId(long value)
   {
      if (this.lastChangeId != value)
      {
         long oldValue = this.lastChangeId;
         this.lastChangeId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LASTCHANGEID, oldValue, value);
      }
   }
   
   public ReplicationNode withLastChangeId(long value)
   {
      setLastChangeId(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_NODEID = "nodeId";
   
   private String nodeId;

   public String getNodeId()
   {
      return this.nodeId;
   }
   
   public void setNodeId(String value)
   {
      if ( ! StrUtil.stringEquals(this.nodeId, value))
      {
         String oldValue = this.nodeId;
         this.nodeId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NODEID, oldValue, value);
      }
   }
   
   public ReplicationNode withNodeId(String value)
   {
      setNodeId(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_JAVAFXAPPLICATION = "javaFXApplication";
   
   private boolean javaFXApplication;

   public boolean isJavaFXApplication()
   {
      return this.javaFXApplication;
   }
   
   public void setJavaFXApplication(boolean value)
   {
      if (this.javaFXApplication != value)
      {
         boolean oldValue = this.javaFXApplication;
         this.javaFXApplication = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_JAVAFXAPPLICATION, oldValue, value);
      }
   }
   
   public ReplicationNode withJavaFXApplication(boolean value)
   {
      setJavaFXApplication(value);
      return this;
   }

   public Collection<SendableEntityCreator> getModelCreators()
   {
      return modelCreators;
   }

}

