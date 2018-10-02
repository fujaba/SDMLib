package org.sdmlib.simple;

import org.junit.Test;

import de.uniks.networkparser.ext.ClassModel;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.DataTypeMap;
import de.uniks.networkparser.graph.DataTypeSet;
import de.uniks.networkparser.graph.Modifier;

public class TestAttributes {

	@Test
	public void testClassWithoutAttributes() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_a");
		Clazz person = model.createClazz("Person");
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testClassWithAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_b");
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithMultipleAttributes() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_c");
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);
		person.withAttribute("age", DataType.INT);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithSetAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_d");
		Clazz person = model.createClazz("Person");
		person.withAttribute("names", DataTypeSet.create(DataType.STRING));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithSingleSetAttributes() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_e");
		Clazz person = model.createClazz("Person");
		person.withAttribute("ages", DataTypeSet.create(DataType.INT));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testClassWithMultipleSetAttributes() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_f");
		Clazz person = model.createClazz("Person");
		person.createAttribute("names", DataTypeSet.create(DataType.STRING));
		person.withAttribute("ages", DataTypeSet.create(DataType.INT));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testClassWithSetSetAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_g");
		Clazz person = model.createClazz("Person");
		person.createAttribute("namesSet", DataTypeSet.create(DataTypeSet.create(DataType.STRING)));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	// FIXME Parser generiert withCreator(...) anstelle von with(...)
	// (temporaer behoben)
	
	@Test
	public void testClassWithMapAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_h");
		Clazz person = model.createClazz("Person");
		person.createAttribute("names", DataTypeMap.create(DataType.STRING, DataType.STRING));
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	
	
	// FIXME withCreator(...) anstelle von with(...) in CreatorCreator
	// (temporaer behoben)
	
	@Test
	public void testClassWithPrivateModifiedAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_l");
		Clazz person = model.createClazz("Person");
		person.createAttribute("personalName", DataType.STRING).with(Modifier.PRIVATE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	// FIXME public Modifier verhindert die Generierung von gettern und settern
	
	@Test
	public void testClassWithPublicModifiedAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_m");
		Clazz person = model.createClazz("Person");
		person.createAttribute("personalName", DataType.STRING).with(Modifier.PUBLIC);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testClassWithStaticModifiedAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_n");
		Clazz person = model.createClazz("Person");
		person.createAttribute("personalName", DataType.STRING).with(Modifier.STATIC);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	@Test
	public void testClassWithProtectedModifiedAttribute() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.attribute_o");
		Clazz person = model.createClazz("Person");
		person.createAttribute("personalName", DataType.STRING).with(Modifier.PROTECTED);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}

}
