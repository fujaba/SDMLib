package org.sdmlib.simple;

import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClassModel;

public class TestReverseEngineering {
	      //@Test
	public void reverseEngineeringTest() {
		ClassModel model=new ClassModel();
		GenClassModel generator = model.getGenerator();
		generator.updateFromCode("src/test/java/", "org.sdmlib.test.examples.modelcouch");
		generator.insertModelCreationCodeHere("src/test/java", "genModel");
	}
}
