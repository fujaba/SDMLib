package org.sdmlib.codegen.util;

import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = (IdMap) new SDMLibJsonIdMap().withSession(session);
      jsonIdMap.with(new LocalVarTableEntryCreator());
      jsonIdMap.with(new LocalVarTableEntryPOCreator());
      jsonIdMap.with(new StatementEntryCreator());
      jsonIdMap.with(new StatementEntryPOCreator());
      jsonIdMap.with(new SymTabEntryCreator());
      jsonIdMap.with(new SymTabEntryPOCreator());
      return jsonIdMap;
   }
}
