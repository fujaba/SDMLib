package org.sdmlib.simple.model.association_c.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.simple.model.association_c.Room;

public class RoomPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new RoomPO(new Room[]{});
      } else {
          return new RoomPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.association_c.util.CreatorCreator.createIdMap(sessionID);
   }
}
