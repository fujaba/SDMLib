package org.sdmlib.test.examples.modelcouch;

import org.junit.Test;
import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.storyboards.StoryPage;
import org.sdmlib.test.examples.modelcouch.util.PersonCreator;

import de.uniks.networkparser.Filter;
import de.uniks.networkparser.json.JsonIdMap;

public class ModelCouchTasksTest
{
   /**
    * 
    * @see <a href='../../../../../../../../doc/BasicModelOnTheCouch.html'>BasicModelOnTheCouch.html</a>
 */
   @Test
	public void testBasicModelOnTheCouch()
	{
		StoryPage story = new StoryPage();

		story.add(""
				+ "This is a test for writing the following objects to the CouchDB instance https://docker.cs.uni-kassel.de:5984/"
				+ "First a database is created and the objects get persisted"
				);

		Person seGroup = new Person();
		seGroup.createPersonData()
		.withValue("segroup")
		.withType("list of person")
		.withLastEditor("zuendorf")
		.withLastModified("29.12.2015 19:18:42:123 CEST");

		String sessionid = "testBasicModelOnTheCouch" + System.currentTimeMillis();

		JsonIdMap idMap = PersonCreator.createIdMap(sessionid);
		idMap.put("root", seGroup);

		ModelCouch couch = new ModelCouch()
				.withHostName("docker.cs.uni-kassel.de")
				.withPort(5984)
				.withUserName("couchdb")
				.withIdMap(idMap)
				.registerAtIdMap()
				.open(seGroup.getPersonData().first().getValue());

		seGroup.createPersonData()
		.withValue("segroup")
		.withType("list of person")
		.withLastEditor("zuendorf")
		.withLastModified("29.12.2015 19:18:42:123 CEST");

		Person tobi = seGroup.createMembers();
		DocumentData tobiData = tobi.createPersonData()
				.withType("person")
				.withValue("tobi")
				.withLastModified("29.12.2015 20:05:23:123 CEST");

		DocumentData tobiLogin = tobiData.createSubData()
				.withTag("login")
				.withValue("lowrider")
				.withType("login")
				.withLastEditor("tobi")
				.withLastModified("29.12.2015 20:05:23:124 CEST");

		Task tobiTask = tobi.createTasks();
		DocumentData tobiTaskData = tobiTask.createTaskData()
				.withTag("test model replication on CouchDB")
				.withType("task")
				.withPersons((Person)tobi);

		story.addObjectDiagram(seGroup);

		story.add(""
				+ "Finally the database is deleted."
				);

		couch.delete(seGroup.getPersonData().first().getValue());

		story.dumpHTML();
	}
}
