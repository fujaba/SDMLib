package org.sdmlib.examples.studyrightWithAssignments.model.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

public class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.UniversityCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.UniversityPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.StudentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.StudentPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.RoomCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.RoomPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.AssignmentCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.AssignmentPOCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.TeachingAssistantCreator());
      jsonIdMap.withCreator(new org.sdmlib.examples.studyrightWithAssignments.model.util.TeachingAssistantPOCreator());

      return jsonIdMap;
   }
}
