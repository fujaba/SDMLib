package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.examples.adamandeve.model.UpdateAdamFlow;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class UpdateAdamFlowPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new UpdateAdamFlowPO(new UpdateAdamFlow[]{});
      } else {
          return new UpdateAdamFlowPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
