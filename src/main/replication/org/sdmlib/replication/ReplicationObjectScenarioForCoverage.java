package org.sdmlib.replication;

import org.junit.Test;
import org.sdmlib.storyboards.StoryboardImpl;

public class ReplicationObjectScenarioForCoverage
{
     /**
    * 
    * <p>Storyboard <a href='./src/main/replication/org/sdmlib/replication/ReplicationObjectScenarioForCoverage.java' type='text/x-java'>ReplicationObjectScenarioForCoverage</a></p>
    * <p>Create some objects just for coverage. This does not serve as an usage example.</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
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
    *          "type":"clazz",
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
    *          "type":"clazz",
    *          "id":"C11 : ChangeHistory"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"C6 : ChangeHistory"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L14 : LogEntry",
    *          "attributes":[
    *             "executedBy=null",
    *             "stepName=null",
    *             "timeStamp=0"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"L8 : Lane",
    *          "attributes":[
    *             "name=todo"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
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
    *          "type":"clazz",
    *          "id":"R10 : ReplicationChannel",
    *          "attributes":[
    *             "socket=null",
    *             "targetNodeId=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
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
    *          "type":"clazz",
    *          "id":"R4 : RemoteTaskBoard"
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : ReplicationRoot",
    *          "attributes":[
    *             "applicationObject=null",
    *             "name=null",
    *             "parent=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
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
    *          "type":"clazz",
    *          "id":"S3 : ServerSocketAcceptThread",
    *          "attributes":[
    *             "port=0",
    *             "replicationNode=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
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
    *             "cardinality":"many",
    *             "property":"changes",
    *             "id":"R9 : ReplicationChange"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"changehistory",
    *             "id":"C6 : ChangeHistory"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"channels",
    *             "id":"R10 : ReplicationChannel"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"sharedSpace",
    *             "id":"S7 : SharedSpace"
    *          }
    *       },
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"history",
    *             "id":"C11 : ChangeHistory"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"sharedspace",
    *             "id":"S7 : SharedSpace"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"lanes",
    *             "id":"L8 : Lane"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"board",
    *             "id":"R4 : RemoteTaskBoard"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"logEntries",
    *             "id":"L14 : LogEntry"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"task",
    *             "id":"B12 : BoardTask"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"next",
    *             "id":"B13 : BoardTask"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"prev",
    *             "id":"B12 : BoardTask"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"sharedSpaces",
    *             "id":"S7 : SharedSpace"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"node",
    *             "id":"R1 : ReplicationNode"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"tasks",
    *             "id":"B12 : BoardTask"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"lane",
    *             "id":"L8 : Lane"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"tasks",
    *             "id":"B13 : BoardTask"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"lane",
    *             "id":"L8 : Lane"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasReplicationObjectScenarioForCoverage2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../doc/ReplicationObjectScenarioForCoverage.html'>ReplicationObjectScenarioForCoverage.html</a>
* @see <a href='../../../../../../doc/ReplicationObjectScenarioForCoverage.html'>ReplicationObjectScenarioForCoverage.html</a>
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
