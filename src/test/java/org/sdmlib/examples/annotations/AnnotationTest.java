package org.sdmlib.examples.annotations;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Feature;
import org.sdmlib.models.classes.Method;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Parameter;


public class AnnotationTest {
	
	@Test
	public void testFeaturesNone() {
		ClassModel model = new ClassModel("org.sdmlib.examples.features.model.simple")
								.withFeatures(null);
		
		Clazz house = model.createClazz("House");
		Method method = house.createMethod("init");
		method.with
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withAssoc(door, "doors", Card.MANY, "house", Card.ONE);
		house.withAssoc(window, "windows", Card.MANY, "house", Card.ONE);
		
		model.generate("src/test/java");
	}
}