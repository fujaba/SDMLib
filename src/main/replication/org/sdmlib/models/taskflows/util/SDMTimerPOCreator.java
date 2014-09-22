package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.taskflows.SDMTimer;

import de.uniks.networkparser.json.JsonIdMap;

public class SDMTimerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SDMTimerPO(new SDMTimer[]{});
      } else {
          return new SDMTimerPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
