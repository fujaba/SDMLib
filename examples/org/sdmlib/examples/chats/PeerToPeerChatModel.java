package org.sdmlib.examples.chats;

import static org.sdmlib.models.classes.Role.R.ONE;

import org.junit.Test;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class PeerToPeerChatModel
{
   private static final String INT = "int";
   private static final String STRING = "String";
   private static final String PEER_TO_PEER_CHAT = "org.sdmlib.examples.chats.PeerToPeerChat";

   @Test
   public void peerToPeerChatModel()
   {
      Scenario scenario = new Scenario("examples");

      ClassModel model = new ClassModel("org.sdmlib.examples.chats");

      Clazz peerToPeerChatClass = model.createClazz(PEER_TO_PEER_CHAT);

      Clazz taskFlowClass = model.createClazz(TaskFlow.class.getName(), 
         "taskNo", INT,
         "idMap", SDMLibJsonIdMap.class.getName())
         .withExternal(true);
      
      taskFlowClass.withAssoc(taskFlowClass, "subFlow", ONE, "parent", ONE);

      model.createClazz("ChatMessageFlow", 
         "msg", String.class.getSimpleName(),
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");

      model.createClazz("DrawPointFlow",
         "x", INT, "y", INT,
         "r", INT, "g", INT, "b", INT,
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");

      model.createClazz(ClearDrawingFlow.class.getName(), 
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");
      
      model.createClazz("PeerToPeerChatArgs", 
         "userName", STRING,
         "localPort", INT,
         "peerIp", STRING,
         "peerPort", INT);
      
      
      model.createClazz(PeerProxy.class.getName(), 
         "ip", STRING, 
         "port", INT, 
         "idMap", JsonIdMap.class.getName()).withExternal(true);
      
      
      model.createClazz("CSChatMessageFlow", 
         "msg", String.class.getSimpleName())
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");
      
      model.createClazz("ChatServer", 
         "allMessages", STRING, 
         "allPeers", PeerProxySet.class.getName());
      
      model.createClazz(ClientLoginFlow.class.getName(), 
         "server", PeerProxy.class.getName(), 
         "clientIP", STRING, 
         "clientPort", INT, 
         "allMessagesText", STRING)
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");
      
      model.createClazz("CSDrawPointFlow",
         "x", INT, 
         "y", INT,
         "r", INT, 
         "g", INT, 
         "b", INT)
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");

      model.createClazz("CSClearDrawingFlow")
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");
      
      Clazz visitAllClientsFlow = model.createClazz("CSVisitAllClientsFlow")
         .withSuperClass(taskFlowClass);
   
      visitAllClientsFlow.createMethods("run()", "void");
      
      Clazz clientTask = visitAllClientsFlow.createClassAndAssoc("CSClientTask", "tgtTask", R.ONE, "parent", R.ONE)
            .withSuperClass(taskFlowClass);
      
      model.createClazz("CTDrawPoint",
         "x", INT, 
         "y", INT,
         "r", INT, 
         "g", INT, 
         "b", INT)
         .withSuperClass(clientTask);
      
      model.createClazz("CTClearDrawing")
      .withSuperClass(clientTask);
      
      model.createClazz("P2PNetworkLoginFlow", 
         "firstPeer", PeerProxy.class.getName(), 
         "clientPeer", PeerProxy.class.getName(),
         "clientName", STRING,
         "allMessages", STRING,
         "peerList", PeerProxySet.class.getName())
         .withSuperClass(taskFlowClass)
         .withMethods("run()", "void")
         .withMethods("getTaskNames()", "Object[]");
      
      model.createClazz("P2PChatMessageFlow", 
         "msg", STRING, 
         "pos", INT)
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");
         
      scenario.addToDo("we should build attr.remove", Scenario.BACKLOG, "zuendorf", "04.10.2012 12:50:42", 0, 8);
      
      model.generate("examples");

      scenario.addImage(model.dumpClassDiag("examples", "PeerToPeerChatModel"));

      scenario.dumpHTML();
   }
}
