/*
   Copyright (c) 2014 zuendorf 
   
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

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.application.Platform;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.SharedSpace.ChannelMsg;
import org.sdmlib.replication.SharedSpace.HookAction;
import org.sdmlib.replication.util.ReplicationChangeSet;
import org.sdmlib.replication.util.ReplicationNodeCreator;
import org.sdmlib.replication.util.SeppelChannelSet;
import org.sdmlib.replication.util.SeppelScopeSet;
import org.sdmlib.replication.util.SeppelSpaceCreator;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.MapUpdateListener;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.json.JsonTokener;

public class SeppelSpace extends Thread implements PropertyChangeInterface, MapUpdateListener
{
   //==========================================================================
   private LinkedBlockingQueue<ChannelMsg> msgQueue = new LinkedBlockingQueue<ChannelMsg>();

   
   //==========================================================================
   @Override
   public void run()
   {
      while (true)
      {
         try
         {
            ChannelMsg msg = msgQueue.take();

            handleMessage(msg);
         }
         catch (Exception e)
         {
            // just try again
         }
      }
   }


   //============================================================================
   public void enqueueMsg(SeppelChannel channel, String msg)
   {
      try
      {
         final ChannelMsg channelMsg = new ChannelMsg(channel, msg);
         
         if (isJavaFXApplication())
         {
            Platform.runLater(new Runnable()
            {
               @Override
               public void run()
               {
                  handleMessage(channelMsg);
               }
            });
         }
         else
         {
            msgQueue.put(channelMsg);
         }
      }
      catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }

   public void handleMessage(ChannelMsg msg)
   {
      try
      {
         // reconstruct change
         JsonObject jsonObject = new JsonObject().withValue(msg.msg);

         if ( msg.channel != null &&  ! msg.channel.isLoginValidated())
         {
            // this message should be the login message, validate login
            try {
               // if there is something wrong, just throw an exception and terminate
               String spaceId = jsonObject.getString("spaceId");
               String login = jsonObject.getString("login");
               String pwd = jsonObject.getString("pwd");

               //               SeppelUser theUser = selfProxy.getKnownUsers()
               //                     .hasLoginName(login)
               //                     .hasPassword(pwd)
               //                     .first();
               //
//               SeppelSpaceProxy theSpace; // = theUser.getOrCreateSpaces(spaceId);
//               selfProxy.withPartners(theSpace);
//               msg.channel.setSeppelSpaceProxy(theSpace);
               
//               if (theSpace.getScopes().isEmpty())
//               {
//                  // provide initial scope
//                  theSpace.withScopes(theUser.getScopes().toArray(new SeppelScope[] {}));
//               }
               
               // that worked, set channel to valid
               msg.channel.setLoginValidated(true);
            }
            finally 
            {
               try
               {
                  msg.channel.getMsgQueue().put("ckeck");
               }
               catch (InterruptedException e)
               {
                  e.printStackTrace();
               }
               
               // now send all related changes
               sendAllChanges(msg.channel);
               
            }
            return;
          }   
         

         // handle change messages 
         this.isApplyingChangeMsg = true;

         JsonIdMap cmap = getChangeMap();
         
         
         ReplicationChange change = (ReplicationChange) cmap.decode(jsonObject);
         
         change.setChangeMsg(EntityUtil.unQuote(change.getChangeMsg()));

         // is change already known?
         if (getHistory().getChanges().contains(change))
         {
            // change already known, ignore
            return;
         }

         // try to apply change
         // is it a conflict?
         String key = change.getTargetObjectId() + "|" + change.getTargetProperty();

         Object oldChange = this.getHistory().getChangeMap().get(key);

         if (change.getIsToManyProperty())
         {
            // no conflict, but consider ordering
            if (oldChange == null)
            {
               // no conflict do it
               applyChange(change, msg.channel);
            }
            else
            {
               ReplicationChangeSet oldChanges = (ReplicationChangeSet) oldChange;

               ReplicationChange higher = oldChanges.higher(change);

               if (higher == null)
               {
                  // new change will be last, just apply it.
                  applyChange(change, msg.channel);
               }
               else
               {
                  // undo higher changes, apply, redo higher changes
                  // find source object, property and earlier content object
                  String changeMsg = higher.getChangeMsg();
                  JsonObject higherJson = new JsonObject().withValue(changeMsg);
                  String sourceId = higherJson.getString(JsonIdMap.ID);
                  Object sourceObject = map.getObject(sourceId);
                  
                  JsonObject updateJson = (JsonObject) higherJson.get(JsonIdMap.UPDATE);
                  
                  if (updateJson == null)
                  {
                     updateJson = (JsonObject) higherJson.get(JsonIdMap.REMOVE);
                  }

                  for (Iterator<String> keyIter = updateJson.keys(); keyIter.hasNext();)
                  {
                     String property = keyIter.next();

                     JsonObject targetJson = updateJson.getJsonObject(property);

                     String targetId = targetJson.getString(JsonIdMap.ID);

                     Object targetObj = map.getObject(targetId);

                     // now remove targetObj and its successors from property
                     // collection
                     SendableEntityCreator creatorClass = map.getCreatorClass(sourceObject);

                     Collection collection = (Collection) creatorClass.getValue(sourceObject,
                        property);

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
                     applyChange(change, msg.channel);

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
               applyChange(change, msg.channel);
            }
            else
            {
               // there is a newer change, but we may need to keep this one
               // until
               // we have synchronized with the sending node.
               getHistory().addToChanges(change);
               getHistory().addToObsoleteChanges(change);
            }
         }
      }
      finally
      {
         this.isApplyingChangeMsg = false;
      }

   }

   public class ChannelMsg
   {
      public ChannelMsg(SeppelChannel channel, String msg)
      {
         this.channel = channel;
         this.msg = msg;
      }
   
      public SeppelChannel channel;

      public String msg;
   }
   
   
   private void applyChange(ReplicationChange change, SeppelChannel sender)
   {
      applyChangeLocally(change);

      sendNewChange(change);
   }

   public void applyChangeLocally(ReplicationChange change)
   {
      // no conflict, apply change
      JsonObject jsonUpdate = new JsonObject(); 
      
      new JsonTokener().withAllowCRLF(true).withText(change.getChangeMsg()).parseToEntity(jsonUpdate);
   
      try
      {
         this.setReadMessages(true);
         map.executeUpdateMsg(jsonUpdate);
      } catch (Exception e) {
         e.printStackTrace();
      }
      finally
      {
         this.setReadMessages(false);
      }

      getHistory().addChange(change);

      writeChange(change);

      this.lastChangeId = Math.max(lastChangeId, change.getHistoryIdNumber());
   }


   //==============================================================================
   @Override
   public boolean sendUpdateMsg(Object target, String property, Object oldObj, Object newObject, JsonObject jsonObject)
   {
      if (isApplyingChangeMsg)
      {
         // ignore
         return true;
      }

      ReplicationChange change = new ReplicationChange()
      .withHistoryIdPrefix(spaceId)
      .withHistoryIdNumber(getNewHistoryIdNumber())
      .withTargetObjectId(jsonObject.getString(JsonIdMap.ID))
      .withChangeMsg(jsonObject.toString());

      Object object = jsonObject.get(JsonIdMap.UPDATE);
      
      if (object == null)
      {
         object = jsonObject.get(JsonIdMap.REMOVE);
      }

      if (object != null)
      {
         JsonObject jsonUpdate = (JsonObject) object;
         Iterator<String> iter = jsonUpdate.keys();
         if ( iter.hasNext())
         {
            String prop = iter.next();
            change.withTargetProperty(prop);
         }
      }

      Object targetObject = map.getObject(change.getTargetObjectId());
      SendableEntityCreator creator = map.getCreatorClass(targetObject);
      Object value = creator.getValue(targetObject, change.getTargetProperty());
      if (value != null && value instanceof Collection)
      {
         change.setIsToManyProperty(true);
      }

      getHistory().addChange(change);

      writeChange(change);

      sendNewChange(change);

      return true;
   }

   
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
   
   public SeppelSpace withSpaceId(String value)
   {
      setSpaceId(value);
      return this;
   } 
   
   private SeppelSpaceProxy selfProxy;

   public SeppelSpaceProxy getSelfProxy()
   {
      return selfProxy;
   }

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();
      
      result.append(" ").append(this.getSpaceId());
      return result.substring(1);
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
   
   public SeppelSpace withHistory(ChangeHistory value)
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
   
   public SeppelSpace withLastChangeId(long value)
   {
      setLastChangeId(value);
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
   
   public SeppelSpace withJavaFXApplication(boolean value)
   {
      setJavaFXApplication(value);
      return this;
   }
   
 
   //==============================================================================
   private JsonIdMap map;

   public JsonIdMap getMap()
   {
      return map;
   }

   public void setMap(JsonIdMap map)
   {
      this.map = map;
   }

   public void withMap(JsonIdMap map)
   {
      this.map = map;
      map.withUpdateMsgListener((MapUpdateListener) this);
   }
   
   public void put(String string, Object object)
   {
      this.getMap().put(string, object);
   }
   
   public Object get(String id)
   {
      return this.getMap().getObject(id);
   } 


   
   //==============================================================================
   public SeppelSpace init(JsonIdMap userModelIdMap, boolean javaFXApplication, String hostName, int portNo)
   {
      String userName = userModelIdMap.getCounter().getPrefixId();
      
      this.setName(userName+"Thread");
      
      this.withSpaceId(userName + "Space")
      .withJavaFXApplication(javaFXApplication);
      
      this.withMap(userModelIdMap);
      userModelIdMap.withCreator(SeppelSpaceCreator.createIdMap(null));
      
      this.selfProxy = new SeppelSpaceProxy()
      .withSpaceId(this.getSpaceId())
      .withHostName(hostName)
      .withPortNo(portNo);
      this.put(this.getSpaceId(), this.selfProxy);

      
      return this;
   }

 
   //==============================================================================
   private boolean isApplyingChangeMsg = false;

   public boolean isApplyingChangeMsg()
   {
      return isApplyingChangeMsg;
   }

   public void setApplyingChangeMsg(boolean isApplyingChangeMsg)
   {
      this.isApplyingChangeMsg = isApplyingChangeMsg;
   }

   
   //==============================================================================
   public long getNewHistoryIdNumber()
   {
      lastChangeId++;

      return lastChangeId;
   }

   public long getNewHistoryIdNumber(int increment)
   {
      lastChangeId += increment;

      return lastChangeId;
   }


   //==============================================================================
   private File logFile = null;
   private FileWriter logFileWriter;

   public void setLogFile(File logFile)
   {
      this.logFile = logFile;
      try
      {
         logFileWriter = new FileWriter(logFile, true);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
   
   private boolean loadingHistory = false; 
   
   private JsonIdMap changeMap = null;

   public JsonIdMap getChangeMap()
   {
      if (changeMap == null)
      {
         changeMap = ReplicationNodeCreator.createIdMap(spaceId);
      }
      return changeMap;
   }

   
   private void writeChange(ReplicationChange change)
   {
      if (loadingHistory)
      {
         return;
      }
      
      try
      {
         if (logFile == null)
         {
            boolean result = new File("./SharedSpace/").mkdirs();
            logFile = new File("./SharedSpace/" + getSpaceId() + ".json");

            result = logFile.createNewFile();
         }
         
         logFileWriter = new FileWriter(logFile, true);

         JsonObject jsonObject = getChangeMap().toJsonObject(change);
         logFileWriter.write(jsonObject.toString() + "\n");
         logFileWriter.flush();
         logFileWriter.close();
         
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

   }

   public void loadHistoryFromFile()
   {
      // remove old backup file
      File backupFile = new File("./SharedSpace/" + getSpaceId() + ".json.backup");

      if (backupFile.exists())
      {
         backupFile.delete();
      }

      // move old history file to backup
      File historyfile = new File("./SharedSpace/" + getSpaceId() + ".json");

      if (historyfile.exists())
      {
         historyfile.renameTo(backupFile);

         try
         {
            BufferedReader in = new BufferedReader(new FileReader(backupFile));

            String line = in.readLine();
            while (line != null)
            {
               ChannelMsg msg = new ChannelMsg(null, line);

               handleMessage(msg);

               line = in.readLine();
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }
   
   
   public void loadHistoryFromFile(File file)
   {
      if (file.exists())
      {
         try
         {
            this.loadingHistory = true;
            
            BufferedReader in = new BufferedReader(new FileReader(file));

            String line = in.readLine();
            while (line != null)
            {
               ChannelMsg msg = new ChannelMsg(null, line);

               handleMessage(msg);

               line = in.readLine();
            }
            
            in.close();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
         finally
         {
            this.loadingHistory = false;
         }
      }
   }
   
   public void storeMyHistoryCompressed()
   {
      String loginName = logFile.getName(); 
      loginName = loginName.split("\\.")[0];
      
      // create backup log
      File backupFile = new File(logFile.getAbsolutePath() + ".backup");
      try
      {
         Files.copy(logFile.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
      
      // clear log file
      try
      {
         new RandomAccessFile(logFile, "rw").setLength(0);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

      // loop through history
      for (ReplicationChange change : getHistory().getChanges())
      {
         // if my change
         if (change.getHistoryIdPrefix().equals(loginName))
         {
            // add to xy.jlog file   
            writeChange(change);
         }
         
      }
   }
   
   public void loadHistoryFromDir(File logDir)
   {
      // loop through logDir and load all .jlog files
      if (logDir.exists() && logDir.isDirectory())
      {
         ArrayList<ReplicationChange> changeList = new ArrayList<ReplicationChange>();
         
         for (File file : logDir.listFiles())
         {
            String fileName = file.getName();
            
            if (fileName.endsWith(".json"))
            {
               // load all changes in list
               try
               {
                  BufferedReader in = new BufferedReader(new FileReader(file));

                  String line = in.readLine();
                  while (line != null)
                  {
                     JsonObject jsonObject = new JsonObject().withValue(line);
                     
                     ReplicationChange change = (ReplicationChange) getChangeMap().decode(jsonObject);
                     change.setChangeMsg(EntityUtil.unQuote(change.getChangeMsg()));

                     changeList.add(change);

                     line = in.readLine();
                  }
                  
                  in.close();
               }
               catch (Exception e)
               {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
            }
         }
         
         // sort by history id and prefix
         Collections.sort(changeList);
         
         // apply in order
         try
         {
            this.isApplyingChangeMsg = true;
            this.loadingHistory = true;
            
            for (ReplicationChange change : changeList)
            {
               applyChangeLocally(change);
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
         finally
         {
            this.isApplyingChangeMsg = false;
            this.loadingHistory = false;
         }
      }
      
   }


   //==========================================================================================
   private boolean readMessages = false;

   public void setReadMessages(boolean readMessages)
   {
      this.readMessages = readMessages;
   }

   public boolean isReadMessages()
   {
      return readMessages;
   }

   @Override
   public boolean isReadMessages(String key, Object element, JsonObject props, String type)
   {
      return readMessages;
   }

      @Override
   public boolean readMessages(String key, Object element, Object value, JsonObject props, String type)
   {
      return false;
   }

   @Override
   public boolean skipCollision(Object masterObj, String key, Object value, JsonObject removeJson, JsonObject updateJson)
   {
      return false;
   }


   private void sendNewChange(ReplicationChange change)
   {
      // TODO Auto-generated method stub
      
   }


   public void sendAllChanges(SeppelChannel channel)
   {
      JsonObject jsonObject = new JsonObject();
      
      // go through history and send changes that are in the scope of this channel
      SeppelScopeSet scopes = channel.getSeppelSpaceProxy().getScopes();
      SeppelScope s = scopes.first();
      
      if (s == null)
      {
         // not yet initalized. Don't send anything
         return;
      }
      
      for (ReplicationChange change : this.getHistory().getChanges())
      {
         String targetObjectId = change.getTargetObjectId();
         Object targetObject = this.get(targetObjectId);
         
         if (targetObject != null && scopes.containsObservedObjects(targetObject))
         {
            // the value should also be in scope
            String changeMsg = change.getChangeMsg();
      
            jsonObject.clear();
            jsonObject.withValue(changeMsg);
            
            jsonObject = (JsonObject) jsonObject.get("upd");
            
            Object valueObject = jsonObject.get(change.getTargetProperty());
            
            if (valueObject != null && valueObject instanceof JsonObject)
            {
               jsonObject = (JsonObject) valueObject;
               String valueObjectId = (String) jsonObject.get("id");
               
               valueObject = this.get(valueObjectId);
               
               if (valueObject != null && scopes.containsObservedObjects(targetObject))
               {
                  // yes, the change is in scope send it
                  JsonIdMap cmap = getChangeMap();

                  JsonObject jsonChange = cmap.toJsonObject(change);
                  
                  String line = jsonChange.toString();

                  channel.send(line);
               }
            }
         }
      }
   } 

}
