package org.sdmlib.examples.helloworld.util;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

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
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

