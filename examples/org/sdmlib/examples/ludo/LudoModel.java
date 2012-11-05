package org.sdmlib.examples.ludo;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import static org.sdmlib.models.classes.Role.R.*;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;

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
      green, 
      blue, 
      red, 
      yellow
   }
   
   @Test
   public void testLudoModel()
   {
      Scenario scenario = new Scenario("examples", "LudoModel");
      
      //      scenario.add("The model: ",
      //         MODELING, "zuendorf", "15.07.2012 15:40:33", 2, 0);
      
      ClassModel model = new ClassModel("org.sdmlib.examples.ludo");
            
      Clazz ludo = model.createClazz("Ludo");
      
      Clazz point = model.createClazz(Point.class.getName(),
         "x", INT,
         "y", INT
         ).withWrapped(true);
      
      Clazz player = ludo.createClassAndAssoc("Player",
         "players", MANY,
         "game", ONE 
         );
      
      //      model
      //      .createClazz(LudoColor.class.getCanonicalName())
      //      .withWrapped(true);
      
      player.withAttributes(
         "color", String.class.getSimpleName(),
         "enumColor", LudoColor.class.getCanonicalName(),
         "name", STRING,
         "x", INT,
         "y", INT);
      
      player.withAssoc(player, "next", ONE, "prev", ONE);
      
      Clazz dice = ludo.createClassAndAssoc("Dice", "dice", ONE, "game", ONE);
      
      dice.withAttributes("value", INT);
      
      player.withAssoc(dice, "dice", ONE, "player", ONE);
      
      Clazz field = ludo.createClassAndAssoc("Field", "fields", MANY, "game", ONE);
      
      field.withAttributes(
         "color", STRING,
         "kind", STRING,
         "x", INT,
         "y", INT);
      
      field.withAssoc(field, "next", ONE, "prev", ONE);
      
      field.withAssoc(field, "landing", ONE, "entry", ONE);
      
      player.withAssoc(field, "start", ONE, "starter", ONE);
      
      player.withAssoc(field, "base", ONE, "baseowner", ONE);
      
      player.withAssoc(field, "landing", ONE, "lander", ONE);
      
      Clazz pawn = player.createClassAndAssoc("Pawn", "pawns", MANY, "player", ONE);
         
      pawn.withAttributes(   
         "color", STRING,
         "x", INT,
         "y", INT);

      pawn.withAssoc(field, "pos", ONE, "pawns", MANY);
      
      scenario.addImage(model.dumpClassDiag("examples", "LudoModel01"));

      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");
      
      model.generate("examples", "examplehelpers");
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
}




