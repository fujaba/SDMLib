package org.sdmlib.test.examples.gofpattern;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.Parameter;

public class StrategyModel
{
     /**
    * 
    * <p>Storyboard GofStrategyModel</p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Blast"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"BombermanPlayer",
    *          "attributes":[
    *             "lastKey : char",
    *             "numberOfBombs : int",
    *             "shortTest : short",
    *             "xPosition : int",
    *             "yPosition : int"
    *          ],
    *          "methods":[
    *             "keyPress(String key)"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"BombermanStrategy",
    *          "methods":[
    *             "handleMove()"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MoveDown"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MoveLeft"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MoveRight"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MoveUp"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Stay"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Blast",
    *             "cardinality":"one",
    *             "property":"blast"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"successor"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveUp",
    *             "cardinality":"one",
    *             "property":"moveup"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveDown",
    *             "cardinality":"one",
    *             "property":"movedown"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveLeft",
    *             "cardinality":"one",
    *             "property":"moveleft"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveRight",
    *             "cardinality":"one",
    *             "property":"moveright"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Blast",
    *             "cardinality":"one",
    *             "property":"blast"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Stay",
    *             "cardinality":"one",
    *             "property":"stay"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveDown",
    *             "cardinality":"one",
    *             "property":"movedown"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveLeft",
    *             "cardinality":"one",
    *             "property":"moveleft"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveRight",
    *             "cardinality":"one",
    *             "property":"moveright"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"MoveUp",
    *             "cardinality":"one",
    *             "property":"moveup"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Stay",
    *             "cardinality":"one",
    *             "property":"stay"
    *          },
    *          "target":{
    *             "id":"BombermanStrategy",
    *             "cardinality":"one",
    *             "property":"bombermanstrategy"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasGofStrategyModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/GofStrategyModel.html'>GofStrategyModel.html</a>
*/
   @Test
   public void GofStrategyModel() 
   {
      Storyboard story = new Storyboard();
      
      ClassModel cm = new ClassModel("org.sdmlib.test.examples.gofpattern.strategy");

      Clazz bPlayer = cm
            .createClazz("BombermanPlayer")
            .withAttribute("xPosition", DataType.INT)
            .withAttribute("yPosition", DataType.INT)
            .withAttribute("numberOfBombs", DataType.INT)
            .withAttribute("lastKey", DataType.CHAR)
            .withAttribute("shortTest", DataType.create("short"))
            .withMethod("keyPress", DataType.VOID,
               new Parameter(DataType.STRING).with("key"));

      Clazz bStrategy = cm.createClazz("BombermanStrategy")
            .with(Modifier.ABSTRACT).withMethod("handleMove", DataType.VOID);

      cm.createClazz("MoveUp").withSuperClazz(bStrategy);
      cm.createClazz("MoveDown").withSuperClazz(bStrategy);
      cm.createClazz("MoveLeft").withSuperClazz(bStrategy);
      cm.createClazz("MoveRight").withSuperClazz(bStrategy);
      cm.createClazz("Blast").withSuperClazz(bStrategy);
      cm.createClazz("Stay").withSuperClazz(bStrategy);

      bStrategy.withUniDirectional(bStrategy, "successor", Cardinality.ONE);
      // bStrategy.withBidirectional(bStrategy, "suc", Cardinality.ONE, "pre", Cardinality.ONE);

      // cm.removeAllGeneratedCode("src/test/java");
      cm.setAuthorName("zuendorf");
      cm.generate("src/test/java");
      
      story.addClassDiagram(cm);
      
      story.dumpHTML();
   }


}
