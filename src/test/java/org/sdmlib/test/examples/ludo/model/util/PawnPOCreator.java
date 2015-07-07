package org.sdmlib.test.examples.ludo.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.ludo.model.Pawn;

import de.uniks.networkparser.json.JsonIdMap;

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
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
