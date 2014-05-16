package org.sdmlib.examples.studyright;

import org.junit.Test;
import org.sdmlib.examples.studyright.model.Room;
import org.sdmlib.examples.studyright.model.Student;
import org.sdmlib.examples.studyright.model.University;
import org.sdmlib.examples.studyright.model.util.UniversityCreator;
import org.sdmlib.models.classes.SDMLibConfig;
import org.sdmlib.models.debug.FlipBook;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;

public class StudyRightStoryboards
{
   @Test
   public void testStudyRightObjectStoryboards()
   {
      Storyboard storyboard = new Storyboard("examples");

      storyboard.setSprint("Sprint.001.Booting");
      
      //=============================================================
      storyboard.add("Start situation: use University class to build object structure",
         DONE, "zuendorf", "06.10.2013 21:37:46", 0, 0);

      University uni = new University()
      .withName("StudyRight");

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
            .withNeighbors(mathRoom, artsRoom); 

      Room examRoom = uni.createRooms()
            .withRoomNo("exam")
            .withCredits(0)
            .withNeighbors(sportsRoom, artsRoom);

      Room progMeth = uni.createRooms().withRoomNo("ProgMeth").withCredits(42).withNeighbors(artsRoom);

      progMeth.withNeighbors(examRoom);

      storyboard.add("step 1: dump object diagram");

      storyboard.addObjectDiagram(uni);


      //===============================================================================================
      storyboard.assertEquals("Number of students: " , 2, uni.getStudents().size()); 

      storyboard.add("step 2: add support for path navigation\n      call ");

      storyboard.markCodeStart();
      int sum = albert.getUni().getRooms().getCredits().sum();
      storyboard.addCode("examples");

      storyboard.add(
         "shall compute to 88.", 
         DONE, "zuendorf joern alex", "19.05.2012 20:40:42", 1, 0);

      // Assert.assertEquals("credits sum error", 88, sum);

      storyboard.add( "Feature Request: model sets need to provide a navigation to any neighbors\n" +
            "e.g.: ModelSet any = ModelSet.startWith(albert).getAny(); ", 
            BACKLOG, "zuendorf", "17.01.2014 16:42:42", 2, 0);

      storyboard.add( "Feature Request (DONE): model sets need to provide set methods and other methods. These methods shall be forwarded to each set member. \n" +
            "e.g.: room.getNeighbors().findPath(path, motivation); ", 
            DONE, "zuendorf", "19.08.2012 23:04:42", 4, 0);

      // storyboard.assertEquals("Number of neighbors for Albert is now ", 2, any.size());

      storyboard.add("step 3: call ");

      storyboard.recordSystemOut();

      storyboard.markCodeStart();
      mathRoom.findPath("", 88);
      storyboard.addCode("examples");

      storyboard.add("System.out: \n" + storyboard.getSystemOut());

      storyboard.dumpHTML();
   }

   @Test
   public void testFlipbook()
   {
      Storyboard storyboard = new Storyboard("examples");


      //=============================================================
      storyboard.add("adding flipbook to protocol changes.");
      SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) UniversityCreator.createIdMap("ajz");
      FlipBook flipBook = idMap.createFlipBook();


      //=============================================================
      storyboard.add("Start situation: use University class to build object structure",
         DONE, "zuendorf", "06.10.2013 21:37:46", 0, 0);

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
            .withNeighbors(mathRoom, artsRoom); 

      Room examRoom = uni.createRooms()
            .withRoomNo("exam")
            .withCredits(0)
            .withNeighbors(sportsRoom, artsRoom);

      Room progMeth = uni.createRooms().withRoomNo("ProgMeth").withCredits(42).withNeighbors(artsRoom);

      progMeth.withNeighbors(examRoom);

      storyboard.add("step 1: dump object diagram");

      storyboard.addObjectDiagram(idMap, uni);


      //===============================================================================================
      storyboard.add("Test flipbook, undo last step: ");

      flipBook.back(42);
      flipBook.forward(42);

      flipBook.back(sportsRoom, Room.PROPERTY_CREDITS);

      flipBook.back()
      .back()
      .back()
      .forward();

      storyboard.addObjectDiagram(idMap, uni);

      //===============================================================================================
      storyboard.assertEquals("Number of students: " , 2, uni.getStudents().size()); 

      storyboard.add("step 2: add support for path navigation\n      call ");

      storyboard.markCodeStart();
      int sum = albert.getUni().getRooms().getCredits().sum();
      storyboard.addCode("examples");

      storyboard.add(
         "      shall compute to 88\n" +
               "      Path classes are generated.", 
               DONE, "zuendorf joern alex", "19.05.2012 20:40:42", 1, 0);

      // Assert.assertEquals("credits sum error", 88, sum);

