package org.sdmlib.test.examples.mancala.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.mancala.model.Mancala;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.mancala.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
