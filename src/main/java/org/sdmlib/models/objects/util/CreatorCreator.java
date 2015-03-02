package org.sdmlib.models.objects.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericGraphCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericGraphPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericObjectCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericObjectPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericAttributeCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericAttributePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericLinkCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.objects.util.GenericLinkPOCreator());

      return jsonIdMap;
   }
}
