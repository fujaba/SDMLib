package org.sdmlib.examples.ludoreverse;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;

public class LudoReverseModel
{  
   
   @Test
   public void LudoModelCreation()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.ludoreverse.model");

      Clazz ludo = model.createClazz("Ludo")
            .withAttribute("style", DataType.STRING)
            .withAttribute("age", DataType.INT);
      
      Clazz point = model.createClazz(Point.class.getName())
            .withAttribute("x", DataType.INT)
            .withAttribute("y", DataType.INT)
            .withExternal(true);
      
      Clazz player = model.createClazz("Player")
            .withAttribute("name", DataType.STRING)
            .withAttribute("color", DataType.STRING)
            .withAssoc(ludo, "players", Card.MANY, "game", Card.ONE);

      model.generate("examples");
   }

   @Test
   public void LudoModelReverse()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.ludoreverse.model");
      
      Clazz ludoClass = model.createClazz("Ludo")
            .withAttribute("style", DataType.STRING)
            .withAttribute("age", DataType.INT)
            .withMethod("toString");
      
      Clazz playerClass = model.createClazz("Player")
            .withAttribute("name", DataType.STRING)
            .withAttribute("color", DataType.STRING)
            .withAssoc(ludoClass, "players", Card.MANY, "game", Card.ONE)
            .withMethod("toString");

      //TODO: FIX integrate Reverse Engineering Module
//      model.updateFromCode("examples", "org.sdmlib.examples.ludoreverse.model");
//
//      model.insertModelCreationCodeHere();
   }
}

