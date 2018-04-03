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
    * <canvas id="myChart" width="1000" height="600"></canvas>
    * <script>
    * var ctx = document.getElementById("myChart").getContext('2d');var myLineChart = new Chart(ctx, {
    *     type: 'line',
    *     data: {
    *         datasets: [
    *             {
    *                 label: "Burn Down",
    *                 data: [
    *                     {x: "2018-04-03T12:15:00+01:00", y: 30.5},
    * {x: "2018-04-03T15:00:00+01:00", y: 28.0},
    *                 ]
    *             }
    *         ]
    *     },
    *     options: {
    *         responsive: false,
    *         scales: {
    *             xAxes: [{
    *                 type: "time",
    *                 time: {
    *                     displayFormats: {
    *                        'millisecond': 'DD MMM hh:mm',
    *                        'second': 'DD MMM hh:mm',
    *                        'minute': 'DD MMM hh:mm',
    *                        'hour': 'DD MMM hh:mm',
    *                        'day': 'DD MMM',
    *                        'week': 'DD MMM',
    *                        'month': 'MMM YYYY',
    *                        'quarter': 'MMM YYYY',
    *                        'year': 'YYYY',
    *                     }
    *                 },
    *                 display: true,
    *                 scaleLabel: {
    *                     display: true,
    *                     labelString: 'Date'
    *                 },
    *                 ticks: {
    *                     major: {
    *                         fontStyle: "bold",
    *                         fontColor: "#FF0000"
    *                     }
    *                 }
    *             }],
    *             yAxes: [{
    *                 display: true,
    *                 scaleLabel: {
    *                     display: true,
    *                     labelString: 'hours'
    *                 },
    *                 ticks: {
    *                     beginAtZero: true
    *                 }
    *             }]
    *         }
    *     }});
    * </script>
    * <canvas id="myChart" width="1000" height="600"></canvas>
    * <script>
    * var ctx = document.getElementById("myChart").getContext('2d');var myLineChart = new Chart(ctx, {
    *     type: 'line',
    *     data: {
    *         datasets: [
    *             {
    *                 label: "Burn Down",
    *                 data: [
    *                     {x: "2018-04-03T12:15:00+01:00", y: 30.5},
    * {x: "2018-04-03T15:00:00+01:00", y: 28.0},
    *                 ]
    *             }
    *         ]
    *     },
    *     options: {
    *         responsive: false,
    *         scales: {
    *             xAxes: [{
    *                 type: "time",
    *                 time: {
    *                     displayFormats: {
    *                        'millisecond': 'DD MMM hh:mm',
    *                        'second': 'DD MMM hh:mm',
    *                        'minute': 'DD MMM hh:mm',
    *                        'hour': 'DD MMM hh:mm',
    *                        'day': 'DD MMM',
    *                        'week': 'DD MMM',
    *                        'month': 'MMM YYYY',
    *                        'quarter': 'MMM YYYY',
    *                        'year': 'YYYY',
    *                     }
    *                 },
    *                 display: true,
    *                 scaleLabel: {
    *                     display: true,
    *                     labelString: 'Date'
    *                 },
    *                 ticks: {
    *                     major: {
    *                         fontStyle: "bold",
    *                         fontColor: "#FF0000"
    *                     }
    *                 }
    *             }],
    *             yAxes: [{
    *                 display: true,
    *                 scaleLabel: {
    *                     display: true,
    *                     labelString: 'hours'
    *                 },
    *                 ticks: {
    *                     beginAtZero: true
    *                 }
    *             }]
    *         }
    *     }});
    * </script>
    * <p>Start: open goals</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G1 : Goal",
    *          "attributes":[
    *             "description=JavaDoc Stories",
    *             "hoursDone=0.0",
    *             "hoursTodo=4.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G2 : Goal",
    *          "attributes":[
    *             "description=add gen javadoc methods to storyBaord",
    *             "hoursDone=0.0",
    *             "hoursTodo=4.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G3 : Goal",
    *          "attributes":[
    *             "description=generate javadoc text",
    *             "hoursDone=0.0",
    *             "hoursTodo=4.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G4 : Goal",
    *          "attributes":[
    *             "description=insert javadoc in class",
    *             "hoursDone=0.0",
    *             "hoursTodo=4.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G5 : Goal",
    *          "attributes":[
    *             "description=object diagrams as image",
    *             "hoursDone=0.0",
    *             "hoursTodo=4.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G6 : Goal",
    *          "attributes":[
    *             "description=generate charts as png",
    *             "hoursDone=0.0",
    *             "hoursTodo=4.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G7 : Goal",
    *          "attributes":[
    *             "description=insert Story as TestMethod javadoc",
    *             "hoursDone=0.0",
    *             "hoursTodo=4.0"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G2 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G1 : Goal"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G3 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G1 : Goal"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G4 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G1 : Goal"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"preGoals",
    *             "id":"G5 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G1 : Goal"
    *          }
    *       },
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
    *             "id":"G1 : Goal"
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
    *             "id":"G4 : Goal"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasJavaDocStoriesMikadoPlan3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p><a name = 'step_1'>Step 1: closed goals</a></p><script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G8 : Goal",
    *          "attributes":[
    *             "description=done",
    *             "hoursDone=0.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G9 : Goal",
    *          "attributes":[
    *             "description=replace see ../xy.java by see xy",
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
    *             "id":"G9 : Goal"
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
              "  chartsAsPng:          \"generate charts as png\"                 main       \n" +
              "  \n" +
              "- mikadoLog: MikadoLog                                                        \n" +
              "  mainGoal: main                                                              \n" +
              "  \n" +
              "- LogEntry  goal:              date:                           hoursDone: hoursRemaining: parent:        \n" +
              "  l1:       main               2018-04-03T12:15:00+01:00        0           4              mikadoLog      \n" +
              "  l2:       genSeeRefsAsLinks  2018-04-03T15:00:00+01:00        2.5         0              mikadoLog      \n" +
              "";

      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());

      Goal root = (Goal) idMap.decode(yaml);

      MikadoLog mikadoLog = (MikadoLog) idMap.getObject("mikadoLog");

      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.add(mikadoLog.burnDownChart());

      Goal done = root.clipDone();

      story.addStep("open goals");

      story.addObjectDiagram(root);

      story.addStep("closed goals");
      story.addObjectDiagram(done);


      story.dumpHTML();
   }


   /**
    * <p>Yamler reads and writes simple key value pairs in YAML syntax.</p>
    * <p>Example:</p>
    * <pre>            String yaml = &quot;&quot; +
    *               &quot;msgType: newPlayer\n&quot; +
    *               &quot;login: albert\n&quot; +
    *               &quot;color: blue\n&quot;;
    * 
    *       Yamler yamler = new Yamler();
    *       LinkedHashMap&lt;String, String&gt; map = yamler.decode(yaml);
    * </pre>
    * <pre>{msgType=newPlayer, login=albert, color=blue}</pre>
    */
   @Test
   public void testGenJavaDocStory()
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      story.add("Yamler reads and writes simple key value pairs in YAML syntax.");
      story.add("Example:");
      story.markCodeStart();
      String yaml = "" +
              "msgType: newPlayer\n" +
              "login: albert\n" +
              "color: blue\n";

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

      story.dumpHTML();
   }

}
