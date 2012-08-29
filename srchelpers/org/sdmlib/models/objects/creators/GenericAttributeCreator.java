package org.sdmlib.models.objects.creators;

import org.sdmlib.models.objects.creators.CreatorCreator;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.objects.GenericAttribute;

public class GenericAttributeCreator implements EntityFactory
{
   private final String[] properties = new String[]
   {
      GenericAttribute.PROPERTY_NAME,
      GenericAttribute.PROPERTY_VALUE,
      GenericAttribute.PROPERTY_OWNER,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GenericAttribute();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericAttribute) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericAttribute) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericAttribute) entity).removeYou();
   }
}


