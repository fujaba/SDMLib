package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;

public class TestSuperClazzes {

	// FIXME fehlendes other Element bei Generalisierungsassociation durch withSuperClazz(...)
	
	@Test
	public void testClazzAsSuperClazz() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.superclazzes_a");
		Clazz person = model.createClazz("Person");
		Clazz pupil = model.createClazz("Pupil").withSuperClazz(person);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	// FIXME fehlendes other Element bei Generalisierungsassociation durch withKidClazzes(...)
	
	@Test
	public void testClazzAsKidClazz() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.superclazzes_b");
		Clazz person = model.createClazz("Person");
		Clazz pupil = model.createClazz("Pupil");
		
		person.withKidClazzes(pupil);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
	
	// FIXME fehlendes other Element bei Generalisierungsassociation durch withSuperClazz(...)
	
	@Test
	public void testClazzAsSuperClazzWithMultipleKids() {
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.superclazzes_c");
		Clazz person = model.createClazz("Person");
		Clazz pupil = model.createClazz("Pupil").withSuperClazz(person);
		Clazz teacher = model.createClazz("Teacher").withSuperClazz(person);
		model.getGenerator().testGeneratedCode();
//		model.generate("src/test/java");
		
	}
//FIXME Wrong call of RemoveYou
//	   @Override
//	   public void removeYou()
//	   {
//	   
//	      super.removeYou();
//
//	      setPerson(null);
//	      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
//	   }
}
