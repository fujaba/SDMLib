package org.sdmlib.models.tables.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new TableCreator());
      jsonIdMap.with(new TablePOCreator());
      jsonIdMap.with(new RowCreator());
      jsonIdMap.with(new RowPOCreator());
      jsonIdMap.with(new ColumnCreator());
      jsonIdMap.with(new ColumnPOCreator());
      jsonIdMap.with(new CellCreator());
      jsonIdMap.with(new CellPOCreator());
      return jsonIdMap;
   }
}
