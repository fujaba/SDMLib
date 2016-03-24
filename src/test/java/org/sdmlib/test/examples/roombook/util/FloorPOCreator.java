package org.sdmlib.test.examples.roombook.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.test.examples.roombook.Floor;

public class FloorPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new FloorPO(new Floor[]{});
      } else {
          return new FloorPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.roombook.util.CreatorCreator.createIdMap(sessionID);
   }
}
