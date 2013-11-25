package de.kassel.roombook.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new de.kassel.roombook.creators.BuildingCreator());
      jsonIdMap.withCreator(new de.kassel.roombook.creators.FloorCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.creators.GenericObjectsTestCreator());
      return jsonIdMap;
   }
}

