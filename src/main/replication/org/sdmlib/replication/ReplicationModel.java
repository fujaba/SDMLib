package org.sdmlib.replication;

import java.beans.PropertyChangeEvent;
import java.net.Socket;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

// 
public class ReplicationModel
{
   private static final String CHANGE_HISTORY = "ChangeHistory";
   private static final String REPLICATION_NODE = "ReplicationNode";
   
     /**
    * 
    * @see <a href='../../../../../../doc/MinChangeModel.html'>MinChangeModel.html</a>
*/
   @Test
   public void MinChangeModel()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.replication");
      
      Clazz changeEvent = model.createClazz("ChangeEvent")
            .withAttribute("objectId", DataType.STRING)
            .withAttribute("objectType", DataType.STRING)
            .withAttribute("property", DataType.STRING)
            .withAttribute("newValue", DataType.STRING)
            .withAttribute("oldValue", DataType.STRING)
            .withAttribute("valueType", DataType.STRING)
            .withAttribute("propertyKind", DataType.STRING)
            .withAttribute("changeNo", DataType.STRING)
            .withAttribute("sessionId", DataType.STRING)
            ;
      
      story.addClassDiagram(model);
      
      model.generate("src/main/replication");

      story.dumpHTML();
   }
   
   
     /**
    * 
    * @see <a href='../../../../../../doc/SeppelModel.html'>SeppelModel.html</a>
*/
   @Test
   public void testSeppelModel()
   {
      Storyboard story = new Storyboard();

      ClassModel model = new ClassModel("org.sdmlib.replication");
      
      // seppel spaces
      Clazz thread = model.createClazz(Thread.class.getName()).withExternal(true);

      Clazz seppelSpace = model.createClazz("SeppelSpace")
            .withAttribute("spaceId", DataType.STRING) 
            .withAttribute("history", DataType.create("ChangeEventList"))
            .withAttribute("lastChangeId", DataType.LONG) 
            .withAttribute("javaFXApplication", DataType.BOOLEAN)
            .withSuperClazz(thread);
      
      Clazz seppelSpaceProxy = model.createClazz("SeppelSpaceProxy")
            .withAttribute("spaceId", DataType.STRING)
            .withAttribute("acceptsConnectionRequests", DataType.BOOLEAN)
            .withAttribute("hostName", DataType.STRING)
            .withAttribute("portNo", DataType.INT)
            .withAttribute("loginName", DataType.STRING)
            .withAttribute("password", DataType.STRING);
      
      seppelSpaceProxy.withBidirectional(seppelSpaceProxy, "partners", Cardinality.MANY, "partners", Cardinality.MANY);
      
      Clazz seppelScope = model.createClazz("SeppelScope")
            .withAttribute("scopeName", DataType.STRING);
      
      seppelScope.withBidirectional(seppelScope, "subScopes", Cardinality.MANY, "superScopes", Cardinality.MANY);

      seppelSpaceProxy.withBidirectional(seppelScope, "scopes", Cardinality.MANY, "spaces", Cardinality.MANY);
      
      Clazz object = model.createClazz(Object.class.getName()).withExternal(true);
      
      seppelScope.withUniDirectional(object, "observedObjects", Cardinality.MANY); 

      Clazz seppelChannel = model.createClazz("SeppelChannel")
            .withSuperClazz(thread)
            .withAttribute("socket", DataType.create(Socket.class))
            .withAttribute("loginValidated", DataType.BOOLEAN); 
      
            
      seppelSpaceProxy.withBidirectional(seppelChannel, "channel", Cardinality.ONE, "seppelSpaceProxy", Cardinality.ONE);
      
      Clazz boardTask = model.createClazz("BoardTask");
      
      seppelSpaceProxy.withBidirectional(boardTask, "tasks", Cardinality.MANY, "proxy", Cardinality.ONE);
      
      model.generate("src/main/replication");

      story.addClassDiagram(model);


      story.dumpHTML();
   }

   @Test
   public void testReplicationModel()
   {
      main(null);
   }

   public static void main(String[] args)
   {
      // file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/ReplicationModel.html
      Storyboard storyboard = new Storyboard("src/main/replication", "ReplicationModel");

      ClassModel model = new ClassModel("org.sdmlib.replication");

      Clazz thread = model.createClazz(Thread.class.getName()).withExternal(true);

      Clazz socket = model.createClazz(Socket.class.getName()).withExternal(true);

      Clazz replicationNode = model.createClazz(REPLICATION_NODE);

      Clazz sharedSpace = model.createClazz("SharedSpace")
            .withAttribute("spaceId", DataType.STRING) 
      .withAttribute("history", DataType.create(CHANGE_HISTORY))
      .withAttribute("lastChangeId", DataType.LONG) 
      .withAttribute("nodeId", DataType.STRING) 
      .withAttribute("javaFXApplication", DataType.BOOLEAN)
      .withSuperClazz(thread);

      replicationNode.withBidirectional(sharedSpace, "sharedSpaces", Cardinality.MANY, "node", Cardinality.ONE);
      
      
      Clazz replicationChannel = model.createClazz("ReplicationChannel")
      .withSuperClazz(thread)
      .withAttribute("socket", DataType.create(Socket.class)) 
      .withAttribute("targetNodeId", DataType.STRING); 
      
      sharedSpace.withBidirectional(replicationChannel, "channels", Cardinality.MANY, "sharedSpace", Cardinality.ONE);

      Clazz replicationServer = model.createClazz("ReplicationServer")
         .withSuperClazz(replicationNode);

      Clazz serverSocketAcceptThread = model.createClazz("ServerSocketAcceptThread")
            .withSuperClazz(thread)
            .withAttribute("port", DataType.INT)
            .withAttribute("replicationNode", DataType.create(replicationNode));

      Clazz task = model.createClazz("Task");

      Clazz logEntry = model.createClazz("LogEntry")
            .withAttribute("stepName", DataType.STRING)
            .withAttribute("executedBy", DataType.STRING)
            .withAttribute("timeStamp", DataType.LONG);

      
      task.withBidirectional(logEntry, "logEntries", Cardinality.MANY, "task", Cardinality.ONE);

      Clazz changeHistory = model.createClazz(CHANGE_HISTORY);

      Clazz change = model.createClazz("ReplicationChange")
            .withSuperClazz(task)
            .withAttribute("historyIdPrefix", DataType.STRING)
            .withAttribute("historyIdNumber", DataType.LONG)
            .withAttribute("targetObjectId", DataType.STRING)
            .withAttribute("targetProperty", DataType.STRING)
            .withAttribute("isToManyProperty", DataType.BOOLEAN)
            .withAttribute("changeMsg", DataType.STRING);
   
      changeHistory.withBidirectional(change, "changes", Cardinality.MANY, "history", Cardinality.ONE);
         
      // replicated task flow model
      Clazz remoteTaskBoard = model.createClazz("RemoteTaskBoard");

      Clazz lane = model.createClazz("Lane")
            .withAttribute("name", DataType.STRING);
            
      remoteTaskBoard.withBidirectional(lane, "lanes", Cardinality.MANY, "board", Cardinality.ONE);

      Clazz boardTask = model.createClazz("BoardTask")
            .withSuperClazz(task)
            .withAttribute("name", DataType.STRING)
            .withAttribute("status", DataType.STRING)
            .withAttribute("manualExecution", DataType.BOOLEAN)
            .withAttribute("stashedPropertyChangeEvent", DataType.create(PropertyChangeEvent.class))
            .withMethod("execute", DataType.VOID);
            
      Clazz runnable = model.createClazz("java.lang.Runnable")
            .withExternal(true)
            .enableInterface();
      
      Clazz remoteTask = model.createClazz("RemoteTask")
            .withSuperClazz(task)
            .withSuperClazz(runnable)
            .withAttribute("boardTask", DataType.create(boardTask));
      
      lane.withBidirectional(boardTask, "tasks", Cardinality.MANY, "lane", Cardinality.ONE);
         
      boardTask.withBidirectional(boardTask, "next", Cardinality.MANY, "prev", Cardinality.MANY);

      Clazz replicationRoot = model.createClazz("ReplicationRoot")
            .withAttribute("name", DataType.STRING)
            .withAttribute("applicationObject", DataType.OBJECT);
      
      replicationRoot.withBidirectional(replicationRoot, "kids", Cardinality.MANY, "parent", Cardinality.ONE);

      model.generate("src/main/replication");

      storyboard.addClassDiagram(model);

      storyboard.dumpHTML();
   }
}
