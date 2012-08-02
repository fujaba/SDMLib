package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.Greeting;

public class GreetingCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Greeting.PROPERTY_GREETINGMESSAGE,
      Greeting.PROPERTY_PERSON,
      Greeting.PROPERTY_TEXT,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Greeting();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Greeting) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Greeting) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}


