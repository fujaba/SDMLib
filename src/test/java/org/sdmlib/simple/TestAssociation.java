package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;

public class TestAssociation {

	// FIXME Unidirectional erstellt bidirektionale Kante und selbstassoc automatisch
	
	@Test
	public void testUniDirectionalAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room"); 
		
		person.withUniDirectional(room, "room", Cardinality.ONE);
		
		model.generate("src/test/java");
		
	}
	
	// FIXME unidirektionale Assoc wird in Zielklasse erstellt, bsp assoc in Person persons (MANY) - persons (MANY)
	
	@Test
	public void testUniDirectionalAssociations() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room"); 
		
		person.withUniDirectional(room, "room", Cardinality.ONE);
		room.withUniDirectional(person, "persons", Cardinality.MANY);
		
		model.generate("src/test/java");
		
	}
	
	// FIXME Fehler beim parsen des Files bezueglich eines Semikolon
	
	@Test
	public void testMultipleUniDirectionalAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room"); 
		
		person.withUniDirectional(room, "room", Cardinality.ONE);
		person.withUniDirectional(person, "prevPerson", Cardinality.ONE);
		person.withUniDirectional(person, "nextPerson", Cardinality.ONE);
		
		model.generate("src/test/java");
		
	}
	
	// FIXME wie bei unidirektional, Kanten existieren auf den Elementen selbst
	// FIXME in PersonSet bei getPersonTransitive wird Aufruf von minus() erstellt, was zu fehler fuehrt
	
	@Test
	public void testOneToOneAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withBidirectional(room, "room", Cardinality.ONE, "person", Cardinality.ONE);
		
		model.generate("src/test/java");
		
	}
	
	// FIXME gleiches problem bezueglich der selbstassoziation
	
	@Test
	public void testOneToManyAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		room.withBidirectional(person, "persons", Cardinality.MANY, "room", Cardinality.ONE);
		
		model.generate("src/test/java");
		
	}
	
	// FIXME gleiches problem bezueglich der selbstassoziation
	
	@Test
	public void testManyToOneAssociation() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		
		model.generate("src/test/java");
		
	}
	
	// FIXME gleiches problem bezueglich der selbstassoziation

	@Test
	public void testMultipleOneToOneAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");
		
		person.withBidirectional(room, "room", Cardinality.ONE, "person", Cardinality.ONE);
		person.withBidirectional(teacher, "teacher", Cardinality.ONE, "person", Cardinality.ONE);
		room.withBidirectional(teacher, "teacher", Cardinality.ONE, "room", Cardinality.ONE);
		
		model.generate("src/test/java");

	}

	// FIXME gleiches problem bezueglich der selbstassoziation
	// FIXME mehrere Selbstassoziationen mit ONE - ONE und MANY - MANY

	@Test
	public void testMultipleOneToManyAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");

		person.withBidirectional(room, "rooms", Cardinality.MANY, "person", Cardinality.ONE);
		person.withBidirectional(teacher, "teachers", Cardinality.MANY, "person", Cardinality.ONE);
		room.withBidirectional(teacher, "teachers", Cardinality.MANY, "room", Cardinality.ONE);

		model.generate("src/test/java");

	}

	// FIXME gleiches problem bezueglich der selbstassoziation
	// FIXME mehrere Selbstassoziationen mit ONE - ONE und MANY - MANY
	
	@Test
	public void testMultipleManyToOneAssociation() {

		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		Clazz teacher = model.createClazz("Teacher");

		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		person.withBidirectional(teacher, "teacher", Cardinality.ONE, "persons", Cardinality.MANY);
		room.withBidirectional(teacher, "teacher", Cardinality.ONE, "rooms", Cardinality.MANY);

		model.generate("src/test/java");

	}

	// FIXME gleiches problem bezueglich der selbstassoziation
	// FIXME mehrere Selbstassoziationen mit ONE - ONE und MANY - MANY
	
	@Test
	public void testMixedAssociations() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		
		person.withUniDirectional(room, "rooms", Cardinality.MANY);
		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		
		model.generate("src/test/java");
		
	}
	
}