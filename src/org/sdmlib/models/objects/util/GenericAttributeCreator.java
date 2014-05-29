package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.serialization.EntityFactory;

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
      int pos = attrName.indexOf('.');
      String attribute = attrName;
      
      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (GenericAttribute.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((GenericAttribute)target).getName();
      }

      if (GenericAttribute.PROPERTY_VALUE.equalsIgnoreCase(attribute))
      {
         return ((GenericAttribute)target).getValue();
      }

      if (GenericAttribute.PROPERTY_OWNER.equalsIgnoreCase(attribute))
      {
         return ((GenericAttribute)target).getOwner();
      }
      return super.getValue(target, attribute);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (GenericAttribute.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((GenericAttribute)target).setName((String) value);
         return true;
      }

      if (GenericAttribute.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((GenericAttribute)target).setValue((String) value);
         return true;
      }

      if (GenericAttribute.PROPERTY_OWNER.equalsIgnoreCase(attrName))
      {
         ((GenericAttribute)target).setOwner((GenericObject) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericAttribute) entity).removeYou();
   }
}


