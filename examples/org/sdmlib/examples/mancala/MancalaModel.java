package org.sdmlib.examples.mancala;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.doc.GraphVizAdapter.GraphViz;
import org.sdmlib.doc.JavascriptAdapter.Javascript;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.Visibility;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Association;
import org.sdmlib.storyboards.Storyboard;

public class MancalaModel {

    @Test
    public void MancalaModelCreation() {
        //tag::mancala[]
        ClassModel model = new ClassModel("org.sdmlib.examples.mancala.model"); //<1>

        Clazz mancala = model.createClazz("Mancala") //<2>
                .withMethod("checkEnd") //<3>
                .withMethod("initGame", DataType.VOID, new Parameter("firstPlayerName", DataType.STRING), new Parameter("secondPlayerName", DataType.STRING)); //<4>

        Clazz player = model.createClazz("Player")
                .withAttribute("name", DataType.STRING) //<5>
                .withAssoc(mancala, "game", Card.ONE, "activePlayer", Card.ONE) //<6>
                .withAssoc(mancala, "game", Card.ONE, "players", Card.MANY);

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

        model.generate("examples"); //<11>
        //model.dumpHTML("MancalaClassDiagram", "mancaladoc", Javascript.NAME);

        //end::mancala[]
    }

    @Test
    public void MancalaModelReverse() {
        ClassModel model = new ClassModel("org.sdmlib.examples.mancala.model");

        model.getGenerator().updateFromCode("examples", "org.sdmlib.examples.mancala.model");
        // FIXME: ALEX fix assoc creationcode competion
        //model.getGenerator().insertModelCreationCodeHere("examples");
    }

}
