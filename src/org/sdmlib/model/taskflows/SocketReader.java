package org.sdmlib.model.taskflows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class SocketReader extends Thread
{
   /**
    * 
    */
   final SocketThread socketThread;
   Socket connection;
   Object defaultTargetThread;

   
   
   public SocketReader (SocketThread socketThread, Socket connection)
   {
      this.socketThread = socketThread;
      this.connection = connection;
   }
   
   @Override
   public void run()
   {
      try
      {
         InputStream byteIn = connection.getInputStream();
         
         InputStreamReader readerIn = new InputStreamReader(
            byteIn);
         BufferedReader in = new BufferedReader(

            readerIn);

         String line = "";

         while (line != null)
         {
            line = in.readLine();
            
            SDMTaskWrap sdmTaskWrap = new SDMTaskWrap(this, line);
            
            //            if (this.socketThread.defaultTargetThread != null && this.socketThread.defaultTargetThread instanceof Display)
            //            {
            //               ((Display) this.socketThread.defaultTargetThread).asyncExec(sdmTaskWrap);
            //            }
            //            else 
               if (this.socketThread.defaultTargetThread != null && this.socketThread.defaultTargetThread instanceof SDMThread)
            {
               ((SDMThread) this.socketThread.defaultTargetThread).enqueueTask(sdmTaskWrap);
            }
            else
            {
               sdmTaskWrap.run();
            }
         }
      }
      catch (IOException e)
      {
         System.out.println("Socket has been closed");
      }
   }
}