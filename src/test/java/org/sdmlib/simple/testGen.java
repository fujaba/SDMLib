package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class testGen {

	@Test
	public void testModel() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		person.createAttribute("name", DataType.STRING);
		person.createAttribute("age", DataType.INT);

		room.withMethod("init", DataType.VOID);
		
		person.withBidirectional(room, "room", Association.ONE, "persons", Association.MANY);
		
//		model.generate("gen");
	}
}
