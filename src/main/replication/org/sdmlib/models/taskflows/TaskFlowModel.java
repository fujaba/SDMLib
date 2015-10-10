package org.sdmlib.models.taskflows;

import java.util.Timer;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;

public class TaskFlowModel
{
   private static final String STRING = "String";

   @Test
   public void taskFlowModel()
   {
      Storyboard storyboard = new Storyboard();

      ClassModel model = new ClassModel("org.sdmlib.models.taskflows");

      Clazz taskFlowClass = model.createClazz("TaskFlow")
            .withAttribute("taskNo", DataType.INT)
            .withAttribute("idMap", DataType.ref(SDMLibJsonIdMap.class));

      taskFlowClass.withAssoc(taskFlowClass, "subFlow", Card.ONE, "parent", Card.ONE);

      model.createClazz("PeerProxy")
      .withAttribute("ip", DataType.STRING)
      .withAttribute("port", DataType.INT)
      .withAttribute("idMap", DataType.ref(SDMLibJsonIdMap.class));

      model.createClazz("SocketThread")
      .withAttribute("ip", DataType.STRING)
      .withAttribute("port", DataType.INT)
      .withAttribute("idMap", DataType.ref(SDMLibJsonIdMap.class))
      .withAttribute("defaultTargetThread", DataType.OBJECT);

      model.createClazz("FetchFileFlow")
      .withAttribute("fileServer", DataType.ref(PeerProxy.class))
      .withAttribute("fileName", DataType.STRING)
      .withSuperClazz(taskFlowClass)
      .withMethod("run", DataType.VOID);

      Clazz loggerClazz = model.createClazz("Logger")
            .withAttribute("startPeer", DataType.ref(PeerProxy.class))
            .withSuperClazz(taskFlowClass);

      Clazz logEntryClass = model.createClazz("LogEntry")
            .withAttribute("nodeName", DataType.STRING)
            .withAttribute("taskName", DataType.STRING);
            
      loggerClazz.withAssoc(logEntryClass, "entries", Card.MANY, "logger", Card.ONE);

      logEntryClass.withAssoc(logEntryClass, "children", Card.MANY, "parent", Card.ONE);

      Clazz timerClass = model.createClazz(Timer.class.getName())
            .withExternal(true);

      model.createClazz("SDMTimer")
      .withSuperClazz(timerClass);
      // .withMethod("schedule", DataType.VOID, new Parameter(DataType.ref(TimerTask.class)));

      storyboard.addClassDiagram(model);

      model.generate("src/main/replication");

      storyboard.dumpHTML();
   }

}
