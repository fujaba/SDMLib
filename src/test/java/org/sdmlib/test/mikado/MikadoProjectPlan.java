package org.sdmlib.test.mikado;

import org.junit.Test;
import org.sdmlib.models.YamlIdMap;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.MikadoLog;
import org.sdmlib.storyboards.Storyboard;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MikadoProjectPlan
{
     /**
    * 
    * @see <a href='../../../../../../../doc/ExcelProjectPlan.html'>ExcelProjectPlan.html</a>
 */
   @Test
   public void testExcelProjectPlan()
   {
      Storyboard story = new Storyboard();

      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());

      idMap.decodeCSV("doc/projectPlans/SDMLibPlan.csv");

      Goal sdmlib = (Goal) idMap.getObject("sdmlib");

      MikadoLog relst18log = (MikadoLog) idMap.getObject("relst18log");

      story.add(relst18log.burnDownChart());

      Goal done = sdmlib.clipDone();

      story.addStep("open goals");

      story.addObjectDiagram(sdmlib);

      story.addStep("closed goals");

      story.addObjectDiagram(done);

      story.dumpHTML();
   }
   
     /**
    * 
    * @see <a href='../../../../../../../doc/MikadoProjectPlan.html'>MikadoProjectPlan.html</a>
 */
   @Test
   public void testMikadoProjectPlan()
   {
      Storyboard story = new Storyboard();

      String yaml =
              "- Goal         description:                             parents:   \n" +
              "  root:        \"mikado support\"                       null       \n" +
              "  model:       \"class model\"                          root       \n" +
              "  burnDown:    \"burn down charts\"                     root       \n" +
              "  yaml:        \"imput per yaml\"                       root       \n" +
              "  excel:       \"imput per excel\"                      root       \n" +
              "  format:      \"decide on csv variant\"                excel      \n" +
              "  yamlExcel:   \"convert csv to yaml\"                  excel      \n" +
              "  releases:    \"support for releases and sprints\"     root       \n" +
              "                                                                   \n" +
              "- mikadoLog: MikadoLog                                             \n" +
              "  mainGoal: root                                                   \n" +
              "\n" +
              "- LogEntry  goal:       date:                           hoursDone: hoursRemaining: parent:        \n" +
              "  l1:       model       2018-02-28T12:00:00+01:00       1           0              mikadoLog      \n" +
              "  l2:       burnDown    2018-03-01T13:00:00+01:00       2           0              mikadoLog      \n" +
              "  l3:       yaml        2018-03-02T15:12:00+01:00       2           0              mikadoLog      \n" +
              "  l4:       root        2018-03-02T15:16:00+01:00       0.1         0              mikadoLog      \n" +
              "  l5:       format      2018-03-03T15:19:00+01:00       2           0              mikadoLog      \n" +
              "  l6:       yamlExcel   2018-03-04T01:00:00+01:00       2           0              mikadoLog      \n" +
              "  l7:       excel       2018-03-04T01:10:00+01:00       0.1         0              mikadoLog      \n" +
                      "";


      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());

      Goal root = (Goal) idMap.decode(yaml);

      MikadoLog mikadoLog = (MikadoLog) idMap.getObject("mikadoLog");

      story.add(mikadoLog.burnDownChart());

      Goal done = root.clipDone();

      story.addStep("open goals");

      story.addObjectDiagram(root);

      story.addStep("closed goals");
      story.addObjectDiagram(done);


      story.dumpHTML();
   }
}
