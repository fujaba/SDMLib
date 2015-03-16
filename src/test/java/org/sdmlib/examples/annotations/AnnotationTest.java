package org.sdmlib.examples.annotations;

import org.junit.Test;
import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;


public class AnnotationTest {
	
	@Test
	public void testFeaturesNone() {
		ClassModel model = new ClassModel("org.sdmlib.examples.annotations.model.simple");
		
		Clazz cube = model.createClazz("Cube");
		cube.createMethod("init");
		
		Clazz house = model.createClazz("House").withSuperClazz(cube);
		house.createMethod("init")
				.withAnnotations(new Annotation().withName("Override"));
		
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withAssoc(door, "doors", Card.MANY, "house", Card.ONE);
		house.withAssoc(window, "windows", Card.MANY, "house", Card.ONE);
		
		model.generate("src/test/java");
	}
}