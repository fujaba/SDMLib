package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.examples.mancala.model.Kalah;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class KalahPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new KalahPO(new Kalah[]{});
      } else {
          return new KalahPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.mancala.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
