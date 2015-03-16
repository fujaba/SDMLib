package org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.Boat;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class BoatPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BoatPO(new Boat[]{});
      } else {
          return new BoatPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
