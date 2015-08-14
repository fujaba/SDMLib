package org.sdmlib.models.objects.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);

      jsonIdMap.withCreator(new GenericGraphCreator());
      jsonIdMap.withCreator(new GenericGraphPOCreator());
      jsonIdMap.withCreator(new GenericObjectCreator());
      jsonIdMap.withCreator(new GenericObjectPOCreator());
      jsonIdMap.withCreator(new GenericAttributeCreator());
      jsonIdMap.withCreator(new GenericAttributePOCreator());
      jsonIdMap.withCreator(new GenericLinkCreator());
      jsonIdMap.withCreator(new GenericLinkPOCreator());
      jsonIdMap.withCreator(new GenericGraphCreator());
      jsonIdMap.withCreator(new GenericGraphPOCreator());
      jsonIdMap.withCreator(new GenericObjectCreator());
      jsonIdMap.withCreator(new GenericObjectPOCreator());
      jsonIdMap.withCreator(new GenericAttributeCreator());
      jsonIdMap.withCreator(new GenericAttributePOCreator());
      jsonIdMap.withCreator(new GenericLinkCreator());
      jsonIdMap.withCreator(new GenericLinkPOCreator());
      jsonIdMap.withCreator(new GenericGraphCreator());
      jsonIdMap.withCreator(new GenericGraphPOCreator());
      jsonIdMap.withCreator(new GenericObjectCreator());
      jsonIdMap.withCreator(new GenericObjectPOCreator());
      jsonIdMap.withCreator(new GenericAttributeCreator());
      jsonIdMap.withCreator(new GenericAttributePOCreator());
      jsonIdMap.withCreator(new GenericLinkCreator());
      jsonIdMap.withCreator(new GenericLinkPOCreator());
      jsonIdMap.withCreator(new GenericGraphCreator());
      jsonIdMap.withCreator(new GenericGraphPOCreator());
      jsonIdMap.withCreator(new GenericObjectCreator());
      jsonIdMap.withCreator(new GenericObjectPOCreator());
      jsonIdMap.withCreator(new GenericAttributeCreator());
      jsonIdMap.withCreator(new GenericAttributePOCreator());
      jsonIdMap.withCreator(new GenericLinkCreator());
      jsonIdMap.withCreator(new GenericLinkPOCreator());
      jsonIdMap.withCreator(new GenericGraphCreator());
      jsonIdMap.withCreator(new GenericGraphPOCreator());
      jsonIdMap.withCreator(new GenericObjectCreator());
      jsonIdMap.withCreator(new GenericObjectPOCreator());
      jsonIdMap.withCreator(new GenericAttributeCreator());
      jsonIdMap.withCreator(new GenericAttributePOCreator());
      jsonIdMap.withCreator(new GenericLinkCreator());
      jsonIdMap.withCreator(new GenericLinkPOCreator());
      return jsonIdMap;
   }
}
