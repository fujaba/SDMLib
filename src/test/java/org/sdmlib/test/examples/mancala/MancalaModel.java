package org.sdmlib.test.examples.mancala;

import java.awt.Point;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.Visibility;
import org.sdmlib.test.examples.mancala.referencemodel.Color;

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

        Enumeration stateEnum = model.createEnumeration("PlayerState")
        .withValueNames("WAIT","WIN","LOSE","ACTIVE");

        Clazz mancala = model.createClazz("Mancala") //<2>
                .withMethod("checkEnd") //<3>
                .withMethod("initGame", DataType.VOID, new Parameter("firstPlayerName", DataType.STRING), new Parameter("secondPlayerName", DataType.STRING)); //<4>

        Clazz player = model.createClazz("Player")
                .withAttribute("name", DataType.STRING) //<5>
                .withAssoc(mancala, "activeGame", Card.ONE, "activePlayer", Card.ONE) //<6>
                .withAssoc(mancala, "game", Card.ONE, "players", Card.MANY)
                .withAttribute("state", DataType.ref(stateEnum))
                .withAttribute("color", DataType.ref(Color.class));

        Clazz point = model.createClazz(Point.class.getName()) //<7>
                .with(new Attribute("x", DataType.INT).with(Visibility.PUBLIC)) //<8>
                .with(new Attribute("y", DataType.INT).with(Visibility.PUBLIC))
                .withExternal(true); //<9>

        Clazz pit = model.createClazz("Pit")
                .withAttribute("nr", DataType.INT)
                .withMethod("moveStones")
                .withAssoc(mancala, "game", Card.ONE, "pits", Card.MANY)
                .withAssoc(player, "player", Card.ONE, "pits", Card.MANY);

        pit.withAssoc(pit, "next", Card.ONE, "previous", Card.ONE)
                .withAssoc(pit, "counterpart", Card.ONE, "counterpart", Card.ONE);

        Clazz kalah = model.createClazz("Kalah")
                .withSuperClazz(pit) //<10>
                .withAssoc(player, "kalahPlayer", Card.ONE, "kalah", Card.ONE);

        Clazz stone = model.createClazz("Stone")
                .withAssoc(player, "player", Card.ONE, "stone", Card.ONE);
        
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

      pitClass.withAssoc(mancalaClass, "game", Card.ONE, "pits", Card.MANY);

      pitClass.withAssoc(pitClass, "next", Card.ONE, "previous", Card.ONE);

      pitClass.withAssoc(pitClass, "counterpart", Card.ONE, "counterpart", Card.ONE);
      
      
      Clazz playerClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Player")
      .with(new Attribute("name", DataType.ref("String")) )
      .with(new Attribute("state", DataType.ref("PlayerState")) );
      /* add assoc */
      playerClass.withAssoc(mancalaClass, "game", Card.ONE, "players", Card.MANY);

      mancalaClass.withAssoc(playerClass, "activePlayer", Card.ONE, "game", Card.ONE);

      pitClass.withAssoc(playerClass, "player", Card.ONE, "pits", Card.MANY);

      playerClass.withAssoc(mancalaClass, "activeGame", Card.ONE, "activePlayer", Card.ONE);

      Clazz playerStateClass = model.createClazz("org.sdmlib.test.examples.mancala.model.PlayerState");

      Clazz stoneClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Stone");

      playerClass.withAssoc(stoneClass, "stone", Card.ONE, "player", Card.ONE);

      Clazz kalahClass = model.createClazz("org.sdmlib.test.examples.mancala.model.Kalah")
      .withSuperClazz(pitClass);

      kalahClass.withAssoc(playerClass, "kalahPlayer", Card.ONE, "kalah", Card.ONE);



//      model.getGenerator().updateFromCode("src/test/java", "org.sdmlib.test.examples.mancala.model");
      
//      model.getGenerator().insertModelCreationCodeHere("src/test/java");
    }

}
