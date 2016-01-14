package org.sdmlib.test.examples.simpleEnumModel;


import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;

public class SimpleClassModelWithEnumeration
{
     /**
    * 
    * @see <a href='../../../../../../../../doc/SimpleModel.html'>SimpleModel.html</a>
 * @see <a href='../../../../../../../../doc/EnumerationInSimpleClassModel.html'>EnumerationInSimpleClassModel.html</a>
 */
   @Test
   public void testEnumerationInSimpleClassModel()
   {
      StoryPage story = new StoryPage();
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleEnumModel.model");
      
      Clazz enumeration = model.createClazz("TEnum").enableEnumeration();
      enumeration.withValueNames("T1", "T2", "12", "T1000");
      enumeration.withMethod("toString", DataType.STRING);
     
      Clazz alexClazz = model.createClazz("Alex");
      alexClazz.withAttribute("Name", DataType.STRING);
          
      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", DataType.STRING)
      .withAttribute("type", DataType.ref(enumeration))
      .withAttribute("owner", DataType.ref(alexClazz));
      
      macClazz.withMethod("concat", DataType.STRING, new Parameter(DataType.INT));
      macClazz.withMethod("select", DataType.ref(enumeration), new Parameter(DataType.INT));
      
//      model.getGenerator().withShowDiff(DIFF.FULL);
      model.generate("src/test/java");
      
      story.addClassDiagram(model);
      
      story.assertEquals("Number of Enumeration types in the model: " , 1, model.getEnumerations().size());
      
      story.dumpHTML();
      
   }
}
