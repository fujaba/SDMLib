package org.sdmlib.test.examples.features;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Feature;


public class FeatureTest {
	
//	@Test
//	public void testFeaturesNone() {
//		ClassModel model = new ClassModel("org.sdmlib.test.examples.features.model.simple")
//								.withFeatures(null);
//		
//		Clazz house = model.createClazz("House");
//		Clazz door = model.createClazz("Door");
//		Clazz window = model.createClazz("Window");
//		
//		house.withBidirectional(door, "doors", Association.MANY, "house", Association.ONE);
//		house.withBidirectional(window, "windows", Association.MANY, "house", Association.ONE);
//		
//		model.generate("src/test/java");
//	}
	
	@Test
	public void testFeaturesAlbertSets() {
		ClassModel model = new ClassModel("org.sdmlib.test.examples.features.model.albertsets")
								.withFeature(Feature.SETCLASS);
		
		Clazz house = model.createClazz("House");
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withBidirectional(door, "doors", Association.MANY, "house", Association.ONE);
		house.withBidirectional(window, "windows", Association.MANY, "house", Association.ONE);
		
		model.generate("src/test/java");
	}
	
	@Test
	public void testFeaturesAll() {
		ClassModel model = new ClassModel("org.sdmlib.test.examples.features.model.all")
								.withFeatures(Feature.getAll());
		
		Clazz house = model.createClazz("House");
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withBidirectional(door, "doors", Association.MANY, "house", Association.ONE);
		house.withBidirectional(window, "windows", Association.MANY, "house", Association.ONE);
		
		model.generate("src/test/java");
	}
	
//	@Test
//	public void testFeaturesSerialization() {
//		ClassModel model = new ClassModel("org.sdmlib.test.examples.features.model.serialization")
//							.withFeatures(null).withFeature(Feature.Serialization);
//		
//		Clazz house = model.createClazz("House");
//		Clazz door = model.createClazz("Door");
//		Clazz window = model.createClazz("Window");
//		
//		house.withBidirectional(door, "doors", Association.MANY, "house", Association.ONE);
//		house.withBidirectional(window, "windows", Association.MANY, "house", Association.ONE);
//		
//		model.generate("src/test/java");
//	}
	
}