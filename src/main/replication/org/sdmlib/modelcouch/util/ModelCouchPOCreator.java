package org.sdmlib.modelcouch.util;

import org.sdmlib.modelcouch.ModelCouch;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.IdMap;

public class ModelCouchPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ModelCouchPO(new ModelCouch[]{});
      } else {
          return new ModelCouchPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.modelcouch.util.CreatorCreator.createIdMap(sessionID);
   }
}
