package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.tables.Row;

import de.uniks.networkparser.IdMap;

public class RowPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new RowPO(new Row[]{});
      } else {
          return new RowPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.tables.util.CreatorCreator.createIdMap(sessionID);
   }
}
