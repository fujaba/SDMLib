package de.uniks.jism.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.JsonObject;

import de.uniks.jism.test.model.ChatMessage;
import de.uniks.jism.test.model.ChatMessageCreator;

public class JsonTest {
	@Test
	public void testChatMessage(){
		ChatMessage chatMessage=new ChatMessage();
		chatMessage.setText("Dies ist eine Testnachricht");
		chatMessage.setSender("Stefan Lindel");
		
		JsonIdMap jsonMap = new JsonIdMap();
		jsonMap.withCreator(new ChatMessageCreator());
		
		String reference="{\r\n  \"id\":\"J1.C1\",\r\n  \"class\":\"de.uniks.jism.test.model.ChatMessage\",\r\n  \"prop\":{\r\n    \"sender\":\"Stefan Lindel\",\r\n    \"txt\":\"Dies ist eine Testnachricht\"\r\n  }\r\n}";
		System.out.println(reference);
		JsonObject actual=jsonMap.toJsonObject(chatMessage);
		assertEquals("WERT Vergleichen", reference, actual.toString(2));
		assertEquals(reference.length(), actual.toString(2).length());
		
		String msg = actual.toString(2);
		
		JsonIdMap map = new JsonIdMap();
		map.withCreator(new ChatMessageCreator());
		
		ChatMessage newChatMsg = (ChatMessage) map.decode(new JsonObject().withValue(msg));
		System.out.println(newChatMsg);
		
	}
}
