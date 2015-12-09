package org.sdmlib.test.examples.tttt;



import org.junit.Test;
import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Modifier;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.models.classes.SetDataType;


public class TestModelGen {
	@Test
	public void testGen() {
		ClassModel model=new ClassModel("org.sdmlib.test.examples.tttt.model");
		Clazz uni = model.createClazz("Uni");
		Method createMethod = uni.createMethod("create", new Parameter(SetDataType.ref(uni)));
		createMethod.with(Modifier.STATIC);
		createMethod.withAnnotation(Annotation.createDeprecatedAnnotation());
		Attribute nameAttr = uni.createAttribute("name", DataType.STRING);
		nameAttr.with(Modifier.STATIC);
		Clazz person = model.createClazz("Person");
		person.withAttribute("name", DataType.STRING);
//		Clazz student = model.createClazz("Student").withSuperClazz(person);
		Clazz prof = model.createClazz("Prof").withSuperClazz(person);

		Method doitMethod = person.createMethod("doit");
		person.withInterface(true);
		
		uni.withAssoc(person, "has", Card.MANY, "owner", Card.ONE);
		
//		model.generate("src/test/java");
//		
//		
//		Uni uni2 = new Uni();
//		uni2.withHas(new Student().withName("Stefan"));
//		uni2.withHas(new Prof().withName("Albert"));
//		
////		JsonIdMap map = new JsonIdMap().withCreator(new UniCreator(), new PersonCreator());
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
