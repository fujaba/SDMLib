package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.models.tables.Column;

import de.uniks.networkparser.IdMap;

public class ColumnPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ColumnPO(new Column[]{});
      } else {
          return new ColumnPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.tables.util.CreatorCreator.createIdMap(sessionID);
   }
}
