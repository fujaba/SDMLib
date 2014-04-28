package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.serialization.interfaces.EntityFactory;

public class MethodCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Method.PROPERTY_NAME,
      Method.PROPERTY_PARAMETERS,
      Method.PROPERTY_RETURNTYPE,
      Method.PROPERTY_CLAZZ,
      Method.PROPERTY_BODY
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Method();
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

      if (Method.PROPERTY_PARAMETERS.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getParameters();
      }

      if (Method.PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getClazz();
      }

      if (Method.PROPERTY_RETURNTYPE.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getReturnType();
      }

      if (Method.PROPERTY_BODY.equalsIgnoreCase(attribute))
      {
         return ((Method) target).getBody();
      }
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Method.PROPERTY_PARAMETERS.equalsIgnoreCase(attrName))
      {
         ((Method) target).addToParameter((Parameter) value);
         return true;
      }
      if (Method.PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         ((Method) target).setClazz((Clazz) value);
         return true;
      }
      if (Method.PROPERTY_RETURNTYPE.equalsIgnoreCase(attrName))
      {
         ((Method) target).setReturnType((DataType) value);
         return true;
      }
      if (Method.PROPERTY_BODY.equalsIgnoreCase(attrName))
      {
         ((Method) target).setBody((String) value);
         return true;
      }
      return false;
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Method) entity).removeYou();
   }
}




