package org.sdmlib.models.objects.util;

import org.sdmlib.models.objects.GenericObject;
import org.sdmlib.serialization.interfaces.EntityFactory;

public class GenericObjectCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      GenericObject.PROPERTY_NAME,
      GenericObject.PROPERTY_TYPE,
      GenericObject.PROPERTY_GRAPH,
      GenericObject.PROPERTY_ATTRS,
      GenericObject.PROPERTY_OUTGOINGLINKS,
      GenericObject.PROPERTY_INCOMMINGLINKS,
      GenericObject.PROPERTY_ICON,
   };
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new GenericObject();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((GenericObject) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((GenericObject) target).set(attrName, value);
   }
   
 
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((GenericObject) entity).removeYou();
   }
}



