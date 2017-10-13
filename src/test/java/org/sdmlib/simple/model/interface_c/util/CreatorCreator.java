package org.sdmlib.simple.model.interface_c.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new SecretaryCreator());
      jsonIdMap.with(new SecretaryPOCreator());
      jsonIdMap.with(new TeacherCreator());
      jsonIdMap.with(new TeacherPOCreator());
      return jsonIdMap;
   }
}
