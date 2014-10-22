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
import org.sdmlib.CGUtil;
import org.sdmlib.examples.studyrightWithAssignments.model.Assignment;
import org.sdmlib.examples.studyrightWithAssignments.model.Room;
import org.sdmlib.examples.studyrightWithAssignments.model.Student;
import org.sdmlib.examples.studyrightWithAssignments.model.TeachingAssistant;
import org.sdmlib.examples.studyrightWithAssignments.model.University;
import org.sdmlib.examples.studyrightWithAssignments.model.util.AssignmentSet;
import org.sdmlib.examples.studyrightWithAssignments.model.util.RoomPO;
import org.sdmlib.examples.studyrightWithAssignments.model.util.RoomSet;
import org.sdmlib.examples.studyrightWithAssignments.model.util.StudentPO;
import org.sdmlib.examples.studyrightWithAssignments.model.util.TeachingAssistantPO;
import org.sdmlib.examples.studyrightWithAssignments.model.util.TeachingAssistantSet;
import org.sdmlib.examples.studyrightWithAssignments.model.util.UniversityCreator;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.pattern.Match;
import org.sdmlib.storyboards.Kanban;
import org.sdmlib.storyboards.Storyboard;

import de.uniks.networkparser.json.JsonArray;
import de.uniks.networkparser.json.JsonIdMap;

public class StoryboardTests {
   @Test
   public void testStudyRightObjectStoryboards()
   {
      Storyboard storyboard = new Storyboard("src/test/java", "StudyRight with assignments storyboard");

      //=============================================================
      storyboard.add("1. (start situation/pre-condition) Karli enters the Study-Right University \n"
            +"in the math room. Karli has no credits yet and still a motivation of 214. ");

      storyboard.markCodeStart();
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
      storyboard.addCode();
      
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

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + a1.getPoints());
      karli.withDone(a1);
      storyboard.addCode();
      
      storyboard.addObjectDiagramWith(karli, mathRoom, mathRoom.getAssignments());

