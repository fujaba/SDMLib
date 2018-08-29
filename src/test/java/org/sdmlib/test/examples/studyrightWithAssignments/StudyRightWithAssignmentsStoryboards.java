/*
   Copyright (c) 2013 ulno (http://contact.ulno.net)

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software
   and associated documentation files (the "Software"), to deal in the Software without restriction,
   including without limitation the rights to use, copy, modify, merge, publish, distribute,
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
   furnished to do so, subject to the following conditions:

   The above copyright notice and this permission notice shall be included in all copies or
   substantial portions of the Software.

   The Software shall be used for Good, not Evil.

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package org.sdmlib.test.examples.studyrightWithAssignments;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.list.SimpleSet;
import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.CGUtil;
import org.sdmlib.models.YamlIdMap;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.studyrightWithAssignments.model.*;
import org.sdmlib.test.examples.studyrightWithAssignments.model.util.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StudyRightWithAssignmentsStoryboards
{
   /**
    *
    * <h3>Storyboard Yaml</h3>
    * <h4><a name = 'step_1'>Step 1: Read graph from yaml text:</a></h4>
    * <pre>- studyRight: University 
    *   name:       &quot;\&quot;Study \&quot; Right\&quot;And\&quot;Fast now\&quot;&quot;
    *   students:   karli
    *   rooms:      mathRoom artsRoom sportsRoom examRoom softwareEngineering 
    * 
    * - karli: Student
    *   id:    4242
    *   name:  karli
    * 
    * 
    * - Assignment   content:                      points: 
    *   matrixMult:  &quot;Matrix Multiplication&quot;     5
    *   series:      &quot;Series&quot;                    6
    *   a3:          Integrals                     8
    * 
    * - Room                  topic:  credits: doors:                 students: assignments: 
    *   mathRoom:             math    17       null                   karli     [matrixMult series a3]
    *   artsRoom:             arts    16       mathRoom               null      null
    *   sportsRoom:           sports  25       [mathRoom artsRoom]
    *   examRoom:             exam     0       [sportsRoom artsRoom]
    *   softwareEngineering:  &quot;Software Engineering&quot; 42 [artsRoom examRoom]
    * </pre>
    * <h4><a name = 'step_2'>Step 2: Call YamlIdMap.decode:</a></h4>
    * <pre><code class="java" data-lang="java">
    *       YamlIdMap yamlIdMap = new YamlIdMap(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    * 
    *       University studyRight = (University) yamlIdMap.decode(yaml);
    * </code></pre>
    * <h4><a name = 'step_3'>Step 3: Decoded object structure:</a></h4>
    * <img src="doc-files/YamlStep5.png" alt="YamlStep5.png" width='644'>
    * <p>Check: root object exists org.sdmlib.test.examples.studyrightWithAssignments.model.University@43a0cee9</p>
    * <h4><a name = 'step_4'>Step 4: Generate Yaml from model:</a></h4>
    * <pre>- u1: 	University
    *   name: 	&quot;\&quot;Study \&quot; Right\&quot;And\&quot;Fast now\&quot;&quot;
    *   students: 	s2 	
    *   rooms: 	r3 	r4 	r5 	r6 	r7 	
    * 
    * - s2: 	Student
    *   assignmentPoints: 	0
    *   credits: 	0
    *   id: 	4242
    *   motivation: 	0
    *   name: 	karli
    * 
    * - r3: 	Room
    *   credits: 	0
    *   students: 	s2 	
    *   assignments: 	a8 	a9 	a10 	
    *   topic: 	math
    * 
    * - r4: 	Room
    *   credits: 	0
    *   doors: 	r3 	
    *   topic: 	arts
    * 
    * - r5: 	Room
    *   credits: 	0
    *   doors: 	r3 	r4 	
    *   topic: 	sports
    * 
    * - r6: 	Room
    *   credits: 	0
    *   doors: 	r5 	r4 	
    *   topic: 	exam
    * 
    * - r7: 	Room
    *   credits: 	0
    *   doors: 	r4 	r6 	
    *   topic: 	&quot;Software Engineering&quot;
    * 
    * - a8: 	Assignment
    *   content: 	&quot;Matrix Multiplication&quot;
    *   points: 	0
    * 
    * - a9: 	Assignment
    *   content: 	Series
    *   points: 	0
    * 
    * - a10: 	Assignment
    *   content: 	Integrals
    *   points: 	0
    * 
    * </pre>
    * <p>Check: yaml starts with - u... true</p>
    * <h4><a name = 'step_5'>Step 5: decoded again:</a></h4>
    * <img src="doc-files/YamlStep11.png" alt="YamlStep11.png" width='651'>
    * <h4><a name = 'step_6'>Step 6: now read from excel file</a></h4>
    * <pre><code class="java" data-lang="java">
    *       byte[] readAllBytes = Files.readAllBytes(Paths.get(&quot;doc&#x2F;StudyRightStartSituation.txt&quot;));
    *       String excelText = new String(readAllBytes);
    * 
    *       YamlIdMap excelIdMap = new YamlIdMap(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    * 
    *       studyRight = (University) excelIdMap.decode(excelText);
    * </code></pre>
    * <p>doc/StudyRightStartSituation.txt</p>
    * <pre>-	studyRight:	University				
    * 	name: 	&quot;&quot;&quot;Study Right&quot;&quot;&quot;				
    * 	students:	karli				
    * 	rooms: 	mathRoom	artsRoom	sportsRoom	examRoom	softwareEngineering
    * 						
    * -	karli:	Student				
    * 	id:	4242				
    * 	name:	karli				
    * 						
    * -	Assignment	content:	points:
    * 	matrixMult:	&quot;&quot;&quot;Matrix Multiplication&quot;&quot;&quot;	5			
    * 	series:	Series	6			
    * 	a3:	Integrals	8			
    * 						
    * -	Room	topic:	credits:	doors:	students:	assignments:
    * 	mathRoom:	math	17	null	karli	[matricMult series a3]
    * 	artsRoom:	arts	25	mathRoom		
    * 	sportsRoom:	sports	25	[mathRoom artsRoom]		
    * 	examRoom:	exam	0	[sportsRoom artsRoom]		
    * 	softwareEngineering:	&quot;&quot;&quot;Software Engineering&quot;&quot;&quot;	42	[artsRoom examRoom]		
    * </pre>
    * <p>result:</p>
    * <img src="doc-files/YamlStep17.png" alt="YamlStep17.png" width='495'>
    * @throws IOException
    */
   @Test
   public void testYaml() throws IOException
   {

      System.out.println(" (StudyRightWithAssignmentsStoryboards.java:85)");

      Storyboard story = new Storyboard();

      story.addStep("Read graph from yaml text:");

      String yaml = ""
            + "- studyRight: University \n"
            + "  name:       \"\\\"Study \\\" Right\\\"And\\\"Fast now\\\"\"\n"
            + "  students:   karli\n"
            + "  rooms:      mathRoom artsRoom sportsRoom examRoom softwareEngineering \n"
            + "\n"
            + "- karli: Student\n"
            + "  id:    4242\n"
            + "  name:  karli\n"
            + "\n"
            // + "- albert: Prof\n"
            // + "  topic:  SE\n"
            + "\n"
            + "- Assignment   content:                      points: \n"
            + "  matrixMult:  \"Matrix Multiplication\"     5\n"
            + "  series:      \"Series\"                    6\n"
            + "  a3:          Integrals                     8\n"
            + "\n"
            + "- Room                  topic:  credits: doors:                 students: assignments: \n"
            + "  mathRoom:             math    17       null                   karli     [matrixMult series a3]\n"
            + "  artsRoom:             arts    16       mathRoom               null      null\n"
            + "  sportsRoom:           sports  25       [mathRoom artsRoom]\n"
            + "  examRoom:             exam     0       [sportsRoom artsRoom]\n"
            + "  softwareEngineering:  \"Software Engineering\" 42 [artsRoom examRoom]\n"
            + "";

      story.addPreformatted(yaml);

      story.addStep("Call YamlIdMap.decode:");

      story.markCodeStart();
      YamlIdMap yamlIdMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");

      University studyRight = (University) yamlIdMap.decode(yaml);
      story.addCode();

      story.addStep("Decoded object structure:");

      story.addObjectDiagram(studyRight);

      story.assertNotNull("root object exists", studyRight);

//      Object albert = yamlIdMap.getObject("albert");
//
//      story.assertNotNull("pojo albert exists", albert);
//
//      story.assertEquals("pojo attr", "SE", ((Prof)albert).getTopic());

      story.addStep("Generate Yaml from model:");

      YamlIdMap yamlEncodeMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");

      String newYaml = yamlEncodeMap.encode(studyRight);

      story.addPreformatted(newYaml);

      story.assertTrue("yaml starts with - u...", newYaml.startsWith("- u"));

      YamlIdMap yamlDecodeMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");

      University newStudyRight = (University) yamlIdMap.decode(newYaml);

      story.addStep("decoded again:");

      story.addObjectDiagram(newStudyRight);

      story.addStep("now read from excel file");

      story.markCodeStart();
      byte[] readAllBytes = Files.readAllBytes(Paths.get("doc/StudyRightStartSituation.txt"));
      String excelText = new String(readAllBytes);

      YamlIdMap excelIdMap = new YamlIdMap("org.sdmlib.test.examples.studyrightWithAssignments.model");

      studyRight = (University) excelIdMap.decode(excelText);
      story.addCode();

      story.add("doc/StudyRightStartSituation.txt");
      story.addPreformatted(excelText);
      story.add("result:");
      story.addObjectDiagram(studyRight);
      story.dumpJavaDoc(YamlIdMap.class.getName());
      story.dumpHTML();
   }


   /**
    *
    * <h3>Storyboard StudyRightWithAssignmentsStoryboard</h3>
    * <p>1. (start situation/pre-condition) Karli enters the Study-Right University 
    * in the math room. Karli has no credits yet and still a motivation of 214. </p>
    * <pre><code class="java" data-lang="java">
    *       University university = new University()
    *             .withName(&quot;StudyRight&quot;);
    * 
    *       Student karli = university.createStudents()
    *             .withId(&quot;4242&quot;)
    *             .withName(&quot;Karli&quot;);
    * 
    *       Assignment matrixMult = new Assignment()
    *             .withContent(&quot;Matrix Multiplication&quot;)
    *             .withPoints(5);
    * 
    *       Assignment series = new Assignment()
    *             .withContent(&quot;Series&quot;)
    *             .withPoints(6);
    * 
    *       Assignment a3 = new Assignment()
    *             .withContent(&quot;Integrals&quot;)
    *             .withPoints(8);
    * 
    *       Room mathRoom = university.createRooms()
    *             .withName(&quot;senate&quot;)
    *             .withTopic(&quot;math&quot;)
    *             .withCredits(17)
    *             .withStudents(karli)
    *             .withAssignments(matrixMult, series, a3);
    * 
    *       Room artsRoom = university.createRooms()
    *             .withName(&quot;7522&quot;)
    *             .withTopic(&quot;arts&quot;)
    *             .withCredits(16)
    *             .withDoors(mathRoom);
    * 
    *       Room sportsRoom = university.createRooms()
    *             .withName(&quot;gymnasium&quot;)
    *             .withTopic(&quot;sports&quot;)
    *             .withCredits(25)
    *             .withDoors(mathRoom, artsRoom);
    * 
    *       Room examRoom = university.createRooms()
    *             .withName(&quot;The End&quot;)
    *             .withTopic(&quot;exam&quot;)
    *             .withCredits(0)
    *             .withDoors(sportsRoom, artsRoom);
    * 
    *       Room softwareEngineering = university.createRooms()
    *             .withName(&quot;7422&quot;)
    *             .withTopic(&quot;Software Engineering&quot;)
    *             .withCredits(42)
    *             .withDoors(artsRoom, examRoom);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsStoryboardStep2.png" alt="StudyRightWithAssignmentsStoryboardStep2.png" width='644'>
    * <p>2. Karli does assignment a1 on Matrix Multiplication and earns 5 points <br>
    * (general rule: the student earns always full points for doing an assignment). <br>
    * Karli's motivation is reduced by 5 points to now 209.
    * </p>
    * <pre><code class="java" data-lang="java">
    *       karli.setAssignmentPoints(karli.getAssignmentPoints() + matrixMult.getPoints());
    *       karli.withDone(matrixMult);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsStoryboardStep5.png" alt="StudyRightWithAssignmentsStoryboardStep5.png" width='480'>
    * <p>3. Karli does assignment a2 on Series and earns another 6 points. <br>
    * Thus Karli has 11 points now. Motivation is reduced to 203.
    * </p>
    * <pre><code class="java" data-lang="java">
    *       karli.setAssignmentPoints(karli.getAssignmentPoints() + series.getPoints());
    *       karli.withDone(series);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsStoryboardStep8.png" alt="StudyRightWithAssignmentsStoryboardStep8.png" width='463'>
    * <p>4. Karli does the third assignment on Integrals, earns <br>
    * another 8 points and thus Karli has now 19 points and a motivation of 195.
    * </p>
    * <pre><code class="java" data-lang="java">
    *       karli.setAssignmentPoints(karli.getAssignmentPoints() + a3.getPoints());
    *       karli.withDone(a3);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsStoryboardStep11.png" alt="StudyRightWithAssignmentsStoryboardStep11.png" width='484'>
    * <p>5. Since 19 points are more than the 17 points required 
    * for the 17 math credits, Karli hands the points in and earns the credits 
    * and has his assignmnet points reset to 0. <br>
    * (General rule: if the points earned by the assignments are higher or equal than 
    * the credit points, the credit points will be awarded to the student.)</p>
    * <pre><code class="java" data-lang="java">
    *       if (karli.getAssignmentPoints() &gt;= mathRoom.getCredits())
    *       {
    *          karli.setCredits(karli.getCredits() + mathRoom.getCredits());
    *          karli.setAssignmentPoints(0);
    *       }
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsStoryboardStep14.png" alt="StudyRightWithAssignmentsStoryboardStep14.png" width='484'>
    * <p>6. (end situation/post-condition) Karli has completed the math topic and moves to sports.</p>
    * <p>Check: Karli's credits:  17 actual 17</p>
    * <p>Check: Karli's assignment points:  0 actual 0</p>
    * <p>Check: Number of students:  1 actual 1</p>
    */
   @Test
   public void testStudyRightWithAssignmentsStoryboard()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
      storyboard.add("1. (start situation/pre-condition) Karli enters the Study-Right University \n"
            + "in the math room. Karli has no credits yet and still a motivation of 214. ");

      storyboard.markCodeStart();
      University university = new University()
            .withName("StudyRight");

      Student karli = university.createStudents()
            .withId("4242")
            .withName("Karli");

      Assignment matrixMult = new Assignment()
            .withContent("Matrix Multiplication")
            .withPoints(5);

      Assignment series = new Assignment()
            .withContent("Series")
            .withPoints(6);

      Assignment a3 = new Assignment()
            .withContent("Integrals")
            .withPoints(8);

      Room mathRoom = university.createRooms()
            .withName("senate")
            .withTopic("math")
            .withCredits(17)
            .withStudents(karli)
            .withAssignments(matrixMult, series, a3);

      Room artsRoom = university.createRooms()
            .withName("7522")
            .withTopic("arts")
            .withCredits(16)
            .withDoors(mathRoom);

      Room sportsRoom = university.createRooms()
            .withName("gymnasium")
            .withTopic("sports")
            .withCredits(25)
            .withDoors(mathRoom, artsRoom);

      Room examRoom = university.createRooms()
            .withName("The End")
            .withTopic("exam")
            .withCredits(0)
            .withDoors(sportsRoom, artsRoom);

      Room softwareEngineering = university.createRooms()
            .withName("7422")
            .withTopic("Software Engineering")
            .withCredits(42)
            .withDoors(artsRoom, examRoom);
      storyboard.addCode();

      storyboard.addObjectDiagram(
            "studyRight", university,
            "karli", "icons/karli.png", karli,
            "mathRoom", "icons/mathroom.png", mathRoom,
            "artsRoom", artsRoom,
            "sportsRoom", sportsRoom,
            "examRoom", examRoom,
            "placeToBe", softwareEngineering,
            "icons/matrix.png", matrixMult,
            "icons/limes.png", series, "icons/integralAssignment.png", a3);

      // ===============================================================================================
      storyboard.add("2. Karli does assignment a1 on Matrix Multiplication and earns 5 points <br>\n"
            + "(general rule: the student earns always full points for doing an assignment). <br>\n"
            + "Karli's motivation is reduced by 5 points to now 209.\n");

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + matrixMult.getPoints());
      karli.withDone(matrixMult);
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("3. Karli does assignment a2 on Series and earns another 6 points. <br>\n"
            + "Thus Karli has 11 points now. Motivation is reduced to 203.\n");

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + series.getPoints());
      karli.withDone(series);
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("4. Karli does the third assignment on Integrals, earns <br>\n"
            + "another 8 points and thus Karli has now 19 points and a motivation of 195.\n");

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + a3.getPoints());
      karli.withDone(a3);
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("5. Since 19 points are more than the 17 points required \n"
            + "for the 17 math credits, Karli hands the points in and earns the credits \n"
            + "and has his assignmnet points reset to 0. <br>\n"
            + "(General rule: if the points earned by the assignments are higher or equal than \n"
            + "the credit points, the credit points will be awarded to the student.)");

      storyboard.markCodeStart();
      if (karli.getAssignmentPoints() >= mathRoom.getCredits())
      {
         karli.setCredits(karli.getCredits() + mathRoom.getCredits());
         karli.setAssignmentPoints(0);
      }
      storyboard.addCode();

      storyboard.addObjectDiagramOnlyWith(karli, mathRoom, mathRoom.getAssignments());

      // ===============================================================================================
      storyboard.add("6. (end situation/post-condition) Karli has completed the math topic and moves to sports.");

      // ===============================================================================================
      storyboard.assertEquals("Karli's credits: ", 17, karli.getCredits());
      storyboard.assertEquals("Karli's assignment points: ", 0, karli.getAssignmentPoints());
      storyboard.assertEquals("Number of students: ", 1, university.getStudents().size());

      // ================ Create HTML
      storyboard.dumpHTML();
   }


   @Test
   public void testStudyRightWithAssignmentsAggregation()
   {
      University university = new University()
            .withName("StudyRight");

      Student abu = university.createStudents()
            .withId("1337")
            .withName("Abu");

      Student karli = new TeachingAssistant().withCertified(true);
      university.withStudents(karli
            .withId("4242")
            .withName("Karli"));

      Student alice = university.createStudents()
            .withId("2323")
            .withName("Alice");

      abu.withFriends(alice);

      Assignment a1 = new Assignment()
            .withContent("Matrix Multiplication")
            .withPoints(5)
            .withStudents(abu);

      Assignment a2 = new Assignment()
            .withContent("Series")
            .withPoints(6);

      Assignment a3 = new Assignment()
            .withContent("Integrals")
            .withPoints(8);

      karli.withDone(a1, a2);

      Room mathRoom = university.createRooms()
            .withName("senate")
            .withTopic("math")
            .withCredits(17)
            .withStudents(karli)
            .withAssignments(a1, a2, a3);

      Room artsRoom = university.createRooms()
            .withName("7522")
            .withTopic("arts")
            .withCredits(16)
            .withDoors(mathRoom);

      Room sportsRoom = university.createRooms()
            .withName("gymnasium")
            .withTopic("sports")
            .withCredits(25)
            .withDoors(mathRoom, artsRoom)
            .withStudents(abu, alice);

      Assignment a4 = sportsRoom.createAssignments()
            .withContent("Pushups")
            .withPoints(4)
            .withStudents(abu);

      Room examRoom = university.createRooms()
            .withName("The End")
            .withTopic("exam")
            .withCredits(0)
            .withDoors(sportsRoom, artsRoom);

      Room softwareEngineering = university.createRooms()
            .withName("7422")
            .withTopic("Software Engineering")
            .withCredits(42)
            .withDoors(artsRoom, examRoom);

      President president = university.createPresident();

      // Assert.assertEquals("presidents lives", true, president);

      // university.removeYou();
      // TODO: generate removeYou method

      Assert.assertEquals("studyright has no more rooms", 0, university.getRooms().size());
      Assert.assertEquals("karli still has assignments", 2, karli.getDone().size());
      // Assert.assertEquals("presidents is dead", false, president.alive);

   }


   /**
    *
    * <h3>Storyboard JsonPersistency</h3>
    * <p>How to serialize an object model to json and how to read json into an object model</p>
    * <h4><a name = 'step_1'>Step 1: Example object structure:</a></h4>
    * <h4><a name = 'step_2'>Step 2: Serialize to json:</a></h4>
    * <pre><code class="java" data-lang="java">
    * 
    *       IdMap idMap = UniversityCreator.createIdMap(&quot;demo&quot;);
    * 
    *       JsonArray jsonArray = idMap.toJsonArray(university);
    * 
    *       String jsonText = jsonArray.toString(3);
    * 
    *       &#x2F;&#x2F; you might write jsonText into a file
    * 
    * </code></pre>
    * <p>Results in:</p>
    * <pre>[
    *    {
    *       "session":"demo",
    *       "class":"org.sdmlib.test.examples.studyrightWithAssignments.model.University",
    *       "id":"U430482803539741",
    *       "timestamp":"430482803539741",
    *       "prop":{
    *          "name":"StudyRight",
    *          "students":[
    *             {
    *                "session":"demo",
    *                "class":"org.sdmlib.test.examples.studyrightWithAssignments.model.Student",
    *                "id":"S430482803685271",
    *                "timestamp":"430482803685271"
    *             }
    *          ]
    *       }
    *    },
    *    {
    *       "session":"demo",
    *       "class":"org.sdmlib.test.examples.studyrightWithAssignments.model.Student",
    *       "id":"S430482803685271",
    *       "timestamp":"430482803685271",
    *       "prop":{
    *          "id":"4242",
    *          "name":"Karli"
    *       }
    *    }
    * ]</pre><h4><a name = 'step_3'>Step 3: Now read it back again</a></h4>
    * <pre><code class="java" data-lang="java">
    * 
    *       &#x2F;&#x2F; read jsonText from file
    *       IdMap readerMap = UniversityCreator.createIdMap(&quot;demo&quot;);
    * 
    *       Object rootObject = readerMap.decode(jsonText);
    * 
    *       University readUniversity = (University) rootObject;
    * </code></pre>
    * <img src="doc-files/JsonPersistencyStep8.png" alt="JsonPersistencyStep8.png" width='147'>
    */
   @Test
   public void testJsonPersistency()
   {
      Storyboard storyboard = new Storyboard();

      storyboard.add("How to serialize an object model to json and how to read json into an object model");

      storyboard.addStep("Example object structure:");

      University university = new University()
         .withName("StudyRight");

      Student karli = university.createStudents()
         .withId("4242")
         .withName("Karli");

      Assignment a1 = new Assignment()
         .withContent("Matrix Multiplication")
         .withPoints(5);

      Assignment a2 = new Assignment()
         .withContent("Series")
         .withPoints(6);

      Assignment integrals = new Assignment()
         .withContent("Integrals")
         .withPoints(8);

//      Room mathRoom = university.createRooms()
//         .withName("senate")
//         .withTopic("math")
//         .withCredits(17)
//         .withStudents(karli)
//         .withAssignments(a1, a2, integrals);

//      Room artsRoom = university.createRooms()
//         .withName("7522")
//         .withTopic("arts")
//         .withCredits(16)
//         .withDoors(mathRoom);
//
//      Room sportsRoom = university.createRooms()
//         .withName("gymnasium")
//         .withTopic("sports")
//         .withCredits(25)
//         .withDoors(mathRoom, artsRoom);

//      Room examRoom = university.createRooms()
//         .withName("The End")
//         .withTopic("exam")
//         .withCredits(0)
//         .withDoors(sportsRoom, artsRoom);
//
//      Room softwareEngineering = university.createRooms()
//         .withName("7422")
//         .withTopic("Software Engineering")
//         .withCredits(42)
//         .withDoors(artsRoom, examRoom);

//      storyboard.addObjectDiagram(
//         "studyRight", university,
//         "karli", "icons/karli.png", karli,
//         "mathRoom", "icons/mathroom.png", mathRoom,
//         "artsRoom", artsRoom,
//         "sportsRoom", sportsRoom,
//         "examRoom", examRoom,
//         "placeToBe", softwareEngineering,
//         "icons/matrix.png", a1,
//         "icons/limes.png", a2, "icons/integralAssignment.png", integrals);

      // =====================================================
      storyboard.addStep("Serialize to json:");

      storyboard.markCodeStart();

      IdMap idMap = UniversityCreator.createIdMap("demo");

      JsonArray jsonArray = idMap.toJsonArray(university);

      String jsonText = jsonArray.toString(3);

      // you might write jsonText into a file

      storyboard.addCode();

      storyboard.add("Results in:");

      storyboard.add("<pre>" + jsonText + "</pre>");

      // =====================================================
      storyboard.addStep("Now read it back again");

      storyboard.markCodeStart();

      // read jsonText from file
      IdMap readerMap = UniversityCreator.createIdMap("demo");

      Object rootObject = readerMap.decode(jsonText);

      University readUniversity = (University) rootObject;
      storyboard.addCode();

      storyboard.addObjectDiagram(rootObject);

      storyboard.dumpHTML();
   }


   /**
    * 
    * <h3>Storyboard StudyRightObjectModelNavigationAndQueries</h3>
    * <p>Extend the class model:</p>
    * <p>How to navigate and query an object model.</p>
    * <h4><a name = 'step_1'>Step 1: Example object structure:</a></h4>
    * <img src="doc-files/StudyRightObjectModelNavigationAndQueriesStep3.png" alt="StudyRightObjectModelNavigationAndQueriesStep3.png" width='923'>
    * <h4><a name = 'step_2'>Step 2: Simple set based navigation:</a></h4>
    * <pre><code class="java" data-lang="java">
    * 
    *       double assignmentPoints = university.getRooms().getAssignments().getPoints().sum();
    * 
    *       double donePoints = university.getStudents().getDone().getPoints().sum();
    * 
    * </code></pre>
    * <p>Results in:</p>
    * <pre>      Sum of assignment points: 23.0. 
    *       Sum of points of assignments that have been done by at least one students: 11.0.</pre>
    * <p>Check: Assignment points:  23.0 actual 23.0</p>
    * <p>Check: donePoints:  15.0 actual 11.0</p>
    */
   @Test
   public void testStudyRightObjectModelNavigationAndQueries()
   {
      Storyboard story = new Storyboard();

      story.add("Extend the class model:");

      story.add("How to navigate and query an object model.");

      story.addStep("Example object structure:");

      University university = new University()
         .withName("StudyRight");

      Student abu = university.createStudents()
         .withId("1337")
         .withName("Abu");

      Student karli = new TeachingAssistant().withCertified(true);
      university.withStudents(karli
         .withId("4242")
         .withName("Karli"));

      Student alice = university.createStudents()
         .withId("2323")
         .withName("Alice");

      abu.withFriends(alice);

      Assignment a1 = new Assignment()
         .withContent("Matrix Multiplication")
         .withPoints(5)
         .withStudents(abu);

      Assignment a2 = new Assignment()
         .withContent("Series")
         .withPoints(6);

      Assignment a3 = new Assignment()
         .withContent("Integrals")
         .withPoints(8);

      karli.withDone(a1, a2);

      Room mathRoom = university.createRooms()
         .withName("senate")
         .withTopic("math")
         .withCredits(17)
         .withStudents(karli)
         .withAssignments(a1, a2, a3);

      Room artsRoom = university.createRooms()
         .withName("7522")
         .withTopic("arts")
         .withCredits(16)
         .withDoors(mathRoom);

      Room sportsRoom = university.createRooms()
         .withName("gymnasium")
         .withTopic("sports")
         .withCredits(25)
         .withDoors(mathRoom, artsRoom)
         .withStudents(abu, alice);

      Assignment a4 = sportsRoom.createAssignments().withContent("Pushups").withPoints(4).withStudents(abu);

      Room examRoom = university.createRooms()
         .withName("The End")
         .withTopic("exam")
         .withCredits(0)
         .withDoors(sportsRoom, artsRoom);

      Room softwareEngineering = university.createRooms()
         .withName("7422")
         .withTopic("Software Engineering")
         .withCredits(42)
         .withDoors(artsRoom, examRoom);

      story.addObjectDiagram(university);

      // =====================================================
      story.addStep("Simple set based navigation:");

      story.markCodeStart();

      double assignmentPoints = university.getRooms().getAssignments().getPoints().sum();

      double donePoints = university.getStudents().getDone().getPoints().sum();

      story.addCode();

      story.add("Results in:");

      String text = CGUtil.replaceAll( "" +
                  "      Sum of assignment points: 42. \n" +
                  "      Sum of points of assignments that have been done by at least one students: 84.",
            "42", assignmentPoints,
            "84", donePoints);

      story.addPreformatted(text);

      story.assertEquals("Assignment points: ", 23.0, assignmentPoints);
      story.assertEquals("donePoints: ", 15.0, donePoints);

      // =====================================================
      story.addStep("Rooms with assignments not yet done by Karli:");

      story.markCodeStart();

      AssignmentSet availableAssignments = university.getRooms().getAssignments().minus(karli.getDone());

      RoomSet rooms = availableAssignments.getRoom();

      story.addCode();

      story.add("Results in:");

      story.addPreformatted("      " + rooms.toString());

      story.assertEquals("rooms.size(): ", 2, rooms.size());

      story.addStep("Filter for attribute:");

      story.markCodeStart();

      RoomSet rooms17 = university.getRooms().filterCredits(17);
      RoomSet roomsGE20 = university.getRooms().filterCredits(20); // TODO , Integer.MAX_VALUE);

      story.addCode();

      story.add("Results in:");

      story.addPreformatted("      rooms17: " + rooms17.toString()
         + "\n      roomsGE20: " + roomsGE20);

      story.addStep("Filter for even values:");

      story.markCodeStart();

      SimpleSet<Room> roomsEven = university.getRooms().filter(r -> r.getCredits() % 2 == 0);

      story.addCode();

      story.add("Results in:");

      story.addPreformatted("      " + roomsEven);


      // ====================================================
      story.addStep("Filter for type: ");

      story.markCodeStart();

      // TOODO TeachingAssistantSet taStudents = university.getRooms().getStudents().instanceOfTeachingAssistant();
      StudentSet taStuds = university.getRooms().getStudents().filter(s -> s instanceof TeachingAssistant);
      TeachingAssistantSet taStudents = new TeachingAssistantSet().with(taStuds);

      story.addCode();

      story.addPreformatted("" + taStudents);


      // ====================================================
      story.addStep("Write operations on sets: ");

      story.markCodeStart();

      university.getStudents().withMotivation(42);

      story.addCode();

      story.addObjectDiagramOnlyWith(university.getStudents());


//      // =====================================================
//      story.addStep("Rooms with two students that are friends (and need supervision): ");
//
//      story.markCodeStart();
//
//      RoomPO roomPO = university.getRooms().createRoomPO();
//
//      StudentPO stud1PO = roomPO.createStudentsPO();
//
//      roomPO.createStudentsPO().createMotivationCondition(42).createFriendsLink(stud1PO);
//
//      rooms = roomPO.allMatches();
//
//      story.addCode();
//
//      story.addPattern(roomPO, false);
//
//      story.add("Results in:");
//
//      story.addPreformatted("      " + rooms.toString());
//
//
//      // =====================================================
//      story.addStep("Rooms with two students with low motivation that are friends (and need supervision): ");
//
//      story.markCodeStart();
//
//      roomPO = university.getRooms().createRoomPO();
//
//      stud1PO = roomPO.createStudentsPO();
//
//      final StudentPO stud2PO = roomPO.createStudentsPO().createMotivationCondition(0, 50);
//
//      stud2PO.createFriendsLink(stud1PO);
//
//      rooms = roomPO.allMatches();
//
//      story.addCode();
//
//      story.addPattern(roomPO, false);
//
//      story.add("Results in:");
//
//      story.addPreformatted("      " + rooms.toString());
//
//      // =====================================================
//      story.addStep("Rooms with two students without supervision that are friends and add teaching assistance: ");
//
//      story.markCodeStart();
//
//      UniversityPO uniPO = new UniversityPO(university);
//
//      roomPO = uniPO.createRoomsPO();
//
//      stud1PO = roomPO.createStudentsPO().createMotivationCondition(0, 42);
//
//      roomPO.createStudentsPO().createFriendsLink(stud1PO);
//
//      roomPO.createTasLink(null);
//
//      roomPO.createTasPO(CREATE);
//
//      rooms = roomPO.allMatches();
//
//      story.addCode();
//
//      story.addPattern(uniPO, false);
//
//      // story.add("Internal pattern structure for debugging.");
//      //
//      // story.addObjectDiagramWith(roomPO.getPattern(),
//      // roomPO.getPattern().getElementsTransitive(null));
//
//      story.add("Results in:");
//
//      story.addObjectDiagramOnlyWith(rooms, rooms.getStudents(), rooms.getTas());
//
//      story.addPreformatted("      " + rooms.toString());
//
//      // =====================================================
//      story.addStep("TAs as students in a room: ");
//
//      story.markCodeStart();
//
//      roomPO = university.getRooms().createRoomPO();
//
//      stud1PO = roomPO.createStudentsPO();
//
//      TeachingAssistantPO taPO = stud1PO.instanceOf(new TeachingAssistantPO());
//
//      TeachingAssistantSet taSet = taPO.allMatches();
//
//      story.addCode();
//
//      story.addPattern(roomPO, false);
//
//      story.addObjectDiagramOnlyWith(taSet, taSet.getRoom());
//
//      // =====================================================
//      story.addStep("Double motivation of all students: ");
//
//      story.markCodeStart();
//
//      roomPO = university.getRooms().createRoomPO();
//
//      stud1PO = roomPO.createStudentsPO();
//
//      for (Match match : (Iterable<Match>) roomPO.getPattern())
//      {
//         Student currentMatch = stud1PO.getCurrentMatch();
//
//         currentMatch.withMotivation(currentMatch.getMotivation() * 2);
//
//         // or more simple:
//         stud1PO.withMotivation(stud1PO.getMotivation() * 2);
//
//         Room assertMatch = roomPO.getCurrentMatch();
//
//         if (match.number == 1)
//         {
//            Assert.assertEquals("Karli", currentMatch.getName());
//            Assert.assertEquals("senate", assertMatch.getName());
//            Assert.assertEquals("math", assertMatch.getTopic());
//            Assert.assertEquals(17, assertMatch.getCredits());
//         }
//         else if (match.number == 2)
//         {
//            Assert.assertEquals("Abu", currentMatch.getName());
//            Assert.assertEquals("gymnasium", assertMatch.getName());
//            Assert.assertEquals("sports", assertMatch.getTopic());
//            Assert.assertEquals(25, assertMatch.getCredits());
//         }
//         else if (match.number == 3)
//         {
//            Assert.assertEquals("Alice", currentMatch.getName());
//            Assert.assertEquals("gymnasium", assertMatch.getName());
//            Assert.assertEquals("sports", assertMatch.getTopic());
//            Assert.assertEquals(25, assertMatch.getCredits());
//         }
//
//         // System.out.println("match " + match.number + ": " + currentMatch + "
//         // in room " + roomPO.getCurrentMatch());
//      }
//
//      story.addCode();
//
//      story.addPattern(roomPO, false);
//
//      story.addObjectDiagramOnlyWith(university.getStudents(), university.getStudents().getIn());
//
//      // =====================================================
//      story.addStep("lure students from other rooms into math room: ");
//
//      story.markCodeStart();
//
//      roomPO = new RoomPO(mathRoom);
//
//      stud1PO = roomPO.createPath(r -> ((Room) r).getDoors().getStudents(), new StudentPO());
//
//      stud1PO.startCreate();
//
//      stud1PO.createInLink(roomPO);
//
//      stud1PO.allMatches();
//
//      story.addCode();
//
//      story.addPattern(roomPO, false);
//
//      story.addObjectDiagramOnlyWith(mathRoom, mathRoom.getDoors(), mathRoom.getStudents());
//
//      story.assertEquals("New students in math room: ", 3, mathRoom.getStudents().size());

      story.dumpHTML();
   }


