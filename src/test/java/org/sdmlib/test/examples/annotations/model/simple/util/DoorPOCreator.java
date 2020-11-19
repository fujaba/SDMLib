package org.sdmlib.test.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.annotations.model.simple.Door;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.annotations.model.simple.util.CreatorCreator.createIdMap(sessionID);
   }
}
