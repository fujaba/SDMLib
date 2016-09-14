package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.PatternElement;

public class PatternElementPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new PatternElementPO(new PatternElement[]{});
      } else {
          return new PatternElementPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
