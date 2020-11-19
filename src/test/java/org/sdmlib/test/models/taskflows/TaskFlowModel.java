package org.sdmlib.test.models.taskflows;

import java.util.Timer;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.taskflows.PeerProxy;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.StoryboardImpl;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class TaskFlowModel
{
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/models/taskflows/TaskFlowModel.java' type='text/x-java'>taskFlowModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"FetchFileFlow",
    *          "attributes":[
    *             "fileName : String",
    *             "fileServer : PeerProxy"
    *          ],
    *          "methods":[
    *             "run()"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"LogEntry",
    *          "attributes":[
    *             "nodeName : String",
    *             "taskName : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Logger",
    *          "attributes":[
    *             "startPeer : PeerProxy"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"PeerProxy",
    *          "attributes":[
    *             "idMap : SDMLibJsonIdMap",
    *             "ip : String",
    *             "port : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"SDMTimer"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"SocketThread",
    *          "attributes":[
    *             "defaultTargetThread : Object",
    *             "idMap : SDMLibJsonIdMap",
    *             "ip : String",
    *             "port : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"TaskFlow",
    *          "attributes":[
    *             "idMap : SDMLibJsonIdMap",
    *             "taskNo : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Timer"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"FetchFileFlow",
    *             "cardinality":"one",
    *             "property":"fetchfileflow"
    *          },
    *          "target":{
    *             "id":"TaskFlow",
    *             "cardinality":"one",
    *             "property":"taskflow"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"LogEntry",
    *             "cardinality":"many",
    *             "property":"children"
    *          },
    *          "target":{
    *             "id":"LogEntry",
    *             "cardinality":"one",
    *             "property":"parent"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"LogEntry",
    *             "cardinality":"many",
    *             "property":"entries"
    *          },
    *          "target":{
    *             "id":"Logger",
    *             "cardinality":"one",
    *             "property":"logger"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Logger",
    *             "cardinality":"one",
    *             "property":"logger"
    *          },
    *          "target":{
    *             "id":"LogEntry",
    *             "cardinality":"many",
    *             "property":"entries"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Logger",
    *             "cardinality":"one",
    *             "property":"logger"
    *          },
    *          "target":{
    *             "id":"TaskFlow",
    *             "cardinality":"one",
    *             "property":"taskflow"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"SDMTimer",
    *             "cardinality":"one",
    *             "property":"sdmtimer"
    *          },
    *          "target":{
    *             "id":"Timer",
    *             "cardinality":"one",
    *             "property":"timer"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"TaskFlow",
    *             "cardinality":"one",
    *             "property":"subFlow"
    *          },
    *          "target":{
    *             "id":"TaskFlow",
    *             "cardinality":"one",
    *             "property":"parent"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"FetchFileFlow",
    *             "cardinality":"one",
    *             "property":"fetchfileflow"
    *          },
    *          "target":{
    *             "id":"TaskFlow",
    *             "cardinality":"one",
    *             "property":"taskflow"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Logger",
    *             "cardinality":"one",
    *             "property":"logger"
    *          },
    *          "target":{
    *             "id":"TaskFlow",
    *             "cardinality":"one",
    *             "property":"taskflow"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"SDMTimer",
    *             "cardinality":"one",
    *             "property":"sdmtimer"
    *          },
    *          "target":{
    *             "id":"Timer",
    *             "cardinality":"one",
    *             "property":"timer"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvastaskFlowModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../doc/taskFlowModel.html'>taskFlowModel.html</a>
* @see <a href='../../../../../../../../doc/taskFlowModel.html'>taskFlowModel.html</a>
 */
   @Test
   public void taskFlowModel()
   {
      StoryboardImpl storyboard = new StoryboardImpl();

      ClassModel model = new ClassModel("org.sdmlib.models.taskflows");

      Clazz taskFlowClass = model.createClazz("TaskFlow");
      taskFlowClass.createAttribute("taskNo", DataType.INT);
      taskFlowClass.createAttribute("idMap", DataType.create(SDMLibJsonIdMap.class));

      taskFlowClass.withBidirectional(taskFlowClass, "subFlow", Association.ONE, "parent", Association.ONE);

      Clazz peerProxy = model.createClazz("PeerProxy");
      peerProxy.createAttribute("ip", DataType.STRING);
      peerProxy.createAttribute("port", DataType.INT);
      peerProxy.createAttribute("idMap", DataType.create(SDMLibJsonIdMap.class));

      model.createClazz("SocketThread")
      .withAttribute("ip", DataType.STRING)
      .withAttribute("port", DataType.INT)
      .withAttribute("idMap", DataType.create(SDMLibJsonIdMap.class))
      .withAttribute("defaultTargetThread", DataType.OBJECT);

      model.createClazz("FetchFileFlow")
      .withAttribute("fileServer", DataType.create(PeerProxy.class))
      .withAttribute("fileName", DataType.STRING)
      .withSuperClazz(taskFlowClass)
      .withMethod("run", DataType.VOID);

      Clazz loggerClazz = model.createClazz("Logger")
            .withAttribute("startPeer", DataType.create(PeerProxy.class))
            .withSuperClazz(taskFlowClass);

      Clazz logEntryClass = model.createClazz("LogEntry")
            .withAttribute("nodeName", DataType.STRING)
            .withAttribute("taskName", DataType.STRING);
            
      loggerClazz.withBidirectional(logEntryClass, "entries", Association.MANY, "logger", Association.ONE);

      logEntryClass.withBidirectional(logEntryClass, "children", Association.MANY, "parent", Association.ONE);

      Clazz timerClass = model.createClazz(Timer.class.getName())
            .withExternal(true);

      model.createClazz("SDMTimer")
      .withSuperClazz(timerClass);
      // .withMethod("schedule", DataType.VOID, new Parameter(DataType.create(TimerTask.class)));

      storyboard.addClassDiagram(model);

      model.generate("src/main/replication");

      storyboard.dumpHTML();
   }

}
