package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.DataTypeMap;
import de.uniks.networkparser.graph.DataTypeSet;
import de.uniks.networkparser.graph.GraphUtil;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Parameter;

public class TestMethods {

	@Test
	public void testClassWithMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_a");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think"));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}

	@Test
	public void testClassWithVoidMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_b");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	//TODO Fehler bei signature = model.getName(false), an diesen Stellen wird signature = model.getName(true) benoetigt
	// (momentan behoben)
	
	@Test
	public void testClassWithVoidAndNamelessParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_c");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataType.STRING)));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	//TODO Fehler bei signature = model.getName(false), an diesen Stellen wird signature = model.getName(true) benoetigt
	// (momentan behoben)
	
	@Test
	public void testClassWithVoidAndParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_d");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataType.STRING).with("value")));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	// FIXME Parametertyp bisher nur in Form de.uniks.networkparser.graph.Clazz bei Methodenparametern
	
	@Test
	public void testClassWithVoidAndClassParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_e");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataType.ref(room.getClass())).with("room")));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}

	@Test
	public void testClassWithVoidAndSimpleClassParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_f");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataType.ref(room)).with("room")));
		person.with(new Method("read", DataType.VOID, new Parameter(room).with("room")));
	
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}

	
	@Test
	public void testClassWithVoidAndSetParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_g");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataTypeSet.ref(DataType.STRING)).with("values")));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	// FIXME im Fall einer Map als Parameter weicht die Signatur zu stark vom Normalfall ab 
	
	@Test
	public void testClassWithVoidAndMapParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_h");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataTypeMap.ref(DataType.STRING, DataType.STRING)).with("values")));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithVoidAndMultipleParametersMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_i");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID, new Parameter(DataType.STRING).with("value"), new Parameter(DataType.INT)));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	//FIXME NullPointer
	@Test
	public void testClassWithNonVoidMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_j");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.STRING));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithNonVoidAndParameterMethod() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_k");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.STRING, new Parameter(DataType.STRING)));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	//FIXME NullPointer
	@Test
	public void testClassWithMultipleMethods() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_l");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.STRING, new Parameter(DataType.STRING))
				, new Method("dontThink", DataType.VOID));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	//FIXME NUllpointer
	@Test
	public void testClassWithMethodAndBody() {
	
		ClassModel model = new ClassModel("org.sdmlib.simple.model.methods_m");
		Clazz person = model.createClazz("Person");
		
		person.with(new Method("think", DataType.VOID)
				.withBody("		String thought = \"\";\n"));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
}
