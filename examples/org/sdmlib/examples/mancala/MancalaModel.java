package org.sdmlib.examples.mancala;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.Visibility;

public class MancalaModel
{

   @Test
   public void MancalaModelCreation()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.mancala.model");

      Clazz mancala = model.createClazz("Mancala")
            .withMethod("checkEnd")
            .withMethod("initGame", DataType.VOID, new Parameter("firstPlayerName", DataType.STRING), new Parameter("secondPlayerName", DataType.STRING));
      
      Clazz point = model.createClazz(Point.class.getName())
            .with(new Attribute("x", DataType.INT).with(Visibility.PUBLIC))
            .with(new Attribute("y", DataType.INT).with(Visibility.PUBLIC))
            .withExternal(true);
      
      Clazz player = model.createClazz("Player")
            .withAttribute("name", DataType.STRING)
            .withAssoc(mancala, "game", Card.ONE, "activePlayer", Card.ONE)
            .withAssoc(mancala, "game", Card.ONE, "players", Card.MANY);
      
      Clazz pit = model.createClazz("Pit")
            .withAttribute("nr", DataType.INT)
            .withMethod("moveStones")
            .withAssoc(mancala, "game", Card.ONE, "pits", Card.MANY)
            .withAssoc(player, "player", Card.ONE, "pits", Card.MANY);
      
      pit.withAssoc(pit, "next", Card.ONE, "previous", Card.ONE)
         .withAssoc(pit, "counterpart", Card.ONE, "counterpart", Card.ONE);
      
      Clazz kalah = model.createClazz("Kalah")
            .withSuperClazz(pit)
            .withAssoc(player, "kalahPlayer", Card.ONE, "kalah", Card.ONE);
      
      Clazz stone = model.createClazz("Stone")
            .withAssoc(player, "player", Card.ONE, "stone", Card.ONE);

      model.generate("examples");
   }
   
   @Test
   public void MancalaModelReverse()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.mancala.model");
      model.getGenerator().updateFromCode("examples", "org.sdmlib.examples.mancala.model");
      model.getGenerator().insertModelCreationCodeHere("examples");
   }
   
}
