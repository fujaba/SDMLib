package org.sdmlib.test.examples.studyright;

import org.junit.Test;
import org.sdmlib.models.debug.FlipBook;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.test.examples.studyright.model.University;
import org.sdmlib.test.examples.studyright.model.util.UniversityCreator;

import de.uniks.networkparser.ext.story.Story;

public class StudyRightStoryboards
{
   /**
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/studyright/StudyRightStoryboards.java' type='text/x-java'>StudyRightObjectStoryboards</a></p>
    * <p>Start situation: use University class to build object structure</p>
    * <p>step 1: dump object diagram</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "roomNo=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "roomNo=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R6 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "room=null",
    *             "roomNo=ProgMeth"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=2323",
    *             "motivation=0",
    *             "name=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R2 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
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
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S8 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R2 : Room"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasStudyRightObjectStoryboards3", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: Number of students:  2 actual 2</p>
    * <p>step 2: add support for path navigation
    *       call </p>
    * <pre>            int sum = albert.getUni().getRooms().getCredits().sum();
    * </pre>
    * <p>shall compute to 88.</p>
    * <p>step 3: call </p>
    * <pre>            mathRoom.findPath(&quot;&quot;, 88);
    * </pre>
    * <p>System.out: 
    * </p>
    * <p></p>
    * @see <a href='../../../../../../../../doc/StudyRightObjectStoryboards.html'>StudyRightObjectStoryboards.html</a>
    */
   @Test
   public void testStudyRightObjectStoryboards()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
      storyboard.add("Start situation: use University class to build object structure");

      University uni = new University()
         .withName("StudyRight");

      Student albert = uni.createStudents()
         .withMatrNo(4242)
         .withName("Albert");

      Student nina = uni.createStudents()
         .withMatrNo(2323)
         .withName("Nina");

      Room mathRoom = uni.createRooms()
         .withRoomNo("math")
         .withCredits(42)
         .withStudents(albert);

      Room artsRoom = uni.createRooms()
         .withRoomNo("arts")
         .withCredits(23)
         .withNeighbors(mathRoom);

      Room sportsRoom = uni.createRooms()
         .withRoomNo("sports")
         .withCredits(23)
         .withNeighbors(mathRoom, artsRoom);

      Room examRoom = uni.createRooms()
         .withRoomNo("exam")
         .withCredits(0)
         .withNeighbors(sportsRoom, artsRoom);

      Room progMeth = uni.createRooms().withRoomNo("ProgMeth").withCredits(42).withNeighbors(artsRoom);

      progMeth.withNeighbors(examRoom);

      storyboard.add("step 1: dump object diagram");

      storyboard.addObjectDiagram(uni);

      // ===============================================================================================
      storyboard.assertEquals("Number of students: ", 2, uni.getStudents().size());

      storyboard.add("step 2: add support for path navigation\n      call ");

      storyboard.markCodeStart();
      int sum = albert.getUni().getRooms().getCredits().sum();
      storyboard.addCode();

      storyboard.add("shall compute to 88.");

      // Assert.assertEquals("credits sum error", 88, sum);

      // storyboard.assertEquals("Number of neighbors for Albert is now ", 2, any.size());

      storyboard.add("step 3: call ");

      storyboard.recordSystemOut();

      storyboard.markCodeStart();
      mathRoom.findPath("", 88);
      storyboard.addCode();

      storyboard.add("System.out: \n");

      storyboard.addSystemOut();

