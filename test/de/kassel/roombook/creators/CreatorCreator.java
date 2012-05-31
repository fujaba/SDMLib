package de.kassel.roombook.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new de.kassel.roombook.creators.BuildingCreator());
      jsonIdMap.addCreator(new de.kassel.roombook.creators.FloorCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.objects.creators.GenericObjectsTestCreator());
      return jsonIdMap;
   }
}

