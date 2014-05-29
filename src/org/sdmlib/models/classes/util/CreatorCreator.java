package org.sdmlib.models.classes.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new AssociationCreator());
      jsonIdMap.withCreator(new AssociationPOCreator());
      jsonIdMap.withCreator(new AttributeCreator());
      jsonIdMap.withCreator(new AttributePOCreator());
      jsonIdMap.withCreator(new ClassModelCreator());
      jsonIdMap.withCreator(new ClassModelPOCreator());
      jsonIdMap.withCreator(new ClazzCreator());
      jsonIdMap.withCreator(new ClazzPOCreator());
      jsonIdMap.withCreator(new MethodCreator());
      jsonIdMap.withCreator(new MethodPOCreator());
      jsonIdMap.withCreator(new ParameterCreator());
      jsonIdMap.withCreator(new ParameterPOCreator());
      jsonIdMap.withCreator(new RoleCreator());
      jsonIdMap.withCreator(new RolePOCreator());
      return jsonIdMap;
   }
}
