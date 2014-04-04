package org.sdmlib.model.taskflows;

import java.util.concurrent.LinkedBlockingQueue;

public class SDMThread extends Thread
{
   private LinkedBlockingQueue<Runnable> taskQueue = new LinkedBlockingQueue<Runnable>();

   public SDMThread(String name)
   {
      super(name);
   }

   @Override
   public void run()
   {
      while (true)
      {
         try
         {
            Runnable task = taskQueue.take();
            task.run();
         }
         catch (Exception e)
         {
            // no problem here
            System.err.println("task did not succeed: ");
            e.printStackTrace();
         }
      }
   }

   public void enqueueTask(Runnable task)
   {
      try
      {
         taskQueue.put(task);
      }
      catch (InterruptedException e)
      {
         // should not happen
         e.printStackTrace();
      }
   }
}
