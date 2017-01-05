package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.tables.Table;

import de.uniks.networkparser.IdMap;

public class TablePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TablePO(new Table[]{});
      } else {
          return new TablePO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.tables.util.CreatorCreator.createIdMap(sessionID);
   }
}
