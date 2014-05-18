package org.sdmlib.examples.helloworld.util;

import org.sdmlib.examples.helloworld.Greeting;
import org.sdmlib.examples.helloworld.GreetingMessage;
import org.sdmlib.examples.helloworld.Person;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

public class GreetingCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Greeting.PROPERTY_TEXT,
      Greeting.PROPERTY_GREETINGMESSAGE,
      Greeting.PROPERTY_PERSON,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Greeting();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (Greeting.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((Greeting) target).getText();
      }

      if (Greeting.PROPERTY_GREETINGMESSAGE.equalsIgnoreCase(attrName))
      {
         return ((Greeting) target).getGreetingMessage();
      }

      if (Greeting.PROPERTY_PERSON.equalsIgnoreCase(attrName))
      {
         return ((Greeting) target).getPerson();
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

      if (Greeting.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((Greeting) target).setText((String) value);
         return true;
      }

      if (Greeting.PROPERTY_GREETINGMESSAGE.equalsIgnoreCase(attrName))
      {
         ((Greeting) target).setGreetingMessage((GreetingMessage) value);
         return true;
      }

      if (Greeting.PROPERTY_PERSON.equalsIgnoreCase(attrName))
      {
         ((Greeting) target).setPerson((Person) value);
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
      ((Greeting) entity).removeYou();
   }
}









