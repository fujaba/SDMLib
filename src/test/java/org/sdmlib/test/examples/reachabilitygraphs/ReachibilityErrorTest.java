package org.sdmlib.test.examples.reachabilitygraphs;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.Node;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.SimpleState;
import org.sdmlib.test.examples.reachabilitygraphs.simplestates.util.SimpleStateCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonObject;

public class ReachibilityErrorTest {

	@Test
	public void testReuseIdMap() {
		ReachableState reachableState = new ReachableState();
		SimpleState simpleState = new SimpleState();
		Node node = simpleState.createNodes();
		reachableState.withGraphRoot(simpleState);
		
		IdMap idMap = SimpleStateCreator.createIdMap("s");
//		idMap.withTimeStamp(1);
		JsonArray jsonArray = idMap.toJsonArray(reachableState.getGraphRoot());
	
		JsonObject jsonObject = (JsonObject) jsonArray.get(0);
	
		String id = (String) jsonObject.get(IdMap.ID);
		
		IdMap newIdMap = new IdMap().with(idMap);
		
		jsonArray = newIdMap.toJsonArray(reachableState.getGraphRoot());
		
		jsonObject = (JsonObject) jsonArray.get(0);
		
		String newId = (String) jsonObject.get(IdMap.ID);

		Assert.assertEquals(id, newId);
	}
	
}
