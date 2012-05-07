package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.serialization.json.JsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.model.test.superclasses.creators.ContinentCreator());
      jsonIdMap.addCreator(new org.sdmlib.model.test.superclasses.creators.StateCreator());
      jsonIdMap.addCreator(new org.sdmlib.model.test.superclasses.creators.TownCreator());
      jsonIdMap.addCreator(new org.sdmlib.model.classes.creators.ReverseClassModelTestCreator());
      return jsonIdMap;
   }
}

