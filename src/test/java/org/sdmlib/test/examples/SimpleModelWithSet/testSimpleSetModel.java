package org.sdmlib.test.examples.SimpleModelWithSet;

import org.junit.Test;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.MapDataType;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;

public class testSimpleSetModel {

	@Test
	public void testModelGen() {
		ClassModel model = new ClassModel("org.sdmlib.test.examples.SimpleModelWithSet.model");
		Clazz person = model.createClazz("Person");
		Clazz child = model.createClazz("Child");
//		person.with(new Attribute("prename", SetDataType.ref(DataType.STRING)));
		person.withAttribute("name", MapDataType.ref(DataType.STRING, DataType.ref(person)));
		
		Method m1 = new Method("setParent", new Parameter("parent", DataType.ref(person)));
		m1.withBody("if (this.parent != parent) {\n"+
					"if (this.parent != null) {\n"+
					"}}");
//this.ludo.removeFromFields(this);
//}
//this.ludo = ludo;
//if (this.ludo != null) {
//this.ludo.addToFields(this);
//})
		child.with(m1);
		
//		public void setLudo(Ludo ludo) {
//
//}
		child.with(new Attribute("parent", DataType.ref(person)));
		
		model.generate("src/test/java");
	}
}
