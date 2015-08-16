package org.sdmlib.modelspace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import de.uniks.networkparser.json.JsonObject;
import javafx.application.Platform;

public class ClientSocketHandler extends Thread
{

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
            Platform.runLater(new Runnable()
            {
               @Override
               public void run()
               {
                  JsonObject jsonObject = new JsonObject();
                  jsonObject.withValue(line);
                  
                  if (jsonObject.has("msgtype"))
                  {
                     String msgtype = jsonObject.getString("msgtype");
                     
                     if (ModelCloudProxy.class.getSimpleName().equals(msgtype))
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
                  }
               }
            });
            
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
}
