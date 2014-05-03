package org.sdmlib.examples.helloworld.util;

import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.serialization.json.SDMLibJsonIdMap;
import org.sdmlib.examples.helloworld.Greeting;

public class GreetingCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Greeting.PROPERTY_TEXT,
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

