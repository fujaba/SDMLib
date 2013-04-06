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

import org.sdmlib.utils.PropertyChangeInterface;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.concurrent.LinkedBlockingQueue;

import org.sdmlib.utils.StrUtil;
import org.sdmlib.replication.creators.ReplicationChangeSet;
import org.sdmlib.replication.creators.SharedSpaceSet;
import org.sdmlib.replication.creators.ReplicationChannelSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import org.sdmlib.serialization.interfaces.MapUpdateListener;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;
import org.sdmlib.replication.ChangeHistory;

public class SharedSpace extends Thread 
implements PropertyChangeInterface, PropertyChangeListener, MapUpdateListener
{

   private LinkedBlockingQueue<String> msgQueue = new LinkedBlockingQueue<String>();

   public void enqueueMsg(String msg)
   {
      try
      {
         msgQueue.put(msg);
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }

   @Override
   public void run()
   {
      while (true)
      {
         try
         {
            String msg = msgQueue.take();

            handleMessage(msg);
         }
         catch (Exception e)
         {
            // just try again
         }
      }
   }

   public void handleMessage(String msg)
   {
      this.isApplyingChangeMsg = true;

      try
      {
         // reconstruct change
         JsonObject jsonObject = new JsonObject(msg);

         JsonIdMap cmap = getChangeMap();

         ReplicationChange change = (ReplicationChange) cmap.readJson(jsonObject);

         // is change already known?
         if (getHistory().getChanges().contains(change))
         {
            // change already known, ignore
            change.withLog("ignore change already known", this.getName());
            
            System.out.println(change);
            return;
         }

         // is it a conflict
         String key = change.getTargetObjectId() + "|" + change.getTargetProperty(); 

         Object oldChange = this.getHistory().getChangeMap().get(key);

         if (change.getIsToManyProperty())
         {
            // no conflict, but consider ordering
            if (oldChange == null)
            {
               // no conflict do it
               applyChange(change);
            }
            else
            {
               ReplicationChangeSet oldChanges = (ReplicationChangeSet) oldChange;

               // new change will be last, just apply it.
               ReplicationChange higher = oldChanges.higher(change);

               if (higher == null)
               {
                  applyChange(change);
               }
               else
               {
                  // undo higher changes, apply, redo higher changes
                  // find source object, property and earlier content object
                  String changeMsg = higher.getChangeMsg();
                  JsonObject higherJson = new JsonObject(changeMsg);
                  String sourceId = higherJson.getString(JsonIdMap.ID);
                  Object sourceObject = map.getObject(sourceId);
                  JsonObject updateJson = (JsonObject) higherJson.get(JsonIdMap.UPDATE);
                  
                  for (Iterator<String> keyIter = updateJson.keys(); keyIter.hasNext();)
                  {
                     String property = keyIter.next();
                     
                     JsonObject targetJson = updateJson.getJsonObject(property);
                     
                     String targetId = targetJson.getString(JsonIdMap.ID);
                     
                     Object targetObj = map.getObject(targetId);
                     
                     // now remove targetObj and its successors from property collection
                     SendableEntityCreator creatorClass = map.getCreatorClass(sourceObject);
                     
                     Collection collection = (Collection) creatorClass.getValue(sourceObject, property);
                     
                     LinkedList<Object> higherList = new LinkedList<Object>();
                     boolean found = false;
                     for (Object obj : collection)
                     {
                        if (obj == targetObj)
                        {
                           found = true;
                        }
                        
                        if (found)
                        {
                           higherList.add(obj);
                        }
                     }
                     
                     // remove higher elems from collection
                     for (Object obj : higherList)
                     {
                        creatorClass.setValue(sourceObject, property + JsonIdMap.REMOVE, obj, null);
                     }
                     
                     // add new 
                     applyChange(change);
                     
                     // re-add higher elements
                     for (Object obj : higherList)
                     {
                        creatorClass.setValue(sourceObject, property, obj, null);
                     }
                     break;
                  }
               }
            }
         }
         else
         {
            // is new change later than old change?
            if (oldChange == null || ((ReplicationChange) oldChange).compareTo(change) < 0)
            {
               applyChange(change);
            }
         }
      }
      finally 
      {
         this.isApplyingChangeMsg = false;
      }


   }

   private void applyChange(ReplicationChange change)
   {
      // no conflict, apply change
      JsonObject jsonUpdate = new JsonObject(change.getChangeMsg());
      map.executeUpdateMsg(jsonUpdate);

      getHistory().addChange(change);
      
      this.lastChangeId = Math.max(lastChangeId, change.getHistoryIdNumber());
      
      change.withLog("change applied", this.getName());

      sendNewChange(change);
   }


   private boolean isApplyingChangeMsg = false;

   private int logLevel = 1;

   @Override
   public boolean sendUpdateMsg(Object oldObj, Object newObject,
         JsonObject jsonObject)
   {
      if (isApplyingChangeMsg)
      {
         // ignore
         return true;
      }

      ReplicationChange change = new ReplicationChange()
      .withHistoryIdPrefix(nodeId)
      .withHistoryIdNumber(getNewHistoryIdNumber())
      .withTargetObjectId(jsonObject.getString(JsonIdMap.ID))
      .withChangeMsg(jsonObject.toString(3));

      Object object = jsonObject.get(JsonIdMap.UPDATE);

      if(object != null)
      {
         JsonObject jsonUpdate = (JsonObject) object;
         for (Iterator<String> iter = jsonUpdate.keys(); iter.hasNext();)
         {
            String property = iter.next();
            change.withTargetProperty(property);
            break;
         }
      }

      Object targetObject = map.getObject(change.getTargetObjectId());
      SendableEntityCreator creator = map.getCreatorClass(targetObject);
      Object value = creator.getValue(targetObject, change.getTargetProperty());
      if (value != null && value instanceof Collection)
      {
         change.setIsToManyProperty(true);
      }
      
      if (logLevel >= Task.DO_LOG)
      {
         change.withLog("change recorded", this.getName());
      }
      
      getHistory().addChange(change);

      sendNewChange(change);

      return true;
   }

   private JsonIdMap changeMap = null;

   private void sendNewChange(ReplicationChange change)
   {
      JsonIdMap cmap = getChangeMap();

      JsonObject jsonObject = cmap.toJsonObject(change);

      String line = jsonObject.toString(); 

      for (ReplicationChannel channel : this.getChannels())
      {
         channel.send(line);
      }
   }

   private long getNewHistoryIdNumber()
   {
      lastChangeId++;

      return lastChangeId;
   }




   //==========================================================================
   private JsonIdMap getChangeMap()
   {
      if (changeMap == null)
      {
         changeMap = org.sdmlib.replication.creators.CreatorCreator.createIdMap(nodeId);
      }
      return changeMap;
   }

   public Object get(String attrName)
   {
      if (PROPERTY_SPACEID.equalsIgnoreCase(attrName))
      {
         return getSpaceId();
      }

      if (PROPERTY_NODE.equalsIgnoreCase(attrName))
      {
         return getNode();
      }

      if (PROPERTY_CHANNELS.equalsIgnoreCase(attrName))
      {
         return getChannels();
      }

      if (PROPERTY_HISTORY.equalsIgnoreCase(attrName))
      {
         return getHistory();
      }

      if (PROPERTY_LASTCHANGEID.equalsIgnoreCase(attrName))
      {
         return getLastChangeId();
      }

      if (PROPERTY_NODEID.equalsIgnoreCase(attrName))
      {
         return getNodeId();
      }

      return null;
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_SPACEID.equalsIgnoreCase(attrName))
      {
         setSpaceId((String) value);
         return true;
      }

      if (PROPERTY_NODE.equalsIgnoreCase(attrName))
      {
         setNode((ReplicationNode) value);
         return true;
      }

      if (PROPERTY_CHANNELS.equalsIgnoreCase(attrName))
      {
         addToChannels((ReplicationChannel) value);
         return true;
      }

      if ((PROPERTY_CHANNELS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromChannels((ReplicationChannel) value);
         return true;
      }

      if (PROPERTY_HISTORY.equalsIgnoreCase(attrName))
      {
         setHistory((ChangeHistory) value);
         return true;
      }

      if (PROPERTY_LASTCHANGEID.equalsIgnoreCase(attrName))
      {
         setLastChangeId(Long.parseLong(value.toString()));
         return true;
      }

      if (PROPERTY_NODEID.equalsIgnoreCase(attrName))
      {
         setNodeId((String) value);
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
      setNode(null);
      removeAllFromChannels();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
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

   public SharedSpace withSpaceId(String value)
   {
      setSpaceId(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getSpaceId());
      _.append(" ").append(this.getNodeId());
      return _.substring(1);
   }



   public static final SharedSpaceSet EMPTY_SET = new SharedSpaceSet();


   /********************************************************************
    * <pre>
    *              many                       one
    * SharedSpace ----------------------------------- ReplicationNode
    *              sharedSpaces                   node
    * </pre>
    */

   public static final String PROPERTY_NODE = "node";

   private ReplicationNode node = null;

   public ReplicationNode getNode()
   {
      return this.node;
   }

   public boolean setNode(ReplicationNode value)
   {
      boolean changed = false;

      if (this.node != value)
      {
         ReplicationNode oldValue = this.node;

         if (this.node != null)
         {
            this.node = null;
            oldValue.withoutSharedSpaces(this);
         }

         this.node = value;

         if (value != null)
         {
            value.withSharedSpaces(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_NODE, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public SharedSpace withNode(ReplicationNode value)
   {
      setNode(value);
      return this;
   } 

   public ReplicationNode createNode()
   {
      ReplicationNode value = new ReplicationNode();
      withNode(value);
      return value;
   } 


   /********************************************************************
    * <pre>
    *              one                       many
    * SharedSpace ----------------------------------- ReplicationChannel
    *              sharedSpace                   channels
    * </pre>
    */

   public static final String PROPERTY_CHANNELS = "channels";

   private ReplicationChannelSet channels = null;

   private JsonIdMap map;

   public JsonIdMap getMap()
   {
      return map;
   }

   public void setMap(JsonIdMap map)
   {
      this.map = map;
   }

   public ReplicationChannelSet getChannels()
   {
      if (this.channels == null)
      {
         return ReplicationChannel.EMPTY_SET;
      }

      return this.channels;
   }

   public boolean addToChannels(ReplicationChannel value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.channels == null)
         {
            this.channels = new ReplicationChannelSet();
         }

         changed = this.channels.add (value);

         if (changed)
         {
            value.withSharedSpace(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNELS, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromChannels(ReplicationChannel value)
   {
      boolean changed = false;

      if ((this.channels != null) && (value != null))
      {
         changed = this.channels.remove (value);

         if (changed)
         {
            value.setSharedSpace(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CHANNELS, value, null);
         }
      }

      return changed;   
   }

   public SharedSpace withChannels(ReplicationChannel value)
   {
      addToChannels(value);
      return this;
   } 

   public SharedSpace withoutChannels(ReplicationChannel value)
   {
      removeFromChannels(value);
      return this;
   } 

   public void removeAllFromChannels()
   {
      LinkedHashSet<ReplicationChannel> tmpSet = new LinkedHashSet<ReplicationChannel>(this.getChannels());

      for (ReplicationChannel value : tmpSet)
      {
         this.removeFromChannels(value);
      }
   }

   public ReplicationChannel createChannels()
   {
      ReplicationChannel value = new ReplicationChannel();
      withChannels(value);
      return value;
   }

   public void withMap(JsonIdMap map)
   {
      this.map = map;
      map.setUpdateMsgListener((MapUpdateListener) this); 
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // System.out.println(evt);
   }

   @Override
   public boolean readMessages(String type, Object value, JsonObject props)
   {
      return false;
   }

   @Override
   public boolean skipCollision(Object masterObj, String key, Object value,
         JsonObject removeJson, JsonObject updateJson)
   {
      return false;
   }

   @Override
   public boolean readMessageObj(Object element, String key, Object value,
         String typ)
   {
      return false;
   } 


   //==========================================================================

   public static final String PROPERTY_HISTORY = "history";

   private ChangeHistory history;

   public ChangeHistory getHistory()
   {
      if (history == null)
      {
         history = new ChangeHistory();
      }

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

   public SharedSpace withHistory(ChangeHistory value)
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

   public SharedSpace withLastChangeId(long value)
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

   public SharedSpace withNodeId(String value)
   {
      setNodeId(value);
      return this;
   } 
}

