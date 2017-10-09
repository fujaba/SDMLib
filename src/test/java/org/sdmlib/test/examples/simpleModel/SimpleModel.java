package org.sdmlib.test.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Import;
import de.uniks.networkparser.graph.Modifier;

public class SimpleModel
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz createClazz = model.createClazz("Alex");
      createClazz.withAttribute("Name", DataType.STRING);
      
      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", DataType.STRING);
      
      // Enable Special Things
      model.getGenerator().withShowDiff(DIFF.FULL);
      model.generate("src/test/java");
   }
   
   @Test
   public void testMethodModel(){
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      Clazz helperClazz = model.createClazz("Item");
      helperClazz.createMethod("init").withBody("System.out.println(new Date());").with(Modifier.STATIC);
//      model.getGenerator().getOrCreateClazz(helperClazz).insertImport("java.util.Date");
      GraphUtil.setImport(helperClazz, Import.create("java.util.Date"));
      
      model.generate("src/test/java");
   }
}
