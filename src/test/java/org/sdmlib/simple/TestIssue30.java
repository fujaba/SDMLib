package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Method;

public class TestIssue30 {
//	@Test
	public void testIsuue30() {
		ClassModel model=new ClassModel("org.sdmlib.simple.model.issue30");
		Clazz zoombieOwner = model.createClazz("ZoombieOwner").enableInterface();
		
		Clazz a = model.createClazz("A");
		Clazz ground = model.createClazz("Ground");
		
		a.withSuperClazz(zoombieOwner);
		a.withSuperClazz(ground);
		Method method = a.createMethod("checkEnd");
		method.with(DataType.BOOLEAN).withBody("");

		model.getGenerator().testGeneratedCode();
	}
	@Test
	public void testsMultiExtends() {
		ClassModel model=new ClassModel("org.sdmlib.simple.model.issue31");
		Clazz a = model.createClazz("A");
		Clazz b = model.createClazz("B");
		Clazz c = model.createClazz("C");
		
		a.withSuperClazz(b);
		a.withSuperClazz(c);
		model.getGenerator().testGeneratedCode();
		
	}
}
