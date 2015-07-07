package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.simpleModel.model.MacList;

import de.uniks.networkparser.json.JsonIdMap;

public class MacListPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new MacListPO(new MacList[]{});
      } else {
          return new MacListPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
