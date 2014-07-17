package org.sdmlib.replication;

import java.net.Socket;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Storyboard;

// 
public class ReplicationModel
{
   private static final String CHANGE_HISTORY = "ChangeHistory";
   private static final String REPLICATION_NODE = "ReplicationNode";

   @Test
   public void testReplicationModel()
   {
      main(null);
   }

   public static void main(String[] args)
   {
      // file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/ReplicationModel.html
      Storyboard storyboard = new Storyboard("replicationsrc", "ReplicationModel");

      ClassModel model = new ClassModel("org.sdmlib.replication");

      Clazz thread = model.createClazz(Thread.class.getName()).withExternal(true);

      Clazz socket = model.createClazz(Socket.class.getName()).withExternal(true);

      Clazz replicationNode = model.createClazz(REPLICATION_NODE);

      Clazz sharedSpace = model.createClazz("SharedSpace")
            .withAttribute("spaceId", DataType.STRING) 
      .withAttribute("history", DataType.ref(CHANGE_HISTORY))
      .withAttribute("lastChangeId", DataType.LONG) 
      .withAttribute("nodeId", DataType.STRING) 
      .withAttribute("javaFXApplication", DataType.BOOLEAN)
      .withSuperClazz(thread);

      replicationNode.withAssoc(sharedSpace, "sharedSpaces", Card.MANY, "node", Card.ONE);
      
      
      Clazz replicationChannel = model.createClazz("ReplicationChannel")
      .withSuperClazz(thread)
      .withAttribute("socket", DataType.ref(Socket.class)) 
      .withAttribute("targetNodeId", DataType.STRING); 
      
      sharedSpace.withAssoc(replicationChannel, "channels", Card.MANY, "sharedSpace", Card.ONE);

      Clazz replicationServer = model.createClazz("ReplicationServer")
         .withSuperClazz(replicationNode);

      Clazz serverSocketAcceptThread = model.createClazz("ServerSocketAcceptThread")
            .withSuperClazz(thread)
            .withAttribute("port", DataType.INT)
            .withAttribute("replicationNode", DataType.ref(replicationNode));

      Clazz task = model.createClazz("Task");

      Clazz logEntry = model.createClazz("LogEntry")
            .withAttribute("stepName", DataType.STRING)
            .withAttribute("executedBy", DataType.STRING)
            .withAttribute("timeStamp", DataType.LONG);

      
      task.withAssoc(logEntry, "logEntries", Card.MANY, "task", Card.ONE);

      Clazz changeHistory = model.createClazz(CHANGE_HISTORY);

      Clazz change = model.createClazz("ReplicationChange")
            .withSuperClazz(task)
            .withAttribute("historyIdPrefix", DataType.STRING)
            .withAttribute("historyIdNumber", DataType.LONG)
            .withAttribute("targetObjectId", DataType.STRING)
            .withAttribute("targetProperty", DataType.STRING)
            .withAttribute("isToManyProperty", DataType.BOOLEAN)
            .withAttribute("changeMsg", DataType.STRING);
   
      changeHistory.withAssoc(change, "changes", Card.MANY, "history", Card.ONE);
         
      // replicated task flow model
      Clazz remoteTaskBoard = model.createClazz("RemoteTaskBoard");

      Clazz lane = model.createClazz("Lane")
            .withAttribute("name", DataType.STRING);
            
      remoteTaskBoard.withAssoc(lane, "lanes", Card.MANY, "board", Card.ONE);

      Clazz boardTask = model.createClazz("BoardTask")
            .withSuperClazz(task)
            .withAttribute("name", DataType.STRING)
            .withAttribute("status", DataType.STRING);
            
      lane.withAssoc(boardTask, "tasks", Card.MANY, "lane", Card.ONE);
         
      boardTask.withAssoc(boardTask, "next", Card.MANY, "prev", Card.MANY);

      Clazz replicationRoot = model.createClazz("ReplicationRoot")
            .withAttribute("name", DataType.STRING)
            .withAttribute("applicationObject", DataType.OBJECT);
      
      replicationRoot.withAssoc(replicationRoot, "kids", Card.MANY, "parent", Card.ONE);
      
      model.generate("src/main/replication");

      storyboard.addClassDiagram(model, "src/main/replication");

      storyboard.dumpHTML();
   }
}
