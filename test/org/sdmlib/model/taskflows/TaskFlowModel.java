package org.sdmlib.model.taskflows;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.scenarios.Scenario;

public class TaskFlowModel 
{
   @Test
   public void taskFlowModel()
   {
      Scenario scenario = new Scenario("test");
      
      ClassModel model = new ClassModel("org.sdmlib.model.taskflows");
      
      Clazz taskFlowClass = model.createClazz("TaskFlow", "taskNo", "int");
      
      model.createClazz("PeerProxy", "ip", "String", "port", "int", "idMap", "org.sdmlib.serialization.json.JsonIdMap");
      
      model.createClazz("SocketThread", "ip", "String", "port", "int", "idMap", "org.sdmlib.serialization.json.JsonIdMap");
      
      scenario.addImage(model.dumpClassDiag("taskflowmodeldiag"));
      
      
      model.generate("swtsrc", "swtsrc"); 
      
      scenario.dumpHTML();
   }
  
}
