package org.sdmlib.simple.model.interface_c.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import org.sdmlib.simple.model.interface_c.Secretary;

public class SecretaryPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SecretaryPO(new Secretary[]{});
      } else {
          return new SecretaryPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.interface_c.util.CreatorCreator.createIdMap(sessionID);
   }
}
