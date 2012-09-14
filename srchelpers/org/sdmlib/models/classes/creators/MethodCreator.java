package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.Method;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class MethodCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Method.PROPERTY_SIGNATURE,
      Method.PROPERTY_RETURNTYPE,
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
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Method) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Method) entity).removeYou();
   }
}



