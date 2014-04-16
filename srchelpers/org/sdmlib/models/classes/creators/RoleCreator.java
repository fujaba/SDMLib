package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.Role;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

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
   
   public String[] getProperties()
   {
      return properties;
   }
   
   public Object getSendableInstance(boolean reference)
   {
      return new Role();
   }
   
   public Object getValue(Object target, String attrName)
   {
      return ((Role) target).get(attrName);
   }
   
   public boolean setValue(Object target, String attrName, Object value, String type)
   {
      return ((Role) target).set(attrName, value);
   }
   
   //==========================================================================
   
   @Override
   public void removeObject(Object entity)
   {
      ((Role) entity).removeYou();
   }
}



