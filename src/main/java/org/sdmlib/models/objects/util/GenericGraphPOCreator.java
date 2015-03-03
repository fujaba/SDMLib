package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericGraph;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class GenericGraphPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GenericGraphPO(new GenericGraph[]{});
      } else {
          return new GenericGraphPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
       return CreatorCreator.createIdMap(sessionID);
   }
}
