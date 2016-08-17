package org.sdmlib.test.examples.modelspace.chat;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelSpaceChatModel
{
     /**
    * 
    * @see <a href='../../../../../../../../../doc/ModelSpaceChatModel.html'>ModelSpaceChatModel.html</a>
*/
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
            
      chatChannel.withBidirectional(chatMsg, "msgs", Cardinality.MANY, "channel", Cardinality.ONE);
      
      Clazz chatGroup = model.createClazz("MSChatGroup")
            .withAttribute("task", DataType.STRING);
      
      Clazz chatMember = model.createClazz("MSChatMember")
            .withAttribute("name", DataType.STRING);
      
      Clazz chatChannelDesc = model.createClazz("MSChatChannelDescription")
            .withAttribute("name", DataType.STRING)
            .withAttribute("location", DataType.STRING);
      
      chatGroup.withBidirectional(chatMember, "members", Cardinality.MANY, "group", Cardinality.ONE);
      
      chatGroup.withBidirectional(chatChannelDesc, "channels", Cardinality.MANY, "group", Cardinality.ONE);
      
      chatMember.withBidirectional(chatChannelDesc, "channels", Cardinality.MANY, "members", Cardinality.MANY);
      
      model.generate("src/test/java");
      
      story.addClassDiagram(model);
      
      story.dumpHTML();
   }
}
