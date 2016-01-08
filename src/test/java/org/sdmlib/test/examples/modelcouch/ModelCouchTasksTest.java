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
				+ "This is a test for writing objects to the CouchDB instance https://docker.cs.uni-kassel.de:5984/ and read from it. "
				+ "First a database is created and the following objects get persisted"
				);

		String databaseName = "segroup";

		String sessionid = "testBasicModelOnTheCouch" + System.currentTimeMillis();

		JsonIdMap idMap = PersonCreator.createIdMap(sessionid);
		
		ModelCouch couch = new ModelCouch()
				.withHostName("docker.cs.uni-kassel.de")
				.withHostName("localhost")
				.withPort(5984)
				.withUserName("couchdb")
				.withIdMap(idMap)
				.registerAtIdMap()
				.open(databaseName);

		Person seGroup = new Person();
		DocumentData seGroupData = seGroup.createPersonData()
				.withValue("segroup");
		
		idMap.put("root", seGroup);
		
		seGroupData.withType("list of person")
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

//		couch.close(); TODO even if db is created
		
		story.addObjectDiagram(seGroup);
		
		story.add(""
				+ "Afterwards the objects get reconstructed by reading all persisted documents from the same CouchDB instance."
				);
		
		String resultSessionid = "resultBasicModelOnTheCouch" + System.currentTimeMillis();
		JsonIdMap resultiIdMap = PersonCreator.createIdMap(resultSessionid);
		couch.withIdMap(resultiIdMap)
			.open(seGroup.getPersonData().first().getValue());
		
		long startMillis = System.currentTimeMillis();
		while(System.currentTimeMillis() < startMillis + 5000)
		{
			//wait 5 seconds
		}
		
		Person resultSeGroup = (Person)resultiIdMap.getObject("root");

		story.addObjectDiagram(resultSeGroup);
		
		story.add(""
				+ "Finally the database is deleted."
				);
		
		
		couch.delete(databaseName);

		story.dumpHTML();
	}
}
