package org.sdmlib.test.examples.ludoreverse;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Modifier;

public class LudoReverseModel
{  
   
   @Test
   public void LudoModelCreation()
   {
      ClassModel model = new ClassModel("org.sdmlib.test.examples.ludoreverse.model");

      Clazz ludo = model.createClazz("Ludo")
            .withAttribute("style", DataType.STRING)
            .withAttribute("age", DataType.INT);
      
      Clazz point = model.createClazz(Point.class.getName());
      point.createAttribute("x", DataType.INT).with(Modifier.PUBLIC);
      point.createAttribute("y", DataType.INT).with(Modifier.PUBLIC);
      point.withExternal(true);
      
      Clazz player = model.createClazz("Player")
            .withAttribute("name", DataType.STRING)
            .withAttribute("color", DataType.STRING)
            .withBidirectional(ludo, "players", Association.MANY, "game", Association.ONE);

      model.generate("src/test/java");
   }

   @Test
   public void LudoModelReverse()
   {
      ClassModel model = new ClassModel("org.sdmlib.test.examples.ludoreverse.model");

      Clazz ludoClass = model.createClazz("org.sdmlib.test.examples.ludoreverse.model.Ludo")
      .withAttribute("style", DataType.create("String"))
      .withAttribute("age", DataType.create("int") );

      Clazz playerClass = model.createClazz("org.sdmlib.test.examples.ludoreverse.model.Player")
      .withAttribute("name", DataType.create("String"))
      .withAttribute("color", DataType.create("String"));

      ludoClass.withBidirectional(playerClass, "game", Association.ONE, "players", Association.MANY);


      model.getGenerator().updateFromCode("examples", "org.sdmlib.test.examples.ludoreverse.model");
      model.getGenerator().insertModelCreationCodeHere("examples");
   }
}
