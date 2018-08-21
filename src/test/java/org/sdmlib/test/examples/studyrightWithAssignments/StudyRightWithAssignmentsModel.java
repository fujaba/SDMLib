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

import de.uniks.networkparser.ext.ClassModel;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import org.junit.Test;
import org.sdmlib.codegen.Gradle;
import org.sdmlib.models.YamlIdMap;
import org.sdmlib.storyboards.Goal;
import org.sdmlib.storyboards.MikadoLog;
import org.sdmlib.storyboards.Storyboard;

public class StudyRightWithAssignmentsModel
{

   public static final String ORG_SDMLIB_TEST_EXAMPLES_STUDYRIGHT_WITH_ASSIGNMENTS_MODEL = "org.sdmlib.test.examples.studyrightWithAssignments.model";
   public static final String SRC_TEST_JAVA = "src/test/java";

   /**
    *
    * <h3>Storyboard StudyRightWithAssignmentsClassGeneration</h3>
    * <p>Check: gradle compileTestJava result 0 actual 0</p>
    * <h4><a name = 'step_1'>Step 1: Build model for class University</a></h4>
    * <p>Check: gradle compileTestJava result 0 actual 0</p>
    * <p>Check: gradle compileTestJava result after adding University.name 0 actual 0</p>
    * <pre><code class="java" data-lang="java">
    *       ClassModel model = new ClassModel(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    * 
    *       Clazz universityClass = model.createClazz(&quot;University&quot;);
    * 
    *       model.generate(SRC_TEST_JAVA);
    *       result = Gradle.runTask(&quot;compileTestJava&quot;);
    *       story.assertEquals(&quot;gradle compileTestJava result&quot;, 0, result);
    * 
    *       universityClass.withAttribute(&quot;name&quot;, DataType.STRING);
    * 
    *       model.resetGenerator();
    *       model.generate(SRC_TEST_JAVA);
    *       result = Gradle.runTask(&quot;compileTestJava&quot;);
    *       story.assertEquals(&quot;gradle compileTestJava result after adding University.name&quot;, 0, result);
    * 
    * 
    * 
    * 
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep5.png" alt="StudyRightWithAssignmentsClassGenerationStep5.png">
    * <h4><a name = 'step_2'>Step 2: Add class Student</a></h4>
    * <pre><code class="java" data-lang="java">
    *       Clazz studentClass = model.createClazz(&quot;Student&quot;)
    *               .withAttribute(&quot;name&quot;, DataType.STRING)
    *               .withAttribute(&quot;id&quot;, DataType.STRING)
    *               .withAttribute(&quot;assignmentPoints&quot;, DataType.INT)
    *               .withAttribute(&quot;motivation&quot;, DataType.INT)
    *               .withAttribute(&quot;credits&quot;, DataType.INT);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep8.png" alt="StudyRightWithAssignmentsClassGenerationStep8.png">
    * <p>Check: gradle compileTestJava result after adding class Student 0 actual 0</p>
    * <p>3. add University --> Student association</p>
    * <pre><code class="java" data-lang="java">
    *       universityClass.withBidirectional(studentClass, &quot;students&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep12.png" alt="StudyRightWithAssignmentsClassGenerationStep12.png">
    * <p>Check: gradle compileTestJava result after adding students assoc 0 actual 0</p>
    * <p>6. generate class source files.</p>
    * <pre><code class="java" data-lang="java">
    *       model.generate(&quot;src&#x2F;test&#x2F;java&quot;); &#x2F;&#x2F; usually don&#x27;t specify anything here, then it goes into src
    * </code></pre>
    * <p>Check: gradle build result after all 0 actual 0</p>
    */
   @Test
   public void testStudyRightWithAssignmentsClassGeneration()
   {
      /* This file will generate that necessary classes and class diagram for the
       * StudyRight with Assignments example in the Story Driven Modeling book
       */
      Storyboard story = new Storyboard();

      // remove old code
      Gradle.removeDir(SRC_TEST_JAVA, ORG_SDMLIB_TEST_EXAMPLES_STUDYRIGHT_WITH_ASSIGNMENTS_MODEL);

      // project compiles?
      int result = Gradle.runTask("compileTestJava");
      story.assertEquals("gradle compileTestJava result", 0, result);


      //============================================================
      story.addStep("Build model for class University");

      story.markCodeStart();
      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyrightWithAssignments.model");

      Clazz universityClass = model.createClazz("University");

      model.generate(SRC_TEST_JAVA);
      result = Gradle.runTask("compileTestJava");
      story.assertEquals("gradle compileTestJava result", 0, result);

      universityClass.withAttribute("name", DataType.STRING);

      model.resetGenerator();
      model.generate(SRC_TEST_JAVA);
      result = Gradle.runTask("compileTestJava");
      story.assertEquals("gradle compileTestJava result after adding University.name", 0, result);




      story.addCode();

      story.addClassDiagramAsImage(model, 400, 200);


      //============================================================
      story.addStep("Add class Student");

      story.markCodeStart();
      Clazz studentClass = model.createClazz("Student")
              .withAttribute("name", DataType.STRING)
              .withAttribute("id", DataType.STRING)
              .withAttribute("assignmentPoints", DataType.INT)
              .withAttribute("motivation", DataType.INT)
              .withAttribute("credits", DataType.INT);
      story.addCode();

      story.addClassDiagramAsImage(model, 400, 240);

      // project compiles?
      model.resetGenerator();
      model.generate(SRC_TEST_JAVA);
      result = Gradle.runTask("compileTestJava");
      story.assertEquals("gradle compileTestJava result after adding class Student", 0, result);


      //============================================================
      story.add("3. add University --> Student association");

      // Association universityToStudent =
      story.markCodeStart();
      universityClass.withBidirectional(studentClass, "students", Cardinality.MANY, "university", Cardinality.ONE);
      story.addCode();

      story.addClassDiagramAsImage(model, 400, 400);

      // project compiles?
      model.resetGenerator();
      model.generate(SRC_TEST_JAVA);
      result = Gradle.runTask("compileTestJava");
      story.assertEquals("gradle compileTestJava result after adding students assoc", 0, result);

//
//
//      //============================================================
//      story.add("4. add University --> Room association");
//
//      story.markCodeStart();
//      Clazz roomClass = model.createClazz("Room")
//              .withAttribute("name", DataType.STRING)
//              .withAttribute("topic", DataType.STRING)
//              .withAttribute("credits", DataType.INT);
//
//      roomClass.withMethod("findPath", DataType.STRING, new Parameter(DataType.INT).with("motivation"));
//
//      //Association universityToRoom =
//      universityClass.createBidirectional(roomClass, "rooms", Cardinality.MANY, "university", Cardinality.ONE).with(AssociationTypes.AGGREGATION);
//
//      // Association doors =
//      roomClass.createBidirectional(roomClass, "doors", Cardinality.MANY, "doors", Cardinality.MANY);
//
//      // Association studentsInRoom =
//      studentClass.createBidirectional(roomClass, "in", Cardinality.ONE, "students", Cardinality.MANY);
//      studentClass.createBidirectional(studentClass, "friends", Cardinality.MANY, "friends", Cardinality.MANY);
//
//      story.addCode();
//
//      // story.addClassDiagramAsImage(model, 400, 500);
//
//
//
//      //============================================================
//      story.add("5. add assignments:");
//
//      story.markCodeStart();
//      Clazz assignmentClass = model.createClazz("Assignment")
//              .withAttribute("content", DataType.STRING)
//              .withAttribute("points", DataType.INT);
//
//      assignmentClass.createBidirectional(roomClass, "room", Cardinality.ONE, "assignments", Cardinality.MANY);
//
//      studentClass.createBidirectional(assignmentClass, "done", Cardinality.MANY, "students", Cardinality.MANY);
//      story.addCode();
//
//      // story.addClassDiagramAsImage(model, 450, 600);
//
//      studentClass.createBidirectional(studentClass, "friends", Cardinality.MANY, "friends", Cardinality.MANY);
//
//
//      // some more classes for model navigation tests
//      studentClass.createBidirectional(studentClass, "friends", Cardinality.MANY, "friends", Cardinality.MANY);
//
//      model.createClazz("TeachingAssistant")
//              .withSuperClazz(studentClass)
//              .withBidirectional(roomClass, "room", Cardinality.ONE, "tas", Cardinality.MANY)
//              .withAttribute("certified", DataType.BOOLEAN);
//
//
//      Clazz presidentClass = model.createClazz("President");
//      universityClass.createBidirectional(presidentClass, "president", Cardinality.ONE, "university", Cardinality.ONE).with(AssociationTypes.AGGREGATION);

      //============================================================
      story.add("6. generate class source files.");

      // model.removeAllGeneratedCode("src/test/java");

      model.setAuthorName("zuendorf");
      story.markCodeStart();
      model.generate("src/test/java"); // usually don't specify anything here, then it goes into src
      story.addCode();

      result = Gradle.runTask("compileTestJava");

      story.assertEquals("gradle build result after all", 0, result);

//      story.dumpJavaDoc(ClassModel.class.getName());

      story.dumpHTML();
   }

