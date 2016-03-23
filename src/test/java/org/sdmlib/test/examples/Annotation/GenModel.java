package org.sdmlib.test.examples.Annotation;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;

public class GenModel {

	@Test
	public void genModel() {
		ClassModel model = new ClassModel("org.sdmlib.test.examples.Annotation");
		Clazz person = model.createClazz("Person");
//		person.withAnnotation(new Annotation().withName(Annotation.OVERRIDE));
		
		model.generate("src/test/java");
		
	}
}
