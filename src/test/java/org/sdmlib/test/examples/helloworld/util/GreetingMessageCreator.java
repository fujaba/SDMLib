package org.sdmlib.test.examples.helloworld.util;

import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.test.examples.helloworld.Greeting;
import org.sdmlib.test.examples.helloworld.GreetingMessage;

import de.uniks.networkparser.IdMap;

public class GreetingMessageCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GreetingMessage.PROPERTY_TEXT,
      GreetingMessage.PROPERTY_GREETING,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new GreetingMessage();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      if (GreetingMessage.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         return ((GreetingMessage) target).getText();
      }

      if (GreetingMessage.PROPERTY_GREETING.equalsIgnoreCase(attrName))
      {
         return ((GreetingMessage) target).getGreeting();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (IdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (GreetingMessage.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((GreetingMessage) target).setText((String) value);
         return true;
      }

      if (GreetingMessage.PROPERTY_GREETING.equalsIgnoreCase(attrName))
      {
         ((GreetingMessage) target).setGreeting((Greeting) value);
         return true;
      }

      return false;
   }
   public static IdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GreetingMessage) entity).removeYou();
   }
}






