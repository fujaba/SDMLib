package org.sdmlib.doc;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

public class testDocGen
{
   @Test
   public void SimpleDocGen(){
      ClassModel model=new ClassModel("de.sdmlib.doc");
      
      model.createClazz("Person");
      
      
      GraphFactory.getAdapter().dumpClassDiagram("Test", model);
   }
}
