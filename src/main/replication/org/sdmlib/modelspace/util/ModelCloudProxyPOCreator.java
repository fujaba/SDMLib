package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.modelspace.ModelCloudProxy;

public class ModelCloudProxyPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ModelCloudProxyPO(new ModelCloudProxy[]{});
      } else {
          return new ModelCloudProxyPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
