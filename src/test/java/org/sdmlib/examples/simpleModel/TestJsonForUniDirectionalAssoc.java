package org.sdmlib.examples.simpleModel;

import org.junit.Test;
import org.sdmlib.examples.simpleModel.model.BigBrother;
import org.sdmlib.examples.simpleModel.model.Person;
import org.sdmlib.examples.simpleModel.model.util.BigBrotherCreator;
import org.sdmlib.examples.studyright.model.util.UniversityCreator;
import org.sdmlib.models.debug.FlipBook;
import org.sdmlib.serialization.SDMLibJsonIdMap;
import org.sdmlib.storyboards.Storyboard;

public class TestJsonForUniDirectionalAssoc
{
   @Test
   public void testUniDirectionalAssocJson()
   {
      Storyboard story = new Storyboard();

      //=============================================================
      story.add("adding flipbook to protocol changes.");
      
      story.markCodeStart();
      SDMLibJsonIdMap idMap = (SDMLibJsonIdMap) BigBrotherCreator.createIdMap("#");
      FlipBook flipBook = idMap.createFlipBook();
      
      BigBrother bigBrother = new BigBrother();
      idMap.put("#bb", bigBrother);
      story.addCode();

      story.withMap(idMap);
      
      //=============================================================
      story.add("extend uni directional assco with objects and check flipbook for change protocol");
      
      Person albert = bigBrother.createNoOne().withName("Albert");
      Person sabine = bigBrother.createSuspects().withName("Sabine");

      Person tom = new Person ().withName("Tom");
      Person jessi = new Person ().withName("Jessi");
      bigBrother.withKids(tom, jessi);
      
      jessi.withName("Jessica");
      
      story.addObjectDiagram(bigBrother);
      

      story.dumpHTML();
   }
}
