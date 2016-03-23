package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.models.pattern.util.PatternObjectCreator;
import org.sdmlib.test.examples.helloworld.GreetingMessage;

import de.uniks.networkparser.IdMap;

public class GreetingMessagePOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new GreetingMessagePO(new GreetingMessage[]{});
     } else {
         return new GreetingMessagePO();
     }
   }
   
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

