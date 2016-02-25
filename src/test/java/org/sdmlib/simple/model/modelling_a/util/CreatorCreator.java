package org.sdmlib.simple.model.modelling_a.util;

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
      jsonIdMap.with(new RoomCreator());
      jsonIdMap.with(new RoomPOCreator());
      jsonIdMap.with(new roomInterfaceCreator());
      jsonIdMap.with(new roomInterfacePOCreator());
      jsonIdMap.with(new IntegerCreator());
      jsonIdMap.with(new IntegerPOCreator());
      return jsonIdMap;
   }
}
