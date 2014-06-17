package org.sdmlib.examples.adamandeve.model.util;

import org.sdmlib.examples.adamandeve.model.Eve;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class EvePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new EvePO(new Eve[]{});
      } else {
          return new EvePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
