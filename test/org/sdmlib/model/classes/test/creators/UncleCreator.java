package org.sdmlib.model.classes.test.creators;

import org.sdmlib.model.classes.test.Uncle;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class UncleCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Uncle.PROPERTY_BROTHER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Uncle();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Uncle) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Uncle) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Uncle) entity).removeYou();
   }
}

