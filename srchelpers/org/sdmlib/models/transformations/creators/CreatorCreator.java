package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
     
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.creators.TransformOpCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.creators.OperationObjectCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.creators.AttributeOpCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.creators.LinkOpCreator());
      jsonIdMap.withCreator(new org.sdmlib.models.transformations.creators.StatementCreator());
      return jsonIdMap;
   }
}













































