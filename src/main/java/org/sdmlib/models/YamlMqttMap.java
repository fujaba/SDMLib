package org.sdmlib.models;

import de.uniks.networkparser.interfaces.SendableEntity;

import java.util.concurrent.ExecutorService;

public class YamlMqttMap
{
   private String mqttUrl;
   private String userName;
   private String fileName;
   private SendableEntity modelRoot;
   private ExecutorService modelThread;

   public YamlMqttMap(String mqttUrl, String userName, String fileName, SendableEntity modelRoot, ExecutorService modelThread)
   {
      this.mqttUrl = mqttUrl;
      this.userName = userName;
      this.fileName = fileName;
      this.modelRoot = modelRoot;
      this.modelThread = modelThread;

      new YamlFileMap(userName, fileName, modelRoot, modelThread);
   }
}
