package org.sdmlib.examples.annotations;


import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.util.MethodSet;


public class AnnotationTest {
	
	@Test
	public void testFeaturesNone() {
		String packageName = "org.sdmlib.examples.annotations.model.simple";
		ClassModel model = new ClassModel(packageName);
		
		Clazz cube = model.createClazz("Cube");
		cube.createMethod("init");
		
		Clazz house = model.createClazz("House").withSuperClazz(cube);
		house.createMethod("init")
				.withAnnotations(
						new Annotation().withName("Override")
				);
		
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withAssoc(door, "doors", Card.MANY, "house", Card.ONE);
		house.withAssoc(window, "windows", Card.MANY, "house", Card.ONE);
		
		model.generate("src/test/java");
		
		model = new ClassModel();
		model.getGenerator().updateFromCode("src/test/java", packageName);
		
		Clazz clazz = model.getClazz("House");
		
		if (clazz == null)
			return;
		
		MethodSet methods = clazz.getMethods();
		
		for (Method method : methods) {
		
			if ("init".equals(method.getName())) {
				System.out.println(method.getAnnotations());
				return;
			}
		}
		Assert.fail();
	}
}