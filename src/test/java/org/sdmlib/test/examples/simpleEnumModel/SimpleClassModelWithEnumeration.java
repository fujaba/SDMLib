package org.sdmlib.test.examples.simpleEnumModel;


import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;

public class SimpleClassModelWithEnumeration
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.examples.simpleEnumModel.model");
      
      Enumeration enumeration = model.createEnumeration("TEnum");
      enumeration.withValueNames("T1", "T2", "12", "T1000");
      enumeration.withMethod("toString", DataType.STRING);
     
      Clazz createClazz = model.createClazz("Alex");
      createClazz.withAttribute("Name", DataType.STRING);
          
      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", DataType.STRING);
      
//      model.getGenerator().withShowDiff(DIFF.FULL);
      model.generate("src/test/java");
      
      Assert.assertEquals(1, model.getEnumerations().size());      
   }
}
