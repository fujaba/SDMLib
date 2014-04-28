package org.sdmlib.examples.ludo;

import static org.sdmlib.models.classes.Role.R.MANY;
import static org.sdmlib.models.classes.Role.R.ONE;

import java.awt.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;

public class LudoModel
{
   private static final String INT = "int";
   private static final String STRING = "String";
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";

   public enum LudoColor
   {
      green, blue, red, yellow
   }

   @Test
   public void testLudoModel()
   {
      Storyboard storyboard = new Storyboard("examples", "LudoModel");

      storyboard.setSprint("Sprint.002.Examples");

      // storyboard.add("The model: ",
      // MODELING, "zuendorf", "15.07.2012 15:40:33", 2, 0);

      ClassModel model = new ClassModel("org.sdmlib.examples.ludo");

      Clazz ludo = model.createClazz("Ludo").withAttribute("date",
         Date.class.getName());

      Clazz point = model
         .createClazz(Point.class.getName(), "x", INT, "y", INT).withWrapped(
            true);

      Clazz player = ludo.createClassAndAssoc("Player", "players", MANY,
         "game", ONE).withAttributes("color", String.class.getSimpleName(),
         "enumColor", LudoColor.class.getCanonicalName(), "name", STRING, "x",
         INT, "y", INT);

      player.withAssoc(player, "next", ONE, "prev", ONE);

      Clazz dice = ludo.createClassAndAssoc("Dice", "dice", ONE, "game", ONE);

      dice.withAttributes("value", INT);

      player.withAssoc(dice, "dice", ONE, "player", ONE);

      Clazz field = ludo.createClassAndAssoc("Field", "fields", MANY, "game",
         ONE);

      field.withAttributes("color", STRING, "kind", STRING, "x", INT, "y", INT,
         "point", point.getName());

      field.withAssoc(field, "next", ONE, "prev", ONE);

      field.withAssoc(field, "landing", ONE, "entry", ONE);

      player.withAssoc(field, "start", ONE, "starter", ONE);

      player.withAssoc(field, "base", ONE, "baseowner", ONE);

      player.withAssoc(field, "landing", ONE, "lander", ONE);

      Clazz pawn = player.createClassAndAssoc("Pawn", "pawns", MANY, "player",
         ONE);

      pawn.withAttributes("color", STRING, "x", INT, "y", INT);

      pawn.withAssoc(field, "pos", ONE, "pawns", MANY);

      // model.updateFromCode("examples", "examples",
      // "org.sdmlib.examples.ludo");

      // model.insertModelCreationCodeHere("examples");

      storyboard.addSVGImage(model.dumpClassDiagram("examples", "LudoModel01"));

      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");

      model.generate("examples", "examplehelpers");

      storyboard.addLogEntry(R.DONE, "zuendorf", "15.07.2012 13:30:42 EST", 20,
         0, "The famous ludo example.");

      StoryboardManager.get().add(storyboard).dumpHTML();
   }
}
