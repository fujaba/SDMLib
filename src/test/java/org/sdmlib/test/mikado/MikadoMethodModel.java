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
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/mikado/MikadoMethodModel.java' type='text/x-java'>MikadoModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Goal",
    *          "attributes":[
    *             "description : String",
    *             "hoursDone : double",
    *             "hoursTodo : double"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"LogEntry",
    *          "attributes":[
    *             "date : String",
    *             "hoursDone : double",
    *             "hoursRemaining : double"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"MikadoLog"
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Goal",
    *             "cardinality":"one",
    *             "property":"goal"
    *          },
    *          "target":{
    *             "id":"LogEntry",
    *             "cardinality":"one",
    *             "property":"logentry"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Goal",
    *             "cardinality":"one",
    *             "property":"mainGoal"
    *          },
    *          "target":{
    *             "id":"MikadoLog",
    *             "cardinality":"one",
    *             "property":"mikadolog"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Goal",
    *             "cardinality":"many",
    *             "property":"preGoals"
    *          },
    *          "target":{
    *             "id":"Goal",
    *             "cardinality":"many",
    *             "property":"parents"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"LogEntry",
    *             "cardinality":"many",
    *             "property":"entries"
    *          },
    *          "target":{
    *             "id":"MikadoLog",
    *             "cardinality":"one",
    *             "property":"parent"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Goal",
    *             "cardinality":"one",
    *             "property":"goal"
    *          },
    *          "target":{
    *             "id":"LogEntry",
    *             "cardinality":"one",
    *             "property":"logentry"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"MikadoLog",
    *             "cardinality":"one",
    *             "property":"parent"
    *          },
    *          "target":{
    *             "id":"LogEntry",
    *             "cardinality":"many",
    *             "property":"entries"
    *          }
    *       },
    *       {
    *          "typ":"unidirectional",
    *          "source":{
    *             "id":"Goal",
    *             "cardinality":"one",
    *             "property":"mainGoal"
    *          },
    *          "target":{
    *             "id":"MikadoLog",
    *             "cardinality":"one",
    *             "property":"mikadolog"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasMikadoModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
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
