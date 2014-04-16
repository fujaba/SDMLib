package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ClassModelCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      ClassModel.PROPERTY_CLASSES,
      ClassModel.PROPERTY_ASSOCIATIONS,
      ClassModel.PROPERTY_PACKAGENAME,
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

      if (ClassModel.PROPERTY_CLASSES.equalsIgnoreCase(attribute))
      {
         return ((ClassModel)target).getClasses();
      }

      if (ClassModel.PROPERTY_ASSOCIATIONS.equalsIgnoreCase(attribute))
      {
         return ((ClassModel)target).getAssociations();
      }

      if (ClassModel.PROPERTY_PACKAGENAME.equalsIgnoreCase(attribute))
      {
         return ((ClassModel)target).getPackageName();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
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

      if (ClassModel.PROPERTY_ASSOCIATIONS.equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).addToAssociations((Association) value);
         return true;
      }

      if ((ClassModel.PROPERTY_ASSOCIATIONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).removeFromAssociations((Association) value);
         return true;
      }

      if (ClassModel.PROPERTY_PACKAGENAME.equalsIgnoreCase(attrName))
      {
         ((ClassModel) target).setPackageName((String) value);
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




