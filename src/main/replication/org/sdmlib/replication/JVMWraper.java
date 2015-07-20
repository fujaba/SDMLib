package org.sdmlib.replication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;

public class JVMWraper
{
   public void killSubProcesses()
   {
      for (Process pid : mySubProcesses)
      {
         pid.destroy();
      }
   }
   
   private LinkedHashSet<Process> mySubProcesses = new LinkedHashSet<Process>();

   public void runJava(String debugPort, String mainClass, String args)
   {
      StringBuilder command = new StringBuilder(
         "java -Xdebug -Xrunjdwp:transport=dt_socket,address=debugPort,server=y,suspend=n " +
         "-classpath myclasspath main args"
            );

      // Albert not the nice solution
//      String java = "\"C:\\Program Files\\Java\\jdk1.8.0_31\\bin\\javaw.exe\" ";
//      String java = System.getProperty("java.home");
      String java = System.getProperty("java.home").replace("\\", "/") + "/bin/java";
      String classPath = System.getProperty("java.class.path");
      
     CGUtil.replaceAll(command, 
         "java", java, 
         "debugPort", debugPort, 
         "myclasspath", classPath, 
         "main", mainClass,
         "args", args);
      
      try
      {         
         Process pid = Runtime.getRuntime().exec(command.toString());
         
         InputStream inputStream = pid.getInputStream();
         
         boolean alive = pid.isAlive();
         
         mySubProcesses.add(pid);
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
