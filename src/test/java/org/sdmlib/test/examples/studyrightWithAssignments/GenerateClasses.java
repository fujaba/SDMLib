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
import static org.sdmlib.models.classes.Card.*;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Parameter;

import static org.sdmlib.models.classes.DataType.*;
import org.sdmlib.storyboards.StoryPage;

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
            .withAttribute("name", STRING);
      story.addCode();
      
      story.addClassDiagram(model);

      //============================================================
      story.add("2. generate class Student");

      story.markCodeStart();
      Clazz studentClass = model.createClazz("Student")
            .withAttribute("name", STRING)
            .withAttribute("id", STRING)
            .withAttribute("assignmentPoints", INT)
            .withAttribute("motivation", INT) 
            .withAttribute("credits", INT);
      story.addCode();

      story.addClassDiagram(model);


      //============================================================
      story.add("3. add University --> Student association");

      // Association universityToStudent = 
      story.markCodeStart();
      universityClass.withAssoc(studentClass, "students", MANY, "university", ONE);
      story.addCode();

      story.addClassDiagram(model);


      //============================================================
      story.add("4. add University --> Room association");

      story.markCodeStart();
      Clazz roomClass = model.createClazz("Room")
            .withAttribute("name", STRING)
            .withAttribute("topic", STRING)
            .withAttribute("credits", INT);

      roomClass.withMethod("findPath", STRING, new Parameter("motivation", INT));

      //Association universityToRoom = 
      universityClass.withAssoc(roomClass, "rooms", MANY, "university", ONE);
      
      // Association doors = 
      roomClass.withAssoc(roomClass, "doors", MANY, "doors", MANY);

      // Association studentsInRoom = 
      studentClass.withAssoc(roomClass, "in", ONE, "students", MANY);
      story.addCode();

      story.addClassDiagram(model);
      
      
      
      //============================================================
      story.add("5. add assignments:");

      story.markCodeStart();
      Clazz assignmentClass = model.createClazz("Assignment")
               .withAttribute("content", STRING)
               .withAttribute("points", INT)
               .withAssoc(roomClass, "room", ONE, "assignments", MANY);
      
      studentClass.withAssoc(assignmentClass, "done", MANY, "students", MANY);
      story.addCode();
      
      story.addClassDiagram(model);
      
      studentClass.withAssoc(studentClass, "friends", MANY, "friends", MANY);
      
      
      // some more classes for model navigation tests
      studentClass.withAssoc(studentClass, "friends", MANY, "friends", MANY);
      
      model.createClazz("TeachingAssistant")
      .withSuperClazz(studentClass)
      .withAssoc(roomClass, "room", ONE, "tas", MANY)
      .withAttribute("certified", BOOLEAN);
      

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
