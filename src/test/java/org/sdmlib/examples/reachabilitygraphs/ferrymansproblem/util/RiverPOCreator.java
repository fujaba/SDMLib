package org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.River;

public class RiverPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new RiverPO(new River[]{});
      } else {
          return new RiverPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
