package org.sdmlib.test.examples.simpleEnumModel.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.simpleEnumModel.model.Mac;

import de.uniks.networkparser.json.JsonIdMap;

public class MacPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MacPO(new Mac[]{});
      } else {
          return new MacPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
