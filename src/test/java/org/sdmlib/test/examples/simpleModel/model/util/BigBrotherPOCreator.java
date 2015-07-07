package org.sdmlib.test.examples.simpleModel.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.simpleModel.model.BigBrother;

import de.uniks.networkparser.json.JsonIdMap;

public class BigBrotherPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new BigBrotherPO(new BigBrother[]{});
      } else {
          return new BigBrotherPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.simpleModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
