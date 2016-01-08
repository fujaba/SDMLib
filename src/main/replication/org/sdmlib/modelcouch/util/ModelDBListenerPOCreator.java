package org.sdmlib.modelcouch.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.modelcouch.ModelDBListener;

public class ModelDBListenerPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ModelDBListenerPO(new ModelDBListener[]{});
      } else {
          return new ModelDBListenerPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.modelcouch.util.CreatorCreator.createIdMap(sessionID);
   }
}
