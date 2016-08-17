package org.sdmlib.simple.model.enums_d.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;

public class IntegerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new IntegerPO(new Integer[]{});
      } else {
          return new IntegerPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.enums_d.util.CreatorCreator.createIdMap(sessionID);
   }
}
