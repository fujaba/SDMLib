package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.Parameter;

public class TestAbstractClazz {
	@Test
	public void testAbstractClazz() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.abstract_A");
		Clazz person = model.createClazz("Person");
		
		Clazz human = model.createClazz("Human");
		human.enableInterface();
		human.withBidirectional(person, "has", Cardinality.ONE, "owner", Cardinality.ONE);
		
		person.withSuperClazz(human);
		person.with(Modifier.ABSTRACT);
		
		Clazz student = model.createClazz("Student");
		student.withSuperClazz(person);
		
		model.getGenerator().testGeneratedCode();
	}
	
	@Test
	public void testAbstractClazzB() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.abstract_B");
		Clazz person = model.createClazz("Person");
		Clazz flower = model.createClazz("Flower");
		
		Clazz human = model.createClazz("Human");
		human.enableInterface();
		human.withBidirectional(flower, "has", Cardinality.ONE, "owner", Cardinality.ONE);
		
		person.withSuperClazz(human);
		person.with(Modifier.ABSTRACT);
		
		Clazz student = model.createClazz("Student");
		student.withSuperClazz(person);
		
		model.getGenerator().testGeneratedCode();
	}
	
	@Test
	public void testAbstractClazzC() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.abstract_C");
				
		// classes
		Clazz game = model.createClazz("Game");
		Clazz grass = model.createClazz("Grass");
		Clazz ground = model.createClazz("Ground").with(Modifier.create("abstract"));
				
		// generalization
		ground.withKidClazzes(grass);
		
		// associations
		game.withBidirectional(ground, "grounds", Cardinality.MANY, "game", Cardinality.ONE);
		
		model.getGenerator().testGeneratedCode();
	}
}

