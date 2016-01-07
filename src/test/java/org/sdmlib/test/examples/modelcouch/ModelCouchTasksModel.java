package org.sdmlib.test.examples.modelcouch;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.StoryPage;

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
		.withAssoc(task, "tasks", Card.MANY, "taskFlow", Card.ONE)
		.withAssoc(task, "firstTasks", Card.MANY, "taskFlowFirst", Card.ONE);

		task
		.withAssoc(task, "transitionTargets", Card.MANY, "transitionSource", Card.ONE)
		.withAssoc(userGroup, "responsibles", Card.MANY, "responsible", Card.MANY)
		.withAssoc(user, "handledBy", Card.ONE, "handledTasks", Card.MANY);
		
		userGroup
		.withAssoc(user, "members", Card.MANY, "groups", Card.MANY);

		model.generate("src/test/java");
		story.addClassDiagram(model);
		story.dumpHTML();
	}
}
