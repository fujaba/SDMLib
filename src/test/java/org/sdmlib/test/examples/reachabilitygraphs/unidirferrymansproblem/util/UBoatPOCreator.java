package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.UBoat;

import de.uniks.networkparser.IdMap;

public class UBoatPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UBoatPO(new UBoat[]{});
      } else {
          return new UBoatPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
