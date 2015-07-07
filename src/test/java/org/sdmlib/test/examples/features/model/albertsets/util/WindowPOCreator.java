package org.sdmlib.test.examples.features.model.albertsets.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.features.model.albertsets.Window;

import de.uniks.networkparser.json.JsonIdMap;

public class WindowPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new WindowPO(new Window[]{});
      } else {
          return new WindowPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.features.model.albertsets.util.CreatorCreator.createIdMap(sessionID);
   }
}
