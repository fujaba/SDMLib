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

      Clazz ludo = model.createClazz("Ludo",
         "style", "String",
         "age", "int");

      Clazz playerClass = ludo.createClassAndAssoc("Player", "players", MANY, "game", ONE);

      playerClass.withAttributes(
         "name", "String",
         "color", "String");

      model.updateFromCode("examples", "examples", "org.sdmlib.examples.ludoreverse.model");

      model.insertModelCreationCodeHere("examples");
   }
}

