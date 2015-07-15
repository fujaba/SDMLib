package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.serialization.EntityFactory;

import de.uniks.networkparser.json.JsonIdMap;
import org.sdmlib.models.classes.Annotation;

public class ClazzCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Clazz.PROPERTY_NAME,
      Clazz.PROPERTY_EXTERNAL,
      Clazz.PROPERTY_CLASSMODEL,
      Clazz.PROPERTY_SUPERCLAZZES,
      Clazz.PROPERTY_ATTRIBUTES,
      Clazz.PROPERTY_METHODS,
      Clazz.PROPERTY_ROLES,
      Clazz.PROPERTY_KIDCLAZZES,
      Clazz.PROPERTY_INTERFAZE,
      Clazz.PROPERTY_ANNOTATIONS,
      Clazz.PROPERTY_ABZTRACT,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new ClassModel().createClazz(null);
   }
   
   public static JsonIdMap createIdMap(String sessionID) {
      return CreatorCreator.createIdMap(sessionID);
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

      if (Clazz.PROPERTY_SUPERCLAZZES.equalsIgnoreCase(attrName))
      {
         return ((Clazz) target).getSuperClass();
      }

      if (Clazz.PROPERTY_EXTERNAL.equalsIgnoreCase(attribute))
      {
         return ((Clazz) target).isExternal();
      }

      if (Clazz.PROPERTY_KIDCLAZZES.equalsIgnoreCase(attribute))
      {
         return ((Clazz) target).getKidClazzes();
      }

      if (Clazz.PROPERTY_INTERFAZE.equalsIgnoreCase(attribute))
      {
         return ((Clazz) target).isInterface();
      }

      if (Clazz.PROPERTY_ANNOTATIONS.equalsIgnoreCase(attribute))
      {
         return ((Clazz) target).getAnnotations();
      }

      if (Clazz.PROPERTY_ABZTRACT.equalsIgnoreCase(attribute))
      {
         return ((Clazz) target).isAbztract();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      
      if (Clazz.PROPERTY_CLASSMODEL.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).with((ClassModel) value);
         return true;
      }

      if (Clazz.PROPERTY_ATTRIBUTES.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).with((Attribute) value);
         return true;
      }

      if ((Clazz.PROPERTY_ATTRIBUTES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).without((Attribute) value);
         return true;
      }

      if (Clazz.PROPERTY_METHODS.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).with((Method) value);
         return true;
      }

      if ((Clazz.PROPERTY_METHODS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).without((Method) value);
         return true;
      }

      if (Clazz.PROPERTY_ROLES.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).with((Role) value);
         return true;
      }

      if ((Clazz.PROPERTY_ROLES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).without((Role) value);
         return true;
      }

      if (Clazz.PROPERTY_SUPERCLAZZES.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).withSuperClazz((Clazz) value);
         return true;
      }
      
      if ((Clazz.PROPERTY_SUPERCLAZZES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz)target).setInterface((Boolean) value);
         return true;
      }

      if (Clazz.PROPERTY_EXTERNAL.equalsIgnoreCase(attrName))
      {
         ((Clazz)target).setExternal((Boolean) value);
         return true;
      }

      if (Clazz.PROPERTY_KIDCLAZZES.equalsIgnoreCase(attrName))
      {
         ((Clazz) target).withKidClazzes((Clazz) value);
         return true;
      }
      
      if ((Clazz.PROPERTY_KIDCLAZZES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz) target).withoutKidClazz((Clazz) value);
         return true;
      }

      if (Clazz.PROPERTY_INTERFAZE.equalsIgnoreCase(attrName))
      {
         ((Clazz) target).setInterface((Boolean) value);
         return true;
      }

      if (Clazz.PROPERTY_ANNOTATIONS.equalsIgnoreCase(attrName))
      {
         ((Clazz) target).withAnnotation((Annotation) value);
         return true;
      }
      
      if ((Clazz.PROPERTY_ANNOTATIONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Clazz) target).withoutAnnotation((Annotation) value);
         return true;
      }

      if (Clazz.PROPERTY_ABZTRACT.equalsIgnoreCase(attrName))
      {
         ((Clazz) target).withAbztract((Boolean) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Clazz) entity).removeYou();
   }
}
