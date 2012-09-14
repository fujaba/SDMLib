package org.sdmlib.models.transformations.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
     
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.TransformOpCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.OperationObjectCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.AttributeOpCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.LinkOpCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.transformations.creators.StatementCreator());
      return jsonIdMap;
   }
}













































