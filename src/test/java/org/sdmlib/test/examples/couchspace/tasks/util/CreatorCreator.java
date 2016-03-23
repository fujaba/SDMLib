package org.sdmlib.test.examples.couchspace.tasks.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new TaskFlowCreator());
      jsonIdMap.with(new TaskFlowPOCreator());
      jsonIdMap.with(new TaskCreator());
      jsonIdMap.with(new TaskPOCreator());
      jsonIdMap.with(new UserGroupCreator());
      jsonIdMap.with(new UserGroupPOCreator());
      jsonIdMap.with(new UserCreator());
      jsonIdMap.with(new UserPOCreator());
      return jsonIdMap;
   }
}
