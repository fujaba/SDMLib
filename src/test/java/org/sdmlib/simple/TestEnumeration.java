package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Literal;

public class TestEnumeration {

	@Test
	public void testEnumerationWithoutEntries() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.enums_a");
		Clazz testEnum = model.createClazz("TestEnum");
		
		testEnum.enableEnumeration();
		
		model.getGenerator().testGeneratedCode();
		
	}
	
	@Test
	public void testEnumerationWithEntry() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.enums_b");
		Clazz testEnum = model.createClazz("TestEnum");

		testEnum.enableEnumeration("PERSON");

		model.getGenerator().testGeneratedCode();
	
	}

	@Test
	public void testEnumerationWithMultipleEntries() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.enums_c");
		Clazz testEnum = model.createClazz("TestEnum");
		
		testEnum.enableEnumeration("PERSON","ROOM");
		testEnum.with(new Literal("TEACHER"));
		
		model.getGenerator().testGeneratedCode();
	
	}
	
}
