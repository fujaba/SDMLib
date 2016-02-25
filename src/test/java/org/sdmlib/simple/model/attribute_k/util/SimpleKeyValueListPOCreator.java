package org.sdmlib.simple.model.attribute_k.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
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
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.simple.model.attribute_k.util.CreatorCreator.createIdMap(sessionID);
   }
}
