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

      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
   }
}
