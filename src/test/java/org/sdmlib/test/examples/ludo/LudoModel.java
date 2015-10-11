package org.sdmlib.test.examples.ludo;

import java.awt.Point;
import java.util.Date;

import org.junit.Test;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Modifier;
import org.sdmlib.storyboards.Storyboard;

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
    * @see <a href='../../../../../../../../doc/LudoModel.html'>LudoModel.html</a>
*/
   @Test
   public void testLudoModel()
   {
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("The model: ");
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.ludo.model");
      Clazz ludo = model.createClazz("Ludo").withAttribute("date", DataType.ref(Date.class));
      
      Clazz point = model.createClazz(Point.class.getName())
            .with(new Attribute("x", DataType.INT).with(Modifier.PUBLIC))
            .with(new Attribute("y", DataType.INT).with(Modifier.PUBLIC))
            .withExternal(true);
      
      Clazz player = model.createClazz("Player")
            .withAssoc(ludo, "game", Card.ONE, "players", Card.MANY)
            .withAttribute("color", DataType.STRING)
            .withAttribute("enumColor", DataType.ref(LudoColor.class))
            .withAttribute("name", DataType.STRING)
            .withAttribute("x", DataType.INT)
            .withAttribute("y", DataType.INT);
      
      player.withAssoc(player, "next", Card.ONE, "prev", Card.ONE);
      
      Clazz dice = model.createClazz("Dice")
            .withAssoc(ludo, "game", Card.ONE, "dice", Card.ONE);
      
      dice.withAttribute("value", DataType.INT);
      
      player.withAssoc(dice, "dice", Card.ONE, "player", Card.ONE);
      
      Clazz field = model.createClazz("Field")
            .withAssoc(ludo, "game", Card.ONE, "fields", Card.MANY);
      
      field
         .withAttribute("color", DataType.STRING)
         .withAttribute("kind", DataType.STRING)
         .withAttribute("x", DataType.INT)
         .withAttribute("y", DataType.INT)
         .withAttribute("point", DataType.ref(point));

     
      field.withAssoc(field, "next", Card.ONE, "prev", Card.ONE);
      
      field.withAssoc(field, "landing", Card.ONE, "entry", Card.ONE);
      
      player.withAssoc(field, "start", Card.ONE, "starter", Card.ONE);
      
      player.withAssoc(field, "base", Card.ONE, "baseowner", Card.ONE);
      
      player.withAssoc(field, "landing", Card.ONE, "lander", Card.ONE);
     
      Clazz pawn = model.createClazz("Pawn")
            .withAssoc(player, "player", Card.ONE, "pawns", Card.MANY);
               
      pawn
         .withAttribute("color", DataType.STRING)
         .withAttribute("x", DataType.INT)
         .withAttribute("y", DataType.INT);

      
      pawn.withAssoc(field, "pos", Card.ONE, "pawns", Card.MANY);
      
      // model.updateFromCode("examples", "examples", "org.sdmlib.test.examples.ludo");

      // model.insertModelCreationCodeHere("examples");
     
      storyboard.addClassDiagram(model);

      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");
      
      model.generate("src/test/java");
      
      storyboard.dumpHTML();
      
   }
}