   /**
    *
    * <h3>Storyboard NetworkParserCodeGenProjectPlan</h3>
    * <img src='doc-files/_NetworkParserCodeGenProjectPlanStep0.png'><h4><a name = 'step_1'>Step 1: open goals</a></h4>
    * <img src="doc-files/NetworkParserCodeGenProjectPlanStep2.png" alt="NetworkParserCodeGenProjectPlanStep2.png">
    * <h4><a name = 'step_2'>Step 2: closed goals</a></h4>
    * <img src="doc-files/NetworkParserCodeGenProjectPlanStep4.png" alt="NetworkParserCodeGenProjectPlanStep4.png">
    */
   @Test
   public void testNetworkParserCodeGenProjectPlan()
   {
      Storyboard story = new Storyboard().withDocDirName("doc/internal");

      String yaml = "" +
              "- Goal                  description:                             parents:                                \n" +
              "  root:                 \"migrate to network parser codegen\"    null                                    \n" +
              "  studyRight:           \"migrate study right example\"          root                                    \n" +
              "  mikado:               \"add micado diagrams to javadoc\"       root                                    \n" +
              "  burndown:             \"fix micado burndown\"                  mikado                                  \n" +
              "  javadoc:              \"enable javadoc for external classes\"  mikado                                  \n" +
              "  compile:              \"add compile test after code gen\"      studyRight                              \n" +
              "  yaml:                 \"enhance yaml\"                         mikado                                  \n" +
              "  yamlSingleQuotes:     \"allow ' for strings in yaml\"          yaml                                    \n" +
              "  yamlIds:              \"assign yaml ids to objects\"           yaml                                    \n" +
              "                                                                                                         \n" +
              "- mikadoLog: MikadoLog                                                                                   \n" +
              "  mainGoal: root                                                                                         \n" +
              "                                                                                                         \n" +
              "- LogEntry  goal:              date:                           hoursDone: hoursRemaining: parent:        \n" +
              "  l1:       studyRight         2018-08-13T12:00:00+01:00       0           4              mikadoLog      \n" +
              "  l2:       studyRight         2018-08-14T12:00:00+01:00       3           3              mikadoLog      \n" +
              "  l3:       mikado             2018-08-15T12:00:00+01:00       3           1              mikadoLog      \n" +
              "  l4:       burndown           2018-08-15T12:13:30+01:00       1           0              mikadoLog      \n" +
              "  l5:       compile            2018-08-15T12:17:21+01:00       4           0              mikadoLog      \n" +
              "";


      YamlIdMap idMap = new YamlIdMap(Goal.class.getPackage().getName());

      Goal root = (Goal) idMap.decode(yaml);

      MikadoLog mikadoLog = (MikadoLog) idMap.getObject("mikadoLog");

      story.addImage(mikadoLog.burnDownChartPng());

      Goal done = root.clipDone();

      story.addStep("open goals");

      story.addObjectDiagramViaGraphViz(root);

      story.addStep("closed goals");
      story.addObjectDiagramViaGraphViz(done);


      story.dumpHTML();
   }

}
