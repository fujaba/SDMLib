package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Feature;

public class FeatureTest {
	@Test
	public void testAndroid() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.androidmodel");
		Clazz uni = model.createClazz("University");
		Clazz student = model.createClazz("Student");
		student.withAttribute("name", DataType.STRING);
		
		uni.withBidirectional(student, "student", Association.MANY, "uni", Association.ONE);
		
		
		// Remove Dependency from SDMLib
		// model.withoutFeature(Feature.PATTERNOBJECT);
		// So Only Networkparser is a Dependency, add it per Maven de.uniks:NetworkParser:Core
		
		// Remove all Dependency
		model.withoutFeature(Feature.SERIALIZATION);
		model.withoutFeature(Feature.SETCLASS);
//		model.getFeature(Feature.SETCLASS).withClazzValue(LinkedHashSet.class);
		
		model.generate("src/test/java");
		
		
	}
}
