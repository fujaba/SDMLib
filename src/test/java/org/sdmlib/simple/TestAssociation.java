package org.sdmlib.simple;

import static de.uniks.networkparser.graph.Cardinality.MANY;
import static de.uniks.networkparser.graph.DataType.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;

public class TestAssociation {

	@Test
	public void testUniDirectionalAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_a");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room"); 
		
		person.withUniDirectional(room, "room", Cardinality.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testUniDirectionalAssociations() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_b");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withUniDirectional(room, "room", Cardinality.ONE);
		room.withUniDirectional(person, "persons", Cardinality.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testMultipleUniDirectionalAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_c");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room"); 
		
		person.withUniDirectional(room, "room", Cardinality.ONE);
		person.withUniDirectional(person, "prevPerson", Cardinality.ONE);
		person.withUniDirectional(person, "nextPerson", Cardinality.ONE);
		
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testOneToOneAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_d");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withBidirectional(room, "room", Cardinality.ONE, "person", Cardinality.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testOneToManyAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_e");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		room.withBidirectional(person, "persons", Cardinality.MANY, "room", Cardinality.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testManyToOneAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_f");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	@Test
	public void testMultipleOneToOneAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_g");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");
		
		person.withBidirectional(room, "room", Cardinality.ONE, "person", Cardinality.ONE);
		person.withBidirectional(teacher, "teacher", Cardinality.ONE, "person", Cardinality.ONE);
		room.withBidirectional(teacher, "teacher", Cardinality.ONE, "room", Cardinality.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");

	}

	@Test
	public void testMultipleOneToManyAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_h");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");

		person.withBidirectional(room, "rooms", Cardinality.MANY, "person", Cardinality.ONE);
		person.withBidirectional(teacher, "teachers", Cardinality.MANY, "person", Cardinality.ONE);
		room.withBidirectional(teacher, "teachers", Cardinality.MANY, "room", Cardinality.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");

	}

	@Test
	public void testMultipleManyToOneAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_i");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");

		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		person.withBidirectional(teacher, "teacher", Cardinality.ONE, "persons", Cardinality.MANY);
		room.withBidirectional(teacher, "teacher", Cardinality.ONE, "rooms", Cardinality.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");

	}

	@Test
	public void testMixedAssociations() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_j");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withUniDirectional(room, "rooms", Cardinality.MANY);
		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testManyToMany() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_k");
//		Clazz lecture = model.createClazz("Lecture");
//		Clazz student = model.createClazz("Student");
//
//		student.withBidirectional(lecture, "attended", Cardinality.MANY, "has", Cardinality.MANY);
		
	    Clazz task = model.createClazz("Task").withAttribute("name", STRING);
	    task.withBidirectional(task, "subTasks", MANY, "parentTasks", MANY);

		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
}