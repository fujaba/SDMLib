package org.sdmlib.models.classes.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.classes.Method;

public class MethodCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Method.PROPERTY_SIGNATURE,
      Method.PROPERTY_CLAZZ,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Method();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Method) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Method) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}




