package org.sdmlib.model.test.superclasses.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.model.test.superclasses.Continent;

public class ContinentCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Continent.PROPERTY_TEST,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Continent();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Continent) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Continent) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}


