package org.sdmlib.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;

public class SimpleModel
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.examples.simpleModel.model");
      
      Clazz createClazz = model.createClazz("Alex");
      createClazz.withAttribute("Name", DataType.STRING);
      
      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", DataType.STRING);
      
      // Enable Special Thinks
      model.getGenerator().withShowDiff(true);
      model.generate("examples");
   }
}
