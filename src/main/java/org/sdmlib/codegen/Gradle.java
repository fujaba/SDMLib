package org.sdmlib.codegen;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnectionException;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.ResultHandler;

public class Gradle
{
   private static int returnCode;

   public static int runTask(String taskName)
   {
      Logger.getGlobal().info("starting gradle");
      ProjectConnection connection = GradleConnector.newConnector()
              .forProjectDirectory(new File("."))
              .connect();

      returnCode = 200;

      try {
         BuildLauncher buildLauncher = connection.newBuild().forTasks(taskName);
         buildLauncher.run(new ResultHandler<Void>()
         {
            @Override
            public void onComplete(Void result)
            {
               returnCode = 0;
            }

            @Override
            public void onFailure(GradleConnectionException failure)
            {
               failure.printStackTrace();

               returnCode = 101;
            }
         });
      } finally {
         connection.close();
      }

      Logger.getGlobal().info("gradle result: " + returnCode);


      return returnCode;

   }

   public static void removeDir(String rootDir, String packageName)
   {
	   System.out.println("REMOVE:"+rootDir+" - "+packageName);
      try
      {
         Path root = Paths.get(rootDir);

         String packageDirName = packageName.replaceAll("\\.", "/");

         Path dir = Paths.get(rootDir + "/" + packageDirName);

         boolean exists = Files.exists(dir) && Files.isDirectory(dir);

         String dirName = dir.toAbsolutePath().toString();

         if (Files.exists(dir) && Files.isDirectory(dir))
         {
            for (Path p : Files.newDirectoryStream(dir))
            {
               if (Files.isDirectory(p))
               {
                  removeDir(rootDir, packageName + "." + p.getName(p.getNameCount()-1));
               }
               else
               {
                  Files.delete(p);
               }
            }
         }
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
   }

   public static void insertCode(String rootDir, String className, String code)
   {
      Path root = Paths.get(rootDir);

      String fileName = className.replaceAll("\\.", "/");

      fileName = fileName + ".java";

      Path file = Paths.get(rootDir + "/" + fileName);

      boolean exists = Files.exists(file);

      String absFileName = file.toAbsolutePath().toString();

      if (exists)
      {
         try
         {
            byte[] bytes = Files.readAllBytes(file);

            StringBuilder buf = new StringBuilder(new String(bytes));

            int bodyStart = buf.indexOf("{") + 1;

            buf.insert(bodyStart, "\n" + code);

            Files.write(file, buf.toString().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
         }
         catch (IOException e)
         {
            e.printStackTrace();
         }
      }

   }
}
