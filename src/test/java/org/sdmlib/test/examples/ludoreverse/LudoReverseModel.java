package org.sdmlib.test.examples.ludoreverse;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Visibility;

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
            .with(new Attribute("x", DataType.INT).with(Visibility.PUBLIC))
            .with(new Attribute("y", DataType.INT).with(Visibility.PUBLIC))
            .withExternal(true);
      
      Clazz player = model.createClazz("Player")
            .withAttribute("name", DataType.STRING)
            .withAttribute("color", DataType.STRING)
            .withAssoc(ludo, "players", Card.MANY, "game", Card.ONE);

      model.generate("src/test/java");
   }

   @Test
   public void LudoModelReverse()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.ludoreverse.model");

      Clazz ludoClass = model.createClazz("org.sdmlib.examples.ludoreverse.model.Ludo")
      .with(new Attribute("style", DataType.ref("String")) )
      .with(new Attribute("age", DataType.ref("int")) );

      Clazz playerClass = model.createClazz("org.sdmlib.examples.ludoreverse.model.Player")
      .with(new Attribute("name", DataType.ref("String")) )
      .with(new Attribute("color", DataType.ref("String")) );

      ludoClass.withAssoc(playerClass, "game", Card.ONE, "players", Card.MANY);


      model.getGenerator().updateFromCode("examples", "org.sdmlib.examples.ludoreverse.model");
      model.getGenerator().insertModelCreationCodeHere("examples");
   }
}
