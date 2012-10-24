package org.sdmlib.examples.chats;

import org.junit.Test;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
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

      Clazz taskFlowClazz = model.createClazz(TaskFlow.class.getName(), 
         "taskNo", INT)
         .withExternal(true);

      model.createClazz("ChatMessageFlow", 
         "msg", String.class.getSimpleName(),
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");

      model.createClazz("DrawPointFlow",
         "x", INT, "y", INT,
         "r", INT, "g", INT, "b", INT,
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");

      model.createClazz(ClearDrawingFlow.class.getName(), 
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClazz)
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
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");
      
      model.createClazz("ChatServer", 
         "allMessages", STRING, 
         "allPeers", PeerProxySet.class.getName());
      
      model.createClazz(ClientLoginFlow.class.getName(), 
         "server", PeerProxy.class.getName(), 
         "clientIP", STRING, 
         "clientPort", INT, 
         "allMessagesText", STRING)
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");
      
      model.createClazz("CSDrawPointFlow",
         "x", INT, 
         "y", INT,
         "r", INT, 
         "g", INT, 
         "b", INT)
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");

      model.createClazz("CSClearDrawingFlow")
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");
      
      Clazz visitAllClientsFlow = model.createClazz("CSVisitAllClientsFlow")
         .withSuperClass(taskFlowClazz);
   
      visitAllClientsFlow.createMethods("run()", "void");
      
      Clazz clientTask = visitAllClientsFlow.createClassAndAssoc("CSClientTask", "tgtTask", R.ONE, "parent", R.ONE)
            .withSuperClass(taskFlowClazz);
      
      model.createClazz("CTDrawPoint",
         "x", INT, 
         "y", INT,
         "r", INT, 
         "g", INT, 
         "b", INT)
         .withSuperClass(clientTask);
      
      model.createClazz("CTClearDrawing")
      .withSuperClass(clientTask);
      
      scenario.addToDo("we should build attr.remove", Scenario.BACKLOG, "zuendorf", "04.10.2012 12:50:42", 0, 8);
      
      model.generate("examples");

      scenario.addImage(model.dumpClassDiag("examples", "PeerToPeerChatModel"));

      scenario.dumpHTML();
   }
}
