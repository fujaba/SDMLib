package de.kassel.roombook.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.kassel.roombook.Floor;
import de.uniks.networkparser.json.JsonIdMap;

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
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
