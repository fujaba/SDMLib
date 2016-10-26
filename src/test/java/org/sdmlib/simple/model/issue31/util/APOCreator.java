package org.sdmlib.simple.model.issue31.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.issue31.A;

public class APOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new APO(new A[]{});
      } else {
          return new APO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.issue31.util.CreatorCreator.createIdMap(sessionID);
   }
}
