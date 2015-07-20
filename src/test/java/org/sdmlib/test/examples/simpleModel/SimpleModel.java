package org.sdmlib.test.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Modifier;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;

public class SimpleModel
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz createClazz = model.createClazz("Alex");
      createClazz.withAttribute("Name", DataType.STRING);
      
      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", DataType.STRING);
      
      // Enable Special Thinks
      model.getGenerator().withShowDiff(DIFF.FULL);
      model.generate("src/test/java");
   }
   
   @Test
   public void testMethodModel(){
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      Clazz helperClazz = model.createClazz("Item");
      helperClazz.createMethod("init").withBody("System.out.println(new Date());").with(Modifier.STATIC);
      helperClazz.withImport("java.util.Date");
      
      model.generate("src/test/java");
   }
}
