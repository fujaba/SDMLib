package org.sdmlib.models.objects.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.objects.GenericAttribute;
import org.sdmlib.models.objects.GenericObject;

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
      if (GenericAttribute.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((GenericAttribute) target).getName();
      }

      if (GenericAttribute.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         return ((GenericAttribute) target).getValue();
      }

      if (GenericAttribute.PROPERTY_OWNER.equalsIgnoreCase(attrName))
      {
         return ((GenericAttribute) target).getOwner();
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

      if (GenericAttribute.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((GenericAttribute) target).setName((String) value);
         return true;
      }

      if (GenericAttribute.PROPERTY_VALUE.equalsIgnoreCase(attrName))
      {
         ((GenericAttribute) target).setValue((String) value);
         return true;
      }

      if (GenericAttribute.PROPERTY_OWNER.equalsIgnoreCase(attrName))
      {
         ((GenericAttribute) target).setOwner((GenericObject) value);
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
      ((GenericAttribute) entity).removeYou();
   }
}
