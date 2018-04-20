package org.sdmlib.test.examples.reachabilitygraphs;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.AssociationTypes;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class SokobanModel
{
     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanModel.java' type='text/x-java'>SokobanModelGen</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"AKarli"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Box"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Karli"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Maze",
    *          "attributes":[
    *             "height : int",
    *             "width : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Sokoban"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Tile",
    *          "attributes":[
    *             "goal : boolean",
    *             "wall : boolean",
    *             "x : int",
    *             "y : int"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"AKarli",
    *             "cardinality":"one",
    *             "property":"akarli"
    *          },
    *          "target":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"many",
    *             "property":"tiles"
    *          },
    *          "target":{
    *             "id":"AKarli",
    *             "cardinality":"one",
    *             "property":"akarli"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Box",
    *             "cardinality":"many",
    *             "property":"boxes"
    *          },
    *          "target":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"one",
    *             "property":"tile"
    *          },
    *          "target":{
    *             "id":"Box",
    *             "cardinality":"one",
    *             "property":"box"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Karli",
    *             "cardinality":"one",
    *             "property":"karli"
    *          },
    *          "target":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"one",
    *             "property":"tile"
    *          },
    *          "target":{
    *             "id":"Karli",
    *             "cardinality":"one",
    *             "property":"karli"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Maze",
    *             "cardinality":"one",
    *             "property":"maze"
    *          },
    *          "target":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"Maze",
    *             "cardinality":"many",
    *             "property":"maze"
    *          },
    *          "target":{
    *             "id":"Tile",
    *             "cardinality":"many",
    *             "property":"tiles"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          },
    *          "target":{
    *             "id":"Maze",
    *             "cardinality":"one",
    *             "property":"maze"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          },
    *          "target":{
    *             "id":"Box",
    *             "cardinality":"many",
    *             "property":"boxes"
    *          }
    *       },
    *       {
    *          "typ":"aggregation",
    *          "source":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          },
    *          "target":{
    *             "id":"Karli",
    *             "cardinality":"one",
    *             "property":"karli"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"AKarli",
    *             "cardinality":"one",
    *             "property":"akarli"
    *          },
    *          "target":{
    *             "id":"Sokoban",
    *             "cardinality":"one",
    *             "property":"sokoban"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"many",
    *             "property":"neighbors"
    *          },
    *          "target":{
    *             "id":"Tile",
    *             "cardinality":"many",
    *             "property":"neighbors"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"one",
    *             "property":"tile"
    *          },
    *          "target":{
    *             "id":"Box",
    *             "cardinality":"one",
    *             "property":"box"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"one",
    *             "property":"tile"
    *          },
    *          "target":{
    *             "id":"Karli",
    *             "cardinality":"one",
    *             "property":"karli"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"many",
    *             "property":"tiles"
    *          },
    *          "target":{
    *             "id":"Maze",
    *             "cardinality":"many",
    *             "property":"maze"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Tile",
    *             "cardinality":"many",
    *             "property":"tiles"
    *          },
    *          "target":{
    *             "id":"AKarli",
    *             "cardinality":"one",
    *             "property":"akarli"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasSokobanModelGenClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/SokobanModelGen.html'>SokobanModelGen.html</a>
 */
   @Test
   public void SokobanModelGen() throws Exception
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.reachabilitygraphs.sokoban");
      
      Clazz sokoban = model.createClazz("Sokoban");
      
      Clazz maze = model.createClazz("Maze")
            .withAttribute("width", DataType.INT)
            .withAttribute("height", DataType.INT);

      sokoban.createUniDirectional(maze, "maze", Cardinality.ONE)
      .with(AssociationTypes.AGGREGATION);
      
      Clazz tile = model.createClazz("Tile")
            .withAttribute("x", DataType.INT)
            .withAttribute("y", DataType.INT)
            .withAttribute("wall", DataType.BOOLEAN)
            .withAttribute("goal", DataType.BOOLEAN);

      maze.createBidirectional(tile, "tiles", Cardinality.MANY, "maze", Cardinality.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      tile.createBidirectional(tile, "neighbors", Cardinality.MANY, "neighbors", Cardinality.MANY);
      
      Clazz box = model.createClazz("Box");
      
      sokoban.createUniDirectional(box, "boxes", Cardinality.MANY)
      .with(AssociationTypes.AGGREGATION);
      
      box.createUniDirectional(tile, "tile", Cardinality.ONE);
      
      Clazz karli = model.createClazz("Karli");

      sokoban.createUniDirectional(karli, "karli", Cardinality.ONE)
      .with(AssociationTypes.AGGREGATION);

      karli.createUniDirectional(tile, "tile", Cardinality.ONE);
      
      Clazz akarli = model.createClazz("AKarli");

      sokoban.createUniDirectional(akarli, "akarli", Cardinality.ONE);

      akarli.createUniDirectional(tile, "tiles", Cardinality.MANY);
      
      

      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
   }
}
