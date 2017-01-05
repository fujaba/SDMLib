package org.sdmlib.modelspace.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.modelspace.ModelSpaceProxy;

import de.uniks.networkparser.IdMap;

public class ModelSpaceProxyPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ModelSpaceProxyPO(new ModelSpaceProxy[]{});
      } else {
          return new ModelSpaceProxyPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.modelspace.util.CreatorCreator.createIdMap(sessionID);
   }
}
