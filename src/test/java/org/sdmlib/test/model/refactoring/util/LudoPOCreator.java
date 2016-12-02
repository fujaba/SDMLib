package org.sdmlib.test.model.refactoring.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.model.refactoring.Ludo;

import de.uniks.networkparser.IdMap;

public class LudoPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LudoPO(new Ludo[]{});
      } else {
          return new LudoPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.model.refactoring.util.CreatorCreator.createIdMap(sessionID);
   }
}
