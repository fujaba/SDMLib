package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;

public class TestReverseEngineering {
	      @Test
      public void genModel() {
      	ClassModel clazzModel = new ClassModel("i.love.sdmlib");


      Clazz sendableEntityClass = clazzModel.createClazz("de.uniks.networkparser.interfaces.SendableEntity");

      Clazz sendableEntityCreatorClass = clazzModel.createClazz("de.uniks.networkparser.interfaces.SendableEntityCreator");

      Clazz simpleSetClass = clazzModel.createClazz("de.uniks.networkparser.list.SimpleSet");

      Clazz basicModelClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.BasicModel")
      .withMethod("addExample", DataType.VOID, new Parameter(DataType.ref("StoryPage")))
      .withMethod("basicModel", DataType.VOID)
      .withMethod("main", DataType.VOID, new Parameter(DataType.ref("String[]")));

      Clazz documentDataClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.DocumentData")
      .withSuperClazz(sendableEntityClass)
      .withMethod("getParentDataTransitive", DataType.ref("DocumentDataSet"))
      .withMethod("getSubDataTransitive", DataType.ref("DocumentDataSet"));

      sendableEntityClass.withAssoc(documentDataClass, "documentdata", Card.1, "sendableentity", Card.1);

      Clazz modelCouchTasksModelClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.ModelCouchTasksModel")
      .withMethod("couchSpaceTasksModel", DataType.VOID);

      Clazz modelCouchTasksTestClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.ModelCouchTasksTest")
      .withMethod("False", DataType.BOOLEAN)
      .withMethod("cleanup", DataType.VOID)
      .withMethod("createCouch", DataType.ref("ModelCouch"))
      .withMethod("testBasicModelOnTheCouch", DataType.VOID)
      .withMethod("testForConnection", DataType.VOID)
      .withMethod("testReplication", DataType.VOID)
      .withMethod("testSendAndConnection", DataType.VOID)
      .withMethod("testUserPrivileges", DataType.VOID);

      Clazz personClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.Person")
      .withSuperClazz(sendableEntityClass)
      .withMethod("getGroupsTransitive", DataType.ref("PersonSet"))
      .withMethod("getMembersTransitive", DataType.ref("PersonSet"));

      sendableEntityClass.withAssoc(personClass, "person", Card.1, "sendableentity", Card.1);

      Clazz taskClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.Task")
      .withSuperClazz(sendableEntityClass)
      .withMethod("getParentTasksTransitive", DataType.ref("TaskSet"))
      .withMethod("getSubTasksTransitive", DataType.ref("TaskSet"));

      taskClass.withAssoc(sendableEntityClass, "sendableentity", Card.1, "task", Card.1);

      Clazz documentDataCreatorClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.util.DocumentDataCreator")
      .withSuperClazz(sendableEntityCreatorClass)
      .withMethod("createIdMap", DataType.ref("IdMap"), new Parameter(DataType.STRING))
      .withMethod("getSendableInstance", DataType.OBJECT, new Parameter(DataType.BOOLEAN))
      .withMethod("getValue", DataType.OBJECT, new Parameter(DataType.OBJECT), new Parameter(DataType.STRING))
      .withMethod("removeObject", DataType.VOID, new Parameter(DataType.OBJECT))
      .withMethod("setValue", DataType.BOOLEAN, new Parameter(DataType.OBJECT), new Parameter(DataType.STRING), new Parameter(DataType.OBJECT), new Parameter(DataType.STRING));

      sendableEntityCreatorClass.withAssoc(documentDataCreatorClass, "documentdatacreator", Card.1, "sendableentitycreator", Card.1);

      Clazz documentDataSetClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.util.DocumentDataSet")
      .withSuperClazz(simpleSetClass)
      .withMethod("getEntryType", DataType.STRING)
      .withMethod("getLastEditor", DataType.ref("StringList"))
      .withMethod("getLastModified", DataType.ref("StringList"))
      .withMethod("getParentData", DataType.ref("DocumentDataSet"))
      .withMethod("getParentDataTransitive", DataType.ref("DocumentDataSet"))
      .withMethod("getPersons", DataType.ref("PersonSet"))
      .withMethod("getSubData", DataType.ref("DocumentDataSet"))
      .withMethod("getSubDataTransitive", DataType.ref("DocumentDataSet"))
      .withMethod("getTag", DataType.ref("StringList"))
      .withMethod("getTasks", DataType.ref("TaskSet"))
      .withMethod("getType", DataType.ref("StringList"))
      .withMethod("getValue", DataType.ref("StringList"))
      .withMethod("hasDocumentDataPO", DataType.ref("DocumentDataPO"))
      .withMethod("hasLastEditor", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("hasLastModified", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("hasParentData", DataType.ref("DocumentDataSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasPersons", DataType.ref("DocumentDataSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasSubData", DataType.ref("DocumentDataSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasTag", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("hasTasks", DataType.ref("DocumentDataSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasType", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("hasValue", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("with", DataType.ref("DocumentDataSet"), new Parameter(DataType.OBJECT))
      .withMethod("withLastEditor", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("withLastModified", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("withParentData", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("withPersons", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withSubData", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("withTag", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("withTasks", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("Task")))
      .withMethod("withType", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("withValue", DataType.ref("DocumentDataSet"), new Parameter(DataType.STRING))
      .withMethod("without", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("withoutParentData", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("withoutPersons", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withoutSubData", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("withoutTasks", DataType.ref("DocumentDataSet"), new Parameter(DataType.ref("Task")));

      simpleSetClass.withAssoc(documentDataSetClass, "documentdataset", Card.1, "simpleset", Card.1);

      Clazz personCreatorClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.util.PersonCreator")
      .withSuperClazz(sendableEntityCreatorClass)
      .withMethod("createIdMap", DataType.ref("IdMap"), new Parameter(DataType.STRING))
      .withMethod("getSendableInstance", DataType.OBJECT, new Parameter(DataType.BOOLEAN))
      .withMethod("getValue", DataType.OBJECT, new Parameter(DataType.OBJECT), new Parameter(DataType.STRING))
      .withMethod("removeObject", DataType.VOID, new Parameter(DataType.OBJECT))
      .withMethod("setValue", DataType.BOOLEAN, new Parameter(DataType.OBJECT), new Parameter(DataType.STRING), new Parameter(DataType.OBJECT), new Parameter(DataType.STRING));

      sendableEntityCreatorClass.withAssoc(personCreatorClass, "personcreator", Card.1, "sendableentitycreator", Card.1);

      Clazz personSetClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.util.PersonSet")
      .withSuperClazz(simpleSetClass)
      .withMethod("getEntryType", DataType.STRING)
      .withMethod("getGroups", DataType.ref("PersonSet"))
      .withMethod("getGroupsTransitive", DataType.ref("PersonSet"))
      .withMethod("getMembers", DataType.ref("PersonSet"))
      .withMethod("getMembersTransitive", DataType.ref("PersonSet"))
      .withMethod("getPersonData", DataType.ref("DocumentDataSet"))
      .withMethod("getTasks", DataType.ref("TaskSet"))
      .withMethod("hasGroups", DataType.ref("PersonSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasMembers", DataType.ref("PersonSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasPersonData", DataType.ref("PersonSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasPersonPO", DataType.ref("PersonPO"))
      .withMethod("hasTasks", DataType.ref("PersonSet"), new Parameter(DataType.OBJECT))
      .withMethod("with", DataType.ref("PersonSet"), new Parameter(DataType.OBJECT))
      .withMethod("withGroups", DataType.ref("PersonSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withMembers", DataType.ref("PersonSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withPersonData", DataType.ref("PersonSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("withTasks", DataType.ref("PersonSet"), new Parameter(DataType.ref("Task")))
      .withMethod("without", DataType.ref("PersonSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withoutGroups", DataType.ref("PersonSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withoutMembers", DataType.ref("PersonSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withoutPersonData", DataType.ref("PersonSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("withoutTasks", DataType.ref("PersonSet"), new Parameter(DataType.ref("Task")));

      simpleSetClass.withAssoc(personSetClass, "personset", Card.1, "simpleset", Card.1);

      Clazz taskCreatorClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.util.TaskCreator")
      .withSuperClazz(sendableEntityCreatorClass)
      .withMethod("createIdMap", DataType.ref("IdMap"), new Parameter(DataType.STRING))
      .withMethod("getSendableInstance", DataType.OBJECT, new Parameter(DataType.BOOLEAN))
      .withMethod("getValue", DataType.OBJECT, new Parameter(DataType.OBJECT), new Parameter(DataType.STRING))
      .withMethod("removeObject", DataType.VOID, new Parameter(DataType.OBJECT))
      .withMethod("setValue", DataType.BOOLEAN, new Parameter(DataType.OBJECT), new Parameter(DataType.STRING), new Parameter(DataType.OBJECT), new Parameter(DataType.STRING));

      taskCreatorClass.withAssoc(sendableEntityCreatorClass, "sendableentitycreator", Card.1, "taskcreator", Card.1);

      Clazz taskSetClass = clazzModel.createClazz("org.sdmlib.test.examples.modelcouch.util.TaskSet")
      .withSuperClazz(simpleSetClass)
      .withMethod("getEntryType", DataType.STRING)
      .withMethod("getParentTasks", DataType.ref("TaskSet"))
      .withMethod("getParentTasksTransitive", DataType.ref("TaskSet"))
      .withMethod("getPersons", DataType.ref("PersonSet"))
      .withMethod("getSubTasks", DataType.ref("TaskSet"))
      .withMethod("getSubTasksTransitive", DataType.ref("TaskSet"))
      .withMethod("getTaskData", DataType.ref("DocumentDataSet"))
      .withMethod("hasParentTasks", DataType.ref("TaskSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasPersons", DataType.ref("TaskSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasSubTasks", DataType.ref("TaskSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasTaskData", DataType.ref("TaskSet"), new Parameter(DataType.OBJECT))
      .withMethod("hasTaskPO", DataType.ref("TaskPO"))
      .withMethod("with", DataType.ref("TaskSet"), new Parameter(DataType.OBJECT))
      .withMethod("withParentTasks", DataType.ref("TaskSet"), new Parameter(DataType.ref("Task")))
      .withMethod("withPersons", DataType.ref("TaskSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withSubTasks", DataType.ref("TaskSet"), new Parameter(DataType.ref("Task")))
      .withMethod("withTaskData", DataType.ref("TaskSet"), new Parameter(DataType.ref("DocumentData")))
      .withMethod("without", DataType.ref("TaskSet"), new Parameter(DataType.ref("Task")))
      .withMethod("withoutParentTasks", DataType.ref("TaskSet"), new Parameter(DataType.ref("Task")))
      .withMethod("withoutPersons", DataType.ref("TaskSet"), new Parameter(DataType.ref("Person")))
      .withMethod("withoutSubTasks", DataType.ref("TaskSet"), new Parameter(DataType.ref("Task")))
      .withMethod("withoutTaskData", DataType.ref("TaskSet"), new Parameter(DataType.ref("DocumentData")));

      taskSetClass.withAssoc(simpleSetClass, "simpleset", Card.1, "taskset", Card.1);
      }
@Test
	public void reverseEngineeringTest() {
		ClassModel model=new ClassModel();
		GenClassModel generator = model.getGenerator();
		generator.updateFromCode("src/test/java/", "org.sdmlib.test.examples.modelcouch");
		generator.insertModelCreationCodeHere("src/test/java", "genModel");
	}
}
