package org.sdmlib.replication;

import org.junit.Test;
import org.sdmlib.storyboards.StoryboardImpl;

public class ReplicationObjectScenarioForCoverage
{

   /**
    * 
    * <p>Storyboard ReplicationObjectScenarioForCoverage</p>
    * <p>Create some objects just for coverage. This does not serve as an usage example.</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"class",
    *          "id":"B12 : BoardTask",
    *          "attributes":[
    *             "manualExecution=false",
    *             "name=firstTask",
    *             "proxy=null",
    *             "source=null",
    *             "stashedPropertyChangeEvent=java.beans.PropertyChangeEvent[propertyName=lane; oldValue=null; newValue=todo; propagationId=null; source=firstTask null]",
    *             "status=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"B13 : BoardTask",
    *          "attributes":[
    *             "manualExecution=false",
    *             "name=secondTask",
    *             "proxy=null",
    *             "source=null",
    *             "stashedPropertyChangeEvent=java.beans.PropertyChangeEvent[propertyName=lane; oldValue=null; newValue=todo; propagationId=null; source=secondTask null]",
    *             "status=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"C11 : ChangeHistory"
    *       },
    *       {
    *          "type":"class",
    *          "id":"C6 : ChangeHistory"
    *       },
    *       {
    *          "type":"class",
    *          "id":"L14 : LogEntry",
    *          "attributes":[
    *             "executedBy=null",
    *             "stepName=null",
    *             "timeStamp=0"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"L8 : Lane",
    *          "attributes":[
    *             "name=todo"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"R1 : ReplicationNode",
    *          "attributes":[
    *             "history=null",
    *             "javaFXApplication=false",
    *             "lastChangeId=0",
    *             "nodeId=null",
    *             "spaceId=coverage"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"R10 : ReplicationChannel",
    *          "attributes":[
    *             "socket=null",
    *             "targetNodeId=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"R2 : ReplicationServer",
    *          "attributes":[
    *             "history=null",
    *             "javaFXApplication=false",
    *             "lastChangeId=0",
    *             "nodeId=null",
    *             "spaceId=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"R4 : RemoteTaskBoard"
    *       },
    *       {
    *          "type":"class",
    *          "id":"R5 : ReplicationRoot",
    *          "attributes":[
    *             "applicationObject=null",
    *             "name=null",
    *             "parent=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"R9 : ReplicationChange",
    *          "attributes":[
    *             "changeMsg={}",
    *             "historyIdNumber=0",
    *             "historyIdPrefix=42",
    *             "isToManyProperty=false",
    *             "targetObjectId=null",
    *             "targetProperty=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"S3 : ServerSocketAcceptThread",
    *          "attributes":[
    *             "port=0",
    *             "replicationNode=null"
    *          ]
    *       },
    *       {
    *          "type":"class",
    *          "id":"S7 : SharedSpace",
    *          "attributes":[
    *             "javaFXApplication=false",
    *             "lastChangeId=0",
    *             "nodeId=null",
    *             "socket=null",
    *             "spaceId=null",
    *             "targetNodeId=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "property":"changes",
    *             "cardinality":"many",
    *             "id":"R9 : ReplicationChange"
    *          },
    *          "target":{
    *             "property":"changehistory",
    *             "cardinality":"one",
    *             "id":"C6 : ChangeHistory"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"channels",
    *             "cardinality":"many",
    *             "id":"R10 : ReplicationChannel"
    *          },
    *          "target":{
    *             "property":"sharedSpace",
    *             "cardinality":"one",
    *             "id":"S7 : SharedSpace"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "property":"history",
    *             "cardinality":"one",
    *             "id":"C11 : ChangeHistory"
    *          },
    *          "target":{
    *             "property":"sharedspace",
    *             "cardinality":"one",
    *             "id":"S7 : SharedSpace"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"lanes",
    *             "cardinality":"many",
    *             "id":"L8 : Lane"
    *          },
    *          "target":{
    *             "property":"board",
    *             "cardinality":"one",
    *             "id":"R4 : RemoteTaskBoard"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"logEntries",
    *             "cardinality":"many",
    *             "id":"L14 : LogEntry"
    *          },
    *          "target":{
    *             "property":"task",
    *             "cardinality":"one",
    *             "id":"B12 : BoardTask"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"next",
    *             "cardinality":"many",
    *             "id":"B13 : BoardTask"
    *          },
    *          "target":{
    *             "property":"prev",
    *             "cardinality":"many",
    *             "id":"B12 : BoardTask"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"sharedSpaces",
    *             "cardinality":"many",
    *             "id":"S7 : SharedSpace"
    *          },
    *          "target":{
    *             "property":"node",
    *             "cardinality":"one",
    *             "id":"R1 : ReplicationNode"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"tasks",
    *             "cardinality":"many",
    *             "id":"B12 : BoardTask"
    *          },
    *          "target":{
    *             "property":"lane",
    *             "cardinality":"one",
    *             "id":"L8 : Lane"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "property":"tasks",
    *             "cardinality":"many",
    *             "id":"B13 : BoardTask"
    *          },
    *          "target":{
    *             "property":"lane",
    *             "cardinality":"one",
    *             "id":"L8 : Lane"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReplicationObjectScenarioForCoverage2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    */
   @Test
   public void testReplicationObjectScenarioForCoverage()
   {
      StoryboardImpl story = new StoryboardImpl();
      
      story.add("Create some objects just for coverage. This does not serve as an usage example.");
      
      ReplicationNode replicationNode = new ReplicationNode().withSpaceId("coverage");
      
      SharedSpace sharedSpace = replicationNode.createSharedSpaces();
      
      sharedSpace.createChannels();
      
      ReplicationServer replicationServer = new ReplicationServer();
      
      ServerSocketAcceptThread serverSocketAcceptThread = new ServerSocketAcceptThread();
      
      RemoteTaskBoard remoteTaskBoard = new RemoteTaskBoard();
      
      Lane lane = remoteTaskBoard.createLanes().withName("todo");
      
      BoardTask task1 = lane.createTask("firstTask");
      BoardTask task2 = lane.createTask("secondTask");
      task1.withNext(task2);
      
      
      LogEntry logEntry = task1.createLogEntries();
      
      ReplicationRoot replicationRoot = new ReplicationRoot();
      
      ChangeHistory changeHistory = new ChangeHistory();
      
      changeHistory.createChanges();
      
      story.addObjectDiagram(replicationNode, replicationServer, serverSocketAcceptThread, remoteTaskBoard, 
         replicationRoot, changeHistory);
      
      story.dumpHTML();
   }
}
