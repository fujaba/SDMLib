package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Modifier;

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
}

