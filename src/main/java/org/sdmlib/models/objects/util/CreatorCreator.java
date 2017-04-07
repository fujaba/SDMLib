package org.sdmlib.models.objects.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);

      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      jsonIdMap.with(new GenericGraphCreator());
      jsonIdMap.with(new GenericGraphPOCreator());
      jsonIdMap.with(new GenericObjectCreator());
      jsonIdMap.with(new GenericObjectPOCreator());
      jsonIdMap.with(new GenericAttributeCreator());
      jsonIdMap.with(new GenericAttributePOCreator());
      jsonIdMap.with(new GenericLinkCreator());
      jsonIdMap.with(new GenericLinkPOCreator());
      return jsonIdMap;
   }
}
