package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.modelspace.ModelCloud;

public class ModelCloudPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ModelCloudPO(new ModelCloud[]{});
      } else {
          return new ModelCloudPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
