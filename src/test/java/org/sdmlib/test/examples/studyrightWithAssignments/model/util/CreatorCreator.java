package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new UniversityCreator());
      jsonIdMap.with(new UniversityPOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      jsonIdMap.with(new RoomCreator());
      jsonIdMap.with(new RoomPOCreator());
      jsonIdMap.with(new AssignmentCreator());
      jsonIdMap.with(new AssignmentPOCreator());
      jsonIdMap.with(new TeachingAssistantCreator());
      jsonIdMap.with(new TeachingAssistantPOCreator());
      return jsonIdMap;
   }
}
