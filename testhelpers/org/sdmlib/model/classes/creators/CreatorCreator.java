package org.sdmlib.model.classes.creators;

import org.sdmlib.serialization.json.JsonIdMap;

public class CreatorCreator
{
   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.addCreator(new org.sdmlib.model.classes.creators.ReverseClassModelTestCreator());
      jsonIdMap.addCreator(new org.sdmlib.model.test.methods.creators.PlaceCreator());
      jsonIdMap.addCreator(new org.sdmlib.model.classes.creators.ReverseClassModelTestCreator());
      jsonIdMap.addCreator(new org.sdmlib.model.test.interfaces.creators.StudentCreator());
      return jsonIdMap;
   }
}


