package org.sdmlib.examples.simpleEnumModel.model.util;

import org.sdmlib.examples.simpleEnumModel.model.Alex;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class AlexPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new AlexPO(new Alex[]{});
      } else {
          return new AlexPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
