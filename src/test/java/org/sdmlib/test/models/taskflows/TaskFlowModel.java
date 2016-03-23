package org.sdmlib.test.models.taskflows;

import java.util.Timer;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.taskflows.PeerProxy;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class TaskFlowModel
{
     /**
    * 
    * @see <a href='../../../../../../../doc/taskFlowModel.html'>taskFlowModel.html</a>
* @see <a href='../../../../../../../../doc/taskFlowModel.html'>taskFlowModel.html</a>
 */
   @Test
   public void taskFlowModel()
   {
      Storyboard storyboard = new Storyboard();

      ClassModel model = new ClassModel("org.sdmlib.models.taskflows");

      Clazz taskFlowClass = model.createClazz("TaskFlow");
      taskFlowClass.createAttribute("taskNo", DataType.INT);
      taskFlowClass.createAttribute("idMap", DataType.create(SDMLibJsonIdMap.class));

      taskFlowClass.withBidirectional(taskFlowClass, "subFlow", Cardinality.ONE, "parent", Cardinality.ONE);

      Clazz peerProxy = model.createClazz("PeerProxy");
      peerProxy.createAttribute("ip", DataType.STRING);
      peerProxy.createAttribute("port", DataType.INT);
      peerProxy.createAttribute("idMap", DataType.create(SDMLibJsonIdMap.class));

      model.createClazz("SocketThread")
      .withAttribute("ip", DataType.STRING)
      .withAttribute("port", DataType.INT)
      .withAttribute("idMap", DataType.create(SDMLibJsonIdMap.class))
      .withAttribute("defaultTargetThread", DataType.OBJECT);

      model.createClazz("FetchFileFlow")
      .withAttribute("fileServer", DataType.create(PeerProxy.class))
      .withAttribute("fileName", DataType.STRING)
      .withSuperClazz(taskFlowClass)
      .withMethod("run", DataType.VOID);

      Clazz loggerClazz = model.createClazz("Logger")
            .withAttribute("startPeer", DataType.create(PeerProxy.class))
            .withSuperClazz(taskFlowClass);

      Clazz logEntryClass = model.createClazz("LogEntry")
            .withAttribute("nodeName", DataType.STRING)
            .withAttribute("taskName", DataType.STRING);
            
      loggerClazz.withBidirectional(logEntryClass, "entries", Cardinality.MANY, "logger", Cardinality.ONE);

      logEntryClass.withBidirectional(logEntryClass, "children", Cardinality.MANY, "parent", Cardinality.ONE);

      Clazz timerClass = model.createClazz(Timer.class.getName())
            .withExternal(true);

      model.createClazz("SDMTimer")
      .withSuperClazz(timerClass);
      // .withMethod("schedule", DataType.VOID, new Parameter(DataType.create(TimerTask.class)));

      storyboard.addClassDiagram(model);

      model.generate("src/main/replication");

      storyboard.dumpHTML();
   }

}
