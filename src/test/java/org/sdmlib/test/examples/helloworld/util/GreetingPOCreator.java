package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.helloworld.Greeting;

import de.uniks.networkparser.IdMap;

public class GreetingPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new GreetingPO(new Greeting[]{});
     } else {
         return new GreetingPO();
     }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

