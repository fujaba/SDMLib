package org.sdmlib.examples.helloworld;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;

public class HelloWorldTmp
{
   @Test
   public void testTmp()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.helloworld.model");

      Clazz nodeClazz = model.createClazz("Node");

      nodeClazz.withAssoc(nodeClazz, "copy", Card.ONE, "orig", Card.ONE);

      model.generate("src/test/java");

   }
}
