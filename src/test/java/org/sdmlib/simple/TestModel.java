package org.sdmlib.simple;

import java.util.HashSet;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class TestModel {

	@Test
	public void testClassWithoutAttributes() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.test");
		model.withoutFeature(Feature.PATTERNOBJECT);
		model.getFeature(Feature.SETCLASS).withClazzValue(HashSet.class);
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);
		person.createAttribute("credits", DataType.LONG);
		
		Clazz uni = model.createClazz("University");
		uni.withBidirectional(person, "stud", Cardinality.MANY, "owner", Cardinality.ONE);
		
		model.getGenerator().testGeneratedCode();
	}
}
