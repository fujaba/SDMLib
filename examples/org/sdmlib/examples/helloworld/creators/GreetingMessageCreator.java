package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.GreetingMessage;

public class GreetingMessageCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      GreetingMessage.PROPERTY_TEXT,
      GreetingMessage.PROPERTY_GREETING,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GreetingMessage();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GreetingMessage) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GreetingMessage) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

