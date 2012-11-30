package org.sdmlib.model.classes;

import org.junit.Test;
import org.sdmlib.model.classes.creators.CreatorCreator;
import org.sdmlib.model.classes.test.NoProperties;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.scenarios.Scenario;

public class EmptyClassTest
{
   @Test
   public void testEmptyClass()
   {
      ClassModel model = new ClassModel("org.sdmlib.model.classes.test");
      
      model.createClazz("NoProperties");
            
      model.generate("test");
      
      NoProperties noPropertiesObj = new NoProperties();
      
      Scenario scenario = new Scenario("test");
      
      scenario.addObjectDiag(org.sdmlib.model.classes.test.creators.CreatorCreator.createIdMap("test"), noPropertiesObj);
      
      scenario.dumpHTML();
   }
}
