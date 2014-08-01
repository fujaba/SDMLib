package org.sdmlib.examples.simpleEnumModel;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.EnumerationValue;

public class SimpleClassModelWithEnumeration
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.examples.simpleEnumModel.model");
      
      Enumeration enumeration = model.createEnumeration("TestEnum")
            .withValues(new EnumerationValue().withName("T1"),
                        new EnumerationValue().withName("T2"), 
                        new EnumerationValue().withName("T1000")
             );
      
      Clazz createClazz = model.createClazz("Alex");
      createClazz.withAttribute("Name", DataType.STRING);
      
      
      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", DataType.STRING);
      
//      model.getGenerator().withShowDiff(DIFF.FULL);
      model.generate("src/test/java");
   }
}
