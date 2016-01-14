package org.sdmlib.test.examples.mancala;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.test.examples.mancala.referencemodel.Color;

import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
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
                .withBidirectional(mancala, "activeGame", Cardinality.ONE, "activePlayer", Cardinality.ONE) //<6>
                .withBidirectional(mancala, "game", Cardinality.ONE, "players", Cardinality.MANY)
                .withAttribute("state", DataType.ref(stateEnum))
                .withAttribute("color", DataType.ref(Color.class));

        Clazz point = model.createClazz(Point.class.getName()) //<7>
                .with(new Attribute("x", DataType.INT).with(Modifier.PUBLIC)) //<8>
                .with(new Attribute("y", DataType.INT).with(Modifier.PUBLIC))
                .withExternal(true); //<9>

        Clazz pit = model.createClazz("Pit")
                .withAttribute("nr", DataType.INT)
                .withMethod("moveStones", DataType.VOID)
                .withBidirectional(mancala, "game", Cardinality.ONE, "pits", Cardinality.MANY)
                .withBidirectional(player, "player", Cardinality.ONE, "pits", Cardinality.MANY);

        pit.withBidirectional(pit, "next", Cardinality.ONE, "previous", Cardinality.ONE)
                .withBidirectional(pit, "counterpart", Cardinality.ONE, "counterpart", Cardinality.ONE);

        Clazz kalah = model.createClazz("Kalah")
                .withSuperClazz(pit) //<10>
                .withBidirectional(player, "kalahPlayer", Cardinality.ONE, "kalah", Cardinality.ONE);

        Clazz stone = model.createClazz("Stone")
                .withBidirectional(player, "player", Cardinality.ONE, "stone", Cardinality.ONE);
        
        Feature.Serialization.withPath("org.sdmlib.test.examples.mancala.referencemodel.util");
        Feature.Serialization.withExcludeClazz(stone);
		
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
      .withMethod("createPitsKalah", DataType.ref("Kalah"));

      Clazz pitClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Pit")
      .with(new Attribute("nr", DataType.ref("int")) )
      .with(new Attribute("nr", DataType.ref("int")) )
      /* add method */
      .withMethod("moveStones", DataType.VOID);

      pitClass.withBidirectional(mancalaClass, "game", Cardinality.ONE, "pits", Cardinality.MANY);

      pitClass.withBidirectional(pitClass, "next", Cardinality.ONE, "previous", Cardinality.ONE);

      pitClass.withBidirectional(pitClass, "counterpart", Cardinality.ONE, "counterpart", Cardinality.ONE);
      
      
      Clazz playerClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Player")
      .with(new Attribute("name", DataType.ref("String")) )
      .with(new Attribute("state", DataType.ref("PlayerState")) );
      /* add assoc */
      playerClass.withBidirectional(mancalaClass, "game", Cardinality.ONE, "players", Cardinality.MANY);

      mancalaClass.withBidirectional(playerClass, "activePlayer", Cardinality.ONE, "game", Cardinality.ONE);

      pitClass.withBidirectional(playerClass, "player", Cardinality.ONE, "pits", Cardinality.MANY);

      playerClass.withBidirectional(mancalaClass, "activeGame", Cardinality.ONE, "activePlayer", Cardinality.ONE);

      Clazz playerStateClass = model.createClazz("org.sdmlib.test.examples.mancala.model.PlayerState");

      Clazz stoneClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Stone");

      playerClass.withBidirectional(stoneClass, "stone", Cardinality.ONE, "player", Cardinality.ONE);

      Clazz kalahClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Kalah")
      .withSuperClazz(pitClass);

      kalahClass.withBidirectional(playerClass, "kalahPlayer", Cardinality.ONE, "kalah", Cardinality.ONE);



//      model.getGenerator().updateFromCode("src/test/java", "org.sdmlib.test.examples.mancala.model");
      
//      model.getGenerator().insertModelCreationCodeHere("src/test/java");
    }

}
