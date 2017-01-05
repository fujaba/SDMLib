package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.Pattern;

import de.uniks.networkparser.IdMap;

public class PatternPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PatternPO(new Pattern[]{});
      } else {
          return new PatternPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