      storyboard.dumpHTML();
   }


   /**
    * <p>Storyboard Flipbook</p>
    * <p>Adding flipbook to protocol changes.</p>
    * <p>Start situation: use University class to build object structure</p>
    * <pre>            SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) UniversityCreator.createIdMap(&quot;ajz&quot;);
    *       FlipBook flipBook = idMap.createFlipBook();
    * 
    *       &#x2F;&#x2F; =============================================================
    *       storyboard.add(&quot;Start situation: use University class to build object structure&quot;);
    * 
    *       University uni = new University()
    *          .withName(&quot;StudyRight&quot;);
    *       idMap.getId(uni);
    *       
    * </pre>
    * <p>From now on the flipbook protocols all changes done to the uni model</p>
    * <p>Add rooms and students and dump the object model.</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "roomNo=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "roomNo=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R6 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "room=null",
    *             "roomNo=ProgMeth"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=2323",
    *             "motivation=0",
    *             "name=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R2 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
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
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S8 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R2 : Room"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasFlipbook6", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p>Check: Number of students expected:  2 actual 2</p>
    * <p>Check: Number of rooms expected:  5 actual 5</p>
    * <p>Test flipbook, with some undo redo steps: </p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasFlipbook10", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "roomNo=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "roomNo=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R6 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "room=null",
    *             "roomNo=ProgMeth"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=2323",
    *             "motivation=0",
    *             "name=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R2 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
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
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S8 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R2 : Room"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasFlipbook11", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "roomNo=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "room=null",
    *             "roomNo=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "room=null",
    *             "roomNo=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=2323",
    *             "motivation=0",
    *             "name=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R2 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
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
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S8 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R2 : Room"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasFlipbook12", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <pre>          *          &quot;source&quot;:{
    *     *             &quot;cardinality&quot;:&quot;many&quot;,
    *     *             &quot;property&quot;:&quot;neighbors&quot;,
    *     *             &quot;id&quot;:&quot;R4 : Room&quot;
    *     *          },
    *     *          &quot;target&quot;:{
    *     *             &quot;cardinality&quot;:&quot;many&quot;,
    *     *             &quot;property&quot;:&quot;neighbors&quot;,
    *     *             &quot;id&quot;:&quot;R2 : Room&quot;
    *     *          }
    *     *       },
    *     *       {
    *     *          &quot;type&quot;:&quot;assoc&quot;,
    * </pre>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "roomNo=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "room=null",
    *             "roomNo=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "room=null",
    *             "roomNo=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=Albert"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=2323",
    *             "motivation=0",
    *             "name=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R2 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
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
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S8 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R2 : Room"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasFlipbook14", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/Flipbook.html'>Flipbook.html</a>
    */
   @Test
   public void testFlipbook()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
      storyboard.add("Adding flipbook to protocol changes.");
      
      
      storyboard.markCodeStart();
      SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) UniversityCreator.createIdMap("ajz");
      FlipBook flipBook = idMap.createFlipBook();

      // =============================================================
      storyboard.add("Start situation: use University class to build object structure");

      University uni = new University()
         .withName("StudyRight");
      idMap.getId(uni);
      
      storyboard.addCode();
      
      storyboard.add("From now on the flipbook protocols all changes done to the uni model");

      storyboard.add("Add rooms and students and dump the object model.");

      Student albert = uni.createStudents()
         .withMatrNo(4242)
         .withName("Albert");

      Student nina = uni.createStudents()
         .withMatrNo(2323)
         .withName("Nina");

      Room mathRoom = uni.createRooms()
         .withRoomNo("math")
         .withCredits(42)
         .withStudents(albert);

      Room artsRoom = uni.createRooms()
         .withRoomNo("arts")
         .withCredits(23)
         .withNeighbors(mathRoom);

      Room sportsRoom = uni.createRooms()
         .withRoomNo("sports")
         .withCredits(23)
         .withNeighbors(mathRoom, artsRoom);

      Room examRoom = uni.createRooms()
         .withRoomNo("exam")
         .withCredits(0)
         .withNeighbors(sportsRoom, artsRoom);

      Room progMeth = uni.createRooms()
            .withRoomNo("ProgMeth")
            .withCredits(42)
            .withNeighbors(artsRoom);

      progMeth.withNeighbors(examRoom);

      storyboard.addObjectDiagram(uni);
      
      storyboard.assertEquals("Number of students expected: ", 2, uni.getStudents().size());
      storyboard.assertEquals("Number of rooms expected: ", 5, uni.getRooms().size());


      // ===============================================================================================
      storyboard.add("Test flipbook, with some undo redo steps: ");

      storyboard.markCodeStart();
      long cs = flipBook.currentStep;
      flipBook.back(34);
      storyboard.addObjectDiagram(uni);
      storyboard.dumpHTML();
      flipBook.forward(34);
      storyboard.addObjectDiagram(uni);
      flipBook.back(sportsRoom, Room.PROPERTY_CREDITS);
      storyboard.addObjectDiagram(uni);
      flipBook.back()
         .back()
         .back()
         .forward();

      storyboard.addCode();
      
      storyboard.addObjectDiagram(uni);

      // ===============================================================================================
