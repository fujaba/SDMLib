package org.sdmlib.examples.adamandeve;

import org.junit.Test;
import org.sdmlib.model.taskflows.PeerProxy;
import org.sdmlib.model.taskflows.TaskFlow;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonIdMap;

public class AdamAndEveModel
{
   @Test
   public void adamAndEveModel()
   {
      Scenario scenario = new Scenario("examples");

      ClassModel model = new ClassModel("org.sdmlib.examples.adamandeve");
      
      model.createClazz("Eve");

      model.createClazz("Adam");

      Clazz taskFlowClazz = model.createClazz(TaskFlow.class.getName(), 
         "taskNo", "int")
         .withExternal(true);
      
      model.createClazz(JsonIdMap.class.getName()).withExternal(true);
      
      model.createClazz(PeerProxy.class.getName(), 
         "ip", "String", 
         "port", "int", 
         "idMap", JsonIdMap.class.getName()).withExternal(true);
      
      model.createClazz("UpdateAdamFlow",
         "adam", PeerProxy.class.getName(),
         "eve", PeerProxy.class.getName(),
         "idMap", JsonIdMap.class.getName(), 
         "adamJarAtEveSiteLastModified", long.class.getSimpleName())
         .withSuperClass(taskFlowClazz)
         .createMethods("run()", "void");
      
      model.generate("examples");

      scenario.addImage(model.dumpClassDiag("AdamAndEveModel"));

      scenario.dumpHTML();
   }
}
