package org.sdmlib.examples.simpleEnumModel.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.simpleEnumModel.model.Mac;

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
      return org.sdmlib.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
