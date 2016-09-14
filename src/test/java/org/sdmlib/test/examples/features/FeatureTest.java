package org.sdmlib.test.examples.features;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Feature;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;


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
//		house.withBidirectional(door, "doors", Cardinality.MANY, "house", Cardinality.ONE);
//		house.withBidirectional(window, "windows", Cardinality.MANY, "house", Cardinality.ONE);
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
		
		house.withBidirectional(door, "doors", Cardinality.MANY, "house", Cardinality.ONE);
		house.withBidirectional(window, "windows", Cardinality.MANY, "house", Cardinality.ONE);
		
		model.generate("src/test/java");
	}
	
	@Test
	public void testFeaturesAll() {
		ClassModel model = new ClassModel("org.sdmlib.test.examples.features.model.all")
								.withFeatures(Feature.getAll());
		
		Clazz house = model.createClazz("House");
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withBidirectional(door, "doors", Cardinality.MANY, "house", Cardinality.ONE);
		house.withBidirectional(window, "windows", Cardinality.MANY, "house", Cardinality.ONE);
		
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
//		house.withBidirectional(door, "doors", Cardinality.MANY, "house", Cardinality.ONE);
//		house.withBidirectional(window, "windows", Cardinality.MANY, "house", Cardinality.ONE);
//		
//		model.generate("src/test/java");
//	}
	
}