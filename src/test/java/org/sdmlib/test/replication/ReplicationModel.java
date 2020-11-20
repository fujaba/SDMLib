package org.sdmlib.test.replication;

import java.beans.PropertyChangeEvent;
import java.net.Socket;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardImpl;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

//
public class ReplicationModel {
  private static final String CHANGE_HISTORY = "ChangeHistory";
  private static final String REPLICATION_NODE = "ReplicationNode";

  /**
   * <p>
   * Storyboard <a href='./src/test/java/org/sdmlib/test/replication/ReplicationModel.java' type=
   * 'text/x-java'>MinChangeModel</a>
   * </p>
   * <script> var json = { "typ":"class", "nodes":[ { "typ":"node", "id":"ChangeEvent", "attributes":[
   * "changeNo : String", "newValue : String", "objectId : String", "objectType : String", "oldValue :
   * String", "property : String", "propertyKind : String", "sessionId : String", "valueType : String"
   * ] } ], "edges":[] } ; new Graph(json, {"canvasid":"canvasMinChangeModelClassDiagram0",
   * "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100); </script>
   * 
   * @see <a href='../../../../../../../doc/MinChangeModel.html'>MinChangeModel.html</a>
   */
  // @Test
  public void MinChangeModel() {
    StoryboardImpl story = new StoryboardImpl();

    ClassModel model = new ClassModel("org.sdmlib.replication");

    Clazz changeEvent = model.createClazz("ChangeEvent")
        .withAttribute("objectId", DataType.STRING)
        .withAttribute("objectType", DataType.STRING)
        .withAttribute("property", DataType.STRING)
        .withAttribute("newValue", DataType.STRING)
        .withAttribute("oldValue", DataType.STRING)
        .withAttribute("valueType", DataType.STRING)
        .withAttribute("propertyKind", DataType.STRING)
        .withAttribute("changeNo", DataType.STRING)
        .withAttribute("sessionId", DataType.STRING);

    story.addClassDiagram(model);

    model.generate("src/main/replication");

    story.dumpHTML();
  }


