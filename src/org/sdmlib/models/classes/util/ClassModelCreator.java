package org.sdmlib.models.classes.util;

import org.sdmlib.serialization.EntityFactory;
import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;

public class ClassModelCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ClassModel.PROPERTY_CLASSES,
      ClassModel.PROPERTY_NAME
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ClassModel();
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

      if (ClassModel.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((ClassModel) target).getName();
      }

      if (ClassModel.PROPERTY_CLASSES.equalsIgnoreCase(attribute))
      {
         return ((ClassModel) target).getClasses();
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

      if (ClassModel.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).setName((String) value);
         return true;
      }
      
      if (ClassModel.PROPERTY_CLASSES.equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).addToClasses((Clazz) value);
         return true;
      }

      if ((ClassModel.PROPERTY_CLASSES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).removeFromClasses((Clazz) value);
         return true;
      }

      return false;
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((ClassModel) entity).removeYou();
   }
}
