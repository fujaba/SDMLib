package org.sdmlib.examples.studyright.util;

import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.util.AssignmentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.util.AssignmentPOCreator());

      return jsonIdMap;
   }
}

