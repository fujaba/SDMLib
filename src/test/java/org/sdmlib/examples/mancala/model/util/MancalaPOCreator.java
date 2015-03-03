package org.sdmlib.examples.mancala.model.util;

import org.sdmlib.examples.mancala.model.Mancala;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class MancalaPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MancalaPO(new Mancala[]{});
      } else {
          return new MancalaPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.mancala.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
