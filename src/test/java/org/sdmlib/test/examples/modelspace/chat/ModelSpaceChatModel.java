package org.sdmlib.test.examples.modelspace.chat;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Storyboard;

public class ModelSpaceChatModel
{
   @Test
   public void testModelSpaceChatModel() throws Exception
   {
      Storyboard story = new Storyboard();
      
      story.add("Chatting via model space, i.e. shared files");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.modelspace.chat");

      Clazz chatChannel = model.createClazz("MSChatChannel")
            .withAttribute("task", DataType.STRING);
      
      Clazz chatMsg = model.createClazz("MSChatMsg") 
            .withAttribute("text", DataType.STRING) 
            .withAttribute("time", DataType.LONG)
            .withAttribute("sender", DataType.STRING);
            
      chatChannel.withAssoc(chatMsg, "msgs", Card.MANY, "channel", Card.ONE);
      
      Clazz chatGroup = model.createClazz("MSChatGroup")
            .withAttribute("task", DataType.STRING);
      
      Clazz chatMember = model.createClazz("MSChatMember")
            .withAttribute("name", DataType.STRING);
      
      Clazz chatChannelDesc = model.createClazz("MSChatChannelDescription")
            .withAttribute("name", DataType.STRING)
            .withAttribute("location", DataType.STRING);
      
      chatGroup.withAssoc(chatMember, "members", Card.MANY, "group", Card.ONE);
      
      chatGroup.withAssoc(chatChannelDesc, "channels", Card.MANY, "group", Card.ONE);
      
      chatMember.withAssoc(chatChannelDesc, "channels", Card.MANY, "members", Card.MANY);
      
      model.generate("src/test/java");
      
      story.addClassDiagram(model);
      
      story.dumpHTML();
   }
}
