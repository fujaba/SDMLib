package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.DataTypeMap;
import de.uniks.networkparser.graph.DataTypeSet;
import de.uniks.networkparser.graph.Modifier;

public class TestAttributes {

	@Test
	public void testClassWithoutAttributes() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");

		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);

		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithMultipleAttributes() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);
		person.with(new Attribute().with("age", DataType.INT));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithSetAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.with(new Attribute("names", DataTypeSet.ref(DataType.STRING)));

		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithMultipleSetAttributes() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.createAttribute("names", DataTypeSet.ref(DataType.STRING));
		person.with(new Attribute("ages", DataTypeSet.ref(DataType.INT)));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME SimpleSet<String>> anstelle von SimpleSet<SimpleSet<String>>
	// Fehler liegt in CGUtil.shortClassName()
	
	@Test
	public void testClassWithSetSetAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.createAttribute("namesSet", DataTypeSet.ref(DataTypeSet.ref(DataType.STRING)));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME Parser generiert withCreator(...) anstelle von with(...)
	// (temporaer behoben)
	
	@Test
	public void testClassWithMapAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.createAttribute("names", DataTypeMap.ref(DataType.STRING, DataType.STRING));

		model.generate("src/test/java");
		
	}
	
	// FIXME gleiches Problem wie bei Sets SimpleKeyValueList<String,String>> anstelle von SimpleKeyValueList<String,SimpleKeyValueList<String,String>>
	
	@Test
	public void testClassWithMapMapAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.createAttribute("namesMap", DataTypeMap.ref(DataType.STRING, DataTypeMap.ref(DataType.STRING, DataType.STRING)));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME SimpleKeyValueList<String,String>> anstelle von SimpleSet<SimpleKeyValueList<String,String>>
	
	@Test
	public void testClassWithSetMapAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Attribute createAttribute = person.createAttribute("namesList", DataTypeSet.ref(DataTypeMap.ref(DataType.STRING, DataType.STRING)));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME SimpleSet<String>> anstelle von SimpleKeyValueList<String,SimpleSet<String>>
	
	@Test
	public void testClassWithMapSetAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Attribute createAttribute = person.createAttribute("names", DataTypeMap.ref(DataType.STRING, DataTypeSet.ref(DataType.STRING)));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME withCreator(...) anstelle von with(...) in CreatorCreator
	// (temporaer behoben)
	
	@Test
	public void testClassWithPrivateModifiedAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.with(new Attribute("personalName", DataType.STRING).with(Modifier.PRIVATE));
		
		model.generate("src/test/java");
		
	}
	
	// FIXME public Modifier verhindert die Generierung von gettern und settern
	
	@Test
	public void testClassWithPublicModifiedAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.with(new Attribute("personalName", DataType.STRING).with(Modifier.PUBLIC));
		
		model.generate("src/test/java");
		
	}
	
	@Test
	public void testClassWithStaticModifiedAttribute() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		person.with(new Attribute("personalName", DataType.STRING).with(Modifier.STATIC));
		
		model.generate("src/test/java");
		
	}
	
}
