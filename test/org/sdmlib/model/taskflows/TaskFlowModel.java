package org.sdmlib.model.taskflows;

import java.util.Timer;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class TaskFlowModel 
{
   @Test
   public void taskFlowModel()
   {
      Scenario scenario = new Scenario("test");
      
      ClassModel model = new ClassModel("org.sdmlib.model.taskflows");
      
      Clazz taskFlowClass = model.createClazz("TaskFlow", "taskNo", "int");
      
      model.createClazz("PeerProxy", 
         "ip", "String", 
         "port", "int", 
         "idMap", SDMLibJsonIdMap.class.getName());
      
      model.createClazz("SocketThread", 
         "ip", "String", 
         "port", "int", 
         "idMap", SDMLibJsonIdMap.class.getName(),
         "defaultTargetThread", "Object");
      
      model.createClazz("FetchFileFlow",
         "fileServer", PeerProxy.class.getName(),
         "idMap", SDMLibJsonIdMap.class.getName(), 
         "fileName", String.class.getSimpleName())
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");
      
      Clazz timerClass = model.createClazz(Timer.class.getName()).withExternal(true);
      
      model.createClazz("SDMTimer").withSuperClass(timerClass)
      .withMethods(new Method("schedule(TimerTask)", "void"));
      
      scenario.addImage(model.dumpClassDiag("taskflowmodeldiag"));
      
      
      model.generate("swtsrc", "swtsrc"); 
      
      scenario.dumpHTML();
   }
  
}
