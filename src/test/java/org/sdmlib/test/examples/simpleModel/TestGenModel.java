package org.sdmlib.test.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Modifier;
import org.sdmlib.storyboards.Storyboard;

public class TestGenModel
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz arrayListClazz = model.createClazz("java.util.ArrayList").withExternal(true);
      Clazz createClazz = model.createClazz("MacList");
      createClazz.createAttribute("serialVersionUID", DataType.LONG).withInitialization("1L").with(Modifier.STATIC, Modifier.FINAL, Modifier.PRIVATE);
      
      createClazz.withAttribute("Name", DataType.STRING);
      createClazz.withSuperClazz(arrayListClazz);
      
      // Enable Special Thinks
      model.generate("src/test/java");
   }
   
   @Test
   public void testUniDirectionalAssoc()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz bigBrother = model.createClazz("BigBrother");
      
      Clazz person = model.createClazz("Person")
            .withAttribute("name", DataType.STRING);
      
      bigBrother.withAssoc(person, "noOne", Card.ONE);
      bigBrother.withAssoc(person, "suspects", Card.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
      
   }

   @Test
   public void testUniDirectionalAssocToObject()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.test.examples.simpleModel.model");
      
      Clazz bigBrother = model.createClazz("BigBrother");
      
      Clazz person = model.createClazz(Object.class.getName()).withExternal(true);
      
      bigBrother.withAssoc(person, "kids", Card.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
      
   }
}
