package org.sdmlib.examples.clickcounter;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

public class DataModel
{
   @Test
   public void testCreateModel() 
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.clickcounter");
      
      model.createClazz("Data", "num", "int");
      
      model.generate("examples");
   }
}
