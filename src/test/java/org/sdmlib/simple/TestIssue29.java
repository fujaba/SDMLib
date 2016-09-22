package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;

public class TestIssue29 {
	@Test
	public void testIsuue29() {
		ClassModel model=new ClassModel("org.sdmlib.simple.model.issue29");
		Clazz a = model.createClazz("A").enableInterface();
		
		Clazz b = model.createClazz("B");
		Clazz c = model.createClazz("C");
		
		b.withSuperClazz(a);
		c.withSuperClazz(a);
		b.withBidirectional(c, "c", Cardinality.ONE, "b", Cardinality.ONE);
		
		model.getGenerator().testGeneratedCode();
		
	}
}
