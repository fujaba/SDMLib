package org.sdmlib.modelspace;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler extends Thread
{

   private ModelCloud modelCloud;
   private ServerSocket serverSocket;
   private Socket clientSocket;
   private ClientSocketHandler clientSocketHandler;

   public ServerSocketHandler(ModelCloud modelCloud)
   {
      this.modelCloud = modelCloud;
   }

   @Override
   public void run()
   {
      try
      {
         serverSocket = new ServerSocket(modelCloud.getAcceptPort());
         
         while (true)
         {
            clientSocket = serverSocket.accept();
            
            clientSocketHandler = new ClientSocketHandler(modelCloud, clientSocket);
            
            clientSocketHandler.start();
         }
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
