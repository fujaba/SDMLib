package org.sdmlib.examples.studyright;

import org.junit.Test;
import org.sdmlib.examples.studyright.creators.UniversityCreator;
import org.sdmlib.models.debug.FlipBook;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.scenarios.ScenarioManager;
import org.sdmlib.serialization.json.JsonIdMap;

public class StudyRightScenarios
{
   @Test
   public void testStudyRightObjectScenarios()
   {
      Scenario scenario = new Scenario("examples");
      
      
      //=============================================================
      scenario.add("adding flipbook to protocol changes.");
      JsonIdMap idMap = UniversityCreator.createIdMap("ajz");
      FlipBook flipBook = idMap.createFlipBook();

      
      //=============================================================
      scenario.add("Start situation: use University class to build object structure",
         BACKLOG, "zuendorf", "25.03.2012 21:37:46", 0, 0);

      University uni = new University()
      .withName("StudyRight");
      idMap.getId(uni);

      Student albert = uni.createStudents()
      .withMatrNo(4242)
      .withName("Albert");

      Student nina = uni.createStudents()
      .withMatrNo(2323)
      .withName("Nina");

      Room mathRoom = uni.createRooms()
      .withRoomNo("math")
      .withCredits(42)  
      .withStudents(albert); 

      Room artsRoom = uni.createRooms()
      .withRoomNo("arts")
      .withCredits(23)
      .withNeighbors(mathRoom); 

      Room sportsRoom = uni.createRooms()
      .withRoomNo("sports")
      .withCredits(23)
      .withNeighbors(mathRoom)
      .withNeighbors(artsRoom); 

      Room examRoom = uni.createRooms()
      .withRoomNo("exam")
      .withCredits(0)
      .withNeighbors(sportsRoom)
      .withNeighbors(artsRoom);
      
      Room progMeth = uni.createRooms().withRoomNo("ProgMeth").withCredits(42).withNeighbors(artsRoom);

      progMeth.withNeighbors(examRoom);
      
      scenario.add("step 1: dump object diagram");

      scenario.addObjectDiag(idMap, uni);

      
      //===============================================================================================
      scenario.add("Test flipbook, undo last step: ");
      
      flipBook.back(42);
      flipBook.forward(42);
      
      flipBook.back(sportsRoom, Room.PROPERTY_CREDITS);

      flipBook.back()
      .back()
      .back()
      .forward();

      scenario.addObjectDiag(idMap, uni);

      //===============================================================================================
      scenario.assertEquals("Number of students: " , 2, uni.getStudents().size()); 

      scenario.add("step 2: add support for path navigation\n      call ");

      scenario.markCodeStart();
      int sum = albert.getUni().getRooms().getCredits().sum();
      scenario.addCode("examples");

      scenario.add(
         "      shall compute to 88\n" +
               "      Path classes are generated.", 
               DONE, "zuendorf joern alex", "19.05.2012 20:40:42", 1, 0);

      // Assert.assertEquals("credits sum error", 88, sum);

      scenario.add( "Feature Request: model sets need to provide a navigation to any neighbors\n" +
            "e.g.: ModelSet any = ModelSet.startWith(albert).getAny(); ", 
            BACKLOG, "zuendorf", "19.05.2012 20:42:42", 0, 2);

      scenario.add( "Feature Request (DONE): model sets need to provide set methods and other methods. These methods shall be forwarded to each set member. \n" +
            "e.g.: room.getNeighbors().findPath(path, motivation); ", 
            DONE, "zuendorf", "19.08.2012 23:04:42", 4, 0);

      // scenario.assertEquals("Number of neighbors for Albert is now ", 2, any.size());

      scenario.add("step 3: call ");

      scenario.recordSystemOut();

      scenario.markCodeStart();
      mathRoom.findPath("", 88);
      scenario.addCode("examples");

      scenario.add("System.out: \n" + scenario.getSystemOut());


      ScenarioManager.get()
      .add(scenario)
      .dumpHTML();
   }


   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";
   public static final String BUG = "bug";
}
