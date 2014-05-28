package org.sdmlib.examples.studyright.model.util;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.serialization.SDMLibJsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.StudentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.StudentPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.AssignmentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.AssignmentPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.UniversityCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.UniversityPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.RoomCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyright.model.util.RoomPOCreator());

      return jsonIdMap;
   }
}

