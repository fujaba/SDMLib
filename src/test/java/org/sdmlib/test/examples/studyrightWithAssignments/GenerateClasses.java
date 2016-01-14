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

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.storyboards.StoryPage;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;
import de.uniks.networkparser.graph.Parameter;

public class GenerateClasses 
{

	/**
	 * 
	 * @see <a href='../../../../../../../../doc/main.html'>main.html</a>/n * @see <a href='../../../../../../../../doc/StudyRightWithAssignmentsClassGeneration.html'>StudyRightWithAssignmentsClassGeneration.html</a>/n */
	@Test
	public void testStudyRightWithAssignmentsClassGeneration()
	{
	  /* This file will generate that necessary classes and class diagram for the
	   * StudyRight with Assignments example in the Story Driven Modeling book
	   */

	  // file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/StudyRight%20with%20assignments%20class%20generation.html

	  StoryPage story = new StoryPage();

	  //============================================================
	  story.add("1. generate class University");

	  story.markCodeStart();
	  ClassModel model = new ClassModel("org.sdmlib.test.examples.studyrightWithAssignments.model");

      Clazz universityClass = model.createClazz("University")
            .withAttribute("name", DataType.STRING);
      story.addCode();
      
      story.addClassDiagram(model);

      //============================================================
      story.add("2. generate class Student");

      story.markCodeStart();
      Clazz studentClass = model.createClazz("Student")
            .withAttribute("name", DataType.STRING)
            .withAttribute("id", DataType.STRING)
            .withAttribute("assignmentPoints", DataType.INT)
            .withAttribute("motivation", DataType.INT) 
            .withAttribute("credits", DataType.INT);
      story.addCode();

      story.addClassDiagram(model);


      //============================================================
      story.add("3. add University --> Student association");

      // Association universityToStudent = 
      story.markCodeStart();
      universityClass.withBidirectional(studentClass, "students", Cardinality.MANY, "university", Cardinality.ONE);
      story.addCode();

      story.addClassDiagram(model);


      //============================================================
      story.add("4. add University --> Room association");

      story.markCodeStart();
      Clazz roomClass = model.createClazz("Room")
            .withAttribute("name", DataType.STRING)
            .withAttribute("topic", DataType.STRING)
            .withAttribute("credits", DataType.INT);

      roomClass.withMethod("findPath", DataType.STRING, new Parameter(DataType.INT).with("motivation"));

      //Association universityToRoom = 
      universityClass.withBidirectional(roomClass, "rooms", Cardinality.MANY, "university", Cardinality.ONE);
      
      // Association doors = 
      roomClass.withBidirectional(roomClass, "doors", Cardinality.MANY, "doors", Cardinality.MANY);

      // Association studentsInRoom = 
      studentClass.withBidirectional(roomClass, "in", Cardinality.ONE, "students", Cardinality.MANY);
      story.addCode();

      story.addClassDiagram(model);
      
      
      
      //============================================================
      story.add("5. add assignments:");

      story.markCodeStart();
      Clazz assignmentClass = model.createClazz("Assignment")
               .withAttribute("content", DataType.STRING)
               .withAttribute("points", DataType.INT)
               .withBidirectional(roomClass, "room", Cardinality.ONE, "assignments", Cardinality.MANY);
      
      studentClass.withBidirectional(assignmentClass, "done", Cardinality.MANY, "students", Cardinality.MANY);
      story.addCode();
      
      story.addClassDiagram(model);
      
      studentClass.withBidirectional(studentClass, "friends", Cardinality.MANY, "friends", Cardinality.MANY);
      
      
      // some more classes for model navigation tests
      studentClass.withBidirectional(studentClass, "friends", Cardinality.MANY, "friends", Cardinality.MANY);
      
      model.createClazz("TeachingAssistant")
      .withSuperClazz(studentClass)
      .withBidirectional(roomClass, "room", Cardinality.ONE, "tas", Cardinality.MANY)
      .withAttribute("certified", DataType.BOOLEAN);
      

      //============================================================
      story.add("6. generate class source files.");

      // model.removeAllGeneratedCode("src/test/java");
      
      model.withAuthorName("zuendorf");
      story.markCodeStart();
      model.generate("src/test/java"); // usually don't specify anything here, then it goes into src
      story.addCode();
      

      story.dumpHTML();
   }

}
