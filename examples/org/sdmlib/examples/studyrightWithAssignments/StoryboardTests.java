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
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.storyboards.StoryboardManager;

public class StoryboardTests {
   @Test
   public void testStudyRightObjectStoryboards()
   {
      Storyboard storyboard = new Storyboard("examples", "StudyRight with assignments storyboard");

      //=============================================================
      storyboard.add("1. (start situation/pre-condition) Karli enters the Study-Right University \n"
            +"in the math room. Karli has no credits yet and still a motivation of 214. ");

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

      Assignment a3 = new Assignment()
      .withContent("Integrals")
      .withPoints(8);

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

      storyboard.addObjectDiagram("studyRight", university, "karli", karli,"mathRoom", mathRoom, "artsRoom", artsRoom,
         "sportsRoom", sportsRoom, "examRoom", examRoom, "placeToBe", softwareEngineering);

      //===============================================================================================
      storyboard.add("2. Karli does assignment a1 on Matrix Multiplication and earns 5 points <br>\n"
            +"(general rule: the student earns always full points for doing an assignment). <br>\n"
            +"Karli's motivation is reduced by 5 points to now 209.\n"); 

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a1.getPoints());

      storyboard.addObjectDiagram(karli, mathRoom, mathRoom.getAssignments(), true);

      //===============================================================================================
      storyboard.add("3. Karli does assignment a2 on Series and earns another 6 points. <br>\n"
            +"Thus Karli has 11 points now. Motivation is reduced to 203.\n");

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a2.getPoints());

      storyboard.addObjectDiagram(karli, mathRoom, mathRoom.getAssignments(), true);

      //===============================================================================================
      storyboard.add("4. Karli does the third assignment on Integrals, earns <br>\n"
            +"another 8 points and thus Karli has now 19 points and a motivation of 195.\n"); 

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a3.getPoints());

      storyboard.addObjectDiagram(karli, mathRoom, mathRoom.getAssignments(), true);

      //===============================================================================================
      storyboard.add("5. Since 19 points are more than the 17 points required \n"
            +"for the 17 math credits, Karli hands the points in and earns the credits \n"
            +"and has his assignmnet points reset to 0. <br>\n"
            +"(General rule: if the points earned by the assignments are higher or equal than \n"
            +"the credit points, the credit points will be awarded to the student.)"); 

      if ( karli.getAssignmentPoints() >= mathRoom.getCredits() )
      {
         karli.setCredits(karli.getCredits() + mathRoom.getCredits() );
         karli.setAssignmentPoints(0);
      }

      storyboard.addObjectDiagram(karli, mathRoom, mathRoom.getAssignments(), true);

      //===============================================================================================
      storyboard.add("6. (end situation/post-condition) Karli has completed the math topic and moves to sports."); 

      //===============================================================================================
      storyboard.assertEquals("Karli's credits: ", 17, karli.getCredits()); 
      storyboard.assertEquals("Karli's assignment points: ", 0, karli.getAssignmentPoints()); 
      storyboard.assertEquals("Number of students: ", 1, university.getStudents().size()); 


      //================ Create HTML
      StoryboardManager.get()
      .add(storyboard)
      .dumpHTML();
   }
}