package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class ClazzCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Clazz.PROPERTY_NAME,
      Clazz.PROPERTY_INTERFAZE,
      Clazz.PROPERTY_EXTERNAL,
      Clazz.PROPERTY_WRAPPED,
      Clazz.PROPERTY_CLASSMODEL,
      Clazz.PROPERTY_KIDCLASSES,
      Clazz.PROPERTY_SUPERCLASS,
      Clazz.PROPERTY_KIDCLASSESASINTERFACE,
      Clazz.PROPERTY_INTERFACES,
      Clazz.PROPERTY_ATTRIBUTES,
      Clazz.PROPERTY_METHODS,
      Clazz.PROPERTY_ROLES
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Clazz();
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

      if (Clazz.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getName();
      }

      if (Clazz.PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getClassModel();
      }

      if (Clazz.PROPERTY_ATTRIBUTES.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getAttributes();
      }

      if (Clazz.PROPERTY_METHODS.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getMethods();
      }

      if (Clazz.PROPERTY_ROLES.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getRoles();
      }

      if (Clazz.PROPERTY_KIDCLASSES.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getKidClasses();
      }

      if (Clazz.PROPERTY_SUPERCLASS.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getSuperClass();
      }

      if (Clazz.PROPERTY_INTERFAZE.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).isInterfaze();
      }

      if (Clazz.PROPERTY_KIDCLASSESASINTERFACE.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getKidClassesAsInterface();
      }

      if (Clazz.PROPERTY_INTERFACES.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getInterfaces();
      }

      if (Clazz.PROPERTY_EXTERNAL.equalsIgnoreCase(attribute))
      {
         return ((Clazz) target).isExternal();
      }

      if (Clazz.PROPERTY_WRAPPED.equalsIgnoreCase(attribute))
      {
         return ((Clazz) target).getWrapped();
      }

      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      
      if (Clazz.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).setName((String) value);
         return true;
      }

      if (Clazz.PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).withClassModel((ClassModel) value);
         return true;
      }

      if (Clazz.PROPERTY_ATTRIBUTES.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).withAttributes((Attribute) value);
         return true;
      }

      if ((Clazz.PROPERTY_ATTRIBUTES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).removeFromAttributes((Attribute) value);
         return true;
      }

      if (Clazz.PROPERTY_METHODS.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).addToMethods((Method) value);
         return true;
      }

      if ((Clazz.PROPERTY_METHODS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).removeFromMethods((Method) value);
         return true;
      }

      if (Clazz.PROPERTY_ROLES.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).addToRoles((Role) value);
         return true;
      }

      if ((Clazz.PROPERTY_ROLES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).removeFromRoles((Role) value);
         return true;
      }

      if (Clazz.PROPERTY_KIDCLASSES.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).withKidClasses((Clazz) value);
         return true;
      }

      if ((Clazz.PROPERTY_KIDCLASSES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).removeFromKidClasses((Clazz) value);
         return true;
      }

      if (Clazz.PROPERTY_SUPERCLASS.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).setSuperClass((Clazz) value);
         return true;
      }

      if (Clazz.PROPERTY_INTERFAZE.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).setInterfaze((Boolean) value);
         return true;
      }

      if (Clazz.PROPERTY_KIDCLASSESASINTERFACE.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).addToKidClassesAsInterface((Clazz) value);
         return true;
      }

      if ((Clazz.PROPERTY_KIDCLASSESASINTERFACE + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).removeFromKidClassesAsInterface((Clazz) value);
         return true;
      }

      if (Clazz.PROPERTY_INTERFACES.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).addToInterfaces((Clazz) value);
         return true;
      }

      if ((Clazz.PROPERTY_INTERFACES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).removeFromInterfaces((Clazz) value);
         return true;
      }

      if (Clazz.PROPERTY_EXTERNAL.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).setExternal((Boolean) value);
         return true;
      }

      if (Clazz.PROPERTY_WRAPPED.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).setWrapped((Boolean) value);
         return true;
      }
      return false;
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Clazz) entity).removeYou();
   }
}


