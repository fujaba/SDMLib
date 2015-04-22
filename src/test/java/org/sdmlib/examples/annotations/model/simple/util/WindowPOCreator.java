package org.sdmlib.examples.annotations.model.simple.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.examples.annotations.model.simple.Window;

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
      return org.sdmlib.examples.annotations.model.simple.util.CreatorCreator.createIdMap(sessionID);
   }
}
