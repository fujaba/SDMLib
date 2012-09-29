package org.sdmlib.examples.chats;

import org.junit.Test;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.model.taskflows.creators.PeerProxySet;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonIdMap;

public class PeerToPeerChatModel
{
   private static final String PEER_TO_PEER_CHAT = "org.sdmlib.examples.chats.PeerToPeerChat";

   @Test
   public void peerToPeerChatModel()
   {
      Scenario scenario = new Scenario("examples");

      ClassModel model = new ClassModel("org.sdmlib.examples.chats");

      Clazz peerToPeerChatClass = model.createClazz(PEER_TO_PEER_CHAT);

      Clazz taskFlowClazz = model.createClazz(TaskFlow.class.getName(), 
         "taskNo", "int")
         .withExternal(true);

      model.createClazz("ChatMessageFlow", 
         "msg", String.class.getSimpleName(),
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");

      model.createClazz("DrawPointFlow",
         "x", "int", "y", "int",
         "r", "int", "g", "int", "b", "int",
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");

      model.createClazz(ClearDrawingFlow.class.getName(), 
         "gui", PEER_TO_PEER_CHAT)
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");
      
      model.createClazz("PeerToPeerChatArgs", 
         "userName", "String",
         "localPort", "int",
         "peerIp", "String",
         "peerPort", "int");
      
      
      model.createClazz("PeerProxy", 
         "ip", "String", 
         "port", "int", 
         "idMap", JsonIdMap.class.getName()).withExternal(true);
      
      model.generate("examples");

      scenario.addImage(model.dumpClassDiag("PeerToPeerChatModel"));

      scenario.dumpHTML();
   }
}
