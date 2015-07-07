package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

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

