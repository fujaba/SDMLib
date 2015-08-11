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

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.FileTime;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import javafx.application.Platform;

import org.sdmlib.replication.ChangeEvent;
import org.sdmlib.replication.ChangeEventList;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.interfaces.BaseItem;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.interfaces.UpdateListener;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;
import de.uniks.networkparser.list.AbstractList;


public  class ModelSpace implements PropertyChangeInterface, UpdateListener
{
   public enum ApplicationType {StandAlone, JavaFX};
   //==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);
   private JsonIdMap idMap;
   private String userName;
   private File modelDir;
   private WatchService watcher;
   private LinkedHashMap<String, BufferedReader> fileReaders = new LinkedHashMap<String, BufferedReader>();
   private boolean isApplyingChangeMsg;

   //==========================================================================

   public static final String PROPERTY_HISTORY = "history";

   private ChangeEventList history = new ChangeEventList();
   private int historyPos;
   private File logFile;
   private FileWriter logFileWriter;
   private ApplicationType appType;
   private long lastChangeId = 0;
   private Path logPath;

   public LinkedBlockingQueue<BufferedReader> changeQueue = new LinkedBlockingQueue<BufferedReader>();
   
   public ChangeEventList getHistory()
   {
      return this.history;
   }

   public ModelSpace(JsonIdMap idMap, String userName)
   {
      this (idMap, userName, ApplicationType.StandAlone);
   }
   
   public ModelSpace(JsonIdMap idMap, String userName, ApplicationType appType)
   {
      this.idMap = idMap;
      this.appType = appType;   

      this.userName = userName;

      idMap.withUpdateListenerSend(this);
   }

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


