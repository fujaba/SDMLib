package org.sdmlib.test.examples.annotations;


import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.list.SimpleSet;


public class AnnotationTest {
	
	@Test
	public void testFeaturesNone() {
		String packageName = "org.sdmlib.test.examples.annotations.model.simple";
		ClassModel model = new ClassModel(packageName);
		
		Clazz cube = model.createClazz("Cube");
		cube.createMethod("init");
		
		Clazz house = model.createClazz("House").withSuperClazz(cube);
		house.createMethod("init")
				.with(
						new Annotation("Override")
				);
		
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withBidirectional(door, "doors", Cardinality.MANY, "house", Cardinality.ONE);
		house.withBidirectional(window, "windows", Cardinality.MANY, "house", Cardinality.ONE);
		
		model.removeAllGeneratedCode("src/test/java");
		model.generate("src/test/java");
		
		model = new ClassModel();
		model.getGenerator().updateFromCode("src/test/java", packageName);
		
		Clazz clazz = model.getClazz("House");
		
		if (clazz == null)
			return;
		
		SimpleSet<Method> methods = clazz.getMethods();
		
		for (Method method : methods) {
		
			if ("init".equals(method.getName())) {
//				System.out.println(method.getAnnotation());
				return;
			}
		}
		Assert.fail();
	}
}