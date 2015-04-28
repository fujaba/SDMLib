package org.sdmlib.examples.features.model.all.util;

import org.sdmlib.examples.features.model.all.Door;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

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
      return org.sdmlib.examples.features.model.all.util.CreatorCreator.createIdMap(sessionID);
   }
}
