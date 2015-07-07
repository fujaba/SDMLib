package org.sdmlib.test.doc;

import org.junit.Test;
import org.sdmlib.doc.GraphFactory;
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
