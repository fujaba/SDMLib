package org.sdmlib.test.examples.studyright;

import org.junit.Test;
import org.sdmlib.models.debug.FlipBook;
import org.sdmlib.models.transformations.Template;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.studyright.model.Room;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.test.examples.studyright.model.University;
import org.sdmlib.test.examples.studyright.model.util.UniversityCreator;

import de.uniks.networkparser.ext.story.Story;

public class StudyRightStoryboards
{
   /**
    * @see <a href='../../../../../../../../doc/StudyRightObjectStoryboards.html'>StudyRightObjectStoryboards.html</a>
    */
   @Test
   public void testStudyRightObjectStoryboards()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
      storyboard.add("Start situation: use University class to build object structure");

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

      // ===============================================================================================
      storyboard.assertEquals("Number of students: ", 2, uni.getStudents().size());

      storyboard.add("step 2: add support for path navigation\n      call ");

      storyboard.markCodeStart();
      int sum = albert.getUni().getRooms().getCredits().sum();
      storyboard.addCode();

      storyboard.add("shall compute to 88.");

      // Assert.assertEquals("credits sum error", 88, sum);

      // storyboard.assertEquals("Number of neighbors for Albert is now ", 2, any.size());

      storyboard.add("step 3: call ");

      storyboard.recordSystemOut();

      storyboard.markCodeStart();
      mathRoom.findPath("", 88);
      storyboard.addCode();

      storyboard.add("System.out: \n");

      storyboard.addSystemOut();

      storyboard.dumpHTML();
   }


   /**
    * @see <a href='../../../../../../../../doc/Flipbook.html'>Flipbook.html</a>
    */
   @Test
   public void testFlipbook()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
      storyboard.add("Adding flipbook to protocol changes.");
      
      
      storyboard.markCodeStart();
      SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) UniversityCreator.createIdMap("ajz");
      FlipBook flipBook = idMap.createFlipBook();

      // =============================================================
      storyboard.add("Start situation: use University class to build object structure");

      University uni = new University()
         .withName("StudyRight");
      idMap.getId(uni);
      
      storyboard.addCode();
      
      storyboard.add("From now on the flipbook protocols all changes done to the uni model");

      storyboard.add("Add rooms and students and dump the object model.");

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

      Room progMeth = uni.createRooms()
            .withRoomNo("ProgMeth")
            .withCredits(42)
            .withNeighbors(artsRoom);

      progMeth.withNeighbors(examRoom);

      storyboard.addObjectDiagram(uni);
      
      storyboard.assertEquals("Number of students expected: ", 2, uni.getStudents().size());
      storyboard.assertEquals("Number of rooms expected: ", 5, uni.getRooms().size());


      // ===============================================================================================
      storyboard.add("Test flipbook, with some undo redo steps: ");

      storyboard.markCodeStart();
      flipBook.back(42);
      storyboard.addObjectDiagram(uni);
      flipBook.forward(42);
      storyboard.addObjectDiagram(uni);
      flipBook.back(sportsRoom, Room.PROPERTY_CREDITS);
      storyboard.addObjectDiagram(uni);
      flipBook.back()
         .back()
         .back()
         .forward();

      storyboard.addCode();
      
      storyboard.addObjectDiagram(uni);

      // ===============================================================================================
      storyboard.assertEquals("Number of students expected: ", 2, uni.getStudents().size());
      storyboard.assertEquals("Number of rooms expected: ", 3, uni.getRooms().size());

      storyboard.dumpHTML();
   }


   /**
    * 
    * @see <a href='../../../../../../../../doc/BidirectionalModelToTextTransformation.html'>BidirectionalModelToTextTransformation.html</a>
    * @see <a href='../../../../../../../../doc/BidirectionalModelToTextTransformation.html'>BidirectionalModelToTextTransformation.html</a>
    */
   @Test
   public void testBidirectionalModelToTextTransformation()
   {
      Storyboard storyboard = new Storyboard();

      // =============================================================
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

      // =============================================================
      storyboard.addStep("Use text templates to generate a natural language description of the object model.");

      storyboard.markCodeStart();

      Template rootTemplate = new Template()
         .withModelObject(uni)
         .with("The example University has 99 rooms and 88 students: \nroomList The students are: \nstudentList ",
            "example", University.PROPERTY_NAME,
            "99", University.PROPERTY_ROOMS + ".size",
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

      storyboard.addCode();

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

      storyboard.dumpHTML();
   }

   public static final String MODELING = "modeling";

   public static final String ACTIVE = "active";

   public static final String DONE = "done";

   public static final String IMPLEMENTATION = "implementation";

   public static final String BACKLOG = "backlog";

   public static final String BUG = "bug";
}
