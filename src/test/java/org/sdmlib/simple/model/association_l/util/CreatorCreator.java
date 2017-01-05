package org.sdmlib.simple.model.association_l.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new LectureCreator());
      jsonIdMap.with(new LecturePOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      jsonIdMap.with(new UniversityCreator());
      jsonIdMap.with(new UniversityPOCreator());
      return jsonIdMap;
   }
}
