package org.sdmlib.simple.model.superclazzes_d.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new TeacherCreator());
      jsonIdMap.with(new TeacherPOCreator());
      jsonIdMap.with(new PupilCreator());
      jsonIdMap.with(new PupilPOCreator());
      return jsonIdMap;
   }
}
