package org.sdmlib.test.examples.m2m.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.m2m.model.Graph;

import de.uniks.networkparser.json.JsonIdMap;

public class GraphPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GraphPO(new Graph[]{});
      } else {
          return new GraphPO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
   }
}
