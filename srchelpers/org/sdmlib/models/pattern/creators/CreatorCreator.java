package org.sdmlib.models.pattern.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.PatternCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.PatternElementCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.PatternObjectCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.PatternLinkCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.AttributeConstraintCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.LinkConstraintCreator());
      jsonIdMap.addCreator(new org.sdmlib.models.pattern.creators.NegativeApplicationConditionCreator());
      return jsonIdMap;
   }
}





