package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.json.JsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.TransformOpCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.OperationObjectCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.StatementCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.TransformOpCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.OperationObjectCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.StatementCreator());
      return jsonIdMap;
   }
}


