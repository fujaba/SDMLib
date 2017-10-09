package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryboardImpl;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Literal;
import de.uniks.networkparser.graph.Parameter;

public class TestModelCreation {

   /**
    * 
    * @see <a href='../../../../../../doc/CreateEntireModel.html'>CreateEntireModel.html</a>
 */
   @Test
	public void testCreateEntireModel() {
		
		StoryboardImpl story = new StoryboardImpl();
		
		ClassModel model = new ClassModel("org.sdmlib.simple.model.modelling_a");

		// Classes
		Clazz person = model.createClazz("Person");
		Clazz pupil = model.createClazz("Pupil");
		Clazz teacher = model.createClazz("Teacher");
		Clazz room = model.createClazz("Room");
		Clazz studentEnum = model.createClazz("StudentEnum").enableEnumeration(new Literal("STUDENT").withValue(42));
		Clazz roomInterface = model.createClazz("roomInterface").enableInterface();
		
		// Attributes
		person.withAttribute("name", DataType.STRING)
			.withAttribute("age", DataType.INT);
		pupil.withAttribute("credits", DataType.INT);
		teacher.withAttribute("rank", DataType.STRING);
		roomInterface.withAttribute("number", DataType.INT);
		
		// Methods
		teacher.createMethod("teach", DataType.STRING)
			.withBody("		String teachResult = \"greatResult\";\n"
					+ "		return teachResult;\n");
		
		person.withMethod("think", DataType.VOID);
		person.withMethod("dontThink", DataType.VOID, new Parameter(DataType.BOOLEAN));
		pupil.withMethod("read", DataType.STRING);
		
		// Super Classes
		pupil.withSuperClazz(person);
		teacher.withSuperClazz(person);
		
		// implemented Intefaces
		room.withSuperClazz(roomInterface);
		
		// Associations
		room.withBidirectional(person, "persons", Cardinality.MANY, "room", Cardinality.ONE);
		room.withBidirectional(pupil, "currentPupils", Cardinality.MANY, "currentRoom", Cardinality.ONE);
		room.withBidirectional(teacher, "currentTeacher", Cardinality.ONE, "currentRoom", Cardinality.ONE);
		pupil.withBidirectional(teacher, "teacher", Cardinality.ONE, "pupils", Cardinality.MANY);
		
		model.generate("src/test/java");
		
		story.addClassDiagram(model);
		
		story.dumpHTML();
		
	}
	
}
