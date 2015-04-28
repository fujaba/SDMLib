package org.sdmlib.replication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.sdmlib.storyboards.Storyboard;

public class StartLaneAction
{

   private Storyboard storyboard;
   private Process child;

   public StartLaneAction()
   {

   }

   public StartLaneAction(Storyboard storyboard)
   {
      this.storyboard = storyboard;

   }

   public Process start(String name, String laneApplicationClassName, int serverPort,
         int debugSocket)
   {
      child = null;
      String[] command = null;
      String rootPath = null;
      try
      {

         if ((System.getProperty("os.name").toLowerCase()).contains("windows"))
         {
            URL resource = StartLaneAction.class.getResource("startjava.bat");
            rootPath = resource.getFile().replace("file:", "");
         }
         else
         {
            URL resource = StartLaneAction.class.getResource("startjava.sh");
            rootPath = resource.getFile().replace("file:", "");
         }
         command = new String[]
               { rootPath, 
               "java", 
               "-Xdebug", 
               "-Xrunjdwp:transport=dt_socket,address=" + debugSocket + ",server=y,suspend=y", 
               "-Dfile.encoding=UTF-8",
               "-classpath",
               System.getProperty("java.class.path") , 
               laneApplicationClassName, Integer.toString(serverPort), name };
         
         ProcessBuilder processBuilder = new ProcessBuilder(command);
         processBuilder.redirectErrorStream(true);
         child = processBuilder.start();
         
         InputStream inputStream = child.getInputStream();
         InputStreamReader in = new InputStreamReader(inputStream);
         final BufferedReader buf = new BufferedReader(in);
         String line = null;
         line = buf.readLine();
         line = line + "\n   " + buf.readLine();
         Assert.assertFalse("Was not able to start abu client correctly.\n" + line,
            line.startsWith("FATAL"));
         ;
         storyboard.add("<pre>   " + line + "</pre>");

         new Thread()
         {
            @Override
            public void run()
            {
               while (true)
               {
                  try
                  {
                     String text = buf.readLine();
                     if (text == null)
                     {
                    	 System.out.println("couldn't read buf");
                        return;
                     }
                     System.out.println(text);
                  }
                  catch (IOException e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
            }
         }.start();

         new Thread()
         {
            @Override
            public void run()
            {
               InputStream errorStream = child.getErrorStream();

               InputStreamReader in = new InputStreamReader(errorStream);
               BufferedReader errbuf = new BufferedReader(in);

               while (true)
               {
                  try
                  {
                     String text = errbuf.readLine();
                     if (text == null)
                     {
                    	 System.out.println("couldn't read buf");
                        return;
                     }
                     System.out.println(text);
                  }
                  catch (IOException e)
                  {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                  }
               }
            }
         }.start();
      }
      catch (Exception e)
      {
         Assert.fail(e.toString());
      }

      int stopSize = storyboard.getStoryboardSteps().size() + 3;
      synchronized (storyboard)
      {
         while (storyboard.getStoryboardSteps().size() < stopSize)
         {
            try
            {
               storyboard.wait(1000);
               stopSize--;
            }
            catch (InterruptedException e)
            {
               // TODO Auto-generated catch block
               // e.printStackTrace();
            }
         }
      }

      return child;
   }

}