//   /**
//    * @see <a href='../../../../../../../../doc/StudyRightTablesAndReports.html'>StudyRightTablesAndReports.html</a>
//    */
//   @Test
//   public void testReports()
//   {
//      University university = new University()
//         .withName("StudyRight");
//
//      Student abu = university.createStudents()
//         .withId("1337")
//         .withName("Abu");
//
//      Student alice = university.createStudents()
//         .withId("2323")
//         .withName("Alice");
//
//      abu.withFriends(alice);
//
//      IdMap map=new IdMap();
//      map.with(new org.sdmlib.test.examples.studyrightWithAssignments.model.util.UniversityCreator());
//      map.with(new org.sdmlib.test.examples.studyrightWithAssignments.model.util.StudentCreator());
//
//      System.out.println(map.toJsonArray(university).toString(2));
//   }
//
//   /**
//    * <p>Storyboard StudyRightTablesAndReports</p>
//    * <p>How to generate table reports from a model.</p>
//    * <p>Start: Example object structure:</p>
//    * <img src="doc-files/StudyRightTablesAndReportsStep2.png" alt="StudyRightTablesAndReportsStep2.png">
//    * <p><a name = 'step_1'>Step 1: Query for table</a></p>
//    * <pre>
//    *          UniversityPO universityPO = new UniversityPO(university);
//    *
//    *          RoomPO createRoomsPO = universityPO.createRoomsPO();
//    *
//    *          Table table = universityPO.createResultTable();
//    *
//    * </pre>
//    * <script>
//    *    var json = {
//    *    "type":"object",
//    *    "nodes":[
//    *       {
//    *          "type":"patternObject",
//    *          "id":"u1 : UniversityPO",
//    *          "attributes":[
//    *             "<< bound>>"
//    *          ]
//    *       },
//    *       {
//    *          "type":"patternObject",
//    *          "id":"r2 : RoomPO",
//    *          "attributes":[]
//    *       }
//    *    ],
//    *    "edges":[
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"u1 : UniversityPO"
//    *          },
//    *          "target":{
//    *             "property":"rooms",
//    *             "id":"r2 : RoomPO"
//    *          }
//    *       }
//    *    ]
//    * }   ;
//    *    json["options"]={"canvasid":"canvasStudyRightTablesAndReportsPatternDiagram5", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
//    *    g.layout(100,100);
//    * </script>
//    * <p>Results in:</p>
//    * <table style="width: auto;" class="table table-bordered table-condensed">
//    * <thead>
//    * <tr>
//    * <th class="text-center">A</th>
//    * <th class="text-center">B</th>
//    * </tr>
//    * </thead>
//    *
//    * <tbody>
//    * <tr>
//    * <td >StudyRight</td>
//    * <td >senate math 17</td>
//    * </tr>
//    * <tr>
//    * <td >StudyRight</td>
//    * <td >7522 arts 16</td>
//    * </tr>
//    * <tr>
//    * <td >StudyRight</td>
//    * <td >gymnasium sports 25</td>
//    * </tr>
//    * </tbody>
//    *
//    * </table>
//    * <script>
//    *    var json = {
//    *    "type":"object",
//    *    "nodes":[
//    *       {
//    *          "type":"patternObject",
//    *          "id":"t1 : TablePO",
//    *          "attributes":[
//    *             "<< bound>>"
//    *          ]
//    *       },
//    *       {
//    *          "type":"patternObject",
//    *          "id":"r2 : RowPO",
//    *          "attributes":[]
//    *       },
//    *       {
//    *          "type":"patternObject",
//    *          "id":"c3 : CellPO",
//    *          "attributes":[]
//    *       },
//    *       {
//    *          "type":"patternObject",
//    *          "id":"r4 : RoomPO",
//    *          "attributes":[]
//    *       },
//    *       {
//    *          "type":"objectdiagram",
//    *          "style":"nac",
//    *          "info":"NegativeApplicationCondition",
//    *          "nodes":[
//    *             {
//    *                "type":"patternObject",
//    *                "id":"s5 : StudentPO",
//    *                "attributes":[]
//    *             }
//    *          ]
//    *       },
//    *       {
//    *          "type":"objectdiagram",
//    *          "style":"nac",
//    *          "info":"OptionalSubPattern",
//    *          "nodes":[
//    *             {
//    *                "type":"patternObject",
//    *                "id":"c6 : CellPO",
//    *                "attributes":[]
//    *             }
//    *          ]
//    *       }
//    *    ],
//    *    "edges":[
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"t1 : TablePO"
//    *          },
//    *          "target":{
//    *             "property":"rows",
//    *             "id":"r2 : RowPO"
//    *          }
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"r2 : RowPO"
//    *          },
//    *          "target":{
//    *             "property":"cells",
//    *             "id":"c3 : CellPO"
//    *          }
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"c3 : CellPO"
//    *          },
//    *          "target":{
//    *             "property":"value",
//    *             "id":"r4 : RoomPO"
//    *          }
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"r4 : RoomPO"
//    *          },
//    *          "target":{
//    *             "property":"students",
//    *             "id":"s5 : StudentPO"
//    *          }
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"r2 : RowPO"
//    *          },
//    *          "target":{
//    *             "property":"cells",
//    *             "id":"c6 : CellPO"
//    *          }
//    *       }
//    *    ]
//    * }   ;
//    *    json["options"]={"canvasid":"canvasStudyRightTablesAndReportsPatternDiagram8", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
//    *    g.layout(100,100);
//    * </script>
//    * <pre>
//    *          table.createColumns(&quot;Topic&quot;, row -&gt; {
//    *             Room r = row.getCellValue(&quot;B&quot;);
//    *             return r.getTopic();
//    *          });
//    *          table.createColumns(&quot;Credits&quot;, row -&gt; ((Room) row.getCellValue(&quot;B&quot;)).getCredits())
//    *             .withTdCssClass(&quot;text-right&quot;);
//    *          table.createColumns(&quot;Students&quot;, row -&gt; ((Room) row.getCellValue(&quot;B&quot;)).getStudents().size())
//    *             .withTdCssClass(&quot;text-right&quot;);
//    *          table.withoutColumns(&quot;A&quot;, &quot;B&quot;);
//    *
//    * </pre>
//    * <table style="width: auto;" class="table table-bordered table-condensed">
//    * <thead>
//    * <tr>
//    * <th class="text-center">Topic</th>
//    * <th class="text-center">Credits</th>
//    * <th class="text-center">Students</th>
//    * </tr>
//    * </thead>
//    *
//    * <tbody>
//    * <tr>
//    * <td >math</td>
//    * <td class="text-right">17</td>
//    * <td class="text-right">1</td>
//    * </tr>
//    * <tr>
//    * <td >arts</td>
//    * <td class="text-right">16</td>
//    * <td class="text-right">0</td>
//    * </tr>
//    * <tr>
//    * <td >sports</td>
//    * <td class="text-right">25</td>
//    * <td class="text-right">2</td>
//    * </tr>
//    * </tbody>
//    *
//    * </table>
//    * <script>
//    *    var json = {
//    *    "type":"object",
//    *    "nodes":[
//    *       {
//    *          "type":"patternObject",
//    *          "id":"t1 : TablePO",
//    *          "attributes":[
//    *             "<< bound>>"
//    *          ]
//    *       },
//    *       {
//    *          "type":"patternObject",
//    *          "style":"create",
//    *          "id":"c2 : ColumnPO",
//    *          "attributes":[
//    *             "<< create>>",
//    *             "name == Topic"
//    *          ]
//    *       },
//    *       {
//    *          "type":"objectdiagram",
//    *          "style":"nac",
//    *          "info":"OptionalSubPattern",
//    *          "nodes":[
//    *             {
//    *                "type":"patternObject",
//    *                "id":"r3 : RowPO",
//    *                "attributes":[]
//    *             },
//    *             {
//    *                "type":"patternObject",
//    *                "id":"c4 : CellPO",
//    *                "attributes":[]
//    *             },
//    *             {
//    *                "type":"patternObject",
//    *                "id":"r5 : RoomPO",
//    *                "attributes":[]
//    *             },
//    *             {
//    *                "type":"patternObject",
//    *                "style":"create",
//    *                "id":"c6 : CellPO",
//    *                "attributes":[
//    *                   "<< create>>",
//    *                   "value == r5.topic"
//    *                ]
//    *             }
//    *          ]
//    *       }
//    *    ],
//    *    "edges":[
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"t1 : TablePO"
//    *          },
//    *          "target":{
//    *             "property":"columns",
//    *             "id":"c2 : ColumnPO"
//    *          },
//    *          "style":"create"
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"t1 : TablePO"
//    *          },
//    *          "target":{
//    *             "property":"rows",
//    *             "id":"r3 : RowPO"
//    *          }
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"r3 : RowPO"
//    *          },
//    *          "target":{
//    *             "property":"cells",
//    *             "id":"c4 : CellPO"
//    *          }
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"c4 : CellPO"
//    *          },
//    *          "target":{
//    *             "property":"value",
//    *             "id":"r5 : RoomPO"
//    *          }
//    *       },
//    *       {
//    *          "typ":"EDGE",
//    *          "source":{
//    *             "property":" ",
//    *             "id":"c2 : ColumnPO"
//    *          },
//    *          "target":{
//    *             "property":"cells",
//    *             "id":"c6 : CellPO"
//    *          },
//    *          "style":"create"
//    *       }
//    *    ]
//    * }   ;
//    *    json["options"]={"canvasid":"canvasStudyRightTablesAndReportsPatternDiagram11", "display":"html", "fontsize":10,"bar":true};   var g = new Graph(json);
//    *    g.layout(100,100);
//    * </script>
//    * <p><a name = 'step_2'>Step 2: List all topics:</a></p>
//    * <pre>
//    *          UniversityPO universityPO = new UniversityPO(university);
//    *
//    *          TablePO tablePO = new TablePO(CREATE);
//    *
//    *          universityPO.addToPattern(tablePO);
//    *
//    *          tablePO.createNameAssignment(&quot;University&quot;);
//    *
//    *          ColumnPO col1PO = tablePO.createColumnsPO(CREATE).createNameAssignment(&quot;Topic&quot;);
//    *
//    *          ColumnPO col2PO = tablePO.createColumnsPO(CREATE)
//    *             .createNameAssignment(&quot;Credits&quot;)
//    *             .createTdCssClassAssignment(&quot;text-right&quot;);
//    *
//    *          ColumnPO col3PO = tablePO.createColumnsPO(CREATE)
//    *             .createNameAssignment(&quot;Students&quot;)
//    *             .createTdCssClassAssignment(&quot;text-right&quot;);
//    *
//    *          RoomPO roomsPO = universityPO.createRoomsPO();
//    *
//    *          RowPO rowPO = tablePO.createRowsPO(CREATE);
//    *
//    *          CellPO cell1PO = rowPO.createCellsPO(CREATE).createColumnLink(col1PO, CREATE);
//    *          cell1PO.createCondition(cell -&gt; cell.withValue(roomsPO.getTopic()) != null);
//    *
//    *          CellPO cell2PO = rowPO.createCellsPO(CREATE).createColumnLink(col2PO, CREATE);
//    *          cell2PO.createCondition(cell -&gt; cell.withValue(roomsPO.getCredits()) != null);
//    *
//    *          CellPO cell3PO = rowPO.createCellsPO(CREATE).createColumnLink(col3PO, CREATE);
//    *          cell3PO.createCondition(cell -&gt; cell.withValue(roomsPO.getStudents().size()) != null);
//    *
//    *          universityPO.doAllMatches();
//    *
//    * </pre>
//    * <p>Results in:</p>
//    * <table style="width: auto;" class="table table-bordered table-condensed">
//    * <thead>
//    * <tr>
//    * <th class="text-center">Topic</th>
//    * <th class="text-center">Credits</th>
//    * <th class="text-center">Students</th>
//    * </tr>
//    * </thead>
//    *
//    * <tbody>
//    * <tr>
//    * <td >math</td>
//    * <td class="text-right">17</td>
//    * <td class="text-right">1</td>
//    * </tr>
//    * <tr>
//    * <td >arts</td>
//    * <td class="text-right">16</td>
//    * <td class="text-right">0</td>
//    * </tr>
//    * <tr>
//    * <td >sports</td>
//    * <td class="text-right">25</td>
//    * <td class="text-right">2</td>
//    * </tr>
//    * </tbody>
//    *
//    * </table>
//    * <img src="doc-files/StudyRightTablesAndReportsStep16.png" alt="StudyRightTablesAndReportsStep16.png">
//    * <p><a name = 'step_3'>Step 3: Do a nested table</a></p>
//    * <pre>
//    *          UniversityPO universityPO = new UniversityPO(university);
//    *
//    *          RoomPO createRoomsPO = universityPO.createRoomsPO();
//    *
//    *          Table table = universityPO.createResultTable();
//    *
//    *          table.createColumns(&quot;Topic&quot;, row -&gt; ((Room) row.getCellValue(&quot;B&quot;)).getTopic());
//    *          table.createColumns(&quot;Assignments&quot;, row -&gt; addAssignments(row));
//    *          table.createColumns(&quot;Students&quot;, row -&gt; ((Room) row.getCellValue(&quot;B&quot;)).getStudents().size())
//    *             .withTdCssClass(&quot;text-right&quot;);
//    *          table.withoutColumns(&quot;A&quot;, &quot;B&quot;);
//    *
//    * </pre>
//    * <table style="width: auto;" class="table table-bordered table-condensed">
//    * <thead>
//    * <tr>
//    * <th class="text-center">Topic</th>
//    * <th class="text-center">Assignments</th>
//    * <th class="text-center">Students</th>
//    * </tr>
//    * </thead>
//    *
//    * <tbody>
//    * <tr>
//    * <td >math</td>
//    * <td ><table style="width: auto;" class="table table-bordered table-condensed">
//    * <thead>
//    * <tr>
//    * <th class="text-center">Content</th>
//    * <th class="text-center">Points</th>
//    * </tr>
//    * </thead>
//    *
//    * <tbody>
//    * <tr>
//    * <td >Matrix Multiplication</td>
//    * <td class="text-right">5</td>
//    * </tr>
//    * <tr>
//    * <td >Series</td>
//    * <td class="text-right">6</td>
//    * </tr>
//    * <tr>
//    * <td >Integrals</td>
//    * <td class="text-right">8</td>
//    * </tr>
//    * </tbody>
//    *
//    * </table>
//    * </td>
//    * <td class="text-right">1</td>
//    * </tr>
//    * <tr>
//    * <td >arts</td>
//    * <td ><table style="width: auto;" class="table table-bordered table-condensed">
//    * <thead>
//    * <tr>
//    * <th class="text-center">Content</th>
//    * <th class="text-center">Points</th>
//    * </tr>
//    * </thead>
//    *
//    * <tbody>
//    * </tbody>
//    *
//    * </table>
//    * </td>
//    * <td class="text-right">0</td>
//    * </tr>
//    * <tr>
//    * <td >sports</td>
//    * <td ><table style="width: auto;" class="table table-bordered table-condensed">
//    * <thead>
//    * <tr>
//    * <th class="text-center">Content</th>
//    * <th class="text-center">Points</th>
//    * </tr>
//    * </thead>
//    *
//    * <tbody>
//    * </tbody>
//    *
//    * </table>
//    * </td>
//    * <td class="text-right">2</td>
//    * </tr>
//    * </tbody>
//    *
//    * </table>
//    * @see <a href='../../../../../../../../doc/StudyRightTablesAndReports.html'>StudyRightTablesAndReports.html</a>
//    */
//   @Test
//   public void testStudyRightTablesAndReports()
//   {
//      Storyboard story = new Storyboard();
//
//      story.add("How to generate table reports from a model.");
//
//      story.addStep("Example object structure:");
//
//      University university = new University()
//         .withName("StudyRight");
//
//      Student abu = university.createStudents()
//         .withId("1337")
//         .withName("Abu");
//
//      Student karli = new TeachingAssistant().withCertified(true);
//      university.withStudents(karli
//         .withId("4242")
//         .withName("Karli"));
//
//      Student alice = university.createStudents()
//         .withId("2323")
//         .withName("Alice");
//
//      abu.withFriends(alice);
//
//      Assignment a1 = new Assignment()
//         .withContent("Matrix Multiplication")
//         .withPoints(5)
//         .withStudents(abu);
//
//      Assignment a2 = new Assignment()
//         .withContent("Series")
//         .withPoints(6);
////
//      Assignment a3 = new Assignment()
//         .withContent("Integrals")
//         .withPoints(8);
//
//      karli.withDone(a1, a2);
//
//      Room mathRoom = university.createRooms()
//         .withName("senate")
//         .withTopic("math")
//         .withCredits(17)
//         .withStudents(karli)
//         .withAssignments(a1, a2, a3);
//
//      Room artsRoom = university.createRooms()
//         .withName("7522")
//         .withTopic("arts")
//         .withCredits(16)
//         .withDoors(mathRoom);
//
//      Room sportsRoom = university.createRooms()
//         .withName("gymnasium")
//         .withTopic("sports")
//         .withCredits(25)
//         .withDoors(mathRoom, artsRoom)
//         .withStudents(abu, alice);
//
////      Assignment a4 = sportsRoom.createAssignments().withContent("Pushups").withPoints(4).withStudents(abu);
//
////      Room examRoom = university.createRooms()
////         .withName("The End")
////         .withTopic("exam")
////         .withCredits(0)
////         .withDoors(sportsRoom, artsRoom);
//
////      Room softwareEngineering = university.createRooms()
////         .withName("7422")
////         .withTopic("Software Engineering")
////         .withCredits(42)
////         .withDoors(artsRoom, examRoom);
//
//      story.addObjectDiagramViaGraphViz(university);
//
//      story.addStep("Query for table");
//
//      {
//         story.markCodeStart();
//
//         UniversityPO universityPO = new UniversityPO(university);
//
//         RoomPO createRoomsPO = universityPO.createRoomsPO();
//
//         Table table = universityPO.createResultTable();
//
//         story.addCode();
//
//         story.addPattern(universityPO, false);
//
//         story.add("Results in:");
//
//         story.addTable(table);
//
//         // filter row rule
//         TablePO tablePO = new TablePO(table);
//         RowPO rowPO = tablePO.createRowsPO();
//         CellPO cellPO = rowPO.createCellsPO();
//         RoomPO roomPO = new RoomPO();
//         cellPO.hasLink("value", roomPO);
//         roomPO.startNAC();
//         roomPO.createStudentsPO();
//         roomPO.endNAC();
//         rowPO.startSubPattern();
//         CellPO otherCellPO = rowPO.createCellsPO();
//         otherCellPO.destroy();
//         otherCellPO.doAllMatches();
//         rowPO.endSubPattern();
//         rowPO.destroy();
//         rowPO.doAllMatches();
//
//         story.addPattern(tablePO, false);
//
//         story.markCodeStart();
//
//         table.createColumns("Topic", row -> {
//            Room r = row.getCellValue("B");
//            return r.getTopic();
//         });
//         table.createColumns("Credits", row -> ((Room) row.getCellValue("B")).getCredits())
//            .withTdCssClass("text-right");
//         table.createColumns("Students", row -> ((Room) row.getCellValue("B")).getStudents().size())
//            .withTdCssClass("text-right");
//         table.withoutColumns("A", "B");
//
//         story.addCode();
//
//         story.addTable(table);
//
//         double creditsSum = table.getColumn("Credits").getValueSum();
//
//         String csv = table.getCSV();
//
//         try
//         {
//            Files.write(Paths.get("doc/StudyRight.csv"), csv.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
//         }
//         catch (IOException e)
//         {
//            e.printStackTrace();
//         }
//
//         tablePO = new TablePO(table);
//         ColumnPO columnsPO = tablePO.createColumnsPO(CREATE);
//         columnsPO.createNameAssignment("Topic");
//         tablePO.startSubPattern();
//         rowPO = tablePO.createRowsPO();
//         cellPO = rowPO.createCellsPO();
//         roomPO = new RoomPO();
//         cellPO.hasLink("value", roomPO);
//         cellPO.startCreate();
//         CellPO cellPO2 = columnsPO.createCellsPO(CREATE);
//         cellPO2.createValueAssignment("r5.topic");
//         cellPO.endCreate();
//         tablePO.endSubPattern();
//
//
//         story.addPattern(tablePO, false);
//
//      }
//
//      // =====================================================
//      story.addStep("List all topics:");
//
//      {
//         story.markCodeStart();
//
//         UniversityPO universityPO = new UniversityPO(university);
//
//         TablePO tablePO = new TablePO(CREATE);
//
//         universityPO.addToPattern(tablePO);
//
//         tablePO.createNameAssignment("University");
//
//         ColumnPO col1PO = tablePO.createColumnsPO(CREATE).createNameAssignment("Topic");
//
//         ColumnPO col2PO = tablePO.createColumnsPO(CREATE)
//            .createNameAssignment("Credits")
//            .createTdCssClassAssignment("text-right");
//
//         ColumnPO col3PO = tablePO.createColumnsPO(CREATE)
//            .createNameAssignment("Students")
//            .createTdCssClassAssignment("text-right");
//
//         RoomPO roomsPO = universityPO.createRoomsPO();
//
//         RowPO rowPO = tablePO.createRowsPO(CREATE);
//
//         CellPO cell1PO = rowPO.createCellsPO(CREATE).createColumnLink(col1PO, CREATE);
//         cell1PO.createCondition(cell -> cell.withValue(roomsPO.getTopic()) != null);
//
//         CellPO cell2PO = rowPO.createCellsPO(CREATE).createColumnLink(col2PO, CREATE);
//         cell2PO.createCondition(cell -> cell.withValue(roomsPO.getCredits()) != null);
//
//         CellPO cell3PO = rowPO.createCellsPO(CREATE).createColumnLink(col3PO, CREATE);
//         cell3PO.createCondition(cell -> cell.withValue(roomsPO.getStudents().size()) != null);
//
//         universityPO.doAllMatches();
//
//         story.addCode();
//
//         story.add("Results in:");
//
//         story.addTable(tablePO.getCurrentMatch());
//
//         story.addObjectDiagramViaGraphViz(tablePO.getCurrentMatch());
//      }
//
//      story.addStep("Do a nested table");
//      {
//         story.markCodeStart();
//
//         UniversityPO universityPO = new UniversityPO(university);
//
//         RoomPO createRoomsPO = universityPO.createRoomsPO();
//
//         Table table = universityPO.createResultTable();
//
//         table.createColumns("Topic", row -> ((Room) row.getCellValue("B")).getTopic());
//         table.createColumns("Assignments", row -> addAssignments(row));
//         table.createColumns("Students", row -> ((Room) row.getCellValue("B")).getStudents().size())
//            .withTdCssClass("text-right");
//         table.withoutColumns("A", "B");
//
//         story.addCode();
//
//         story.addTable(table);
//      }
//      story.dumpHTML();
//   }
//
//   public Table addAssignments(Row row)
//   {
//      Room room = (Room) row.getCellValue("B");
//
//      RoomPO roomPO = new RoomPO(room);
//
//      AssignmentPO assignmentPO = roomPO.createAssignmentsPO();
//
//      Table table = roomPO.createResultTable();
//
//      table.createColumns("Content", r -> ((Assignment) r.getCellValue("B")).getContent());
//      table.createColumns("Points", r -> ((Assignment) r.getCellValue("B")).getPoints())
//         .withTdCssClass("text-right");
//      table.withoutColumns("A", "B");
//
//      return table;
//   }
//
//   /**
//    *
//    * @see <a href=
//    *      '../../../../../../../../doc/StudyRightReachabilityGraph.html'>StudyRightReachabilityGraph.html</a>
//    * @see <a href='../../../../../../../../doc/StudyRightReachabilityGraph.html'>StudyRightReachabilityGraph.html</a>
// */
//   @Test
//   public void testStudyRightReachabilityGraph()
//   {
//      Storyboard story = new Storyboard();
//
//      story.addStep("Build a start graph");
//
//      University university = new University()
//         .withName("StudyRight");
//
//      Student karli = university.createStudents()
//         .withId("1337")
//         .withName("Karli")
//         .withMotivation(100);
//
//      Room mathRoom = university.createRooms()
//         .withName("senate")
//         .withTopic("math")
//         .withCredits(17)
//         .withStudents(karli);
//
//      karli.withCredits(17).withMotivation(100 - 17);
//
//      Room artsRoom = university.createRooms()
//         .withName("7522")
//         .withTopic("arts")
//         .withCredits(16)
//         .withDoors(mathRoom);
//
//      Room sportsRoom = university.createRooms()
//         .withName("gymnasium")
//         .withTopic("sports")
//         .withCredits(25)
//         .withDoors(mathRoom, artsRoom);
//
//      Room examRoom = university.createRooms()
//         .withName("The End")
//         .withTopic("exam")
//         .withCredits(0)
//         .withDoors(sportsRoom, artsRoom);
//
//      Room softwareEngineering = university.createRooms()
//         .withName("7422")
//         .withTopic("Software Engineering")
//         .withCredits(42)
//         .withDoors(artsRoom, examRoom);
//
//      story.addObjectDiagramOnlyWith(university, karli, university.getRooms());
//
//      // =====================================================
//      IdMap idMap = UniversityCreator.createIdMap("s");
//      idMap.with(ReachabilityGraphCreator.createIdMap("rg"));
//
//      ReachableState startState = new ReachableState().withGraphRoot(university);
//      ReachabilityGraph reachabilityGraph = new ReachabilityGraph().withMasterMap(idMap)
//         .withStates(startState).withTodo(startState);
//
//      UniversityPO uniPO = new UniversityPO();
//
//      StudentPO studPO = uniPO.createStudentsPO();
//
//      RoomPO currentRoomPO = studPO.createInPO();
//
//      RoomPO nextRoomPO = currentRoomPO.createDoorsPO();
//
//      studPO.createCondition(s -> studPO.getMotivation() >= nextRoomPO.getCredits());
//
//      uniPO.getPattern().clone(reachabilityGraph);
//
//      studPO.startCreate().createInLink(nextRoomPO).endCreate();
//
//      studPO.createCondition(s -> {
//         studPO.withMotivation(studPO.getMotivation() - nextRoomPO.getCredits());
//         studPO.withCredits(studPO.getCredits() + nextRoomPO.getCredits());
//         return true;
//      });
//
//      story.addPattern(uniPO, false);
//
//      reachabilityGraph.addToRules(uniPO.getPattern().withName("next"));
//
//      reachabilityGraph.explore();
//
//      ReachableStateSet allStates = reachabilityGraph.getStates();
//      // interestingStates.addAll(reachabilityGraph.getStates().filterNumber(60,
//      // Integer.MAX_VALUE));
//
//      ReachableStateSet interestingStates = new ReachableStateSet();
//
//      for (ReachableState state : allStates)
//      {
//         University uni = (University) state.getGraphRoot();
//         Student student = uni.getStudents().first();
//         Room room = student.getIn();
//         if (student.getMotivation() == 0 && room.getTopic().equals("exam"))
//         {
//            interestingStates.add(state);
//            break;
//         }
//      }
//
//      RuleApplicationSet ruleApplications = new RuleApplicationSet();
//
//      ReachableState endState = interestingStates.first();
//
//      for (int i = 1; i <= 10; i++)
//      {
//         RuleApplication ruleApplication = endState.getResultOf().first();
//
//         if (ruleApplication != null)
//         {
//            ruleApplications.add(ruleApplication);
//            endState = ruleApplication.getSrc();
//            interestingStates.add(endState);
//         }
//         else
//         {
//            break;
//         }
//      }
//
//      UniversitySet interestingUniversities = CGUtil.instanceOf(interestingStates.getGraphRoot(), new UniversitySet());
//
//      StudentSet studentsSet = CGUtil.instanceOf(allStates.getGraphRoot(), new UniversitySet()).getStudents().filterMotivation(0);
//
//      story.addObjectDiagramOnlyWith(
//         interestingStates,
//         ruleApplications,
//         interestingUniversities,
//         interestingUniversities.getStudents(),
//         interestingUniversities.getStudents().getIn());
//
//      // ok do it with search pattern
//      ReachabilityGraphPO reachabilityGraphPO = new ReachabilityGraphPO(reachabilityGraph);
//      ReachableStatePO statePO = reachabilityGraphPO.createStatesPO();
//      UniversityPO universityPO =
//            statePO.createGraphRootPO().instanceOf(new UniversityPO());
//      StudentPO studentPO =
//            universityPO.createStudentsPO().createMotivationCondition(0);
//      RoomPO roomPO = studentPO.createInPO().createTopicCondition("exam");
//
//      ReachableState currentMatch = statePO.getCurrentMatch();
//
//      story.add("Total number of states " + reachabilityGraph.getStates().size());
//
//      story.add("Success state " + currentMatch.getNumber());
//
//      story.addPattern(reachabilityGraphPO, true);
//
//      story.dumpHTML();
//   }

}
