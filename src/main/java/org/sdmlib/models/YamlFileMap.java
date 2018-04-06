package org.sdmlib.models;

import de.uniks.networkparser.interfaces.SendableEntity;
import javafx.application.Platform;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class YamlFileMap
{
   private SDMComponentListener sdmComponentListener;
   private YamlIdMap yamlIdMap = null;
   private ScheduledExecutorService timer = null;
   private String userName = null;
   private String fileName;
   private SendableEntity modelRoot;
   private ExecutorService modelThread;
   private StringBuilder buf;
   private boolean loadingFile;
   private LocalDateTime lastChange;
   private int changeCount = 0;
   private boolean closed = false;


   public YamlIdMap getYamlIdMap()
   {
      return yamlIdMap;
   }


   private YamlFileMap()
   {
      // use with parameters.
   }

   public YamlFileMap(String userName, String fileName, SendableEntity modelRoot)
   {
      this(userName, fileName, modelRoot, null);
   }

   public YamlFileMap(String userName, String fileName, SendableEntity modelRoot, ExecutorService modelThread)
   {
      this.userName = userName;
      this.fileName = fileName;
      this.modelRoot = modelRoot;
      this.modelThread = modelThread;

      Objects.requireNonNull(userName);
      Objects.requireNonNull(fileName);
      Objects.requireNonNull(modelRoot);

      // first, add Listener and protocol changes
      buf = new StringBuilder();
      this.yamlIdMap = new YamlIdMap(modelRoot.getClass().getPackage().getName()).withUserId(userName);
      sdmComponentListener = new SDMComponentListener(modelRoot, e -> changeListener(e));

      // load old file
      loadFile();

      timer = Executors.newSingleThreadScheduledExecutor();

      timer.schedule(() -> checkFileCompression(), 10, TimeUnit.SECONDS);
   }


   public YamlFileMap(String fileName, SendableEntity modelRoot)
   {
      this.fileName = fileName;
      this.modelRoot = modelRoot;

      Objects.requireNonNull(fileName);
      Objects.requireNonNull(modelRoot);

      // first, add Listener and protocol changes
      buf = new StringBuilder();
      this.yamlIdMap = new YamlIdMap(modelRoot.getClass().getPackage().getName());
      sdmComponentListener = new SDMComponentListener(modelRoot, e -> changeListener(e));

      // load old file
      loadFile();

      timer = Executors.newSingleThreadScheduledExecutor();

      timer.schedule(() -> checkFileCompression(), 10, TimeUnit.SECONDS);
   }

   public void removeYou()
   {
      this.closed = true;
      sdmComponentListener.removeYou();
      timer.shutdown();
   }

   private void checkFileCompression()
   {
      if (closed) return;

      try
      {
         if (modelThread != null)
         {
            modelThread.execute(() -> doCheckFileCompression());
         }
         else
         {
            Platform.runLater(() -> doCheckFileCompression());
         }
      }
      catch (Exception e)
      {
         // probably not running a gui, just do it direct
         doCheckFileCompression();
      }
   }

   // run by javafx or model thread
   private void doCheckFileCompression()
   {
      if (closed) return;

      LocalDateTime checkTime = LocalDateTime.now();

      if (Duration.between(lastChange, checkTime).toMillis() > 15*1000 && changeCount >= 10)
      {
         compressLogFile();
      }

      timer.schedule(() -> checkFileCompression(), 10, TimeUnit.SECONDS);
   }

   public void compressLogFile()
   {
      // write current state to file
      try
      {
         String yamlText = yamlIdMap.encode(modelRoot);
         Path path = Paths.get(getYamlFileName());
         Files.createDirectories(path.getParent());
         Files.write(path, yamlText.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

         Path logPath = Paths.get(getYamlFileName() + ".log");
         Files.write(logPath, "".getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
         changeCount = 0;
      }
      catch (IOException e)
      {
         Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
      }
      // truncate log file
   }

   private void loadFile()
   {
      try
      {
         String yamlFileName = getYamlFileName();
         Path path = Paths.get(yamlFileName);
         if (Files.exists(path))
         {
            byte[] bytes = Files.readAllBytes(path);
            String yamlText = new String(bytes);
            loadingFile = true;
            yamlIdMap.decode(yamlText, modelRoot);
         }

         Path logPath = Paths.get(yamlFileName + ".log");
         if (Files.exists(logPath))
         {
            byte[] logBytes = Files.readAllBytes(logPath);
            String yamlLogText = new String(logBytes);
            yamlIdMap.decode(yamlLogText);
         }

      } catch (IOException e)
      {
         Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
      }
      finally
      {
         loadingFile = false;
      }
   }

   private String getYamlFileName()
   {
      String yamlFileName = fileName + ".yaml";
      if (userName != null)
      {
         yamlFileName = fileName + "." + userName + ".yaml";
      }
      return yamlFileName;
   }

   private void changeListener(PropertyChangeEvent e)
   {
      if (loadingFile || closed)
      {
         return;
      }

      String yaml = yamlIdMap.encode(e);
      buf.append(yaml);

      // write to log file
      try
      {
         Path path = Paths.get(getYamlFileName() + ".log");
         if (path.getParent() != null)
         {
            Files.createDirectories(path.getParent());
         }
         Files.write(path, buf.toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
         buf.setLength(0);
      }
      catch (IOException e1)
      {
         Logger.getGlobal().log(Level.SEVERE, e1.getMessage(), e1);
      }

      lastChange = LocalDateTime.now();
      changeCount++;
   }



}
