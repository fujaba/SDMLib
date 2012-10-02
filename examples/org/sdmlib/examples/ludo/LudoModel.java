package org.sdmlib.examples.ludo;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;

public class LudoModel
{
   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";
   
   @Test
   public void testLudoModel()
   {
      Scenario scenario = new Scenario("examples", "LudoModel");
      
      scenario.add("Start situation: ",
         MODELING, "zuendorf", "15.07.2012 15:40:33", 2, 0);
      
      ClassModel model = new ClassModel();
            
      Clazz ludo = model.createClazz("org.sdmlib.examples.ludo.Ludo");
      
      Clazz player = ludo.createClassAndAssoc("org.sdmlib.examples.ludo.Player",
         "players", Role.MANY,
         "game", Role.ONE);
      
      player.withAttributes(
         "color", String.class.getSimpleName(),
         "name", "String",
         "x", "int",
         "y", "int");
      
      player.withAssoc(player, "next", Role.ONE, "prev", Role.ONE);
      
      Clazz dice = ludo.createClassAndAssoc("org.sdmlib.examples.ludo.Dice", "dice", Role.ONE, "game", Role.ONE);
      
      dice.withAttributes("value", "int");
      
      player.withAssoc(dice, "dice", Role.ONE, "player", Role.ONE);
      
      Clazz field = ludo.createClassAndAssoc("org.sdmlib.examples.ludo.Field", "fields", Role.MANY, "game", Role.ONE);
      
      field.withAttributes(
         "color", "String",
         "kind", "String",
         "x", "int",
         "y", "int");
      
      field.withAssoc(field, "next", Role.ONE, "prev", Role.ONE);
      
      field.withAssoc(field, "landing", Role.ONE, "entry", Role.ONE);
      
      player.withAssoc(field, "start", Role.ONE, "starter", Role.ONE);
      
      player.withAssoc(field, "base", Role.ONE, "baseowner", Role.ONE);
      
      player.withAssoc(field, "landing", Role.ONE, "lander", Role.ONE);
      
      Clazz pawn = player.createClassAndAssoc("org.sdmlib.examples.ludo.Pawn", "pawns", Role.MANY, "player", Role.ONE);
         
      pawn.withAttributes(   
         "color", "String",
         "x", "int",
         "y", "int");

      pawn.withAssoc(field, "pos", Role.ONE, "pawns", Role.MANY);
      
      scenario.addImage(model.dumpClassDiag("LudoModel01"));

      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");
      
      model.generate("examples", "examplehelpers");
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
}




