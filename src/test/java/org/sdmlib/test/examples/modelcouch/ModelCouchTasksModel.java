package org.sdmlib.test.examples.modelcouch;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class ModelCouchTasksModel
{
   /**
    * 
    * @see <a href='../../../../../../../../../doc/couchSpaceTasksModel.html'>couchSpaceTasksModel.html</a>/n * @see <a href='../../../../../../../../doc/couchSpaceTasksModel.html'>couchSpaceTasksModel.html</a>
 */
   @Test
	public void couchSpaceTasksModel() throws Exception
	{
		StoryPage story = new StoryPage();

		story.add("Exchange tasks via couch space, i.e. couch databases");

		ClassModel model = new ClassModel("org.sdmlib.test.examples.couchspace.tasks");

		// classes
		Clazz taskFlow = model.createClazz("TaskFlow")
				.withAttribute("title", DataType.STRING);

		Clazz task = model.createClazz("Task")
				.withAttribute("title", DataType.STRING)
				.withAttribute("transitionCondition",DataType.STRING)
				.withAttribute("copySdmLibId", DataType.STRING);

		Clazz userGroup = model.createClazz("UserGroup")
				.withAttribute("name", DataType.STRING);
		
		Clazz user = model.createClazz("User")
				.withAttribute("name", DataType.STRING);

		// assocs
		taskFlow
		.withBidirectional(task, "tasks", Cardinality.MANY, "taskFlow", Cardinality.ONE)
		.withBidirectional(task, "firstTasks", Cardinality.MANY, "taskFlowFirst", Cardinality.ONE);

		task
		.withBidirectional(task, "transitionTargets", Cardinality.MANY, "transitionSource", Cardinality.ONE)
		.withBidirectional(userGroup, "responsibles", Cardinality.MANY, "responsible", Cardinality.MANY)
		.withBidirectional(user, "handledBy", Cardinality.ONE, "handledTasks", Cardinality.MANY);
		
		userGroup
		.withBidirectional(user, "members", Cardinality.MANY, "groups", Cardinality.MANY);

		model.generate("src/test/java");
		story.addClassDiagram(model);
		story.dumpHTML();
	}
}