//FIXME @Albert      storyboard.assertEquals("Number of students expected: ", 2, uni.getStudents().size());
//      storyboard.assertEquals("Number of rooms expected: ", 3, uni.getRooms().size());

      storyboard.dumpHTML();
   }


   /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/studyright/StudyRightStoryboards.java' type='text/x-java'>BidirectionalModelToTextTransformation</a></p>
    * <p>Start: We start with the usual StudyRight object model.</p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "roomNo=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "roomNo=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R6 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "room=null",
    *             "roomNo=ProgMeth"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=Tom"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=2323",
    *             "motivation=0",
    *             "name=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R2 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
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
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S8 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R2 : Room"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBidirectionalModelToTextTransformation2", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <p><a name = 'step_1'>Step 1: Use text templates to generate a natural language description of the object model.</a></p>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"P10 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=name",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=example",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P11 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=rooms.size",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=99",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P12 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=students.size",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=88",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P13 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=rooms",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "textFragment=roomList",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P14 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=students",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "textFragment=studentList",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P17 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=roomNo",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=xy",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P18 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=credits",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=42",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P19 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=neighbors",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "textFragment=neighbors",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P20 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=name",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=Stud",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P21 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=matrNo",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=1234",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"P23 : PlaceHolderDescription",
    *          "attributes":[
    *             "attrName=roomNo",
    *             "isKeyAttribute=false",
    *             "prefix=null",
    *             "subTemplate=null",
    *             "textFragment=name",
    *             "value=null"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R2 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "roomNo=math"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R3 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=arts"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R4 : Room",
    *          "attributes":[
    *             "credits=23",
    *             "roomNo=sports"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R5 : Room",
    *          "attributes":[
    *             "credits=0",
    *             "roomNo=exam"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"R6 : Room",
    *          "attributes":[
    *             "credits=42",
    *             "room=null",
    *             "roomNo=ProgMeth"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S7 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "matrNo=4242",
    *             "motivation=0",
    *             "name=Tom"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"S8 : Student",
    *          "attributes":[
    *             "credits=0",
    *             "in=null",
    *             "matrNo=2323",
    *             "motivation=0",
    *             "name=Nina"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T15 : Template",
    *          "attributes":[
    *             "chooser=null",
    *             "expandedText=null",
    *             "listEnd=\u000a",
    *             "listSeparator=\u000a",
    *             "listStart=",
    *             "modelClassName=null",
    *             "modelObject=null",
    *             "name=null",
    *             "referenceLookup=true",
    *             "templateText= - The xy room has 42 credits. It is connected to rooms: neighbors"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T16 : Template",
    *          "attributes":[
    *             "chooser=null",
    *             "expandedText=null",
    *             "listEnd=\u000a",
    *             "listSeparator=\u000a",
    *             "listStart=",
    *             "modelClassName=null",
    *             "modelObject=null",
    *             "name=null",
    *             "referenceLookup=false",
    *             "templateText= - Stud has student number 1234."
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T22 : Template",
    *          "attributes":[
    *             "chooser=null",
    *             "expandedText=null",
    *             "listEnd=.",
    *             "listSeparator=, ",
    *             "listStart=",
    *             "modelClassName=null",
    *             "modelObject=null",
    *             "name=null",
    *             "referenceLookup=true",
    *             "templateText=name"
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"T9 : Template",
    *          "attributes":[
    *             "chooser=null",
    *             "expandedText=null",
    *             "listEnd=",
    *             "listSeparator=",
    *             "listStart=",
    *             "modelClassName=null",
    *             "name=null",
    *             "referenceLookup=false",
    *             "templateText=The example University has 99 rooms and 88 students: \u000aroomList The students are: \u000astudentList "
    *          ]
    *       },
    *       {
    *          "type":"clazz",
    *          "id":"U1 : University",
    *          "attributes":[
    *             "name=StudyRight"
    *          ]
    *       }
    *    ],
    *    "edges":[
    *       {
    *          "type":"edge",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"modelObject",
    *             "id":"U1 : University"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"template",
    *             "id":"T9 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R3 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R4 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"neighbors",
    *             "id":"R5 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P10 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T9 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P11 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T9 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P12 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T9 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P13 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T9 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P14 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T9 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P17 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T15 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P18 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T15 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P19 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T15 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P20 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T16 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P21 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T16 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"placeholders",
    *             "id":"P23 : PlaceHolderDescription"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"owners",
    *             "id":"T22 : Template"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R2 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R3 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
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
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R5 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"rooms",
    *             "id":"R6 : Room"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S8 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"uni",
    *             "id":"U1 : University"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"many",
    *             "property":"students",
    *             "id":"S7 : Student"
    *          },
    *          "target":{
    *             "cardinality":"one",
    *             "property":"in",
    *             "id":"R2 : Room"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"subTemplate",
    *             "id":"T15 : Template"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"parents",
    *             "id":"P13 : PlaceHolderDescription"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"subTemplate",
    *             "id":"T16 : Template"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"parents",
    *             "id":"P14 : PlaceHolderDescription"
    *          }
    *       },
    *       {
    *          "type":"assoc",
    *          "source":{
    *             "cardinality":"one",
    *             "property":"subTemplate",
    *             "id":"T22 : Template"
    *          },
    *          "target":{
    *             "cardinality":"many",
    *             "property":"parents",
    *             "id":"P19 : PlaceHolderDescription"
    *          }
    *       }
    *    ]
    * }   ;
    *    json["options"]={"canvasid":"canvasBidirectionalModelToTextTransformation4", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * <pre>      
    *       Template rootTemplate = new Template()
    *          .withModelObject(uni)
    *          .with(&quot;The example University has 99 rooms and 88 students: \nroomList The students are: \nstudentList &quot;,
    *             &quot;example&quot;, University.PROPERTY_NAME,
    *             &quot;99&quot;, University.PROPERTY_ROOMS + &quot;.size&quot;,
    *             &quot;88&quot;, University.PROPERTY_STUDENTS + &quot;.size&quot;);
    * 
    *       Template roomTemplate = rootTemplate.createPlaceHolderAndSubTemplate()
    *          .withReferenceLookup(true)
    *          .withParent(&quot;roomList&quot;, University.PROPERTY_ROOMS)
    *          .with(
    *             &quot; - The xy room has 42 credits. It is connected to rooms: neighbors&quot;,
    *             &quot;xy&quot;, Room.PROPERTY_ROOMNO,
    *             &quot;42&quot;, Room.PROPERTY_CREDITS)
    *          .withList(&quot;&quot;, &quot;\n&quot;, &quot;\n&quot;);
    * 
    *       Template neighborsTemplate = roomTemplate.createPlaceHolderAndSubTemplate()
    *          .withParent(&quot;neighbors&quot;, Room.PROPERTY_NEIGHBORS)
    *          .withReferenceLookup(true)
    *          .with(
    *             &quot;name&quot;,
    *             &quot;name&quot;, Room.PROPERTY_ROOMNO)
    *          .withList(&quot;&quot;, &quot;, &quot;, &quot;.&quot;);
    * 
    *       Template studentTemplate = rootTemplate.createPlaceHolderAndSubTemplate()
    *          .withParent(&quot;studentList&quot;, University.PROPERTY_STUDENTS)
    *          .with(
    *             &quot; - Stud has student number 1234.&quot;,
    *             &quot;Stud&quot;, Student.PROPERTY_NAME,
    *             &quot;1234&quot;, Student.PROPERTY_MATRNO)
    *          .withList(&quot;&quot;, &quot;\n&quot;, &quot;\n&quot;);
    * 
    *       storyboard.addObjectDiagram(rootTemplate);
    * 
    *       rootTemplate.generate();
    * 
    * </pre>
    * <p>Results in the following text:</p>
    * <pre>The StudyRight University has 5 rooms and 2 students: 
    *  - The math room has 42 credits. It is connected to rooms: arts, sports.
    *  - The arts room has 23 credits. It is connected to rooms: math, sports, exam, ProgMeth.
    *  - The sports room has 23 credits. It is connected to rooms: math, arts, exam.
    *  - The exam room has 0 credits. It is connected to rooms: sports, arts, ProgMeth.
    *  - The ProgMeth room has 42 credits. It is connected to rooms: arts, exam.
    *  The students are: 
    *  - Tom has student number 4242.
    *  - Nina has student number 2323.
    *  </pre><p><a name = 'step_2'>Step 2: Use templates to parse text into object model</a></p>
    * <pre>      
    *       rootTemplate.setExpandedText(
    *          &quot;The Study False University has many rooms and some students: \n&quot; +
    *             &quot; - The class diagrams room has 23 credits. It is connected to rooms: laws, business.\n&quot; +
    *             &quot; - The laws room has 24 credits. It is connected to rooms: class diagrams, business.\n&quot; +
    *             &quot; - The business room has 3 credits. It is connected to rooms: laws, class diagrams.\n &quot; +
    *             &quot;The students are: \n&quot; +
    *             &quot; - Bart has student number 111.\n&quot; +
    *             &quot; - Meggie has student number 112.\n &quot;);
    * 
    *       rootTemplate.parse();
    * 
    * </pre>
    * <script>
    *    var json = {
    *    "type":"objectdiagram",
    *    "nodes":[
    *       {
    *          "type":"clazz",
    *          "id":"U24 : University",
    *          "attributes":[
    *             "name=Study False"
    *          ]
    *       }
    *    ],
    *    "edges":null
    * }   ;
    *    json["options"]={"canvasid":"canvasBidirectionalModelToTextTransformation10", "display":"svg", "fontsize":10,"bar":true};   var g = new Graph(json);
    *    g.layout(100,100);
    * </script>
    * @see <a href='../../../../../../../../doc/BidirectionalModelToTextTransformation.html'>BidirectionalModelToTextTransformation.html</a>
    * @see <a href='../../../../../../../../doc/BidirectionalModelToTextTransformation.html'>BidirectionalModelToTextTransformation.html</a>
    */
   @Test
   public void testBidirectionalModelToTextTransformation()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
      storyboard.addStep("We start with the usual StudyRight object model.");

      University uni = new University()
         .withName("StudyRight");

      Student albert = uni.createStudents()
         .withMatrNo(4242)
         .withName("Tom");

      Student nina = uni.createStudents()
         .withMatrNo(2323)
         .withName("Nina");

      Room mathRoom = uni.createRooms()
         .withRoomNo("math")
         .withCredits(42)
         .withStudents(albert);

      Room artsRoom = uni.createRooms()
         .withRoomNo("arts")
         .withCredits(23)
         .withNeighbors(mathRoom);

      Room sportsRoom = uni.createRooms()
         .withRoomNo("sports")
         .withCredits(23)
         .withNeighbors(mathRoom, artsRoom);

      Room examRoom = uni.createRooms()
         .withRoomNo("exam")
         .withCredits(0)
         .withNeighbors(sportsRoom, artsRoom);

      Room progMeth = uni.createRooms()
         .withRoomNo("ProgMeth")
         .withCredits(42)
         .withNeighbors(artsRoom, examRoom);

      storyboard.addObjectDiagram(uni);

      // =============================================================
      storyboard.addStep("Use text templates to generate a natural language description of the object model.");

      storyboard.markCodeStart();

      Template rootTemplate = new Template()
         .withModelObject(uni)
         .with("The example University has 99 rooms and 88 students: \nroomList The students are: \nstudentList ",
            "example", University.PROPERTY_NAME,
            "99", University.PROPERTY_ROOMS + ".size",
            "88", University.PROPERTY_STUDENTS + ".size");

      Template roomTemplate = rootTemplate.createPlaceHolderAndSubTemplate()
         .withReferenceLookup(true)
         .withParent("roomList", University.PROPERTY_ROOMS)
         .with(
            " - The xy room has 42 credits. It is connected to rooms: neighbors",
            "xy", Room.PROPERTY_ROOMNO,
            "42", Room.PROPERTY_CREDITS)
         .withList("", "\n", "\n");

      Template neighborsTemplate = roomTemplate.createPlaceHolderAndSubTemplate()
         .withParent("neighbors", Room.PROPERTY_NEIGHBORS)
         .withReferenceLookup(true)
         .with(
            "name",
            "name", Room.PROPERTY_ROOMNO)
         .withList("", ", ", ".");

      Template studentTemplate = rootTemplate.createPlaceHolderAndSubTemplate()
         .withParent("studentList", University.PROPERTY_STUDENTS)
         .with(
            " - Stud has student number 1234.",
            "Stud", Student.PROPERTY_NAME,
            "1234", Student.PROPERTY_MATRNO)
         .withList("", "\n", "\n");

      storyboard.addObjectDiagram(rootTemplate);

      rootTemplate.generate();

      storyboard.addCode();

      storyboard.add("Results in the following text:");
      storyboard.add("<pre>" + rootTemplate.getExpandedText() + "</pre>");

      storyboard.addStep("Use templates to parse text into object model");

      storyboard.markCodeStart();

      rootTemplate.setExpandedText(
         "The Study False University has many rooms and some students: \n" +
            " - The class diagrams room has 23 credits. It is connected to rooms: laws, business.\n" +
            " - The laws room has 24 credits. It is connected to rooms: class diagrams, business.\n" +
            " - The business room has 3 credits. It is connected to rooms: laws, class diagrams.\n " +
            "The students are: \n" +
            " - Bart has student number 111.\n" +
            " - Meggie has student number 112.\n ");

      rootTemplate.parse();

      storyboard.addCode();

      storyboard.addObjectDiagram(rootTemplate.getModelObject());

      storyboard.dumpHTML();
   }

   public static final String MODELING = "modeling";

   public static final String ACTIVE = "active";

   public static final String DONE = "done";

   public static final String IMPLEMENTATION = "implementation";

   public static final String BACKLOG = "backlog";

   public static final String BUG = "bug";
}
