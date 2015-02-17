package org.sdmlib.examples.replication.chat;

import java.net.Socket;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Storyboard;

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
      
      ClassModel model = new ClassModel("org.sdmlib.examples.replication.chat");
      
      Clazz chatRoot = model.createClazz("ChatRoot");
      
      Clazz chatUser = model.createClazz("ChatUser")
            .withAttribute("userName", DataType.STRING);
      
      chatRoot.withAssoc(chatUser, "users", Card.MANY, "chatRoot", Card.ONE);
      
      Clazz chatChannel = model.createClazz("ChatChannel");
      
      chatUser.withAssoc(chatChannel, "channels", Card.MANY, "users", Card.MANY);
      
      Clazz chatMsg = model.createClazz("ChatMsg") 
            .withAttribute("text", DataType.STRING) 
            .withAttribute("time", DataType.LONG)
            .withAttribute("sender", DataType.STRING);
            
      chatChannel.withAssoc(chatMsg, "msgs", Card.MANY, "channel", Card.ONE);
            
      
      model.generate("src/test/java");
      
      story.addClassDiagram(model);
      
      story.dumpHTML();
   }

}
