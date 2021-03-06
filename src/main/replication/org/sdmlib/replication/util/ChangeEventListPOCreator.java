package org.sdmlib.replication.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.replication.ChangeEventList;

import de.uniks.networkparser.IdMap;

public class ChangeEventListPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ChangeEventListPO(new ChangeEventList[]{});
      } else {
          return new ChangeEventListPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.replication.util.CreatorCreator.createIdMap(sessionID);
   }
}
