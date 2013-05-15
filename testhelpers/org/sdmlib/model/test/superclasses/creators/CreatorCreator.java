package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
	   JsonIdMap jsonIdMap = new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.model.test.superclasses.creators.ContinentCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.test.superclasses.creators.StateCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.test.superclasses.creators.TownCreator());
      jsonIdMap.withCreator(new org.sdmlib.model.classes.creators.ReverseClassModelTestCreator());
      return jsonIdMap;
   }
}

