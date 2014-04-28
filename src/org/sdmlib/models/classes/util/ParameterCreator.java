package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Parameter;
import org.sdmlib.serialization.interfaces.EntityFactory;

public class ParameterCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
         Parameter.PROPERTY_INITIALIZATION,
         Parameter.PROPERTY_METHOD,
         Parameter.PROPERTY_TYPE,
         Parameter.PROPERTY_NAME
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

      if (Parameter.PROPERTY_INITIALIZATION.equalsIgnoreCase(attribute))
      {
         return ((Parameter) target).getInitialization();
      }

      if (Parameter.PROPERTY_METHOD.equalsIgnoreCase(attribute))
      {
         return ((Parameter) target).getMethod();
      }

      if (Parameter.PROPERTY_TYPE.equalsIgnoreCase(attribute))
      {
         return ((Parameter) target).getType();
      }

      if (Parameter.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Parameter) target).getName();
      }
      return null;
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Parameter.PROPERTY_INITIALIZATION.equalsIgnoreCase(attrName))
      {
         ((Parameter) target).setInitialization((String) value);
         return true;
      }

      if (Parameter.PROPERTY_METHOD.equalsIgnoreCase(attrName))
      {
         ((Parameter) target).setMethod((Method) value);
         return true;
      }

      if (Parameter.PROPERTY_TYPE.equalsIgnoreCase(attrName))
      {
         ((Parameter) target).setType((DataType) value);
         return true;
      }

      if (Parameter.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Parameter) target).setName((String) value);
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
