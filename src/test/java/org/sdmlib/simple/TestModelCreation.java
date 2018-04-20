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
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/simple/TestModelCreation.java' type='text/x-java'>CreateEntireModel</a></p>
    * <script>
    *    var json = {
    *    "typ":"class",
    *    "nodes":[
    *       {
    *          "typ":"node",
    *          "id":"Person",
    *          "attributes":[
    *             "age : int",
    *             "name : String"
    *          ],
    *          "methods":[
    *             "dontThink(boolean p1)",
    *             "think()"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Pupil",
    *          "attributes":[
    *             "credits : int"
    *          ],
    *          "methods":[
    *             "read() String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Room",
    *          "attributes":[
    *             "number : int"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"StudentEnum",
    *          "attributes":[
    *             "value0 : Integer"
    *          ],
    *          "methods":[
    *             "StudentEnum(java.lang.Integer value0) "
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Teacher",
    *          "attributes":[
    *             "rank : String"
    *          ],
    *          "methods":[
    *             "teach() String"
    *          ]
    *       },
    *       {
    *          "typ":"node",
    *          "id":"Integer"
    *       },
    *       {
    *          "typ":"node",
    *          "id":"roomInterface",
    *          "attributes":[
    *             "number : int"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"persons"
    *          },
    *          "target":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"room"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Pupil",
    *             "cardinality":"one",
    *             "property":"pupil"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"person"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Teacher",
    *             "cardinality":"one",
    *             "property":"teacher"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"person"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Pupil",
    *             "cardinality":"many",
    *             "property":"currentPupils"
    *          },
    *          "target":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"currentRoom"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Pupil",
    *             "cardinality":"many",
    *             "property":"pupils"
    *          },
    *          "target":{
    *             "id":"Teacher",
    *             "cardinality":"one",
    *             "property":"teacher"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Pupil",
    *             "cardinality":"one",
    *             "property":"pupil"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"person"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"currentRoom"
    *          },
    *          "target":{
    *             "id":"Pupil",
    *             "cardinality":"many",
    *             "property":"currentPupils"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"currentRoom"
    *          },
    *          "target":{
    *             "id":"Teacher",
    *             "cardinality":"one",
    *             "property":"currentTeacher"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"room"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"many",
    *             "property":"persons"
    *          }
    *       },
    *       {
    *          "typ":"implements",
    *          "source":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"room"
    *          },
    *          "target":{
    *             "id":"roomInterface",
    *             "cardinality":"one",
    *             "property":"roominterface"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Teacher",
    *             "cardinality":"one",
    *             "property":"currentTeacher"
    *          },
    *          "target":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"currentRoom"
    *          }
    *       },
    *       {
    *          "typ":"assoc",
    *          "source":{
    *             "id":"Teacher",
    *             "cardinality":"one",
    *             "property":"teacher"
    *          },
    *          "target":{
    *             "id":"Pupil",
    *             "cardinality":"many",
    *             "property":"pupils"
    *          }
    *       },
    *       {
    *          "typ":"generalisation",
    *          "source":{
    *             "id":"Teacher",
    *             "cardinality":"one",
    *             "property":"teacher"
    *          },
    *          "target":{
    *             "id":"Person",
    *             "cardinality":"one",
    *             "property":"person"
    *          }
    *       },
    *       {
    *          "typ":"implements",
    *          "source":{
    *             "id":"Room",
    *             "cardinality":"one",
    *             "property":"room"
    *          },
    *          "target":{
    *             "id":"roomInterface",
    *             "cardinality":"one",
    *             "property":"roominterface"
    *          }
    *       }
    *    ]
    * }   ;
    *    new Graph(json, {"canvasid":"canvasCreateEntireModelClassDiagram0", "display":"html", fontsize:10, bar:false, propertyinfo:false}).layout(100,100);
    * </script>
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
