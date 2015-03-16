package org.sdmlib.examples.simpleModel.model.util;

import java.util.ArrayList;

import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class ArrayListPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new ArrayListPO(new ArrayList[]{});
      } else {
          return new ArrayListPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return org.sdmlib.examples.simpleModel.model.util.CreatorCreator.createIdMap(sessionID);
   }
}
