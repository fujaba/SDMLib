package org.sdmlib.models.classes.creators;

import org.sdmlib.models.classes.Association;
import org.sdmlib.serialization.interfaces.EntityFactory;
import org.sdmlib.serialization.json.JsonIdMap;

public class AssociationCreator extends EntityFactory
{
   private final String[] properties = new String[]
   { Association.PROPERTY_MODEL, Association.PROPERTY_SOURCE,
         Association.PROPERTY_TARGET, };

   public String[] getProperties()
   {
      return properties;
   }

   public Object getSendableInstance(boolean reference)
   {
      return new Association();
   }

   public Object getValue(Object target, String attrName)
   {
      return ((Association) target).get(attrName);
   }

   public boolean setValue(Object target, String attrName, Object value,
         String type)
   {
      return ((Association) target).set(attrName, value);
   }

   public static JsonIdMap createIdMap(String sessionID)
   {
      return CreatorCreator.createIdMap(sessionID);
   }

   @Override
   public void removeObject(Object entity)
   {
      ((Association) entity).removeYou();
   }

}
