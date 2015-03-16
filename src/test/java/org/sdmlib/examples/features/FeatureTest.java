package org.sdmlib.examples.features;

import org.junit.Test;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Feature;


public class FeatureTest {
	
	@Test
	public void testFeaturesNone() {
		ClassModel model = new ClassModel("org.sdmlib.examples.features.model.simple")
								.withFeatures(null);
		
		Clazz house = model.createClazz("House");
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withAssoc(door, "doors", Card.MANY, "house", Card.ONE);
		house.withAssoc(window, "windows", Card.MANY, "house", Card.ONE);
		
		model.generate("src/test/java");
	}
	
	@Test
	public void testFeaturesAlbertSets() {
		ClassModel model = new ClassModel("org.sdmlib.examples.features.model.albertsets")
								.withFeature(Feature.ALBERTsSets);
		
		Clazz house = model.createClazz("House");
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withAssoc(door, "doors", Card.MANY, "house", Card.ONE);
		house.withAssoc(window, "windows", Card.MANY, "house", Card.ONE);
		
		model.generate("src/test/java");
	}
	
	@Test
	public void testFeaturesAll() {
		ClassModel model = new ClassModel("org.sdmlib.examples.features.model.all")
								.withFeatures(Feature.getAll());
		
		Clazz house = model.createClazz("House");
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withAssoc(door, "doors", Card.MANY, "house", Card.ONE);
		house.withAssoc(window, "windows", Card.MANY, "house", Card.ONE);
		
		model.generate("src/test/java");
	}
	
	@Test
	public void testFeaturesSerialization() {
		ClassModel model = new ClassModel("org.sdmlib.examples.features.model.serialization")
							.withFeatures(null).withFeature(Feature.Serialization);
		
		Clazz house = model.createClazz("House");
		Clazz door = model.createClazz("Door");
		Clazz window = model.createClazz("Window");
		
		house.withAssoc(door, "doors", Card.MANY, "house", Card.ONE);
		house.withAssoc(window, "windows", Card.MANY, "house", Card.ONE);
		
		model.generate("src/test/java");
	}
	
}