package org.sdmlib.examples.helloworld.util;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.models.pattern.util.PatternObjectCreator;

import de.uniks.networkparser.json.JsonIdMap;

public class PersonPOCreator extends PatternObjectCreator
{
   @Override
   public Object getSendableInstance(boolean reference)
   {
      if(reference) {
         return new PersonPO(new Person[]{});
     } else {
         return new PersonPO();
     }
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

