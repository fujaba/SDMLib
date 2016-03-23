package org.sdmlib.test.examples.replication.chat;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ReplicationChatModel
{
   @Test
   public void testReplicationChatModel()
   {
      main(null);
   }
   
   public static void main(String[] args)
   {
      Storyboard story = new Storyboard("src/test/java", "ReplicationChatModel");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.replication.chat");
      
      Clazz chatRoot = model.createClazz("ChatRoot");
      
      Clazz chatUser = model.createClazz("ChatUser")
            .withAttribute("userName", DataType.STRING);
      
      chatRoot.withBidirectional(chatUser, "users", Cardinality.MANY, "chatRoot", Cardinality.ONE);
      
      Clazz chatChannel = model.createClazz("ChatChannel");
      
      chatUser.withBidirectional(chatChannel, "channels", Cardinality.MANY, "users", Cardinality.MANY);
      
      Clazz chatMsg = model.createClazz("ChatMsg") 
            .withAttribute("text", DataType.STRING) 
            .withAttribute("time", DataType.LONG)
            .withAttribute("sender", DataType.STRING);
            
      chatChannel.withBidirectional(chatMsg, "msgs", Cardinality.MANY, "channel", Cardinality.ONE);
            
      
      model.generate("src/test/java");
      
      story.addClassDiagram(model);
      
      story.dumpHTML();
   }

}
