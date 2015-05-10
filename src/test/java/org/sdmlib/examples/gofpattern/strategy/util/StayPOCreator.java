package org.sdmlib.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.gofpattern.strategy.Stay;

public class StayPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new StayPO(new Stay[]{});
      } else {
          return new StayPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.gofpattern.strategy.util.CreatorCreator.createIdMap(sessionID);
   }
}
