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

import org.junit.Test;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;

public class GenerateClasses {

   public static void main(String[] args) 
   {
      /* This file will generate that necessary classes and class diagram for the
       * StudyRight with Assignments example in the Story Driven Modeling book
       */

      // file:///C:/Users/zuendorf/eclipseworkspaces/indigo/SDMLib/doc/StudyRight%20with%20assignments%20class%20generation.html
      
      Storyboard storyboard = new Storyboard("src/test/java", "StudyRight with assignments class generation");

      //============================================================
      storyboard.add("1. generate class University");
      
      storyboard.markCodeStart();
      ClassModel model = new ClassModel("org.sdmlib.examples.studyrightWithAssignments.model");

      Clazz universityClass = model.createClazz("University")
            .withAttribute("name", DataType.STRING);
      storyboard.addCode();
      
      storyboard.addClassDiagram(model);

      //============================================================
      storyboard.add("2. generate class Student");

      storyboard.markCodeStart();
      Clazz studentClass = model.createClazz("Student")
            .withAttribute("name", DataType.STRING)
            .withAttribute("id", DataType.STRING)
            .withAttribute("assignmentPoints", DataType.INT)
            .withAttribute("motivation", DataType.INT) 
            .withAttribute("credits", DataType.INT);
      storyboard.addCode();

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("3. add University --> Student association");

      // Association universityToStudent = 
      storyboard.markCodeStart();
      universityClass.withAssoc(studentClass, "students", Card.MANY, "university", Card.ONE);
      storyboard.addCode();

      storyboard.addClassDiagram(model);


      //============================================================
      storyboard.add("4. add University --> Room association");

      storyboard.markCodeStart();
      Clazz roomClass = model.createClazz("Room")
            .withAttribute("name", DataType.STRING)
            .withAttribute("topic", DataType.STRING)
            .withAttribute("credits", DataType.INT);

      roomClass.createMethod("findPath").withParameter("String", DataType.INT);

      //Association universityToRoom = 
      universityClass.withAssoc(roomClass, "rooms", Card.MANY, "university", Card.ONE);
      
      // Association doors = 
      roomClass.withAssoc(roomClass, "doors", Card.MANY, "doors", Card.MANY);

      // Association studentsInRoom = 
      studentClass.withAssoc(roomClass, "in", Card.ONE, "students", Card.MANY);
      storyboard.addCode();

      storyboard.addClassDiagram(model);
      
      
      
      //============================================================
      storyboard.add("5. add assignments:");

      storyboard.markCodeStart();
      Clazz assignmentClass = model.createClazz("Assignment")
               .withAttribute("content", DataType.STRING)
               .withAttribute("points", DataType.INT)
               .withAssoc(roomClass, "room", Card.ONE, "assignments", Card.MANY);
      
      studentClass.withAssoc(assignmentClass, "done", Card.MANY, "students", Card.MANY);
      storyboard.addCode();
      
      storyboard.addClassDiagram(model);
      
      studentClass.withAssoc(studentClass, "friends", Card.MANY, "friends", Card.MANY);
      
      
      // some more classes for model navigation tests
      studentClass.withAssoc(studentClass, "friends", Card.MANY, "friends", Card.MANY);
      
      model.createClazz("TeachingAssistant")
      .withSuperClazz(studentClass)
      .withAssoc(roomClass, "room", Card.ONE, "tas", Card.MANY)
      .withAttribute("certified", DataType.BOOLEAN);
      

      //============================================================
      storyboard.add("6. generate class source files.");

      model.removeAllGeneratedCode("src/test/java");

      storyboard.markCodeStart();
      model.generate("src/test/java"); // usually don't specify anything here, then it goes into src
      storyboard.addCode();
      

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
