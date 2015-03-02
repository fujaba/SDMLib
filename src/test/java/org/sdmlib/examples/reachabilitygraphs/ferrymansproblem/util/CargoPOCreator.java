package org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util;

import org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.Cargo;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class CargoPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new CargoPO(new Cargo[]{});
      } else {
          return new CargoPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.reachabilitygraphs.ferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
