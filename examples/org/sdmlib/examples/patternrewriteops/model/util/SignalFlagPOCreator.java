package org.sdmlib.examples.patternrewriteops.model.util;

import org.sdmlib.examples.patternrewriteops.model.SignalFlag;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

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

