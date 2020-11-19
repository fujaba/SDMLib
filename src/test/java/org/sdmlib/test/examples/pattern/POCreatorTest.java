package org.sdmlib.test.examples.pattern;

import org.junit.Test;

public class POCreatorTest
{

   @Test
   public void testPOCreator()
   {
//      // Storyboard storyboard = new Storyboard();
//      University university = new University();
//      Room mathRoom = university.withName("UniKasselVersitaet").createRooms().withCredits(6).withName("Math");
//      mathRoom.createStudents().withName("Jane Doe");
//
//      // storyboard.addObjectDiagram(university);
//
//      POCreator docDataPOCreator = new POCreator();
//      PatternObject patternObject = docDataPOCreator
//         .createPO(university);
//
//      // storyboard.dumpDiagram(patternObject, "POTest");
//
//      patternObject.rebind(university);
//      assertTrue(patternObject.isHasMatch());
//      assertEquals(3, patternObject.getPattern().getPatternObjectCount());
//
//      // storyboard.dumpHTML();
   }

     /**
    *
    * @see <a href='../../../../../../../../doc/POCreatorLargeModel.html'>POCreatorLargeModel.html</a>
 */
   @Test
   public void testPOCreatorLargeModel()
   {
//      StoryboardImpl storyboard = new StoryboardImpl();
//      storyboard.addStep("At first we instanciate a Model");
//
//      storyboard.markCodeStart();
//      University university = new University().withName("StudyRight University");
//      Room mathRoom = university.createRooms().withCredits(6).withName("Multiply").withTopic("Math");
//      Student joan = mathRoom.createStudents().withName("Jane Doe");
//      Student peter = mathRoom.createStudentsTeachingAssistant().withName("Peter Teacher").withIn(mathRoom);
//      mathRoom.createAssignments().withContent("1*1").withPoints(2).withStudents(joan);
//      university.withStudents(joan, peter);
//      storyboard.addCode();
//
//      storyboard.addObjectDiagram(university);
//
//      storyboard.addStep("Now the corresponding Pattern is generated");
//      storyboard.markCodeStart();
//      POCreator poCreator = new POCreator();
//      PatternObject po = poCreator
//         .createPO(university);
//      storyboard.addCode();
//
//      storyboard.addStep("Now we want to rebind the patternObject to the University model and look for the matches");
//      storyboard.markCodeStart();
//      po.rebind(university);
//      storyboard.addCode();
//
//      storyboard.addPattern(po.getPattern(), true);
//      storyboard.assertTrue("The PatternObject has a match", po.isHasMatch());
//      storyboard.assertEquals("The University is the Match of the PatternObject", university, po.getCurrentMatch());
//      storyboard.assertEquals(
//         "The Pattern contains as many PatternObjects as Objects were created in the University Model", 5, po
//            .getPattern().getPatternObjectCount());
//      storyboard.dumpHTML();
   }
}
