package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.CloneOp;

import de.uniks.networkparser.json.JsonIdMap;

public class CloneOpPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new CloneOpPO(new CloneOp[]{});
      } else {
         return new CloneOpPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

