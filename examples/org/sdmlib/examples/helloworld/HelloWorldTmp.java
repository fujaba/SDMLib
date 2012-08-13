package org.sdmlib.examples.helloworld;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;

public class HelloWorldTmp
{
   @Test
   public void testTmp()
   {
      ClassModel model = new ClassModel();

      Clazz nodeClazz = new Clazz("org.sdmlib.examples.helloworld.Node");

      new Association()
      .withTarget(nodeClazz, "copy", Role.ONE)
      .withSource(nodeClazz, "orig", Role.ONE);

      model.generate("examples", "examples");

   }
}
