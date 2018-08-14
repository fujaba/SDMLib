package org.sdmlib.models.taskflows;

import org.junit.Test;
import org.sdmlib.storyboards.StoryboardImpl;

public class TaskFlowObjectScenarioForCoverage
{


   /**
    * 
    * <p>Storyboard TaskFlowObjectScenarioForCoverage</p>
    * <p>Create some objects just for coverage. This does not serve as an usage example.</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"class",
    *          "id":"F1 : FetchFileFlow",
    *          "attributes":[
    *             "fileName=null",
    *             "idMap=null",
    *             "taskNo=0"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"L5 : Logger",
    *          "attributes":[
    *             "idMap=null",
    *             "startPeer=null",
    *             "taskNo=0"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"L6 : LogEntry",
    *          "attributes":[
    *             "nodeName=null",
    *             "taskName=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"L7 : LogEntry",
    *          "attributes":[
    *             "nodeName=null",
    *             "parent=null",
    *             "taskName=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"L8 : LogEntry",
    *          "attributes":[
    *             "logger=null",
    *             "nodeName=null",
    *             "taskName=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"P4 : PeerProxy",
    *          "attributes":[
    *             "idMap=null",
    *             "ip=null",
    *             "port=0"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"S2 : SocketThread",
    *          "attributes":[
    *             "defaultTargetThread=null",
    *             "idMap=null",
    *             "ip=null",
    *             "port=0"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"S3 : SDMTimer"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"children",
    *             "cardinality":"many",
    *             "id":"L8 : LogEntry"
    *          },
    *          "target":{
    *             "property":"parent",
    *             "cardinality":"one",
    *             "id":"L6 : LogEntry"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"entries",
    *             "cardinality":"many",
    *             "id":"L6 : LogEntry"
    *          },
    *          "target":{
    *             "property":"logger",
    *             "cardinality":"one",
    *             "id":"L5 : Logger"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"entries",
    *             "cardinality":"many",
    *             "id":"L7 : LogEntry"
    *          },
    *          "target":{
    *             "property":"logger",
    *             "cardinality":"one",
    *             "id":"L5 : Logger"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "property":"fileServer",
    *             "cardinality":"one",
    *             "id":"P4 : PeerProxy"
    *          },
    *          "target":{
    *             "property":"fetchfileflow",
    *             "cardinality":"one",
    *             "id":"F1 : FetchFileFlow"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"subFlow",
    *             "cardinality":"one",
    *             "id":"L5 : Logger"
    *          },
    *          "target":{
    *             "property":"parent",
    *             "cardinality":"one",
    *             "id":"F1 : FetchFileFlow"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasTaskFlowObjectScenarioForCoverage2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
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
