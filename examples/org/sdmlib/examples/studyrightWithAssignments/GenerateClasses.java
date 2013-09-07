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


package org.sdmlib.examples.studyrightWithAssignments;

import static org.sdmlib.models.classes.Role.R.INT;
import static org.sdmlib.models.classes.Role.R.MANY;
import static org.sdmlib.models.classes.Role.R.ONE;
import static org.sdmlib.models.classes.Role.R.STRING;

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;

public class GenerateClasses {

	public static void main(String[] args) {
		 /* This file will generate that necessary classes and class diagram for the
		  * StudyRight with Assignments example in the Story Driven Modeling book
		  */

	      Storyboard storyboard = new Storyboard("examples", "StudyRight with assignments class generation");

	      //============================================================
	      storyboard.add("1. generate class University");

	      ClassModel model = new ClassModel("org.sdmlib.examples.studyrightWithAssignments");

	      Clazz universityClass = new Clazz("University")
	      .withAttribute("name", STRING);

	      storyboard.addImage(model.dumpClassDiagram("examples", "StudyRight with assignments class generation 01"));


	      //============================================================
	      storyboard.add("2. generate class Student");

	      Clazz studentClass = new Clazz("Student").withAttributes(
	         "name", STRING,
	         "id", STRING,
	         "assignmentPoints", INT,
	         "motivation", INT, 
	         "credits", INT);

	      storyboard.addImage(model.dumpClassDiagram("examples", "StudyRight with assignments class generation 02"));


	      //============================================================
	      storyboard.add("3. add University --> Student association");

	      //Association universityToStudent = 
	      new Association()
	      .withSource("university", universityClass, ONE)
	      .withTarget("students", studentClass, MANY); 

	      storyboard.addImage(model.dumpClassDiagram("examples", "StudyRight with assignments class generation 03"));


	      //============================================================
	      storyboard.add("4. add University --> Room association");

	      Clazz roomClass = new Clazz("Room")
	      .withAttribute("name", STRING)
	      .withAttribute("topic", STRING)
	      .withAttribute("credits", INT);

	      new Method().withClazz(roomClass).withSignature("findPath(String,int)").withReturnType("void");

	      //Association universityToRoom = 
	      new Association()
	      .withSource("university", universityClass, ONE, Role.AGGREGATION)
	      .withTarget("rooms", roomClass, MANY); 

	      //Association doors = 
	      new Association()
	      .withSource("doors", roomClass, MANY)
	      .withTarget("doors", roomClass, MANY);

	      //Association studentsInRoom = 
	      new Association()
	      .withSource("students", studentClass, MANY)
	      .withTarget("in", roomClass, ONE);

	      storyboard.addImage(model.dumpClassDiagram("examples", "StudyRightClasses04"));

	      
	      //============================================================
	      storyboard.add("5. add assignments:");
	      
	      Clazz assignmentClass = roomClass.createClassAndAssoc("Assignment", "assignments", MANY, "room", ONE)
	            .withAttributes("content", STRING, "points", INT);
	      
	      studentClass.withAssoc(assignmentClass, "done", MANY, "students", ONE);

	      storyboard.addImage(model.dumpClassDiagram("examples", "StudyRightClasses04b"));

	      
	      //============================================================
	      storyboard.add("6. generate class source files.");

	      model.removeAllGeneratedCode("examples");
	      
	      model.generate("examples"); // usually don't specify anything here, then it goes into src


	      StoryboardManager.get()
	      .add(storyboard)
	      .dumpHTML();
	}

	@Test
	public void testMain()
	{
	   main(null);
	}
	
}
