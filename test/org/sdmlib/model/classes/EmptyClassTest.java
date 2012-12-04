package org.sdmlib.model.classes;

import org.junit.Test;
import org.sdmlib.model.classes.creators.CreatorCreator;
import org.sdmlib.model.classes.test.NoProperties;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.scenarios.Scenario;

public class EmptyClassTest
{
   @Test
   public void testEmptyClass()
   {
      Scenario scenario = new Scenario("test");
      
      ClassModel model = new ClassModel("org.sdmlib.model.classes.test");
      
      model.createClazz("NoProperties");
      
      Clazz parent = model.createClazz("Parent", "name", "String");
      
      parent.createClassAndAssoc("Uncle", "uncle", R.ONE, "brother", R.ONE);
            
      model.createClazz("Kid").withSuperClass(parent);
      
      // model.removeAllGeneratedCode("test", "test", "test");

      model.generate("test");
      
      scenario.addImage(model.dumpClassDiag("test", "EmptyClassTestClasses01"));
      
      NoProperties noPropertiesObj = new NoProperties();
      
      scenario.addObjectDiag(org.sdmlib.model.classes.test.creators.CreatorCreator.createIdMap("test"), noPropertiesObj);
      
      scenario.add("Simple object without properties creates object diagram,  now", 
         "done", "zuendorf", "30.11.2012 22:55:42", 1, 0);
      
      scenario.dumpHTML();
   }
}