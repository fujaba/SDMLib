package org.sdmlib.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.gofpattern.strategy.MoveLeft;

public class MoveLeftPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MoveLeftPO(new MoveLeft[]{});
      } else {
          return new MoveLeftPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.gofpattern.strategy.util.CreatorCreator.createIdMap(sessionID);
   }
}
