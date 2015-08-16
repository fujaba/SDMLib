package org.sdmlib.modelspace;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

import de.uniks.networkparser.json.JsonObject;

public class ModelCloudChannel implements PropertyChangeListener
{
   private ModelCloud modelCloud;
   private ModelCloudProxy proxy;
   private Socket socket;
   private BufferedWriter out;
   
   public void setSocket(Socket socket)
   {
      this.socket = socket;
   }
   
   public Socket getSocket()
   {
      return socket;
   }

   public ModelCloudChannel(ModelCloud modelCloud, ModelCloudProxy proxy)
   {
      this.modelCloud = modelCloud;
      this.proxy = proxy;
      
      proxy.setChannel(this);
      
      this.propertyChange(null);
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt)
   {
      if (this.socket == null && proxy.getPortNo() > 0)
      {
         try
         {
            socket = new Socket(proxy.getHostName(), proxy.getPortNo());
            
            new ClientSocketHandler(modelCloud, socket).start();
            
            // first send my own data
            sendMyProxyAddress();
         }
         catch (IOException e)
         {
            // did not work
            socket = null;
            proxy.setState("offline");
         }
      }
      
   }

   public void sendMyProxyAddress() throws IOException
   {
      OutputStream outputStream = socket.getOutputStream();
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
      out = new BufferedWriter(outputStreamWriter);
      
      JsonObject jsonObject = new JsonObject();
      jsonObject.withKeyValue("msgtype", ModelCloudProxy.class.getSimpleName())
      .withKeyValue("hostName", "localhost")
      .withKeyValue("portNo", modelCloud.getAcceptPort());
      
      String message = jsonObject.toString();
      
      out.write(message + "\n");
      out.flush();
      
      proxy.setState("online");
   }


}
