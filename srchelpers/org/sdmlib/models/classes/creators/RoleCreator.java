package org.sdmlib.models.classes.creators;

import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.models.classes.Role;

public class RoleCreator implements SendableEntityCreator
{
   private final String[] properties = new String[]
   {
      Role.PROPERTY_NAME,
      Role.PROPERTY_CARD,
      Role.PROPERTY_KIND,
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
   
   public boolean setValue(Object target, String attrName, Object value)
   {
      return ((Role) target).set(attrName, value);
   }
   
   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }
}

