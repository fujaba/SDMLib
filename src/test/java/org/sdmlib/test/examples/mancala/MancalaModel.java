package org.sdmlib.test.examples.mancala;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.test.examples.mancala.referencemodel.Color;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Feature;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.Parameter;

public class MancalaModel {

	 @Test
	 public void MancalaAttributeTypeCreation() {
		 ClassModel model = new ClassModel("org.sdmlib.test.examples.mancala.referencemodel");
		 model.createClazz("Color");
		 model.generate("src/test/java");
	 }
	
    @Test
    public void MancalaModelCreation() {
        //tag::mancala[]
        ClassModel model = new ClassModel("org.sdmlib.test.examples.mancala.model"); //<1>

        Clazz stateEnum = model.createClazz("PlayerState").enableEnumeration("WAIT","WIN","LOSE","ACTIVE");

        Clazz mancala = model.createClazz("Mancala") //<2>
                .withMethod("checkEnd", DataType.VOID) //<3>
                .withMethod("initGame", DataType.VOID, new Parameter(DataType.STRING).with("firstPlayerName"), new Parameter(DataType.STRING).with("secondPlayerName")); //<4>

        Clazz player = model.createClazz("Player")
                .withAttribute("name", DataType.STRING) //<5>
                .withBidirectional(mancala, "activeGame", Association.ONE, "activePlayer", Association.ONE) //<6>
                .withBidirectional(mancala, "game", Association.ONE, "players", Association.MANY)
                .withAttribute("state", DataType.create(stateEnum))
                .withAttribute("color", DataType.create(Color.class));

        Clazz point = model.createClazz(Point.class.getName()); //<7>
        point.createAttribute("x", DataType.INT).with(Modifier.PUBLIC); //<8>
        point.createAttribute("y", DataType.INT).with(Modifier.PUBLIC);
        point.withExternal(true); //<9>

        Clazz pit = model.createClazz("Pit")
                .withAttribute("nr", DataType.INT)
                .withMethod("moveStones", DataType.VOID)
                .withBidirectional(mancala, "game", Association.ONE, "pits", Association.MANY)
                .withBidirectional(player, "player", Association.ONE, "pits", Association.MANY);

        pit.withBidirectional(pit, "next", Association.ONE, "previous", Association.ONE)
                .withBidirectional(pit, "counterpart", Association.ONE, "counterpart", Association.ONE);

        Clazz kalah = model.createClazz("Kalah")
                .withSuperClazz(pit) //<10>
                .withBidirectional(player, "kalahPlayer", Association.ONE, "kalah", Association.ONE);

        Clazz stone = model.createClazz("Stone")
                .withBidirectional(player, "player", Association.ONE, "stone", Association.ONE);
        
        Feature feature = model.getFeature(Feature.SERIALIZATION);
        if(feature != null) {
        	feature.withPath("org.sdmlib.test.examples.mancala.referencemodel.util").withExcludeClazz(stone);
        }
		
        model.generate("src/test/java"); //<11>
        //model.dumpHTML("MancalaClassDiagram", "mancaladoc", Javascript.NAME);

        //end::mancala[]
        
        Assert.assertEquals(1, model.getEnumerations().size());      

    }

    @Test
    public void MancalaModelReverse() {
      ClassModel model = new ClassModel("org.sdmlib.test.examples.mancala.model");

      Clazz mancalaClass = model.createClazz("Mancala")
      /* add method */
      .withMethod("checkEnd", DataType.VOID)
      /* add method */
      .withMethod("initGame", DataType.VOID, new Parameter(DataType.STRING), new Parameter(DataType.STRING))
      /* add method */
      .withMethod("createPitsKalah", DataType.create("Kalah"));

      Clazz pitClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Pit")
      .withAttribute("nr", DataType.create("int"))
      .withAttribute("nr", DataType.create("int"))
      /* add method */
      .withMethod("moveStones", DataType.VOID);

      pitClass.withBidirectional(mancalaClass, "game", Association.ONE, "pits", Association.MANY);

      pitClass.withBidirectional(pitClass, "next", Association.ONE, "previous", Association.ONE);

      pitClass.withBidirectional(pitClass, "counterpart", Association.ONE, "counterpart", Association.ONE);
      
      
      Clazz playerClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Player")
      .withAttribute("name", DataType.create("String"))
      .withAttribute("state", DataType.create("PlayerState"));
      /* add assoc */
      playerClass.withBidirectional(mancalaClass, "game", Association.ONE, "players", Association.MANY);

      mancalaClass.withBidirectional(playerClass, "activePlayer", Association.ONE, "game", Association.ONE);

      pitClass.withBidirectional(playerClass, "player", Association.ONE, "pits", Association.MANY);

      playerClass.withBidirectional(mancalaClass, "activeGame", Association.ONE, "activePlayer", Association.ONE);

      Clazz playerStateClass = model.createClazz("org.sdmlib.test.examples.mancala.model.PlayerState");

      Clazz stoneClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Stone");

      playerClass.withBidirectional(stoneClass, "stone", Association.ONE, "player", Association.ONE);

      Clazz kalahClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Kalah")
      .withSuperClazz(pitClass);

      kalahClass.withBidirectional(playerClass, "kalahPlayer", Association.ONE, "kalah", Association.ONE);



//      model.getGenerator().updateFromCode("src/test/java", "org.sdmlib.test.examples.mancala.model");
      
//      model.getGenerator().insertModelCreationCodeHere("src/test/java");
    }

}
