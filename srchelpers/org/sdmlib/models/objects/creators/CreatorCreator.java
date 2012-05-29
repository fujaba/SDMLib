package org.sdmlib.models.objects.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.models.objects.creators.GenericObjectCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.objects.creators.GenericAttributeCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.objects.creators.GenericLinkCreator());
      return jsonIdMap;
   }
}

