package org.sdmlib.storyboards;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.concurrent.TimeUnit;

import org.sdmlib.models.YamlIdMap;

public class MikadoPlan
{
   public static void main(String[] args)
   {
      new MikadoPlan().run(args);
   }

   private void run(String[] args)
   {
      boolean autoLoad = false;
      String fileName = null;
      String mainGoal = "mikado";

      // analyze args
      for (int i = 0; i < args.length; i++)
      {
         String token = args[i];

         if (token.startsWith("-auto"))
         {
            autoLoad = true;
         }
         else if (token.startsWith("-goal:"))
         {
            i++;
            mainGoal = args[i];
         }
         else
         {
            fileName = args[i];
         }
      }

      if (fileName == null)
      {
         System.out.println("filename required!!, -auto optional");
      }

      // load file
      loadCSVFile(fileName, mainGoal);

      // supervise file
      if (autoLoad)
      {
         String pathName = ".";
         int pos = fileName.lastIndexOf('/');

         if (pos > 0)
         {
            pathName = fileName.substring(0, pos);
         }

         Path path = Paths.get(pathName);
         WatchService watchService = null;

         try
         {
            watchService = path.getFileSystem().newWatchService();
            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            WatchKey watchKey = null;
            while (true)
            {
               watchKey = watchService.poll(10, TimeUnit.MINUTES);

               if(watchKey != null)
               {
                  String fn = fileName;
                  String mg = mainGoal;

                  watchKey.pollEvents().stream().forEach(event -> handleDirEvent(event, fn, mg));

                  watchKey.reset();
               }
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }
      }

   }

   private void handleDirEvent(WatchEvent<?> event, String fileName, String mainGoal)
   {
      String context = event.context().toString();

      if (fileName.endsWith(context))
      {
         // yep, its me
         System.out.println(context);
         loadCSVFile(fileName, mainGoal);
      }
   }

   /**
    * 
    * @see <a href='../../../../../../doc/SDMLibPlan.csv.html'>SDMLibPlan.csv.html</a>
 */
   private void loadCSVFile(String fileName, String mainGoal)
   {
      String storyName = fileName.substring(fileName.lastIndexOf('/')+1);

      Storyboard story = new Storyboard();

      story.getStoryboard().setName(storyName);

      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());

      idMap.decodeCSV(fileName);

      Goal root = (Goal) idMap.getObject(mainGoal);

      MikadoLog rootLog = (MikadoLog) idMap.getObject(mainGoal + "log");

      story.addImage(rootLog.burnDownChartPng());

      Goal done = root.clipDone();

      story.addStep("open goals");

      story.addObjectDiagram(root);

      story.addStep("closed goals");

      story.addObjectDiagram(done);

      story.dumpHTML();
   }
}
