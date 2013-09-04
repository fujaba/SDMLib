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
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;

public class StoryboardTests {
  @Test
   public void testStudyRightObjectScenarios()
   {
      Scenario scenario = new Scenario("examples", "StudyRight with assignments storyboard");
      
      
      //=============================================================
      JsonIdMap idMap = UniversityCreator.createIdMap("ajz");

      
      //=============================================================
      scenario.add("1. (start situation/pre-condition) Karli enters the Study-Right University \n"
    		  +"in the math room. Karli has no credits yet and still a motivation of 214. ");

      University university = new University()
      .withName("StudyRight");
      idMap.getId(university);

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
      .withAssignments(a1)
      .withAssignments(a2)
      .withAssignments(a3);

      Room artsRoom = university.createRooms()
      .withName("7522")
      .withTopic("arts")
      .withCredits(16)
      .withDoor(mathRoom); 

      Room sportsRoom = university.createRooms()
      .withName("gymnasium")
      .withTopic("sports")
      .withCredits(25)
      .withDoor(mathRoom)
      .withDoor(artsRoom); 

      Room examRoom = university.createRooms()
      .withName("The End")
      .withTopic("exam")
      .withCredits(0)
      .withDoor(sportsRoom)
      .withDoor(artsRoom);
      
      Room softwareEngineering = university.createRooms()
      .withName("7422")
      .withTopic("Software Engineering")
      .withCredits(42)
      .withDoor(artsRoom);

      softwareEngineering.withDoor(examRoom);
      
      scenario.addObjectDiag(idMap, university);

      //===============================================================================================
      scenario.add("2. Karli does assignment a1 on Matrix Multiplication and earns 5 points \n"
    		  +"(general rule: the student earns always full points for doing an assignment). \n"
    		  +"Karli's motivation is reduced by 5 points to now 209."); 

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a1.getPoints());
      scenario.addObjectDiag(idMap, university);

      //===============================================================================================
      scenario.add("3. Karli does assignment a2 on Series and earns another 6 points. \n"
    		  +"Thus Karli has 11 points now. Motivation is reduced to 203.");

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a2.getPoints());
      scenario.addObjectDiag(idMap, university);

      //===============================================================================================
	  scenario.add("4. Karli does the third assignment on Integrals, earns \n"
    		  +"another 8 points and thus Karli has now 19 points and a motivation of 195."); 

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a3.getPoints());
      scenario.addObjectDiag(idMap, university);

      //===============================================================================================
	  scenario.add("5. Since 19 points are more than the 17 points required \n"
    		  +"for the 17 math credits, Karli hands the points in and earns the credits \n"
    		  +"and has his assignmnet points reset to 0. \n"
    		  +"(General rule: if the points earned by the assignments are higher or equal than \n"
    		  +"the credit points, the credit points will be awarded to the student.)"); 

	  if ( karli.getAssignmentPoints() >= mathRoom.getCredits() )
	  {
		  karli.setCredits(karli.getCredits() + mathRoom.getCredits() );
		  karli.setAssignmentPoints(0);
	  }
      scenario.addObjectDiag(idMap, university);

      //===============================================================================================
	  scenario.add("6. (end situation/post-condition) Karli has completed the math topic and moves to sports."); 
      
      //===============================================================================================
      scenario.assertEquals("Karli's credits: ", 17, karli.getCredits()); 
      scenario.assertEquals("Karli's assignment points: ", 0, karli.getAssignmentPoints()); 
     scenario.assertEquals("Number of students: ", 1, university.getStudents().size()); 


      //================ Create HTML
      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }
}
