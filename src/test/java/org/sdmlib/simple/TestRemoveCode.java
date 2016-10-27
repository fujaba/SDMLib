package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.GenClass;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class TestRemoveCode {

//	@Test
	public void testRemoveAttribute() {
		
		String rootDir = "src/test/java";
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.removeCode_a");
		
		Clazz person = model.createClazz("Person");
		
		person.withAttribute("name", DataType.STRING);
		
		model.generate(rootDir);
		
		model.getGenerator().getOrCreate(person.getAttributes().first()).removeGeneratedCode(rootDir);
		
	}
	
//	@Test
	public void testRemoveMethod() {
		
		String rootDir = "src/test/java";
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.removeCode_b");
		
		Clazz person = model.createClazz("Person");
		
		person.withMethod("think", DataType.VOID);
		
		model.generate(rootDir);
		
		model.getGenerator().getOrCreate(person.getMethods().first()).removeGeneratedCode(rootDir);
		
	}
	
//	@Test
	public void testRemoveClass() {
		
		String rootDir = "src/test/java";
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.removeCode_c");
		
		Clazz person = model.createClazz("Person");
		Clazz pupil = model.createClazz("Pupil");
		
		model.generate(rootDir);
		
		GenClass genPupil = (GenClass) model.getGenerator().getOrCreate(pupil);
		
		genPupil.removeGeneratedCode(rootDir);
		
	}
	
//	@Test
	public void testRemoveAssociation() {
		
		String rootDir = "src/test/java";
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.removeCode_d");
		
		Clazz person = model.createClazz("Person");
		Clazz pupil = model.createClazz("Pupil");
		
		person.withBidirectional(pupil, "pupils", Cardinality.MANY, "person", Cardinality.ONE);
		
		model.generate(rootDir);
		
		model.getGenerator().getOrCreate(person.getAssociations().first()).removeGeneratedCode(rootDir);
		
		model.getGenerator().getOrCreate(pupil.getAssociations().first()).removeGeneratedCode(rootDir);
		
	}
	
}
