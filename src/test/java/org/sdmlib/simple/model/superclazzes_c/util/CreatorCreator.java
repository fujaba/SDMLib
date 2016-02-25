package org.sdmlib.simple.model.superclazzes_c.util;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = new JsonIdMap().withSessionId(sessionID);
      jsonIdMap.with(new PersonCreator());
      jsonIdMap.with(new PersonPOCreator());
      jsonIdMap.with(new PupilCreator());
      jsonIdMap.with(new PupilPOCreator());
      jsonIdMap.with(new TeacherCreator());
      jsonIdMap.with(new TeacherPOCreator());
      return jsonIdMap;
   }
}
