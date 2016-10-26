package org.sdmlib.simple.model.abstract_C.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.abstract_C.Grass;

public class GrassPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GrassPO(new Grass[]{});
      } else {
          return new GrassPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.abstract_C.util.CreatorCreator.createIdMap(sessionID);
   }
}
