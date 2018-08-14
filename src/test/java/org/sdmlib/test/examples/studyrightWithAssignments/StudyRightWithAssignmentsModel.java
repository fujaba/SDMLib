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
import de.uniks.networkparser.ext.story.Story;
import de.uniks.networkparser.ext.story.StoryStepSourceCode;
import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import org.junit.Test;
import org.sdmlib.storyboards.Storyboard;

public class StudyRightWithAssignmentsModel
{
   
   /**
    * 
    * <h3>Storyboard StudyRightWithAssignmentsClassGeneration</h3>
    * <h4><a name = 'step_1'>Step 1: Build model for class University</a></h4>
    * <pre><code class="java" data-lang="java">
    *       ClassModel model = new ClassModel(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    * 
    *       Clazz universityClass = model.createClazz(&quot;University&quot;)
    *               .withAttribute(&quot;name&quot;, DataType.STRING);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep2.png" alt="StudyRightWithAssignmentsClassGenerationStep2.png">
    * <h4><a name = 'step_2'>Step 2: Add class Student</a></h4>
    * <pre><code class="java" data-lang="java">
    *       Clazz studentClass = model.createClazz(&quot;Student&quot;)
    *               .withAttribute(&quot;name&quot;, DataType.STRING)
    *               .withAttribute(&quot;id&quot;, DataType.STRING)
    *               .withAttribute(&quot;assignmentPoints&quot;, DataType.INT)
    *               .withAttribute(&quot;motivation&quot;, DataType.INT)
    *               .withAttribute(&quot;credits&quot;, DataType.INT);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep5.png" alt="StudyRightWithAssignmentsClassGenerationStep5.png">
    * <p>3. add University --> Student association</p>
    * <pre><code class="java" data-lang="java">
    *       universityClass.withBidirectional(studentClass, &quot;students&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE);
    * </code></pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep8.png" alt="StudyRightWithAssignmentsClassGenerationStep8.png">
    */
   @Test
   public void testStudyRightWithAssignmentsClassGeneration()
   {
      /* This file will generate that necessary classes and class diagram for the
       * StudyRight with Assignments example in the Story Driven Modeling book
       */

      Storyboard story = new Storyboard();

      //============================================================
      story.addStep("Build model for class University");

      story.markCodeStart();
      ClassModel model = new ClassModel("org.sdmlib.test.examples.studyrightWithAssignments.model");

      Clazz universityClass = model.createClazz("University")
              .withAttribute("name", DataType.STRING);
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



      //============================================================
      story.add("3. add University --> Student association");

      // Association universityToStudent =
      story.markCodeStart();
      universityClass.withBidirectional(studentClass, "students", Cardinality.MANY, "university", Cardinality.ONE);
      story.addCode();

      story.addClassDiagramAsImage(model, 400, 400);

      //      model.generate("src/test/java");

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
//
//      //============================================================
//      story.add("6. generate class source files.");
//
//      // model.removeAllGeneratedCode("src/test/java");
//
//      model.setAuthorName("zuendorf");
//      story.markCodeStart();
        model.generate("src/test/java"); // usually don't specify anything here, then it goes into src
//      story.addCode();
//
//      story.dumpJavaDoc(ClassModel.class.getName());
      story.dumpHTML();
   }

}
