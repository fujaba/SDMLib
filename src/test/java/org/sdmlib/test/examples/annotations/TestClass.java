package org.sdmlib.test.examples.annotations;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;

public class TestClass {

	@Test(timeout = 200000)
	public void testMethod() {
		ClassModel model = new ClassModel();
		model.getGenerator().updateFromCode("src/test/java", "org.sdmlib.test.examples.annotations");
		
		for(Clazz clazz : model.getClazzes()) {
			if(clazz.getName().equals("org.sdmlib.test.examples.annotations.TestClass")) {
				String name = clazz
				      .getMethods()
				      .first()
				      .getAnnotation()
				      .getName();
				Assert.assertEquals("@Test(timeout=200000)", name);
			}
		}
	}
}
