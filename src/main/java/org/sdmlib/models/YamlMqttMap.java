package org.sdmlib.models;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class YamlMqttMap implements MqttCallback
{
   private YamlFileMap fileMap;
   private MqttClient mqttClient;
   private String mqttUrl;
   private String userName;
   private String fileName;
   private SendableEntity modelRoot;
   private ExecutorService modelThread;
   private LinkedHashMap<String, String> state = new LinkedHashMap<>();

   public YamlMqttMap(String mqttUrl, String userName, String fileName, SendableEntity modelRoot, ExecutorService modelThread)
   {
      this.mqttUrl = mqttUrl;
      this.userName = userName;
      this.fileName = fileName;
      this.modelRoot = modelRoot;
      this.modelThread = modelThread;

      fileMap = new YamlFileMap(userName, fileName, modelRoot, modelThread);

      try
      {
         mqttClient = new MqttClient(mqttUrl, fileName + "/" + userName);
         mqttClient.setCallback(this);
         mqttClient.connect();

         mqttClient.subscribe(fileName + "/model");
         mqttClient.subscribe(fileName + "/lobby");

         String msg = "" +
                 "msg: hello\n" +
                 "user: " + userName + "\n";

         msg = msg + "lastTimeStamps: " + fileMap.getYamlIdMap().getLastTimeStamps() + "\n";

         mqttClient.publish(fileName + "/lobby", new MqttMessage(msg.getBytes()));
      }
      catch (Exception e)
      {
         Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);

         e.printStackTrace();
      }

      System.out.println();
   }


   @Override
   public void deliveryComplete(IMqttDeliveryToken token)
   {

   }


   @Override
   public void connectionLost(Throwable cause)
   {

   }

   @Override
   public void messageArrived(String topic, MqttMessage message) throws Exception
   {
      if (topic.equals(fileName + "/lobby"))
      {
         LinkedHashMap<String, String> msgMap = new Yamler().decode(message.toString());

         String msgType = msgMap.get("msg");

         if (msgType.equals("hello"))
         {
            handleHello(msgMap);
         }
         else if (msgType.equals("welcome"))
         {
            String newUserName = msgMap.get("newUser");
            if (newUserName.equals(userName))
            {
               if (state.get(userName + ".firstWelcome") !=  null)
               {
                  // I already have been welcomed. Ignore.
                  return;
               }

               state.put(userName + ".firstWelcome", msgMap.get("oldUser"));

               // I am the new user.
               // do I know a thing, the old user does not know
               String lastTimeStamps = msgMap.get("lastTimeStamps");
               sendUpdates(lastTimeStamps);
            }
         }
      }
   }

   private void handleHello(LinkedHashMap<String, String> msgMap) throws MqttException
   {
      // new kid on the block
      String newUserName = msgMap.get("user");

      if (newUserName.equals(userName))
      {
         // its my own message. Ignore
         return;
      }

      // send welcome
      String msg = "" +
              "msg: welcome\n" +
              "newUser: " + newUserName + "\n" +
              "oldUser: " + userName + "\n";

      msg = msg + "lastTimeStamps: " + fileMap.getYamlIdMap().getLastTimeStamps() + "\n";

      mqttClient.publish(fileName + "/lobby", new MqttMessage(msg.getBytes()));
   }

   private void sendUpdates(String lastTimeStamps)
   {
      LinkedHashMap<String, String> lastTimeStampMap = fileMap.getYamlIdMap().getLastTimeStampMap(lastTimeStamps);

      for (Map.Entry<String, String> e : fileMap.getYamlIdMap().getAttrTimeStamps().entrySet())
      {
         String timeStamp = e.getValue();
         int pos = timeStamp.lastIndexOf('.');
         String user = timeStamp.substring(pos + 1);

         String myTimeStamp = lastTimeStampMap.get(user);
         if (myTimeStamp == null || timeStamp.compareTo(myTimeStamp) <= 0)
         {
            String key = e.getKey();
            pos = key.lastIndexOf('.');
            String objId = key.substring(0, pos);
            String propertyName = key.substring(pos + 1);

            Object obj = fileMap.getYamlIdMap().getObjIdMap().get(objId);
            SendableEntityCreator creator = fileMap.getYamlIdMap().getCreator(obj.getClass().getSimpleName());
            Object value = creator.getValue(obj, propertyName);

            // compute update
            StringBuilder buf = new StringBuilder();
            fileMap.getYamlIdMap().encodeAttrValue(buf, obj, propertyName, value);

            // send it
            try
            {
               mqttClient.publish(fileName + "/model", new MqttMessage(buf.toString().getBytes()));
            }
            catch (MqttException e1)
            {
               Logger.getGlobal().log(Level.SEVERE, e1.getMessage(), e1);
            }
         }
      }
   }


}
