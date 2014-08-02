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
      jsonIdMap.withCreator(new org.sdmlib.models.classes.util.SDMLibClassCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.util.SDMLibClassPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.util.ValueCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.util.ValuePOCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.util.SymTabEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.util.SymTabEntryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.util.LocalVarTableEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.util.LocalVarTableEntryPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.util.StatementEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.util.StatementEntryPOCreator());
      return jsonIdMap;
   }
}
