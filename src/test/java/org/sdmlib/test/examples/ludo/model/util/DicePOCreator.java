package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.ludo.model.Dice;

import de.uniks.networkparser.IdMap;

public class DicePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new DicePO(new Dice[]{});
      } else {
          return new DicePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
