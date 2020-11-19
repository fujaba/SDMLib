package org.sdmlib.test.examples.studyrightWithAssignments.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new AssignmentCreator());
      jsonIdMap.with(new AssignmentPOCreator());
      jsonIdMap.with(new PresidentCreator());
      jsonIdMap.with(new PresidentPOCreator());
      jsonIdMap.with(new RoomCreator());
      jsonIdMap.with(new RoomPOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      jsonIdMap.with(new TeachingAssistantCreator());
      jsonIdMap.with(new TeachingAssistantPOCreator());
      jsonIdMap.with(new UniversityCreator());
      jsonIdMap.with(new UniversityPOCreator());
      return jsonIdMap;
   }
}
