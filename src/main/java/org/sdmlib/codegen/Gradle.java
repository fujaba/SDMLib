package org.sdmlib.codegen;

import org.gradle.tooling.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Gradle
{
   private static int returnCode;

   public static int runTask(String taskName)
   {
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

      return returnCode;

   }

   public static void removeDir(String rootDir, String packageName)
   {
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
}
