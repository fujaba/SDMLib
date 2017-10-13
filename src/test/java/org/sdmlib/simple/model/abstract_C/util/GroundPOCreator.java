package org.sdmlib.simple.model.abstract_C.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.abstract_C.Ground;

public class GroundPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GroundPO(new Ground[]{});
      } else {
          return new GroundPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.abstract_C.util.CreatorCreator.createIdMap(sessionID);
   }
}
