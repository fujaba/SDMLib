package org.sdmlib.test.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Modifier;

public class TestGenModel
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz arrayListClazz = model.createClazz("java.util.ArrayList").withExternal(true);
      Clazz createClazz = model.createClazz("MacList");
      createClazz.createAttribute("serialVersionUID", DataType.LONG).withValue("1L").with(Modifier.STATIC, Modifier.FINAL, Modifier.PRIVATE);
      
      createClazz.withAttribute("Name", DataType.STRING);
      createClazz.withSuperClazz(arrayListClazz);
      
      // Enable Special Thinks
      model.generate("src/test/java");
   }
   
     /**
    * 
    * @see <a href='../../../../../../../../doc/UniDirectionalAssoc.html'>UniDirectionalAssoc.html</a>
*/
   @Test
   public void testUniDirectionalAssoc()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz bigBrother = model.createClazz("BigBrother");
      
      Clazz person = model.createClazz("Person")
            .withAttribute("name", DataType.STRING);
      
      bigBrother.withUniDirectional(person, "noOne", Cardinality.ONE);
      bigBrother.withUniDirectional(person, "suspects", Cardinality.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
      
   }

     /**
    * 
    * @see <a href='../../../../../../../../doc/UniDirectionalAssoc.html'>UniDirectionalAssoc.html</a>
* @see <a href='../../../../../../../../doc/UniDirectionalAssocToObject.html'>UniDirectionalAssocToObject.html</a>
*/
   @Test
   public void testUniDirectionalAssocToObject()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz bigBrother = model.createClazz("BigBrother");
      
      Clazz person = model.createClazz(Object.class.getName()).withExternal(true);
      
      bigBrother.withUniDirectional(person, "kids", Cardinality.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
      
   }
}
