package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Method;

public class testGen {

	@Test
	public void testModel() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model");
		Clazz person = model.createClazz("Person");
		Clazz room = model.createClazz("Room");
		person.createAttribute("name", DataType.STRING);
		person.createAttribute("age", DataType.INT);

		room.with(new Method("init", DataType.VOID));
		
		person.withBidirectional(room, "room", Cardinality.ONE, "persons", Cardinality.MANY);
		
		// Class
		
		// Class with Attribute
		//
		// Class with multiple Attributes
		// Class with Set Attribute
		// Class with Set Set Attribute
		// Class with Map Attribute
		// Class with Map Map Attribute
		// Class with Set Map Attribute
		// Class with Map Set Attribute

		// Class with Annotation
		//
		// Class with multiple Annotations

		// Class with Method
		//
		// Class with Method return type void no parameters
		// Class with Method return type void and one parameter
		// Class with Method return type void and one class parameter
		// Class with Method return type void and multiple parameters
		
		// Class as superClass with atleast one kidClass
		//
		// Class as superClass with multiple kidClasses
		
		// Class as kidClass
		//
		//
		
		// Class with unidirectional Association
		//
		// Class with multiple unidirectional Associations
		
		// Class with bidirectional Association
		//
		// Class with bidirectional Association one - one
		// Class with bidirectional Association one - many
		// Class with bidirectional Association many - one
		// Class with multiple bidirectional Association one - one
		// Class with multiple bidirectional Association one - many
		// Class with multiple bidirectional Association many - one
		
		// Class with mixed uni and bidirectional Associations

		// Class is Interface
		// Class is Enumeration
		
		model.generate("test");
	}
}
