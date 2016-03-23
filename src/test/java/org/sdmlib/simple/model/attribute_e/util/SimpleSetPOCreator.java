package org.sdmlib.simple.model.attribute_e.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.list.SimpleSet;

public class SimpleSetPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SimpleSetPO(new SimpleSet[]{});
      } else {
          return new SimpleSetPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.attribute_e.util.CreatorCreator.createIdMap(sessionID);
   }
}