      //===============================================================================================
      storyboard.add("3. Karli does assignment a2 on Series and earns another 6 points. <br>\n"
            +"Thus Karli has 11 points now. Motivation is reduced to 203.\n");

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + a2.getPoints());
      karli.withDone(a2);
      storyboard.addCode();
      
      storyboard.addObjectDiagramWith(karli, mathRoom, mathRoom.getAssignments());

      //===============================================================================================
      storyboard.add("4. Karli does the third assignment on Integrals, earns <br>\n"
            +"another 8 points and thus Karli has now 19 points and a motivation of 195.\n"); 

      storyboard.markCodeStart();
      karli.setAssignmentPoints(karli.getAssignmentPoints() + a3.getPoints());
      karli.withDone(a3);
      storyboard.addCode();
      
      storyboard.addObjectDiagramWith(karli, mathRoom, mathRoom.getAssignments());

      //===============================================================================================
      storyboard.add("5. Since 19 points are more than the 17 points required \n"
            +"for the 17 math credits, Karli hands the points in and earns the credits \n"
            +"and has his assignmnet points reset to 0. <br>\n"
            +"(General rule: if the points earned by the assignments are higher or equal than \n"
            +"the credit points, the credit points will be awarded to the student.)"); 

      storyboard.markCodeStart();
      if ( karli.getAssignmentPoints() >= mathRoom.getCredits() )
      {
         karli.setCredits(karli.getCredits() + mathRoom.getCredits() );
         karli.setAssignmentPoints(0);
      }
      storyboard.addCode();
      

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
      
      storyboard.addLogEntry(Kanban.DONE, "stefan", "11.11.2012 12:00:00 EST", 80, 0, "Serialisation via JSON works great.");
      
      storyboard.dumpHTML();
   }

   @Test
   public void testStudyRightObjectModelNavigationAndQueries()
   {
      Storyboard story = new Storyboard();
      
      story.add("Extend the class model:", Storyboard.DONE, "zuendorf", "17.01.2014 16:35:42", 40, 0);
      
      ClassModel model = new ClassModel();
      
      Clazz studentClass = model.createClazz(Student.class.getName());
 
      studentClass.withAssoc(studentClass, "friends", Card.MANY, "friends", Card.MANY);
      
      Clazz roomClass = model.createClazz(Room.class.getName());
      
      Clazz taClass = model.createClazz("TeachingAssistant");
      
      roomClass.withAssoc(taClass, "tas", Card.MANY, "room", Card.ONE);
      
      story.addClassDiagram(model);
      
      // model.generate("examples");
      
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
            .withName("Karli")
            );
      
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

      story.addObjectDiagram(
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
      story.addStep("Simple set based navigation:");
      
      story.markCodeStart();
 
      int assignmentPoints = university.getRooms().getAssignments().getPoints().sum();
      
      int donePoints = university.getStudents().getDone().getPoints().sum();
      
      story.addCode();
      
      story.add("Results in:");
      
      String text = CGUtil.replaceAll(
         "      Sum of assignment points: 42. \n" +
         "      Sum of points of assignments that have been done by at least one students: 84.",
         "42", assignmentPoints, 
         "84", donePoints);
      
      story.addPreformatted(text);
      
      story.assertEquals("Assignment points: ", 23, assignmentPoints);
      story.assertEquals("donePoints: ", 9, donePoints);
      
      //=====================================================
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
      
      RoomSet rooms17 = university.getRooms().hasCredits(17);
      RoomSet roomsGE20 = university.getRooms().hasCredits(20, Integer.MAX_VALUE);

      story.addCode();
      
      story.add("Results in:");
      
      story.addPreformatted("      rooms17: " + rooms17.toString() 
         + "\n      roomsGE20: " + roomsGE20);
      
      story.addStep("Filter for attribute greater than:");
      
      story.markCodeStart();
      
      // Java 8:
      // (Room elem) -> elem.getCredits() > 20
      
      RoomSet roomsEven = university.getRooms().has(rooms.new Condition() {

         @Override
         public boolean check(Room elem)
         {
            return elem.getCredits() % 2 == 0;
         }
         
      });
      
      story.addCode();
      
      story.add("Results in:");
      
      story.addPreformatted("      " + roomsEven);
      
      //====================================================
      story.addStep("Filter for type: ");
      
      story.markCodeStart();
      
      TeachingAssistantSet taStudents = university.getRooms().getStudents().instanceOf(new TeachingAssistantSet());
      
      story.addCode();
      
      story.addPreformatted("" + taStudents);
      
      //====================================================
      story.addStep("Write operations on sets: ");
      
      story.markCodeStart();
      
      university.getStudents().withMotivation(42);
      
      story.addCode();
      
      story.addObjectDiagramWith(university.getStudents());
      
      
      //=====================================================
      story.addStep("Rooms with two students that are friends (and need supervision): ");
      
      story.markCodeStart();
      
      RoomPO roomPO = university.getRooms().hasRoomPO();
            
      StudentPO stud1PO = roomPO.hasStudents();      
      
      roomPO.hasStudents().hasMotivation(42).hasFriends(stud1PO);
      
      rooms = roomPO.allMatches();
      
      story.addCode();
     
      story.addPattern(roomPO.getPattern(), false);
      
      story.add("Results in:");
      
      story.addPreformatted("      " + rooms.toString());
      

      //=====================================================
      story.addStep("Rooms with two students with low motivation that are friends (and need supervision): ");
      
      story.markCodeStart();
      
      roomPO = university.getRooms().hasRoomPO();
            
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
      
      story.addCode();
     
      story.addPattern(roomPO.getPattern(), false);
      
      story.add("Results in:");
      
      story.addPreformatted("      " + rooms.toString());

      //=====================================================
      story.addStep("Rooms with two students without supervision that are friends and add teaching assistance: ");
      
      story.markCodeStart();
      
      roomPO = university.getRooms().hasRoomPO();
            
      stud1PO = roomPO.hasStudents();      
      
      roomPO.hasStudents().hasFriends(stud1PO);
      
      roomPO.hasTas(null);
      
      roomPO.createTas();
      
      rooms = roomPO.allMatches();
      
      story.addCode();
     
      story.addPattern(roomPO.getPattern(), false);
      
      story.add("Results in:");
      
      story.addObjectDiagramWith(rooms, rooms.getStudents(), rooms.getTas());
      
      story.addPreformatted("      " + rooms.toString());
      
      //=====================================================
      story.addStep("TAs as students in a room: ");
      
      story.markCodeStart();
      
      roomPO = university.getRooms().hasRoomPO();
      
      stud1PO = roomPO.hasStudents();
      
      TeachingAssistantPO taPO = stud1PO.instanceOf(new TeachingAssistantPO());
      
      TeachingAssistantSet taSet = taPO.allMatches();
      
      story.addCode();

      story.addPattern(roomPO, false);
      
      story.addObjectDiagramWith(taSet, taSet.getRoom());


      //=====================================================
      story.addStep("Double motivation of all students: ");
      
      story.markCodeStart();
      
      roomPO = university.getRooms().hasRoomPO();
      
      stud1PO = roomPO.hasStudents();

      for (Match match : (Iterable<Match>) roomPO.getPattern())
      {
         Student currentMatch = stud1PO.getCurrentMatch();
         
         currentMatch.withMotivation(currentMatch.getMotivation() * 2);
         
         // or more simple:
         stud1PO.withMotivation(stud1PO.getMotivation() * 2);
        
         System.out.println("match " + match.number + ": " + currentMatch + " in room " + roomPO.getCurrentMatch());
      }
      
      story.addCode();

      story.addPattern(roomPO, false);
      
      story.addObjectDiagramWith(university.getStudents(), university.getStudents().getIn());
      
      story.addLogEntry(Kanban.DONE, "zuendorf", "24.02.2014 20:03:42 EST", 2, 0, "added createXY shortcuts for patterns");
      story.addLogEntry(Kanban.DONE, "zuendorf", "25.02.2014 22:11:42 EST", 2, 0, "switched to roomSet.hasRoomPO()");


      story.dumpHTML();
   }

}
