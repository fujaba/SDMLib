package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.helloworld.model.Edge;

import de.uniks.networkparser.IdMap;

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
   
   public static IdMap createIdMap(String sessionID) {
       return CreatorCreator.createIdMap(sessionID);
   }
}

