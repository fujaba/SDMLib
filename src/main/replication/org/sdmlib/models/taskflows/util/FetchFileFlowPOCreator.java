package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.taskflows.FetchFileFlow;

import de.uniks.networkparser.json.JsonIdMap;

public class FetchFileFlowPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new FetchFileFlowPO(new FetchFileFlow[]{});
      } else {
          return new FetchFileFlowPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
