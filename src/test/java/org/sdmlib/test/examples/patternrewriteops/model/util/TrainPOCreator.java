package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.patternrewriteops.model.Train;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.patternrewriteops.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
