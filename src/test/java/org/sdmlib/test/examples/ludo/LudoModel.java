package org.sdmlib.test.examples.ludo;

import java.awt.Point;
import java.util.Date;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Modifier;

public class LudoModel
{
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";
   
   public enum LudoColor
   {
      green, 
      blue, 
      red, 
      yellow
   }
   
     /**
    * 
    * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/examples/ludo/LudoModel.java' type='text/x-java'>LudoModel</a></p>
    * <p>The model: </p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Dice",
    *          "attributes":[
    *             "value : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Field",
    *          "attributes":[
    *             "color : String",
    *             "kind : String",
    *             "point : Point",
    *             "x : int",
    *             "y : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Ludo",
    *          "attributes":[
    *             "date : Date"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Pawn",
    *          "attributes":[
    *             "color : String",
    *             "x : int",
    *             "y : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Player",
    *          "attributes":[
    *             "color : String",
    *             "enumColor : LudoColor",
    *             "name : String",
    *             "x : int",
    *             "y : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Point",
    *          "attributes":[
    *             "x : int",
    *             "y : int"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Dice",
    *             "cardinality":"one",
    *             "property":"dice"
    *          },
    *          "target":{
    *             "id":"Ludo",
    *             "cardinality":"one",
    *             "property":"game"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Dice",
    *             "cardinality":"one",
    *             "property":"dice"
    *          },
    *          "target":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"player"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"base"
    *          },
    *          "target":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"baseowner"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Field",
    *             "cardinality":"many",
    *             "property":"fields"
    *          },
    *          "target":{
    *             "id":"Ludo",
    *             "cardinality":"one",
    *             "property":"game"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"landing"
    *          },
    *          "target":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"entry"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"landing"
    *          },
    *          "target":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"lander"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"next"
    *          },
    *          "target":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"prev"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"pos"
    *          },
    *          "target":{
    *             "id":"Pawn",
    *             "cardinality":"many",
    *             "property":"pawns"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"start"
    *          },
    *          "target":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"starter"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Ludo",
    *             "cardinality":"one",
    *             "property":"game"
    *          },
    *          "target":{
    *             "id":"Player",
    *             "cardinality":"many",
    *             "property":"players"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Ludo",
    *             "cardinality":"one",
    *             "property":"game"
    *          },
    *          "target":{
    *             "id":"Dice",
    *             "cardinality":"one",
    *             "property":"dice"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Ludo",
    *             "cardinality":"one",
    *             "property":"game"
    *          },
    *          "target":{
    *             "id":"Field",
    *             "cardinality":"many",
    *             "property":"fields"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Pawn",
    *             "cardinality":"many",
    *             "property":"pawns"
    *          },
    *          "target":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"player"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Pawn",
    *             "cardinality":"many",
    *             "property":"pawns"
    *          },
    *          "target":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"pos"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"baseowner"
    *          },
    *          "target":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"base"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"lander"
    *          },
    *          "target":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"landing"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"next"
    *          },
    *          "target":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"prev"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"player"
    *          },
    *          "target":{
    *             "id":"Dice",
    *             "cardinality":"one",
    *             "property":"dice"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"player"
    *          },
    *          "target":{
    *             "id":"Pawn",
    *             "cardinality":"many",
    *             "property":"pawns"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Player",
    *             "cardinality":"many",
    *             "property":"players"
    *          },
    *          "target":{
    *             "id":"Ludo",
    *             "cardinality":"one",
    *             "property":"game"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Player",
    *             "cardinality":"one",
    *             "property":"starter"
    *          },
    *          "target":{
    *             "id":"Field",
    *             "cardinality":"one",
    *             "property":"start"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasLudoModelClassDiagram1", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/LudoModel.html'>LudoModel.html</a>
*/
   @Test
   public void testLudoModel()
   {
      Storyboard storyboard = new Storyboard().withDocDirName("doc/internal");
      
      storyboard.add("The model: ");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.ludo.model");
      Clazz ludo = model.createClazz("Ludo").withAttribute("date", DataType.create(Date.class));
      
      Clazz point = model.createClazz(Point.class.getName());
      point.createAttribute("x", DataType.INT).with(Modifier.PUBLIC);
      point.createAttribute("y", DataType.INT).with(Modifier.PUBLIC);
      point.withExternal(true);
      
      Clazz player = model.createClazz("Player")
            .withBidirectional(ludo, "game", Cardinality.ONE, "players", Cardinality.MANY)
            .withAttribute("color", DataType.STRING)
            .withAttribute("enumColor", DataType.create(LudoColor.class))
            .withAttribute("name", DataType.STRING)
            .withAttribute("x", DataType.INT)
            .withAttribute("y", DataType.INT);
      
      player.withBidirectional(player, "next", Cardinality.ONE, "prev", Cardinality.ONE);
      
      Clazz dice = model.createClazz("Dice")
            .withBidirectional(ludo, "game", Cardinality.ONE, "dice", Cardinality.ONE);
      
      dice.withAttribute("value", DataType.INT);
      
      player.withBidirectional(dice, "dice", Cardinality.ONE, "player", Cardinality.ONE);
      
      Clazz field = model.createClazz("Field")
            .withBidirectional(ludo, "game", Cardinality.ONE, "fields", Cardinality.MANY);
      
      field
         .withAttribute("color", DataType.STRING)
         .withAttribute("kind", DataType.STRING)
         .withAttribute("x", DataType.INT)
         .withAttribute("y", DataType.INT)
         .withAttribute("point", DataType.create(point));

     
      field.withBidirectional(field, "next", Cardinality.ONE, "prev", Cardinality.ONE);
      
      field.withBidirectional(field, "landing", Cardinality.ONE, "entry", Cardinality.ONE);
      
      player.withBidirectional(field, "start", Cardinality.ONE, "starter", Cardinality.ONE);
      
      player.withBidirectional(field, "base", Cardinality.ONE, "baseowner", Cardinality.ONE);
      
      player.withBidirectional(field, "landing", Cardinality.ONE, "lander", Cardinality.ONE);
     
      Clazz pawn = model.createClazz("Pawn")
            .withBidirectional(player, "player", Cardinality.ONE, "pawns", Cardinality.MANY);
               
      pawn
         .withAttribute("color", DataType.STRING)
         .withAttribute("x", DataType.INT)
         .withAttribute("y", DataType.INT);

      
      pawn.withBidirectional(field, "pos", Cardinality.ONE, "pawns", Cardinality.MANY);
      
      // model.updateFromCode("examples", "examples", "org.sdmlib.test.examples.ludo");

      // model.insertModelCreationCodeHere("examples");
     
      storyboard.addClassDiagram(model);

      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
      
   }
}

