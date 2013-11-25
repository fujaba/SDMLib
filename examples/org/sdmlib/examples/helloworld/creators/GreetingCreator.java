package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class GreetingCreator extends EntityFactory
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

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Greeting) entity).removeYou();
   }
}



