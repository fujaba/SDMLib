package org.sdmlib.examples.ludoreverse;

import java.awt.Point;

import org.junit.Test;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Visibility;
import org.sdmlib.models.classes.Association;

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

      model.generate("examples");
   }

   @Test
   public void LudoModelReverse()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.ludoreverse.model");

      model.getGenerator().updateFromCode("examples", "org.sdmlib.examples.ludoreverse.model");
      model.getGenerator().insertModelCreationCodeHere("examples");
   }
}
