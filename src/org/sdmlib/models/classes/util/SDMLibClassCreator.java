package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.SDMLibClass;

import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.json.JsonIdMap;

public abstract class SDMLibClassCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
         {
            SDMLibClass.PROPERTY_NAME
         };
   
   /**
    * Calls entity.removeYou().
    *
    * @param entity the entity to be deleted
    */
   public void removeObject(Object entity)
   {
      
   }

   public Object call(Object entity, String method, Object... args)
   {
      return null;
   }

   @Override
   public String[] getProperties()
   {
      return properties;
   }

   @Override
   public Object getValue(Object entity, String attrName)
   {
      int pos = attrName.indexOf('.');
      String attribute = attrName;

      if (pos > 0)
      {
         attribute = attrName.substring(0, pos);
      }

      if (Clazz.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Clazz) entity).getName();
      }
      return null;
   }

   @Override
   public boolean setValue(Object entity, String attrName, Object value,
         String type)
   {
      if (JsonIdMap.REMOVE.equals(type))
      {
         attrName = attrName + type;
      }
      
      if (Clazz.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Clazz)entity).setName((String) value);
         return true;
      }
      return false;
   }   
}
