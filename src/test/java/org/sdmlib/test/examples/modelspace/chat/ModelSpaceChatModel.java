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
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/modelspace/chat/ModelSpaceChatModel.java' type='text/x-java'>ModelSpaceChatModel</a></p>
    * <p>Chatting via model space, i.e. shared files</p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"MSChatChannel",
    *          "attributes":[
    *             "task : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MSChatChannelDescription",
    *          "attributes":[
    *             "location : String",
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MSChatGroup",
    *          "attributes":[
    *             "task : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MSChatMember",
    *          "attributes":[
    *             "name : String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MSChatMsg",
    *          "attributes":[
    *             "sender : String",
    *             "text : String",
    *             "time : long"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatChannel",
    *             "cardinality":"one",
    *             "property":"channel"
    *          },
    *          "target":{
    *             "id":"MSChatMsg",
    *             "cardinality":"many",
    *             "property":"msgs"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatChannelDescription",
    *             "cardinality":"many",
    *             "property":"channels"
    *          },
    *          "target":{
    *             "id":"MSChatGroup",
    *             "cardinality":"one",
    *             "property":"group"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatChannelDescription",
    *             "cardinality":"many",
    *             "property":"channels"
    *          },
    *          "target":{
    *             "id":"MSChatMember",
    *             "cardinality":"many",
    *             "property":"members"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatGroup",
    *             "cardinality":"one",
    *             "property":"group"
    *          },
    *          "target":{
    *             "id":"MSChatMember",
    *             "cardinality":"many",
    *             "property":"members"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatGroup",
    *             "cardinality":"one",
    *             "property":"group"
    *          },
    *          "target":{
    *             "id":"MSChatChannelDescription",
    *             "cardinality":"many",
    *             "property":"channels"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatMember",
    *             "cardinality":"many",
    *             "property":"members"
    *          },
    *          "target":{
    *             "id":"MSChatGroup",
    *             "cardinality":"one",
    *             "property":"group"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatMember",
    *             "cardinality":"many",
    *             "property":"members"
    *          },
    *          "target":{
    *             "id":"MSChatChannelDescription",
    *             "cardinality":"many",
    *             "property":"channels"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MSChatMsg",
    *             "cardinality":"many",
    *             "property":"msgs"
    *          },
    *          "target":{
    *             "id":"MSChatChannel",
    *             "cardinality":"one",
    *             "property":"channel"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasModelSpaceChatModelClassDiagram1", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
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
