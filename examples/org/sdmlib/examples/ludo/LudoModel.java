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
            
      Clazz ludo = new Clazz("org.sdmlib.examples.ludo.Ludo");
      
      Clazz player = new Clazz("org.sdmlib.examples.ludo.Player")
      .withAttribute("color", "String")
      .withAttribute("name", "String")
      .withAttribute("x", "int")
      .withAttribute("y", "int");
      
      new Association()
      .withTarget(player, "players", Role.MANY)
      .withSource(ludo, "game", Role.ONE);
      
      new Association()
      .withTarget(player, "next", Role.ONE)
      .withSource(player, "prev", Role.ONE);
      
      Clazz dice = new Clazz("org.sdmlib.examples.ludo.Dice")
      .withAttribute("value", "int");
      
      new Association()
      .withTarget(dice, "dice", Role.ONE)
      .withSource(ludo, "game", Role.ONE);
      
      new Association()
      .withTarget(dice, "dice", Role.ONE)
      .withSource(player, "player", Role.ONE);
      
      Clazz field = new Clazz("org.sdmlib.examples.ludo.Field")
      .withAttribute("color", "String")
      .withAttribute("kind", "String")
      .withAttribute("x", "int")
      .withAttribute("y", "int");
      
      new Association()
      .withTarget(field, "fields", Role.MANY)
      .withSource(ludo, "game", Role.ONE);

      new Association()
      .withTarget(field, "next", Role.ONE)
      .withSource(field, "prev", Role.ONE);
      
      new Association()
      .withTarget(field, "landing", Role.ONE)
      .withSource(field, "entry", Role.ONE);
      
      new Association()
      .withTarget(field, "start", Role.ONE)
      .withSource(player, "starter", Role.ONE);

      new Association()
      .withTarget(field, "base", Role.ONE)
      .withSource(player, "baseowner", Role.ONE);

      new Association()
      .withTarget(field, "landing", Role.ONE)
      .withSource(player, "lander", Role.ONE);

      Clazz pawn = new Clazz("org.sdmlib.examples.ludo.Pawn")
      .withAttribute("color", "String")
      .withAttribute("x", "int")
      .withAttribute("y", "int");
      
      new Association()
      .withTarget(pawn, "pawns", Role.MANY)
      .withSource(player, "player", Role.ONE);

      new Association()
      .withTarget(field, "pos", Role.ONE)
      .withSource(pawn, "pawns", Role.MANY);
      
      scenario.addImage(model.dumpClassDiag("LudoModel01"));

      model.generate("examples", "examplehelpers");
      
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
}



