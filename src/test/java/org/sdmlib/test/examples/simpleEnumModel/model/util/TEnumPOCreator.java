package org.sdmlib.test.examples.simpleEnumModel.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.simpleEnumModel.model.TEnum;

import de.uniks.networkparser.IdMap;

public class TEnumPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new TEnumPO(new TEnum[]{});
      } else {
          return new TEnumPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
      return org.sdmlib.test.examples.simpleEnumModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
