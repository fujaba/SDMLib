package org.sdmlib.serialization.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.IdMap;

public class SDMLibJsonIdMapPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new SDMLibJsonIdMapPO(new SDMLibJsonIdMap[]{});
      } else {
         return new SDMLibJsonIdMapPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

