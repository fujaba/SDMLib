package org.sdmlib.test.examples.simpleEnumModel;


import static org.sdmlib.models.classes.DataType.*;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.storyboards.StoryPage;

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
      
      Enumeration enumeration = model.createEnumeration("TEnum");
      enumeration.withValueNames("T1", "T2", "12", "T1000");
      enumeration.withMethod("toString", STRING);
     
      Clazz alexClazz = model.createClazz("Alex");
      alexClazz.withAttribute("Name", STRING);
          
      Clazz macClazz = model.createClazz("Mac");
      macClazz.withAttribute("Name", STRING)
      .withAttribute("type", enumeration)
      .withAttribute("owner", alexClazz);
      
      macClazz.withMethod("concat", STRING, new Parameter("p1", INT));
      macClazz.withMethod("select", enumeration, new Parameter("p1", INT));
      
//      model.getGenerator().withShowDiff(DIFF.FULL);
      model.generate("src/test/java");
      
      story.addClassDiagram(model);
      
      story.assertEquals("Number of Enumeration types in the model: " , 1, model.getEnumerations().size());
      
      story.dumpHTML();
      
   }
}
