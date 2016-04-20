package org.sdmlib.models.classes.util;

import org.sdmlib.codegen.util.LocalVarTableEntryCreator;
import org.sdmlib.codegen.util.StatementEntryCreator;
import org.sdmlib.codegen.util.SymTabEntryCreator;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new ClassModelCreator());
      jsonIdMap.with(new SymTabEntryCreator());
      jsonIdMap.with(new LocalVarTableEntryCreator());
      jsonIdMap.with(new StatementEntryCreator());

      return jsonIdMap;
   }
}
