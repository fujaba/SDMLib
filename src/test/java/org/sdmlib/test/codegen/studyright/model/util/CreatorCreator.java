package org.sdmlib.test.codegen.studyright.model.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new UniversityCreator());
      jsonIdMap.with(new UniversityPOCreator());
      jsonIdMap.with(new StudentCreator());
      jsonIdMap.with(new StudentPOCreator());
      return jsonIdMap;
   }
}
