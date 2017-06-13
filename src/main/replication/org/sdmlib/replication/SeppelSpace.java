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

import java.beans.PropertyChangeEvent;
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
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import org.sdmlib.StrUtil;
import org.sdmlib.replication.util.ObjectSet;
import org.sdmlib.replication.util.SeppelScopeSet;
import org.sdmlib.replication.util.SeppelSpaceCreator;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.SimpleEvent;
import de.uniks.networkparser.interfaces.ObjectCondition;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.json.JsonTokener;
import javafx.application.Platform;
   /**
    * 
    * @see <a href='../../../../../../src/main/replication/org/sdmlib/replication/ReplicationModel.java'>ReplicationModel.java</a>
* @see <a href='../../../../../../src/test/java/org/sdmlib/test/replication/ReplicationModel.java'>ReplicationModel.java</a>
 */
   public class SeppelSpace extends Thread implements PropertyChangeInterface, ObjectCondition, SendableEntity
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
            e.printStackTrace();
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
                  try
                  {
                     handleMessage(channelMsg);
                  }
                  catch (Exception e)
                  {
                     e.printStackTrace();
                  }
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
               
               // find the space proxy for that user
               SeppelSpaceProxy theSpace = this.getSelfProxy().getPartners().hasLoginName(login).hasPassword(pwd).first();

               theSpace.withChannel(msg.channel);
               
               // that worked, set channel to valid
               msg.channel.setLoginValidated(true);
            }
            finally 
            {
               try
               {
                  msg.channel.getMsgQueue().put("check");
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

         ChangeEvent change = new ChangeEvent(jsonObject);
         
         historyPos = history.addChange(change);
         
         if (historyPos < 0)
         {
            // change already known, ignore
            return;
         }

         // try to apply change
         applyChange(change, msg.channel);
         
      }
      catch (Exception e)
      {
         e.printStackTrace();
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
   
   
   private void applyChange(ChangeEvent change, SeppelChannel sender)
   {
      applyChangeLocally(change);

      sendNewChange(change);
   }

   public void applyChangeLocally(ChangeEvent change)
   {
      // get source object
      Object object = map.getObject(change.getObjectId());
      
      String objectType = change.getObjectType();
      SendableEntityCreator creator = map.getCreator(objectType, false, null);
      
      if (object == null)
      {
         // new object, create it
         object = creator.getSendableInstance(false);
         this.put(change.getObjectId(), object);
      }
      
      if (ChangeEvent.PLAIN.equals(change.getPropertyKind()))
      {
         // simple attribute just do assignment
         creator.setValue(object, change.getProperty(), change.getNewValue(), null);
      }
      else if (ChangeEvent.TO_ONE.equals(change.getPropertyKind()))
      {
         String newValueId = change.getNewValue();
         
         if (newValueId == null)
         {
            // set pointer to null
            creator.setValue(object, change.getProperty(), null, null);
         }
         else
         {
            // provide target object
            Object targetObject = map.getObject(newValueId);
            
            if (targetObject == null)
            {
               // not yet known target, build it. 
               SendableEntityCreator targetCreator = map.getCreator(change.getValueType(), false, null);
               targetObject = targetCreator.getSendableInstance(false);
               this.put(newValueId, targetObject);
            }
            
            // assign value
            creator.setValue(object, change.getProperty(), targetObject, null);
         }
      }
      else // toMany
      {
         String targetId = change.getNewValue();
         
         if (targetId == null)
         {
            // remove the object from the to_many attribute
            targetId = change.getOldValue();
            
            Object targetObject = map.getObject(targetId);
            
            if (targetObject != null)
            {
               creator.setValue(object, change.getProperty(), targetObject, SendableEntityCreator.REMOVE);
            }
         }
         else
         {
            // insertion
            Object targetObject = map.getObject(targetId);
            
            if (targetObject == null)
            {
               // create unknown target
               SendableEntityCreator targetCreator = map.getCreator(change.getValueType(), false, null);
               targetObject = targetCreator.getSendableInstance(false);
               this.put(targetId, targetObject);
            }
            
            // assign value
            creator.setValue(object, change.getProperty(), targetObject, null);
            
            // try to adjust position
            tryToAdjustPosition(object, change.getProperty(), targetObject, creator);
         }
      }
      
      writeChange(change);
   }
   
   private void tryToAdjustPosition(Object object, String property, Object targetObject, SendableEntityCreator creator)
   {
      Object value = creator.getValue(object, property);
         
      if (value != null && value instanceof List)
      {
         List<Object> valueList = (List<Object>) value;
         int indexOf = valueList.indexOf(targetObject);
            
         if (indexOf != historyPos)
         {
            valueList.remove(indexOf);
            valueList.add(historyPos, targetObject);
         }
         
      }
   }

   public static class RestrictToFilter implements ObjectCondition {
      private ObjectSet explicitElems;

      public RestrictToFilter(ObjectSet explicitElems2)
      {
         this.explicitElems = explicitElems2;

      }

      @Override
      public boolean update(Object event) {
    	  SimpleEvent evt=(SimpleEvent) event;
         if (evt.getNewValue() != null)
         {
            if (evt.getDepth() >= 3)
            {
               return false;
            }
            else if ("Integer Float Double Long Boolean String"
               .indexOf(evt.getNewValue().getClass().getSimpleName()) >= 0)
            {
               return true;
            }
         }
         
         return explicitElems.contains(evt.getNewValue());
      }
   }


   //==============================================================================
   @Override
   public boolean update(Object event) {
      if (isApplyingChangeMsg)
      {
         // ignore
         return true;
      }
      SimpleEvent simpleEvent = (SimpleEvent) event;
      JsonObject jsonObject = (JsonObject) simpleEvent.getEntity();

      // {"id":"testerProxy",
      //  "class":"org.sdmlib.replication.SeppelSpaceProxy",
      //  "upd":{"scopes":{"class":"org.sdmlib.replication.SeppelScope",
      //                   "id":"tester.S1",
      //                   "prop":{"scopeName":"commands",
      //                           "spaces":[{"id":"testerProxy"}]}}}}

      String opCode = SendableEntityCreator.UPDATE;
      
      Object attributes = jsonObject.get(SendableEntityCreator.UPDATE);
      
      if (attributes == null)
      {
         attributes = jsonObject.get(SendableEntityCreator.REMOVE);
         opCode = SendableEntityCreator.REMOVE;
         
         if (attributes == null)
         {
            attributes = jsonObject.get("prop");
            opCode = SendableEntityCreator.UPDATE;
         }
      }

      JsonObject valueJsonObject = null;
      JsonObject attributesJson = null;
      String prop = null;
      
      if (attributes != null)
      {
         attributesJson = (JsonObject) attributes;
         
         Iterator<String> iter = attributesJson.keyIterator();
         while ( iter.hasNext())
         {
            prop = iter.next();

            ChangeEvent change = new ChangeEvent()
            .withSessionId(spaceId)
            .withChangeNo("" + getNewHistoryIdNumber())
            .withObjectId(jsonObject.getString(IdMap.ID))
            .withObjectType(jsonObject.getString(IdMap.CLASS))
            .withProperty(prop);
            
            Object attrValue = attributesJson.get(prop);
            
            if (attrValue instanceof JsonObject)
            {
               JsonArray valueJsonArray = new JsonArray();
               valueJsonArray.add(attrValue);
               attrValue = valueJsonArray;
            }
            
            if (attrValue instanceof JsonArray)
            {
               JsonArray valueJsonArray = (JsonArray) attrValue;
               
               for (Object arrayElem : valueJsonArray)
               {
                  valueJsonObject = (JsonObject) arrayElem;

                  String valueObjectId = (String) valueJsonObject.get(IdMap.ID);
               
                  String valueObjectType = (String) valueJsonObject.get(IdMap.CLASS);

                  Object valueObject = map.getObject(valueObjectId);

                  if (valueObjectType == null)
                  {
                     // get object and ask it
                     valueObjectType = valueObject.getClass().getName();
                  }

                  change.withValueType(valueObjectType);

                  // toOne or toMany
                  change.withPropertyKind(ChangeEvent.TO_ONE);

                  Object targetObject = map.getObject(change.getObjectId());

                  SendableEntityCreator creator = map.getCreatorClass(targetObject);

                  Object value = creator.getValue(targetObject, change.getProperty());

                  if (value != null && value instanceof Collection)
                  {
                     change.setPropertyKind(ChangeEvent.TO_MANY);
                  }

                  // newValue or oldValue?
                  if (opCode.equals(SendableEntityCreator.REMOVE))
                  {
                     change.withOldValue(valueObjectId);
                  }
                  else
                  {
                     change.withNewValue(valueObjectId);
                  }

                  // store it
                  getHistory().addChange(change);
                  writeChange(change);
                  sendNewChange(change);

                  // does the value have properties?
                  if (valueJsonObject.get("prop") != null)
                  {
                     // call recursive
//                     this.update(typ, valueJsonObject, valueObject, prop, null, null);
                	  simpleEvent.with(valueJsonObject);
                	  this.update(simpleEvent);
                  }
               }
            }
            else
            {
            	PropertyChangeEvent evt = (PropertyChangeEvent) event;
               String oldValueString = "" + evt.getOldValue();
               if (evt.getOldValue() == null)
               {
                  oldValueString = null;
               }
               
               // plain attribute
               change.withPropertyKind(ChangeEvent.PLAIN)
               .withNewValue("" + attrValue)
               .withOldValue(oldValueString);
               
               getHistory().addChange(change);
               writeChange(change);
               sendNewChange(change);
            }
         }
      }


//      
//      
//      
//      if (target instanceof SeppelScope 
//            && SeppelScope.PROPERTY_OBSERVEDOBJECTS.equals(change.getProperty())
//            && valueJsonObject != null)
//      {
//         // some new object has been added to a scope, 
//         // provide all details of that object as it will now be send to new partner spaces
//         if (valueJsonObject.get(IdMap.ID) != null && valueJsonObject.size() == 1)
//         {
//            // it contains only the object id, no properties of the object, just add those
//            String valueObjectId = valueJsonObject.getString(IdMap.ID);
//            Object valueObject = map.getObject(valueObjectId);
//            ObjectSet explicitElems = ((SeppelScope) target).getObservedObjects();
//            JsonObject newValueJsonObject = map.toJsonObject(valueObject, 
//               new Filter().withPropertyRegard(new RestrictToFilter(explicitElems)));
//            
//            jsonUpdate.put(prop, newValueJsonObject);
//            // change.withChangeMsg(jsonObject.toString());
//         }
//         
//      }
//      else if (target instanceof SeppelScope 
//            && SeppelScope.PROPERTY_SPACES.equals(change.getProperty())
//            && valueJsonObject != null)
//      {
//         // the scope is attached to a new space proxy. Add scope name to change
//         // as the scope object will now be created in the corresponding space
//         // and all the other parts
//         JsonObject newValueJsonObject = map.toJsonObject(target, 
//            new Filter().withConvertable(new Deep().withDeep(0)));
//         JsonArray spaceArray = new JsonArray();
//         spaceArray.add(valueJsonObject);
//         JsonObject selfProxyId = new JsonObject();
//         selfProxyId.put(IdMap.ID, map.getKey(selfProxy));
//         spaceArray.add(selfProxyId);
//         jsonUpdate.put(SeppelScope.PROPERTY_SCOPENAME, ((SeppelScope) target).getScopeName());
//         jsonUpdate.put(SeppelScope.PROPERTY_SPACES, spaceArray);
//         jsonObject.put(JsonIdMap.JSON_PROPS, jsonUpdate);
//         jsonObject.remove(JsonIdMap.UPDATE);
//         // change.withChangeMsg(jsonObject.toString());
//      }
//         
//
//      getHistory().addChange(change);
//
//      writeChange(change);
//
//      sendNewChange(change);

      return true;
   }

   
   //==========================================================================
   
   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   
   @Override
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
   
   private ChangeEventList history;

   public ChangeEventList getHistory()
   {
      if (history == null)
      {
         history = new ChangeEventList();
      }

      return this.history;
   }
   
   public void setHistory(ChangeEventList value)
   {
      if (this.history != value)
      {
         ChangeEventList oldValue = this.history;
         this.history = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_HISTORY, oldValue, value);
      }
   }
   
   public SeppelSpace withHistory(ChangeEventList value)
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
   private IdMap map;

   public IdMap getMap()
   {
      return map;
   }

   public void setMap(IdMap map)
   {
      this.map = map;
   }

   public void withMap(IdMap map)
   {
      this.map = map;
      map.with((ObjectCondition)this);
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
   public SeppelSpace init(IdMap userModelIdMap, boolean javaFXApplication, String hostName, int portNo)
   {
      String userName = userModelIdMap.getSession();
      
      this.setName(userName+"Thread");
      
      this.withSpaceId(userName + "Space")
      .withJavaFXApplication(javaFXApplication);
      
      this.withMap(userModelIdMap);
      userModelIdMap.with(SeppelSpaceCreator.createIdMap(null));
      
      this.selfProxy = new SeppelSpaceProxy()
      .withSpaceId(this.getSpaceId())
      .withLoginName(userName)
      .withHostName(hostName)
      .withPortNo(portNo);
      
      this.put(this.selfProxy.getLoginName()+"Proxy", this.selfProxy);

      
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
      long result = System.currentTimeMillis();
      
      if (result <= lastChangeId)
      {
         result = lastChangeId + 1;
      }
      
      lastChangeId = result;

      return result;
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
   
   //   private JsonIdMap changeMap = null;
   //
   //   public JsonIdMap getChangeMap()
   //   {
   //      if (changeMap == null)
   //      {
   //         changeMap = ReplicationNodeCreator.createIdMap(spaceId);
   //      }
   //      return changeMap;
   //   }

   
   private void writeChange(ChangeEvent change)
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

         JsonObject jsonObject = change.toJson();
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
      for (ChangeEvent change : getHistory().getChanges())
      {
         // if my change
         if (change.getSessionId().equals(loginName))
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
         ArrayList<ChangeEvent> changeList = new ArrayList<ChangeEvent>();
         
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
                     
                     ChangeEvent change = new ChangeEvent(jsonObject);

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
            
            for (ChangeEvent change : changeList)
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

   private void sendNewChange(ChangeEvent change)
   {
      // go through all channels
      for(SeppelChannel channel : this.getSelfProxy().getPartners().getChannel())
      {
         SeppelScopeSet scopes = channel.getSeppelSpaceProxy().getScopes();
         
         sendChangeToChannel(channel, scopes, change);
      }
   }


   public void sendAllChanges(SeppelChannel channel)
   {
      JsonObject jsonObject;
      
      if (channel == null || channel.getSeppelSpaceProxy() == null) return; //<==========

      // go through history and send changes that are in the scope of this channel
      SeppelScopeSet scopes = channel.getSeppelSpaceProxy().getScopes();
      SeppelScope s = scopes.first();
      
      if (s == null)
      {
         // not yet initalized. Don't send anything
         return;
      }
      
      for (ChangeEvent change : this.getHistory().getChanges())
      {
         sendChangeToChannel(channel, scopes, change);
      }
   }


   private void sendChangeToChannel(SeppelChannel channel, SeppelScopeSet scopes, ChangeEvent change)
   {
      JsonObject jsonObject;
      String targetObjectId = change.getObjectId();
      Object targetObject = this.get(targetObjectId);
      
      if (targetObject != null 
            && (scopes.containsObservedObjects(targetObject)
                  || channel.getSeppelSpaceProxy() == targetObject
                  || selfProxy == targetObject
                  || scopes.contains(targetObject)))
      {
         // the value should also be in scope
         String changeMsg = change.toJson().toString();
    
         jsonObject = new JsonObject();
         jsonObject.withValue(changeMsg);
         
         jsonObject = (JsonObject) jsonObject.get("upd");
         
         if (jsonObject == null)
         {
            jsonObject = new JsonObject();
            jsonObject.withValue(changeMsg);
            jsonObject = (JsonObject) jsonObject.get(JsonTokener.PROPS);
         }
         
         Object valueObject = null; 
         
         if (jsonObject != null) 
         {
            valueObject = jsonObject.get(change.getProperty());
         }
         
         if (valueObject != null && valueObject instanceof JsonObject)
         {
            JsonObject valueJsonObject = (JsonObject) valueObject;
            String valueObjectId = (String) valueJsonObject.get("id");
            
            valueObject = this.get(valueObjectId);
            
            if (valueObject != null 
                  && (scopes.containsObservedObjects(valueObject)
                        || scopes.contains(valueObject)
                        || channel.getSeppelSpaceProxy() == valueObject)) 
            {
               // yes, the change is in scope send it
               JsonObject jsonChange = change.toJson();
               
               String line = jsonChange.toString();

               channel.send(line);
            }
         }
         else 
         {
            // seems to be a plain value, send it
            JsonObject jsonChange = change.toJson();
                        
            String line = jsonChange.toString();

            channel.send(line);
         }
      }
   }


   public SeppelSpaceProxy connectTo(String serverName, String hostName, int portNo, String loginName, String pwd, SeppelScope commonScope)
   {
      selfProxy.withLoginName(loginName).withPassword(pwd);
      
      SeppelSpaceProxy serverProxy = selfProxy.getPartners().hasLoginName(serverName).first();
      
      if (serverProxy == null)
      {
         serverProxy = new SeppelSpaceProxy();
         this.put(serverName+"Proxy", serverProxy);
         serverProxy.withLoginName(serverName);
         selfProxy.withPartners(serverProxy);
      }
      
      serverProxy.withHostName(hostName).withPortNo(portNo);
      
      serverProxy.withScopes(commonScope);
      
      try {
         SeppelChannel channel = serverProxy.getOrCreateChannel();
         channel.setSeppelSpace(this);
         channel.start();
         channel.login();
         this.sendAllChanges(channel);
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      
      return serverProxy;
   }

   SeppelTaskHandler taskHandler = null;

   private int historyPos;
   
   public SeppelSpace withTaskHandler(SeppelTaskHandler handler)
   {
      this.taskHandler = handler;
      
      handler.withSeppelSpace(this);
      
      // subscribe at selfProxy
      selfProxy.getPropertyChangeSupport().addPropertyChangeListener(SeppelSpaceProxy.PROPERTY_TASKS, handler);
      
      return this;
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
