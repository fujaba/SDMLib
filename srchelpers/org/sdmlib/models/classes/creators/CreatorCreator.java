package org.sdmlib.models.classes.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
  	  JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.models.classes.creators.ClassModelCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.creators.ClazzCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.creators.AttributeCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.creators.MethodCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.creators.AssociationCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.classes.creators.RoleCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.creators.SymTabEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.creators.LocalVarTableEntryCreator());
      jsonIdMap.withCreator(new org.sdmlib.codegen.creators.StatementEntryCreator());
      return jsonIdMap;
   }
}




