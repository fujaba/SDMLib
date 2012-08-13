package org.sdmlib.examples.helloworld.creators;

import org.sdmlib.examples.helloworld.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.examples.helloworld.Person;

public class PersonCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_NAME,
      Person.PROPERTY_GREETING,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Person();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Person) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Person) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Person) entity).removeYou();
   }
}