   private class DirChangeListener extends Thread
   {
      @Override
      public void run()
      {
         this.setName("DirChangeListener");
         while (true)
         {
            try
            {
               WatchKey watchKey = watcher.take();
               
               System.out.println("got dir change " + watchKey);

               for (WatchEvent<?> event : watchKey.pollEvents())
               {
                  WatchEvent.Kind<?> kind = event.kind();

                  if (kind == OVERFLOW) 
                  {
                     continue;
                  }
                  else if (kind == ENTRY_CREATE)
                  {
                     // if its a new json file, read it
                     WatchEvent<Path> ev = (WatchEvent<Path>)event;
                     
                     Path filepath = ev.context();
                     
                     String filename = modelDir + "/" + filepath.toString();
                     
                     if (filename.endsWith(".json") && ! filename.endsWith(userName + ".json"))
                     {
                        File file = new File(filename);
                        
                        FileReader fileReader = new FileReader(file);
                        
                        BufferedReader buf = new BufferedReader(fileReader);

                        fileReaders.put(file.getCanonicalPath(), buf);

                        readChangesTask(buf);
                     }
                  }
                  else if (kind == ENTRY_MODIFY)
                  {
                     // do I have a buf for this one, then read
                     WatchEvent<Path> ev = (WatchEvent<Path>)event;
                     
                     Path filepath = ev.context();
                     
                     String filename = modelDir + "/" + filepath.toString();
                     
                     File file = new File(filename);
                     
                     String name = file.getCanonicalPath();
                     
                     BufferedReader buf = fileReaders.get(name);
                     
                     if (buf != null)
                     {
                        readChangesTask(buf);
                     }
                  }
                  else if (kind == ENTRY_DELETE)
                  {
                     // do I have a buf for this one, then read
                     WatchEvent<Path> ev = (WatchEvent<Path>)event;
                     
                     Path filename = ev.context();
                     
                     File file = filename.toFile();
                     
                     String name = file.getCanonicalPath();
                     
                     fileReaders.remove(name);
                  }
               }
               
               boolean reset = watchKey.reset();
               
               System.out.println("reset: " + reset);
            }
            catch (Exception e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }
   }

   @SuppressWarnings("resource")
   public ModelSpace open(String path)
   {
      // get or create model space directory
      modelDir = new File(path);

      if (! modelDir.exists())
      {
         modelDir.mkdirs();
      }

      if (! modelDir.isDirectory())
      {
         throw new RuntimeException("path " + path + " does not refer to a directory.");
      }

      // watch directory for new Files
      try
      {
         watcher = FileSystems.getDefault().newWatchService();

         Path dirPath = Paths.get(path);
         dirPath.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

         new DirChangeListener().start();
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      // load all json files, subscribe readers for other user files
      for (File f : modelDir.listFiles())
      {
         if (f.getName().endsWith(".json"))
         {
            try
            {
               FileReader fileReader = new FileReader(f);
               BufferedReader buf = new BufferedReader(fileReader);

               if (! f.getName().equals(userName + ".json"))
               {
                  fileReaders.put(f.getCanonicalPath(), buf);
               }

               readChanges(buf);
            }
            catch (IOException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }
      }

      return this;
   }

   public void readChangesTask(final BufferedReader buf)
   {
      if (appType == ApplicationType.JavaFX)
      {
         Platform.runLater(new Runnable()
         {
            @Override
            public void run()
            {
               readChanges(buf);
            }
         });
      }
      else
      {
         try
         {
            changeQueue.put(buf);
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
   }

   public void readChanges(BufferedReader buf)
   {
      String line;
      try
      {
         line = buf.readLine();

         while (line != null)
         {
            // process change
            System.out.println(userName + " reads: " + line);
            handleChange(line);
            line = buf.readLine();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   private void handleChange(String line)
   {
      // line is a json string describing a change
      JsonObject jsonObject = new JsonObject().withValue(line);

      this.isApplyingChangeMsg = true;
      try 
      {
         ChangeEvent change = new ChangeEvent(jsonObject);
         historyPos = history.addChange(change);

         if (historyPos < 0)
         {
            // change already known, ignore
            return;
         }

         // try to apply change
         applyChange(change);
      }
      finally
      {
         this.isApplyingChangeMsg = false;
      }
   }

   private void applyChange(ChangeEvent change)
   {
      Object object = idMap.getObject(change.getObjectId());

      String objectType = change.getObjectType();

      SendableEntityCreator creator = idMap.getCreator(objectType, false);

      if (object == null)
      {
         // new object, create it
         object = creator.getSendableInstance(false);
         idMap.put(change.getObjectId(), object);
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
            Object targetObject = idMap.getObject(newValueId);

            if (targetObject == null)
            {
               // not yet known target, build it. 
               SendableEntityCreator targetCreator = idMap.getCreator(change.getValueType(), false);
               targetObject = targetCreator.getSendableInstance(false);
               idMap.put(newValueId, targetObject);
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

            Object targetObject = idMap.getObject(targetId);

            if (targetObject != null)
            {
               creator.setValue(object, change.getProperty(), targetObject, JsonIdMap.REMOVE);
            }
         }
         else
         {
            // insertion
            Object targetObject = idMap.getObject(targetId);

            if (targetObject == null)
            {
               // create unknown target
               SendableEntityCreator targetCreator = idMap.getCreator(change.getValueType(), false);
               targetObject = targetCreator.getSendableInstance(false);
               idMap.put(targetId, targetObject);
            }

            // assign value
            creator.setValue(object, change.getProperty(), targetObject, null);

            // try to adjust position
            tryToAdjustPosition(object, change.getProperty(), targetObject, creator);
         }
      }

      writeChange(change);

   }


   private void writeChange(ChangeEvent change)
   {
      if (isApplyingChangeMsg)
      {
         return;
      }

      try
      {
         if (logFile == null)
         {
            logFile = new File(modelDir.getCanonicalPath() + "/" + userName + ".json");

            logFile.createNewFile();
         }

         if (logFileWriter == null)
         {
            logFileWriter = new FileWriter(logFile, true);
         }

         JsonObject jsonObject = change.toJson();
         logFileWriter.write(jsonObject.toString() + "\n");
         logFileWriter.flush();
         
         if (logPath == null)
         {
            logPath = Paths.get(modelDir.getCanonicalPath() + "/" + userName + ".json");
         }
         
         Files.setLastModifiedTime(logPath, FileTime.fromMillis(System.currentTimeMillis()));
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }

   }


   private void tryToAdjustPosition(Object object, String property, Object targetObject, SendableEntityCreator creator)
   {
      Object value = creator.getValue(object, property);

      if (value != null && value instanceof AbstractList)
      {
         AbstractList valueList = (AbstractList) value;
         
         int indexOf = valueList.indexOf(targetObject);

         if (indexOf != historyPos)
         {
            valueList.remove(indexOf);
            valueList.add(historyPos, targetObject);
         }        
      }
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


   @Override
   public boolean update(String typ, BaseItem source, Object target, String property, Object oldValue, Object newValue)
   {
      if (isApplyingChangeMsg)
      {
         // ignore
         return true;
      }
      
      JsonObject jsonObject = (JsonObject) source;
      
      // {"id":"testerProxy",
      //  "class":"org.sdmlib.replication.SeppelSpaceProxy",
      //  "upd":{"scopes":{"class":"org.sdmlib.replication.SeppelScope",
      //                   "id":"tester.S1",
      //                   "prop":{"scopeName":"commands",
      //                           "spaces":[{"id":"testerProxy"}]}}}}

      String opCode = JsonIdMap.UPDATE;
      
      Object attributes = jsonObject.get(JsonIdMap.UPDATE);
      
      if (attributes == null)
      {
         attributes = jsonObject.get(JsonIdMap.REMOVE);
         opCode = JsonIdMap.REMOVE;
         
         if (attributes == null)
         {
            attributes = jsonObject.get("prop");
            opCode = JsonIdMap.UPDATE;
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
            .withSessionId(userName)
            .withChangeNo("" + getNewHistoryIdNumber())
            .withObjectId(jsonObject.getString(JsonIdMap.ID))
            .withObjectType(jsonObject.getString(JsonIdMap.CLASS))
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

                  String valueObjectId = (String) valueJsonObject.get(JsonIdMap.ID);
               
                  String valueObjectType = (String) valueJsonObject.get(JsonIdMap.CLASS);

                  Object valueObject = idMap.getObject(valueObjectId);

                  if (valueObjectType == null)
                  {
                     // get object and ask it
                     valueObjectType = valueObject.getClass().getName();
                  }

                  change.withValueType(valueObjectType);

                  // toOne or toMany
                  change.withPropertyKind(ChangeEvent.TO_ONE);

                  Object targetObject = idMap.getObject(change.getObjectId());

                  SendableEntityCreator creator = idMap.getCreatorClass(targetObject);

                  Object value = creator.getValue(targetObject, change.getProperty());

                  if (value != null && value instanceof Collection)
                  {
                     change.setPropertyKind(ChangeEvent.TO_MANY);
                  }

                  // newValue or oldValue?
                  if (opCode.equals(JsonIdMap.REMOVE))
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
                  
                  // does the value have properties?
                  if (valueJsonObject.get("prop") != null)
                  {
                     // call recursive
                     this.update(typ, valueJsonObject, valueObject, prop, null, null);
                  }
               }
            }
            else
            {
               String oldValueString = "" + oldValue;
               if (oldValue == null)
               {
                  oldValueString = null;
               }
               
               // plain attribute
               change.withPropertyKind(ChangeEvent.PLAIN)
               .withNewValue("" + attrValue)
               .withOldValue(oldValueString);
               
               getHistory().addChange(change);
               writeChange(change);
            }
         }
      }


      return true;
   }
}
