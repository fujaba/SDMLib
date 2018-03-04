package org.sdmlib.test.mikado;

import static de.uniks.networkparser.graph.Cardinality.*;
import de.uniks.networkparser.graph.Clazz;
import static de.uniks.networkparser.graph.DataType.*;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;

public class MikadoMethodModel
{
     /**
    * 
    * @see <a href='../../../../../../../doc/MikadoModel.html'>MikadoModel.html</a>
 */
   @Test
   public void testMikadoModel()
   {
      Storyboard story = new Storyboard();

      ClassModel model = new ClassModel("org.sdmlib.storyboards");

      Clazz goal = model.createClazz("Goal")
              .withAttribute("description", STRING)
              .withAttribute("hoursTodo", DOUBLE)
              .withAttribute("hoursDone", DOUBLE)
              ;

      goal.withBidirectional(goal, "preGoals", MANY, "parents", MANY);

      Clazz mikadoLog = model.createClazz("MikadoLog");

      Clazz logEntry = model.createClazz("LogEntry")
              .withAttribute("date", STRING)
              .withAttribute("hoursDone", DOUBLE)
              .withAttribute("hoursRemaining", DOUBLE);

      mikadoLog.withUniDirectional(goal, "mainGoal", ONE);

      mikadoLog.withBidirectional(logEntry,"entries", MANY, "parent", ONE);

      logEntry.withUniDirectional(goal, "goal", ONE);

      story.addClassDiagram(model);

      model.generate("src/main/java");

      story.dumpHTML();
   }

}
