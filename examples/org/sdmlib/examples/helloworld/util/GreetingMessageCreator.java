package org.sdmlib.examples.helloworld.util;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.helloworld.GreetingMessage;

public class GreetingMessageCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GreetingMessage.PROPERTY_TEXT,
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

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (GreetingMessage.PROPERTY_TEXT.equalsIgnoreCase(attrName))
      {
         ((GreetingMessage) target).setText((String) value);
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
      ((GreetingMessage) entity).removeYou();
   }
}

