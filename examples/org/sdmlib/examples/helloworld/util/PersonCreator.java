package org.sdmlib.examples.helloworld.util;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class PersonCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Person.PROPERTY_NAME,
      Person.PROPERTY_GREETING,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Person();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Person.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getName();
      }

      if (Person.PROPERTY_GREETING.equalsIgnoreCase(attrName))
      {
         return ((Person) target).getGreeting();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Person.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Person) target).setName((String) value);
         return true;
      }

      if (Person.PROPERTY_GREETING.equalsIgnoreCase(attrName))
      {
         ((Person) target).setGreeting((Greeting) value);
         return true;
      }


      return false;
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






