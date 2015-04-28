package org.sdmlib.examples.annotations.model.simple.util;

import org.sdmlib.examples.annotations.model.simple.House;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

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
      return org.sdmlib.examples.annotations.model.simple.util.CreatorCreator.createIdMap(sessionID);
   }
}
