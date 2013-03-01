package org.sdmlib.models.objects.creators;

import org.sdmlib.models.objects.GenericObjectsTest;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class GenericObjectsTestCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GenericObjectsTest();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericObjectsTest) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericObjectsTest) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericObjectsTest) entity).removeYou();
   }
}