      storyboard.add( "Feature Request: model sets need to provide a navigation to any neighbors\n" +
            "e.g.: ModelSet any = ModelSet.startWith(albert).getAny(); ", 
            BACKLOG, "zuendorf", "19.05.2012 20:42:42", 0, 2);

      storyboard.add( "Feature Request (DONE): model sets need to provide set methods and other methods. These methods shall be forwarded to each set member. \n" +
            "e.g.: room.getNeighbors().findPath(path, motivation); ", 
            DONE, "zuendorf", "19.08.2012 23:04:42", 4, 0);

      // storyboard.assertEquals("Number of neighbors for Albert is now ", 2, any.size());

      storyboard.add("step 3: call ");

      storyboard.recordSystemOut();

      storyboard.markCodeStart();
      mathRoom.findPath("", 88);
      storyboard.addCode("examples");

      storyboard.add("System.out: \n" + storyboard.getSystemOut());

      storyboard.dumpHTML();
   }


   @Test
   public void testBidirectionalModelToTextTransformation()
   {
      Storyboard storyboard = new Storyboard("examples");


      //=============================================================
      storyboard.addStep("We start with the usual StudyRight object model.");

      University uni = new University()
      .withName("StudyRight");

      Student albert = uni.createStudents()
            .withMatrNo(4242)
            .withName("Tom");

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
            .withNeighbors(mathRoom, artsRoom); 

      Room examRoom = uni.createRooms()
            .withRoomNo("exam")
            .withCredits(0)
            .withNeighbors(sportsRoom, artsRoom);

      Room progMeth = uni.createRooms()
            .withRoomNo("ProgMeth")
            .withCredits(42)
            .withNeighbors(artsRoom, examRoom);

      storyboard.addObjectDiagram(uni);


      //=============================================================
      storyboard.addStep("Use text templates to generate a natural language description of the object model.");

      storyboard.markCodeStart();

      Template rootTemplate = new Template()
      .withModelObject(uni)
      .with("The example University has 99 rooms and 88 students: \nroomList The students are: \nstudentList ",
         "example", University.PROPERTY_NAME, 
         "99", University.PROPERTY_ROOMS +  ".size", 
         "88", University.PROPERTY_STUDENTS + ".size");

      Template roomTemplate = rootTemplate.createPlaceHolderAndSubTemplate()
            .withReferenceLookup(true)
            .withParent("roomList", University.PROPERTY_ROOMS)
            .with(
               " - The xy room has 42 credits. It is connected to rooms: neighbors",
               "xy", Room.PROPERTY_ROOMNO,
               "42", Room.PROPERTY_CREDITS)
               .withList("", "\n", "\n");

      Template neighborsTemplate = roomTemplate.createPlaceHolderAndSubTemplate()
            .withParent("neighbors", Room.PROPERTY_NEIGHBORS)
            .withReferenceLookup(true)
            .with(
               "name",
               "name", Room.PROPERTY_ROOMNO)
               .withList("", ", ", ".");
      
      Template studentTemplate = rootTemplate.createPlaceHolderAndSubTemplate()
            .withParent("studentList", University.PROPERTY_STUDENTS)
            .with(
               " - Stud has student number 1234.",
               "Stud", Student.PROPERTY_NAME,
               "1234", Student.PROPERTY_MATRNO)
               .withList("", "\n", "\n");

      storyboard.addObjectDiagram(rootTemplate);

      rootTemplate.generate();

      storyboard.addCode("examples");

      storyboard.add("Results in the following text:");
      storyboard.add("<pre>" + rootTemplate.getExpandedText() + "</pre>");

      storyboard.addStep("Use templates to parse text into object model");
      
      storyboard.markCodeStart();

      rootTemplate.setExpandedText(
         "The Study False University has many rooms and some students: \n" + 
               " - The class diagrams room has 23 credits. It is connected to rooms: laws, business.\n" + 
               " - The laws room has 24 credits. It is connected to rooms: class diagrams, business.\n" + 
               " - The business room has 3 credits. It is connected to rooms: laws, class diagrams.\n " + 
               "The students are: \n" + 
               " - Bart has student number 111.\n" + 
               " - Meggie has student number 112.\n ");

      rootTemplate.parse();
      
      storyboard.addCode();
      
      storyboard.addObjectDiagram(rootTemplate.getModelObject());

      storyboard.addLogEntry(SDMLibConfig.DONE, "zuendorf", "11.11.2013 18:06:42 EST", 40, 0, "Using attribute names for parsing and generation");

      storyboard.addLogEntry(SDMLibConfig.DONE, "zuendorf", "23.02.2014 18:06:42 EST", 2, 0, "Reenabled parsing with references");
      
      storyboard.dumpHTML();
   }



   public static final String MODELING = "modeling";
   public static final String ACTIVE = "active";
   public static final String DONE = "done";
   public static final String IMPLEMENTATION = "implementation";
   public static final String BACKLOG = "backlog";
   public static final String BUG = "bug";
}