  /**
   * 
   * <p>
   * Storyboard <a href='./src/test/java/org/sdmlib/test/replication/ReplicationModel.java' type=
   * 'text/x-java'>SeppelModel</a>
   * </p>
   * <script> var json = { "typ":"class", "nodes":[ { "typ":"node", "id":"BoardTask" }, {
   * "typ":"node", "id":"SeppelChannel", "attributes":[ "loginValidated : boolean", "socket : Socket"
   * ] }, { "typ":"node", "id":"SeppelScope", "attributes":[ "scopeName : String" ] }, { "typ":"node",
   * "id":"SeppelSpace", "attributes":[ "history : ChangeEventList", "javaFXApplication : boolean",
   * "lastChangeId : long", "spaceId : String" ] }, { "typ":"node", "id":"SeppelSpaceProxy",
   * "attributes":[ "acceptsConnectionRequests : boolean", "hostName : String", "loginName : String",
   * "password : String", "portNo : int", "spaceId : String" ] }, { "typ":"node", "id":"Object" }, {
   * "typ":"node", "id":"Thread" }, { "typ":"node", "id":"Socket" }, { "typ":"node",
   * "id":"ChangeEventList" } ], "edges":[ { "typ":"assoc", "source":{ "id":"BoardTask",
   * "cardinality":"many", "property":"tasks" }, "target":{ "id":"SeppelSpaceProxy",
   * "cardinality":"one", "property":"proxy" } }, { "typ":"assoc", "source":{ "id":"SeppelChannel",
   * "cardinality":"one", "property":"channel" }, "target":{ "id":"SeppelSpaceProxy",
   * "cardinality":"one", "property":"seppelSpaceProxy" } }, { "typ":"generalisation", "source":{
   * "id":"SeppelChannel", "cardinality":"one", "property":"seppelchannel" }, "target":{
   * "id":"Thread", "cardinality":"one", "property":"thread" } }, { "typ":"assoc", "source":{
   * "id":"SeppelScope", "cardinality":"many", "property":"scopes" }, "target":{
   * "id":"SeppelSpaceProxy", "cardinality":"many", "property":"spaces" } }, { "typ":"assoc",
   * "source":{ "id":"SeppelScope", "cardinality":"many", "property":"subScopes" }, "target":{
   * "id":"SeppelScope", "cardinality":"many", "property":"superScopes" } }, { "typ":"unidirectional",
   * "source":{ "id":"Object", "cardinality":"many", "property":"observedObjects" }, "target":{
   * "id":"SeppelScope", "cardinality":"one", "property":"seppelscope" } }, { "typ":"generalisation",
   * "source":{ "id":"SeppelSpace", "cardinality":"one", "property":"seppelspace" }, "target":{
   * "id":"Thread", "cardinality":"one", "property":"thread" } }, { "typ":"assoc", "source":{
   * "id":"SeppelSpaceProxy", "cardinality":"many", "property":"partners" }, "target":{
   * "id":"SeppelSpaceProxy", "cardinality":"many", "property":"partners" } }, { "typ":"assoc",
   * "source":{ "id":"SeppelSpaceProxy", "cardinality":"one", "property":"proxy" }, "target":{
   * "id":"BoardTask", "cardinality":"many", "property":"tasks" } }, { "typ":"assoc", "source":{
   * "id":"SeppelSpaceProxy", "cardinality":"one", "property":"seppelSpaceProxy" }, "target":{
   * "id":"SeppelChannel", "cardinality":"one", "property":"channel" } }, { "typ":"assoc", "source":{
   * "id":"SeppelSpaceProxy", "cardinality":"many", "property":"spaces" }, "target":{
   * "id":"SeppelScope", "cardinality":"many", "property":"scopes" } }, { "typ":"unidirectional",
   * "source":{ "id":"Object", "cardinality":"many", "property":"observedObjects" }, "target":{
   * "id":"SeppelScope", "cardinality":"one", "property":"seppelscope" } }, { "typ":"generalisation",
   * "source":{ "id":"SeppelSpace", "cardinality":"one", "property":"seppelspace" }, "target":{
   * "id":"Thread", "cardinality":"one", "property":"thread" } }, { "typ":"generalisation", "source":{
   * "id":"SeppelChannel", "cardinality":"one", "property":"seppelchannel" }, "target":{
   * "id":"Thread", "cardinality":"one", "property":"thread" } } ] } ; new Graph(json,
   * {"canvasid":"canvasSeppelModelClassDiagram0", "display":"html", fontsize:10, bar:false,
   * propertyinfo:false}).layout(100,100); </script>
   * 
   * @see <a href='../../../../../../doc/SeppelModel.html'>SeppelModel.html</a>
   * @see <a href='../../../../../../../doc/SeppelModel.html'>SeppelModel.html</a>
   */
  @Test
  public void testSeppelModel() {
    StoryboardImpl story = new StoryboardImpl();

    ClassModel model = new ClassModel("org.sdmlib.replication");

    // seppel spaces
    Clazz thread = model.createClazz(Thread.class.getName()).withExternal(true);

    Clazz seppelSpace = model.createClazz("SeppelSpace")
        .withAttribute("spaceId", DataType.STRING)
        .withAttribute("history", DataType.create("org.sdmlib.replication.ChangeEventList"))
        .withAttribute("lastChangeId", DataType.LONG)
        .withAttribute("javaFXApplication", DataType.BOOLEAN)
        .withSuperClazz(thread);

    Clazz seppelSpaceProxy = model.createClazz("SeppelSpaceProxy")
        .withAttribute("spaceId", DataType.STRING)
        .withAttribute("acceptsConnectionRequests", DataType.BOOLEAN)
        .withAttribute("hostName", DataType.STRING)
        .withAttribute("portNo", DataType.INT)
        .withAttribute("loginName", DataType.STRING)
        .withAttribute("password", DataType.STRING);

    seppelSpaceProxy.withBidirectional(seppelSpaceProxy, "partners", Association.MANY, "partners", Association.MANY);

    Clazz seppelScope = model.createClazz("SeppelScope")
        .withAttribute("scopeName", DataType.STRING);

    seppelScope.withBidirectional(seppelScope, "subScopes", Association.MANY, "superScopes", Association.MANY);

    seppelSpaceProxy.withBidirectional(seppelScope, "scopes", Association.MANY, "spaces", Association.MANY);

    Clazz object = model.createClazz(Object.class.getName()).withExternal(true);

    seppelScope.withUniDirectional(object, "observedObjects", Association.MANY);

    Clazz seppelChannel = model.createClazz("SeppelChannel")
        .withSuperClazz(thread)
        .withAttribute("socket", DataType.create(Socket.class))
        .withAttribute("loginValidated", DataType.BOOLEAN);


    seppelSpaceProxy.withBidirectional(seppelChannel, "channel", Association.ONE, "seppelSpaceProxy", Association.ONE);

    Clazz boardTask = model.createClazz("BoardTask");

    seppelSpaceProxy.withBidirectional(boardTask, "tasks", Association.MANY, "proxy", Association.ONE);

    model.generate("src/main/replication");

    story.addClassDiagram(model);


    story.dumpHTML();
  }

