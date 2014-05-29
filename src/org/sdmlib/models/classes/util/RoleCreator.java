package org.sdmlib.models.classes.util;

import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.serialization.EntityFactory;

public class RoleCreator extends EntityFactory
{
   private final String[] properties = new String[]
   {
      Role.PROPERTY_NAME,
      Role.PROPERTY_CARD,
      Role.PROPERTY_KIND,
      Role.PROPERTY_CLAZZ,
      Role.PROPERTY_ASSOC,
   };
   
   @Override
   public String[] getProperties()
   {
      return properties;
   }
   
   @Override
   public Object getSendableInstance(boolean reference)
   {
      return new Role();
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

      if (Role.PROPERTY_NAME.equalsIgnoreCase(attribute))
      {
         return ((Role)target).getName();
      }

      if (Role.PROPERTY_CARD.equalsIgnoreCase(attribute))
      {
         return ((Role)target).getCard();
      }

      if (Role.PROPERTY_KIND.equalsIgnoreCase(attribute))
      {
         return ((Role)target).getKind();
      }

      if (Role.PROPERTY_CLAZZ.equalsIgnoreCase(attribute))
      {
         return ((Role)target).getClazz();
      }

      if (Role.PROPERTY_ASSOC.equalsIgnoreCase(attribute))
      {
         return ((Role)target).getAssoc();
      }
      return super.getValue(target, attrName);
   }
   
   @Override
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      if (Role.PROPERTY_KIND.equalsIgnoreCase(attrName))
      {
         ((Role)target).setKind((String) value);
         return true;
      }

      if (Role.PROPERTY_CARD.equalsIgnoreCase(attrName))
      {
         ((Role)target).setCard((String) value);
         return true;
      }

      if (Role.PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         ((Role)target).setName((String) value);
         return true;
      }

      if (Role.PROPERTY_CLAZZ.equalsIgnoreCase(attrName))
      {
         ((Role)target).setClazz((Clazz) value);
         return true;
      }

      if (Role.PROPERTY_ASSOC.equalsIgnoreCase(attrName))
      {
         ((Role)target).setAssoc((Association) value);
         return true;
      }
      return super.setValue(target, attrName, value, type);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Role) entity).removeYou();
   }
}



