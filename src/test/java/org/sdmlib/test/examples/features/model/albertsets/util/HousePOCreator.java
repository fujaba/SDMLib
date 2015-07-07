package org.sdmlib.test.examples.features.model.albertsets.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.features.model.albertsets.House;

import de.uniks.networkparser.json.JsonIdMap;

public class HousePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new HousePO(new House[]{});
      } else {
          return new HousePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.features.model.albertsets.util.CreatorCreator.createIdMap(sessionID);
   }
}
