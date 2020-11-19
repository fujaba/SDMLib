package de.uniks.se1.ss18.teamb.UoM.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.se1.ss18.teamb.UoM.model.Game;

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
      return de.uniks.se1.ss18.teamb.UoM.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
