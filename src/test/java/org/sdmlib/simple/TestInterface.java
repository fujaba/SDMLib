package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;

public class TestInterface {

	@Test
	public void testClassAsInterface() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.interface_a");
		Clazz person = model.createClazz("Person");
		
		person.enableInterface();
		
		model.getGenerator().testGeneratedCode();
		
	}
	
	@Test
	public void testClassAsInterfaceWithImplementedClass() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.interface_b");
		Clazz person = model.createClazz("Person");
		Clazz pupil = model.createClazz("Pupil");
		
		person.enableInterface();
		
		person.withMethod("think", DataType.VOID);
		person.withMethod("sayName", DataType.VOID, new Parameter(DataType.STRING));
		person.withAttribute("name", DataType.STRING);
		
		pupil.withImplements(person);
		
		model.getGenerator().testGeneratedCode();
		
	}
	
}
