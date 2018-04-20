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
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/mikado/MikadoProjectPlan.java' type='text/x-java'>ExcelProjectPlan</a></p>
    * <canvas id="myChart" width="880" height="550"></canvas>
    * <script>
    * var ctx = document.getElementById("myChart").getContext('2d');var myLineChart = new Chart(ctx, {
    *     type: 'line',
    *     data: {
    *         datasets: [
    *             {
    *                 label: "Burn Down",
    *                 data: [
    *                     {x: "2018-01-03T12:00:00+01:00", y: 5.1},
    * {x: "2018-01-04T12:00:00+01:00", y: 4.1},
    * {x: "2018-03-03T12:00:00+01:00", y: 3.1},
    * {x: "2018-03-04T00:52:00+01:00", y: 2.1},
    * {x: "2018-03-04T17:22:00+01:00", y: 1.1},
    * {x: "2018-03-04T17:30:00+01:00", y: 1.0},
    *                 ]
    *             }
    *         ]
    *     },
    *     options: {
    *         animation: false,
    *         responsive: false,
    *          animation: {
    *             onComplete: function(animation) {
    *                     java.screendump("42");
    *                     java.close();
    *                 }
    *          },
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
    *             "description=Summer 18 Release",
    *             "hoursDone=0.1",
    *             "hoursTodo=1.0"
    *          ]
    *       }
    *    ],
    *    "edges":null
    * }   ;
    *    json["options"]={"canvasid":"canvasExcelProjectPlan3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p><a name = 'step_1'>Step 1: closed goals</a></p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G2 : Goal",
    *          "attributes":[
    *             "description=done",
    *             "hoursDone=0.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G3 : Goal",
    *          "attributes":[
    *             "description=Mikado planning support",
    *             "hoursDone=2.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G4 : Goal",
    *          "attributes":[
    *             "description=Yaml based id map",
    *             "hoursDone=8.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G5 : Goal",
    *          "attributes":[
    *             "description=Allow editing with Excel CSV",
    *             "hoursDone=2.0",
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
    *             "id":"G3 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G2 : Goal"
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
    *             "id":"G2 : Goal"
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
    *             "id":"G3 : Goal"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasExcelProjectPlan5", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../doc/ExcelProjectPlan.html'>ExcelProjectPlan.html</a>
 */
   @Test
   public void testExcelProjectPlan()
   {
      Storyboard story = new Storyboard();

      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());

      idMap.decodeCSV("doc/projectPlans/SDMLibPlan.csv");

      Goal relst18 = (Goal) idMap.getObject("relst18");

      MikadoLog relst18log = (MikadoLog) idMap.getObject("relst18log");

      story.add(relst18log.burnDownChart());

      Goal done = relst18.clipDone();

      story.addStep("open goals");

      story.addObjectDiagram(relst18);

      story.addStep("closed goals");

      story.addObjectDiagram(done);

      story.dumpHTML();
   }
   
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/mikado/MikadoProjectPlan.java' type='text/x-java'>MikadoProjectPlan</a></p>
    * <canvas id="myChart" width="880" height="550"></canvas>
    * <script>
    * var ctx = document.getElementById("myChart").getContext('2d');var myLineChart = new Chart(ctx, {
    *     type: 'line',
    *     data: {
    *         datasets: [
    *             {
    *                 label: "Burn Down",
    *                 data: [
    *                     {x: "2018-02-28T12:00:00+01:00", y: 10.3},
    * {x: "2018-03-01T13:00:00+01:00", y: 8.3},
    * {x: "2018-03-02T15:12:00+01:00", y: 6.3},
    * {x: "2018-03-02T15:16:00+01:00", y: 6.2},
    * {x: "2018-03-03T15:19:00+01:00", y: 4.2},
    * {x: "2018-03-04T01:00:00+01:00", y: 2.2},
    * {x: "2018-03-04T01:10:00+01:00", y: 2.1},
    * {x: "2018-03-04T16:30:00+01:00", y: 1.6},
    * {x: "2018-03-04T18:30:00+01:00", y: 0.1},
    * {x: "2018-03-04T18:30:00+01:00", y: -0.0},
    *                 ]
    *             }
    *         ]
    *     },
    *     options: {
    *         animation: false,
    *         responsive: false,
    *          animation: {
    *             onComplete: function(animation) {
    *                     java.screendump("42");
    *                     java.close();
    *                 }
    *          },
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
    *             "description=mikado support",
    *             "hoursDone=0.1",
    *             "hoursTodo=0.0"
    *          ]
    *       }
    *    ],
    *    "edges":null
    * }   ;
    *    json["options"]={"canvasid":"canvasMikadoProjectPlan3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p><a name = 'step_1'>Step 1: closed goals</a></p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"G10 : Goal",
    *          "attributes":[
    *             "description=auto import excel file",
    *             "hoursDone=1.5",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G2 : Goal",
    *          "attributes":[
    *             "description=done",
    *             "hoursDone=0.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G3 : Goal",
    *          "attributes":[
    *             "description=class model",
    *             "hoursDone=1.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G4 : Goal",
    *          "attributes":[
    *             "description=burn down charts",
    *             "hoursDone=2.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G5 : Goal",
    *          "attributes":[
    *             "description=imput per yaml",
    *             "hoursDone=2.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G6 : Goal",
    *          "attributes":[
    *             "description=imput per excel",
    *             "hoursDone=0.2",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G7 : Goal",
    *          "attributes":[
    *             "description=support for releases and sprints",
    *             "hoursDone=0.5",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G8 : Goal",
    *          "attributes":[
    *             "description=decide on csv variant",
    *             "hoursDone=2.0",
    *             "hoursTodo=0.0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"G9 : Goal",
    *          "attributes":[
    *             "description=convert csv to yaml",
    *             "hoursDone=2.0",
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
    *             "id":"G3 : Goal"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"goal",
    *             "id":"G2 : Goal"
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
    *             "id":"G2 : Goal"
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
    *             "id":"G2 : Goal"
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
    *             "id":"G2 : Goal"
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
    *             "id":"G2 : Goal"
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
    *             "id":"G6 : Goal"
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
    *             "id":"G6 : Goal"
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
    *             "id":"G6 : Goal"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasMikadoProjectPlan5", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../doc/MikadoProjectPlan.html'>MikadoProjectPlan.html</a>
 */
   @Test
   public void testMikadoProjectPlan()
   {
      Storyboard story = new Storyboard();

      String yaml =
              "- Goal                  description:                             parents:   \n" +
              "  root:                 \"mikado support\"                       null       \n" +
              "  model:                \"class model\"                          root       \n" +
              "  burnDown:             \"burn down charts\"                     root       \n" +
              "  yaml:                 \"imput per yaml\"                       root       \n" +
              "  excel:                \"imput per excel\"                      root       \n" +
              "  format:               \"decide on csv variant\"                excel      \n" +
              "  yamlExcel:            \"convert csv to yaml\"                  excel      \n" +
              "  excelAutoImport:      \"auto import excel file\"               excel      \n" +
              "  releases:             \"support for releases and sprints\"     root       \n" +
              "                                                                            \n" +
              "- mikadoLog: MikadoLog                                                      \n" +
              "  mainGoal: root                                                            \n" +
              "\n" +
              "- LogEntry  goal:              date:                           hoursDone: hoursRemaining: parent:        \n" +
              "  l1:       model              2018-02-28T12:00:00+01:00       1           0              mikadoLog      \n" +
              "  l2:       burnDown           2018-03-01T13:00:00+01:00       2           0              mikadoLog      \n" +
              "  l3:       yaml               2018-03-02T15:12:00+01:00       2           0              mikadoLog      \n" +
              "  l4:       root               2018-03-02T15:16:00+01:00       0.1         0              mikadoLog      \n" +
              "  l5:       format             2018-03-03T15:19:00+01:00       2           0              mikadoLog      \n" +
              "  l6:       yamlExcel          2018-03-04T01:00:00+01:00       2           0              mikadoLog      \n" +
              "  l7:       excel              2018-03-04T01:10:00+01:00       0.1         1              mikadoLog      \n" +
              "  l8:       releases           2018-03-04T16:30:00+01:00       0.5         0              mikadoLog      \n" +
              "  l9:       excelAutoImport    2018-03-04T18:30:00+01:00       1.5         0              mikadoLog      \n" +
              "  l10:      excel              2018-03-04T18:30:00+01:00       0.1         0              mikadoLog      \n" +
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
