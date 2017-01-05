package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.tables.Cell;

import de.uniks.networkparser.IdMap;

public class CellPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new CellPO(new Cell[]{});
      } else {
          return new CellPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.tables.util.CreatorCreator.createIdMap(sessionID);
   }
}
