package org.sdmlib.test.examples.simpleEnumModel;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Literal;
import de.uniks.networkparser.graph.Parameter;

public class SimpleClassModelWithEnumeration
{
   /**
    * @see <a href='../../../../../../../../doc/EnumerationInSimpleClassModel.html'>EnumerationInSimpleClassModel.html</a>
    */
   @Test
   public void testEnumerationInSimpleClassModel()
   {
      Storyboard story = new Storyboard();

      story.markCodeStart();
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleEnumModel.model");

      Clazz enumeration = model.createClazz("TEnum").enableEnumeration();

      enumeration.with(new Literal("T1"),
         new Literal("T2"),
         new Literal("12"),
         new Literal("T1000"));
      enumeration.withMethod("toString", DataType.STRING);

      Clazz alexClazz = model.createClazz("Alex");
      alexClazz.withAttribute("Name", DataType.STRING);

      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", DataType.STRING)
         .withAttribute("type", DataType.create(enumeration))
         .withAttribute("owner", DataType.create(alexClazz));

      macClazz.withMethod("concat", DataType.STRING, new Parameter(DataType.INT));
      macClazz.withMethod("select", DataType.create(enumeration), new Parameter(DataType.INT));

      model.generate("src/test/java");
      story.addCode();
      
      story.addClassDiagram(model);

      story.assertEquals("Number of Enumeration types in the model: ", 1, model.getEnumerations().size());

      story.dumpHTML();

   }
}
