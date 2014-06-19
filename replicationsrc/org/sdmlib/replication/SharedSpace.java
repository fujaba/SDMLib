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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.application.Platform;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.ReplicationChangeSet;
import org.sdmlib.replication.util.ReplicationChannelSet;
import org.sdmlib.replication.util.ReplicationNodeCreator;
import org.sdmlib.replication.util.ReplicationRootSet;
import org.sdmlib.replication.util.SharedSpaceSet;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.EntityUtil;
import de.uniks.networkparser.interfaces.MapUpdateListener;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;



public class SharedSpace extends Thread implements PropertyChangeInterface, PropertyChangeListener,
      MapUpdateListener
{

   public static final String JLOG = "jlog";

   public static final String CURRENT_HISTORY_ID = "currentHistoryId";

   public static final String TERMINATE = "terminate";

   private static final String RESEND_ID_HISTORY_PREFIX = "resendIdHistoryPrefix";

   private static final String RESEND_ID_HISTORY_NUMBER = "resendIdHistoryNumber";

   private static final String LOWER_ID_PREFIX = "lowerIdPrefix";

   private static final String LOWER_ID_NUMBER = "lowerIdNumber";

   private LinkedBlockingQueue<ChannelMsg> msgQueue = new LinkedBlockingQueue<ChannelMsg>();

   private boolean firstMessage = true;

   private String serverIp;

   private int serverPort;

   private boolean javaFx;

   public SharedSpace()
   {
   }

   public SharedSpace(String spaceId, String nodeId, String serverIp, int serverPort, JsonIdMap map)
   {
      this.spaceId = spaceId;
      this.nodeId = nodeId;
      this.serverIp = serverIp;
      this.serverPort = serverPort;
      this.map = map;
   }

   public ReplicationRoot plainInit()
   {
      map.withCreator(ReplicationNodeCreator.createIdMap("i").getCreators());
      
      map.withUpdateMsgListener((MapUpdateListener) this);
      
      ReplicationRoot replicationRoot = new ReplicationRoot();
      map.put(SharedSpace.REPLICATION_ROOT, replicationRoot);
      
      return replicationRoot;
   }

   public SharedSpace init(PropertyChangeListener laneListener)
   {
      map.withCreator(ReplicationNodeCreator.createIdMap("i").getCreators());
      setName("Lane" + nodeId);
      ReplicationChannel channel = createChannels().withConnect(serverIp, serverPort);
      channel.setName("ReplicationChannel" + nodeId);
      channel.start();

      ReplicationRoot replicationRoot = new ReplicationRoot();
      map.put(SharedSpace.REPLICATION_ROOT, replicationRoot);
      
      channel.sendSpaceConnectionRequest(spaceId);
      waitForCurrentHistoryId();
      
      remoteTaskBoard = new RemoteTaskBoard();
      map.put(REMOTE_TASK_BOARD_ROOT, remoteTaskBoard);
      
      if (laneListener != null)
      {
         remoteTaskBoard.getPropertyChangeSupport().addPropertyChangeListener(laneListener);
      }
      
      return this;
   }

   public void enqueueMsg(ReplicationChannel channel, String msg)
   {
      try
      {
         if (firstMessage && ! javaFXApplication)
         {
            firstMessage = false;
            JsonObject jsonObject = new JsonObject().withValue(msg);
            if (jsonObject.get(CURRENT_HISTORY_ID) != null)
            {
               long receivedId = (long) jsonObject.get(CURRENT_HISTORY_ID);
               if (receivedId > this.lastChangeId)
               {
                  this.lastChangeId = receivedId;
                  synchronized (this)
                  {
                     this.notifyAll();
                  }
               }
               return;
            }
         }

         ChannelMsg channelMsg = new ChannelMsg(channel, msg);
         
         if (javaFXApplication)
         {
            Platform.runLater(new JavaFXMsgHandler(channelMsg));
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

   public void waitForCurrentHistoryId()
   {

      while (this.lastChangeId == 0)
      {
         try
         {
            synchronized (this)
            {
               wait();
            }
         }
         catch (InterruptedException e)
         {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }

   @Override
   public void run()
   {
      while (true)
      {
         try
         {
            ChannelMsg msg = msgQueue.take();

            if (TERMINATE.equals(msg.msg))
            {
               for (ReplicationChannel c : getChannels())
               {
                  c.send(TERMINATE);
               }
               break;
            }

            handleMessage(msg);
         }
         catch (Exception e)
         {
            // just try again
         }
      }
   }

   public static abstract class HookAction
   {
      public abstract void run(ReplicationChange change);
   }
   
   private LinkedHashSet<HookAction> beforeHandleMessageActions = new LinkedHashSet<HookAction>();
   private LinkedHashSet<HookAction> afterHandleMessageActions = new LinkedHashSet<HookAction>();
   
   public void addToBeforeHandleMessageActions(HookAction r)
   {
      this.beforeHandleMessageActions.add(r);
   }
   
   public void removeFromBeforeHandleMessageActions(HookAction r)
   {
      this.beforeHandleMessageActions.remove(r);
   }
   
   
   public void addToAfterHandleMessageActions(HookAction r) {
      this.afterHandleMessageActions.add(r);
   }
   
   public void removeFromAfterHandleMessageActions(HookAction r) {
      this.afterHandleMessageActions.remove(r);
   }
   
   private ReplicationChange previousChange = null;

   public void handleMessage(ChannelMsg msg)
   {
      try
      {
         // reconstruct change
         JsonObject jsonObject = new JsonObject().withValue(msg.msg);
//         System.out.println(jsonObject.toString(2));
//         JsonObject propJson = (JsonObject) jsonObject.get(JsonIdMap.JSON_PROPS);
//         if (propJson != null) {
//            String id = (String) propJson.get("historyIdPrefix");
//            if (id == null || !id.equals(nodeId))
//            {
//               for (HookAction r : beforeHandleMessageActions)
//               {
//                  
//                  r.run(jsonObject);
//               }
//            }
//         }
//         else {
//            System.out.println(msg.msg);
//         }


         this.isApplyingChangeMsg = true;


         if (previousChange == null)
         {
            previousChange = new ReplicationChange();
         }

         // check for initial message
         if (jsonObject.get("spaceId") != null)
         {
            // initial message, send current historyId
            JsonObject jsonHistoryId = new JsonObject();
            jsonHistoryId.put(CURRENT_HISTORY_ID, this.lastChangeId);
            msg.channel.send(jsonHistoryId.toString());

            // send my changes
            previousChange.withHistoryIdNumber(0).withHistoryIdPrefix(" ");

            sendAllChangesSince(previousChange, msg.channel);

            return;
         }

         // handle resend request
         if (jsonObject.get(RESEND_ID_HISTORY_NUMBER) != null)
         {
            previousChange.setHistoryIdNumber((long) jsonObject.get(RESEND_ID_HISTORY_NUMBER));
            previousChange.setHistoryIdPrefix(jsonObject.getString(RESEND_ID_HISTORY_PREFIX));

            sendAllChangesSince(previousChange, msg.channel);

            return;
         }

         JsonIdMap cmap = getChangeMap();

         ReplicationChange change = (ReplicationChange) cmap.decode(jsonObject);
         
         change.setChangeMsg(EntityUtil.unQuote(change.getChangeMsg()));

         // is change already known?
         if (getHistory().getChanges().contains(change))
         {
            // change already known, ignore
            change.withLog("ignore change already known", this.getName());
            
            return;
         }

         if (change.getHistoryIdNumber() <= -2)
         {
            System.out.println("Handling change\n" + change.toString());
         }

         // is previous change known?
         String previousPrefix = (String) jsonObject.get(LOWER_ID_PREFIX);
//         if (false && previousPrefix != null)
//         {
//            long previousNumber = jsonObject.getLong(LOWER_ID_NUMBER);
//            previousChange.withHistoryIdNumber(previousNumber).withHistoryIdPrefix(previousPrefix);
//
//            // there is an id for the previous change, do I know that one?
//            ReplicationChange floor = this.getHistory().getChanges().floor(previousChange);
//            if (floor == null || floor.compareTo(previousChange) != 0)
//            {
//               // ups, I do not have the previous change.
//               // this should not happen.
//               // well, ask for the previous change and wait for it
//               // well what is the latest change I have already that is before
//               // the one I have no longer
//               ReplicationChange previousfloor = getHistory().getChanges().floor(previousChange);
//               if (floor != null)
//               {
//                  previousNumber = previousfloor.getHistoryIdNumber();
//                  previousPrefix = previousfloor.getHistoryIdPrefix();
//               }
//               else
//               {
//                  // ups, I do not have the previous Id nor any one before that,
//                  // start from the beginning
//                  previousNumber = 0;
//                  previousPrefix = " ";
//               }
//               JsonObject jsonRequest = new JsonObject();
//               jsonRequest.put(RESEND_ID_HISTORY_NUMBER, previousNumber);
//               jsonRequest.put(RESEND_ID_HISTORY_PREFIX, previousPrefix);
//
//               msg.channel.send(jsonRequest.toString());
//
//               // wait for it
//               return;
//            }
//         }
//         else
//         {
//            // sender does not have an earlier change, use dummy for further
//            // processing
//            previousChange.withHistoryIdNumber(0).withHistoryIdPrefix(" ");
//         }

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

               // new change will be last, just apply it.
               ReplicationChange higher = oldChanges.higher(change);

               if (higher == null)
               {
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
         
//         JsonObject jsonObject = new JsonObject().withValue(msg.msg);
//         System.out.println(jsonObject.toString(2));
//         JsonObject propJson = (JsonObject) jsonObject.get(JsonIdMap.JSON_PROPS);
//         if (propJson != null) {
//            String id = (String) propJson.get("historyIdPrefix");
//            if (id == null || !id.equals(nodeId))
//            {
//               for (HookAction r : afterHandleMessageActions)
//               {
//                  r.run(jsonObject);
//               }
//            }
//         }
      }

   }

   public void sendAllChangesSince(ReplicationChange lower, ReplicationChannel channel)
   {
      if (lower == null)
      {
         lower = new ReplicationChange().withHistoryIdNumber(0).withHistoryIdPrefix(" ");
      }

      for (ReplicationChange change : getHistory().getChanges().tailSet(lower))
      {
         if (logLevel >= 1)
         {
            change.withLog("resending", getName());
         }

         sendChangeTo(channel, change);
      }
   }

   private void sendChangeTo(ReplicationChannel channel, ReplicationChange change)
   {
      if (channel == null)
      {
         return;
      }

      JsonIdMap cmap = getChangeMap();

      JsonObject jsonObject = cmap.toJsonObject(change);

      // add id of previous change to enable completeness check by receiver
      ReplicationChange lower = getHistory().getChanges().lower(change);

      if (lower != null)
      {
         jsonObject.put(LOWER_ID_NUMBER, lower.getHistoryIdNumber());
         jsonObject.put(LOWER_ID_PREFIX, lower.getHistoryIdPrefix());
      }

      String line = jsonObject.toString();

      channel.send(line);

   }

   private void applyChange(ReplicationChange change, ReplicationChannel sender)
   {
      applyChangeLocally(change);

      sendNewChange(change);

      // if the change is not the last, the sender might not have got some
      // changes
      for (ReplicationChange newerChange : getHistory().getChanges().tailSet(change, false))
      {
         sendChangeTo(sender, newerChange);
      }
   }

   
   public void applyNoConflictChange(ReplicationChange change)
   {
      String key = change.getTargetObjectId() + "|" + change.getTargetProperty();

      Object oldChange = this.getHistory().getChangeMap().get(key);

      if (change.getIsToManyProperty())
      {
         // no conflict, apply
         applyChangeLocally(change);
      }
      else
      {
         if (oldChange == null || ((ReplicationChange) oldChange).compareTo(change) < 0)
         {
            applyChangeLocally(change);
         }
      }
   }
   
   public void applyChangeLocally(ReplicationChange change)
   {
      // no conflict, apply change
      JsonObject jsonUpdate = new JsonObject().withValue(change.getChangeMsg());
      
      for (HookAction r : beforeHandleMessageActions)
      {
         r.run(change);
      }
   
      try
      {
         this.setReadMessages(true);
         map.executeUpdateMsg(jsonUpdate);
      }
      finally
      {
         this.setReadMessages(false);
      }

      getHistory().addChange(change);

      writeChange(change);

      this.lastChangeId = Math.max(lastChangeId, change.getHistoryIdNumber());

      change.withLog("change applied", this.getName());
      
      for (HookAction r : afterHandleMessageActions)
      {
         r.run(change);
      }
   }

   private File logFile = null;

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
            logFile = new File("./SharedSpace/" + getSpaceId() + "_" + getNodeId() + ".json");

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
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   public void loadHistoryFromFile()
   {
      // remove old backup file
      File backupFile = new File("./SharedSpace/" + getSpaceId() + "_" + getNodeId()
         + ".json.backup");

      if (backupFile.exists())
      {
         backupFile.delete();
      }

      // move old history file to backup
      File historyfile = new File("./SharedSpace/" + getSpaceId() + "_" + getNodeId() + ".json");

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
            
            if (fileName.endsWith(JLOG))
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


   private boolean isApplyingChangeMsg = false;

   public boolean isApplyingChangeMsg()
   {
      return isApplyingChangeMsg;
   }

   public void setApplyingChangeMsg(boolean isApplyingChangeMsg)
   {
      this.isApplyingChangeMsg = isApplyingChangeMsg;
   }

   private int logLevel = 0;
   
   static public int msgNo = 0;

   @Override
   public boolean sendUpdateMsg(Object target, String property, Object oldObj, Object newObject,
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
      .withChangeMsg(jsonObject.toString());

      Object object = jsonObject.get(JsonIdMap.UPDATE);

      if (object != null)
      {
         JsonObject jsonUpdate = (JsonObject) object;
         for (Iterator<String> iter = jsonUpdate.keys(); iter.hasNext();)
         {
            String prop = iter.next();
            change.withTargetProperty(prop);
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

      writeChange(change);

      sendNewChange(change);

      return true;
   }

   private JsonIdMap changeMap = null;

   private void sendNewChange(ReplicationChange change)
   {
      JsonIdMap cmap = getChangeMap();

      JsonObject jsonObject = cmap.toJsonObject(change);

      // add id of previous change to enable completeness check by receiver
      ReplicationChange lower = getHistory().getChanges().lower(change);

      if (lower != null)
      {
         jsonObject.put(LOWER_ID_NUMBER, lower.getHistoryIdNumber());
         jsonObject.put(LOWER_ID_PREFIX, lower.getHistoryIdPrefix());
      }

      String line = jsonObject.toString();

      for (ReplicationChannel channel : this.getChannels())
      {
         channel.send(line);
      }
   }

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

   // ==========================================================================
   public JsonIdMap getChangeMap()
   {
      if (changeMap == null)
      {
         changeMap = ReplicationNodeCreator.createIdMap(nodeId);
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

      if (PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attrName))
      {
         return getJavaFXApplication();
      }

      return null;
   }

   // ==========================================================================

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

      if (PROPERTY_JAVAFXAPPLICATION.equalsIgnoreCase(attrName))
      {
         setJavaFXApplication((Boolean) value);
         return true;
      }

      return false;
   }

   // ==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   // ==========================================================================

   public void removeYou()
   {
      setNode(null);
      removeAllFromChannels();
      withoutChannels(this.getChannels().toArray(new ReplicationChannel[this.getChannels().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================

   public static final String PROPERTY_SPACEID = "spaceId";

   private String spaceId;

   public String getSpaceId()
   {
      return this.spaceId;
   }

   public void setSpaceId(String value)
   {
      if (!StrUtil.stringEquals(this.spaceId, value))
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

         changed = this.channels.add(value);

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
         changed = this.channels.remove(value);

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
      LinkedHashSet<ReplicationChannel> tmpSet = new LinkedHashSet<ReplicationChannel>(
            this.getChannels());

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
      map.withUpdateMsgListener((MapUpdateListener) this);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      // System.out.println(evt);
   }

   @Override
   public boolean readMessages(String key, Object element, Object value, JsonObject props,
         String type)
   {
      return false;
   }

   @Override
   public boolean skipCollision(Object masterObj, String key, Object value, JsonObject removeJson,
         JsonObject updateJson)
   {
      return false;
   }

   // ==========================================================================

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

   // ==========================================================================

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

   // ==========================================================================

   public static final String PROPERTY_NODEID = "nodeId";

   private String nodeId;

   private FileWriter logFileWriter;

   public String getNodeId()
   {
      return this.nodeId;
   }

   public void setNodeId(String value)
   {
      if (!StrUtil.stringEquals(this.nodeId, value))
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

   public <T> T glueObjectsAtId(String id, T newObj)
   {
      // id already in use?
      Object oldObj = map.getObject(id);

      if (oldObj == null)
      {
         map.put(id, newObj);
         return newObj;
      }
      else
      {
         String newKey = map.getKey(newObj);

         // transfer attributes from newObj to oldObj
         // use the newer change

         SendableEntityCreator creator = map.getCreatorClass(oldObj);
         for (String prop : creator.getProperties())
         {
            Object value = creator.getValue(newObj, prop);

            if (value != null && value instanceof Collection)
            {
               // add all elements to oldObj
               for (Object elem : (Collection) value)
               {
                  creator.setValue(oldObj, prop, elem, "");
               }
            }
            else if (value != null)
            {
               creator.setValue(oldObj, prop, value, "");
            }
         }

         return (T) oldObj;
      }
   }

   private boolean readMessages = false;

   public static final String REMOTE_TASK_BOARD_ROOT = "taskFlowBoardRoot";

   public static final String REPLICATION_ROOT = "replicationRoot";

   private RemoteTaskBoard remoteTaskBoard;
   
   public RemoteTaskBoard getRemoteTaskBoard()
   {
      return remoteTaskBoard;
   }

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
      // TODO Auto-generated method stub
      return readMessages;
   }

   public SharedSpace withChannels(ReplicationChannel... value)
   {
      for (ReplicationChannel item : value)
      {
         addToChannels(item);
      }
      return this;
   }

   public SharedSpace withoutChannels(ReplicationChannel... value)
   {
      for (ReplicationChannel item : value)
      {
         removeFromChannels(item);
      }
      return this;
   }

   public Object getSharedObject(String objectName)
   {
      ReplicationRoot replicationRoot = (ReplicationRoot) map.getObject(REPLICATION_ROOT);
      if(replicationRoot != null) {
         ReplicationRootSet kids = replicationRoot.getKids();
         for (ReplicationRoot kid : kids)
         {
            if(StrUtil.stringEquals(kid.getName(), objectName)) {
               return kid.getApplicationObject();
            }
         }
      }
      return null;
   }

   public boolean isLoadingHistory()
   {
      return loadingHistory;
   }

   public void setLoadingHistory(boolean loadingHistory)
   {
      this.loadingHistory = loadingHistory;
   }

   //==========================================================================
   
   public static final String PROPERTY_JAVAFXAPPLICATION = "javaFXApplication";
   
   private boolean javaFXApplication;

   public boolean getJavaFXApplication()
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
   
   public SharedSpace withJavaFXApplication(boolean value)
   {
      setJavaFXApplication(value);
      return this;
   } 
   
   private class JavaFXMsgHandler implements Runnable
   {
      private ChannelMsg channelMsg;

      public JavaFXMsgHandler(ChannelMsg channelMsg)
      {
         this.channelMsg = channelMsg;
         // TODO Auto-generated constructor stub
      }

      @Override
      public void run()
      {
         handleMessage(channelMsg);
      }
   }

   public class ChannelMsg
   {
      public ChannelMsg(ReplicationChannel channel, String msg)
      {
         this.channel = channel;
         this.msg = msg;
      }
   
      public ReplicationChannel channel;

      public String msg;
   }
   //==========================================================================
   
   public static final String PROPERTY_SOCKET = "socket";
   
   private Socket socket;

   public Socket getSocket()
   {
      return this.socket;
   }
   
   public void setSocket(Socket value)
   {
      if (this.socket != value)
      {
         Socket oldValue = this.socket;
         this.socket = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_SOCKET, oldValue, value);
      }
   }
   
   public SharedSpace withSocket(Socket value)
   {
      setSocket(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TARGETNODEID = "targetNodeId";
   
   private String targetNodeId;

   public String getTargetNodeId()
   {
      return this.targetNodeId;
   }
   
   public void setTargetNodeId(String value)
   {
      if ( ! StrUtil.stringEquals(this.targetNodeId, value))
      {
         String oldValue = this.targetNodeId;
         this.targetNodeId = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TARGETNODEID, oldValue, value);
      }
   }
   
   public SharedSpace withTargetNodeId(String value)
   {
      setTargetNodeId(value);
      return this;
   } 
}

