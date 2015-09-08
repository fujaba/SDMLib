package org.sdmlib.models.taskflows.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import java.lang.Object;

public class ObjectPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ObjectPO(new Object[]{});
      } else {
          return new ObjectPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.models.taskflows.util.CreatorCreator.createIdMap(sessionID);
   }
}
