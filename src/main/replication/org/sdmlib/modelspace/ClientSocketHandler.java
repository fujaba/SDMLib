package org.sdmlib.modelspace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import org.sdmlib.replication.ChangeEvent;

import de.uniks.networkparser.json.JsonObject;
import javafx.application.Platform;

public class ClientSocketHandler extends Thread
{

   private final class HandleLineRunnable implements Runnable
   {
      private String runLine;

      public HandleLineRunnable(String newLine)
      {
         runLine = newLine;
      }

      @Override
      public void run()
      {
         JsonObject jsonObject = new JsonObject();
         jsonObject.withValue(runLine);
         
         if (jsonObject.has("msgtype"))
         {
            String msgtype = jsonObject.getString("msgtype");
            
            if (ModelCloudProxy.class.getSimpleName().equals(msgtype))
            {
               connectCloudProxy(jsonObject);
            }
            else
            {
               subscribeCloudToSpace(jsonObject);
            }
         }
         else if (jsonObject.has(ChangeEvent.PROPERTY_OBJECTID))
         {
            receiveChangeEvent(jsonObject);
         }
      }
   }

   private ModelCloud modelCloud;
   private Socket clientSocket;
   private BufferedReader in;
   private String line;
   ModelCloudProxy proxy; 

   public ClientSocketHandler(ModelCloud modelCloud, Socket clientSocket)
   {
      this.modelCloud = modelCloud;
      this.clientSocket = clientSocket;
   }

   @Override
   public void run()
   {
      // open clientSocket and read messages
      try
      {
         InputStream inputStream = clientSocket.getInputStream();
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
         in = new BufferedReader(inputStreamReader);
         
         line = in.readLine();
                  
         while (line != null)
         {
            Platform.runLater(new HandleLineRunnable(line));
            
            System.out.println(line);
            
            line = in.readLine();
         }
      }
      catch (IOException e)
      {
         System.out.println("Connection closed");
         Platform.runLater(new Runnable()
         {
            @Override
            public void run()
            {
               // TODO Auto-generated method stub
               if (proxy != null) 
               {
                  proxy.setState("offline");
               }
            }
         });
      }
   }


   private void receiveChangeEvent(JsonObject jsonObject)
   {
      // is is a change, find model space and apply it
      String modelSpaceName = (String) jsonObject.get("modelSpaceName");
      
      if (modelSpaceName != null)
      {
         ModelSpaceProxy spaceProxy = modelCloud.getOrCreateModelSpaceProxy(modelSpaceName, null);
         ModelDirListener modelDirListener = spaceProxy.getModelDirListener();
         if (modelDirListener != null)
         {
            modelDirListener.receiveChange(jsonObject);
         }
      }
   }

   
   private void connectCloudProxy(JsonObject jsonObject)
   {
      // hostname and portno of partner, store socket at model channel
      String hostName = jsonObject.getString("hostName");
      int portNo = jsonObject.getInt("portNo");
      proxy = modelCloud.getProxy(hostName, portNo);
      
      if (proxy == null)
      {
         proxy = new ModelCloudProxy().withHostName(hostName).withPortNo(portNo);
         ModelCloudChannel channel = new ModelCloudChannel(modelCloud, proxy);
      }
      
      ModelCloudChannel channel = proxy.getChannel();
      if (channel.getSocket() != clientSocket)
      {
         channel.setSocket(clientSocket);
         try
         {
            channel.sendMyProxyAddress();
            
            modelCloud.subscribeForExistingModelSpaces();
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }
      
      if (proxy.getRoot() == null)
      {
         modelCloud.withServers(proxy);
      }
   }

   private void subscribeCloudToSpace(JsonObject jsonObject)
   {
      String hostName = jsonObject.getString("hostName");
      int portNo = jsonObject.getInt("portNo");
      String location = jsonObject.getString("modelSpaceName");
      
      ModelSpaceProxy spaceProxy = modelCloud.getOrCreateModelSpaceProxy(location, null);
      
      ModelCloudProxy cloudProxy = modelCloud.getProxy(hostName, portNo);
      
      cloudProxy.withProvidedSpaces(spaceProxy);
      
      // send data
      spaceProxy.sendAllDataTo(cloudProxy);
   }
}
