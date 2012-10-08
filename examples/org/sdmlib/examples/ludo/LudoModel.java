package org.sdmlib.examples.ludo;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.Role.R;
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
      
      ClassModel model = new ClassModel("org.sdmlib.examples.ludo");
            
      Clazz ludo = model.createClazz("Ludo");
      
      Clazz player = ludo.createClassAndAssoc("Player",
         "players", R.MANY,
         "game", R.ONE);
      
      player.withAttributes(
         "color", String.class.getSimpleName(),
         "name", "String",
         "x", "int",
         "y", "int");
      
      player.withAssoc(player, "next", R.ONE, "prev", R.ONE);
      
      Clazz dice = ludo.createClassAndAssoc("Dice", "dice", R.ONE, "game", R.ONE);
      
      dice.withAttributes("value", "int");
      
      player.withAssoc(dice, "dice", R.ONE, "player", R.ONE);
      
      Clazz field = ludo.createClassAndAssoc("Field", "fields", R.MANY, "game", R.ONE);
      
      field.withAttributes(
         "color", "String",
         "kind", "String",
         "x", "int",
         "y", "int");
      
      field.withAssoc(field, "next", R.ONE, "prev", R.ONE);
      
      field.withAssoc(field, "landing", R.ONE, "entry", R.ONE);
      
      player.withAssoc(field, "start", R.ONE, "starter", R.ONE);
      
      player.withAssoc(field, "base", R.ONE, "baseowner", R.ONE);
      
      player.withAssoc(field, "landing", R.ONE, "lander", R.ONE);
      
      Clazz pawn = player.createClassAndAssoc("Pawn", "pawns", R.MANY, "player", R.ONE);
         
      pawn.withAttributes(   
         "color", "String",
         "x", "int",
         "y", "int");

      pawn.withAssoc(field, "pos", R.ONE, "pawns", R.MANY);
      
      scenario.addImage(model.dumpClassDiag("examples", "LudoModel01"));

      // model.removeAllGeneratedCode("examples", "examples", "examplehelpers");
      
      model.generate("examples", "examplehelpers");
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
}