  // deprecated @Test
  public void testReplicationModel() {
    main(null);
  }

  /**
   * 
   * @see <a href='../../../../../../../doc/ReplicationModel.html'>ReplicationModel.html</a>
   */
  public static void main(String[] args) {
    // file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/ReplicationModel.html
    Storyboard storyboard = new Storyboard();

    ClassModel model = new ClassModel("org.sdmlib.replication");

    Clazz thread = model.createClazz(Thread.class.getName()).withExternal(true);

    Clazz socket = model.createClazz(Socket.class.getName()).withExternal(true);

    Clazz replicationNode = model.createClazz(REPLICATION_NODE);

    Clazz changeHistory = model.createClazz(CHANGE_HISTORY);

    Clazz sharedSpace = model.createClazz("SharedSpace")
        .withAttribute("spaceId", DataType.STRING)
        .withUniDirectional(changeHistory, "history", Association.ONE)
        .withAttribute("lastChangeId", DataType.LONG)
        .withAttribute("nodeId", DataType.STRING)
        .withAttribute("javaFXApplication", DataType.BOOLEAN)
        .withSuperClazz(thread);

    replicationNode.withBidirectional(sharedSpace, "sharedSpaces", Association.MANY, "node", Association.ONE);


    Clazz replicationChannel = model.createClazz("ReplicationChannel")
        .withSuperClazz(thread)
        .withAttribute("socket", DataType.create(Socket.class))
        .withAttribute("targetNodeId", DataType.STRING);

    sharedSpace.withBidirectional(replicationChannel, "channels", Association.MANY, "sharedSpace", Association.ONE);

    Clazz replicationServer = model.createClazz("ReplicationServer")
        .withSuperClazz(replicationNode);

    Clazz serverSocketAcceptThread = model.createClazz("ServerSocketAcceptThread")
        .withSuperClazz(thread)
        .withAttribute("port", DataType.INT)
        .withAttribute("replicationNode", DataType.create(replicationNode));

    Clazz task = model.createClazz("Task");

    Clazz logEntry = model.createClazz("LogEntry")
        .withAttribute("stepName", DataType.STRING)
        .withAttribute("executedBy", DataType.STRING)
        .withAttribute("timeStamp", DataType.LONG);


    task.withBidirectional(logEntry, "logEntries", Association.MANY, "task", Association.ONE);


    Clazz change = model.createClazz("ReplicationChange")
        .withSuperClazz(task)
        .withAttribute("historyIdPrefix", DataType.STRING)
        .withAttribute("historyIdNumber", DataType.LONG)
        .withAttribute("targetObjectId", DataType.STRING)
        .withAttribute("targetProperty", DataType.STRING)
        .withAttribute("isToManyProperty", DataType.BOOLEAN)
        .withAttribute("changeMsg", DataType.STRING);

    changeHistory.withBidirectional(change, "changes", Association.MANY, "history", Association.ONE);

    // replicated task flow model
    Clazz remoteTaskBoard = model.createClazz("RemoteTaskBoard");

    Clazz lane = model.createClazz("Lane")
        .withAttribute("name", DataType.STRING);

    remoteTaskBoard.withBidirectional(lane, "lanes", Association.MANY, "board", Association.ONE);

    Clazz boardTask = model.createClazz("BoardTask")
        .withSuperClazz(task)
        .withAttribute("name", DataType.STRING)
        .withAttribute("status", DataType.STRING)
        .withAttribute("manualExecution", DataType.BOOLEAN)
        .withAttribute("stashedPropertyChangeEvent", DataType.create(PropertyChangeEvent.class))
        .withMethod("execute", DataType.VOID);

    Clazz runnable = model.createClazz("java.lang.Runnable")
        .withExternal(true)
        .enableInterface();

    Clazz remoteTask = model.createClazz("RemoteTask")
        .withSuperClazz(task)
        .withSuperClazz(runnable)
        .withAttribute("boardTask", DataType.create(boardTask));

    lane.withBidirectional(boardTask, "tasks", Association.MANY, "lane", Association.ONE);

    boardTask.withBidirectional(boardTask, "next", Association.MANY, "prev", Association.MANY);

    Clazz replicationRoot = model.createClazz("ReplicationRoot")
        .withAttribute("name", DataType.STRING)
        .withAttribute("applicationObject", DataType.OBJECT);

    replicationRoot.withBidirectional(replicationRoot, "kids", Association.MANY, "parent", Association.ONE);

    model.generate("src/main/replication");

    storyboard.addClassDiagram(model);

    storyboard.dumpHTML();
  }
}
