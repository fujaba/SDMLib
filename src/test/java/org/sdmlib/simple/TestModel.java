package org.sdmlib.simple;

import java.util.HashSet;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Feature;
import de.uniks.networkparser.graph.Method;

public class TestModel {

	@Test
	public void testClassWithoutAttributes() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.test");
		// model.withoutFeature(Feature.PATTERNOBJECT);
		model.getFeature(Feature.SETCLASS).withClazzValue(HashSet.class);
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);
		person.createAttribute("credits", DataType.LONG);
		Method createMethod = person.createMethod("getLong");
		createMethod.with(DataType.LONG);
		createMethod.withBody( "" +
		      "      return this.getCredits() + 42; \n");
		
		Clazz uni = model.createClazz("University");
		uni.withBidirectional(person, "stud", Association.MANY, "owner", Association.ONE);
		
		model.getGenerator().testGeneratedCode();
	}
}
