package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.examples.patternrewriteops.model.Train;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class TrainPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TrainPO(new Train[]{});
      } else {
          return new TrainPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

