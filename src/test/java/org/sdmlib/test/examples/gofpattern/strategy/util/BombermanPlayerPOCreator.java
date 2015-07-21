package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanPlayer;

public class BombermanPlayerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BombermanPlayerPO(new BombermanPlayer[]{});
      } else {
          return new BombermanPlayerPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap(sessionID);
   }
}
