package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.DataTypeMap;
import de.uniks.networkparser.graph.DataTypeSet;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Parameter;

public class TestMethods {

	@Test
	public void testClassWithMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think"));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithVoidMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID));
		
		model.generate("src/test/java");
		
	}
	
	// Fehler bei signature = model.getName(false), an diesen Stellen wird signature = model.getName(true) benoetigt
	// (momentan behoben)
	
	@Test
	public void testClassWithVoidAndNamelessParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataType.STRING)));
		
		model.generate("src/test/java");
		
	}
	
	// Fehler bei signature = model.getName(false), an diesen Stellen wird signature = model.getName(true) benoetigt
	// (momentan behoben)
	
	@Test
	public void testClassWithVoidAndParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter("value", DataType.STRING)));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME Parametertyp bisher nur in Form de.uniks.networkparser.graph.Clazz bei Methodenparametern
	
	@Test
	public void testClassWithVoidAndClassParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.with(new Method("think", DataType.VOID, new Parameter("room", DataType.ref(room.getClass()))));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithVoidAndSetParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter("values", DataTypeSet.ref(DataType.STRING))));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME im Fall einer Map als Parameter weicht die Signatur zu stark vom Normalfall ab 
	
	@Test
	public void testClassWithVoidAndMapParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter("values", DataTypeMap.ref(DataType.STRING, DataType.STRING))));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithVoidAndMultipleParametersMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter("value", DataType.STRING), new Parameter(DataType.INT)));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithNonVoidMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.STRING));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithNonVoidAndParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.STRING, new Parameter(DataType.STRING)));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithMultipleMethods() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.STRING, new Parameter(DataType.STRING))
				, new Method("dontThink", DataType.VOID));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithMethodAndBody() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID)
				.withBody("		String thought = \"\";\n"));
		
		model.generate("src/test/java");
		
	}
	
}
