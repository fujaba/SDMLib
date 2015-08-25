package org.sdmlib.modelspace;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import org.sdmlib.modelspace.ModelSpace.ApplicationType;
import org.sdmlib.modelspace.util.CloudModelDirectoryCreator;
import org.sdmlib.replication.ChangeEvent;
import org.sdmlib.replication.ChangeEventList;

import com.oracle.webservices.internal.api.message.MessageContextFactory;

import de.uniks.networkparser.json.JsonIdMap;
import de.uniks.networkparser.json.JsonObject;
import javafx.application.Platform;

public class ModelDirListener extends Thread implements PropertyChangeListener
{
   private ModelCloud modelCloud;
   private String location;
   private WatchService watcher;
   private LinkedHashMap<String, BufferedReader> fileReaders = new LinkedHashMap<String, BufferedReader>();
   private File dir;
   private String spaceName;
   private ChangeEventList history;
   private ModelSpaceProxy spaceProxy;
   private LinkedHashMap<String, ModelDirListener> dirListenerMap;
   
   public ChangeEventList getHistory()
   {
      return history;
   }

   public ModelDirListener(LinkedHashMap<String, ModelDirListener> dirListenerMap, ModelCloud modelCloud, String location, String spaceName)
   {
      this.dirListenerMap = dirListenerMap;
      
      if (this.dirListenerMap == null)
      {
         this.dirListenerMap = new LinkedHashMap<String, ModelDirListener>();
      }
      
      this.dirListenerMap.put(location, this);
      
      this.modelCloud = modelCloud;
      this.location = location;
      this.spaceName = spaceName;
      
      if (this.spaceName.startsWith("/"))
      {
         this.spaceName = this.spaceName.substring(1);
      }

      try
      {
         watcher = FileSystems.getDefault().newWatchService();

         Path dirPath = Paths.get(location);
         dirPath.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }


   @Override
   public void run()
   {
      this.setName("" +  location + "Listener");

      Platform.runLater(new Runnable()
      {
         @Override
         public void run()
         {
            scanDir();
         }
      });

      while (true)
      {
         try
         {
            WatchKey watchKey = watcher.take();

            // System.out.println("got dir change " + watchKey);

            for (WatchEvent<?> event : watchKey.pollEvents())
            {
               Platform.runLater(new EventRunnable(event));
            }

            watchKey.reset();
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }
   }
   
   
   private final class EventRunnable implements Runnable
   {
      private WatchEvent<?> event;

      public EventRunnable(WatchEvent<?> event)
      {
         this.event = event;
      }

      @Override
      public void run()
      {
         handleWatchEvent(event);
      }
   }


   private void handleWatchEvent(WatchEvent<?> event)
   {
      try {
      WatchEvent.Kind<?> kind = event.kind();

      if (kind == OVERFLOW) 
      {
         return;
      }
      else if (kind == ENTRY_CREATE)
      {
         // if its a new json file, read it
         WatchEvent<Path> ev = (WatchEvent<Path>)event;

         Path filepath = ev.context();

         String shortFileName = filepath.toString();
         
         String fileName = location + "/" + shortFileName;

         if (fileName.endsWith(ModelSpace.JSONCHGS))
         {
            addJsonFile(fileName);
         }
         else
         {
            File f = new File(fileName);
            if (f.isDirectory())
            {
               // recursion
               String newLocation = location + "/" + shortFileName;
               if (this.dirListenerMap.get(newLocation) == null)
               {
                  ModelDirListener modelDirListener = new ModelDirListener(this.dirListenerMap, modelCloud, newLocation, spaceName + "/" + shortFileName);
                  modelDirListener.start();
               }
            }
            else
            {
               updateCloudFile(shortFileName, f);
            }

         }
      }
      else if (kind == ENTRY_MODIFY)
      {
         // do I have a buf for this one, then read
         WatchEvent<Path> ev = (WatchEvent<Path>)event;

         Path filepath = ev.context();

         String shortFileName = filepath.toString();
         String fileName = location + "/" + shortFileName;
         
         if (fileName.endsWith(ModelSpace.JSONCHGS))
         {
            addJsonFile(fileName);
         }

         File file = new File(fileName);

         String name = file.getCanonicalPath();

         BufferedReader buf = fileReaders.get(name);

         if (buf != null)
         {
            readChanges(buf);
         }
         else
         {
            if (file.isDirectory())
            {
               // recursion
               String newLocation = location + "/" + shortFileName;
               if (this.dirListenerMap.get(newLocation) == null)
               {
                  ModelDirListener modelDirListener = new ModelDirListener(this.dirListenerMap, modelCloud, newLocation, spaceName + "/" + shortFileName);
                  modelDirListener.start();
               }
            }
            else
            {
               updateCloudFile(shortFileName, file);
            }  
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
      catch (Exception e) 
      {
         e.printStackTrace();
      }
   
   }
   
   public ModelSpace fileDataModelSpace = null;
   private JsonIdMap fileDataIdMap;
   private CloudModelDirectory cloudModelDirectory;


   private void scanDir()
   {
      dir = new File(location);

      if (dir.isDirectory())
      {
         String[] list = dir.list();

         for (String fileName : list)
         {
            if (".cloudData".equals(fileName))
            {
               // this is the cloud management directory, skip it
               continue;
            }
            
            if (fileName.endsWith(ModelSpace.JSONCHGS))
            {
               addJsonFile(location + "/" + fileName);
            }
            else
            {
               File f = new File(location + "/" + fileName);
               if (f.isDirectory())
               {
                  // recursion
                  String newLocation = location + "/" + fileName;
                  if (this.dirListenerMap.get(newLocation) == null)
                  {
                     ModelDirListener modelDirListener = new ModelDirListener(this.dirListenerMap, modelCloud, newLocation, spaceName + "/" + fileName);
                     modelDirListener.start();
                  }
               }
               else 
               {
                  // usual file, create or open .filedata model space and add this file to it. 
                  updateCloudFile(fileName, f);
               }
            }
         }
      }
   }

   private void updateCloudFile(String fileName, File f)
   {
      if (fileDataModelSpace == null)
      {
         fileDataIdMap = CloudModelDirectoryCreator.createIdMap("" + modelCloud.getHostName() + modelCloud.getAcceptPort());
         
         cloudModelDirectory = new CloudModelDirectory();
         
         fileDataIdMap.put("cloudModelDirectory", cloudModelDirectory);
         
         fileDataModelSpace = new ModelSpace(fileDataIdMap, "" + modelCloud.getHostName() + modelCloud.getAcceptPort(), ApplicationType.JavaFX);
         fileDataModelSpace.open(location + "/.fileData");
         
         cloudModelDirectory.getPropertyChangeSupport().addPropertyChangeListener(this);
      }
      
      CloudModelFile cloudFile = cloudModelDirectory.getOrCreateFiles(fileDataIdMap, fileName);
      
      long lastModified = f.lastModified();
      
      if (lastModified > cloudFile.getLastModifiedTime())
      {
         cloudFile.setLastModifiedTime(lastModified);
      }
   }


   private void addJsonFile(String fileName)
   {
      // found a model file, create modelSpaceProxy and history and read the file
      this.spaceProxy = modelCloud.getOrCreateModelSpaceProxy(spaceName, this);
      
      try
      {
         
         File f = new File(fileName);
         
         String canonicalPath = f.getCanonicalPath();

         if (fileReaders.get(canonicalPath) == null)
         {

            FileReader fileReader = new FileReader(f);
            BufferedReader buf = new BufferedReader(fileReader);

            fileReaders.put(canonicalPath, buf);

            readChanges(buf);
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
   
   
   public void readChanges(BufferedReader buf)
   {
      if (this.history == null)
      {
         this.history = new ChangeEventList();
      }

      String line;
      try
      {
         line = buf.readLine();

         while (line != null)
         {
            // process change
            handleChange(line);
            line = buf.readLine();
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   
   private int handleChange(String line)
   {
      // line is a json string describing a change
      JsonObject jsonObject = new JsonObject().withValue(line);

      ChangeEvent change = new ChangeEvent(jsonObject);
        
      int historyPos = history.addChange(change);

      if (historyPos < 0)
      {
         // change already known, ignore
         return historyPos;
      }

      // try to apply change
      sendChange(change);
      
      return historyPos;
   }


   private void sendChange(ChangeEvent change)
   {
      for (ModelCloudProxy cloudProxy : this.spaceProxy.getProvidingClouds())
      {
         if (cloudProxy.getState().equals("online"))
         {
            ModelCloudChannel channel = cloudProxy.getChannel();
            
            JsonObject json = change.toJson();
            json.withKeyValue("modelSpaceName", spaceProxy.getLocation());
            String msg = json.toString();
            channel.send(msg);
         }
      }
   }

   public void receiveChange(JsonObject jsonObject)
   {
      int pos = handleChange(jsonObject.toString());
      
      if (pos >= 0)
      {
         // new change, write it to file 
         String fileName = jsonObject.getString(ChangeEvent.PROPERTY_SESSIONID);
         fileName = location + "/" + fileName + ModelSpace.JSONCHGS;
         File file = new File(fileName);
         try
         {
            FileWriter fileWriter = new FileWriter(file, true);
            
            BufferedWriter out = new BufferedWriter(fileWriter);
            out.write(jsonObject.toString() + "\n");
            out.flush();
            out.close();
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
   }

   @Override
   public void propertyChange(final PropertyChangeEvent evt)
   {
      if (evt == null)
      {
         // init
         for (CloudModelFile cloudFile : cloudModelDirectory.getFiles())
         {
            cloudFile.getPropertyChangeSupport().addPropertyChangeListener(this);
         }
      }
      else if (evt.getNewValue() != null && evt.getNewValue() instanceof CloudModelFile)
      {
         CloudModelFile cloudFile = (CloudModelFile) evt.getNewValue();
         cloudFile.getPropertyChangeSupport().addPropertyChangeListener(this);
      }
      
      handleCloudFileChange(evt);
   }

   public void handleCloudFileChange(PropertyChangeEvent evt)
   {
      // try to make file and cloudFile consistent
      if (evt == null)
      {
         for (CloudModelFile cloudFile : cloudModelDirectory.getFiles())
         {
            makeCloudFileConsistent(cloudFile);
         }
      }
      else if (evt.getSource() instanceof CloudModelFile)
      {
         makeCloudFileConsistent((CloudModelFile) evt.getSource()); 
      }
   }

   private void makeCloudFileConsistent(CloudModelFile cloudFile)
   {
      // is the cloudFile information complete
      if (cloudFile.getFileName() == null || cloudFile.getLastModifiedTime() <= 0)
      {
         return;
      }
      
      // do I have the file on disk?
      File diskFile = new File(location + "/" + cloudFile.getFileName());
      
      if (! diskFile.exists() || diskFile.lastModified() < cloudFile.getLastModifiedTime())
      {
         // create load file task
         Task loadFileTask = modelCloud.getOrCreateFileTask(
            spaceName, 
            cloudFile.getFileName(),
            cloudFile.getLastModifiedTime());
         
         
      }
      
   }

}
