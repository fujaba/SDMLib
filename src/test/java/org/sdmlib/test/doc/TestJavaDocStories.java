package org.sdmlib.test.doc;

import java.util.LinkedHashMap;

import org.junit.Test;
import org.sdmlib.models.YamlIdMap;
import org.sdmlib.models.Yamler;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.MikadoLog;
import org.sdmlib.storyboards.Storyboard;

public class TestJavaDocStories
{
   public static void main(String[] args)
   {
      System.out.println("Hello");
   }



   /**
    *
    * <h3>Storyboard JavaDocStoriesMikadoPlan</h3>
    * <img src='doc-files/JavaDocStoriesMikadoPlanStep0.png' width='880'>
    * <h4><a name = 'step_1'>Step 1: open goals:</a></h4>
    * <img src="doc-files/JavaDocStoriesMikadoPlanStep2.png" alt="JavaDocStoriesMikadoPlanStep2.png" width='765'>
    * <p>object diagrams: {@link Storyboard#addObjectDiagram}</p>
    * <p>generate charts as png: {@link Storyboard#addImage}</p>
    * <h4><a name = 'step_2'>Step 2: closed goals:</a></h4>
    * <img src="doc-files/JavaDocStoriesMikadoPlanStep6.png" alt="JavaDocStoriesMikadoPlanStep6.png" width='1628'>
    */
   @Test
   public void testJavaDocStoriesMikadoPlan()
   {
      String mikadoText = "" +
              "- Goal                  description:                               parents:   \n" +
              "  main:                 \"JavaDoc Stories\"                        null       \n" +
              "  storyBoardExtension:  \"add gen javadoc methods to storyBoard\"  main       \n" +
              "  generateJavaDocText:  \"generate javadoc text\"                  main       \n" +
              "  insertJavaDocInClass: \"insert javadoc in class\"                main       \n" +
              "  insertStoryInTest:    \"insert Story as TestMethod javadoc\"     insertJavaDocInClass       \n" +
              "  genSeeRefsAsLinks:    \"replace see ../xy.java by see xy\"       insertJavaDocInClass       \n" +
              "  graphVizObjectDiags:  \"object diagrams as image via graphviz\"               main       \n" +
              "  codeGen4OtherRole:    \"extend Creator for other role\"               main       \n" +
              "  graphVizClazzDiags:   \"class diagrams as image\"                main       \n" +
              "  graphVizPatternDiags:   \"pattern diagrams as image\"                main       \n" +
              "  chartsAsPng:          \"generate charts as png\"                 main       \n" +
              "  objectTables:         \"have object tables in javadocs\"         main       \n" +
              "  \n" +
              "- mikadoLog: MikadoLog                                                        \n" +
              "  mainGoal: main                                                              \n" +
              "  \n" +
              "- LogEntry  goal:              date:                           hoursDone: hoursRemaining:  parent:        \n" +
              "  l1:       main               2018-04-03T12:15:00+01:00        0           4              mikadoLog      \n" +
              "  l2:       genSeeRefsAsLinks  2018-04-03T15:00:00+01:00        2.5         0              mikadoLog      \n" +
              "  l3:       insertStoryInTest  2018-04-03T20:00:00+01:00        2.5         0              mikadoLog      \n" +
              "  l4:       insertJavaDocInClass  2018-04-03T20:00:00+01:00     1           0              mikadoLog      \n" +
              "  l5:       generateJavaDocText  2018-04-03T20:00:00+01:00      1           0              mikadoLog      \n" +
              "  l6:       storyBoardExtension  2018-04-03T20:00:00+01:00      1           0              mikadoLog      \n" +
              "  l7:       graphVizObjectDiags  2018-04-08T17:00:00+01:00      2           0              mikadoLog      \n" +
              "  l8:       graphVizClazzDiags   2018-04-08T17:10:00+01:00      2           3              mikadoLog      \n" +
              "  l9:       chartsAsPng          2018-04-08T17:20:00+01:00      2           0              mikadoLog      \n" +
              "  l10:      objectTables         2018-04-22T01:47:00+01:00      4           0              mikadoLog      \n" +
              "";

      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());
      Goal root = (Goal) idMap.decode(mikadoText);
      MikadoLog mikadoLog = (MikadoLog) idMap.getObject("mikadoLog");
      Storyboard story = new Storyboard().withDocDirName("doc/internal");
      story.addImage(mikadoLog.burnDownChartPng());
      Goal done = root.clipDone();
      story.addStep("open goals:");
      story.addObjectDiagram(root);
      story.add("object diagrams: {@link " + Storyboard.class.getSimpleName() + "#addObjectDiagram"  +  "}");
      story.add("generate charts as png: {@link " + Storyboard.class.getSimpleName() + "#addImage"  +  "}");
      story.addStep("closed goals:");
      story.addObjectDiagram(done);
      story.dumpHTML();
   }


   /**
    * <p>Storyboard GenJavaDocStory</p>
    * <p>Yamler reads simple key value pairs in YAML syntax.</p>
    * <p>Example:</p>
    * <pre>            String yaml = &quot;&quot; +
    *               &quot;msgType: newPlayer\n&quot; +
    *               &quot;login: albert\n&quot; +
    *               &quot;colors: blue red \n&quot;;
    * 
    *       Yamler yamler = new Yamler();
    *       LinkedHashMap&lt;String, String&gt; map = yamler.decode(yaml);
    * </pre>
    * <pre>{msgType=newPlayer, login=albert, colors=blue red}</pre>
    */
   @Test
   public void testGenJavaDocStory()
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.add("Yamler reads simple key value pairs in YAML syntax.");
      story.add("Example:");
      story.markCodeStart();
      String yaml = "" +
              "msgType: newPlayer\n" +
              "login: albert\n" +
              "colors: blue red \n";

      Yamler yamler = new Yamler();
      LinkedHashMap<String, String> map = yamler.decode(yaml);
      story.addCode();

      story.addPreformatted(map.toString());

      // story.dumpJavaDoc(Yamler.class.getName());
      // story.dumpJavaDoc(Yamler.class.getName(), "decode(String)");

      story.dumpHTML();
   }

}
