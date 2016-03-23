package org.sdmlib.test.examples.tttt;



import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Attribute;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.DataTypeSet;
import de.uniks.networkparser.graph.Method;
import de.uniks.networkparser.graph.Modifier;
import de.uniks.networkparser.graph.Parameter;


public class TestModelGen {
	@Test
	public void testGen() {
		ClassModel model=new ClassModel("org.sdmlib.test.examples.tttt.model");
		Clazz uni = model.createClazz("Uni");
		Method createMethod = uni.createMethod("create", new Parameter(DataTypeSet.create(uni)));
		createMethod.with(Modifier.STATIC);
		createMethod.with(Annotation.DEPRECATED);
		Attribute nameAttr = uni.createAttribute("name", DataType.STRING);
		nameAttr.with(Modifier.STATIC);
		Clazz person = model.createClazz("Person");
		person.createAttribute("name", DataType.STRING);
//		Clazz student = model.createClazz("Student").withSuperClazz(person);
		Clazz prof = model.createClazz("Prof").withSuperClazz(person);

		Method doitMethod = person.createMethod("doit");
		person.enableInterface();
		
		uni.withBidirectional(person, "has", Cardinality.MANY, "owner", Cardinality.ONE);
		
//		model.generate("src/test/java");
//		
//		
//		Uni uni2 = new Uni();
//		uni2.withHas(new Student().withName("Stefan"));
//		uni2.withHas(new Prof().withName("Albert"));
//		
////		JsonIdMap map = new JsonIdMap().with(new UniCreator(), new PersonCreator());
//		JsonIdMap map = UniCreator.createIdMap(null);
//		System.out.println(map.encode(uni2));
		
//		JsonIdMap map = uniCreator.createIdMap(null);
//		
//		JsonObject json = map.encode(uni2);
//		
//		map.withUpdateListenerSend(new UpdateListener() {
//			
//			@Override
//			public boolean update(String typ, BaseItem source, Object target, String property, Object oldValue,
//					Object newValue) {
//				// Update
//				return false;
//			}
//		});
//		
//		String string = json.toString();
//		
//		System.out.println(json);
//		
//		
//		JsonIdMap decodemap = uniCreator.createIdMap(null);
//		uni decode = (org.sdmlib.test.examples.tttt.model.uni) decodemap.decode(string);
	}

}
