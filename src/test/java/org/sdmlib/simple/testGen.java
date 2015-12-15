package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class testGen {

	@Test
	public void testModel() {
		ClassModel model=new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);
		model.generate("gen");
		System.out.println("Generation complete");
	}
}
