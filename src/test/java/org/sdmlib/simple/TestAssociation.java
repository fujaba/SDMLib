package org.sdmlib.simple;

import static de.uniks.networkparser.graph.Association.MANY;
import static de.uniks.networkparser.graph.DataType.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;

public class TestAssociation {

	@Test
	public void testUniDirectionalAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_a");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room"); 
		
		person.withUniDirectional(room, "room", Association.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testUniDirectionalAssociations() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_b");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withUniDirectional(room, "room", Association.ONE);
		room.withUniDirectional(person, "persons", Association.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testMultipleUniDirectionalAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_c");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room"); 
		
		person.withUniDirectional(room, "room", Association.ONE);
		person.withUniDirectional(person, "prevPerson", Association.ONE);
		person.withUniDirectional(person, "nextPerson", Association.ONE);
		
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testOneToOneAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_d");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withBidirectional(room, "room", Association.ONE, "person", Association.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testOneToManyAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_e");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		room.withBidirectional(person, "persons", Association.MANY, "room", Association.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	@Test
	public void testManyToOneAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_f");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withBidirectional(room, "room", Association.ONE, "persons", Association.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	@Test
	public void testMultipleOneToOneAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_g");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");
		
		person.withBidirectional(room, "room", Association.ONE, "person", Association.ONE);
		person.withBidirectional(teacher, "teacher", Association.ONE, "person", Association.ONE);
		room.withBidirectional(teacher, "teacher", Association.ONE, "room", Association.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");

	}

	@Test
	public void testMultipleOneToManyAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_h");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");

		person.withBidirectional(room, "rooms", Association.MANY, "person", Association.ONE);
		person.withBidirectional(teacher, "teachers", Association.MANY, "person", Association.ONE);
		room.withBidirectional(teacher, "teachers", Association.MANY, "room", Association.ONE);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");

	}

	@Test
	public void testMultipleManyToOneAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_i");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");

		person.withBidirectional(room, "room", Association.ONE, "persons", Association.MANY);
		person.withBidirectional(teacher, "teacher", Association.ONE, "persons", Association.MANY);
		room.withBidirectional(teacher, "teacher", Association.ONE, "rooms", Association.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");

	}

	@Test
	public void testMixedAssociations() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_j");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withUniDirectional(room, "rooms", Association.MANY);
		person.withBidirectional(room, "room", Association.ONE, "persons", Association.MANY);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	
	@Test
	public void testManyToMany() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_k");
//		Clazz lecture = model.createClazz("Lecture");
//		Clazz student = model.createClazz("Student");
//
//		student.withBidirectional(lecture, "attended", Association.MANY, "has", Association.MANY);
		
	    Clazz task = model.createClazz("Task").withAttribute("name", STRING);
	    task.withBidirectional(task, "subTasks", MANY, "parentTasks", MANY);

		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
	@Test
	public void testOneToManyInterace() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model.association_l");
		Clazz lecture = model.createClazz("Lecture");
		Clazz student = model.createClazz("Student");
		Clazz uni = model.createClazz("University");
		uni.enableInterface();
		lecture.enableInterface();

		student.withBidirectional(lecture, "attended", Association.MANY, "has", Association.MANY);
		student.withBidirectional(uni, "studs", Association.ONE, "students", Association.MANY);
		

		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
	}
}