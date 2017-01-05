package org.sdmlib.test.examples.gofpattern.strategy.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.gofpattern.strategy.BombermanPlayer;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.gofpattern.strategy.util.CreatorCreator.createIdMap(sessionID);
   }
}
