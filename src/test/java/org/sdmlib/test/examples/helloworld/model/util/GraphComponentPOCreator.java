package org.sdmlib.test.examples.helloworld.model.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.helloworld.model.GraphComponent;

import de.uniks.networkparser.IdMap;

public class GraphComponentPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
          return new GraphComponentPO(new GraphComponent[]{});
      } else {
          return new GraphComponentPO();
      }
   }
   
   public static IdMap createIdMap(String sessionID) {
       return CreatorCreator.createIdMap(sessionID);
   }
}

