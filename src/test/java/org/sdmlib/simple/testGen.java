package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Clazz.ClazzType;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Method;

public class testGen {

	@Test
	public void testModel() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		person.createAttribute("name", DataType.STRING);
		person.createAttribute("age", DataType.INT);

		room.with(new Method("init", DataType.VOID));
		
		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		
		// Test modellieren

		// Test - gueltiges Modell zu ungueltigem Modell umformen
//		model.generate("gen");
		System.out.println("Generation complete");
	}
}
