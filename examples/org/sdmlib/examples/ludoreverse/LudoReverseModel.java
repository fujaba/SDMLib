package org.sdmlib.examples.ludoreverse;

import static org.sdmlib.models.classes.Role.R.MANY;
import static org.sdmlib.models.classes.Role.R.ONE;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Association;

public class LudoReverseModel
{
   private static final String INT = "int";
   private static final String STRING = "String";
   
   
   @Test
   public void LudoModelCreation()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.ludoreverse.model");

      Clazz ludo = model.createClazz("Ludo", 
         "style", STRING, 
         "age", "int");
      
      Clazz point = model.createClazz(Point.class.getName(),
         "x", INT,
         "y", INT
         ).withWrapped(true);
      
      

      Clazz player = ludo.createClassAndAssoc("Player",
         "players", MANY,
         "game", ONE 
            );

      player.withAttributes(
         "name", "String", 
         "color", "String");

      model.generate("examples", "examplehelpers");
   }

   @Test
   public void LudoModelReverse()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.ludoreverse.model");

      Clazz ludoClass = new Clazz("org.sdmlib.examples.ludoreverse.model.Ludo")
      .withAttribute("style", "String")
      .withAttribute("age", "int");

      new Method()
			.withClazz(ludoClass)
			.withSignature("toString()");

      Clazz playerClass = new Clazz("org.sdmlib.examples.ludoreverse.model.Player")
      .withAttribute("name", "String")
      .withAttribute("color", "String");

      new Method()
			.withClazz(playerClass)
			.withSignature("toString()");

      ludoClass.withAssoc(playerClass, "players", MANY, "game", ONE);

      model.updateFromCode("examples", "examples", "org.sdmlib.examples.ludoreverse.model");

      model.insertModelCreationCodeHere("examples");
   }
}

