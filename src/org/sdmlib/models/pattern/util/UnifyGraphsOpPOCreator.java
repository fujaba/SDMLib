package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.UnifyGraphsOp;

import de.uniks.networkparser.json.JsonIdMap;

public class UnifyGraphsOpPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new UnifyGraphsOpPO(new UnifyGraphsOp[]{});
      } else {
         return new UnifyGraphsOpPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

