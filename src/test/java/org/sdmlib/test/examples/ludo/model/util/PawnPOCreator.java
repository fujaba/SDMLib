package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.ludo.model.Pawn;

import de.uniks.networkparser.IdMap;

public class PawnPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PawnPO(new Pawn[]{});
      } else {
          return new PawnPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
