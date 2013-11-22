package org.sdmlib.examples.clickcounter;

import javafx.beans.property.IntegerProperty;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

public class DataModel
{
   @Test
   public void testCreateModel() 
   {
      ClassModel model = new ClassModel("org.sdmlib.examples.clickcounter");
      
      model.createClazz("Data", 
         "num", "int", 
         "fxnum", IntegerProperty.class.getName());
      
      model.generate("examples");
   }
}
