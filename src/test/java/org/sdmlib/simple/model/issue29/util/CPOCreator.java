package org.sdmlib.simple.model.issue29.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.issue29.C;

public class CPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new CPO(new C[]{});
      } else {
          return new CPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.issue29.util.CreatorCreator.createIdMap(sessionID);
   }
}
