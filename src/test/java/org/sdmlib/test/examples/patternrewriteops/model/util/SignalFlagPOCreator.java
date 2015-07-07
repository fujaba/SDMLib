package org.sdmlib.test.examples.patternrewriteops.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.patternrewriteops.model.SignalFlag;

import de.uniks.networkparser.json.JsonIdMap;

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
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

