package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.SDMLibClass;
import org.sdmlib.models.classes.Value;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;

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

      if (Value.PROPERTY_INITIALIZATION.equalsIgnoreCase(attribute))
      {
         return ((Value) target).getInitialization();
      }

      if (Value.PROPERTY_TYPE.equalsIgnoreCase(attribute))
      {
         return ((Value) target).getType();
      }

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((SDMLibClass) target).getName();
      }

      if (Attribute.PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return ((Attribute) target).getClazz();
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

      if (Value.PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         ((Value) target).setInitialization((String) value);
         return true;
      }

      if (Value.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((Value) target).setType((org.sdmlib.models.classes.DataType) value);
         return true;
      }

      if (SDMLibClass.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((SDMLibClass) target).setName((String) value);
         return true;
      }

      if (Attribute.PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         ((Attribute) target).setClazz((Clazz) value);
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
