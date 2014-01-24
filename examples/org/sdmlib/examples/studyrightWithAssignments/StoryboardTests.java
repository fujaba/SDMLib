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
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.examples.studyrightWithAssignments.creators.AssignmentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.ModelPattern;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.creators.RoomSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentPO;
import org.sdmlib.examples.studyrightWithAssignments.creators.StudentSet;
import org.sdmlib.examples.studyrightWithAssignments.creators.UniversityCreator;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.models.pattern.GenericConstraint;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.json.JsonArray;
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

      storyboard.addObjectDiagram(
         "studyRight", university, 
         "karli", "icons/karli.png", karli, 
         "mathRoom", "icons/mathroom.png", mathRoom, 
         "artsRoom", artsRoom,
         "sportsRoom", sportsRoom, 
         "examRoom", examRoom, 
         "placeToBe", softwareEngineering, 
         "icons/matrix.png", a1, 
         "icons/limes.png", a2 , "icons/integralAssignment.png", a3);

      //===============================================================================================
      storyboard.add("2. Karli does assignment a1 on Matrix Multiplication and earns 5 points <br>\n"
            +"(general rule: the student earns always full points for doing an assignment). <br>\n"
            +"Karli's motivation is reduced by 5 points to now 209.\n"); 

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a1.getPoints());
      karli.addToDone(a1);

      storyboard.addObjectDiagramWith(karli, mathRoom, mathRoom.getAssignments());

      //===============================================================================================
      storyboard.add("3. Karli does assignment a2 on Series and earns another 6 points. <br>\n"
            +"Thus Karli has 11 points now. Motivation is reduced to 203.\n");

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a2.getPoints());
      karli.addToDone(a2);

      storyboard.addObjectDiagramWith(karli, mathRoom, mathRoom.getAssignments());

      //===============================================================================================
      storyboard.add("4. Karli does the third assignment on Integrals, earns <br>\n"
            +"another 8 points and thus Karli has now 19 points and a motivation of 195.\n"); 

      karli.setAssignmentPoints(karli.getAssignmentPoints() + a3.getPoints());
      karli.addToDone(a3);

      storyboard.addObjectDiagramWith(karli, mathRoom, mathRoom.getAssignments());

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

      storyboard.addObjectDiagramWith(karli, mathRoom, mathRoom.getAssignments());

      //===============================================================================================
      storyboard.add("6. (end situation/post-condition) Karli has completed the math topic and moves to sports."); 

      //===============================================================================================
      storyboard.assertEquals("Karli's credits: ", 17, karli.getCredits()); 
      storyboard.assertEquals("Karli's assignment points: ", 0, karli.getAssignmentPoints()); 
      storyboard.assertEquals("Number of students: ", 1, university.getStudents().size()); 


      //================ Create HTML
      storyboard.dumpHTML();
   }
   
   @Test
   public void testStudyRightObjectModelSerialisation()
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

      storyboard.addObjectDiagram(
         "studyRight", university, 
         "karli", "icons/karli.png", karli, 
         "mathRoom", "icons/mathroom.png", mathRoom, 
         "artsRoom", artsRoom,
         "sportsRoom", sportsRoom, 
         "examRoom", examRoom, 
         "placeToBe", softwareEngineering, 
         "icons/matrix.png", a1, 
         "icons/limes.png", a2 , "icons/integralAssignment.png", a3);

      
      //=====================================================
      storyboard.addStep("Serialize to json:");
      
      storyboard.markCodeStart();
      
      JsonIdMap idMap = UniversityCreator.createIdMap("demo");
      
      JsonArray jsonArray = idMap.toJsonArray(university);
      
      String jsonText = jsonArray.toString(3);
      
      // you might write jsonText into a file
      
      storyboard.addCode();
      
      storyboard.add("Results in:");
      
      storyboard.add("<pre>" + jsonText + "</pre>");
      
      
      //=====================================================
      storyboard.addStep("Now read it back again");
      
      storyboard.markCodeStart();
      
      // read jsonText from file
      JsonArray readJsonArray = new JsonArray().withValue(jsonText);
      
      JsonIdMap readerMap = UniversityCreator.createIdMap("demo");
      
      Object rootObject = readerMap.decode(readJsonArray);
      
      storyboard.addCode();
      
      storyboard.addObjectDiagram(rootObject);
      
      storyboard.dumpHTML();
   }

   @Test
   public void testStudyRightObjectModelNavigationAndQueries()
   {
      Storyboard storyboard = new Storyboard();
      
      storyboard.add("Extend the class model:", Storyboard.DONE, "zuendorf", "17.01.2014 16:35:42", 40, 0);
      
      ClassModel model = new ClassModel();
      
      Clazz studentClass = model.createClazz(Student.class.getName());
 
      studentClass.withAssoc(studentClass, "friends", R.MANY, "friends", R.MANY);
      
      Clazz roomClass = model.createClazz(Room.class.getName());
      
      roomClass.createClassAndAssoc("TeachingAssistant", "tas", R.MANY, "room", R.ONE);
      
      storyboard.addClassDiagram(model);
      
      // model.generate("examples");
      
      storyboard.add("How to navigate and query an object model.");
      
      storyboard.addStep("Example object structure:");
      
      University university = new University()
      .withName("StudyRight");

      Student karli = university.createStudents()
            .withId("4242")
            .withName("Karli");
      
      Student abu = university.createStudents()
            .withId("1337")
            .withName("Abu");
      
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

      storyboard.addObjectDiagram(
         "studyRight", university, 
         "karli", "icons/karli.png", karli, 
         "abu", "icons/karli.png", abu, 
         "alice", "icons/karli.png", alice, 
         "mathRoom", "icons/mathroom.png", mathRoom, 
         "artsRoom", artsRoom,
         "sportsRoom", sportsRoom, 
         "examRoom", examRoom, 
         "placeToBe", softwareEngineering, 
         "icons/matrix.png", a1, 
         "icons/limes.png", a2 , 
         "icons/integralAssignment.png", a3, 
         a4);

      
      //=====================================================
      storyboard.addStep("Simple set based navigation:");
      
      storyboard.markCodeStart();
 
      int assignmentPoints = university.getRooms().getAssignments().getPoints().sum();
      
      int donePoints = university.getStudents().getDone().getPoints().sum();
      
      storyboard.addCode();
      
      storyboard.add("Results in:");
      
      StringBuilder text = new StringBuilder(
         "      Sum of assignment points: 42. \n      Sum of points of assignments that have been done by at least one students: 23."
            );
      
      CGUtil.replaceAll(text, "42", "" + assignmentPoints, "23", "" + donePoints);
      
      storyboard.addPreformatted(text.toString());
      
      
      //=====================================================
      storyboard.addStep("Rooms with assignments not yet done by Karli:");
      
      storyboard.markCodeStart();
      
      AssignmentSet availableAssignments = university.getRooms().getAssignments().minus(karli.getDone());
      
      RoomSet rooms = availableAssignments.getRoom();
      
      storyboard.addCode();
     
      storyboard.add("Results in:");
      
      storyboard.addPreformatted("      " + rooms.toString());
      
      storyboard.addStep("Filter for attribute:");
      
      storyboard.markCodeStart();
      
      RoomSet rooms17 = rooms.hasCredits(17);

      storyboard.addCode();
      
      storyboard.add("Results in:");
      
      storyboard.addPreformatted("      " + rooms17.toString());
      
      storyboard.addStep("Filter for attribute greater than:");
      
      storyboard.markCodeStart();
      
      // Java 8:
      // (Room elem) -> elem.getCredits() > 20
      
      RoomSet roomsGT = rooms.has(rooms.new Condition() {

         @Override
         public boolean check(Room elem)
         {
            return elem.getCredits() > 20;
         }
         
      });

      storyboard.addCode();
      
      storyboard.add("Results in:");
      
      storyboard.addPreformatted("      " + roomsGT.toString());
      
      
      //====================================================
      storyboard.addStep("Write operations on sets: ");
      
      storyboard.markCodeStart();
      
      university.getStudents().withMotivation(42);
      
      storyboard.addCode();
      
      storyboard.addObjectDiagramWith(university.getStudents());
      
      
      //=====================================================
      storyboard.addStep("Rooms with two students that are friends (and need supervision): ");
      
      storyboard.markCodeStart();
      
      RoomPO roomPO = university.getRooms().startModelPattern();
            
      StudentPO stud1PO = roomPO.hasStudents();      
      
      roomPO.hasStudents().hasMotivation(42).hasFriends(stud1PO);
      
      rooms = roomPO.allMatches();
      
      storyboard.addCode();
     
      storyboard.addPattern(roomPO.getPattern(), false);
      
      storyboard.add("Results in:");
      
      storyboard.addPreformatted("      " + rooms.toString());
      

      //=====================================================
      storyboard.addStep("Rooms with two students with low motivation that are friends (and need supervision): ");
      
      storyboard.markCodeStart();
      
      roomPO = university.getRooms().startModelPattern();
            
      stud1PO = roomPO.hasStudents();      
      
      final StudentPO stud2PO = roomPO.hasStudents().hasMotivation(0, 50);
      
      // Java 8: 
      // stud2PO.has( () -> stud2PO.getMotivation() < 50);
      
      //      stud2PO.has(new GenericConstraint.Condition()
      //      {
      //         @Override
      //         public boolean check()
      //         {
      //            return stud2PO.getMotivation() < 50;
      //         }
      //      });
      
      stud2PO.hasFriends(stud1PO);
      
      rooms = roomPO.allMatches();
      
      storyboard.addCode();
     
      storyboard.addPattern(roomPO.getPattern(), false);
      
      storyboard.add("Results in:");
      
      storyboard.addPreformatted("      " + rooms.toString());

      //=====================================================
      storyboard.addStep("Rooms with two students without supervision that are friends and add teaching assistance: ");
      
      storyboard.markCodeStart();
      
      roomPO = university.getRooms().startModelPattern();
            
      stud1PO = roomPO.hasStudents();      
      
      roomPO.hasStudents().hasFriends(stud1PO);
      
      roomPO.hasTas(null);
      
      roomPO.startCreate().hasTas().endCreate();
      
      rooms = roomPO.allMatches();
      
      storyboard.addCode();
     
      storyboard.addPattern(roomPO.getPattern(), false);
      
      storyboard.add("Results in:");
      
      storyboard.addObjectDiagramWith(rooms, rooms.getStudents(), rooms.getTas());
      
      storyboard.addPreformatted("      " + rooms.toString());

      storyboard.dumpHTML();
   }

}
