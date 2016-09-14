package org.sdmlib.models.pattern.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.models.pattern.CloneOp;

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
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.models.pattern.util.CreatorCreator.createIdMap(sessionID);
   }
}
