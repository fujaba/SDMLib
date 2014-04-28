package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.serialization.interfaces.EntityFactory;

public class GenericAttributeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GenericAttribute.PROPERTY_NAME,
      GenericAttribute.PROPERTY_VALUE,
      GenericAttribute.PROPERTY_OWNER,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new GenericAttribute();
   }
   
   @Override
   public Object getValue(Object target, String attrName)
   {
      return ((GenericAttribute) target).get(attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericAttribute) target).set(attrName, value);
   }

   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericAttribute) entity).removeYou();
   }
}


