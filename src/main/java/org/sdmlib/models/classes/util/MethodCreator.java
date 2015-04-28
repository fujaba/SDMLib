package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;

import de.uniks.networkparser.json.JsonIdMap;

public class MethodCreator extends SDMLibClassCreator
{
   private final String[] properties = new String[]
   {
      Method.PROPERTY_NAME,
      Method.PROPERTY_PARAMETER,
      Method.PROPERTY_RETURNTYPE,
      Method.PROPERTY_CLAZZ,
      Method.PROPERTY_BODY,
      Method.PROPERTY_ENUMERATION,
      Method.PROPERTY_ANNOTATIONS,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Method(null);
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

      if (Method.PROPERTY_RETURNTYPE.equalsIgnoreCase(attrName))
      {
         return ((Method) target).getReturnType();
      }

      if (Method.PROPERTY_BODY.equalsIgnoreCase(attrName))
      {
         return ((Method) target).getBody();
      }

      if (Method.PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getClazz();
      }

      if (Method.PROPERTY_PARAMETER.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getParameter();
      }

      if (Method.PROPERTY_ENUMERATION.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getEnumeration();
      }

      if (Method.PROPERTY_ANNOTATIONS.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getAnnotations();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (JsonIdMap.REMOVE.equals(type) && value != null)
      {
         attrName = attrName + type;
      }

      if (Method.PROPERTY_RETURNTYPE.equalsIgnoreCase(attrName))
      {
         ((Method) target).setReturnType((org.sdmlib.models.classes.DataType) value);
         return true;
      }

      if (Method.PROPERTY_BODY.equalsIgnoreCase(attrName))
      {
         ((Method) target).setBody((String) value);
         return true;
      }

      if (Method.PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         ((Method) target).setClazz((Clazz) value);
         return true;
      }

      if (Method.PROPERTY_PARAMETER.equalsIgnoreCase(attrName))
      {
         ((Method) target).with((Parameter) value);
         return true;
      }
      
      if ((Method.PROPERTY_PARAMETER + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Method) target).without((Parameter) value);
         return true;
      }

      if (Method.PROPERTY_ENUMERATION.equalsIgnoreCase(attrName))
      {
         ((Method) target).setEnumeration((Enumeration) value);
         return true;
      }

      if (Method.PROPERTY_ANNOTATIONS.equalsIgnoreCase(attrName))
      {
         ((Method) target).withAnnotations((Annotation) value);
         return true;
      }
      
      if ((Method.PROPERTY_ANNOTATIONS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         ((Method) target).withoutAnnotations((Annotation) value);
         return true;
      }

      return super.setValue(target, attrName, value, type);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Method) entity).removeYou();
   }
}
