package org.sdmlib.test.examples.modelcouch;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelCouchTasksModel {
  /**
   * 
   * <p>
   * Storyboard
   * <a href='./src/test/java/org/sdmlib/test/examples/modelcouch/ModelCouchTasksModel.java' type=
   * 'text/x-java'>couchSpaceTasksModel</a>
   * </p>
   * <p>
   * Exchange tasks via couch space, i.e. couch databases
   * </p>
   * <script> var json = { "typ":"class", "nodes":[ { "typ":"node", "id":"Task", "attributes":[
   * "copySdmLibId : String", "title : String", "transitionCondition : String" ] }, { "typ":"node",
   * "id":"TaskFlow", "attributes":[ "title : String" ] }, { "typ":"node", "id":"User", "attributes":[
   * "name : String" ] }, { "typ":"node", "id":"UserGroup", "attributes":[ "name : String" ] } ],
   * "edges":[ { "typ":"assoc", "source":{ "id":"Task", "cardinality":"many", "property":"firstTasks"
   * }, "target":{ "id":"TaskFlow", "cardinality":"one", "property":"taskFlowFirst" } }, {
   * "typ":"assoc", "source":{ "id":"Task", "cardinality":"many", "property":"handledTasks" },
   * "target":{ "id":"User", "cardinality":"one", "property":"handledBy" } }, { "typ":"assoc",
   * "source":{ "id":"Task", "cardinality":"many", "property":"responsible" }, "target":{
   * "id":"UserGroup", "cardinality":"many", "property":"responsibles" } }, { "typ":"assoc",
   * "source":{ "id":"Task", "cardinality":"many", "property":"tasks" }, "target":{ "id":"TaskFlow",
   * "cardinality":"one", "property":"taskFlow" } }, { "typ":"assoc", "source":{ "id":"Task",
   * "cardinality":"one", "property":"transitionSource" }, "target":{ "id":"Task",
   * "cardinality":"many", "property":"transitionTargets" } }, { "typ":"assoc", "source":{
   * "id":"TaskFlow", "cardinality":"one", "property":"taskFlow" }, "target":{ "id":"Task",
   * "cardinality":"many", "property":"tasks" } }, { "typ":"assoc", "source":{ "id":"TaskFlow",
   * "cardinality":"one", "property":"taskFlowFirst" }, "target":{ "id":"Task", "cardinality":"many",
   * "property":"firstTasks" } }, { "typ":"assoc", "source":{ "id":"User", "cardinality":"one",
   * "property":"handledBy" }, "target":{ "id":"Task", "cardinality":"many", "property":"handledTasks"
   * } }, { "typ":"assoc", "source":{ "id":"User", "cardinality":"many", "property":"members" },
   * "target":{ "id":"UserGroup", "cardinality":"many", "property":"groups" } }, { "typ":"assoc",
   * "source":{ "id":"UserGroup", "cardinality":"many", "property":"groups" }, "target":{ "id":"User",
   * "cardinality":"many", "property":"members" } }, { "typ":"assoc", "source":{ "id":"UserGroup",
   * "cardinality":"many", "property":"responsibles" }, "target":{ "id":"Task", "cardinality":"many",
   * "property":"responsible" } } ] } ; new Graph(json,
   * {"canvasid":"canvascouchSpaceTasksModelClassDiagram1", "display":"html", fontsize:10, bar:false,
   * propertyinfo:false}).layout(100,100); </script>
   * 
   * @see <a href=
   *      '../../../../../../../../../doc/couchSpaceTasksModel.html'>couchSpaceTasksModel.html</a>/n
   *      * @see <a href=
   *      '../../../../../../../../doc/couchSpaceTasksModel.html'>couchSpaceTasksModel.html</a>
   */
  // @Test
  public void couchSpaceTasksModel() throws Exception {
    Storyboard story = new Storyboard();

    story.add("Exchange tasks via couch space, i.e. couch databases");

    ClassModel model = new ClassModel("org.sdmlib.test.examples.couchspace.tasks");

    // classes
    Clazz taskFlow = model.createClazz("TaskFlow")
        .withAttribute("title", DataType.STRING);

    Clazz task = model.createClazz("Task")
        .withAttribute("title", DataType.STRING)
        .withAttribute("transitionCondition", DataType.STRING)
        .withAttribute("copySdmLibId", DataType.STRING);

    Clazz userGroup = model.createClazz("UserGroup")
        .withAttribute("name", DataType.STRING);

    Clazz user = model.createClazz("User")
        .withAttribute("name", DataType.STRING);

    // assocs
    taskFlow
        .withBidirectional(task, "tasks", Association.MANY, "taskFlow", Association.ONE)
        .withBidirectional(task, "firstTasks", Association.MANY, "taskFlowFirst", Association.ONE);

    task
        .withBidirectional(task, "transitionSource", Association.ONE, "transitionTargets", Association.MANY)
        .withBidirectional(userGroup, "responsibles", Association.MANY, "responsible", Association.MANY)
        .withBidirectional(user, "handledBy", Association.ONE, "handledTasks", Association.MANY);

    userGroup
        .withBidirectional(user, "members", Association.MANY, "groups", Association.MANY);

    model.generate("src/test/java");
    story.addClassDiagram(model);
    story.dumpHTML();
  }
}
