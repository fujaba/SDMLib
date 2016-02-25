package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.test.examples.annotations.model.simple.Door;

public class DoorPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new DoorPO(new Door[]{});
      } else {
          return new DoorPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.annotations.model.simple.util.CreatorCreator.createIdMap(sessionID);
   }
}
