package de.kassel.test.roombook.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.kassel.test.roombook.Floor;
import de.uniks.networkparser.IdMap;

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
      return CreatorCreator.createIdMap(sessionID);
   }
}
