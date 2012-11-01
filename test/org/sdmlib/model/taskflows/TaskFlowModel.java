package org.sdmlib.model.taskflows;

import java.util.Timer;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import static org.sdmlib.models.classes.Role.R.*;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class TaskFlowModel 
{
   private static final String STRING = "String";

@Test
   public void taskFlowModel()
   {
      Scenario scenario = new Scenario("test");
      
      ClassModel model = new ClassModel("org.sdmlib.model.taskflows");
      
      Clazz taskFlowClass = model.createClazz("TaskFlow",
         "taskNo", "int",
         "idMap", SDMLibJsonIdMap.class.getName());
      
      model.createClazz("PeerProxy", 
         "ip", STRING, 
         "port", "int", 
         "idMap", SDMLibJsonIdMap.class.getName());
      
      model.createClazz("SocketThread", 
         "ip", STRING, 
         "port", "int", 
         "idMap", SDMLibJsonIdMap.class.getName(),
         "defaultTargetThread", "Object");
      
      model.createClazz("FetchFileFlow",
         "fileServer", PeerProxy.class.getName(),
         "idMap", SDMLibJsonIdMap.class.getName(), 
         "fileName", String.class.getSimpleName())
         .withSuperClass(taskFlowClass)
         .createMethods("run()", "void");
      
      Clazz loggerClazz = model.createClazz("Logger",
    	 "startPeer", PeerProxy.class.getName())
    	 .withSuperClass(taskFlowClass)
    	 .withAssoc(taskFlowClass, "targetTaskFlow", ONE, "logger", ONE);
      
      Clazz logEntryClass = loggerClazz.createClassAndAssoc("LogEntry", "entries", MANY, "logger", ONE);
      logEntryClass.withAssoc(logEntryClass, "children", MANY, "parent", ONE);
      logEntryClass.withAttributes(
    		  "nodeName", STRING,
    		  "taskName", STRING);
      
      Clazz timerClass = model.createClazz(Timer.class.getName()).withExternal(true);
      
      model.createClazz("SDMTimer").withSuperClass(timerClass)
      .withMethods(new Method("schedule(TimerTask)", "void"));
      
      scenario.addImage(model.dumpClassDiag("swtsrc", "taskflowmodeldiag"));
      
      
      model.generate("swtsrc", "swtsrc"); 
      
      scenario.addToDo("WrapExistingClasses", "backlog", "zuendorf", "01.11.2012 14:42:42", 0, 6);
      
      scenario.addToDo("SortIndex", "backlog", "zuendorf", "01.11.2012 14:43:42", 0, 2);
      
      scenario.dumpHTML();
   }
  
}
