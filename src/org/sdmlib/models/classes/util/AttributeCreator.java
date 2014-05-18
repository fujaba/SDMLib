package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.serialization.EntityFactory;

public class AttributeCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Attribute.PROPERTY_INITIALIZATION,
      Attribute.PROPERTY_CLAZZ,
      Attribute.PROPERTY_TYPE,
      Attribute.PROPERTY_NAME,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Attribute();
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

      if (Attribute.PROPERTY_INITIALIZATION.equalsIgnoreCase(attribute))
      {
         return ((Attribute) target).getInitialization();
      }

      if (Attribute.PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return ((Attribute) target).getClazz();
      }

      if (Attribute.PROPERTY_TYPE.equalsIgnoreCase(attribute))
      {
         return ((Attribute) target).getType();
      }

      if (Attribute.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Attribute) target).getName();
      }
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Attribute.PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         ((Attribute) target).setInitialization((String) value);
         return true;
      }

      if (Attribute.PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         ((Attribute) target).setClazz((Clazz) value);
         return true;
      }

      if (Attribute.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((Attribute) target).setType((DataType) value);
         return true;
      }

      if (Attribute.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Attribute) target).setName((String) value);
         return true;
      }

      return false;
   }
     
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Attribute) entity).removeYou();
   }
}





