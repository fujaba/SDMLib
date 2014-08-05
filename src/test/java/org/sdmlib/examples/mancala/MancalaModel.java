package org.sdmlib.examples.mancala;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.Visibility;

public class MancalaModel {

    @Test
    public void MancalaModelCreation() {
        //tag::mancala[]
        ClassModel model = new ClassModel("org.sdmlib.examples.mancala.model"); //<1>

        Enumeration stateEnum = model.createEnumeration("PlayerState")
        .withValueNames("WAIT","WIN","LOSE","ACTIVE");

        Clazz mancala = model.createClazz("Mancala") //<2>
                .withMethod("checkEnd") //<3>
                .withMethod("initGame", DataType.VOID, new Parameter("firstPlayerName", DataType.STRING), new Parameter("secondPlayerName", DataType.STRING)); //<4>

        Clazz player = model.createClazz("Player")
                .withAttribute("name", DataType.STRING) //<5>
                .withAssoc(mancala, "game", Card.ONE, "activePlayer", Card.ONE) //<6>
                .withAssoc(mancala, "game", Card.ONE, "players", Card.MANY)
                .withAttribute("state", DataType.ref(stateEnum));

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
        

        model.generate("src/test/java"); //<11>
        //model.dumpHTML("MancalaClassDiagram", "mancaladoc", Javascript.NAME);

        //end::mancala[]
    }

    @Test
    public void MancalaModelReverse() {
      ClassModel model = new ClassModel("org.sdmlib.examples.mancala.model");

      Clazz mancalaClass = model.createClazz("org.sdmlib.examples.mancala.model.Mancala");
      /* add method */
      new Method("initGame", new Parameter(DataType.ref("String")), new Parameter(DataType.ref("String")))
        .with(mancalaClass);


      Clazz playerStateClass = model.createClazz("org.sdmlib.examples.mancala.model.PlayerState");
      /* add method */
      new Method("createPitsKalah")
        .with(mancalaClass);
      /* add method */
      new Method("createKalah")
        .with(mancalaClass);
      /* add method */
      new Method("initGame", new Parameter(DataType.ref("String")), new Parameter(DataType.ref("String")))
        .with(mancalaClass);
      /* add method */
      new Method("checkEnd")
        .with(mancalaClass);

      new Method("checkEnd")
        .with(mancalaClass);

      new Method("initGame", new Parameter(DataType.ref("String")), new Parameter(DataType.ref("String")))
        .with(mancalaClass);

      new Method("createKalah")
        .with(mancalaClass);

      new Method("createPitsKalah")
        .with(mancalaClass);

      Clazz pitClass = model.createClazz("org.sdmlib.examples.mancala.model.Pit")
      .with(new Attribute("nr", DataType.ref("int")) );
      /* add method */
      new Method("moveStones")
        .with(pitClass);

      Clazz kalahClass = model.createClazz("org.sdmlib.examples.mancala.model.Kalah")
      .withSuperClazz(pitClass);

      Clazz playerClass = model.createClazz("org.sdmlib.examples.mancala.model.Player")
      .with(new Attribute("name", DataType.ref("String")) );

      mancalaClass.withAssoc(playerClass, "activePlayer", Card.ONE, "game", Card.ONE);

      pitClass.withAssoc(playerClass, "player", Card.ONE, "pits", Card.MANY);

      kalahClass.withAssoc(playerClass, "kalahPlayer", Card.ONE, "kalah", Card.ONE);

      Clazz stoneClass = model.createClazz("org.sdmlib.examples.mancala.model.Stone");

      playerClass.withAssoc(stoneClass, "stone", Card.ONE, "player", Card.ONE);

      pitClass.withAssoc(mancalaClass, "game", Card.ONE, "pits", Card.MANY);

      pitClass.withAssoc(pitClass, "next", Card.ONE, "previous", Card.ONE);

      pitClass.withAssoc(pitClass, "counterpart", Card.ONE, "counterpart", Card.ONE);

      model.getGenerator().updateFromCode("src/test/java", "org.sdmlib.examples.mancala.model");
      model.getGenerator().insertModelCreationCodeHere("src/test/java");
    }

}
