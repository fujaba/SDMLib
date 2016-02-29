package org.sdmlib.simple.model.attribute_h.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.list.SimpleKeyValueList;

public class SimpleKeyValueListPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new SimpleKeyValueListPO(new SimpleKeyValueList[]{});
      } else {
          return new SimpleKeyValueListPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.attribute_h.util.CreatorCreator.createIdMap(sessionID);
   }
}
