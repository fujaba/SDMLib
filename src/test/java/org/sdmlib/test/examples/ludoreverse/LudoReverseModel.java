package org.sdmlib.test.examples.ludoreverse;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
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
      
      Clazz point = model.createClazz(Point.class.getName())
            .with(new Attribute("x", DataType.INT).with(Modifier.PUBLIC))
            .with(new Attribute("y", DataType.INT).with(Modifier.PUBLIC))
            .withExternal(true);
      
      Clazz player = model.createClazz("Player")
            .withAttribute("name", DataType.STRING)
            .withAttribute("color", DataType.STRING)
            .withBidirectional(ludo, "players", Cardinality.MANY, "game", Cardinality.ONE);

      model.generate("src/test/java");
   }

   @Test
   public void LudoModelReverse()
   {
      ClassModel model = new ClassModel("org.sdmlib.test.examples.ludoreverse.model");

      Clazz ludoClass = model.createClazz("org.sdmlib.test.examples.ludoreverse.model.Ludo")
      .with(new Attribute("style", DataType.ref("String")) )
      .with(new Attribute("age", DataType.ref("int")) );

      Clazz playerClass = model.createClazz("org.sdmlib.test.examples.ludoreverse.model.Player")
      .with(new Attribute("name", DataType.ref("String")) )
      .with(new Attribute("color", DataType.ref("String")) );

      ludoClass.withBidirectional(playerClass, "game", Cardinality.ONE, "players", Cardinality.MANY);


      model.getGenerator().updateFromCode("examples", "org.sdmlib.test.examples.ludoreverse.model");
      model.getGenerator().insertModelCreationCodeHere("examples");
   }
}
