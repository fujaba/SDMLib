package org.sdmlib.models.taskflows;

import org.junit.Test;
import org.sdmlib.storyboards.StoryboardImpl;

public class TaskFlowObjectScenarioForCoverage
{
     /**
    * 
    * <p>Storyboard <a href='./src/main/replication/org/sdmlib/models/taskflows/TaskFlowObjectScenarioForCoverage.java' type='text/x-java'>TaskFlowObjectScenarioForCoverage</a></p>
    * <p>Create some objects just for coverage. This does not serve as an usage example.</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"F1 : FetchFileFlow",
    *          "attributes":[
    *             "fileName=null",
    *             "idMap=null",
    *             "taskNo=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L5 : Logger",
    *          "attributes":[
    *             "idMap=null",
    *             "startPeer=null",
    *             "taskNo=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L6 : LogEntry",
    *          "attributes":[
    *             "nodeName=null",
    *             "taskName=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L7 : LogEntry",
    *          "attributes":[
    *             "nodeName=null",
    *             "parent=null",
    *             "taskName=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L8 : LogEntry",
    *          "attributes":[
    *             "logger=null",
    *             "nodeName=null",
    *             "taskName=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P4 : PeerProxy",
    *          "attributes":[
    *             "idMap=null",
    *             "ip=null",
    *             "port=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : SocketThread",
    *          "attributes":[
    *             "defaultTargetThread=null",
    *             "idMap=null",
    *             "ip=null",
    *             "port=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S3 : SDMTimer"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"children",
    *             "id":"L8 : LogEntry"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"L6 : LogEntry"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"entries",
    *             "id":"L6 : LogEntry"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"logger",
    *             "id":"L5 : Logger"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"entries",
    *             "id":"L7 : LogEntry"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"logger",
    *             "id":"L5 : Logger"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"fileServer",
    *             "id":"P4 : PeerProxy"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"fetchfileflow",
    *             "id":"F1 : FetchFileFlow"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"subFlow",
    *             "id":"L5 : Logger"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"parent",
    *             "id":"F1 : FetchFileFlow"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasTaskFlowObjectScenarioForCoverage2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../doc/TaskFlowObjectScenarioForCoverage.html'>TaskFlowObjectScenarioForCoverage.html</a>
* @see <a href='../../../../../../../doc/TaskFlowObjectScenarioForCoverage.html'>TaskFlowObjectScenarioForCoverage.html</a>
*/
   @Test
   public void testTaskFlowObjectScenarioForCoverage()
   {
      StoryboardImpl story = new StoryboardImpl();
      
      story.add("Create some objects just for coverage. This does not serve as an usage example.");
      
      FetchFileFlow fetchFileFlow = new FetchFileFlow().withFileServer(new PeerProxy());
      
      Logger logger = new Logger();
      
      fetchFileFlow.withSubFlow(logger);
      
      LogEntry logEntry1 = logger.createEntries();
      LogEntry logEntry2 = logger.createEntries();
      
      logEntry1.createChildren();
      
      SocketThread socketThread = new SocketThread();
      
      SDMTimer sdmTimer = new SDMTimer();
      
      story.addObjectDiagram(fetchFileFlow, socketThread, sdmTimer);
      
      story.dumpHTML();
   }
}
