package org.sdmlib.models.taskflows;

import java.io.IOException;

import de.uniks.networkparser.json.JsonArray;

class SDMTaskWrap implements Runnable
{
   /**
    * 
    */
   private final SocketReader socketReader;
   private String line;

   public SDMTaskWrap(SocketReader socketReader, String line)
   {
      this.socketReader = socketReader;
      this.line = line;
   }

   @Override
   public void run()
   {
      try
      {
         Object obj = this.socketReader.socketThread.idMap
            .decode(new JsonArray().withValue(line));

         if (obj instanceof FetchFileFlow)
         {
            FetchFileFlow fetchFileFlow = (FetchFileFlow) obj;
            try
            {
               fetchFileFlow.setOut(this.socketReader.connection
                  .getOutputStream());
            }
            catch (IOException e)
            {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         }

         ((Runnable) obj).run();
      }
      catch (Exception e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}