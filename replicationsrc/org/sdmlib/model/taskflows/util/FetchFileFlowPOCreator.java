package org.sdmlib.model.taskflows.util;

import org.sdmlib.model.taskflows.FetchFileFlow;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

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
