package org.sdmlib.test.examples.maumau.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.maumau.model.Holder;

import de.uniks.networkparser.IdMap;

public class HolderPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new HolderPO(new Holder[]{});
      } else {
          return new HolderPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.maumau.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
