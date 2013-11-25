package org.sdmlib.examples.helloworld;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.Role.R;

public class HelloWorldTmp
{
   @Test
   public void testTmp()
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.helloworld");

      Clazz nodeClazz = model.createClazz("Node");

      nodeClazz.withAssoc(nodeClazz, "copy", R.ONE, "orig", R.ONE);

      model.generate("examples", "examples");

   }
}
