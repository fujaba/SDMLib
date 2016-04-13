package org.sdmlib.test.examples.modelcouch;

import org.junit.Test;
import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.modelcouch.ModelCouch.ApplicationType;
import org.sdmlib.storyboards.StoryPage;
import org.sdmlib.test.examples.modelcouch.util.PersonCreator;

import de.uniks.networkparser.IdMap;

public class ModelCouchTasksTest
{
   private static final String DB_USERNAME = "docker.cs.uni-kassel.de";
   private static final String DB_HOST = "couchdb";

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
				+ "First a database is created, the following objects get persisted and the connection gets closed."
				);

		String databaseName = "segroup";

		String sessionid = "testBasicModelOnTheCouch" + System.currentTimeMillis();

		IdMap idMap = PersonCreator.createIdMap(sessionid);
		
		ModelCouch couch = new ModelCouch()
				.withHostName(DB_HOST)
				.withPort(5984)
				.withUserName(DB_USERNAME)
				.withApplicationType(ApplicationType.StandAlone)
				.withIdMap(idMap)
				.registerAtIdMap()
				.open(databaseName);
		
		if (couch.getModelDBListener() == null)
		{
		   // no database. 
		   // abort test
//		   System.out.println("Could not connect to " + couch.getHostName());
		   return;
		}

		Person seGroup = new Person();
		idMap.put("root", seGroup);

		DocumentData seGroupData = seGroup.createPersonData()
				.withValue("segroup");
		
		
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

		couch.close();
		
		story.addObjectDiagram(seGroup);
		
		story.add(""
				+ "Afterwards the objects get reconstructed by reading all persisted documents from the same CouchDB instance."
				);
		
		String resultSessionid = "resultBasicModelOnTheCouch" + System.currentTimeMillis();
		IdMap resultiIdMap = PersonCreator.createIdMap(resultSessionid);
		
		couch = new ModelCouch()
				.withHostName(DB_HOST)
				.withPort(5984)
				.withUserName(DB_USERNAME)
				.withApplicationType(ApplicationType.StandAlone)
				.withIdMap(idMap)
				.registerAtIdMap()
				.open(databaseName);
		
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
		
		
		couch.deleteDatabase(databaseName);

		story.dumpHTML();
	}
}
