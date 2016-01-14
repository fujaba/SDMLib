package org.sdmlib.modelspace;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

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
   private InputStream inputStream; 

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
         inputStream = clientSocket.getInputStream();
         InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
         in = new BufferedReader(inputStreamReader);
         
         line = in.readLine();
                  
         while (line != null)
         {
            if (line.startsWith("{"))
            {
               try {
                  
                  final JsonObject jsonObject = new JsonObject();
                  jsonObject.withValue(line);
                  
                  if (jsonObject.has("msgtype"))
                  {
                     String msgtype = jsonObject.getString("msgtype");
                     if (msgtype.equals("fileTransfer"))
                     {
                        // read the file content
                        int fileSize = jsonObject.getInt("fileSize");
                        final byte[] content = new byte[fileSize];
                        
                        int readSize = 0;
                        
                        while (readSize < fileSize)
                        {
                           int blockSize = inputStream.read(content, readSize, fileSize-readSize);
                           
                           if (blockSize == -1)
                           {
                              // something did not work.
                              throw new RuntimeException("Receiving file data problem occured");
                           }
                           readSize += blockSize;
                        }
                        
                        System.out.println("Received " + jsonObject.getString("fileName") + " " + readSize + " of " + fileSize + " bytes.");
                        
                        if (readSize == fileSize)
                        {
                           Platform.runLater(new Runnable()
                           {
                              @Override
                              public void run()
                              {
                                 String fileName = modelCloud.getLocation() + "/" + jsonObject.getString("fileName");
                                 long lastModified = (long) jsonObject.get("lastModified");
                                 // got it, write the file
                                 try
                                 {
                                    Path path = Paths.get(fileName);
                                    
                                    if (Files.exists(path))
                                    {
                                       // mv old version to .fileData
                                       Path targetPath = path.getParent();
                                       String targetFileDir = targetPath.toString();
                                       Path plainNamePath = path.getName(path.getNameCount()-1);
                                       String plainName = plainNamePath.toString();
                                       
                                       Date date = new Date(lastModified);
                                       SimpleDateFormat simpleDateFormat = new SimpleDateFormat(".yyyy.MM.dd_HH.mm.ss");
                                       String dateString = simpleDateFormat.format(date);
                                       
                                       String fullTargetFileName = targetFileDir + "/.fileData/" + plainName + dateString;
                                       
                                       targetPath = Paths.get(fullTargetFileName);
                                       
                                       Files.move(path, targetPath);
                                    }
                                    
                                    // write new file
                                    Files.write(path, content);
                                    new File(fileName).setLastModified(lastModified);
                                 }
                                 catch (IOException e)
                                 {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                 }
                              }
                           });
                        }
                     }
                     else
                     {
                        Platform.runLater(new HandleLineRunnable(line));
                     }
                  }
                  else
                  {
                     Platform.runLater(new HandleLineRunnable(line));
                  }
               }
               catch (Exception e)
               {
                  e.printStackTrace();
               }
            }
            else
            {
               // only json messages are accepted
               System.out.println("only json messages are accepted\n" + line);
            }
            
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
