package org.sdmlib.test.examples.pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sdmlib.models.pattern.POCreator;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.storyboards.StoryboardImpl;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.test.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.test.examples.studyrightWithAssignments.model.University;

public class POCreatorTest
{

   @Test
   public void testPOCreator()
   {
      // Storyboard storyboard = new Storyboard();
      University university = new University();
      Room mathRoom = university.withName("UniKasselVersitaet").createRooms().withCredits(6).withName("Math");
      mathRoom.createStudents().withName("Jane Doe");

      // storyboard.addObjectDiagram(university);

      POCreator docDataPOCreator = new POCreator();
      PatternObject patternObject = docDataPOCreator
         .createPO(university);

      // storyboard.dumpDiagram(patternObject, "POTest");

      patternObject.rebind(university);
      assertTrue(patternObject.isHasMatch());
      assertEquals(3, patternObject.getPattern().getPatternObjectCount());

      // storyboard.dumpHTML();
   }

     /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/pattern/POCreatorTest.java' type='text/x-java'>POCreatorLargeModel</a></p>
    * <p>Start: At first we instanciate a Model</p>
    * <pre>            University university = new University().withName(&quot;StudyRight University&quot;);
    *       Room mathRoom = university.createRooms().withCredits(6).withName(&quot;Multiply&quot;).withTopic(&quot;Math&quot;);
    *       Student joan = mathRoom.createStudents().withName(&quot;Jane Doe&quot;);
    *       Student peter = mathRoom.createStudentsTeachingAssistant().withName(&quot;Peter Teacher&quot;).withIn(mathRoom);
    *       mathRoom.createAssignments().withContent(&quot;1*1&quot;).withPoints(2).withStudents(joan);
    *       university.withStudents(joan, peter);
    * </pre>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"A5 : Assignment",
    *          "attributes":[
    *             "content=1*1",
    *             "points=2"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=6",
    *             "name=Multiply",
    *             "topic=Math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S2 : Student",
    *          "attributes":[
    *             "assignmentPoints=0",
    *             "credits=0",
    *             "id=null",
    *             "motivation=0",
    *             "name=Jane Doe"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T3 : TeachingAssistant",
    *          "attributes":[
    *             "assignmentPoints=0",
    *             "certified=false",
    *             "credits=0",
    *             "id=null",
    *             "motivation=0",
    *             "name=Peter Teacher",
    *             "room=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight University",
    *             "president=null"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"assignments",
    *             "id":"A5 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"room",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"done",
    *             "id":"A5 : Assignment"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S2 : Student"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S2 : Student"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"T3 : TeachingAssistant"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S2 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"T3 : TeachingAssistant"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"university",
    *             "id":"U1 : University"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasPOCreatorLargeModel3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p><a name = 'step_1'>Step 1: Now the corresponding Pattern is generated</a></p>
    * <pre>            POCreator poCreator = new POCreator();
    *       PatternObject po = poCreator
    *          .createPO(university);
    * </pre>
    * <p><a name = 'step_2'>Step 2: Now we want to rebind the patternObject to the University model and look for the matches</a></p>
    * <pre>            po.rebind(university);
    * </pre>
    * <script>
    *    var json = {
    *    "type":"object",
    *    "nodes":[
    *       {
    *          "type":"patternObject",
    *          "id":"u1 : UniversityPO",
    *          "attributes":[
    *             "<< bound>>",
    *             "name == StudyRight University",
    *             "president == null"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"s2 : StudentPO",
    *          "attributes":[
    *             "name == Jane Doe",
    *             "id == null",
    *             "assignmentPoints == 0",
    *             "motivation == 0",
    *             "credits == 0"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"r3 : RoomPO",
    *          "attributes":[
    *             "name == Multiply",
    *             "topic == Math",
    *             "credits == 6"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"t4 : TeachingAssistantPO",
    *          "attributes":[
    *             "certified == false",
    *             "name == Peter Teacher",
    *             "id == null",
    *             "assignmentPoints == 0",
    *             "motivation == 0",
    *             "credits == 0",
    *             "room == null"
    *          ]
    *       },
    *       {
    *          "type":"patternObject",
    *          "id":"a5 : AssignmentPO",
    *          "attributes":[
    *             "content == 1*1",
    *             "points == 2"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"u1 : UniversityPO"
    *          },
    *          "target":{
    *             "property":"students",
    *             "id":"s2 : StudentPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"s2 : StudentPO"
    *          },
    *          "target":{
    *             "property":"university",
    *             "id":"u1 : UniversityPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"s2 : StudentPO"
    *          },
    *          "target":{
    *             "property":"in",
    *             "id":"r3 : RoomPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"r3 : RoomPO"
    *          },
    *          "target":{
    *             "property":"university",
    *             "id":"u1 : UniversityPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"r3 : RoomPO"
    *          },
    *          "target":{
    *             "property":"students",
    *             "id":"s2 : StudentPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"r3 : RoomPO"
    *          },
    *          "target":{
    *             "property":"students",
    *             "id":"t4 : TeachingAssistantPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"t4 : TeachingAssistantPO"
    *          },
    *          "target":{
    *             "property":"university",
    *             "id":"u1 : UniversityPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"t4 : TeachingAssistantPO"
    *          },
    *          "target":{
    *             "property":"in",
    *             "id":"r3 : RoomPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"r3 : RoomPO"
    *          },
    *          "target":{
    *             "property":"students",
    *             "id":"t4 : TeachingAssistantPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"r3 : RoomPO"
    *          },
    *          "target":{
    *             "property":"assignments",
    *             "id":"a5 : AssignmentPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"a5 : AssignmentPO"
    *          },
    *          "target":{
    *             "property":"students",
    *             "id":"s2 : StudentPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"a5 : AssignmentPO"
    *          },
    *          "target":{
    *             "property":"room",
    *             "id":"r3 : RoomPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"r3 : RoomPO"
    *          },
    *          "target":{
    *             "property":"assignments",
    *             "id":"a5 : AssignmentPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"s2 : StudentPO"
    *          },
    *          "target":{
    *             "property":"in",
    *             "id":"r3 : RoomPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"s2 : StudentPO"
    *          },
    *          "target":{
    *             "property":"done",
    *             "id":"a5 : AssignmentPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"u1 : UniversityPO"
    *          },
    *          "target":{
    *             "property":"students",
    *             "id":"s2 : StudentPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"u1 : UniversityPO"
    *          },
    *          "target":{
    *             "property":"students",
    *             "id":"t4 : TeachingAssistantPO"
    *          }
    *       },
    *       {
    *          "typ":"EDGE",
    *          "source":{
    *             "property":" ",
    *             "id":"u1 : UniversityPO"
    *          },
    *          "target":{
    *             "property":"rooms",
    *             "id":"r3 : RoomPO"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasPOCreatorLargeModelPatternDiagram7", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: The PatternObject has a match true</p>
    * <p>Check: The University is the Match of the PatternObject StudyRight University actual StudyRight University</p>
    * <p>Check: The Pattern contains as many PatternObjects as Objects were created in the University Model 5 actual 5</p>
    * @see <a href='../../../../../../../../doc/POCreatorLargeModel.html'>POCreatorLargeModel.html</a>
 */
   @Test
   public void testPOCreatorLargeModel()
   {
      StoryboardImpl storyboard = new StoryboardImpl();
      storyboard.addStep("At first we instanciate a Model");

      storyboard.markCodeStart();
      University university = new University().withName("StudyRight University");
      Room mathRoom = university.createRooms().withCredits(6).withName("Multiply").withTopic("Math");
      Student joan = mathRoom.createStudents().withName("Jane Doe");
      Student peter = mathRoom.createStudentsTeachingAssistant().withName("Peter Teacher").withIn(mathRoom);
      mathRoom.createAssignments().withContent("1*1").withPoints(2).withStudents(joan);
      university.withStudents(joan, peter);
      storyboard.addCode();

      storyboard.addObjectDiagram(university);

      storyboard.addStep("Now the corresponding Pattern is generated");
      storyboard.markCodeStart();
      POCreator poCreator = new POCreator();
      PatternObject po = poCreator
         .createPO(university);
      storyboard.addCode();

      storyboard.addStep("Now we want to rebind the patternObject to the University model and look for the matches");
      storyboard.markCodeStart();
      po.rebind(university);
      storyboard.addCode();

      storyboard.addPattern(po.getPattern(), true);
      storyboard.assertTrue("The PatternObject has a match", po.isHasMatch());
      storyboard.assertEquals("The University is the Match of the PatternObject", university, po.getCurrentMatch());
      storyboard.assertEquals(
         "The Pattern contains as many PatternObjects as Objects were created in the University Model", 5, po
            .getPattern().getPatternObjectCount());
      storyboard.dumpHTML();
   }
}
