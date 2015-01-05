package org.sdmlib.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Visibility;
import org.sdmlib.storyboards.Storyboard;

public class testGenModel
{
   @Test
   public void testSimpleModel(){
      ClassModel model = new ClassModel("org.sdmlib.examples.simpleModel.model");
      
      Clazz arrayListClazz = model.createClazz("java.util.ArrayList").withExternal(true);
      Clazz createClazz = model.createClazz("MacList");
      createClazz.createAttribute("serialVersionUID", DataType.LONG).withInitialization("1L").with(Visibility.STATIC, Visibility.FINAL, Visibility.PRIVATE);
      
      createClazz.withAttribute("Name", DataType.STRING);
      createClazz.withSuperClazz(arrayListClazz);
      
      // Enable Special Thinks
      model.generate("src/test/java");
   }
   
   @Test
   public void testUniDirectionalAssoc()
   {
      Storyboard story = new Storyboard();
      
      ClassModel model = new ClassModel("org.sdmlib.examples.simpleModel.model");
      
      Clazz bigBrother = model.createClazz("BigBrother");
      
      Clazz person = model.createClazz("Person");
      
      bigBrother.withUniDirectionalAssoc(person, "noOne", Card.ONE);
      bigBrother.withUniDirectionalAssoc(person, "suspects", Card.MANY);
      
      story.addClassDiagram(model);
      
      model.generate("src/test/java");
      
      story.dumpHTML();
      
   }
}
