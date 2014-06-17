package org.sdmlib.examples.helloworld.model.util;

import org.sdmlib.examples.helloworld.model.Edge;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class EdgePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new EdgePO(new Edge[]{});
      } else {
          return new EdgePO();
      }
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
       return CreatorCreator.createIdMap(sessionID);
   }
}

