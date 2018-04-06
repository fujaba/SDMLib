package org.sdmlib.test.doc;

import org.junit.Test;
import org.sdmlib.models.YamlIdMap;
import org.sdmlib.models.Yamler;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.MikadoLog;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

import java.util.LinkedHashMap;

public class TestJavaDocStories
{
   /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java' type='text/x-java'>JavaDocStoriesMikadoPlan</a></p>
    * <p>Hello Story</p><img src="doc-files/JavaDocStoriesMikadoPlanStep0.png"></img><p>Start: open goals</p>
    * <p>Hello Story</p><img src="doc-files/JavaDocStoriesMikadoPlanStep1.png"></img><p><a name = 'step_1'>Step 1: closed goals</a></p><script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G10 : Goal",
    *          "attributes":[
    *             "description=replace see ../xy.java by see xy",
    *             "hoursDone=2.5",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G5 : Goal",
    *          "attributes":[
    *             "description=done",
    *             "hoursDone=0.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G6 : Goal",
    *          "attributes":[
    *             "description=add gen javadoc methods to storyBaord",
    *             "hoursDone=1.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G7 : Goal",
    *          "attributes":[
    *             "description=generate javadoc text",
    *             "hoursDone=1.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G8 : Goal",
    *          "attributes":[
    *             "description=insert javadoc in class",
    *             "hoursDone=1.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G9 : Goal",
    *          "attributes":[
    *             "description=insert Story as TestMethod javadoc",
    *             "hoursDone=2.5",
    *             "hoursTodo=0.0"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G6 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G5 : Goal"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G7 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G5 : Goal"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G8 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G5 : Goal"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G9 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G8 : Goal"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G10 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G8 : Goal"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasJavaDocStoriesMikadoPlan5", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    */
   @Test
   public void testJavaDocStoriesMikadoPlan()
   {
      String yaml = "" +
              "- Goal                  description:                               parents:   \n" +
              "  main:                 \"JavaDoc Stories\"                        null       \n" +
              "  storyBoardExtension:  \"add gen javadoc methods to storyBaord\"  main       \n" +
              "  generateJavaDocText:  \"generate javadoc text\"                  main       \n" +
              "  insertJavaDocInClass: \"insert javadoc in class\"                main       \n" +
              "  insertStoryInTest:    \"insert Story as TestMethod javadoc\"     insertJavaDocInClass       \n" +
              "  genSeeRefsAsLinks:    \"replace see ../xy.java by see xy\"       insertJavaDocInClass       \n" +
              "  graphVizObjectDiags:  \"object diagrams as image\"               main       \n" +
              "  graphVizClazzDiags:   \"object diagrams as image\"               main       \n" +
              "  chartsAsPng:          \"generate charts as png\"                 main       \n" +
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
              "";

      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());
      Goal root = (Goal) idMap.decode(yaml);
      MikadoLog mikadoLog = (MikadoLog) idMap.getObject("mikadoLog");
      Storyboard story = new Storyboard().withDocDirName("doc/internal");
      story.addAsImage("<h1>look at this</h1>\n" + mikadoLog.burnDownChart());
      Goal done = root.clipDone();
      story.addStep("open goals");
      story.addObjectDiagramAsImage(root);
      story.addStep("closed goals");
      story.addObjectDiagram(done);
      story.dumpHTML();

      try
      {
         Thread.sleep(10 * 1000);
      } catch (InterruptedException e)
      {
         e.printStackTrace();
      }
   }


   /**
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

      TestJavaDocValidator self = new TestJavaDocValidator();

      //      University uni = new University();
      //      uni.createRooms().withName("Math").withCredits(42);
      //      story.addObjectDiagram(uni);

      self.hello();

      story.addPreformatted(map.toString());

      story.dumpJavaDoc(Yamler.class.getName());
      story.dumpJavaDoc(Yamler.class.getName(), "decode(String)");

      story.dumpHTML();
   }

}
