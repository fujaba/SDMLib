package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;

public class SignalFlagPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SignalFlagPO(new SignalFlag[]{});
      } else {
          return new SignalFlagPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.patternrewriteops.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
