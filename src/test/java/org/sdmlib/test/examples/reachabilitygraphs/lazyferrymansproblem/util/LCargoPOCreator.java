package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.LCargo;

public class LCargoPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new LCargoPO(new LCargo[]{});
      } else {
          return new LCargoPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
