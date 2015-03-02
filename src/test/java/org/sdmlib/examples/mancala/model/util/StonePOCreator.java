package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.examples.mancala.model.Stone;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class StonePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new StonePO(new Stone[]{});
      } else {
          return new StonePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.mancala.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
