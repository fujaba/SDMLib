package org.sdmlib.simple.model.abstract_C.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.simple.model.abstract_C.Game;

import de.uniks.networkparser.IdMap;

public class GamePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GamePO(new Game[]{});
      } else {
          return new GamePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.abstract_C.util.CreatorCreator.createIdMap(sessionID);
   }
}
