package org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.Cargo;

public class CargoPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new CargoPO(new Cargo[]{});
      } else {
          return new CargoPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.reachabilitygraphs.lazyferrymansproblem.util.CreatorCreator.createIdMap(sessionID);
   }
}
